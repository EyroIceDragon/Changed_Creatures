package net.hhdsj.changed_creatures.client.models.latex_dragon;


import net.hhdsj.changed_creatures.entity.simple.LatexIceFieldWolfDragonEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.ltxprogrammer.changed.client.renderer.animate.AnimatorPresets;
import net.ltxprogrammer.changed.client.renderer.animate.HumanoidAnimator;

import net.minecraft.world.entity.HumanoidArm;
//Changed-mc mod import 我的世界转变MOD导入
import net.ltxprogrammer.changed.client.renderer.model.AdvancedHumanoidModel;


//Goodblock mod import
import net.hhdsj.changed_creatures.entity.*;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ModelIceFieldWolfDragon extends AdvancedHumanoidModel<LatexIceFieldWolfDragonEntity>{
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this models's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("changed_creatures", "latexnewfoxunified"), "main");
    private final ModelPart LeftLeg;
    public final ModelPart RightLeg;
    private final ModelPart RightArm;
    private final ModelPart LeftArm;
    private final ModelPart Head;
    private final ModelPart Torso;
    private final ModelPart Tail;
    private final HumanoidAnimator<LatexIceFieldWolfDragonEntity, ModelIceFieldWolfDragon> animator;

    public ModelIceFieldWolfDragon(ModelPart root) {
        super(root);
        this.RightLeg = root.getChild("RightLeg");
        this.LeftLeg = root.getChild("LeftLeg");
        this.Head = root.getChild("Head");
        this.Torso = root.getChild("Torso");
        this.Tail = Torso.getChild("Tail");
        this.RightArm = root.getChild("RightArm");
        this.LeftArm = root.getChild("LeftArm");

        var tailPrimary = Tail.getChild("TailPrimary");
        var tailSecondary = tailPrimary.getChild("TailSecondary");
        var tailTertiary = tailSecondary.getChild("TailTertiary");

        var leftLowerLeg = LeftLeg.getChild("LeftLowerLeg");
        var leftFoot = leftLowerLeg.getChild("LeftFoot");
        var rightLowerLeg = RightLeg.getChild("RightLowerLeg");
        var rightFoot = rightLowerLeg.getChild("RightFoot");

        animator = HumanoidAnimator.of(this).hipOffset(-1.5f)
                .addPreset(AnimatorPresets.dragonLike(
                        Head, Torso, LeftArm, RightArm,
                        Tail, List.of(tailPrimary, tailSecondary, tailTertiary),
                        LeftLeg, leftLowerLeg, leftFoot, leftFoot.getChild("LeftPad"), RightLeg, rightLowerLeg, rightFoot, rightFoot.getChild("RightPad")));
    }

	@SuppressWarnings("unused")
    public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create(), PartPose.offset(-2.5F, 10.5F, 0.0F));

		PartDefinition RightThigh_r1 = RightLeg.addOrReplaceChild("RightThigh_r1", CubeListBuilder.create().texOffs(48, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition RightLowerLeg = RightLeg.addOrReplaceChild("RightLowerLeg", CubeListBuilder.create(), PartPose.offset(0.0F, 6.375F, -3.45F));

		PartDefinition RightCalf_r1 = RightLowerLeg.addOrReplaceChild("RightCalf_r1", CubeListBuilder.create().texOffs(48, 40).addBox(-1.99F, -0.125F, -2.9F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.125F, 1.95F, 0.8727F, 0.0F, 0.0F));

		PartDefinition RightFoot = RightLowerLeg.addOrReplaceChild("RightFoot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.8F, 7.175F));

		PartDefinition RightArch_r1 = RightFoot.addOrReplaceChild("RightArch_r1", CubeListBuilder.create().texOffs(13, 57).addBox(-2.0F, -8.45F, -0.725F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.005F)), PartPose.offsetAndRotation(0.0F, 7.075F, -4.975F, -0.3491F, 0.0F, 0.0F));

		PartDefinition RightPad = RightFoot.addOrReplaceChild("RightPad", CubeListBuilder.create().texOffs(52, 32).addBox(-2.0F, 0.0F, -2.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(56, 59).addBox(-1.0F, 1.05F, -0.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(58, 60).addBox(-1.75F, 1.05F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(58, 60).addBox(-0.5F, 1.05F, -2.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(58, 60).addBox(0.75F, 1.05F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.325F, -4.425F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create(), PartPose.offset(2.5F, 10.5F, 0.0F));

		PartDefinition LeftThigh_r1 = LeftLeg.addOrReplaceChild("LeftThigh_r1", CubeListBuilder.create().texOffs(32, 44).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition LeftLowerLeg = LeftLeg.addOrReplaceChild("LeftLowerLeg", CubeListBuilder.create(), PartPose.offset(0.0F, 6.375F, -3.45F));

		PartDefinition LeftCalf_r1 = LeftLowerLeg.addOrReplaceChild("LeftCalf_r1", CubeListBuilder.create().texOffs(48, 22).addBox(-2.01F, -0.125F, -2.9F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.125F, 1.95F, 0.8727F, 0.0F, 0.0F));

		PartDefinition LeftFoot = LeftLowerLeg.addOrReplaceChild("LeftFoot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.8F, 7.175F));

		PartDefinition LeftArch_r1 = LeftFoot.addOrReplaceChild("LeftArch_r1", CubeListBuilder.create().texOffs(56, 11).addBox(-2.0F, -8.45F, -0.725F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.005F)), PartPose.offsetAndRotation(0.0F, 7.075F, -4.975F, -0.3491F, 0.0F, 0.0F));

		PartDefinition LeftPad = LeftFoot.addOrReplaceChild("LeftPad", CubeListBuilder.create().texOffs(24, 0).addBox(-2.0F, 0.0F, -2.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.325F, -4.425F));

		PartDefinition RightPad3 = LeftPad.addOrReplaceChild("RightPad3", CubeListBuilder.create().texOffs(47, 59).addBox(-1.0F, 1.05F, -0.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(49, 60).addBox(-1.75F, 1.05F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(49, 60).addBox(-0.5F, 1.05F, -2.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(49, 60).addBox(0.75F, 1.05F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(34, 74).addBox(-2.5F, -9.0F, -5.1F, 5.0F, 5.0F, 2.0F, new CubeDeformation(-1.0F)), PartPose.offset(0.0F, -0.5F, 0.0F));

		PartDefinition RightEar = Head.addOrReplaceChild("RightEar", CubeListBuilder.create(), PartPose.offset(-3.0F, -7.7F, 0.0F));

		PartDefinition RightEarPivot = RightEar.addOrReplaceChild("RightEarPivot", CubeListBuilder.create().texOffs(0, 4).addBox(-1.9F, -1.2F, -1.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.05F))
				.texOffs(0, 16).addBox(-0.9F, -1.6F, -0.4F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.04F))
				.texOffs(64, 5).addBox(-0.9F, -0.6F, 0.1F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.04F))
				.texOffs(32, 22).addBox(-0.9F, -2.3F, -1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.05F))
				.texOffs(24, 0).addBox(0.1F, -3.2F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.05F)), PartPose.offsetAndRotation(0.5F, -1.25F, 0.0F, -0.1309F, 0.5236F, -0.3491F));

		PartDefinition cube_r1 = RightEarPivot.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(76, 0).addBox(0.0F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.9F, -1.2F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition LeftEar = Head.addOrReplaceChild("LeftEar", CubeListBuilder.create(), PartPose.offset(3.0F, -7.7F, 0.0F));

		PartDefinition LeftEarPivot = LeftEar.addOrReplaceChild("LeftEarPivot", CubeListBuilder.create().texOffs(0, 0).addBox(-1.1F, -1.2F, -1.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.05F))
				.texOffs(0, 20).addBox(-1.1F, -1.6F, -0.4F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.04F))
				.texOffs(64, 8).addBox(-1.1F, -0.6F, 0.1F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.04F))
				.texOffs(32, 24).addBox(-1.1F, -2.3F, -1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.05F))
				.texOffs(0, 32).addBox(-1.1F, -3.3F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.05F)), PartPose.offsetAndRotation(-0.5F, -1.25F, 0.0F, -0.1309F, -0.5236F, 0.3491F));

		PartDefinition cube_r2 = LeftEarPivot.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(80, 0).addBox(-2.3F, -1.15F, -1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6F, -0.95F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.2F))
				.texOffs(24, 8).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition bone = Head.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(24, 22).addBox(-1.5F, -1.0F, -5.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(15, 32).addBox(-2.0F, -3.0F, -6.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Snout_r1 = bone.addOrReplaceChild("Snout_r1", CubeListBuilder.create().texOffs(60, 0).addBox(0.0F, -28.0F, -5.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 25.0F, -1.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition Snout_r2 = bone.addOrReplaceChild("Snout_r2", CubeListBuilder.create().texOffs(64, 0).addBox(0.0F, -28.0F, -5.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, 25.0F, -1.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition Snout_r3 = bone.addOrReplaceChild("Snout_r3", CubeListBuilder.create().texOffs(68, 0).addBox(-2.0F, -27.0F, -5.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 21.0F, -20.5F, -0.7854F, 0.0F, 0.0F));

		PartDefinition Snout_r4 = bone.addOrReplaceChild("Snout_r4", CubeListBuilder.create().texOffs(24, 2).addBox(-1.0F, -29.625F, -0.95F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 26.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition bone3 = Head.addOrReplaceChild("bone3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r3 = bone3.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(64, 21).addBox(-1.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(75, 5).addBox(-4.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -8.8F, -2.2F, -0.4363F, 0.0F, 0.0F));

		PartDefinition cube_r4 = bone3.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(60, 22).addBox(-1.0F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(71, 7).addBox(-4.0F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -8.2F, -1.9F, 0.3054F, 0.0F, 0.0F));

		PartDefinition Torso = partdefinition.addOrReplaceChild("Torso", CubeListBuilder.create().texOffs(28, 28).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 0.0F));

		PartDefinition Tail = Torso.addOrReplaceChild("Tail", CubeListBuilder.create(), PartPose.offset(0.0F, 10.5F, 0.0F));

		PartDefinition TailPrimary = Tail.addOrReplaceChild("TailPrimary", CubeListBuilder.create(), PartPose.offset(0.0F, -0.5F, 0.0F));

		PartDefinition TailBase_r1 = TailPrimary.addOrReplaceChild("TailBase_r1", CubeListBuilder.create().texOffs(48, 50).addBox(-2.5F, -8.25F, 11.6F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(0.0F, 15.75F, 0.0F, 1.0036F, 0.0F, 0.0F));

		PartDefinition TailSecondary = TailPrimary.addOrReplaceChild("TailSecondary", CubeListBuilder.create(), PartPose.offset(0.0F, 0.4F, 0.6F));

		PartDefinition TailBase_r2 = TailSecondary.addOrReplaceChild("TailBase_r2", CubeListBuilder.create().texOffs(70, 11).addBox(-2.0F, -1.75F, 12.6F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(0.0F, 15.35F, -0.8F, 1.1781F, 0.0F, 0.0F));

		PartDefinition TailTertiary = TailSecondary.addOrReplaceChild("TailTertiary", CubeListBuilder.create(), PartPose.offset(0.0F, 3.0F, 6.5F));

		PartDefinition TailBase_r3 = TailTertiary.addOrReplaceChild("TailBase_r3", CubeListBuilder.create().texOffs(27, 55).addBox(-2.0F, 9.0F, 10.3F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 12.15F, -7.5F, 1.4835F, 0.0F, 0.0F));

		PartDefinition TailQuaternary = TailTertiary.addOrReplaceChild("TailQuaternary", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 5.5F));

		PartDefinition TailBase_r4 = TailQuaternary.addOrReplaceChild("TailBase_r4", CubeListBuilder.create().texOffs(80, 73).addBox(-1.5F, 17.3F, 7.1F, 3.0F, 6.0F, 3.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(0.0F, 10.35F, -8.4F, 1.7017F, 0.0F, 0.0F));

		PartDefinition TailBase_r5 = TailQuaternary.addOrReplaceChild("TailBase_r5", CubeListBuilder.create().texOffs(0, 56).addBox(-1.5F, 17.3F, 7.1F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.15F)), PartPose.offsetAndRotation(0.0F, 11.05F, -13.9F, 1.7017F, 0.0F, 0.0F));

		PartDefinition Hair2 = TailQuaternary.addOrReplaceChild("Hair2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition TailBase_r6 = Hair2.addOrReplaceChild("TailBase_r6", CubeListBuilder.create().texOffs(73, 20).addBox(1.5F, 20.3F, 7.1F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.1F, 0.2F, -8.5F, 0.4363F, 0.0F, -1.5708F));

		PartDefinition TailBase_r7 = Hair2.addOrReplaceChild("TailBase_r7", CubeListBuilder.create().texOffs(73, 20).addBox(1.5F, 20.3F, 7.1F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(16.9F, -2.8F, -8.5F, 0.4363F, 0.0F, 1.5708F));

		PartDefinition TailBase_r8 = Hair2.addOrReplaceChild("TailBase_r8", CubeListBuilder.create().texOffs(73, 20).addBox(1.5F, 20.3F, 7.1F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.6F, -17.8F, -8.5F, 0.4363F, 0.0F, 0.0F));

		PartDefinition bone2 = Torso.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(0, 93).addBox(-4.0F, 11.9F, 18.5F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.9F, -21.0F));

		PartDefinition Base_r1 = bone2.addOrReplaceChild("Base_r1", CubeListBuilder.create().texOffs(0, 85).addBox(-4.0F, -16.0F, -2.0F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 22.85F, 9.25F, -0.7854F, 0.0F, 0.0F));

		PartDefinition Base_r2 = bone2.addOrReplaceChild("Base_r2", CubeListBuilder.create().texOffs(0, 85).addBox(-4.0F, -16.0F, -2.0F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 23.65F, 9.75F, -0.7854F, 0.0F, 0.0F));

		PartDefinition Base_r3 = bone2.addOrReplaceChild("Base_r3", CubeListBuilder.create().texOffs(0, 83).addBox(-4.0F, -15.0F, -2.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 22.0F, 9.1F, -0.7854F, 0.0F, 0.0F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(16, 40).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(16, 66).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F))
				.texOffs(90, 4).addBox(-2.9F, 9.1F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(90, 4).addBox(-1.6F, 9.1F, -1.8F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(88, 3).addBox(-2.0F, 9.1F, -0.3F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(90, 4).addBox(-0.2F, 9.1F, -1.6F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 1.5F, 0.0F));

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(0, 40).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(90, 1).addBox(1.8F, 9.1F, -1.6F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(90, 1).addBox(-0.8F, 9.1F, -1.6F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(90, 1).addBox(0.5F, 9.1F, -1.8F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(88, 0).addBox(0.0F, 9.1F, -0.3F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 66).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offset(5.0F, 1.5F, 0.0F));

		return LayerDefinition.create(meshdefinition, 96, 96);
    }

    public void setupHand(LatexIceFieldWolfDragonEntity entity) {
        animator.setupHand();
    }

    @Override
    public void setupAnim(@NotNull LatexIceFieldWolfDragonEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    }

	public ModelPart m_102851_(HumanoidArm p_102852_) {
		return p_102852_ == HumanoidArm.LEFT ? this.LeftArm : this.RightArm;
	}

    public ModelPart getLeg(HumanoidArm p_102852_) {
        return p_102852_ == HumanoidArm.LEFT ? this.LeftLeg : this.RightLeg;
    }

    public ModelPart getHead() {
        return this.Head;
    }

    public ModelPart getTorso() {
        return Torso;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        RightLeg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        LeftLeg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        Head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        Torso.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        RightArm.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        LeftArm.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public HumanoidAnimator<LatexIceFieldWolfDragonEntity, ModelIceFieldWolfDragon> getAnimator(LatexIceFieldWolfDragonEntity entity) {
        return animator;
    }
}