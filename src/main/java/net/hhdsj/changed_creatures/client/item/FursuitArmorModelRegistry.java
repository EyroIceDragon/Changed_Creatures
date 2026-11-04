package net.hhdsj.changed_creatures.client.item;

import net.hhdsj.changed_creatures.client.models.armors.fursuit.HumanoidFursuitModel;
import net.hhdsj.changed_creatures.client.models.armors.fursuit.ModelFursuitFox;
import net.hhdsj.changed_creatures.client.models.armors.fursuit.ModelFursuitProtogen;
import net.hhdsj.changed_creatures.init.ChangedCreatureModItems;
import net.hhdsj.changed_creatures.item.HidePlayerPartArmor;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class FursuitArmorModelRegistry {
    private static final Map<Item, Supplier<? extends HumanoidFursuitModel>> MODELS = new HashMap<>();

    private FursuitArmorModelRegistry() {
    }

    @SubscribeEvent
    public static void registerModels(EntityRenderersEvent.RegisterRenderers event) {
        register(ChangedCreatureModItems.FURSUIT_PROTOGEN_HELMET.get(),
                FursuitArmorModelRegistry::createProtogenModel);
        register(ChangedCreatureModItems.FURSUIT_PROTOGEN_CHESTPLATE.get(),
                FursuitArmorModelRegistry::createProtogenModel);
        register(ChangedCreatureModItems.FURSUIT_PROTOGEN_LEGGINGS.get(),
                FursuitArmorModelRegistry::createProtogenModel);
        register(ChangedCreatureModItems.FURSUIT_PROTOGEN_BOOTS.get(),
                FursuitArmorModelRegistry::createProtogenModel);

        register(ChangedCreatureModItems.FURSUIT_FOX_HELMET.get(),
                FursuitArmorModelRegistry::createFoxModel);
        register(ChangedCreatureModItems.FURSUIT_FOX_CHESTPLATE.get(),
                FursuitArmorModelRegistry::createFoxModel);
        register(ChangedCreatureModItems.FURSUIT_FOX_LEGGINGS.get(),
                FursuitArmorModelRegistry::createFoxModel);
        register(ChangedCreatureModItems.FURSUIT_FOX_BOOTS.get(),
                FursuitArmorModelRegistry::createFoxModel);
    }

    public static void register(Item item, Supplier<? extends HumanoidFursuitModel> modelFactory) {
        if (MODELS.putIfAbsent(item, modelFactory) != null) {
            throw new IllegalStateException("A fursuit model is already registered for " + item);
        }
    }

    public static HumanoidFursuitModel createModel(HidePlayerPartArmor item) {
        Supplier<? extends HumanoidFursuitModel> modelFactory = MODELS.get(item);
        if (modelFactory == null) {
            throw new IllegalStateException("No fursuit model registered for " + item);
        }
        return modelFactory.get();
    }

    private static HumanoidFursuitModel createProtogenModel() {
        return new ModelFursuitProtogen(
                Minecraft.getInstance().getEntityModels().bakeLayer(ModelFursuitProtogen.LAYER_LOCATION));
    }

    private static HumanoidFursuitModel createFoxModel() {
        return new ModelFursuitProtogen(
                Minecraft.getInstance().getEntityModels().bakeLayer(ModelFursuitFox.LAYER_LOCATION));
    }
}
