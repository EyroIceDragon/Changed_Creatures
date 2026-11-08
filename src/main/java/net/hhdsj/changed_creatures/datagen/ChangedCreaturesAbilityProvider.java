package net.hhdsj.changed_creatures.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.ability.data.AbstractAbility;
import net.hhdsj.changed_creatures.init.ChangedCreaturesModAbiliiesVariants;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.ltxprogrammer.changed.entity.variant.TransfurVariant;
import net.minecraftforge.registries.RegistryObject;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ChangedCreaturesAbilityProvider implements DataProvider {
    private final PackOutput output;

    public ChangedCreaturesAbilityProvider(PackOutput output) {
        this.output = output;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        List<CompletableFuture<?>> futures = new ArrayList<>();
        futures.add(simpleadd(
                ChangedCreaturesModAbiliiesVariants.ABILITY_TEST,
                ChangedCreaturesModAbiliiesVariants.ABILITY_TEST_VARIANTS,
                "default",
                cache
        ));
        return CompletableFuture.allOf(futures.toArray(new CompletableFuture<?>[0]));
    }

    private CompletableFuture<?> simpleadd(
            RegistryObject<AbstractAbility> ability,
            List<? extends RegistryObject<? extends TransfurVariant<?>>> variants,
            String fileName,
            CachedOutput cache) {
        JsonObject json = new JsonObject();
        json.addProperty("ability", ability.getId().toString());
        json.add("values", getVariantIds(variants));
        return save(json, fileName, cache);
    }

    private CompletableFuture<?> simpleadd(
            List<RegistryObject<AbstractAbility>> abilities,
            List<? extends RegistryObject<? extends TransfurVariant<?>>> variants,
            String fileName,
            CachedOutput cache) {
        JsonObject json = new JsonObject();
        JsonArray abilityIds = new JsonArray();
        if (abilities.size() > 4) {
            ChangedCreature.LOGGER.warn(
                    "Ability data generator entry {} has {} abilities; only the first 4 will be written",
                    fileName,
                    abilities.size());
        }
        abilities.stream().limit(4)
                .forEach(ability -> abilityIds.add(ability.getId().toString()));
        json.add("ability", abilityIds);
        json.add("values", getVariantIds(variants));
        return save(json, fileName, cache);
    }

    private JsonArray getVariantIds(
            List<? extends RegistryObject<? extends TransfurVariant<?>>> variants) {
        JsonArray variantIds = new JsonArray();
        variants.forEach(variant -> variantIds.add(variant.getId().toString()));
        return variantIds;
    }

    private CompletableFuture<?> save(JsonObject json, String fileName, CachedOutput cache) {
        String normalizedFileName = fileName.endsWith(".json")
                ? fileName
                : fileName + ".json";
        Path path = output.getOutputFolder(PackOutput.Target.DATA_PACK)
                .resolve("changed_creatures/ability/" + normalizedFileName);
        return DataProvider.saveStable(cache, json, path);
    }

    @Override
    public String getName() {
        return "ChangedCreatures Ability Assignments";
    }
}
