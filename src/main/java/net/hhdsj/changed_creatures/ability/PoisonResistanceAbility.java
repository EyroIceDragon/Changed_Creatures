package net.hhdsj.changed_creatures.ability;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.ability.data.AbstractAbility;
import net.ltxprogrammer.changed.process.ProcessTransfur;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.List;

public class PoisonResistanceAbility extends AbstractAbility {

    private final ResourceLocation texture =
            ResourceLocation.fromNamespaceAndPath(
                    ChangedCreature.MODID,
                    "textures/gui/ability/latex_ability_poison_resistance.png"
            );

    public PoisonResistanceAbility() {
        super(Component.translatable("ability.changed_creatures.poison_resistance.name"), 0xFFFFFF, 2);
    }

    @Override
    public ResourceLocation getAbilityTexture() {
        return texture;
    }

    @Override
    public float useExp(int level) {
        return 20 + 60 * level * 0.5F;
    }

    @Override
    public void onTick(Player player, int exp_level) {
        if (exp_level == 0) return;

        Level level = player.level();
        if (level.isClientSide) return;

        if (ProcessTransfur.getPlayerTransfurVariant(player) == null) return;

        MobEffectInstance poison = player.getEffect(MobEffects.POISON);
        if (poison == null) return;

        // 高等级：缩短中毒时间
        int duration = poison.getDuration();
        int reduce = switch (exp_level) {
            case 1 -> 1;   // 每 tick 减少 1
            case 2 -> 3;   // 每 tick 减少 3
            default -> 0;
        };

        if (reduce > 0) {
            int newDuration = Math.max(0, duration - reduce);
            if (newDuration <= 0) {
                player.removeEffect(MobEffects.POISON);
            } else {
                player.addEffect(new MobEffectInstance(
                        MobEffects.POISON,
                        newDuration,
                        poison.getAmplifier(),
                        poison.isAmbient(),
                        poison.isVisible(),
                        poison.showIcon()
                ));
            }
        }

        if (exp_level >= 2 && poison.getAmplifier() > 0) {
            player.addEffect(new MobEffectInstance(
                    MobEffects.POISON,
                    Math.max(0, poison.getDuration() - 3),
                    poison.getAmplifier() - 1,
                    poison.isAmbient(),
                    poison.isVisible(),
                    poison.showIcon()
            ));
        }
    }

    @Override
    public void onHurt(Player player, LivingEntity attacker, int exp_level) {
        if (exp_level == 0) return;
        if (player.level().isClientSide) return;

        MobEffectInstance poison = player.getEffect(MobEffects.POISON);
        if (poison == null) return;

        int duration = poison.getDuration();
        int cut = 20 + exp_level * 20;

        int newDuration = Math.max(0, duration - cut);
        if (newDuration <= 0) {
            player.removeEffect(MobEffects.POISON);
        } else {
            player.addEffect(new MobEffectInstance(MobEffects.POISON, newDuration, poison.getAmplifier(), poison.isAmbient(), poison.isVisible(), poison.showIcon()
            ));
        }

        if (exp_level >= 2 && player.getRandom().nextFloat() < 0.5F) {
            player.removeEffect(MobEffects.POISON);
        }
    }

    @Override
    public void appendHoverText(List<Component> list, Player player, int level) {
        list.add(Component.translatable("ability.changed_creatures.poison_resistance.desc1"));
        list.add(Component.translatable("ability.changed_creatures.poison_resistance.desc2"));

        if (level <= 0) {
            list.add(Component.translatable("ability.changed_creatures.poison_resistance.level.locked"));
            list.add(Component.translatable("ability.changed_creatures.poison_resistance.level.locked_hint"));
        } else {
            list.add(Component.translatable("ability.changed_creatures.poison_resistance.effect.duration"));
            list.add(Component.translatable("ability.changed_creatures.poison_resistance.effect.reduce"));

            if (level >= 2) {
                list.add(Component.translatable("ability.changed_creatures.poison_resistance.effect.immune"));
            }

            list.add(Component.translatable("ability.changed_creatures.poison_resistance.hint"));
        }

        String whisperKey;
        if (level < 1) {
            whisperKey = "ability.changed_creatures.poison_resistance.whisper.0";
        } else if (level < 2) {
            whisperKey = "ability.changed_creatures.poison_resistance.whisper.1";
        } else {
            whisperKey = "ability.changed_creatures.poison_resistance.whisper.2";
        }

        list.add(Component.translatable(whisperKey));
    }
}