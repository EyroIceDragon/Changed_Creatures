package net.hhdsj.changed_creatures.world.inventory;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.hhdsj.changed_creatures.init.ChangedCreatureModShaders;
import net.hhdsj.changed_creatures.network.data.ClientHypnotizeData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class MentalConfusionShaderHandler {

    private static final float MAX_INTENSITY = 0.3f;
    private static final double MAX_EFFECT_DISTANCE = 10.0;

    private static float timeAccumulator = 0.0f;
    private static int lastColorTextureId = -1;
    /*
    @SubscribeEvent
    public static void onRenderLevelStage(RenderLevelStageEvent event) {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_ENTITIES) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;
        if (mc.options.graphicsMode().get() == GraphicsStatus.FAST) return;

        float finalIntensity = getEffectIntensity(mc);
        if (finalIntensity <= 0.001f) return;
        ChangedCreature.LOGGER.info("[Debug] Test Shader ok to use!!!");
        //renderShader(mc, event.getPartialTick(), finalIntensity);
    }*/

    private static float getEffectIntensity(Minecraft mc) {
        ClientHypnotizeData.TopAttackerInfo top = ClientHypnotizeData.getTopAttacker();
        if (top == null) return 0.0f;

        Player attacker = null;
        if (mc.level != null) {
            attacker = mc.level.getPlayerByUUID(top.uuid());
        }
        if (attacker == null) return 0.0f;

        double distance = 0;
        if (mc.player != null) {
            distance = mc.player.position().distanceTo(attacker.position());
        }
        if (distance >= MAX_EFFECT_DISTANCE) return 0.0f;

        float factor = (float) (1.0 - (distance / MAX_EFFECT_DISTANCE));
        return MAX_INTENSITY * factor;
    }

    private static void renderShader(Minecraft mc, float partialTick, float intensity) {
        ShaderInstance shader = ChangedCreatureModShaders.getMentalConfusionShader();
        if (shader == null) return;

        shader.safeGetUniform("Intensity").set(intensity);

        //累积时间
        timeAccumulator += partialTick;
        shader.safeGetUniform("Time").set(timeAccumulator);

        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        RenderSystem.setShader(() -> shader);

        int colorTextureId = mc.getMainRenderTarget().getColorTextureId();
        if (colorTextureId != lastColorTextureId) {
            RenderSystem.setShaderTexture(0, colorTextureId);
            lastColorTextureId = colorTextureId;
        }

        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder buffer = tesselator.getBuilder();
        buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);

        int width = mc.getWindow().getWidth();
        int height = mc.getWindow().getHeight();

        buffer.vertex(0.0D, height, -90.0D).uv(0.0F, 0.0F).endVertex();
        buffer.vertex(width, height, -90.0D).uv(1.0F, 0.0F).endVertex();
        buffer.vertex(width, 0.0D, -90.0D).uv(1.0F, 1.0F).endVertex();
        buffer.vertex(0.0D, 0.0D, -90.0D).uv(0.0F, 1.0F).endVertex();

        tesselator.end();

        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        RenderSystem.disableBlend();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);

        lastColorTextureId = -1;
    }
}