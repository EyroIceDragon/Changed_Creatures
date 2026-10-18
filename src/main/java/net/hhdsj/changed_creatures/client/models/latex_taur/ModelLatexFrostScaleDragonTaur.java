package net.hhdsj.changed_creatures.client.models.latex_taur;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.client.renderer.animate.GoodBlockAnimatorPresets;
import net.hhdsj.changed_creatures.entity.simple.LatexFrostScaleDragonTaurEntity;
import net.ltxprogrammer.changed.client.renderer.animate.HumanoidAnimator;
import net.ltxprogrammer.changed.client.renderer.model.AdvancedHumanoidModel;
import net.ltxprogrammer.changed.client.renderer.model.LowerTorsoedModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.HumanoidArm;
import org.jetbrains.annotations.NotNull;

import java.util.List;

//AdvancedHumanoidModelInterface<LatexFrostScaleDragonTaurEntity, ModelLatexDarkPurpleDragonTaur>,
public class ModelLatexFrostScaleDragonTaur extends AdvancedHumanoidModel<LatexFrostScaleDragonTaurEntity> implements LowerTorsoedModel {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this models's constructor
    public static final ModelLayerLocation LAYER_LOCATION =
            ChangedCreature.ChangedCreatureModelResourceLocation("latex_frost_scale_dragon_taur");
    private final ModelPart FrontRightLeg;
    private final ModelPart FrontLeftLeg;
    private final ModelPart BackRightLeg;
    private final ModelPart BackLeftLeg;
    private final ModelPart RightArm;
    private final ModelPart LeftArm;
    private final ModelPart Head;
    private final ModelPart Torso;
    private final ModelPart LowerTorso;
    private final ModelPart Saddle;
    private final ModelPart Tail;
    private final ModelPart RightWing;
    private final ModelPart LeftWing;

    private final HumanoidAnimator<LatexFrostScaleDragonTaurEntity, ModelLatexFrostScaleDragonTaur> animator;

    public ModelLatexFrostScaleDragonTaur(ModelPart root) {
        super(root);
        this.Head = root.getChild("Head");
        this.Torso = root.getChild("Torso");
        this.LowerTorso = root.getChild("LowerTorso");
        this.Saddle = LowerTorso.getChild("Saddle");
        this.RightArm = root.getChild("RightArm");
        this.LeftArm = root.getChild("LeftArm");
        this.RightWing = LowerTorso.getChild("RightWing");
        this.LeftWing = LowerTorso.getChild("LeftWing");

        this.FrontRightLeg = LowerTorso.getChild("RightLeg");
        this.FrontLeftLeg = LowerTorso.getChild("LeftLeg");
        this.BackRightLeg = LowerTorso.getChild("RightLeg2");
        this.BackLeftLeg = LowerTorso.getChild("LeftLeg2");
        this.Tail = LowerTorso.getChild("Tail");

        var tailPrimary = Tail.getChild("TailPrimary");
        var tailSecondary = tailPrimary.getChild("TailSecondary");
        var tailTertiary = tailSecondary.getChild("TailTertiary");
		var tailQuaternary = tailTertiary.getChild("TailQuaternary");

        var leftLowerLeg = FrontLeftLeg.getChild("LeftLowerLeg");
        var rightLowerLeg = FrontRightLeg.getChild("RightLowerLeg");

        var leftLowerLeg2 = BackLeftLeg.getChild("LeftLowerLeg2");
        var leftFoot2 = leftLowerLeg2.getChild("LeftFoot2");
        var rightLowerLeg2 = BackRightLeg.getChild("RightLowerLeg2");
        var rightFoot2 = rightLowerLeg2.getChild("RightFoot2");
        var leftWingRoot = LeftWing.getChild("leftWingRoot");
        var rightWingRoot = RightWing.getChild("rightWingRoot");

        animator = HumanoidAnimator.of(this).addPreset(GoodBlockAnimatorPresets.FrostScaleDragonTaurLike(
        Head, Head.getChild("bone").getChild("Left"), Head.getChild("bone").getChild("Right"),
        Torso, LeftArm, RightArm,
        LowerTorso, FrontLeftLeg,
        leftLowerLeg, leftLowerLeg.getChild("LeftFoot"),
        FrontRightLeg, rightLowerLeg, rightLowerLeg.getChild("RightFoot"),
        BackLeftLeg, leftLowerLeg2, leftFoot2,
        leftFoot2.getChild("LeftPad2"), BackRightLeg,
        rightLowerLeg2, rightFoot2, rightFoot2.getChild("RightPad2"),
        // 左翼 - 修复
        leftWingRoot, 
        leftWingRoot.getChild("leftFirstise"), 
        leftWingRoot.getChild("leftFirstise").getChild("leftSecondaries"), 
        leftWingRoot.getChild("leftFirstise").getChild("leftSecondaries").getChild("leftTertiaries"),
        // 右翼 - 修复
        rightWingRoot, 
        rightWingRoot.getChild("rightFirsties"), 
        rightWingRoot.getChild("rightFirsties").getChild("rightSecondaries"), 
        rightWingRoot.getChild("rightFirsties").getChild("rightSecondaries").getChild("rightTertiaries"),
        Tail, List.of(tailPrimary, tailSecondary, tailTertiary, tailQuaternary)))
         .forwardOffset(-7.0f).hipOffset(-1.5f).legLength(13.5f).torsoLength(11.05f);
    }

    @SuppressWarnings("unused")
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 25).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(68, 39).addBox(-2.0F, -3.0F, -6.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(68, 43).addBox(-1.5F, -1.0F, -5.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, -7.0F));

        PartDefinition bone = Head.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 0.0F));

        PartDefinition Right = bone.addOrReplaceChild("Right", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.0F, -8.0F, -1.5F, 0.0F, 0.0F, -0.3054F));

        PartDefinition cube_r1 = Right.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(71, 119).addBox(-1.0F, -6.0F, -1.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.35F, 0.8F, 1.85F, -0.9163F, -0.3491F, 0.0F));

        PartDefinition cube_r2 = Right.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(68, 112).addBox(0.0F, -5.0F, -1.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.65F, 1.15F, 1.85F, -0.9163F, -0.3491F, 0.0F));

        PartDefinition RightHorn = Right.addOrReplaceChild("RightHorn", CubeListBuilder.create(), PartPose.offsetAndRotation(0.1031F, -3.3989F, 2.6305F, 0.0F, 0.0F, -0.0436F));

        PartDefinition Horn_r1 = RightHorn.addOrReplaceChild("Horn_r1", CubeListBuilder.create().texOffs(52, 107).mirror().addBox(-3.0F, -31.3F, 18.8F, 2.0F, 2.0F, 3.0F, new CubeDeformation(-0.3F)).mirror(false), PartPose.offsetAndRotation(2.4903F, 35.7215F, -2.6305F, 0.48F, -0.1745F, 0.0F));

        PartDefinition Horn_r2 = RightHorn.addOrReplaceChild("Horn_r2", CubeListBuilder.create().texOffs(52, 97).addBox(-3.0F, -31.3F, 18.8F, 2.0F, 2.0F, 3.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(2.1903F, 34.7215F, -0.8305F, 0.48F, -0.1745F, 0.0F));

        PartDefinition Horn_r3 = RightHorn.addOrReplaceChild("Horn_r3", CubeListBuilder.create().texOffs(52, 117).addBox(-3.0F, -35.2F, 8.1F, 2.0F, 2.0F, 3.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(2.4903F, 35.7215F, -2.6305F, 0.2182F, -0.1745F, 0.0F));

        PartDefinition Horn_r4 = RightHorn.addOrReplaceChild("Horn_r4", CubeListBuilder.create().texOffs(53, 122).addBox(-3.0F, -30.75F, -19.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(2.4903F, 35.7215F, -2.6305F, -0.5672F, -0.1745F, 0.0F));

        PartDefinition Left = bone.addOrReplaceChild("Left", CubeListBuilder.create(), PartPose.offsetAndRotation(2.9F, -8.0F, -1.5F, 0.0F, 0.0F, 0.3054F));

        PartDefinition cube_r3 = Left.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(61, 119).addBox(-1.0F, -6.0F, -1.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.45F, 0.8F, 1.85F, -0.9163F, 0.3491F, 0.0F));

        PartDefinition cube_r4 = Left.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(62, 112).addBox(-1.0F, -5.0F, -1.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.55F, 1.15F, 1.85F, -0.9163F, 0.3491F, 0.0F));

        PartDefinition LeftHorn = Left.addOrReplaceChild("LeftHorn", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.3028F, -3.5865F, 2.6305F, 0.0F, 0.0F, 0.0436F));

        PartDefinition Horn_r5 = LeftHorn.addOrReplaceChild("Horn_r5", CubeListBuilder.create().texOffs(52, 112).addBox(1.0F, -31.3F, 18.8F, 2.0F, 2.0F, 3.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(-2.1903F, 34.7215F, -0.8305F, 0.48F, 0.1745F, 0.0F));

        PartDefinition Horn_r6 = LeftHorn.addOrReplaceChild("Horn_r6", CubeListBuilder.create().texOffs(52, 92).addBox(1.0F, -31.3F, 18.8F, 2.0F, 2.0F, 3.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(-2.4903F, 35.7215F, -2.6305F, 0.48F, 0.1745F, 0.0F));

        PartDefinition Horn_r7 = LeftHorn.addOrReplaceChild("Horn_r7", CubeListBuilder.create().texOffs(52, 102).addBox(1.0F, -35.2F, 8.1F, 2.0F, 2.0F, 3.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(-2.4903F, 35.7215F, -2.6305F, 0.2182F, 0.1745F, 0.0F));

        PartDefinition Horn_r8 = LeftHorn.addOrReplaceChild("Horn_r8", CubeListBuilder.create().texOffs(53, 86).addBox(1.0F, -30.75F, -19.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-2.4903F, 35.7215F, -2.6305F, -0.5672F, 0.1745F, 0.0F));

        PartDefinition Ear = bone.addOrReplaceChild("Ear", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition Torso = partdefinition.addOrReplaceChild("Torso", CubeListBuilder.create().texOffs(32, 25).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, -7.0F));

        PartDefinition LowerTorso = partdefinition.addOrReplaceChild("LowerTorso", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -2.0F, -2.0F, 8.0F, 6.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.5F, -7.0F));

        PartDefinition Saddle = LowerTorso.addOrReplaceChild("Saddle", CubeListBuilder.create().texOffs(92, 0).addBox(-4.0F, -5.0F, -2.0F, 8.0F, 9.0F, 10.0F, new CubeDeformation(0.15F)), PartPose.offset(0.0F, 3.0F, 5.0F));

        PartDefinition LeftLeg2 = LowerTorso.addOrReplaceChild("LeftLeg2", CubeListBuilder.create(), PartPose.offset(3.5F, 0.0F, 15.875F));

        PartDefinition LeftThigh_r1 = LeftLeg2.addOrReplaceChild("LeftThigh_r1", CubeListBuilder.create().texOffs(16, 41).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

        PartDefinition LeftLowerLeg2 = LeftLeg2.addOrReplaceChild("LeftLowerLeg2", CubeListBuilder.create(), PartPose.offset(0.0F, 6.375F, 1.55F));

        PartDefinition LeftCalf_r1 = LeftLowerLeg2.addOrReplaceChild("LeftCalf_r1", CubeListBuilder.create().texOffs(56, 22).addBox(-2.01F, -0.125F, -2.9F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.125F, -3.05F, 0.8727F, 0.0F, 0.0F));

        PartDefinition LeftFoot2 = LeftLowerLeg2.addOrReplaceChild("LeftFoot2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.8F, 7.175F));

        PartDefinition LeftArch_r1 = LeftFoot2.addOrReplaceChild("LeftArch_r1", CubeListBuilder.create().texOffs(66, 65).addBox(-2.0F, -8.45F, -0.725F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.005F)), PartPose.offsetAndRotation(0.0F, 7.075F, -9.975F, -0.3491F, 0.0F, 0.0F));

        PartDefinition LeftPad2 = LeftFoot2.addOrReplaceChild("LeftPad2", CubeListBuilder.create().texOffs(56, 32).addBox(-2.0F, 0.0F, -7.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.325F, -4.425F));

        PartDefinition RightLeg2 = LowerTorso.addOrReplaceChild("RightLeg2", CubeListBuilder.create(), PartPose.offset(-3.5F, 0.0F, 15.875F));

        PartDefinition RightThigh_r1 = RightLeg2.addOrReplaceChild("RightThigh_r1", CubeListBuilder.create().texOffs(48, 51).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, -0.2182F, 0.0F, 0.0F));

        PartDefinition RightLowerLeg2 = RightLeg2.addOrReplaceChild("RightLowerLeg2", CubeListBuilder.create(), PartPose.offset(0.0F, 6.375F, 2.05F));

        PartDefinition RightCalf_r1 = RightLowerLeg2.addOrReplaceChild("RightCalf_r1", CubeListBuilder.create().texOffs(0, 57).addBox(-1.99F, -0.125F, -2.9F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.125F, -3.05F, 0.8727F, 0.0F, 0.0F));

        PartDefinition RightFoot2 = RightLowerLeg2.addOrReplaceChild("RightFoot2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.8F, 7.175F));

        PartDefinition RightArch_r1 = RightFoot2.addOrReplaceChild("RightArch_r1", CubeListBuilder.create().texOffs(0, 67).addBox(-2.0F, -8.45F, -0.725F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.005F)), PartPose.offsetAndRotation(0.0F, 7.075F, -9.975F, -0.3491F, 0.0F, 0.0F));

        PartDefinition RightPad2 = RightFoot2.addOrReplaceChild("RightPad2", CubeListBuilder.create().texOffs(48, 62).addBox(-2.0F, 0.0F, -7.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.325F, -4.425F));

        PartDefinition LeftLeg = LowerTorso.addOrReplaceChild("LeftLeg", CubeListBuilder.create(), PartPose.offset(4.0F, 0.0F, -1.7F));

        PartDefinition LeftUpperLeg_r1 = LeftLeg.addOrReplaceChild("LeftUpperLeg_r1", CubeListBuilder.create().texOffs(54, 11).addBox(-2.0F, -6.89F, -4.2461F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(-0.25F, 5.5348F, 3.9528F, 0.0873F, 0.0F, 0.0F));

        PartDefinition LeftLowerLeg = LeftLeg.addOrReplaceChild("LeftLowerLeg", CubeListBuilder.create(), PartPose.offset(-0.25F, 5.7848F, 8.7028F));

        PartDefinition LeftLowerLeg_r1 = LeftLowerLeg.addOrReplaceChild("LeftLowerLeg_r1", CubeListBuilder.create().texOffs(32, 55).addBox(-2.0F, 3.8638F, 2.7342F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.275F, -10.575F, -0.2182F, 0.0F, 0.0F));

        PartDefinition LeftFoot = LeftLowerLeg.addOrReplaceChild("LeftFoot", CubeListBuilder.create().texOffs(66, 58).addBox(-1.95F, 0.0F, -7.0F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.7152F, -4.3278F));

        PartDefinition RightLeg = LowerTorso.addOrReplaceChild("RightLeg", CubeListBuilder.create(), PartPose.offset(-4.0F, 0.0F, -1.7F));

        PartDefinition RightUpperLeg_r1 = RightLeg.addOrReplaceChild("RightUpperLeg_r1", CubeListBuilder.create().texOffs(54, 0).addBox(-9.5F, -6.89F, -4.2461F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(7.75F, 5.5348F, 3.9528F, 0.0873F, 0.0F, 0.0F));

        PartDefinition RightLowerLeg = RightLeg.addOrReplaceChild("RightLowerLeg", CubeListBuilder.create(), PartPose.offset(0.0F, 5.7848F, 8.7028F));

        PartDefinition RightLowerLeg_r1 = RightLowerLeg.addOrReplaceChild("RightLowerLeg_r1", CubeListBuilder.create().texOffs(16, 52).addBox(-2.0F, 3.8638F, 2.7342F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, -5.275F, -10.575F, -0.2182F, 0.0F, 0.0F));

        PartDefinition RightFoot = RightLowerLeg.addOrReplaceChild("RightFoot", CubeListBuilder.create().texOffs(64, 51).addBox(-2.025F, 0.0F, -7.0F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.25F, 5.7152F, -4.3278F));

        PartDefinition Tail = LowerTorso.addOrReplaceChild("Tail", CubeListBuilder.create(), PartPose.offset(0.0F, -0.5F, 14.0F));

        PartDefinition TailPrimary = Tail.addOrReplaceChild("TailPrimary", CubeListBuilder.create(), PartPose.offset(0.0F, -0.5F, 0.0F));

        PartDefinition TailBase_r1 = TailPrimary.addOrReplaceChild("TailBase_r1", CubeListBuilder.create().texOffs(92, 119).addBox(-2.5F, -8.25F, 11.6F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.18F)), PartPose.offsetAndRotation(0.0F, 18.0F, 0.0F, 1.0036F, 0.0F, 0.0F));

        PartDefinition TailSecondary = TailPrimary.addOrReplaceChild("TailSecondary", CubeListBuilder.create(), PartPose.offset(0.0F, 1.5F, 3.5F));

        PartDefinition TailBase_r2 = TailSecondary.addOrReplaceChild("TailBase_r2", CubeListBuilder.create().texOffs(112, 106).addBox(-2.0F, -1.75F, 12.6F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(0.0F, 16.5F, -3.5F, 1.1781F, 0.0F, 0.0F));

        PartDefinition TailTertiary = TailSecondary.addOrReplaceChild("TailTertiary", CubeListBuilder.create(), PartPose.offset(0.0F, 3.0F, 9.5F));

        PartDefinition TailBase_r3 = TailTertiary.addOrReplaceChild("TailBase_r3", CubeListBuilder.create().texOffs(112, 117).addBox(-2.0F, 9.0F, 10.3F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 13.5F, -13.0F, 1.4835F, 0.0F, 0.0F));

        PartDefinition TailQuaternary = TailTertiary.addOrReplaceChild("TailQuaternary", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 5.5F));

        PartDefinition TailBase_r4 = TailQuaternary.addOrReplaceChild("TailBase_r4", CubeListBuilder.create().texOffs(116, 97).addBox(-1.5F, 17.3F, 7.1F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.15F)), PartPose.offsetAndRotation(0.0F, 12.5F, -18.5F, 1.7017F, 0.0F, 0.0F));

        PartDefinition TailEnd = TailQuaternary.addOrReplaceChild("TailEnd", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 6.7F));

        PartDefinition cube_r5 = TailEnd.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(108, 105).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 1.8F, -2.4F, 1.7017F, 0.0F, 0.0F));

        PartDefinition LeftWing = LowerTorso.addOrReplaceChild("LeftWing", CubeListBuilder.create(), PartPose.offsetAndRotation(1.6F, -1.35F, 7.1F, 1.7453F, -0.3491F, -0.2618F));

        PartDefinition leftWingRoot = LeftWing.addOrReplaceChild("leftWingRoot", CubeListBuilder.create(), PartPose.offset(-0.3F, -0.3F, 0.0F));

        PartDefinition cube_r6 = leftWingRoot.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 113).addBox(5.2053F, -17.5097F, 1.6435F, 7.0F, 2.0F, 1.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(1.0F, 17.525F, -2.0F, 0.0F, 0.0F, -0.3491F));

        PartDefinition cube_r7 = leftWingRoot.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(30, 93).addBox(12.8509F, -5.5632F, 2.0935F, 11.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1F, 18.525F, -2.0F, 0.0F, 0.0F, -1.2654F));

        PartDefinition leftFirstise = leftWingRoot.addOrReplaceChild("leftFirstise", CubeListBuilder.create(), PartPose.offset(6.0F, -2.0F, 0.0F));

        PartDefinition cube_r8 = leftFirstise.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(24, 116).addBox(-2.9367F, -1.5985F, -3.6045F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(2.4245F, -1.1265F, 3.248F, 0.0F, 0.0F, -0.3927F));

        PartDefinition cube_r9 = leftFirstise.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(40, 113).addBox(-0.5F, -3.0F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9844F, 6.4217F, 0.1435F, 0.0F, 0.0F, 0.2705F));

        PartDefinition cube_r10 = leftFirstise.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(30, 107).addBox(-4.0F, -1.0F, -0.5F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.085F, -2.7754F, 0.1435F, 0.0F, 0.0F, -0.7854F));

        PartDefinition leftSecondaries = leftFirstise.addOrReplaceChild("leftSecondaries", CubeListBuilder.create(), PartPose.offsetAndRotation(5.675F, -5.375F, -0.5F, 0.0F, 0.0F, -0.5236F));

        PartDefinition cube_r11 = leftSecondaries.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(44, 113).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5801F, -0.6933F, 0.6435F, 0.0F, 0.0F, -2.0246F));

        PartDefinition cube_r12 = leftSecondaries.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(28, 77).addBox(-6.0F, -4.0F, 0.0F, 12.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.6423F, 9.7596F, 0.5945F, 0.0F, 0.0F, -1.8326F));

        PartDefinition cube_r13 = leftSecondaries.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(32, 113).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 11.0F, 1.0F, new CubeDeformation(0.05F)), PartPose.offsetAndRotation(0.4249F, -0.4427F, 0.6435F, 0.0F, 0.0F, 0.1178F));

        PartDefinition cube_r14 = leftSecondaries.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(16, 116).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.9991F, 14.1361F, 0.6435F, 0.0F, 0.0F, 0.48F));

        PartDefinition cube_r15 = leftSecondaries.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(0, 77).addBox(-7.0F, -5.0F, 0.0F, 14.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2082F, 8.3427F, 0.5915F, 0.0F, 0.0F, -0.7505F));

        PartDefinition leftTertiaries = leftSecondaries.addOrReplaceChild("leftTertiaries", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.35F, -0.25F, 0.0F, 0.0F, 0.0F, -0.9599F));

        PartDefinition cube_r16 = leftTertiaries.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(36, 113).addBox(-5.0516F, -20.9916F, 1.6435F, 1.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.4F, 30.4F, -1.5F, 0.0F, 0.0F, 0.5236F));

        PartDefinition cube_r17 = leftTertiaries.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(0, 97).addBox(9.3354F, -13.9881F, 2.0835F, 15.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 28.0F, -1.5F, 0.0F, 0.0F, -0.8727F));

        PartDefinition cube_r18 = leftTertiaries.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(8, 116).addBox(6.8623F, -25.869F, 1.6435F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.005F)), PartPose.offsetAndRotation(-5.55F, 26.5F, -1.5F, 0.0F, 0.0F, -0.0436F));

        PartDefinition RightWing = LowerTorso.addOrReplaceChild("RightWing", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.6F, -1.35F, 7.1F, 1.7453F, 0.3491F, 0.2618F));

        PartDefinition rightWingRoot = RightWing.addOrReplaceChild("rightWingRoot", CubeListBuilder.create(), PartPose.offset(0.3F, -0.3F, 0.0F));

        PartDefinition cube_r19 = rightWingRoot.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(16, 113).addBox(-12.2053F, -17.5097F, 1.6435F, 7.0F, 2.0F, 1.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-1.0F, 17.525F, -2.0F, 0.0F, 0.0F, 0.3491F));

        PartDefinition cube_r20 = rightWingRoot.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(30, 100).addBox(-23.8509F, -5.5632F, 2.0935F, 11.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.1F, 18.525F, -2.0F, 0.0F, 0.0F, 1.2654F));

        PartDefinition rightFirsties = rightWingRoot.addOrReplaceChild("rightFirsties", CubeListBuilder.create(), PartPose.offset(-6.0F, -2.0F, 0.0F));

        PartDefinition cube_r21 = rightFirsties.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(28, 116).addBox(1.9367F, -1.5985F, -3.6045F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-2.4245F, -1.1265F, 3.248F, 0.0F, 0.0F, 0.3927F));

        PartDefinition cube_r22 = rightFirsties.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(40, 120).addBox(-0.5F, -3.0F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.9844F, 6.4217F, 0.1435F, 0.0F, 0.0F, -0.2705F));

        PartDefinition cube_r23 = rightFirsties.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(30, 110).addBox(-4.0F, -1.0F, -0.5F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.085F, -2.7754F, 0.1435F, 0.0F, 0.0F, 0.7854F));

        PartDefinition rightSecondaries = rightFirsties.addOrReplaceChild("rightSecondaries", CubeListBuilder.create(), PartPose.offsetAndRotation(-5.675F, -5.375F, -0.5F, 0.0F, 0.0F, 0.5236F));

        PartDefinition cube_r24 = rightSecondaries.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(44, 116).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5801F, -0.6933F, 0.6435F, 0.0F, 0.0F, 2.0246F));

        PartDefinition cube_r25 = rightSecondaries.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(28, 85).addBox(-6.0F, -4.0F, 0.0F, 12.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6423F, 9.7596F, 0.5945F, 0.0F, 0.0F, 1.8326F));

        PartDefinition cube_r26 = rightSecondaries.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(0, 116).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 11.0F, 1.0F, new CubeDeformation(0.05F)), PartPose.offsetAndRotation(-0.4249F, -0.4427F, 0.6435F, 0.0F, 0.0F, -0.1178F));

        PartDefinition cube_r27 = rightSecondaries.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(20, 116).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.9991F, 14.1361F, 0.6435F, 0.0F, 0.0F, -0.48F));

        PartDefinition cube_r28 = rightSecondaries.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(0, 87).addBox(-7.0F, -5.0F, 0.0F, 14.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.2082F, 8.3427F, 0.5915F, 0.0F, 0.0F, 0.7505F));

        PartDefinition rightTertiaries = rightSecondaries.addOrReplaceChild("rightTertiaries", CubeListBuilder.create(), PartPose.offsetAndRotation(0.35F, -0.25F, 0.0F, 0.0F, 0.0F, 0.9599F));

        PartDefinition cube_r29 = rightTertiaries.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(4, 116).addBox(4.0516F, -20.9916F, 1.6435F, 1.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.4F, 30.4F, -1.5F, 0.0F, 0.0F, -0.5236F));

        PartDefinition cube_r30 = rightTertiaries.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(0, 105).addBox(-24.3354F, -13.9881F, 2.0835F, 15.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 28.0F, -1.5F, 0.0F, 0.0F, 0.8727F));

        PartDefinition cube_r31 = rightTertiaries.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(12, 116).addBox(-7.8623F, -25.869F, 1.6435F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.005F)), PartPose.offsetAndRotation(5.55F, 26.5F, -1.5F, 0.0F, 0.0F, 0.0436F));

        PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(0, 41).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 0.5F, -7.0F));

        PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(32, 39).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 0.5F, -7.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    public void setupHand(LatexFrostScaleDragonTaurEntity entity) {
        animator.setupHand();
    }

    @Override
    public void setupAnim(@NotNull LatexFrostScaleDragonTaurEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    }

    public ModelPart getArm(HumanoidArm p_102852_) {
        return p_102852_ == HumanoidArm.LEFT ? this.LeftArm : this.RightArm;
    }

    public ModelPart getLeg(HumanoidArm p_102852_) {
        return null;
    }

    public ModelPart getHead() {
        return this.Head;
    }

    public ModelPart getTorso() {
        return Torso;
    }

    /*

    @Override
    public HelperModel getTransfurHelperModel(Limb limb) {
        if (limb == Limb.LOWER_TORSO)
            return TransfurHelper.getTaurTorso();
        else if (limb == Limb.TORSO)
            return null;
        return super.getTransfurHelperModel(limb);
    }

     */

    @Override
    public boolean shouldPartTransfur(ModelPart part) {
        return super.shouldPartTransfur(part) && part != this.Saddle;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        Torso.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        RightArm.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        LeftArm.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.swapResetPoseStack(poseStack);
        LowerTorso.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.swapResetPoseStack(poseStack);
    }

    @Override
    public HumanoidAnimator<LatexFrostScaleDragonTaurEntity, ModelLatexFrostScaleDragonTaur> getAnimator(LatexFrostScaleDragonTaurEntity entity) {
        return animator;
    }

    @Override
    public ModelPart getLowerTorso() {
        return LowerTorso;
    }
}
