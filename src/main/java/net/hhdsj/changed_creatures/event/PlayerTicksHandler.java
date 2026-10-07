package net.hhdsj.changed_creatures.event;

import net.hhdsj.changed_creatures.init.ChangedCreatureModGameRules;
import net.hhdsj.changed_creatures.init.ChangedCreatureModParticleTypes;
import net.hhdsj.changed_creatures.init.ChangedCreatureModTransfurVariants;
import net.hhdsj.changed_creatures.network.SyncCrystalRingPacket;
import net.hhdsj.changed_creatures.util.ProgressTransfurExt;
import net.hhdsj.changed_creatures.util.PlayerDataGetHelper;
import net.ltxprogrammer.changed.entity.TransfurCause;
import net.ltxprogrammer.changed.entity.TransfurContext;
import net.ltxprogrammer.changed.entity.variant.TransfurVariant;
import net.ltxprogrammer.changed.entity.variant.TransfurVariantInstance;
import net.ltxprogrammer.changed.init.ChangedRegistry;
import net.ltxprogrammer.changed.process.ProcessTransfur;
import net.minecraft.ChatFormatting;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.entity.player.Player;
import net.hhdsj.changed_creatures.network.GoodblockModVariables;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Mod.EventBusSubscriber(value = Dist.DEDICATED_SERVER)
public class PlayerTicksHandler {
    private static final UUID CRYSTAL_SLOW_UUID = UUID.fromString("c8e5c5a4-8f3a-4e2a-9b1c-7d6e5f4a3b2c");
    private static boolean wasFlying = false;
    private static final Random RANDOM = new Random();
    private static final List<String> LIST_VARIANT = new ArrayList<>(List.of(
            "changed_creatures:form_orange_fox",
            "changed_creatures:form_latex_kcahra_shark",
            "changed_creatures:form_blue_dragon",
            "changed_creatures:form_darkfu_latex_wolf_male",
            "changed_creatures:form_latex_ice_dragon",
            "changed_creatures:form_ink_suger",
            "changed_creatures:form_black_pup",
            "changed_creatures:form_white_blue_pup",
            "changed_creatures:form_latex_purplews_wolf",
            "changed_creatures:form_latex_three_month_wolf",
            "changed_creatures:form_latex_yunxq_hot_dragon",
            "changed_creatures:form_latex_yun_xq_ice_dragon",
            "changed_creatures:form_latex_ice_field_wolf_dragon",
            "changed_creatures:form_latex_purple_cooc_wolf",
            "changed_creatures:form_latex_yun_qi_ice_dragon",
            "changed_creatures:form_latex_radiation_fox",
            "changed_creatures:form_latex_night_owl_dragon",
            "changed_creatures:form_latex_iq_gold_dragon",
            "changed_creatures:form_latex_dark_purple_dragon_taur",
            "changed_creatures:form_latex_dark_purple_dragon",
            "changed_creatures:form_latex_luo_hong_early_spring_fox_dragon",
            "changed_creatures:form_latex_neondimness_wolf",
            "changed_creatures:form_latex_cheng_xi_fox",
            "changed_creatures:form_latex_yao_ling_dragon",
            "changed_creatures:form_latex_dragon_fruit_wolf",
            "changed_creatures:form_latex_divine_sword_kitsune",
            "changed_creatures:form_latex_gao_hui_fox",
            "changed_creatures:form_latex_hyazinthe_fox",
            "changed_creatures:form_latex_yue_xi_fox_female",
            "changed_creatures:form_latex_enkang_dragon_shark",
            "changed_creatures:form_latex_qian_gui_shark",
            "changed_creatures:form_latex_dawn_wolf",
            "changed_creatures:form_latex_blueve_cat"
    ));
    private static ResourceLocation FORM_VARIANT = new ResourceLocation("changed_creatures", "form_latex_yunxq_hot_dragon");

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        TransfurVariantInstance<?> TransfurVariant = ProcessTransfur.getPlayerTransfurVariant(player);

        Player_latex_changed(event,player,TransfurVariant);
        Player_crystal_draw(player,TransfurVariant);
        Player_Fire_Particle(player,event);
        Player_Crystal_Jelly_Infection(player,event);

    }
    // 翅膀渲染状态清理仅存在于客户端，服务端专用环境下不得加载任何客户端类，
    // 因此将该逻辑移入独立的、带 Dist.CLIENT 限制的订阅类（见 ClientWingCleanupHandler）。
    public static void Player_crystal_draw(Player player,TransfurVariantInstance<?> variant){
        boolean shouldHaveRing = variant != null && variant.getParent() == ChangedCreatureModTransfurVariants.LATEX_LUOLONG_DRAGON_SHARK.get();

        if (player instanceof ServerPlayer sp) {
            sp.getCapability(CrystalRingHandler.CAPABILITY).ifPresent(c -> {
                if (c.isActive() != shouldHaveRing) {
                    c.setActive(shouldHaveRing);
                    SyncCrystalRingPacket.sync(sp);
                }
            });
        }
    }

    public static void Player_latex_changed(TickEvent.PlayerTickEvent event,Player player,TransfurVariantInstance<?> TransfurVariant) {

        if (player instanceof ServerPlayer serverPlayer) {
            GameType gameMode = serverPlayer.gameMode.getGameModeForPlayer();
            if (gameMode == GameType.CREATIVE || gameMode == GameType.SPECTATOR){
                return;
            }

        }

        if (event.phase == TickEvent.Phase.END && !event.player.level().isClientSide()) {
            GoodblockModVariables.PlayerVariables data = PlayerDataGetHelper.get(player);

            if (!data.Player_Is_Infection) {
                return;
            }

            Level level = event.player.level();
            if (!level.getGameRules().getBoolean(ChangedCreatureModGameRules.TEST_RULE)) {
                return;
            }

            if (TransfurVariant != null) {
                data.Player_Infection_tick = 0;
                data.Player_Is_Infection = true;
                data.syncPlayerVariables(player);
                return;
            }

            data.Player_Infection_tick++;

            if (data.Player_Infection_tick % 100 == 0) {
                data.syncPlayerVariables(player);
            }

            if (data.Player_Infection_tick >= 1000) {
                player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 50, 1));
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 50, 2));
                FORM_VARIANT = new ResourceLocation(LIST_VARIANT.get(RANDOM.nextInt(LIST_VARIANT.size())));
                TransfurVariant<?> variant = ChangedRegistry.TRANSFUR_VARIANT.get().getValue(FORM_VARIANT);
                if (variant != null) {
                    GoodblockProcessTransfur.progressTransfur(player, 1f, variant, TransfurContext.hazard(TransfurCause.FACE_HAZARD));
                }
            }

            if (data.Player_Infection_tick % 100 == 0) {
                //System.out.println("Now Player_Infection_tick : " + data.Player_Infection_tick);
            }
        }
    }

    //=========飞行火焰粒子========//

    private static void Player_Fire_Particle(Player player,TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }

        boolean isFlying = player.getAbilities().flying;

        if (!player.level().isClientSide) {
            if (isFlying && !wasFlying) {
                onPlayerStartFlying(player);
            } else if (!isFlying && wasFlying) {
                onPlayerStopFlying(player);
            }

            if (isFlying) {
                onPlayerFlying(player);
            }

            wasFlying = isFlying;
        }
    }

    private static void onPlayerStartFlying(Player player) {

    }

    private static void onPlayerStopFlying(Player player) {

    }

    private static void onPlayerFlying(Player player) {
        TransfurVariantInstance<?> variant = ProcessTransfur.getPlayerTransfurVariant(player);
        if (variant == null) {
            return;
        }

        if (variant.getParent() == ChangedCreatureModTransfurVariants.LATEX_GAO_HUI_FOX.get()) {
            ServerLevel serverLevel = (ServerLevel) player.level();

            if (player.tickCount % 4 == 0) {
                double x = player.getX();
                double y = player.getY() + 1.0;
                double z = player.getZ();
                double radius = 1.0;
                int particleCount = 4;

                for (int i = 0; i < particleCount; i++) {
                    double angle = (player.tickCount * 15 + i * (360.0 / particleCount)) * Math.PI / 180.0;
                    double offsetX = Math.cos(angle) * radius;
                    double offsetZ = Math.sin(angle) * radius;
                    double offsetY = Math.sin(player.tickCount * 0.2 + i) * 0.15;

                    serverLevel.sendParticles(
                            ChangedCreatureModParticleTypes.GREEN_FLAME.get(),
                            x + offsetX,
                            y + offsetY,
                            z + offsetZ,
                            1,
                            0.0, 0.0, 0.0,
                            0.02
                    );
                }
            }
        }
    }

    public static void Player_Crystal_Jelly_Infection(Player player, TickEvent.PlayerTickEvent event){
        TransfurVariantInstance<?> variant = ProcessTransfur.getPlayerTransfurVariant(player);

        // 定义一个固定的 UUID 用于识别这个减速效果


        if (event.phase == TickEvent.Phase.END && !event.player.level().isClientSide()) {
            GoodblockModVariables.PlayerVariables data = PlayerDataGetHelper.get(player);

            if (variant != null) {
                removeCrystalSlowModifier(player);
                data.Crystal_Jelly_Infection_Progress = 0f;
                data.Crystal_Jelly_Infection = false;
                return;
            }

            if (!data.Crystal_Jelly_Infection) {
                removeCrystalSlowModifier(player);
                return;
            }

            if (data.Crystal_Jelly_Infection_Progress >= 100) {
                System.out.print(player);
                ProgressTransfurExt.tf(player, 10f, "changed_creatures:form_latex_crystal_jelly_dragon", TransfurCause.FACE_HAZARD);
                data.Crystal_Jelly_Infection_Progress = 100f;
                removeCrystalSlowModifier(player);
            } else {
                // 计算减速数值（正值表示减少速度）
                float progress = data.Crystal_Jelly_Infection_Progress;
                float maxSlow = 0.15f;
                float slowAmount = (progress / 100f) * maxSlow;

                applyCrystalSlowModifier(player, slowAmount);

                data.Crystal_Jelly_Infection_Progress += 0.05f;
                if (RANDOM.nextInt(100) < 2) {
                    player.displayClientMessage(Component.literal("你感到身体混在一起了").withStyle((style -> {
                        Style returnStyle = style.withColor(ChatFormatting.BLUE);
                        returnStyle = returnStyle.withItalic(true);
                        return returnStyle;
                    })), true);
                }
            }
            //System.out.println(data.Crystal_Jelly_Infection_Progress);
        }
    }

    // 应用减速修饰符
    private static void applyCrystalSlowModifier(Player player, double amount) {
        var attribute = player.getAttribute(Attributes.MOVEMENT_SPEED);
        if (attribute == null) return;

        // 移除旧的修饰符
        attribute.removeModifier(CRYSTAL_SLOW_UUID);

        // 添加新的修饰符（ADDITION 模式：基础值 - amount）
        AttributeModifier modifier = new AttributeModifier(
                CRYSTAL_SLOW_UUID,
                "crystal_jelly_slow",
                -amount,
                AttributeModifier.Operation.ADDITION
        );
        attribute.addTransientModifier(modifier);
    }

    // 移除减速修饰符
    private static void removeCrystalSlowModifier(Player player) {
        var attribute = player.getAttribute(Attributes.MOVEMENT_SPEED);
        if (attribute != null) {
            attribute.removeModifier(CRYSTAL_SLOW_UUID);
        }
    }
}