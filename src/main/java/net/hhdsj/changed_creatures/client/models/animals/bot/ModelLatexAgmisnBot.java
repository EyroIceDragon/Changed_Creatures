package net.hhdsj.changed_creatures.client.models.animals.bot;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.entity.animals.bot.LatexAgmisnBotEntity;
import net.ltxprogrammer.changed.client.renderer.animate.AnimatorPresets;
import net.ltxprogrammer.changed.client.renderer.animate.HumanoidAnimator;
import net.ltxprogrammer.changed.client.renderer.animate.tail.DragonTailInitAnimator;
import net.ltxprogrammer.changed.client.renderer.animate.upperbody.WolfHeadInitAnimator;
import net.ltxprogrammer.changed.client.renderer.model.AdvancedHumanoidModel;
import net.ltxprogrammer.changed.client.renderer.model.LowerTorsoedModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.HumanoidArm;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ModelLatexAgmisnBot extends AdvancedHumanoidModel<LatexAgmisnBotEntity> implements LowerTorsoedModel {
    public static final ModelLayerLocation LAYER_LOCATION =
            ChangedCreature.ChangedCreatureModelResourceLocation("latex_agmisn_bot");

    private final ModelPart FrontRightLeg;
    private final ModelPart FrontLeftLeg;
    private final ModelPart BackRightLeg;
    private final ModelPart BackLeftLeg;
    private final ModelPart LowerTorso;
    private final ModelPart Tail;
    private final ModelPart Head;
    private final HumanoidAnimator<LatexAgmisnBotEntity,ModelLatexAgmisnBot> animator;

    public ModelLatexAgmisnBot(ModelPart root) {
        super(root);

        this.Head = root.getChild("Head");
        this.LowerTorso = root.getChild("LowerTorso");
        this.FrontRightLeg = this.LowerTorso.getChild("RightLeg");
        this.FrontLeftLeg = this.LowerTorso.getChild("LeftLeg");
        this.BackRightLeg = this.LowerTorso.getChild("RightLeg2");
        this.BackLeftLeg = this.LowerTorso.getChild("LeftLeg2");
        this.Tail = this.LowerTorso.getChild("Tail");

        ModelPart tailPrimary = this.Tail.getChild("TailPrimary");
        ModelPart tailSecondary = tailPrimary.getChild("TailSecondary");
        ModelPart tailTertiary = tailSecondary.getChild("TailTertiary");
        ModelPart tailQuaternary = tailTertiary.getChild("TailQuaternary");
        ModelPart leftLowerLeg = this.FrontLeftLeg.getChild("LeftLowerLeg");
        ModelPart rightLowerLeg = this.FrontRightLeg.getChild("RightLowerLeg");

        ModelPart leftLowerLeg2 = this.BackLeftLeg.getChild("LeftLowerLeg2");
        ModelPart leftFoot2 = leftLowerLeg2.getChild("LeftFoot2");

        ModelPart rightLowerLeg2 = this.BackRightLeg.getChild("RightLowerLeg2");
        ModelPart rightFoot2 = rightLowerLeg2.getChild("RightFoot2");

        this.animator = HumanoidAnimator.of(this)
                .addPreset(AnimatorPresets.taurLegs(
                        this.LowerTorso,
                        this.FrontLeftLeg, leftLowerLeg, leftLowerLeg.getChild("LeftFoot"),
                        this.FrontRightLeg, rightLowerLeg, rightLowerLeg.getChild("RightFoot"),
                        this.BackLeftLeg, leftLowerLeg2, leftFoot2, leftFoot2.getChild("LeftPad2"),
                        this.BackRightLeg, rightLowerLeg2, rightFoot2, rightFoot2.getChild("RightPad2")
                ))
                .addAnimator(new DragonTailInitAnimator<>(
                        this.Tail,
                        List.of(tailPrimary, tailSecondary, tailTertiary, tailQuaternary)
                ))
                .addAnimator(new WolfHeadInitAnimator<>(this.Head))
                .forwardOffset(-7.0F)
                .hipOffset(-1.5F)
                .legLength(13.5F)
                .torsoLength(13.05F);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create(), PartPose.offset(0.0F, 10.5F, -3.6F));

        PartDefinition bone5 = Head.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(32, 61).addBox(-0.975F, -2.9F, 5.8F, 6.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 32).addBox(0.025F, -2.5F, 0.0F, 4.0F, 5.0F, 6.0F, new CubeDeformation(0.02F))
                .texOffs(110, 119).addBox(-1.0F, -3.0F, 5.6F, 6.0F, 6.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offset(-2.025F, -0.1F, -7.8F));

        PartDefinition cube_r1 = bone5.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(62, 28).addBox(0.0F, -2.5F, 0.0F, 2.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.1309F, 0.0F));

        PartDefinition cube_r2 = bone5.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(50, 61).addBox(-2.0F, -2.5F, 0.0F, 2.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.05F, 0.0F, 0.0F, 0.0F, 0.1309F, 0.0F));

        PartDefinition LowerTorso = partdefinition.addOrReplaceChild("LowerTorso", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -2.0F, 4.0F, 8.0F, 6.0F, 13.0F, new CubeDeformation(0.0F))
                .texOffs(0, 19).addBox(-4.0F, -2.0F, -3.0F, 8.0F, 6.0F, 7.0F, new CubeDeformation(0.2F))
                .texOffs(46, 88).addBox(4.5F, -1.0F, 5.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(42, 0).addBox(-6.0F, 0.0F, 5.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(84, 88).addBox(-5.5F, -1.0F, 5.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(42, 33).addBox(4.0F, 0.0F, 5.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(64, 39).addBox(-1.0F, -3.9F, -4.0F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(82, 41).addBox(-2.0F, -2.9F, -1.0F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.5F, -7.0F));

        PartDefinition cube_r3 = LowerTorso.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(42, 17).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(-0.5F, -7.1F, 7.2F, -1.0908F, 0.0F, 0.0F));

        PartDefinition cube_r4 = LowerTorso.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(40, 93).addBox(0.0F, -9.0F, -1.0F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -2.9F, -0.8F, -1.0908F, 0.0F, 0.0F));

        PartDefinition cube_r5 = LowerTorso.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(66, 61).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 7.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, -0.3F, 9.0F, 0.2182F, 0.0F, 0.0F));

        PartDefinition Saddle = LowerTorso.addOrReplaceChild("Saddle", CubeListBuilder.create(), PartPose.offset(0.0F, 3.0F, 8.0F));

        PartDefinition LeftLeg2 = LowerTorso.addOrReplaceChild("LeftLeg2", CubeListBuilder.create(), PartPose.offset(3.5F, 0.0F, 15.875F));

        PartDefinition LeftThigh_r1 = LeftLeg2.addOrReplaceChild("LeftThigh_r1", CubeListBuilder.create().texOffs(36, 50).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

        PartDefinition LeftLowerLeg2 = LeftLeg2.addOrReplaceChild("LeftLowerLeg2", CubeListBuilder.create(), PartPose.offset(0.0F, 6.375F, 4.55F));

        PartDefinition LeftCalf_r1 = LeftLowerLeg2.addOrReplaceChild("LeftCalf_r1", CubeListBuilder.create().texOffs(68, 48).addBox(-2.01F, -0.125F, -2.9F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.125F, -6.05F, 0.8727F, 0.0F, 0.0F));

        PartDefinition LeftFoot2 = LeftLowerLeg2.addOrReplaceChild("LeftFoot2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.8F, 7.175F));

        PartDefinition LeftArch_r1 = LeftFoot2.addOrReplaceChild("LeftArch_r1", CubeListBuilder.create().texOffs(18, 79).addBox(-2.0F, -8.45F, -0.725F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.005F)), PartPose.offsetAndRotation(0.0F, 7.075F, -12.975F, -0.3491F, 0.0F, 0.0F));

        PartDefinition LeftPad2 = LeftFoot2.addOrReplaceChild("LeftPad2", CubeListBuilder.create().texOffs(72, 76).addBox(-2.0F, 0.0F, -10.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.325F, -4.425F));

        PartDefinition bone2 = LeftPad2.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offset(-2.5F, 2.0F, -10.5F));

        PartDefinition cube_r6 = bone2.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(56, 95).addBox(0.0F, -1.0F, -2.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -1.0F, 1.0F, 0.0F, -0.2182F, 0.0F));

        PartDefinition cube_r7 = bone2.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(52, 95).addBox(0.0F, -1.0F, -2.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -1.0F, 1.0F, 0.0F, -0.0873F, 0.0F));

        PartDefinition cube_r8 = bone2.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(48, 95).addBox(0.0F, -1.0F, -2.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -1.0F, 1.0F, 0.0F, 0.0873F, 0.0F));

        PartDefinition cube_r9 = bone2.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(44, 95).addBox(0.0F, -1.0F, -2.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -1.0F, 1.0F, 0.0F, 0.2182F, 0.0F));

        PartDefinition LeftLeg = LowerTorso.addOrReplaceChild("LeftLeg", CubeListBuilder.create(), PartPose.offset(4.0F, 0.0F, -0.7F));

        PartDefinition LeftUpperLeg_r1 = LeftLeg.addOrReplaceChild("LeftUpperLeg_r1", CubeListBuilder.create().texOffs(64, 0).addBox(5.5F, -6.89F, -4.2461F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(-7.75F, 5.5348F, 2.9528F, 0.0873F, 0.0F, 0.0F));

        PartDefinition LeftLowerLeg = LeftLeg.addOrReplaceChild("LeftLowerLeg", CubeListBuilder.create(), PartPose.offset(0.0F, 5.7848F, 10.7028F));

        PartDefinition LeftLowerLeg_r1 = LeftLowerLeg.addOrReplaceChild("LeftLowerLeg_r1", CubeListBuilder.create().texOffs(54, 17).addBox(-2.0F, 3.8638F, 2.7342F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, -5.275F, -13.575F, -0.2182F, 0.0F, 0.0F));

        PartDefinition LeftFoot = LeftLowerLeg.addOrReplaceChild("LeftFoot", CubeListBuilder.create().texOffs(80, 0).addBox(-1.95F, 0.0F, -10.0F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.25F, 5.7152F, -4.3278F));

        PartDefinition bone4 = LeftFoot.addOrReplaceChild("bone4", CubeListBuilder.create(), PartPose.offset(-2.4F, 2.0F, -10.5F));

        PartDefinition cube_r10 = bone4.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(16, 96).addBox(0.0F, -1.0F, -2.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -1.0F, 1.0F, 0.0F, -0.2182F, 0.0F));

        PartDefinition cube_r11 = bone4.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(92, 95).addBox(0.0F, -1.0F, -2.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -1.0F, 1.0F, 0.0F, -0.0873F, 0.0F));

        PartDefinition cube_r12 = bone4.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(88, 95).addBox(0.0F, -1.0F, -2.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -1.0F, 1.0F, 0.0F, 0.0873F, 0.0F));

        PartDefinition cube_r13 = bone4.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(84, 95).addBox(0.0F, -1.0F, -2.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -1.0F, 1.0F, 0.0F, 0.2182F, 0.0F));

        PartDefinition RightLeg = LowerTorso.addOrReplaceChild("RightLeg", CubeListBuilder.create(), PartPose.offset(-4.0F, 0.0F, -0.7F));

        PartDefinition RightUpperLeg_r1 = RightLeg.addOrReplaceChild("RightUpperLeg_r1", CubeListBuilder.create().texOffs(0, 63).addBox(-9.5F, -6.89F, -4.2461F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(7.75F, 5.5348F, 2.9528F, 0.0873F, 0.0F, 0.0F));

        PartDefinition RightLowerLeg = RightLeg.addOrReplaceChild("RightLowerLeg", CubeListBuilder.create(), PartPose.offset(0.0F, 5.7848F, 10.7028F));

        PartDefinition RightLowerLeg_r1 = RightLowerLeg.addOrReplaceChild("RightLowerLeg_r1", CubeListBuilder.create().texOffs(52, 50).addBox(-2.0F, 3.8638F, 2.7342F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, -5.275F, -13.575F, -0.2182F, 0.0F, 0.0F));

        PartDefinition RightFoot = RightLowerLeg.addOrReplaceChild("RightFoot", CubeListBuilder.create().texOffs(0, 79).addBox(-2.025F, 0.0F, -10.0F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.25F, 5.7152F, -4.3278F));

        PartDefinition bone = RightFoot.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(-2.25F, 2.0F, -10.5F));

        PartDefinition cube_r14 = bone.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(46, 84).addBox(0.0F, -1.0F, -2.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -1.0F, 1.0F, 0.0F, -0.2182F, 0.0F));

        PartDefinition cube_r15 = bone.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(80, 7).addBox(0.0F, -1.0F, -2.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -1.0F, 1.0F, 0.0F, -0.0873F, 0.0F));

        PartDefinition cube_r16 = bone.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(78, 34).addBox(0.0F, -1.0F, -2.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -1.0F, 1.0F, 0.0F, 0.0873F, 0.0F));

        PartDefinition cube_r17 = bone.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(64, 11).addBox(0.0F, -1.0F, -2.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -1.0F, 1.0F, 0.0F, 0.2182F, 0.0F));

        PartDefinition Tail = LowerTorso.addOrReplaceChild("Tail", CubeListBuilder.create(), PartPose.offset(0.0F, 0.1F, 14.7F));

        PartDefinition TailPrimary = Tail.addOrReplaceChild("TailPrimary", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r18 = TailPrimary.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(90, 76).addBox(-2.0F, 0.0F, -2.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(52, 72).addBox(3.0F, 0.0F, -2.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, 3.0F, -0.5236F, 0.0F, 0.0F));

        PartDefinition cube_r19 = TailPrimary.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(86, 17).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 3.9F, 0.0F, 0.5236F, 1.5708F));

        PartDefinition Base_r1 = TailPrimary.addOrReplaceChild("Base_r1", CubeListBuilder.create().texOffs(68, 83).addBox(-2.0F, -2.9F, 0.4F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

        PartDefinition TailSecondary = TailPrimary.addOrReplaceChild("TailSecondary", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 3.5F));

        PartDefinition cube_r20 = TailSecondary.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(84, 83).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.3F, 3.5F, 0.0F, -0.3927F, -1.5708F));

        PartDefinition cube_r21 = TailSecondary.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(84, 61).addBox(-2.0F, 0.2F, 1.8F, 3.0F, 0.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 74).addBox(3.0F, 0.2F, 1.8F, 3.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -1.0F, -0.5F, -0.3927F, 0.0F, 0.0F));

        PartDefinition cube_r22 = TailSecondary.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(84, 56).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.3F, 3.5F, 0.0F, 0.3927F, 1.5708F));

        PartDefinition Base_r2 = TailSecondary.addOrReplaceChild("Base_r2", CubeListBuilder.create().texOffs(18, 49).addBox(-1.5F, -1.4F, -2.7F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.05F)), PartPose.offsetAndRotation(0.0F, 1.0F, 2.5F, -0.3927F, 0.0F, 0.0F));

        PartDefinition TailTertiary = TailSecondary.addOrReplaceChild("TailTertiary", CubeListBuilder.create(), PartPose.offset(0.0F, 2.5F, 5.0F));

        PartDefinition cube_r23 = TailTertiary.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(16, 88).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.3F, 2.9F, 0.0F, -0.1309F, -1.5708F));

        PartDefinition cube_r24 = TailTertiary.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(86, 71).addBox(-2.0F, 1.8F, 6.3F, 3.0F, 0.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 86).addBox(3.0F, 1.8F, 6.3F, 3.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -3.5F, -5.5F, -0.1309F, 0.0F, 0.0F));

        PartDefinition cube_r25 = TailTertiary.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(86, 7).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.3F, 2.9F, 0.0F, 0.1309F, 1.5708F));

        PartDefinition Base_r3 = TailTertiary.addOrReplaceChild("Base_r3", CubeListBuilder.create().texOffs(0, 43).addBox(-1.5F, -13.225F, 6.6F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.22F)), PartPose.offsetAndRotation(0.0F, 10.5F, -8.5F, -0.1309F, 0.0F, 0.0F));

        PartDefinition TailQuaternary = TailTertiary.addOrReplaceChild("TailQuaternary", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, 4.5F));

        PartDefinition cube_r26 = TailQuaternary.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(66, 70).addBox(-2.5F, 0.0F, -2.0F, 4.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.1F, 3.1F, 0.0F, 0.0436F, -1.5708F));

        PartDefinition cube_r27 = TailQuaternary.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(70, 21).addBox(-1.5F, 0.0F, -2.0F, 4.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.1F, 3.1F, 0.0F, -0.0436F, 1.5708F));

        PartDefinition cube_r28 = TailQuaternary.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(86, 66).addBox(-1.2F, 4.3F, 10.7F, 3.0F, 0.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(86, 12).addBox(2.2F, 4.3F, 10.7F, 3.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -4.0F, -10.0F, 0.0436F, 0.0F, 0.0F));

        PartDefinition Base_r4 = TailQuaternary.addOrReplaceChild("Base_r4", CubeListBuilder.create().texOffs(54, 28).addBox(0.0F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(-0.5F, -0.8171F, 5.773F, 0.0436F, 0.0F, 0.0F));

        PartDefinition Base_r5 = TailQuaternary.addOrReplaceChild("Base_r5", CubeListBuilder.create().texOffs(90, 21).addBox(-1.0F, -10.45F, 13.5F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.05F)), PartPose.offsetAndRotation(0.0F, 10.0F, -13.0F, 0.0436F, 0.0F, 0.0F));

        PartDefinition LeftWing = LowerTorso.addOrReplaceChild("LeftWing", CubeListBuilder.create(), PartPose.offsetAndRotation(2.2F, -2.0F, 4.0F, 1.742F, -0.4974F, -0.7953F));

        PartDefinition leftWingRoot = LeftWing.addOrReplaceChild("leftWingRoot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r29 = leftWingRoot.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(32, 72).addBox(15.975F, -4.475F, 1.65F, 10.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 20.0F, -2.0F, 0.0F, 0.0F, -1.2654F));

        PartDefinition cube_r30 = leftWingRoot.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(68, 58).addBox(19.0123F, -12.7627F, 1.2462F, 6.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-2.0F, 20.0F, -2.0F, 0.0F, 0.0F, -0.7854F));

        PartDefinition cube_r31 = leftWingRoot.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(16, 93).addBox(7.775F, -19.75F, 1.2F, 5.0F, 2.0F, 1.0F, new CubeDeformation(-0.11F)), PartPose.offsetAndRotation(-2.0F, 20.0F, -2.0F, 0.0F, 0.0F, -0.3491F));

        PartDefinition leftSecondaries = leftWingRoot.addOrReplaceChild("leftSecondaries", CubeListBuilder.create(), PartPose.offsetAndRotation(7.3F, -7.0F, -0.5F, 0.0F, 0.0F, -0.2618F));

        PartDefinition cube_r32 = leftSecondaries.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(60, 88).addBox(-1.7886F, -3.5029F, -0.3544F, 1.0F, 15.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-0.3F, 3.025F, 0.2F, -0.0051F, 0.0001F, 0.2384F));

        PartDefinition cube_r33 = leftSecondaries.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(30, 19).addBox(12.525F, -13.85F, 1.648F, 12.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.3F, 27.0F, -1.5F, 0.0F, 0.0F, -0.7418F));

        PartDefinition cube_r34 = leftSecondaries.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(42, 10).addBox(11.4F, 8.625F, 1.651F, 11.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.3F, 27.0F, -1.5F, 0.0F, 0.0F, -1.8326F));

        PartDefinition leftTertiaries = leftSecondaries.addOrReplaceChild("leftTertiaries", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.3F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

        PartDefinition cube_r35 = leftTertiaries.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(12, 91).addBox(-0.8995F, -0.7819F, -0.6457F, 1.0F, 15.0F, 1.0F, new CubeDeformation(-0.151F)), PartPose.offsetAndRotation(-0.2726F, 0.2793F, 0.5232F, -0.0105F, 0.059F, 0.0727F));

        PartDefinition cube_r36 = leftTertiaries.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(20, 33).addBox(14.125F, -10.525F, 1.64F, 11.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, 27.0F, -1.5F, 0.0F, 0.0F, -0.8727F));

        PartDefinition cube_r37 = leftTertiaries.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(0, 91).addBox(-0.8995F, -0.7819F, -0.6457F, 1.0F, 15.0F, 1.0F, new CubeDeformation(-0.151F)), PartPose.offsetAndRotation(-0.2726F, 0.2793F, 0.5232F, -0.0109F, 0.0658F, 0.07F));

        PartDefinition RightWing = LowerTorso.addOrReplaceChild("RightWing", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.2F, -2.0F, 4.0F, 1.742F, 0.4974F, 0.7953F));

        PartDefinition rightWingRoot = RightWing.addOrReplaceChild("rightWingRoot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r38 = rightWingRoot.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(52, 76).addBox(-25.975F, -4.475F, 1.65F, 10.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 20.0F, -2.0F, 0.0F, 0.0F, 1.2654F));

        PartDefinition cube_r39 = rightWingRoot.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(90, 80).addBox(-25.0123F, -12.7627F, 1.2462F, 6.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(2.0F, 20.0F, -2.0F, 0.0F, 0.0F, 0.7854F));

        PartDefinition cube_r40 = rightWingRoot.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(28, 93).addBox(-12.775F, -19.75F, 1.2F, 5.0F, 2.0F, 1.0F, new CubeDeformation(-0.11F)), PartPose.offsetAndRotation(2.0F, 20.0F, -2.0F, 0.0F, 0.0F, 0.3491F));

        PartDefinition rightSecondaries = rightWingRoot.addOrReplaceChild("rightSecondaries", CubeListBuilder.create(), PartPose.offsetAndRotation(-7.3F, -7.0F, -0.5F, 0.0F, 0.0F, 0.2618F));

        PartDefinition cube_r41 = rightSecondaries.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(42, 43).addBox(-22.4F, 8.625F, 1.651F, 11.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.3F, 27.0F, -1.5F, 0.0F, 0.0F, 1.8326F));

        PartDefinition cube_r42 = rightSecondaries.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(64, 88).addBox(0.7886F, -3.5029F, -0.3544F, 1.0F, 15.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.3F, 3.025F, 0.2F, -0.0051F, -0.0001F, -0.2384F));

        PartDefinition cube_r43 = rightSecondaries.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(30, 26).addBox(-24.525F, -13.85F, 1.648F, 12.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.3F, 27.0F, -1.5F, 0.0F, 0.0F, 0.7418F));

        PartDefinition rightTertiaries = rightSecondaries.addOrReplaceChild("rightTertiaries", CubeListBuilder.create(), PartPose.offsetAndRotation(0.3F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3491F));

        PartDefinition cube_r44 = rightTertiaries.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(8, 91).addBox(-0.1005F, -0.7819F, -0.6457F, 1.0F, 15.0F, 1.0F, new CubeDeformation(-0.151F)), PartPose.offsetAndRotation(0.2726F, 0.2793F, 0.5232F, -0.0109F, -0.0658F, -0.07F));

        PartDefinition cube_r45 = rightTertiaries.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(4, 91).addBox(-0.1005F, -0.7819F, -0.6457F, 1.0F, 15.0F, 1.0F, new CubeDeformation(-0.151F)), PartPose.offsetAndRotation(0.2726F, 0.2793F, 0.5232F, -0.0105F, -0.059F, -0.0727F));

        PartDefinition cube_r46 = rightTertiaries.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(20, 41).addBox(-25.125F, -10.525F, 1.64F, 11.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.0F, 27.0F, -1.5F, 0.0F, 0.0F, 0.8727F));

        PartDefinition RightLeg2 = LowerTorso.addOrReplaceChild("RightLeg2", CubeListBuilder.create(), PartPose.offset(-3.5F, 0.0F, 15.875F));

        PartDefinition RightThigh_r1 = RightLeg2.addOrReplaceChild("RightThigh_r1", CubeListBuilder.create().texOffs(16, 58).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

        PartDefinition RightLowerLeg2 = RightLeg2.addOrReplaceChild("RightLowerLeg2", CubeListBuilder.create(), PartPose.offset(0.0F, 6.375F, 4.55F));

        PartDefinition RightCalf_r1 = RightLowerLeg2.addOrReplaceChild("RightCalf_r1", CubeListBuilder.create().texOffs(70, 11).addBox(-1.99F, -0.125F, -2.9F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.125F, -6.05F, 0.8727F, 0.0F, 0.0F));

        PartDefinition RightFoot2 = RightLowerLeg2.addOrReplaceChild("RightFoot2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.8F, 7.175F));

        PartDefinition RightArch_r1 = RightFoot2.addOrReplaceChild("RightArch_r1", CubeListBuilder.create().texOffs(84, 47).addBox(-2.0F, -8.45F, -0.725F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.005F)), PartPose.offsetAndRotation(0.0F, 7.075F, -12.975F, -0.3491F, 0.0F, 0.0F));

        PartDefinition RightPad2 = RightFoot2.addOrReplaceChild("RightPad2", CubeListBuilder.create().texOffs(78, 27).addBox(-2.0F, 0.0F, -10.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.325F, -4.425F));

        PartDefinition bone3 = RightPad2.addOrReplaceChild("bone3", CubeListBuilder.create(), PartPose.offset(-2.5F, 2.0F, -10.5F));

        PartDefinition cube_r47 = bone3.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(56, 95).addBox(0.0F, -1.0F, -2.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -1.0F, 1.0F, 0.0F, -0.2182F, 0.0F));

        PartDefinition cube_r48 = bone3.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(52, 95).addBox(0.0F, -1.0F, -2.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -1.0F, 1.0F, 0.0F, -0.0873F, 0.0F));

        PartDefinition cube_r49 = bone3.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(48, 95).addBox(0.0F, -1.0F, -2.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -1.0F, 1.0F, 0.0F, 0.0873F, 0.0F));

        PartDefinition cube_r50 = bone3.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(44, 95).addBox(0.0F, -1.0F, -2.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -1.0F, 1.0F, 0.0F, 0.2182F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public ModelPart getArm(HumanoidArm arm) {
        return arm == HumanoidArm.LEFT ? this.FrontLeftLeg : this.FrontRightLeg;
    }

    public ModelPart getLeg(HumanoidArm arm) {
        return arm == HumanoidArm.LEFT ? this.BackLeftLeg : this.BackRightLeg;
    }

    @Override
    public @NotNull ModelPart getHead() {
        return this.Head;
    }

    public ModelPart getTorso() {
        return this.LowerTorso;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer,
                               int packedLight, int packedOverlay,
                               float red, float green, float blue, float alpha) {
        this.Head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LowerTorso.render(poseStack, buffer, packedLight, packedOverlay);
    }

    public HumanoidAnimator<LatexAgmisnBotEntity, ModelLatexAgmisnBot> getAnimator(LatexAgmisnBotEntity entity) {
        return this.animator;
    }

    public ModelPart getLowerTorso() {
        return this.LowerTorso;
    }
}