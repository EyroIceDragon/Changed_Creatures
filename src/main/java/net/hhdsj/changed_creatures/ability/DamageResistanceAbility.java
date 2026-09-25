package net.hhdsj.changed_creatures.ability;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.ability.data.AbstractAbility;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class DamageResistanceAbility extends AbstractAbility {

    private static final float REDUCTION_PER_LEVEL = 0.10F;
    private static final float MAX_REDUCTION = 0.80F;

    public DamageResistanceAbility() {
        super("ability.changed_creatures.damage_resistance", 0xFF5555FF, 5);
    }

    @Override
    public ResourceLocation getAbilityTexture() {
        return new ResourceLocation(ChangedCreature.MODID, "textures/gui/ability/latex_ability_damage_resistance.png");
    }

    public float getReduction(int level) {
        if (level <= 0) return 0.0F;
        return Math.min(REDUCTION_PER_LEVEL * level, MAX_REDUCTION);
    }

    @Override
    public void onHurt(Player player, LivingEntity attacker, int level) {

        if (level <= 0 || player.level().isClientSide) return;

    }

    @Override
    public void appendHoverText(List<Component> list, Player player, int level) {
        list.add(Component.translatable("ability.changed_creatures.damage_resistance.desc"));
        list.add(Component.translatable(
                "ability.changed_creatures.damage_resistance.value",
                (int) (getReduction(level) * 100)
        ));
    }
}