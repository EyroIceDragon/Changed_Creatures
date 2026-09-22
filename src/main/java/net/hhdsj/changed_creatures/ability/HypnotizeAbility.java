package net.hhdsj.changed_creatures.ability;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.ability.data.AbstractAbility;
import net.ltxprogrammer.changed.entity.ChangedEntity;
import net.ltxprogrammer.changed.entity.TransfurCause;
import net.ltxprogrammer.changed.entity.TransfurContext;
import net.ltxprogrammer.changed.entity.variant.TransfurVariant;
import net.ltxprogrammer.changed.process.ProcessTransfur;
import net.ltxprogrammer.changed.util.CameraUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class HypnotizeAbility extends AbstractAbility {
    //技能图标
    private final ResourceLocation texture = new ResourceLocation(ChangedCreature.MODID,"textures/gui/ability/latex_ability_hypnotize.png");
    private final Set<UUID> attackedPlayers = new HashSet<>();

    public HypnotizeAbility() {
        super("◈ 催眠 ", 0xFFFFFF, 2);
    }

    @Override
    public ResourceLocation getAbilityTexture() {
        return texture;
    }

    @Override
    public void onTick(Player player, int exp_level) {
        if (exp_level == 0) return;
        Level level = player.level();
        if (level.isClientSide) return;

        var playerVariantInstance = ProcessTransfur.getPlayerTransfurVariant(player);
        if (playerVariantInstance == null) return;

        TransfurVariant<?> playerVariant = playerVariantInstance.getParent();
        if (playerVariant == null) return;

        List<Player> targets = level.getEntitiesOfClass(
                Player.class,
                player.getBoundingBox().inflate(3.0D + exp_level),
                e -> e != player
                        && e.isAlive()
                        && player.hasLineOfSight(e)
                        && TransfurVariant.getEntityVariant(e) == null
                        && attackedPlayers.contains(e.getUUID())
        );

        //清理已失效的目标
        Set<UUID> validIds = new HashSet<>();
        for (Player p : targets) validIds.add(p.getUUID());
        attackedPlayers.removeIf(id -> !validIds.contains(id));

        float amount = 0.2F + exp_level * 0.05F;
        float max = (float) ProcessTransfur.getEntityTransfurTolerance(player);

        for (Player target : targets) {
            if (ProcessTransfur.getPlayerTransfurVariant(target)==null) continue;
            float old = ProcessTransfur.getPlayerTransfurProgress(target);
            float next = old + amount;

            if (next >= max && old < max) {
                ProcessTransfur.setPlayerTransfurProgress(target, 0.0F);
                ProcessTransfur.setPlayerTransfurVariant(
                        target, playerVariant,
                        TransfurContext.hazard(TransfurCause.GRAB_REPLICATE),
                        1.0F
                );
                attackedPlayers.remove(target.getUUID());
            } else {
                ProcessTransfur.setPlayerTransfurProgress(target, next);
            }

            level.getNearbyEntities(
                    Player.class,
                    TargetingConditions.DEFAULT,
                    player,
                    AABB.ofSize(player.position(), 4.0D, 4.0D, 4.0D)
            ).forEach(Player -> {

                double dot = Player.getLookAngle()
                        .normalize()
                        .dot(player.getEyePosition()
                                .subtract(Player.getEyePosition())
                                .normalize());
                if (dot < 0.85D) return;

                CameraUtil.tugEntityLookDirection(Player, player, 0.1D);
                if (exp_level >= 1) {
                    Player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 5, 2, false, false), player);
                }
                if (exp_level == 2) {
                    Player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 5, 2, false, false), player);
                }
            });
        }
    }

    @Override
    public void onHurt(Player player, Entity attack, int exp_level) {
        Level level = player.level();
        if (exp_level == 0) return;

        // 只记录被玩家攻击的玩家目标
        if (attack instanceof Player targetPlayer && targetPlayer != player) {
            attackedPlayers.add(targetPlayer.getUUID());
        }

        level.getNearbyEntities(
                Mob.class,
                TargetingConditions.DEFAULT,
                player,
                AABB.ofSize(player.position(), 3.0D, 3.0D, 3.0D)
        ).forEach(mob -> {
            if (mob instanceof ChangedEntity) return;
            if (mob.getTarget() != null && mob.getTarget().is(player)) {
                mob.setTarget(null);
            }
        });

        
    }

    @Override
    public void appendHoverText(List<Component> list, Player player, int level) {
        list.add(Component.literal("以目光为锁链，以低语为牢笼。").withStyle(ChatFormatting.GRAY));
        list.add(Component.literal("让未转化的玩家注视你，并且逐渐侵蚀其意志。").withStyle(ChatFormatting.GRAY));

        if (level <= 0) {
            list.add(Component.literal("§7当前等级: §f未解锁").withStyle(ChatFormatting.GRAY));
            list.add(Component.literal("§8提升等级以激活此能力。").withStyle(ChatFormatting.DARK_GRAY));
        } else {
            list.add(Component.literal("§7效果范围: §f" + (3.0D + level) + " 格").withStyle(ChatFormatting.GRAY));
            list.add(Component.literal("§7感染速度: §f" + (0.2F + level * 0.05F) + " / tick").withStyle(ChatFormatting.GRAY));

            list.add(Component.literal("§a· 强制目标视线跟随").withStyle(ChatFormatting.GREEN));
            list.add(Component.literal("§a· 施加缓慢 II").withStyle(ChatFormatting.GREEN));
            if (level >= 2) {
                list.add(Component.literal("§a· 施加挖掘疲劳 II").withStyle(ChatFormatting.GREEN));
            }

            list.add(Component.literal("§7对未转化玩家发动攻击可标记目标。").withStyle(ChatFormatting.DARK_GRAY));
        }

        String whisper;
        if (level < 1) {
            whisper = "他们的脚步，开始变得迟疑……";
        } else if (level < 2) {
            whisper = "他们的意志，正在一寸寸融化……";
        } else {
            whisper = "他们的灵魂，已在你眼中沉没.";
        }

        list.add(Component.literal(whisper)
                .withStyle(ChatFormatting.DARK_PURPLE, ChatFormatting.ITALIC));
    }
}