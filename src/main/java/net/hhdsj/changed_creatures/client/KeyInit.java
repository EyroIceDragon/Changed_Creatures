package net.hhdsj.changed_creatures.client;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.client.gui.SimpleDebugScreen;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = ChangedCreature.MODID, value = Dist.CLIENT)
public class KeyInit {

    public static final KeyMapping OPEN_DEBUG_GUI = new KeyMapping(
            "key.changed_creatures.open_debug_gui",
            GLFW.GLFW_KEY_G,           // 默认 G 键
            "key.categories.changed_creatures"
    );

    @Mod.EventBusSubscriber(modid = ChangedCreature.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModBusEvents {
        @SubscribeEvent
        public static void onRegisterKeys(RegisterKeyMappingsEvent event) {
            event.register(OPEN_DEBUG_GUI);
        }
    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (OPEN_DEBUG_GUI.consumeClick()) {
            Minecraft.getInstance().setScreen(new SimpleDebugScreen());
        }
    }
}