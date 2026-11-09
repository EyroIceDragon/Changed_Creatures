package net.hhdsj.changed_creatures.client.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.hhdsj.changed_creatures.client.models.armors.fursuit.HumanoidFursuitModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class FursuitGlowRenderer {

    /**
     * 渲染兽装的自发光层。
     * <p>前置条件：调用方已对 model 调用 {@code setVisibilityForItem(slot)}，
     * 且 poseStack 已应用与基础层相同的变换。
     *
     * @param glow     发光贴图路径，为 null 时直接返回
     * @param poseStack 当前变换栈
     * @param buffer   顶点缓冲
     * @param model    兽装模型
     */
    public static void renderGlow(ResourceLocation glow, PoseStack poseStack,
                                  MultiBufferSource buffer, HumanoidFursuitModel model) {
        if (glow == null) return;
        VertexConsumer glowConsumer = buffer.getBuffer(RenderType.eyes(glow));
        poseStack.pushPose();
        float inflate = 1.0F + 0.005F;
        poseStack.scale(inflate, inflate, inflate);
        model.renderToBuffer(poseStack, glowConsumer, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY,
                1.0F, 1.0F, 1.0F, 1.0F);
        poseStack.popPose();
    }

    /**
     * 渲染模型块的自发光层。
     * 且 poseStack 已应用与基础层相同的变换。
     *
     * @param glow     发光贴图路径，为 null 时直接返回
     * @param poseStack 当前变换栈
     * @param buffer   顶点缓冲
     * @param part    模型块
     */
    public static void renderGlow(ResourceLocation glow, PoseStack poseStack,
                                  MultiBufferSource buffer, ModelPart part) {
        if (glow == null) return;
        VertexConsumer glowConsumer = buffer.getBuffer(RenderType.eyes(glow));
        poseStack.pushPose();
        float inflate = 1.0F + 0.005F;
        poseStack.scale(inflate, inflate, inflate);
        part.render(poseStack, glowConsumer, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
    }
}
