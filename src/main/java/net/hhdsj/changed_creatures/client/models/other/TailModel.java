package net.hhdsj.changed_creatures.client.models.other;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.hhdsj.changed_creatures.ChangedCreature;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import java.util.List;

public class TailModel extends Model {

    private static final ResourceLocation TAIL_TEXTURE = ChangedCreature.CCRL("textures/entities/tail_white.png");

    private final float[] targetYRot = new float[3];
    private final float[] currentYRot = new float[3];
    private boolean initialized = false;
    private final ModelPart Tail;
    private final List<ModelPart> tailJoints;

    public TailModel(ModelPart root) {
        super(RenderType::entityCutoutNoCull);
        this.Tail = root.getChild("Tail");

        ModelPart TailPrimary = Tail.getChild("TailPrimary");
        ModelPart TailSecondary = TailPrimary.getChild("TailSecondary");
        ModelPart TailTertiary = TailSecondary.getChild("TailTertiary");

        this.tailJoints = List.of(TailPrimary, TailSecondary, TailTertiary);
    }

    @SuppressWarnings("unused")
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Tail = partdefinition.addOrReplaceChild("Tail", CubeListBuilder.create(), PartPose.offset(0.0F, 10.0F, 0.0F));

        PartDefinition TailPrimary = Tail.addOrReplaceChild("TailPrimary", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1309F, 0.0F, 0.0F));

        PartDefinition Base_r1 = TailPrimary.addOrReplaceChild("Base_r1", CubeListBuilder.create().texOffs(0, 13).addBox(-2.0F, 0.75F, -1.5F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.1781F, 0.0F, 0.0F));

        PartDefinition TailSecondary = TailPrimary.addOrReplaceChild("TailSecondary", CubeListBuilder.create(), PartPose.offset(0.0F, 1.25F, 4.5F));

        PartDefinition Base_r2 = TailSecondary.addOrReplaceChild("Base_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -0.45F, -2.0F, 5.0F, 8.0F, 5.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.0F, 0.5F, 0.0F, 1.4835F, 0.0F, 0.0F));

        PartDefinition TailTertiary = TailSecondary.addOrReplaceChild("TailTertiary", CubeListBuilder.create(), PartPose.offset(0.0F, 0.75F, 6.5F));

        PartDefinition Base_r3 = TailTertiary.addOrReplaceChild("Base_r3", CubeListBuilder.create().texOffs(16, 13).addBox(-2.0F, -0.7F, -1.95F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, 1.8326F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    /**
     * 设置目标旋转（不再直接赋值给 joint）
     */
    public void setupAnim(float limbSwing, float limbSwingAmount, float ageInTicks) {
        float f = 1.0F;

        Tail.xRot = 0.0F;
        Tail.zRot = 0.0F;

        float tailSway = 0.1F * Mth.cos(ageInTicks * 0.083333336F + 0.7853982F);
        float tailBalance = Mth.cos(limbSwing * 0.6662F) * 0.125F * limbSwingAmount / f;
        float tailTargetYRot = Mth.lerp(limbSwingAmount, tailSway, tailBalance);

        if (!initialized) {
            // 首次初始化，直接跳转到目标
            Tail.yRot = tailTargetYRot;
        }
        // 父节点 Tail 仍然直接设置（因为它通常不被平滑）
        Tail.yRot = tailTargetYRot;

        // 设置关节的目标旋转
        float offset = 0.0F;
        for (int i = 0; i < tailJoints.size(); i++) {
            targetYRot[i] = Mth.lerp(limbSwingAmount,
                    0.1F * Mth.cos(ageInTicks * 0.083333336F - 1.0471976F * offset),
                    0.0F);
            if (!initialized) {
                currentYRot[i] = targetYRot[i];
            }
            offset += 0.75F;
        }
        initialized = true;
    }

    /**
     * 使用 partialTicks 进行平滑插值（每帧调用）
     * 这保证了运动与帧率无关
     */
    public void applySmoothRotation(float partialTicks) {
        // 线性插值速度：值越大越快响应，建议 0.15~0.3
        float smoothSpeed = 0.2f;
        for (int i = 0; i < tailJoints.size(); i++) {
            currentYRot[i] = Mth.lerp(smoothSpeed, currentYRot[i], targetYRot[i]);
            tailJoints.get(i).yRot = currentYRot[i];
        }
    }



    /**
     * 游泳动画
     */
    public void setupSwimAnim(float swimAmount) {
        Tail.xRot = Mth.lerp(swimAmount, Tail.xRot, -1.1F);
        float oldZ = Tail.zRot;
        Tail.zRot = Mth.lerp(swimAmount, 0.0F, Tail.yRot);
        Tail.yRot = Mth.lerp(swimAmount, oldZ, 0.0F);
    }

    /**
     * 潜行动画
     */
    public void setupCrouchAnim() {
        Tail.xRot -= 0.3F;
    }

    /**
     * 骑乘动画
     */
    public void setupRideAnim() {
        Tail.xRot = 0.005F;
    }

    /**
     * 睡觉动画
     */
    public void setupSleepAnim() {
        Tail.xRot = -1.4137167F;
    }

    /**
     * 滑翔动画
     */
    public void setupFallFlyAnim(float fallFlyingTicks) {
        float fallFlyAmount = Mth.clamp(fallFlyingTicks * fallFlyingTicks / 100.0F, 0.0F, 1.0F);
        Tail.xRot = Mth.lerp(fallFlyAmount, Tail.xRot, -1.0F);
    }

    public ResourceLocation getTexture() {
        return TAIL_TEXTURE;
    }

    public ModelPart getTailPart() {
        return Tail;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer,
                               int packedLight, int packedOverlay,
                               float red, float green, float blue, float alpha) {
        Tail.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}