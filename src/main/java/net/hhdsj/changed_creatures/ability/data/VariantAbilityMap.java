package net.hhdsj.changed_creatures.ability.data;

import net.hhdsj.changed_creatures.init.ChangedCreaturesModNewAbiliies;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class VariantAbilityMap {
    private static final Map<ResourceLocation, List<ResourceLocation>> MAP = new HashMap<>();
    private static volatile Map<ResourceLocation, List<ResourceLocation>> DATA_MAP = Map.of();
    public static final List<ResourceLocation> DEFAULT_ABILITIES = List.of(
            ChangedCreaturesModNewAbiliies.ELECTRIC_RESISTANCE.getId(),
            ChangedCreaturesModNewAbiliies.DAMAGE_RESISTANCE.getId(),
            ChangedCreaturesModNewAbiliies.ENRAGE.getId(),
            ChangedCreaturesModNewAbiliies.POISON_RESISTANCE.getId()
    );

    @SafeVarargs
    public static void register(ResourceLocation variantId, RegistryObject<AbstractAbility>... abilities) {
        List<ResourceLocation> ids = new ArrayList<>(abilities.length);
        for (RegistryObject<AbstractAbility> ability : abilities) {
            ids.add(ability.getId());
        }
        MAP.put(variantId, List.copyOf(ids));
    }

    public static void register(ResourceLocation variantId, List<RegistryObject<AbstractAbility>> abilities) {
        MAP.put(variantId, abilities.stream().map(RegistryObject::getId).toList());
    }

    public static void registerIds(ResourceLocation variantId, List<ResourceLocation> abilityIds) {
        MAP.put(variantId, List.copyOf(abilityIds));
    }

    public static void setDataDriven(Map<ResourceLocation, List<ResourceLocation>> abilitiesByVariant) {
        Map<ResourceLocation, List<ResourceLocation>> copy = new HashMap<>();
        abilitiesByVariant.forEach((variantId, abilityIds) ->
                copy.put(variantId, List.copyOf(new LinkedHashSet<>(abilityIds))));
        DATA_MAP = Map.copyOf(copy);
    }

    public static List<ResourceLocation> getFor(ResourceLocation variantId) {
        List<ResourceLocation> dataDriven = DATA_MAP.get(variantId);
        if (dataDriven != null) return dataDriven;
        return MAP.getOrDefault(variantId, DEFAULT_ABILITIES);
    }
}