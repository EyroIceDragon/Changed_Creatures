package net.hhdsj.changed_creatures.ability;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.ability.data.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;
import java.util.Map;

public class DamageResistanceAbility extends AbstractAbility {
    @Override
    public float useExp(int level) {return 20 + 80 * level * 0.5F;}
    private static final float REDUCTION_PER_LEVEL = 0.10F;
    private static final float MAX_REDUCTION = 0.80F;

    public DamageResistanceAbility() {
        super(Component.translatable("ability.changed_creatures.damage_resistance.name"), 0xFFFFFF, 5);
    }

    @Override
    public ResourceLocation getAbilityTexture() {
        return ChangedCreature.ChangedCreatureResourceLocation("textures/gui/ability/latex_ability_damage_resistance.png");
    }
    public float getReduction(int level) {
        if (level <= 0) return 0.0F;
        return Math.min(REDUCTION_PER_LEVEL * level, MAX_REDUCTION);
    }

    @Override
    public void onHurt(Player player, LivingEntity attacker, int level) {
        if (level <= 0) return;
        if (player.level().isClientSide) return;

        PlayerAbilities abilities = PlayerAbilitiesCapability.get(player);
        int cost = costExp(level);
        int have = abilities.getPlayerExp();
        if (have < cost) return;

        abilities.reduceExp(cost);

        if (player instanceof ServerPlayer sp) {
            GlobalExpSyncPacket.send(sp);
        }

    }

    @Override
    public void appendHoverText(List<Component> list, Player player, int level) {
        list.add(Component.translatable("ability.changed_creatures.damage_resistance.desc1"));
        list.add(Component.translatable("ability.changed_creatures.damage_resistance.desc2"));

        if (level <= 0) {
            list.add(Component.translatable("ability.changed_creatures.damage_resistance.level.locked"));
            list.add(Component.translatable("ability.changed_creatures.damage_resistance.level.locked_hint"));
        } else {
            list.add(Component.translatable("ability.changed_creatures.damage_resistance.reduction",(level * 10)+"%"));
            list.add(Component.translatable("ability.changed_creatures.damage_resistance.cost",
                    costExp(level)));

            list.add(Component.translatable("ability.changed_creatures.damage_resistance.effect.reduce"));

            list.add(Component.translatable("ability.changed_creatures.damage_resistance.hint"));
        }

        String whisperKey;
        if (level < 1) {
            whisperKey = "ability.changed_creatures.damage_resistance.whisper.0";
        } else if (level < 3) {
            whisperKey = "ability.changed_creatures.damage_resistance.whisper.1";
        } else {
            whisperKey = "ability.changed_creatures.damage_resistance.whisper.2";
        }
        list.add(Component.translatable(whisperKey));
    }

    public int costExp(int level) {
        return 1 + level * 2;
    }

    public boolean enoughCostExp(PlayerAbilities abilities,int level){
        int cost = costExp(level);
        int have = abilities.getPlayerExp();
        return have >= cost;
    }
}