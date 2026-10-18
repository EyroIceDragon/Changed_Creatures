package net.hhdsj.changed_creatures.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.hhdsj.changed_creatures.init.ChangedCreatureModEntities;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ChangedCreaturesCompatibilityDataProvider implements DataProvider {

    private static final List<RegistryObject<? extends EntityType<?>>> HUMANOID_ENTITIES = List.of(
            ChangedCreatureModEntities.LATEX_ORANGE_FOX,
            ChangedCreatureModEntities.LATEX_KCAHRA_SHARK,
            ChangedCreatureModEntities.LATEXBLUEDRAGON,
            ChangedCreatureModEntities.DARKFU_LATEX_WOLF_MALE,
            ChangedCreatureModEntities.LATEXICE_DRAGON,
            ChangedCreatureModEntities.LATEXPURPLEWSWOLF,
            ChangedCreatureModEntities.LATEXYUNXQHOTDRAGON,
            ChangedCreatureModEntities.LATEXYUNXQICEDRAGON,
            ChangedCreatureModEntities.LATEXPRTPLECOOCWOLF,
            ChangedCreatureModEntities.LATEXTHREEMONTHWOLF,
            ChangedCreatureModEntities.LATEXYUNQIICEDRAGON,
            ChangedCreatureModEntities.LATEXRADIATIONFOX,
            ChangedCreatureModEntities.LATEXNIGHTOWLDRAGON,
            ChangedCreatureModEntities.LATEXIQGOLDDRAGON,
            ChangedCreatureModEntities.LATEXDARKPURPLEDRAGON,
            ChangedCreatureModEntities.LATEXLUOHONGEARLYSPRINGFOXDRAGON,
            ChangedCreatureModEntities.LATEXNEONDIMNESSWOLF,
            ChangedCreatureModEntities.LATEX_CHENG_XI,
            ChangedCreatureModEntities.LATEX_RADIARY_DRAGON,
            ChangedCreatureModEntities.LATEX_DIVINE_SWORD_KITSUNE,
            ChangedCreatureModEntities.LATEX_GAO_HUI_FOX,
            ChangedCreatureModEntities.LATEX_HYAZINTHE_FOX,
            ChangedCreatureModEntities.LATEX_YUE_XI_FOX_FEMALE,
            ChangedCreatureModEntities.LATEX_QIAN_GUI_SHARK,
            ChangedCreatureModEntities.LATEXFRUITDRAGONWOLF,
            ChangedCreatureModEntities.LATEX_DAWN_WOLF,
            ChangedCreatureModEntities.LATEX_BLUEVE_CAT,
            ChangedCreatureModEntities.LATEX_ETAN_KIND,
            ChangedCreatureModEntities.LATEX_CRYSTAL_JELLY,
            ChangedCreatureModEntities.LATEX_DUSK_DAWN_DRAGON_FEMALE,
            ChangedCreatureModEntities.LATEX_DUSK_DAWN_DRAGON,
            ChangedCreatureModEntities.LATEX_STEPPEN_WOLF,
            ChangedCreatureModEntities.LATEX_PEAUNT_BUTTER_WOLF,
            ChangedCreatureModEntities.LATEX_EMBERSCALES_MAGMA_DRAGON_MALE,
            ChangedCreatureModEntities.LATEX_XUAN_JIN_DRAGON,
            ChangedCreatureModEntities.LATEX_XUAN_JIN_WOLF_DRAGON,
            ChangedCreatureModEntities.LATEX_EYRO_END_DRAGON,
            ChangedCreatureModEntities.LATEX_INGERTE_FOX
    );

    private static final List<RegistryObject<? extends EntityType<?>>> LATEX_ENTITIES = List.of(
            ChangedCreatureModEntities.LATEX_ORANGE_FOX,
            ChangedCreatureModEntities.LATEX_KCAHRA_SHARK,
            ChangedCreatureModEntities.LATEXBLUEDRAGON,
            ChangedCreatureModEntities.DARKFU_LATEX_WOLF_MALE,
            ChangedCreatureModEntities.LATEXICE_DRAGON,
            ChangedCreatureModEntities.INKSUGER,
            ChangedCreatureModEntities.BLACKPUPMALE,
            ChangedCreatureModEntities.WHITEBLUEPUP,
            ChangedCreatureModEntities.LATEXPURPLEWSWOLF,
            ChangedCreatureModEntities.LATEXYUNXQHOTDRAGON,
            ChangedCreatureModEntities.LATEXYUNXQICEDRAGON,
            ChangedCreatureModEntities.LATEXPRTPLECOOCWOLF,
            ChangedCreatureModEntities.LATEXTHREEMONTHWOLF,
            ChangedCreatureModEntities.LATEXYUNQIICEDRAGON,
            ChangedCreatureModEntities.LATEXRADIATIONFOX,
            ChangedCreatureModEntities.LATEXNIGHTOWLDRAGON,
            ChangedCreatureModEntities.LATEXIQGOLDDRAGON,
            ChangedCreatureModEntities.LATEXDARKPURPLEDRAGONTAUR,
            ChangedCreatureModEntities.LATEXDARKPURPLEDRAGON,
            ChangedCreatureModEntities.LATEXLUOHONGEARLYSPRINGFOXDRAGON,
            ChangedCreatureModEntities.LATEXNEONDIMNESSWOLF,
            ChangedCreatureModEntities.LATEX_CHENG_XI,
            ChangedCreatureModEntities.LATEX_RADIARY_DRAGON,
            ChangedCreatureModEntities.LATEX_DIVINE_SWORD_KITSUNE,
            ChangedCreatureModEntities.LATEX_GAO_HUI_FOX,
            ChangedCreatureModEntities.LATEX_HYAZINTHE_FOX,
            ChangedCreatureModEntities.LATEX_YUE_XI_FOX_FEMALE,
            ChangedCreatureModEntities.LATEX_QIAN_GUI_SHARK,
            ChangedCreatureModEntities.LATEXFRUITDRAGONWOLF,
            ChangedCreatureModEntities.LATEX_DAWN_WOLF,
            ChangedCreatureModEntities.LATEX_BLUEVE_CAT,
            ChangedCreatureModEntities.LATEX_ETAN_KIND,
            ChangedCreatureModEntities.LATEX_CRYSTAL_JELLY,
            ChangedCreatureModEntities.LATEX_DUSK_DAWN_DRAGON_FEMALE,
            ChangedCreatureModEntities.LATEX_DUSK_DAWN_DRAGON,
            ChangedCreatureModEntities.LATEX_FROST_SCALE_DRAGON_TAUR,
            ChangedCreatureModEntities.LATEX_STEPPEN_WOLF,
            ChangedCreatureModEntities.LATEX_PEAUNT_BUTTER_WOLF,
            ChangedCreatureModEntities.LATEX_EMBERSCALES_MAGMA_DRAGON_MALE,
            ChangedCreatureModEntities.LATEX_XUAN_JIN_DRAGON,
            ChangedCreatureModEntities.LATEX_XUAN_JIN_WOLF_DRAGON,
            ChangedCreatureModEntities.LATEX_EYRO_END_DRAGON,
            ChangedCreatureModEntities.LATEX_INGERTE_FOX
    );

    private static final List<RegistryObject<? extends EntityType<?>>> PARTIAL_LATEX_ENTITIES = List.of(
            ChangedCreatureModEntities.INKSUGER,
            ChangedCreatureModEntities.BLACKPUPMALE,
            ChangedCreatureModEntities.WHITEBLUEPUP
    );

    private final PackOutput output;

    public ChangedCreaturesCompatibilityDataProvider(PackOutput output) {
        this.output = output;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        Path dataPack = output.getOutputFolder(PackOutput.Target.DATA_PACK);
        List<CompletableFuture<?>> futures = new ArrayList<>();

        addEntityTag(futures, cache, dataPack, "can_open_lab_doors", LATEX_ENTITIES);
        addEntityTag(futures, cache, dataPack, "can_wear_exoskeleton", HUMANOID_ENTITIES);
        addEntityTag(futures, cache, dataPack, "latexes", LATEX_ENTITIES);
        addEntityTag(futures, cache, dataPack, "partial_latexes", PARTIAL_LATEX_ENTITIES);

        addAccessoryEntities(futures, cache, dataPack, "humanoids", HUMANOID_ENTITIES, List.of(
                "changed:body",
                "changed:full_body",
                "changed:legs",
                "changed:hands",
                "changed:head",
                "changed:face",
                "changed:neck"
        ));
        addAccessoryEntities(futures, cache, dataPack, "taurs", List.of(
                ChangedCreatureModEntities.LATEXDARKPURPLEDRAGONTAUR,
                ChangedCreatureModEntities.LATEX_FROST_SCALE_DRAGON_TAUR
        ), List.of(
                "changed:body",
                "changed:hands",
                "changed:head",
                "changed:face",
                "changed:lower_body",
                "changed:lower_body_side",
                "changed:neck"
        ));

        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    private static void addEntityTag(
            List<CompletableFuture<?>> futures,
            CachedOutput cache,
            Path dataPack,
            String tagName,
            List<RegistryObject<? extends EntityType<?>>> entities
    ) {
        JsonObject tag = new JsonObject();
        tag.addProperty("replace", false);
        tag.add("values", toEntityIdArray(entities));

        Path path = dataPack.resolve(
                "data/changed/tags/entity_types/" + tagName + ".json"
        );
        futures.add(DataProvider.saveStable(cache, tag, path));
    }

    private static void addAccessoryEntities(
            List<CompletableFuture<?>> futures,
            CachedOutput cache,
            Path dataPack,
            String fileName,
            List<RegistryObject<? extends EntityType<?>>> entities,
            List<String> slots
    ) {
        JsonObject accessories = new JsonObject();
        accessories.addProperty("replace", false);
        accessories.add("entities", toEntityIdArray(entities));
        accessories.add("slots", toJsonArray(slots));

        Path path = dataPack.resolve(
                "data/changed_creatures/accessories/entities/" + fileName + ".json"
        );
        futures.add(DataProvider.saveStable(cache, accessories, path));
    }

    /** 把实体注册对象转成真实注册 ID 的 JSON 数组，跳过未注册的项 */
    private static JsonArray toEntityIdArray(List<RegistryObject<? extends EntityType<?>>> entities) {
        JsonArray array = new JsonArray();
        for (RegistryObject<? extends EntityType<?>> ro : entities) {
            if (ro == null || !ro.isPresent()) continue;
            ResourceLocation id = ForgeRegistries.ENTITY_TYPES.getKey(ro.get());
            if (id != null) {
                array.add(id.toString());
            }
        }
        return array;
    }

    private static JsonArray toJsonArray(List<String> values) {
        JsonArray array = new JsonArray();
        values.forEach(array::add);
        return array;
    }

    @Override
    public String getName() {
        return "ChangedCreatures Compatibility Data";
    }
}