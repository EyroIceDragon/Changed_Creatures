package net.hhdsj.changed_creatures.init;

import net.hhdsj.changed_creatures.ability.data.AbstractAbility;
import net.hhdsj.changed_creatures.ability.data.VariantAbilityMap;
import net.ltxprogrammer.changed.entity.variant.TransfurVariant;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ChangedCreaturesModAbiliiesVariants {
    public static final List<RegistryObject<AbstractAbility>> ABILITY_TEST = List.of(
            ChangedCreaturesModNewAbiliies.HYPNOSIE,
            ChangedCreaturesModNewAbiliies.ELECTRIC_RESISTANCE,
            ChangedCreaturesModNewAbiliies.DAMAGE_RESISTANCE,
            ChangedCreaturesModNewAbiliies.POISON_RESISTANCE
    );
    public static final List<RegistryObject<? extends TransfurVariant<?>>> ABILITY_TEST_VARIANTS = List.of(
            ChangedCreatureModTransfurVariants.LATEX_EYRO_END_DRAGON,
            ChangedCreatureModTransfurVariants.LATEX_FROST_SCALE_DRAGON_TAUR
    );

    public static void bindAll() {
        List<ResourceLocation> abilityIds = ABILITY_TEST.stream().map(RegistryObject::getId).toList();
        for (RegistryObject<? extends TransfurVariant<?>> variant : ABILITY_TEST_VARIANTS) {
            VariantAbilityMap.registerIds(variant.getId(), abilityIds);
        }
    }
}