package net.hhdsj.changed_creatures.client.models.other;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.hhdsj.changed_creatures.ChangedCreature;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ModelLatexWing extends AgeableListModel<AbstractClientPlayer> {
    public static final ModelLayerLocation LAYER_LOCATION =
            ChangedCreature.ChangedCreatureModelResourceLocation("latex_wind");

    private final ModelPart Torso;
    private final ModelPart LeftWing;
    private final ModelPart leftWingRoot;
    private final ModelPart leftFirstise;
    private final ModelPart leftSecondaries;
    private final ModelPart leftTertiaries;
    private final ModelPart RightWing;
    private final ModelPart rightWingRoot;
    private final ModelPart rightFirsties;
    private final ModelPart rightSecondaries;
    private final ModelPart rightTertiaries;

    // [wingRotX, wingRotY, wingRotZ, z0Rot, z1Rot, z2Rot]
    private static final Map<UUID, float[]> animationState = new HashMap<>();

    public ModelLatexWing(ModelPart root) {
        this.Torso = root.getChild("Torso");
        this.LeftWing = this.Torso.getChild("LeftWing");
        this.leftWingRoot = this.LeftWing.getChild("leftWingRoot");
        this.leftFirstise = this.leftWingRoot.getChild("leftFirstise");
        this.leftSecondaries = this.leftFirstise.getChild("leftSecondaries");
        this.leftTertiaries = this.leftSecondaries.getChild("leftTertiaries");
        this.RightWing = this.Torso.getChild("RightWing");
        this.rightWingRoot = this.RightWing.getChild("rightWingRoot");
        this.rightFirsties = this.rightWingRoot.getChild("rightFirsties");
        this.rightSecondaries = this.rightFirsties.getChild("rightSecondaries");
        this.rightTertiaries = this.rightSecondaries.getChild("rightTertiaries");
    }

    public ModelPart getTorso() {
        return this.Torso;
    }

    public ModelPart getLeftWing() {
        return this.LeftWing;
    }

    public ModelPart getLeftWingRoot() {
        return this.leftWingRoot;
    }

    public ModelPart getLeftFirstise() {
        return this.leftFirstise;
    }

    public ModelPart getLeftSecondaries() {
        return this.leftSecondaries;
    }

    public ModelPart getLeftTertiaries() {
        return this.leftTertiaries;
    }

    public ModelPart getRightWing() {
        return this.RightWing;
    }

    public ModelPart getRightWingRoot() {
        return this.rightWingRoot;
    }

    public ModelPart getRightFirsties() {
        return this.rightFirsties;
    }

    public ModelPart getRightSecondaries() {
        return this.rightSecondaries;
    }

    public ModelPart getRightTertiaries() {
        return this.rightTertiaries;
    }

    @Override
    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of();
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.Torso);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight,
                               int packedOverlay, float red, float green, float blue, float alpha) {
        Torso.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void setupAnim(AbstractClientPlayer player, float limbSwing, float limbSwingAmount,
                          float ageInTicks, float netHeadYaw, float headPitch) {

        UUID playerId = player.getUUID();
        float[] state = animationState.computeIfAbsent(playerId, k -> new float[6]);

        float breatheOffset = (float) Math.sin(ageInTicks * 0.15F) * 0.02F;

        //地面默认角度
        float defaultXRot = 0.0F + breatheOffset * 0.5F;
        float defaultYRot = -0.2617994F + breatheOffset * 0.3F;
        float defaultZRot = -0.174532F + breatheOffset * 0.2F;

        float targetXRot = defaultXRot;
        float targetYRot = defaultYRot;
        float targetZRot = defaultZRot;
        float targetZ0Rot = -0.087266F;
        float targetZ1Rot = -0.48171F;
        float targetZ2Rot = -0.9599F;
        // 滑翔状态
        if (player.isFallFlying()) {
            float f4 = 1.0F;
            Vec3 vec3 = player.getDeltaMovement();
            if (vec3.y < 0.0D) {
                Vec3 vec31 = vec3.normalize();
                f4 = 1.0F - (float) Math.pow(-vec31.y, 1.5D);
            }

            targetXRot = f4 * 0.34906584F + (1.0F - f4) * defaultXRot;

            float wingFlap = (float) Math.sin(ageInTicks * 2.5F) * 0.2F;
            targetXRot += wingFlap * 0.3F;
            targetYRot += wingFlap * 0.1F;

        } else if (player.isCrouching()) {
            // 潜行状态
            targetXRot = 0.3F;
            targetZRot = -0.5F;
            targetYRot = -0.08726646F;
            targetZ0Rot = -0.007266F;
            targetZ1Rot = -0.10171F;
            targetZ2Rot = -0.39171F;

        } else if (player.isSwimming()) {
            // 游泳状态（目前保持默认）

        } else if (player.getAbilities().flying) {
            // 创造/旁观飞行
            float rawFlap = Mth.sin(ageInTicks * 0.2F);
            float flapAmount = rawFlap * rawFlap;
            targetYRot = Mth.map(flapAmount, 0.0F, 1.0F, -0.5606584F, 0.4F);
            targetXRot = 0.1F;
            targetZRot = -0.087266F;

        } else {
            // 地面行走/待机
            float walkingWave = (float) Math.sin(limbSwing * 1.5F) * 0.06F * limbSwingAmount;
            float idleSway = (float) Math.sin(ageInTicks * 0.1F) * 0.02F;

            targetXRot = defaultXRot + walkingWave * 0.3F + idleSway * 0.5F;
            targetYRot = defaultYRot + walkingWave * 0.2F + idleSway * 0.3F;
            targetZRot = defaultZRot + walkingWave * 0.1F + idleSway * 0.2F;
            targetZ0Rot = -0.007266F;
            targetZ1Rot = -0.10171F;
            targetZ2Rot = -0.39171F;
        }


        state[0] += (targetXRot - state[0]) * 0.15F;
        state[1] += (targetYRot - state[1]) * 0.15F;
        state[2] += (targetZRot - state[2]) * 0.15F;
        state[3] += (targetZ0Rot - state[3]) * 0.15F;
        state[4] += (targetZ1Rot - state[4]) * 0.15F;
        state[5] += (targetZ2Rot - state[5]) * 0.15F;

        // 左翅膀
        this.leftWingRoot.xRot = state[0];
        this.leftWingRoot.yRot = state[1];
        this.leftWingRoot.zRot = state[2];
        this.leftFirstise.zRot = state[3];
        this.leftSecondaries.zRot = state[4];
        this.leftTertiaries.zRot = state[5];

        // 右翅膀（镜像对称）
        this.rightWingRoot.yRot = -state[1];
        this.rightWingRoot.xRot = state[0];
        this.rightWingRoot.zRot = -state[2];
        this.rightFirsties.zRot = -state[3];
        this.rightSecondaries.zRot = -state[4];
        this.rightTertiaries.zRot = -state[5];
    }

    /**
     * 清理不再使用的玩家动画状态（可选，防止内存泄漏）
     * 可以在玩家退出时调用
     */
    public static void cleanupPlayerState(UUID playerId) {
        animationState.remove(playerId);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Torso = partdefinition.addOrReplaceChild("Torso", CubeListBuilder.create(), PartPose.offset(0.0F, -0.5F, 0.0F));

        PartDefinition LeftWing = Torso.addOrReplaceChild("LeftWing", CubeListBuilder.create(), PartPose.offsetAndRotation(1.3F, 3.3F, 2.0F, 0.0F, -0.48F, 0.0F));

        PartDefinition leftWingRoot = LeftWing.addOrReplaceChild("leftWingRoot", CubeListBuilder.create(), PartPose.offset(-0.3F, -0.3F, 0.0F));

        PartDefinition cube_r1 = leftWingRoot.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 36).addBox(5.2053F, -17.5097F, 1.6435F, 7.0F, 2.0F, 1.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(1.0F, 17.525F, -2.0F, 0.0F, 0.0F, -0.3491F));

        PartDefinition cube_r2 = leftWingRoot.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(30, 8).addBox(12.8509F, -5.5632F, 2.0935F, 11.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1F, 18.525F, -2.0F, 0.0F, 0.0F, -1.2654F));

        PartDefinition leftFirstise = leftWingRoot.addOrReplaceChild("leftFirstise", CubeListBuilder.create(), PartPose.offset(6.0F, -2.0F, 0.0F));

        PartDefinition cube_r3 = leftFirstise.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(40, 36).addBox(-2.9367F, -1.5985F, -3.6045F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(2.4245F, -1.1265F, 3.248F, 0.0F, 0.0F, -0.3927F));

        PartDefinition cube_r4 = leftFirstise.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(24, 39).addBox(-0.5F, -3.0F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9844F, 6.4217F, 0.1435F, 0.0F, 0.0F, 0.2705F));

        PartDefinition cube_r5 = leftFirstise.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(30, 15).addBox(-4.0F, -1.0F, -0.5F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.085F, -2.7754F, 0.1435F, 0.0F, 0.0F, -0.7854F));

        PartDefinition leftSecondaries = leftFirstise.addOrReplaceChild("leftSecondaries", CubeListBuilder.create(), PartPose.offsetAndRotation(5.675F, -5.375F, -0.5F, 0.0F, 0.0F, -0.5236F));

        PartDefinition cube_r6 = leftSecondaries.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(44, 36).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5801F, -0.6933F, 0.6435F, 0.0F, 0.0F, -2.0246F));

        PartDefinition cube_r7 = leftSecondaries.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(28, 0).addBox(-6.0F, -4.0F, 0.0F, 12.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.6423F, 9.7596F, 0.5945F, 0.0F, 0.0F, -1.8326F));

        PartDefinition cube_r8 = leftSecondaries.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(32, 36).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 11.0F, 1.0F, new CubeDeformation(0.05F)), PartPose.offsetAndRotation(0.4249F, -0.4427F, 0.6435F, 0.0F, 0.0F, 0.1178F));

        PartDefinition cube_r9 = leftSecondaries.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(8, 39).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.9991F, 14.1361F, 0.6435F, 0.0F, 0.0F, 0.48F));

        PartDefinition cube_r10 = leftSecondaries.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -5.0F, 0.0F, 14.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2082F, 8.3427F, 0.5915F, 0.0F, 0.0F, -0.7505F));

        PartDefinition leftTertiaries = leftSecondaries.addOrReplaceChild("leftTertiaries", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.35F, -0.25F, 0.0F, 0.0F, 0.0F, -0.9599F));

        PartDefinition cube_r11 = leftTertiaries.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(36, 36).addBox(-5.0516F, -20.9916F, 1.6435F, 1.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.4F, 30.4F, -1.5F, 0.0F, 0.0F, 0.5236F));

        PartDefinition cube_r12 = leftTertiaries.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(0, 10).addBox(9.3354F, -13.9881F, 2.0835F, 15.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 28.0F, -1.5F, 0.0F, 0.0F, -0.8727F));

        PartDefinition cube_r13 = leftTertiaries.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(12, 39).addBox(6.8623F, -25.869F, 1.6435F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.005F)), PartPose.offsetAndRotation(-5.55F, 26.5F, -1.5F, 0.0F, 0.0F, -0.0436F));

        PartDefinition RightWing = Torso.addOrReplaceChild("RightWing", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.3F, 3.3F, 2.0F, 0.0F, 0.48F, 0.0F));

        PartDefinition rightWingRoot = RightWing.addOrReplaceChild("rightWingRoot", CubeListBuilder.create(), PartPose.offset(0.3F, -0.3F, 0.0F));

        PartDefinition cube_r14 = rightWingRoot.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(16, 36).addBox(-12.2053F, -17.5097F, 1.6435F, 7.0F, 2.0F, 1.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-1.0F, 17.525F, -2.0F, 0.0F, 0.0F, 0.3491F));

        PartDefinition cube_r15 = rightWingRoot.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(30, 26).addBox(-23.8509F, -5.5632F, 2.0935F, 11.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.1F, 18.525F, -2.0F, 0.0F, 0.0F, 1.2654F));

        PartDefinition rightFirsties = rightWingRoot.addOrReplaceChild("rightFirsties", CubeListBuilder.create(), PartPose.offset(-6.0F, -2.0F, 0.0F));

        PartDefinition cube_r16 = rightFirsties.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(40, 43).addBox(1.9367F, -1.5985F, -3.6045F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-2.4245F, -1.1265F, 3.248F, 0.0F, 0.0F, 0.3927F));

        PartDefinition cube_r17 = rightFirsties.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(28, 39).addBox(-0.5F, -3.0F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.9844F, 6.4217F, 0.1435F, 0.0F, 0.0F, -0.2705F));

        PartDefinition cube_r18 = rightFirsties.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(30, 33).addBox(-4.0F, -1.0F, -0.5F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.085F, -2.7754F, 0.1435F, 0.0F, 0.0F, 0.7854F));

        PartDefinition rightSecondaries = rightFirsties.addOrReplaceChild("rightSecondaries", CubeListBuilder.create(), PartPose.offsetAndRotation(-5.675F, -5.375F, -0.5F, 0.0F, 0.0F, 0.5236F));

        PartDefinition cube_r19 = rightSecondaries.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(44, 39).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5801F, -0.6933F, 0.6435F, 0.0F, 0.0F, 2.0246F));

        PartDefinition cube_r20 = rightSecondaries.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(28, 18).addBox(-6.0F, -4.0F, 0.0F, 12.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6423F, 9.7596F, 0.5945F, 0.0F, 0.0F, 1.8326F));

        PartDefinition cube_r21 = rightSecondaries.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(0, 39).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 11.0F, 1.0F, new CubeDeformation(0.05F)), PartPose.offsetAndRotation(-0.4249F, -0.4427F, 0.6435F, 0.0F, 0.0F, -0.1178F));

        PartDefinition cube_r22 = rightSecondaries.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(16, 39).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.9991F, 14.1361F, 0.6435F, 0.0F, 0.0F, -0.48F));

        PartDefinition cube_r23 = rightSecondaries.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(0, 18).addBox(-7.0F, -5.0F, 0.0F, 14.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.2082F, 8.3427F, 0.5915F, 0.0F, 0.0F, 0.7505F));

        PartDefinition rightTertiaries = rightSecondaries.addOrReplaceChild("rightTertiaries", CubeListBuilder.create(), PartPose.offsetAndRotation(0.35F, -0.25F, 0.0F, 0.0F, 0.0F, 0.9599F));

        PartDefinition cube_r24 = rightTertiaries.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(4, 39).addBox(4.0516F, -20.9916F, 1.6435F, 1.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.4F, 30.4F, -1.5F, 0.0F, 0.0F, -0.5236F));

        PartDefinition cube_r25 = rightTertiaries.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(0, 28).addBox(-24.3354F, -13.9881F, 2.0835F, 15.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 28.0F, -1.5F, 0.0F, 0.0F, 0.8727F));

        PartDefinition cube_r26 = rightTertiaries.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(20, 39).addBox(-7.8623F, -25.869F, 1.6435F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.005F)), PartPose.offsetAndRotation(5.55F, 26.5F, -1.5F, 0.0F, 0.0F, 0.0436F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}