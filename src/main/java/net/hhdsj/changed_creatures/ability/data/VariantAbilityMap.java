package net.hhdsj.changed_creatures.ability.data;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.init.ChangedCreaturesModNewAbiliies;
import net.ltxprogrammer.changed.entity.variant.TransfurVariant;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.RegistryObject;

import java.util.*;

public class VariantAbilityMap {
    private static final Map<ResourceLocation, List<RegistryObject<AbstractAbility>>> MAP = new HashMap<>();
    private static final List<RegistryObject<AbstractAbility>> DEFAULT_ABILITIES =
            List.of(ChangedCreaturesModNewAbiliies.ELECTRIC_RESISTANCE,ChangedCreaturesModNewAbiliies.DAMAGE_RESISTANCE);

    @SafeVarargs
    public static void register(ResourceLocation variantId, RegistryObject<AbstractAbility>... abilities) {
        MAP.put(variantId, List.of(abilities));
    }

    public static void register(ResourceLocation variantId, List<RegistryObject<AbstractAbility>> abilities) {
        MAP.put(variantId, List.copyOf(abilities));
    }

    public static List<RegistryObject<AbstractAbility>> getFor(ResourceLocation variantId) {
        List<RegistryObject<AbstractAbility>> result = MAP.get(variantId);
        return result != null ? result : DEFAULT_ABILITIES;
    }
}