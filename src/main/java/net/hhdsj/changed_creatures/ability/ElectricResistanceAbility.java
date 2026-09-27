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
        super(Component.translatable("ability.changed_creatures.electric_resistance.name"), 0xFFFFFF, 5);
    }
    @Override
    public void onTick(Player player, int level) {
        super.onTick(player, level);
    }

    @Override
    public void appendHoverText(List<Component> list, Player player, int level) {
        list.add(Component.translatable("ability.changed_creatures.electric_resistance.desc1"));
        list.add(Component.translatable("ability.changed_creatures.electric_resistance.desc2"));

        if (level <= 0) {
            list.add(Component.translatable("ability.changed_creatures.electric_resistance.level.locked"));
            list.add(Component.translatable("ability.changed_creatures.electric_resistance.level.locked_hint"));
        } else {
            list.add(Component.translatable("ability.changed_creatures.electric_resistance.reduction", level+ "/ tick"
            ));
            list.add(Component.translatable("ability.changed_creatures.electric_resistance.effect.duration"));
            list.add(Component.translatable("ability.changed_creatures.electric_resistance.hint"));
        }

        String whisperKey;
        if (level < 1) {
            whisperKey = "ability.changed_creatures.electric_resistance.whisper.0";
        } else if (level < 3) {
            whisperKey = "ability.changed_creatures.electric_resistance.whisper.1";
        } else {
            whisperKey = "ability.changed_creatures.electric_resistance.whisper.2";
        }
        list.add(Component.translatable(whisperKey));
    }
    @Override
    public ResourceLocation getAbilityTexture() {
        return texture;
    }
}
