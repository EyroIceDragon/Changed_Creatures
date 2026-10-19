package net.hhdsj.changed_creatures.client.models.latex_dragon;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.entity.simple.LatexEmberscalesMagmaDragonMaleEntity;
import net.ltxprogrammer.changed.client.renderer.animate.AnimatorPresets;
import net.ltxprogrammer.changed.client.renderer.animate.HumanoidAnimator;
import net.ltxprogrammer.changed.client.renderer.model.AdvancedHumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.HumanoidArm;
import org.jetbrains.annotations.NotNull;

import java.util.List;

//Changed-mc mod import 我的世界转变MOD导入
//Goodblock mod import

// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class ModelLatexEmberscalesMagmaDragonMale extends AdvancedHumanoidModel<LatexEmberscalesMagmaDragonMaleEntity>{
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this models's constructor
	public static final ModelLayerLocation LAYER_LOCATION =
			ChangedCreature.ChangedCreatureModelResourceLocation("latex_emberscales_magma_dragon_male");
	public final ModelPart RightLeg;
	public final ModelPart LeftLeg;
	public final ModelPart Head;
	public final ModelPart Torso;
	public final ModelPart RightArm;
	public final ModelPart LeftArm;
	public final ModelPart Tail;
	public final ModelPart RightWing;
	public final ModelPart LeftWing;
	public final HumanoidAnimator<LatexEmberscalesMagmaDragonMaleEntity, ModelLatexEmberscalesMagmaDragonMale> animator;

	public ModelLatexEmberscalesMagmaDragonMale(ModelPart root) {
		super(root);
		this.RightLeg = root.getChild("RightLeg");
		this.LeftLeg = root.getChild("LeftLeg");
		this.Head = root.getChild("Head");
		this.Torso = root.getChild("Torso");
		this.Tail = Torso.getChild("Tail");
		this.RightArm = root.getChild("RightArm");
		this.LeftArm = root.getChild("LeftArm");
		this.RightWing = Torso.getChild("RightWing");
		this.LeftWing = Torso.getChild("LeftWing");

		var tailPrimary = Tail.getChild("TailPrimary");
		var tailSecondary = tailPrimary.getChild("TailSecondary");
		var tailTertiary = tailSecondary.getChild("TailTertiary");

		var leftLowerLeg = LeftLeg.getChild("LeftLowerLeg");
		var leftFoot = leftLowerLeg.getChild("LeftFoot");
		var rightLowerLeg = RightLeg.getChild("RightLowerLeg");
		var rightFoot = rightLowerLeg.getChild("RightFoot");

		var leftWingRoot = LeftWing.getChild("leftWingRoot");
		var rightWingRoot = RightWing.getChild("rightWingRoot");
		var leftFirstise = leftWingRoot.getChild("leftFirstise");
		var rightFirsties = rightWingRoot.getChild("rightFirsties");

		animator = HumanoidAnimator.of(this).hipOffset(-1.5f)
				.addPreset(AnimatorPresets.wingedDragonLike(
						Head, Torso, LeftArm, RightArm,
						Tail, List.of(tailPrimary, tailSecondary, tailTertiary),
						LeftLeg, leftLowerLeg, leftFoot, leftFoot.getChild("LeftPad"),
						RightLeg, rightLowerLeg, rightFoot, rightFoot.getChild("RightPad"),
						leftWingRoot, leftFirstise.getChild("leftSecondaries"),
						leftFirstise.getChild("leftSecondaries").getChild("leftTertiaries"),
						rightWingRoot, rightFirsties.getChild("rightSecondaries"),
						rightFirsties.getChild("rightSecondaries").getChild("rightTertiaries")
				));
	}
	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create(), PartPose.offset(-2.5F, 10.5F, 0.0F));

		PartDefinition RightThigh_r1 = RightLeg.addOrReplaceChild("RightThigh_r1", CubeListBuilder.create().texOffs(32, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition RightLowerLeg = RightLeg.addOrReplaceChild("RightLowerLeg", CubeListBuilder.create(), PartPose.offset(0.0F, 6.375F, -3.45F));

		PartDefinition RightCalf_r1 = RightLowerLeg.addOrReplaceChild("RightCalf_r1", CubeListBuilder.create().texOffs(16, 43).addBox(-1.99F, -0.125F, -2.9F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.125F, 1.95F, 0.8727F, 0.0F, 0.0F));

		PartDefinition RightFoot = RightLowerLeg.addOrReplaceChild("RightFoot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.8F, 7.175F));

		PartDefinition RightArch_r1 = RightFoot.addOrReplaceChild("RightArch_r1", CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, -8.45F, -0.725F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.005F)), PartPose.offsetAndRotation(0.0F, 7.075F, -4.975F, -0.3491F, 0.0F, 0.0F));

		PartDefinition RightPad = RightFoot.addOrReplaceChild("RightPad", CubeListBuilder.create().texOffs(48, 31).addBox(-2.0F, 0.0F, -2.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.325F, -4.425F));

		PartDefinition bone9 = RightLeg.addOrReplaceChild("bone9", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.6F, 10.8F, -1.8F, 0.0F, 0.0F, 0.0175F));

		PartDefinition bone_r1 = bone9.addOrReplaceChild("bone_r1", CubeListBuilder.create().texOffs(8, 57).mirror().addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.2F)).mirror(false), PartPose.offsetAndRotation(9.7F, -4.3F, 1.4F, 2.6766F, 0.1237F, 0.2412F));

		PartDefinition bone_r2 = bone9.addOrReplaceChild("bone_r2", CubeListBuilder.create().texOffs(8, 57).mirror().addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.1F)).mirror(false), PartPose.offsetAndRotation(9.7F, -4.3F, 1.4F, 2.72F, 0.2366F, 0.4815F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create(), PartPose.offset(2.5F, 10.5F, 0.0F));

		PartDefinition LeftThigh_r1 = LeftLeg.addOrReplaceChild("LeftThigh_r1", CubeListBuilder.create().texOffs(40, 9).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition bone8 = LeftLeg.addOrReplaceChild("bone8", CubeListBuilder.create(), PartPose.offsetAndRotation(3.6F, 10.8F, -1.8F, 0.0F, 0.0F, -0.0175F));

		PartDefinition bone_r3 = bone8.addOrReplaceChild("bone_r3", CubeListBuilder.create().texOffs(8, 57).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(-9.7F, -4.3F, 1.4F, 2.6766F, -0.1237F, -0.2412F));

		PartDefinition bone_r4 = bone8.addOrReplaceChild("bone_r4", CubeListBuilder.create().texOffs(8, 57).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-9.7F, -4.3F, 1.4F, 2.72F, -0.2366F, -0.4815F));

		PartDefinition LeftLowerLeg = LeftLeg.addOrReplaceChild("LeftLowerLeg", CubeListBuilder.create(), PartPose.offset(0.0F, 6.375F, -3.45F));

		PartDefinition LeftCalf_r1 = LeftLowerLeg.addOrReplaceChild("LeftCalf_r1", CubeListBuilder.create().texOffs(32, 43).addBox(-2.01F, -0.125F, -2.9F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.125F, 1.95F, 0.8727F, 0.0F, 0.0F));

		PartDefinition LeftFoot = LeftLowerLeg.addOrReplaceChild("LeftFoot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.8F, 7.175F));

		PartDefinition LeftArch_r1 = LeftFoot.addOrReplaceChild("LeftArch_r1", CubeListBuilder.create().texOffs(48, 45).addBox(-2.0F, -8.45F, -0.725F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.005F)), PartPose.offsetAndRotation(0.0F, 7.075F, -4.975F, -0.3491F, 0.0F, 0.0F));

		PartDefinition LeftPad = LeftFoot.addOrReplaceChild("LeftPad", CubeListBuilder.create().texOffs(48, 38).addBox(-2.0F, 0.0F, -2.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.325F, -4.425F));

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(38, 54).addBox(-2.0F, -3.0F, -6.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(56, 29).addBox(-1.5F, -1.0F, -5.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 0.0F));

		PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition bone7 = Head.addOrReplaceChild("bone7", CubeListBuilder.create(), PartPose.offset(0.0F, -2.4F, 1.9F));

		PartDefinition cube_r1 = bone7.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(62, 61).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 2.075F, 0.175F, 0.6545F, 0.0F, 0.0F));

		PartDefinition cube_r2 = bone7.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(62, 61).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 1.3F, 1.0F, 0.6545F, 0.0F, 0.0F));

		PartDefinition cube_r3 = bone7.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(62, 61).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.2F, 0.6545F, 0.0F, 0.0F));

		PartDefinition cube_r4 = bone7.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(62, 61).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -1.7F, 1.2F, 0.6545F, 0.0F, 0.0F));

		PartDefinition cube_r5 = bone7.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(62, 61).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -3.4F, 1.2F, 0.6545F, 0.0F, 0.0F));

		PartDefinition cube_r6 = bone7.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(62, 61).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -6.65F, 1.675F, -0.5236F, 0.0F, 0.0F));

		PartDefinition cube_r7 = bone7.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(62, 61).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -5.0F, 1.2F, 0.6545F, 0.0F, 0.0F));

		PartDefinition LeftCrystals = Head.addOrReplaceChild("LeftCrystals", CubeListBuilder.create(), PartPose.offset(3.0F, -1.9F, -0.5F));

		PartDefinition Crystal_r1 = LeftCrystals.addOrReplaceChild("Crystal_r1", CubeListBuilder.create().texOffs(30, 62).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.15F)), PartPose.offsetAndRotation(0.1F, 0.0F, -2.2F, 0.3491F, 0.6109F, 0.0F));

		PartDefinition Crystal_r2 = LeftCrystals.addOrReplaceChild("Crystal_r2", CubeListBuilder.create().texOffs(62, 45).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.15F)), PartPose.offsetAndRotation(-0.1F, 0.7F, -2.4F, -0.2618F, 0.5236F, 0.0F));

		PartDefinition Crystal_r3 = LeftCrystals.addOrReplaceChild("Crystal_r3", CubeListBuilder.create().texOffs(62, 49).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.15F)), PartPose.offsetAndRotation(0.1F, 0.3F, -2.1F, 0.0873F, 0.6981F, 0.0F));

		PartDefinition Crystal_r4 = LeftCrystals.addOrReplaceChild("Crystal_r4", CubeListBuilder.create().texOffs(60, 54).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.1F, 0.3F, -0.6F, 0.0873F, 0.6981F, 0.0F));

		PartDefinition Crystal_r5 = LeftCrystals.addOrReplaceChild("Crystal_r5", CubeListBuilder.create().texOffs(54, 59).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(-0.1F, 0.9F, -1.1F, -0.2618F, 0.5236F, 0.0F));

		PartDefinition Crystal_r6 = LeftCrystals.addOrReplaceChild("Crystal_r6", CubeListBuilder.create().texOffs(46, 59).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.1F, -0.5F, -0.9F, 0.3491F, 0.6109F, 0.0F));

		PartDefinition RightCrystals = Head.addOrReplaceChild("RightCrystals", CubeListBuilder.create(), PartPose.offset(-3.0F, -1.9F, -0.5F));

		PartDefinition Crystal_r7 = RightCrystals.addOrReplaceChild("Crystal_r7", CubeListBuilder.create().texOffs(62, 49).mirror().addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.2F)).mirror(false), PartPose.offsetAndRotation(-0.1F, 0.3F, -0.6F, 0.0873F, -0.6981F, 0.0F));

		PartDefinition Crystal_r8 = RightCrystals.addOrReplaceChild("Crystal_r8", CubeListBuilder.create().texOffs(62, 45).mirror().addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.2F)).mirror(false), PartPose.offsetAndRotation(0.1F, 0.9F, -1.1F, -0.2618F, -0.5236F, 0.0F));

		PartDefinition Crystal_r9 = RightCrystals.addOrReplaceChild("Crystal_r9", CubeListBuilder.create().texOffs(30, 62).mirror().addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.2F)).mirror(false), PartPose.offsetAndRotation(-0.1F, -0.5F, -0.9F, 0.3491F, -0.6109F, 0.0F));

		PartDefinition Crystal_r10 = RightCrystals.addOrReplaceChild("Crystal_r10", CubeListBuilder.create().texOffs(62, 49).mirror().addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.15F)).mirror(false), PartPose.offsetAndRotation(-0.1F, 0.3F, -2.1F, 0.0873F, -0.6981F, 0.0F));

		PartDefinition Crystal_r11 = RightCrystals.addOrReplaceChild("Crystal_r11", CubeListBuilder.create().texOffs(62, 45).mirror().addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.15F)).mirror(false), PartPose.offsetAndRotation(0.1F, 0.7F, -2.4F, -0.2618F, -0.5236F, 0.0F));

		PartDefinition Crystal_r12 = RightCrystals.addOrReplaceChild("Crystal_r12", CubeListBuilder.create().texOffs(30, 62).mirror().addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.15F)).mirror(false), PartPose.offsetAndRotation(-0.1F, 0.0F, -2.2F, 0.3491F, -0.6109F, 0.0F));

		PartDefinition bone = Head.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Right = bone.addOrReplaceChild("Right", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.0F, -7.0F, -1.5F, 0.0F, 0.0F, -0.3054F));

		PartDefinition RightHorn = Right.addOrReplaceChild("RightHorn", CubeListBuilder.create(), PartPose.offsetAndRotation(4.0F, 24.0F, 0.0F, 0.0F, 0.0F, -0.0436F));

		PartDefinition Horn_r1 = RightHorn.addOrReplaceChild("Horn_r1", CubeListBuilder.create().texOffs(56, 19).mirror().addBox(-3.0F, -31.3F, 18.8F, 2.0F, 2.0F, 3.0F, new CubeDeformation(-0.3F)).mirror(false), PartPose.offsetAndRotation(-1.2068F, 8.135F, 0.0F, 0.48F, -0.1745F, 0.0F));

		PartDefinition Horn_r2 = RightHorn.addOrReplaceChild("Horn_r2", CubeListBuilder.create().texOffs(56, 9).addBox(-3.0F, -31.3F, 18.8F, 2.0F, 2.0F, 3.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(-1.5068F, 7.135F, 1.8F, 0.48F, -0.1745F, 0.0F));

		PartDefinition Horn_r3 = RightHorn.addOrReplaceChild("Horn_r3", CubeListBuilder.create().texOffs(50, 54).addBox(-3.0F, -35.2F, 8.1F, 2.0F, 2.0F, 3.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(-1.2068F, 8.135F, 0.0F, 0.2182F, -0.1745F, 0.0F));

		PartDefinition Horn_r4 = RightHorn.addOrReplaceChild("Horn_r4", CubeListBuilder.create().texOffs(32, 9).addBox(-3.0F, -30.75F, -19.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-1.2068F, 8.135F, 0.0F, -0.5672F, -0.1745F, 0.0F));

		PartDefinition Left = bone.addOrReplaceChild("Left", CubeListBuilder.create(), PartPose.offsetAndRotation(2.9F, -7.0F, -1.5F, 0.0F, 0.0F, 0.3054F));

		PartDefinition LeftHorn = Left.addOrReplaceChild("LeftHorn", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0436F));

		PartDefinition Horn_r5 = LeftHorn.addOrReplaceChild("Horn_r5", CubeListBuilder.create().texOffs(56, 24).addBox(1.0F, -31.3F, 18.8F, 2.0F, 2.0F, 3.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.3F, 7.0F, 1.8F, 0.48F, 0.1745F, 0.0F));

		PartDefinition Horn_r6 = LeftHorn.addOrReplaceChild("Horn_r6", CubeListBuilder.create().texOffs(56, 19).addBox(1.0F, -31.3F, 18.8F, 2.0F, 2.0F, 3.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(0.0F, 8.0F, 0.0F, 0.48F, 0.1745F, 0.0F));

		PartDefinition Horn_r7 = LeftHorn.addOrReplaceChild("Horn_r7", CubeListBuilder.create().texOffs(56, 14).addBox(1.0F, -35.2F, 8.1F, 2.0F, 2.0F, 3.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(0.0F, 8.0F, 0.0F, 0.2182F, 0.1745F, 0.0F));

		PartDefinition Horn_r8 = LeftHorn.addOrReplaceChild("Horn_r8", CubeListBuilder.create().texOffs(0, 57).addBox(1.0F, -30.75F, -19.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 8.0F, 0.0F, -0.5672F, 0.1745F, 0.0F));

		PartDefinition Ear = bone.addOrReplaceChild("Ear", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r8 = Ear.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(108, 50).addBox(-1.0F, -6.0F, -1.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.35F, -6.2F, 0.35F, -0.9163F, 0.3491F, 0.0F));

		PartDefinition cube_r9 = Ear.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(118, 50).addBox(-1.0F, -6.0F, -1.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.35F, -6.2F, 0.35F, -0.9163F, -0.3491F, 0.0F));

		PartDefinition cube_r10 = Ear.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(122, 59).addBox(0.0F, -5.0F, -1.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.35F, -5.85F, 0.35F, -0.9163F, -0.3491F, 0.0F));

		PartDefinition cube_r11 = Ear.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(116, 59).addBox(-1.0F, -5.0F, -1.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.35F, -5.85F, 0.35F, -0.9163F, 0.3491F, 0.0F));

		PartDefinition Torso = partdefinition.addOrReplaceChild("Torso", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 0.0F));

		PartDefinition bone4 = Torso.addOrReplaceChild("bone4", CubeListBuilder.create(), PartPose.offset(0.0F, 5.0F, 0.0F));

		PartDefinition cube_r12 = bone4.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(62, 61).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 1.7F, 0.9F, 0.6545F, 0.0F, 0.0F));

		PartDefinition cube_r13 = bone4.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(62, 61).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.9F, 0.6545F, 0.0F, 0.0F));

		PartDefinition cube_r14 = bone4.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(62, 61).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -1.7F, 0.9F, 0.6545F, 0.0F, 0.0F));

		PartDefinition cube_r15 = bone4.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(62, 61).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -3.4F, 0.9F, 0.6545F, 0.0F, 0.0F));

		PartDefinition cube_r16 = bone4.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(62, 61).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -5.0F, 0.9F, 0.6545F, 0.0F, 0.0F));

		PartDefinition Tail = Torso.addOrReplaceChild("Tail", CubeListBuilder.create(), PartPose.offset(0.0F, 10.5F, 0.0F));

		PartDefinition TailPrimary = Tail.addOrReplaceChild("TailPrimary", CubeListBuilder.create(), PartPose.offset(0.0F, -0.5F, 0.0F));

		PartDefinition TailBase_r1 = TailPrimary.addOrReplaceChild("TailBase_r1", CubeListBuilder.create().texOffs(32, 0).addBox(-2.5F, -8.25F, 11.6F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.18F)), PartPose.offsetAndRotation(0.0F, 16.0F, 0.0F, 1.0036F, 0.0F, 0.0F));

		PartDefinition bone5 = TailPrimary.addOrReplaceChild("bone5", CubeListBuilder.create(), PartPose.offset(0.0F, 16.0F, 0.0F));

		PartDefinition cube_r17 = bone5.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(62, 58).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -16.9F, 4.0F, -1.3526F, 0.0F, 0.0F));

		PartDefinition cube_r18 = bone5.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(62, 58).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -17.7F, 3.0F, -1.3526F, 0.0F, 0.0F));

		PartDefinition TailSecondary = TailPrimary.addOrReplaceChild("TailSecondary", CubeListBuilder.create(), PartPose.offset(0.0F, 1.5F, 3.5F));

		PartDefinition TailBase_r2 = TailSecondary.addOrReplaceChild("TailBase_r2", CubeListBuilder.create().texOffs(16, 32).addBox(-2.0F, -1.75F, 12.6F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(0.0F, 14.5F, -3.5F, 1.1781F, 0.0F, 0.0F));

		PartDefinition TailTertiary = TailSecondary.addOrReplaceChild("TailTertiary", CubeListBuilder.create(), PartPose.offset(0.0F, 3.0F, 9.5F));

		PartDefinition TailBase_r3 = TailTertiary.addOrReplaceChild("TailBase_r3", CubeListBuilder.create().texOffs(40, 20).addBox(-2.0F, 9.0F, 10.3F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.5F, -13.0F, 1.4835F, 0.0F, 0.0F));

		PartDefinition TailQuaternary = TailTertiary.addOrReplaceChild("TailQuaternary", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 5.5F));

		PartDefinition TailBase_r4 = TailQuaternary.addOrReplaceChild("TailBase_r4", CubeListBuilder.create().texOffs(52, 0).addBox(-1.5F, 17.3F, 7.1F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.15F)), PartPose.offsetAndRotation(0.0F, 10.5F, -18.5F, 1.7017F, 0.0F, 0.0F));

		PartDefinition TailEnd = TailQuaternary.addOrReplaceChild("TailEnd", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 6.7F));

		PartDefinition cube_r19 = TailEnd.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(38, 58).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, -0.2F, -2.4F, 1.7017F, 0.0F, 0.0F));

		PartDefinition bone6 = TailSecondary.addOrReplaceChild("bone6", CubeListBuilder.create(), PartPose.offset(0.0F, 16.8F, -0.3F));

		PartDefinition cube_r20 = bone6.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(0, 63).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -16.1F, 8.2F, -1.3526F, 0.0F, 0.0F));

		PartDefinition cube_r21 = bone6.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(0, 63).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -16.6F, 6.9F, -1.3526F, 0.0F, 0.0F));

		PartDefinition cube_r22 = bone6.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(0, 63).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -17.1F, 5.6F, -1.3526F, 0.0F, 0.0F));

		PartDefinition cube_r23 = bone6.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(0, 63).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -18.1F, 3.3F, -1.3526F, 0.0F, 0.0F));

		PartDefinition cube_r24 = bone6.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(0, 63).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -17.7F, 4.4F, -1.3526F, 0.0F, 0.0F));

		PartDefinition Plantoids = Torso.addOrReplaceChild("Plantoids", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, -2.0F));

		PartDefinition RightPlantoid_r1 = Plantoids.addOrReplaceChild("RightPlantoid_r1", CubeListBuilder.create().texOffs(16, 67).addBox(-4.25F, -1.9F, -0.6F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.03F))
				.texOffs(4, 67).addBox(0.25F, -1.9F, -0.6F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.03F)), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, -0.2793F, 0.0F, 0.0F));

		PartDefinition Center_r1 = Plantoids.addOrReplaceChild("Center_r1", CubeListBuilder.create().texOffs(0, 67).addBox(-0.5F, -1.5F, -0.1F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, -0.192F, 0.0F, 0.0F));

		PartDefinition LeftWing = Torso.addOrReplaceChild("LeftWing", CubeListBuilder.create(), PartPose.offsetAndRotation(1.3F, 3.3F, 2.0F, 0.0F, -0.48F, 0.0F));

		PartDefinition leftWingRoot = LeftWing.addOrReplaceChild("leftWingRoot", CubeListBuilder.create(), PartPose.offset(-0.3F, -0.3F, 0.0F));

		PartDefinition cube_r25 = leftWingRoot.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(75, 36).addBox(5.2053F, -17.5097F, 1.6435F, 7.0F, 2.0F, 1.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(1.0F, 17.525F, -2.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r26 = leftWingRoot.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(105, 16).addBox(12.8509F, -5.5632F, 2.0935F, 11.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1F, 18.525F, -2.0F, 0.0F, 0.0F, -1.2654F));

		PartDefinition leftFirstise = leftWingRoot.addOrReplaceChild("leftFirstise", CubeListBuilder.create(), PartPose.offset(6.0F, -2.0F, 0.0F));

		PartDefinition cube_r27 = leftFirstise.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(99, 39).addBox(-2.9367F, -1.5985F, -3.6045F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(2.4245F, -1.1265F, 3.248F, 0.0F, 0.0F, -0.3927F));

		PartDefinition cube_r28 = leftFirstise.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(115, 36).addBox(-0.5F, -3.0F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9844F, 6.4217F, 0.1435F, 0.0F, 0.0F, 0.2705F));

		PartDefinition cube_r29 = leftFirstise.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(105, 30).addBox(-4.0F, -1.0F, -0.5F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.085F, -2.7754F, 0.1435F, 0.0F, 0.0F, -0.7854F));

		PartDefinition leftSecondaries = leftFirstise.addOrReplaceChild("leftSecondaries", CubeListBuilder.create(), PartPose.offsetAndRotation(5.675F, -5.375F, -0.5F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r30 = leftSecondaries.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(119, 36).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5801F, -0.6933F, 0.6435F, 0.0F, 0.0F, -2.0246F));

		PartDefinition cube_r31 = leftSecondaries.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(103, 0).addBox(-6.0F, -4.0F, 0.0F, 12.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.6423F, 9.7596F, 0.5945F, 0.0F, 0.0F, -1.8326F));

		PartDefinition cube_r32 = leftSecondaries.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(107, 36).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 11.0F, 1.0F, new CubeDeformation(0.05F)), PartPose.offsetAndRotation(0.4249F, -0.4427F, 0.6435F, 0.0F, 0.0F, 0.1178F));

		PartDefinition cube_r33 = leftSecondaries.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(91, 39).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.9991F, 14.1361F, 0.6435F, 0.0F, 0.0F, 0.48F));

		PartDefinition cube_r34 = leftSecondaries.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(75, 0).addBox(-7.0F, -5.0F, 0.0F, 14.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2082F, 8.3427F, 0.5915F, 0.0F, 0.0F, -0.7505F));

		PartDefinition leftTertiaries = leftSecondaries.addOrReplaceChild("leftTertiaries", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.35F, -0.25F, 0.0F, 0.0F, 0.0F, -0.9599F));

		PartDefinition cube_r35 = leftTertiaries.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(111, 36).addBox(-5.0516F, -20.9916F, 1.6435F, 1.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.4F, 30.4F, -1.5F, 0.0F, 0.0F, 0.5236F));

		PartDefinition cube_r36 = leftTertiaries.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(75, 20).addBox(9.3354F, -13.9881F, 2.0835F, 15.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 28.0F, -1.5F, 0.0F, 0.0F, -0.8727F));

		PartDefinition cube_r37 = leftTertiaries.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(83, 39).addBox(6.8623F, -25.869F, 1.6435F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.005F)), PartPose.offsetAndRotation(-5.55F, 26.5F, -1.5F, 0.0F, 0.0F, -0.0436F));

		PartDefinition RightWing = Torso.addOrReplaceChild("RightWing", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.3F, 3.3F, 2.0F, 0.0F, 0.48F, 0.0F));

		PartDefinition rightWingRoot = RightWing.addOrReplaceChild("rightWingRoot", CubeListBuilder.create(), PartPose.offset(0.3F, -0.3F, 0.0F));

		PartDefinition cube_r38 = rightWingRoot.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(91, 36).addBox(-12.2053F, -17.5097F, 1.6435F, 7.0F, 2.0F, 1.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-1.0F, 17.525F, -2.0F, 0.0F, 0.0F, 0.3491F));

		PartDefinition cube_r39 = rightWingRoot.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(105, 23).addBox(-23.8509F, -5.5632F, 2.0935F, 11.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.1F, 18.525F, -2.0F, 0.0F, 0.0F, 1.2654F));

		PartDefinition rightFirsties = rightWingRoot.addOrReplaceChild("rightFirsties", CubeListBuilder.create(), PartPose.offset(-6.0F, -2.0F, 0.0F));

		PartDefinition cube_r40 = rightFirsties.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(103, 39).addBox(1.9367F, -1.5985F, -3.6045F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-2.4245F, -1.1265F, 3.248F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r41 = rightFirsties.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(115, 43).addBox(-0.5F, -3.0F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.9844F, 6.4217F, 0.1435F, 0.0F, 0.0F, -0.2705F));

		PartDefinition cube_r42 = rightFirsties.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(105, 33).addBox(-4.0F, -1.0F, -0.5F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.085F, -2.7754F, 0.1435F, 0.0F, 0.0F, 0.7854F));

		PartDefinition rightSecondaries = rightFirsties.addOrReplaceChild("rightSecondaries", CubeListBuilder.create(), PartPose.offsetAndRotation(-5.675F, -5.375F, -0.5F, 0.0F, 0.0F, 0.5236F));

		PartDefinition cube_r43 = rightSecondaries.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(119, 39).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5801F, -0.6933F, 0.6435F, 0.0F, 0.0F, 2.0246F));

		PartDefinition cube_r44 = rightSecondaries.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(103, 8).addBox(-6.0F, -4.0F, 0.0F, 12.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6423F, 9.7596F, 0.5945F, 0.0F, 0.0F, 1.8326F));

		PartDefinition cube_r45 = rightSecondaries.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(75, 39).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 11.0F, 1.0F, new CubeDeformation(0.05F)), PartPose.offsetAndRotation(-0.4249F, -0.4427F, 0.6435F, 0.0F, 0.0F, -0.1178F));

		PartDefinition cube_r46 = rightSecondaries.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(95, 39).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.9991F, 14.1361F, 0.6435F, 0.0F, 0.0F, -0.48F));

		PartDefinition cube_r47 = rightSecondaries.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(75, 10).addBox(-7.0F, -5.0F, 0.0F, 14.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.2082F, 8.3427F, 0.5915F, 0.0F, 0.0F, 0.7505F));

		PartDefinition rightTertiaries = rightSecondaries.addOrReplaceChild("rightTertiaries", CubeListBuilder.create(), PartPose.offsetAndRotation(0.35F, -0.25F, 0.0F, 0.0F, 0.0F, 0.9599F));

		PartDefinition cube_r48 = rightTertiaries.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(79, 39).addBox(4.0516F, -20.9916F, 1.6435F, 1.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.4F, 30.4F, -1.5F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r49 = rightTertiaries.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(75, 28).addBox(-24.3354F, -13.9881F, 2.0835F, 15.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 28.0F, -1.5F, 0.0F, 0.0F, 0.8727F));

		PartDefinition cube_r50 = rightTertiaries.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(87, 39).addBox(-7.8623F, -25.869F, 1.6435F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.005F)), PartPose.offsetAndRotation(5.55F, 26.5F, -1.5F, 0.0F, 0.0F, 0.0436F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(0, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 1.5F, 0.0F));

		PartDefinition bone_r5 = RightArm.addOrReplaceChild("bone_r5", CubeListBuilder.create().texOffs(8, 57).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(-2.2F, 4.7F, 1.4F, 2.6766F, -0.1237F, -0.2412F));

		PartDefinition bone_r6 = RightArm.addOrReplaceChild("bone_r6", CubeListBuilder.create().texOffs(8, 57).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-2.2F, 4.7F, 1.4F, 2.72F, -0.2366F, -0.4815F));

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(24, 16).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 1.5F, 0.0F));

		PartDefinition bone_r7 = LeftArm.addOrReplaceChild("bone_r7", CubeListBuilder.create().texOffs(8, 57).mirror().addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.1F)).mirror(false), PartPose.offsetAndRotation(2.2F, 4.7F, 1.4F, 2.72F, 0.2366F, 0.4815F));

		PartDefinition bone_r8 = LeftArm.addOrReplaceChild("bone_r8", CubeListBuilder.create().texOffs(8, 62).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(2.2F, 4.7F, 1.4F, 2.6766F, 0.1237F, 0.2412F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

    public void setupHand(LatexEmberscalesMagmaDragonMaleEntity entity) {
        animator.setupHand();
    }


    public ModelPart getArm(HumanoidArm p_102852_) {
        return p_102852_ == HumanoidArm.LEFT ? this.LeftArm : this.RightArm;
    }

    public ModelPart getLeg(HumanoidArm p_102852_) {
        return p_102852_ == HumanoidArm.LEFT ? this.LeftLeg : this.RightLeg;
    }

    public @NotNull ModelPart getHead() {
        return this.Head;
    }

    public ModelPart getTorso() {
        return Torso;
    }

	@Override
    public HumanoidAnimator<LatexEmberscalesMagmaDragonMaleEntity, ModelLatexEmberscalesMagmaDragonMale> getAnimator(LatexEmberscalesMagmaDragonMaleEntity entity) {
        return this.animator;
    }

    public void setupAnim(@NotNull LatexEmberscalesMagmaDragonMaleEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    }
    /*
	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.LeftLeg.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
		this.RightArm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
		this.Head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.Head.xRot = headPitch / (180F / (float) Math.PI);
		this.RightLeg.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
		this.LeftArm.xRot = Mth.cos(limbSwing * 0.6662F) * limbSwingAmount;
	}
*/

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		RightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Torso.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
