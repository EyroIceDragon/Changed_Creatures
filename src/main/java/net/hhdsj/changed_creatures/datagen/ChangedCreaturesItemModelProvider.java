package net.hhdsj.changed_creatures.datagen;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.init.ChangedCreatureModEntities;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class ChangedCreaturesItemModelProvider extends ItemModelProvider {

    public ChangedCreaturesItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ChangedCreature.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        List<ResourceLocation> spawnEggIds = new ArrayList<>();
        for (RegistryObject<?> spawnEgg : ChangedCreatureModEntities.SPAWN_EGGS.values()) {
            spawnEggIds.add(Objects.requireNonNull(
                    spawnEgg.getId(),
                    "Registered spawn egg is missing its registry id"
            ));
        }

        spawnEggIds.sort(Comparator.comparing(ResourceLocation::toString));
        for (ResourceLocation spawnEggId : spawnEggIds) {
            withExistingParent(spawnEggId.getPath(), "item/template_spawn_egg");
        }
    }

    @Override
    public String getName() {
        return "ChangedCreatures Item Models";
    }
}
