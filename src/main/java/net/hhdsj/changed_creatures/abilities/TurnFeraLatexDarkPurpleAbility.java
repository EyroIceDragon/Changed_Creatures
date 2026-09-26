package net.hhdsj.changed_creatures.abilities;

import net.ltxprogrammer.changed.ability.AbstractAbility;
import net.ltxprogrammer.changed.ability.IAbstractChangedEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class TurnFeraLatexDarkPurpleAbility extends AbstractAbility<TurnFeralLatexDarkpurpleAbilityInstance> {
    public TurnFeraLatexDarkPurpleAbility() {
        super(TurnFeralLatexDarkpurpleAbilityInstance::new);
    }

    @Override
    public Component getAbilityName(IAbstractChangedEntity entity) {
        return Component.translatable("changed_addon.ability.turn_feral");
    }

    public ResourceLocation getTextureLocation(IAbstractChangedEntity entity) {
        return ResourceLocation.fromNamespaceAndPath("changed_creatures","textures/screens/ability/normal_paw.png");
    }

    @Nullable
    @Override
    public Component getSelectedDisplayText(IAbstractChangedEntity entity) {
        return super.getSelectedDisplayText(entity);
    }

    @Override
    public int getChargeTime(IAbstractChangedEntity entity) {
        return 32;
    }

    @Override
    public int getCoolDown(IAbstractChangedEntity entity) {
        return 30;
    }

    @Override
    public UseType getUseType(IAbstractChangedEntity entity) {
        return UseType.CHARGE_TIME;
    }
}
