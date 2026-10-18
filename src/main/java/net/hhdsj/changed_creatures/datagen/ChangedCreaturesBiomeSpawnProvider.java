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
                entry(ChangedCreatureModEntities.LATEX_RADIATION_FOX, 4, 1, 2),
                entry(ChangedCreatureModEntities.LATEX_DARKFU_WOLF_MALE, 6, 1, 2),
                entry(ChangedCreatureModEntities.LATEX_PURPLE_WS_WOLF, 2, 1, 2),
                entry(ChangedCreatureModEntities.LATEX_THREE_MONTH_WOLF, 3, 1, 2),
                entry(ChangedCreatureModEntities.LATEX_PRTPLE_COOC_WOLF, 1, 1, 2),
                entry(ChangedCreatureModEntities.LATEX_FRUIT_DRAGON_WOLF, 2, 1, 2),
                entry(ChangedCreatureModEntities.BLACK_PUP_MALE, 2, 1, 2),
                entry(ChangedCreatureModEntities.WHITE_BLUE_PUP, 1, 1, 2),
                entry(ChangedCreatureModEntities.INK_SUGER_PUP, 1, 1, 2),
                entry(ChangedCreatureModEntities.LATEX_ICE_DRAGON, 3, 1, 2),
                entry(ChangedCreatureModEntities.LATEX_YUN_XQ_ICE_DRAGON, 3, 1, 2),
                entry(ChangedCreatureModEntities.LATEX_BLUE_DRAGON, 3, 1, 2),
                entry(ChangedCreatureModEntities.LATEX_YUN_QI_ICE_DRAGON, 3, 1, 2),
                entry(ChangedCreatureModEntities.LATEX_DARK_PURPLE_DRAGON_TAUR, 3, 1, 2),
                entry(ChangedCreatureModEntities.LATEX_DARK_PURPLE_DRAGON, 3, 1, 2),
                entry(ChangedCreatureModEntities.LATEX_IQ_GOLD_DRAGON, 3, 1, 2),
                entry(ChangedCreatureModEntities.LATEX_EMBERSCALES_MAGMA_DRAGON_MALE, 3, 1, 1)
        ));

        groups.put("beach", List.of(
                entry(ChangedCreatureModEntities.LATEX_CHENG_XI, 10, 1, 2),
                entry(ChangedCreatureModEntities.LATEX_LUO_HONG_EARLY_SPRING_FOX_DRAGON, 10, 1, 2),
                entry(ChangedCreatureModEntities.LATEX_IQ_GOLD_DRAGON, 6, 1, 2)
        ));

        groups.put("crimson_forest", List.of(
                entry(ChangedCreatureModEntities.LATEX_NIGHT_OWL_DRAGON, 1, 1, 2),
                entry(ChangedCreatureModEntities.LATEX_YUN_XQ_HOT_DRAGON, 2, 1, 2)
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