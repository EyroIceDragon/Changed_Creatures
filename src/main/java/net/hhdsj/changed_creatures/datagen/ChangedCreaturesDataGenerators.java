package net.hhdsj.changed_creatures.datagen;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.ExistingFileHelper;
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
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        // 战利品表（实体 + 方块）
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

        // 群系生成
        generator.addProvider(
                event.includeServer(),
                new ChangedCreaturesBiomeSpawnProvider(generator.getPackOutput())
        );

        generator.addProvider(
                event.includeServer(),
                new ChangedCreaturesCompatibilityDataProvider(generator.getPackOutput())
        );

        // 配方
        generator.addProvider(
                event.includeServer(),
                new ChangedCreaturesRecipeProvider(generator.getPackOutput())
        );

        // 物品模型
        generator.addProvider(
                event.includeClient(),
                new ChangedCreaturesItemModelProvider(generator.getPackOutput(), existingFileHelper)
        );
    }
}