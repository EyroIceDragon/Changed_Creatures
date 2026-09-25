package net.hhdsj.changed_creatures.init;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.ability.data.AbstractAbility;
import net.hhdsj.changed_creatures.ability.data.VariantAbilityMap;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ChangedCreaturesModAbiliiesVariants {
    final static List<RegistryObject<AbstractAbility>> ABILITY_TEST = List.of(
            ChangedCreaturesModNewAbiliies.HYPNOSIE,
            ChangedCreaturesModNewAbiliies.ELECTRIC_RESISTANCE,
            ChangedCreaturesModNewAbiliies.DAMAGE_RESISTANCE
    );
    public static void bindAll() {
        VariantAbilityMap.register(
                ChangedCreatureModTransfurVariants.LATEX_EYRO_END_DRAGON.getId(),
                ABILITY_TEST
        );
    }
}