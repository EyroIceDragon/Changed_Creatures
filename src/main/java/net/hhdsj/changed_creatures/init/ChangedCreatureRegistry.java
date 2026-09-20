package net.hhdsj.changed_creatures.init;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.ability.data.AbstractAbility;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

@Mod.EventBusSubscriber(modid = ChangedCreature.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ChangedCreatureRegistry {
    public static final ResourceKey<Registry<AbstractAbility>> ABILITY = ResourceKey.createRegistryKey(new ResourceLocation(ChangedCreature.MODID, "ability"));

    @SubscribeEvent
    public static void createRegistries(NewRegistryEvent event) {
        event.create(new RegistryBuilder<AbstractAbility>().setName(ABILITY.location()).setDefaultKey(ABILITY.location()));
    }
}
