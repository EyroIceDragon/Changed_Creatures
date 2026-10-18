package net.hhdsj.changed_creatures.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.hhdsj.changed_creatures.init.ChangedCreatureModEntities;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.CompletableFuture;

public class ChangedCreaturesBiomeSpawnProvider implements DataProvider {

    private final PackOutput output;

    public ChangedCreaturesBiomeSpawnProvider(PackOutput output) {
        this.output = output;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        List<CompletableFuture<?>> futures = new ArrayList<>();

        Map<String, List<SpawnEntry>> groups = new LinkedHashMap<>();

        groups.put("plains", List.of(
                entry(ChangedCreatureModEntities.LATEX_ORANGE_FOX, 4, 1, 2),
                entry(ChangedCreatureModEntities.LATEXRADIATIONFOX, 4, 1, 2),
                entry(ChangedCreatureModEntities.DARKFU_LATEX_WOLF_MALE, 6, 1, 2),
                entry(ChangedCreatureModEntities.LATEXPURPLEWSWOLF, 2, 1, 2),
                entry(ChangedCreatureModEntities.LATEXTHREEMONTHWOLF, 3, 1, 2),
                entry(ChangedCreatureModEntities.LATEXPRTPLECOOCWOLF, 1, 1, 2),
                entry(ChangedCreatureModEntities.LATEXFRUITDRAGONWOLF, 2, 1, 2),
                entry(ChangedCreatureModEntities.BLACKPUPMALE, 2, 1, 2),
                entry(ChangedCreatureModEntities.WHITEBLUEPUP, 1, 1, 2),
                entry(ChangedCreatureModEntities.INKSUGER, 1, 1, 2),
                entry(ChangedCreatureModEntities.LATEXICE_DRAGON, 3, 1, 2),
                entry(ChangedCreatureModEntities.LATEXYUNXQICEDRAGON, 3, 1, 2),
                entry(ChangedCreatureModEntities.LATEXBLUEDRAGON, 3, 1, 2),
                entry(ChangedCreatureModEntities.LATEXYUNQIICEDRAGON, 3, 1, 2),
                entry(ChangedCreatureModEntities.LATEXDARKPURPLEDRAGONTAUR, 3, 1, 2),
                entry(ChangedCreatureModEntities.LATEXDARKPURPLEDRAGON, 3, 1, 2),
                entry(ChangedCreatureModEntities.LATEXIQGOLDDRAGON, 3, 1, 2),
                entry(ChangedCreatureModEntities.LATEX_EMBERSCALES_MAGMA_DRAGON_MALE, 3, 1, 1)
        ));

        groups.put("beach", List.of(
                entry(ChangedCreatureModEntities.LATEX_CHENG_XI, 10, 1, 2),
                entry(ChangedCreatureModEntities.LATEXLUOHONGEARLYSPRINGFOXDRAGON, 10, 1, 2),
                entry(ChangedCreatureModEntities.LATEXIQGOLDDRAGON, 6, 1, 2)
        ));

        groups.put("crimson_forest", List.of(
                entry(ChangedCreatureModEntities.LATEXNIGHTOWLDRAGON, 1, 1, 2),
                entry(ChangedCreatureModEntities.LATEXYUNXQHOTDRAGON, 2, 1, 2)
        ));

        groups.put("desert", List.of(
                entry(ChangedCreatureModEntities.LATEX_PEAUNT_BUTTER_WOLF, 6, 1, 2),
                entry(ChangedCreatureModEntities.LATEX_STEPPEN_WOLF, 6, 1, 2)
        ));

        groups.put("nether", List.of(
                entry(ChangedCreatureModEntities.LATEX_EMBERSCALES_MAGMA_DRAGON_MALE, 4, 1, 1)
        ));

        groups.put("river", List.of(
                entry(ChangedCreatureModEntities.LATEX_KCAHRA_SHARK, 2, 1, 1)
        ));

        groups.put("snowy", List.of(
                entry(ChangedCreatureModEntities.LATEX_FROST_SCALE_DRAGON_TAUR, 6, 1, 2)
        ));

        String[] biomeTags = {
                "#forge:is_plains", "#minecraft:is_beach", "minecraft:crimson_forest",
                "#forge:is_desert", "#forge:nether", "#minecraft:is_river", "#forge:is_snowy"
        };
        String[] keys = {"plains", "beach", "crimson_forest", "desert", "nether", "river", "snowy"};

        for (int i = 0; i < keys.length; i++) {
            JsonObject root = new JsonObject();
            root.addProperty("type", "forge:add_spawns");
            root.addProperty("biomes", biomeTags[i]);
            JsonArray spawners = new JsonArray();
            for (SpawnEntry e : groups.get(keys[i])) {
                ResourceLocation id = ForgeRegistries.ENTITY_TYPES.getKey(e.entity.get());
                if (id == null) continue;
                JsonObject s = new JsonObject();
                s.addProperty("type", id.toString());
                s.addProperty("weight", e.weight);
                s.addProperty("minCount", e.min);
                s.addProperty("maxCount", e.max);
                spawners.add(s);
            }
            root.add("spawners", spawners);

            Path path = output.getOutputFolder(PackOutput.Target.DATA_PACK)
                    .resolve("data/changed_creatures/forge/biome_modifier/" + keys[i] + "_spawners.json");
            futures.add(DataProvider.saveStable(cache, root, path));
        }

        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
    }

    private static SpawnEntry entry(RegistryObject<? extends net.minecraft.world.entity.EntityType<?>> entity,
                                    int weight, int min, int max) {
        return new SpawnEntry(entity, weight, min, max);
    }

    private record SpawnEntry(RegistryObject<? extends net.minecraft.world.entity.EntityType<?>> entity,
                              int weight, int min, int max) {}

    @Override
    public String getName() {
        return "ChangedCreatures Biome Spawns";
    }
}