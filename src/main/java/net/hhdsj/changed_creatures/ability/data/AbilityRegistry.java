package net.hhdsj.changed_creatures.ability.data;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.init.ChangedCreatureRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryManager;

import java.util.Collection;
import java.util.List;

@Mod.EventBusSubscriber(modid = ChangedCreature.MODID)
public class AbilityRegistry {

    public static AbstractAbility get(ResourceLocation id) {
        var registry = RegistryManager.ACTIVE.getRegistry(ChangedCreatureRegistry.ABILITY);
        if (registry == null) return null;
        return registry.getValue(id);
    }

    public static Collection<AbstractAbility> getAll() {
        var registry = RegistryManager.ACTIVE.getRegistry(ChangedCreatureRegistry.ABILITY);
        if (registry == null) return List.of();
        return registry.getValues();
    }

    public static boolean contains(ResourceLocation id) {
        var registry = RegistryManager.ACTIVE.getRegistry(ChangedCreatureRegistry.ABILITY);
        if (registry == null) return false;
        return registry.containsKey(id);
    }
}