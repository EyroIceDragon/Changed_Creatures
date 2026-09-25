package net.hhdsj.changed_creatures.ability;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.ability.data.AbstractAbility;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class ElectricResistanceAbility extends AbstractAbility {
    private final ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(ChangedCreature.MODID,"textures/gui/ability/latex_ability_anit_lightning.png");
    public ElectricResistanceAbility() {
        super("◈ 抗电", 0xFFFF88, 5);
    }
    @Override
    public void onTick(Player player, int level) {
        super.onTick(player, level);
    }

    @Override
    public void appendHoverText(List<Component> list, Player player, int level) {
        list.add(Component.literal("减少电击的持续时间"));
    }
    @Override
    public ResourceLocation getAbilityTexture() {
        return texture;
    }
}
