package net.hhdsj.changed_creatures.world.inventory;

import net.hhdsj.changed_creatures.init.ChangedCreatureModShaders;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterShadersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.IOException;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientShaderLoader {

    @SubscribeEvent
    public static void onRegisterShaders(RegisterShadersEvent event) throws IOException {
        event.registerShader(
                new ShaderInstance(
                        event.getResourceProvider(),
                        ChangedCreatureModShaders.MENTAL_CONFUSION_ID,
                        com.mojang.blaze3d.vertex.DefaultVertexFormat.POSITION_TEX
                ),
                ChangedCreatureModShaders::setMentalConfusionShader
        );
    }
}