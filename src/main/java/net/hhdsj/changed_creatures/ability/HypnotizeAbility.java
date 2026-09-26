package net.hhdsj.changed_creatures.ability;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.ability.data.AbstractAbility;
import net.hhdsj.changed_creatures.util.ProgressTransfurExt;
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
    private final ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(ChangedCreature.MODID,"textures/gui/ability/latex_ability_hypnotize.png");
    private final Set<UUID> attackedPlayers = new HashSet<>();

    public HypnotizeAbility() {
        super(String.valueOf(Component.translatable("ability.changed_creatures.hypnotize.name")), 0xFFFFFF, 2);
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

        Set<UUID> validIds = new HashSet<>();
        for (Player p : targets) validIds.add(p.getUUID());
        attackedPlayers.retainAll(validIds);

        for (Player target : targets) {

            if (ProcessTransfur.getPlayerTransfurVariant(target) != null) continue;

            float amount = 0.2F + exp_level * 0.05F;
            float max = (float) ProcessTransfur.getEntityTransfurTolerance(target);
            float old = ProcessTransfur.getPlayerTransfurProgress(target);
            float next = old + amount;
            //System.out.println("Debug Player : "+ target + " / 进度: " + old);

            if (next >= max && old < max) {
                ProcessTransfur.setPlayerTransfurProgress(target, 0.0F);
                ProcessTransfur.progressTransfur(target, 20f, playerVariant, TransfurContext.hazard(TransfurCause.FACE_HAZARD));
                //attackedPlayers.remove(target.getUUID());
            } else {
                ProcessTransfur.setPlayerTransfurProgress(target, next);
            }

            CameraUtil.tugEntityLookDirection(target, player, 1.25D);

            if (exp_level >= 1) {
                target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 120, 4));
            }
            if (exp_level == 2) {
                target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 60, 2));
            }
        }

    }

    @Override
    public void onAttack(Player player, LivingEntity target, int exp_level) {
        Level level = player.level();
        if (level.isClientSide) return;
        if (exp_level == 0) return;

        if (target instanceof Player targetPlayer && targetPlayer != player) {
            attackedPlayers.add(targetPlayer.getUUID());
        }

//        level.getNearbyEntities(
//                Mob.class,
//                TargetingConditions.DEFAULT,
//                player,
//                AABB.ofSize(player.position(), 3.0D, 3.0D, 3.0D)
//        ).forEach(mob -> {
//            if (mob instanceof ChangedEntity) return;
//            if (mob.getTarget() != null && mob.getTarget().is(player)) {
//                mob.setTarget(null);
//            }
//        });

        
    }

    @Override
    public void appendHoverText(List<Component> list, Player player, int level) {
        list.add(Component.translatable("ability.changed_creatures.hypnotize.desc1"));
        list.add(Component.translatable("ability.changed_creatures.hypnotize.desc2"));

        if (level <= 0) {
            list.add(Component.translatable("ability.changed_creatures.hypnotize.level.locked"));
            list.add(Component.translatable("ability.changed_creatures.hypnotize.level.locked_hint"));
        } else {
            list.add(Component.translatable("ability.changed_creatures.hypnotize.range",
                    (3.0D + level)));
            list.add(Component.translatable("ability.changed_creatures.hypnotize.speed",
                    (0.2F + level * 0.05F)));

            list.add(Component.translatable("ability.changed_creatures.hypnotize.effect.look"));
            list.add(Component.translatable("ability.changed_creatures.hypnotize.effect.slow"));
            if (level >= 2) {
                list.add(Component.translatable("ability.changed_creatures.hypnotize.effect.fatigue"));
            }

            list.add(Component.translatable("ability.changed_creatures.hypnotize.mark_hint"));
        }

        String whisperKey;
        if (level < 1) {
            whisperKey = "ability.changed_creatures.hypnotize.whisper.0";
        } else if (level < 2) {
            whisperKey = "ability.changed_creatures.hypnotize.whisper.1";
        } else {
            whisperKey = "ability.changed_creatures.hypnotize.whisper.2";
        }

        list.add(Component.translatable(whisperKey));
    }
}