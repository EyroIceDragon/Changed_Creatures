package net.hhdsj.changed_creatures.datagen;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collections;
import java.util.List;

@Mod.EventBusSubscriber(modid = ChangedCreature.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ChangedCreaturesDataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();

        generator.addProvider(
                event.includeServer(),
                new LootTableProvider(
                        generator.getPackOutput(),
                        Collections.emptySet(),
                        List.of(
                                new LootTableProvider.SubProviderEntry(
                                        ChangedCreaturesEntityLootProvider::new,
                                        LootContextParamSets.ENTITY
                                ),
                                new LootTableProvider.SubProviderEntry(
                                        ChangedCreaturesBlockLootProvider::new,
                                        LootContextParamSets.BLOCK
                                )

                        )
                )
        );

        generator.addProvider(
                event.includeServer(),
                new ChangedCreaturesBiomeSpawnProvider(generator.getPackOutput())
        );
    }
}