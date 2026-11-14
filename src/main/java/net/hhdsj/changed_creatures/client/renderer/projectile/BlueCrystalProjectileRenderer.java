package net.hhdsj.changed_creatures.client.renderer.projectile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.client.models.other.CrystalJavelinModel;
import net.hhdsj.changed_creatures.entity.simple.BlueCrystalProjectile;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class BlueCrystalProjectileRenderer extends EntityRenderer<BlueCrystalProjectile> {
    private static final ResourceLocation TEXTURE =
            ChangedCreature.CCRL("textures/entities/crystal_javelin.png");

    private final CrystalJavelinModel<BlueCrystalProjectile> model;

    public BlueCrystalProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
        ModelPart modelPart = context.bakeLayer(CrystalJavelinModel.LAYER_LOCATION);
        this.model = new CrystalJavelinModel<>(modelPart);
    }

    @Override
    public void render(BlueCrystalProjectile entity, float entityYaw, float partialTicks,
                       PoseStack poseStack, @NotNull MultiBufferSource bufferSource,
                       int packedLight) {

        poseStack.pushPose();

        // 获取飞行的角度
        float yaw = Mth.lerp(partialTicks, entity.yRotO, entity.getYRot());
        float pitch = Mth.lerp(partialTicks, entity.xRotO, entity.getXRot());

        // 关键：模型默认是垂直的（沿Y轴），需要旋转使其指向飞行方向
        // 先绕Y轴旋转水平方向
        poseStack.mulPose(com.mojang.math.Axis.YP.rotationDegrees(yaw + 90.0F));
        // 再绕X轴旋转垂直方向（使其指向正确的俯仰角）
        poseStack.mulPose(com.mojang.math.Axis.XP.rotationDegrees(pitch));
        // 最后绕Z轴旋转90度，让模型从垂直变成水平指向
        poseStack.mulPose(com.mojang.math.Axis.ZP.rotationDegrees(90.0F));

        // 缩放模型大小
        poseStack.scale(0.8F, 0.8F, 0.8F);

        VertexConsumer vertexConsumer = bufferSource.getBuffer(
                RenderType.entityCutoutNoCull(this.getTextureLocation(entity))
        );
        this.model.renderToBuffer(poseStack, vertexConsumer, packedLight,
                OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        poseStack.popPose();

        super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLight);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull BlueCrystalProjectile entity) {
        return TEXTURE;
    }
}