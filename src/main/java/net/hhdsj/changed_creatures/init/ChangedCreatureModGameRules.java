package net.hhdsj.changed_creatures.init;

import net.minecraft.world.level.GameRules;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.hhdsj.changed_creatures.ChangedCreature;

@Mod.EventBusSubscriber(modid = ChangedCreature.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ChangedCreatureModGameRules {

    public static GameRules.Key<GameRules.BooleanValue> TEST_RULE;
    public static GameRules.Key<GameRules.BooleanValue> INFECTION_RULE;

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            TEST_RULE = GameRules.register(
                    "changed_creatures:test",
                    GameRules.Category.MISC,
                    GameRules.BooleanValue.create(false)
            );
        });
        event.enqueueWork(() -> {
            INFECTION_RULE = GameRules.register(
                    "changed_creatures:latex_infection",
                    GameRules.Category.MISC,
                    GameRules.BooleanValue.create(false)
            );
        });
    }

    public static void init() {}
}