package net.hhdsj.changed_creatures.ability.data;

import com.google.gson.JsonArray;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.hhdsj.changed_creatures.ChangedCreature;
import net.ltxprogrammer.changed.init.ChangedRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Mod.EventBusSubscriber(modid = ChangedCreature.MODID)
public class AbilityDataReloadListener extends SimpleJsonResourceReloadListener {
    private static final String DIRECTORY = "ability";
    private static final Gson GSON = new Gson();

    public AbilityDataReloadListener() {
        super(GSON, DIRECTORY);
    }

    @SubscribeEvent
    public static void register(AddReloadListenerEvent event) {
        event.addListener(new AbilityDataReloadListener());
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> resources,
                         ResourceManager resourceManager,
                         ProfilerFiller profiler) {
        Map<ResourceLocation, List<ResourceLocation>> abilitiesByVariant = new HashMap<>();
        for (Map.Entry<ResourceLocation, JsonElement> entry : new TreeMap<>(resources).entrySet()) {
            ResourceLocation fileId = entry.getKey();
            try {
                JsonObject json = GsonHelper.convertToJsonObject(entry.getValue(), fileId.toString());
                List<ResourceLocation> abilityIds = readAbilityIds(json.get("ability"), fileId);
                if (abilityIds.isEmpty()) continue;

                JsonArray values = GsonHelper.getAsJsonArray(json, "values");
                List<ResourceLocation> variantIds = new ArrayList<>(values.size());
                for (JsonElement value : values) {
                    ResourceLocation variantId = readResourceLocation(value, "values entry");
                    if (ChangedRegistry.TRANSFUR_VARIANT.get().getValue(variantId) == null) {
                        ChangedCreature.LOGGER.warn("Unknown transfur variant {} in {}", variantId, fileId);
                        continue;
                    }
                    variantIds.add(variantId);
                }
                for (ResourceLocation variantId : variantIds) {
                    List<ResourceLocation> variantAbilities =
                            abilitiesByVariant.computeIfAbsent(variantId, ignored -> new ArrayList<>());
                    for (ResourceLocation abilityId : abilityIds) {
                        if (variantAbilities.contains(abilityId)) continue;
                        if (variantAbilities.size() >= 4) {
                            ChangedCreature.LOGGER.warn(
                                    "Ignoring ability {} from {}: variant {} already has 4 configured abilities",
                                    abilityId,
                                    fileId,
                                    variantId);
                            continue;
                        }
                        variantAbilities.add(abilityId);
                    }
                }
            } catch (JsonParseException | IllegalStateException exception) {
                ChangedCreature.LOGGER.warn(
                        "Unable to load ability assignments from {}: {}",
                        fileId,
                        exception.getMessage());
            }
        }

        VariantAbilityMap.setDataDriven(abilitiesByVariant);
        ChangedCreature.LOGGER.info("Loaded ability assignments for {} variants", abilitiesByVariant.size());
    }

    private static ResourceLocation readResourceLocation(JsonElement element, String field) {
        if (element == null || !element.isJsonPrimitive() || !element.getAsJsonPrimitive().isString()) {
            throw new JsonParseException("Expected a resource location string for " + field);
        }

        String value = element.getAsString();
        ResourceLocation id = ResourceLocation.tryParse(value);
        if (id == null) {
            throw new JsonParseException("Invalid resource location for " + field + ": " + value);
        }
        return id;
    }

    private static List<ResourceLocation> readAbilityIds(JsonElement element, ResourceLocation fileId) {
        List<ResourceLocation> abilityIds = new ArrayList<>();
        if (element != null && element.isJsonPrimitive() && element.getAsJsonPrimitive().isString()) {
            addAbilityId(abilityIds, element, fileId);
            return abilityIds;
        }

        if (element == null || !element.isJsonArray()) {
            throw new JsonParseException("Expected a resource location string or array for ability");
        }

        JsonArray values = element.getAsJsonArray();
        if (values.size() > 4) {
            ChangedCreature.LOGGER.warn(
                    "Ability list in {} has {} entries; only the first 4 will be read",
                    fileId,
                    values.size());
        }

        for (int i = 0; i < Math.min(values.size(), 4); i++) {
            addAbilityId(abilityIds, values.get(i), fileId);
        }
        return abilityIds;
    }

    private static void addAbilityId(List<ResourceLocation> abilityIds,
                                     JsonElement element,
                                     ResourceLocation fileId) {
        ResourceLocation abilityId = readResourceLocation(element, "ability");
        if (!AbilityRegistry.contains(abilityId)) {
            ChangedCreature.LOGGER.warn("Unknown ability {} in {}", abilityId, fileId);
            return;
        }
        if (!abilityIds.contains(abilityId)) {
            abilityIds.add(abilityId);
        }
    }
}
