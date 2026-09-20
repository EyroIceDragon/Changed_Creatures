package net.hhdsj.changed_creatures.entity;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.network.KeyMessage;
import net.hhdsj.changed_creatures.util.PlayerDataGetHelper;
import net.ltxprogrammer.changed.entity.variant.TransfurVariantInstance;
import net.ltxprogrammer.changed.process.ProcessTransfur;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = ChangedCreature.MODID)
public class PartiallyTransfurVariant {
    private static final Map<UUID, Boolean> clientJumpPressMap = new HashMap<>();
    private static final Map<UUID, Boolean> serverJumpPressMap = new HashMap<>();
    private static final Map<UUID, Boolean> lastJumpStateMap = new HashMap<>();
    private static final Map<UUID, Boolean> PlayerCanGlideMap = new HashMap<>();
    private static final float HORIZONTAL_PENALTY_SPRINT = 0.725F;
    private static final float HORIZONTAL_PENALTY_WALK = 0.625F;
    private static final float VERTICAL_PENALTY_UP = 0.3F;
    private static final float VERTICAL_PENALTY_DOWN = 0.8F;
    private static final int MIN_FOOD_LEVEL = 6;
    private static final float EXHAUSTION_SPRINT = 0.05F;
    private static final float EXHAUSTION_WALK = 0.025F;
    private static final Map<UUID, Boolean> lastFlightState = new HashMap<>();

    private static boolean isPartiallyTransfurred(Player player) {
        if (player == null) return false;

        TransfurVariantInstance<?> variant = ProcessTransfur.getPlayerTransfurVariant(player);
        return variant != null;
    }

    public static boolean canFly(Player player) {
        if (player == null) return false;
        if (player.isCreative() || player.isSpectator()) return true;
        if (isPartiallyTransfurred(player)) return false;
        if (PlayerDataGetHelper.GetPlayerCanFly(player)) return true;
        return false;
    }

    public static void applyFlightPhysics(Player player) {
        if (player == null) return;
        if (!player.getAbilities().flying) return;
        if (player.isCreative() || player.isSpectator()) return;

        if (!canFly(player) || !meetsFlightCriteria(player)) {
            return;
        }

        float horizontalPenalty = player.isSprinting() ? HORIZONTAL_PENALTY_SPRINT : HORIZONTAL_PENALTY_WALK;
        float verticalPenalty = player.getDeltaMovement().y > 0.0F ? VERTICAL_PENALTY_UP : VERTICAL_PENALTY_DOWN;

        player.setDeltaMovement(
                player.getDeltaMovement().multiply(horizontalPenalty, verticalPenalty, horizontalPenalty)
        );

        if (!player.level().isClientSide) {
            player.causeFoodExhaustion(player.isSprinting() ? EXHAUSTION_SPRINT : EXHAUSTION_WALK);
        }
    }

    public static boolean meetsFlightCriteria(Player player) {
        if (player == null) return false;
        if (player.getFoodData().getFoodLevel() <= MIN_FOOD_LEVEL) return false;
        if (player.isInWater()) return false;
        if (player.getVehicle() != null) return false;
        return true;
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Player player = event.player;
        UUID playerId = player.getUUID();

        // 服务端每帧重置
        if (!player.level().isClientSide) {
            serverJumpPressMap.put(playerId, false);
        }

        applyFlightPhysics(player);
        if (!player.level().isClientSide) {
            autoManageFlight(player);
        }
        Gliding(player);
    }

    private static void autoManageFlight(Player player) {
        UUID playerId = player.getUUID();
        boolean canFly = canFly(player);
        boolean meetsCriteria = meetsFlightCriteria(player);
        boolean shouldHaveFlight = canFly && meetsCriteria;

        boolean currentMayfly = player.getAbilities().mayfly;
        boolean currentFlying = player.getAbilities().flying;

        Boolean lastState = lastFlightState.get(playerId);

        if (lastState != null && lastState == shouldHaveFlight) {
            return;
        }

        if (shouldHaveFlight) {
            if (!currentMayfly) {
                player.getAbilities().mayfly = true;
                player.onUpdateAbilities();
            }
        } else {
            if ((currentMayfly || currentFlying)) {
                player.getAbilities().mayfly = false;
                if (currentFlying) {
                    player.getAbilities().flying = false;
                }
                player.onUpdateAbilities();
            }
        }

        lastFlightState.put(playerId, shouldHaveFlight);
    }

    @SubscribeEvent
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        lastFlightState.remove(event.getEntity().getUUID());
    }

    public static void manageGliding(Player player) {
        Item chestItem = player.getItemBySlot(EquipmentSlot.CHEST).getItem();
        boolean hasElytra = chestItem == Items.ELYTRA;
        if (hasElytra) {
            return;
        }
        boolean currentGlide = PlayerDataGetHelper.GetPlayerCanGliding(player);
        System.out.print("Can Glide : " + currentGlide + "\n");

        player.displayClientMessage(Component.literal("Can Glide : " + currentGlide).withStyle((style -> {
            Style returnStyle = style.withColor(ChatFormatting.WHITE);
            returnStyle = returnStyle.withItalic(true);
            return returnStyle;
        })), true);


    }

    public static void Gliding(Player player) {
        Item chestItem = player.getItemBySlot(EquipmentSlot.CHEST).getItem();
        boolean hasElytra = chestItem == Items.ELYTRA;
        if (hasElytra) {
            return;
        }
        UUID playerId = player.getUUID();
        boolean jumpPressed;
        if (player.level().isClientSide) {
            jumpPressed = clientJumpPressMap.getOrDefault(playerId, false);
        } else {
            jumpPressed = serverJumpPressMap.getOrDefault(playerId, false);
        }

        if (player.isFallFlying()) {
            Vec3 delta = player.getDeltaMovement();
            double horizontalSpeed = Math.sqrt(delta.x * delta.x + delta.z * delta.z);
            double verticalSpeed = Math.abs(delta.y);
            double totalSpeed = Math.sqrt(delta.x * delta.x + delta.y * delta.y + delta.z * delta.z);

            if (player.horizontalCollision) {

                float retainRatio = (float) Math.max(0.05, 0.20 - (horizontalSpeed / 2.0) * 0.15);
                if (horizontalSpeed > 2.0) {
                    retainRatio = 0.05F;
                }

                player.setDeltaMovement(delta.x * retainRatio, delta.y, delta.z * retainRatio);
                player.fallDistance = 0.0F;

                if (!player.level().isClientSide) {
                    float damage = 1.0F + (float) horizontalSpeed * 0.8F;
                    damage = Math.min(damage, 20.0F);
                    damage = Math.max(damage, 0.5F);

                    player.hurt(player.damageSources().flyIntoWall(), damage);

                    float exhaustionCost = 0.02F + (float) horizontalSpeed * 0.02F;
                    player.causeFoodExhaustion(Math.min(exhaustionCost, 0.2F));
                }

                if (player.level().isClientSide) {
                    float pitch = 1.0F + (float) (horizontalSpeed / 4.0);
                    player.level().playSound(player, player.blockPosition(),
                            SoundEvents.PLAYER_HURT, SoundSource.PLAYERS,
                            0.5F + (float) horizontalSpeed * 0.1F, Math.min(pitch, 2.0F));
                }

                if (horizontalSpeed > 1.5) {
                    PlayerCanGlideMap.put(playerId, false);
                    player.stopFallFlying();
                    return;
                }
            }

            if (player.verticalCollision) {
                if (delta.y > 0.5) {
                    player.setDeltaMovement(delta.x, -delta.y * 0.3, delta.z);
                } else {
                    player.setDeltaMovement(delta.x, 0.0, delta.z);
                }
                player.fallDistance = 0.0F;

                if (!player.level().isClientSide && verticalSpeed > 1.0) {
                    float damage = 0.5F + (float) verticalSpeed * 0.5F;
                    damage = Math.min(damage, 4.0F);
                    player.hurt(player.damageSources().flyIntoWall(), damage);
                }
            }
        }

        boolean shouldGlide = !player.onGround() &&
                Math.abs(player.fallDistance) >= 0F &&
                !player.isInWater() &&
                !player.isInLava() &&
                !player.getAbilities().flying &&
                canFly(player) &&
                PlayerDataGetHelper.GetPlayerCanGliding(player);

        boolean shouldStopGliding = player.onGround() ||
                player.isInWater() ||
                player.isInLava() ||
                player.getAbilities().flying ||
                !canFly(player) ||
                !PlayerDataGetHelper.GetPlayerCanGliding(player) ||
                (player.isFallFlying() && player.isShiftKeyDown());

        if (shouldGlide && !player.isFallFlying()) {
            if (player.fallDistance >= 0.6F) {
                if (jumpPressed) {
                    PlayerCanGlideMap.put(playerId, true);
                }
            }
            if (PlayerCanGlideMap.getOrDefault(playerId, false)) {
                player.startFallFlying();
            }
        } else if (shouldStopGliding && player.isFallFlying() && PlayerCanGlideMap.getOrDefault(playerId, false) == true) {
            PlayerCanGlideMap.put(playerId, false);
            player.stopFallFlying();
        }
        if (!canFly(player)){
            PlayerCanGlideMap.put(playerId, false);
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null || mc.screen != null) return;

        UUID playerId = player.getUUID();
        boolean currentJump = mc.options.keyJump.isDown();
        boolean lastState = lastJumpStateMap.getOrDefault(playerId, false);
        boolean pressed = currentJump && !lastState;

        clientJumpPressMap.put(playerId, pressed);

        if (pressed) {
            ChangedCreature.PACKET_HANDLER.sendToServer(new KeyMessage(1, 0));
        }

        lastJumpStateMap.put(playerId, currentJump);
    }

    public static void setServerJumpPressed(UUID playerId) {
        serverJumpPressMap.put(playerId, true);
    }
}