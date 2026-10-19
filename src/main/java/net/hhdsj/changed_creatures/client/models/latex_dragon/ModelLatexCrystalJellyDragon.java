package net.hhdsj.changed_creatures.client.models.latex_dragon;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.entity.simple.LatexCrystalJellyDragonEntity;
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
public class ModelLatexCrystalJellyDragon extends AdvancedHumanoidModel<LatexCrystalJellyDragonEntity>{//<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this models's constructor
	public static final ModelLayerLocation LAYER_LOCATION =
			ChangedCreature.ChangedCreatureModelResourceLocation("latex_deep_crystal_dragon");
	public final ModelPart RightLeg;
	public final ModelPart LeftLeg;
	public final ModelPart Head;
	public final ModelPart Torso;
	public final ModelPart RightArm;
	public final ModelPart LeftArm;
	public final ModelPart Tail;
	public final HumanoidAnimator<LatexCrystalJellyDragonEntity, ModelLatexCrystalJellyDragon> animator;

	public ModelLatexCrystalJellyDragon(ModelPart root) {
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

		PartDefinition RightThigh_r1 = RightLeg.addOrReplaceChild("RightThigh_r1", CubeListBuilder.create().texOffs(32, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition RightLowerLeg = RightLeg.addOrReplaceChild("RightLowerLeg", CubeListBuilder.create(), PartPose.offset(0.0F, 6.375F, -3.45F));

		PartDefinition RightCalf_r1 = RightLowerLeg.addOrReplaceChild("RightCalf_r1", CubeListBuilder.create().texOffs(16, 43).addBox(-1.99F, -0.125F, -2.9F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.125F, 1.95F, 0.8727F, 0.0F, 0.0F));

		PartDefinition RightFoot = RightLowerLeg.addOrReplaceChild("RightFoot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.8F, 7.175F));

		PartDefinition RightArch_r1 = RightFoot.addOrReplaceChild("RightArch_r1", CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, -8.45F, -0.725F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.005F)), PartPose.offsetAndRotation(0.0F, 7.075F, -4.975F, -0.3491F, 0.0F, 0.0F));

		PartDefinition RightPad = RightFoot.addOrReplaceChild("RightPad", CubeListBuilder.create().texOffs(48, 31).addBox(-2.0F, 0.0F, -2.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.325F, -4.425F));

		PartDefinition bone = RightPad.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(2.0F, 4.5F, -0.05F));

		PartDefinition cube_r1 = bone.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(4, 63).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-0.4F, -2.9F, -2.5F, 0.2752F, -0.2368F, -0.1128F));

		PartDefinition cube_r2 = bone.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(4, 63).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
				.texOffs(4, 63).addBox(-1.575F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-1.45F, -2.9F, -2.5F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r3 = bone.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(4, 63).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-3.6F, -2.9F, -2.5F, 0.2752F, 0.2368F, 0.1128F));

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

		PartDefinition bone2 = LeftPad.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offset(-2.0F, 4.5F, -0.05F));

		PartDefinition cube_r4 = bone2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(4, 63).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(3.6F, -2.9F, -2.5F, 0.2752F, -0.2368F, -0.1128F));

		PartDefinition cube_r5 = bone2.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(4, 63).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
				.texOffs(4, 63).addBox(-1.575F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(2.55F, -2.9F, -2.5F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r6 = bone2.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(4, 63).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.4F, -2.9F, -2.5F, 0.2752F, 0.2368F, 0.1128F));

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(38, 54).addBox(-2.0F, -3.0F, -6.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(56, 29).addBox(-1.5F, -1.0F, -5.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 0.0F));

		PartDefinition RightHorn = Head.addOrReplaceChild("RightHorn", CubeListBuilder.create(), PartPose.offsetAndRotation(0.5F, 17.0F, 0.0F, 0.0F, 0.0F, -0.0436F));

		PartDefinition Horn_r1 = RightHorn.addOrReplaceChild("Horn_r1", CubeListBuilder.create().texOffs(56, 19).mirror().addBox(-3.0F, -31.3F, 18.8F, 2.0F, 2.0F, 3.0F, new CubeDeformation(-0.3F)).mirror(false), PartPose.offsetAndRotation(0.0F, 8.0F, 0.0F, 0.48F, -0.1745F, 0.0F));

		PartDefinition Horn_r2 = RightHorn.addOrReplaceChild("Horn_r2", CubeListBuilder.create().texOffs(56, 9).addBox(-3.0F, -31.3F, 18.8F, 2.0F, 2.0F, 3.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(-0.3F, 7.0F, 1.8F, 0.48F, -0.1745F, 0.0F));

		PartDefinition Horn_r3 = RightHorn.addOrReplaceChild("Horn_r3", CubeListBuilder.create().texOffs(50, 54).addBox(-3.0F, -35.2F, 8.1F, 2.0F, 2.0F, 3.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(0.0F, 8.0F, 0.0F, 0.2182F, -0.1745F, 0.0F));

		PartDefinition Horn_r4 = RightHorn.addOrReplaceChild("Horn_r4", CubeListBuilder.create().texOffs(32, 9).addBox(-3.0F, -30.75F, -19.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 8.0F, 0.0F, -0.5672F, -0.1745F, 0.0F));

		PartDefinition LeftHorn = Head.addOrReplaceChild("LeftHorn", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.5F, 17.0F, 0.0F, 0.0F, 0.0F, 0.0436F));

		PartDefinition Horn_r5 = LeftHorn.addOrReplaceChild("Horn_r5", CubeListBuilder.create().texOffs(56, 24).addBox(1.0F, -31.3F, 18.8F, 2.0F, 2.0F, 3.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.3F, 7.0F, 1.8F, 0.48F, 0.1745F, 0.0F));

		PartDefinition Horn_r6 = LeftHorn.addOrReplaceChild("Horn_r6", CubeListBuilder.create().texOffs(56, 19).addBox(1.0F, -31.3F, 18.8F, 2.0F, 2.0F, 3.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(0.0F, 8.0F, 0.0F, 0.48F, 0.1745F, 0.0F));

		PartDefinition Horn_r7 = LeftHorn.addOrReplaceChild("Horn_r7", CubeListBuilder.create().texOffs(56, 14).addBox(1.0F, -35.2F, 8.1F, 2.0F, 2.0F, 3.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(0.0F, 8.0F, 0.0F, 0.2182F, 0.1745F, 0.0F));

		PartDefinition Horn_r8 = LeftHorn.addOrReplaceChild("Horn_r8", CubeListBuilder.create().texOffs(0, 57).addBox(1.0F, -30.75F, -19.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 8.0F, 0.0F, -0.5672F, 0.1745F, 0.0F));

		PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition bone7 = Head.addOrReplaceChild("bone7", CubeListBuilder.create(), PartPose.offset(0.0F, -2.4F, 1.9F));

		PartDefinition cube_r7 = bone7.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(62, 61).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 2.075F, 0.175F, 0.6545F, 0.0F, 0.0F));

		PartDefinition cube_r8 = bone7.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(62, 61).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 1.3F, 1.0F, 0.6545F, 0.0F, 0.0F));

		PartDefinition cube_r9 = bone7.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(62, 61).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.2F, 0.6545F, 0.0F, 0.0F));

		PartDefinition cube_r10 = bone7.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(62, 61).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -1.7F, 1.2F, 0.6545F, 0.0F, 0.0F));

		PartDefinition cube_r11 = bone7.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(62, 61).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -3.4F, 1.2F, 0.6545F, 0.0F, 0.0F));

		PartDefinition cube_r12 = bone7.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(62, 61).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -6.65F, 1.675F, -0.5236F, 0.0F, 0.0F));

		PartDefinition cube_r13 = bone7.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(62, 61).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -5.0F, 1.2F, 0.6545F, 0.0F, 0.0F));

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

		PartDefinition Torso = partdefinition.addOrReplaceChild("Torso", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 0.0F));

		PartDefinition bone4 = Torso.addOrReplaceChild("bone4", CubeListBuilder.create(), PartPose.offset(0.0F, 5.0F, 0.0F));

		PartDefinition cube_r14 = bone4.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(62, 61).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 1.7F, 0.9F, 0.6545F, 0.0F, 0.0F));

		PartDefinition cube_r15 = bone4.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(62, 61).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.9F, 0.6545F, 0.0F, 0.0F));

		PartDefinition cube_r16 = bone4.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(62, 61).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -1.7F, 0.9F, 0.6545F, 0.0F, 0.0F));

		PartDefinition cube_r17 = bone4.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(62, 61).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -3.4F, 0.9F, 0.6545F, 0.0F, 0.0F));

		PartDefinition cube_r18 = bone4.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(62, 61).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -5.0F, 0.9F, 0.6545F, 0.0F, 0.0F));

		PartDefinition Tail = Torso.addOrReplaceChild("Tail", CubeListBuilder.create(), PartPose.offset(0.0F, 10.5F, 0.0F));

		PartDefinition TailPrimary = Tail.addOrReplaceChild("TailPrimary", CubeListBuilder.create(), PartPose.offset(0.0F, -0.5F, 0.0F));

		PartDefinition TailBase_r1 = TailPrimary.addOrReplaceChild("TailBase_r1", CubeListBuilder.create().texOffs(32, 0).addBox(-2.5F, -8.25F, 11.6F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.18F)), PartPose.offsetAndRotation(0.0F, 16.0F, 0.0F, 1.0036F, 0.0F, 0.0F));

		PartDefinition bone5 = TailPrimary.addOrReplaceChild("bone5", CubeListBuilder.create(), PartPose.offset(0.0F, 16.0F, 0.0F));

		PartDefinition cube_r19 = bone5.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(62, 58).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -16.9F, 4.0F, -1.3526F, 0.0F, 0.0F));

		PartDefinition cube_r20 = bone5.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(62, 58).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -17.7F, 3.0F, -1.3526F, 0.0F, 0.0F));

		PartDefinition TailSecondary = TailPrimary.addOrReplaceChild("TailSecondary", CubeListBuilder.create(), PartPose.offset(0.0F, 1.5F, 3.5F));

		PartDefinition TailBase_r2 = TailSecondary.addOrReplaceChild("TailBase_r2", CubeListBuilder.create().texOffs(16, 32).addBox(-2.0F, -1.75F, 12.6F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(0.0F, 14.5F, -3.5F, 1.1781F, 0.0F, 0.0F));

		PartDefinition TailTertiary = TailSecondary.addOrReplaceChild("TailTertiary", CubeListBuilder.create(), PartPose.offset(0.0F, 3.0F, 9.5F));

		PartDefinition TailBase_r3 = TailTertiary.addOrReplaceChild("TailBase_r3", CubeListBuilder.create().texOffs(40, 20).addBox(-2.0F, 9.0F, 10.3F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.5F, -13.0F, 1.4835F, 0.0F, 0.0F));

		PartDefinition TailQuaternary = TailTertiary.addOrReplaceChild("TailQuaternary", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 5.5F));

		PartDefinition TailBase_r4 = TailQuaternary.addOrReplaceChild("TailBase_r4", CubeListBuilder.create().texOffs(52, 0).addBox(-1.5F, 17.3F, 7.1F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.15F)), PartPose.offsetAndRotation(0.0F, 10.5F, -18.5F, 1.7017F, 0.0F, 0.0F));

		PartDefinition TailEnd = TailQuaternary.addOrReplaceChild("TailEnd", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 6.7F));

		PartDefinition cube_r21 = TailEnd.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(38, 58).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.2F, -2.4F, 1.7017F, 0.0F, 0.0F));

		PartDefinition bone3 = TailEnd.addOrReplaceChild("bone3", CubeListBuilder.create(), PartPose.offset(0.0F, -0.5F, 2.9F));

		PartDefinition cube_r22 = bone3.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(22, 53).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(-0.3F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, -3.0F, 1.7728F, -0.8625F, -0.1543F));

		PartDefinition cube_r23 = bone3.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(30, 53).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(-0.5F)).mirror(false), PartPose.offsetAndRotation(0.0F, -0.2F, -3.0F, 2.2195F, -1.353F, -0.6372F));

		PartDefinition cube_r24 = bone3.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(14, 53).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(-0.3F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, -3.0F, 1.708F, -0.3027F, -0.0411F));

		PartDefinition cube_r25 = bone3.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(14, 53).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(0.0F, 0.0F, -3.0F, 1.708F, 0.3027F, 0.0411F));

		PartDefinition cube_r26 = bone3.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(30, 53).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, -0.2F, -3.0F, 2.2195F, 1.353F, 0.6372F));

		PartDefinition cube_r27 = bone3.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(22, 53).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(0.0F, 0.0F, -3.0F, 1.7728F, 0.8625F, 0.1543F));

		PartDefinition bone6 = TailSecondary.addOrReplaceChild("bone6", CubeListBuilder.create(), PartPose.offset(0.0F, 16.8F, -0.3F));

		PartDefinition cube_r28 = bone6.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(0, 63).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -16.1F, 8.2F, -1.3526F, 0.0F, 0.0F));

		PartDefinition cube_r29 = bone6.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(0, 63).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -16.6F, 6.9F, -1.3526F, 0.0F, 0.0F));

		PartDefinition cube_r30 = bone6.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(0, 63).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -17.1F, 5.6F, -1.3526F, 0.0F, 0.0F));

		PartDefinition cube_r31 = bone6.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(0, 63).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -18.1F, 3.3F, -1.3526F, 0.0F, 0.0F));

		PartDefinition cube_r32 = bone6.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(0, 63).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -17.7F, 4.4F, -1.3526F, 0.0F, 0.0F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(0, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 1.5F, 0.0F));

		PartDefinition bone_r5 = RightArm.addOrReplaceChild("bone_r5", CubeListBuilder.create().texOffs(8, 57).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(-2.2F, 4.7F, 1.4F, 2.6766F, -0.1237F, -0.2412F));

		PartDefinition bone_r6 = RightArm.addOrReplaceChild("bone_r6", CubeListBuilder.create().texOffs(8, 57).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-2.2F, 4.7F, 1.4F, 2.72F, -0.2366F, -0.4815F));

		PartDefinition bone10 = RightArm.addOrReplaceChild("bone10", CubeListBuilder.create(), PartPose.offset(4.5F, 25.3F, -0.55F));

		PartDefinition cube_r33 = bone10.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(4, 63).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-3.9F, -15.2F, -1.1F, 0.4351F, -0.2163F, -0.1487F));

		PartDefinition cube_r34 = bone10.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(4, 63).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
				.texOffs(4, 63).addBox(-1.575F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-4.95F, -15.2F, -1.1F, 0.4189F, 0.0F, 0.0F));

		PartDefinition cube_r35 = bone10.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(4, 63).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-7.1F, -15.2F, -1.1F, 0.4351F, 0.2163F, 0.1487F));

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(24, 16).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 1.5F, 0.0F));

		PartDefinition bone_r7 = LeftArm.addOrReplaceChild("bone_r7", CubeListBuilder.create().texOffs(8, 57).mirror().addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.1F)).mirror(false), PartPose.offsetAndRotation(2.2F, 4.7F, 1.4F, 2.72F, 0.2366F, 0.4815F));

		PartDefinition bone_r8 = LeftArm.addOrReplaceChild("bone_r8", CubeListBuilder.create().texOffs(8, 62).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(2.2F, 4.7F, 1.4F, 2.6766F, 0.1237F, 0.2412F));

		PartDefinition bone11 = LeftArm.addOrReplaceChild("bone11", CubeListBuilder.create(), PartPose.offset(6.5F, 25.3F, -0.55F));

		PartDefinition cube_r36 = bone11.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(4, 63).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-3.9F, -15.2F, -1.1F, 0.4351F, -0.2163F, -0.1487F));

		PartDefinition cube_r37 = bone11.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(4, 63).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
				.texOffs(4, 63).addBox(-1.575F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-4.95F, -15.2F, -1.1F, 0.4189F, 0.0F, 0.0F));

		PartDefinition cube_r38 = bone11.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(4, 63).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-7.1F, -15.2F, -1.1F, 0.4351F, 0.2163F, 0.1487F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

    public void setupHand(LatexCrystalJellyDragonEntity entity) {
        animator.setupHand();
    }


    public ModelPart getArm(HumanoidArm p_102852_) {
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
    public HumanoidAnimator<LatexCrystalJellyDragonEntity, ModelLatexCrystalJellyDragon> getAnimator(LatexCrystalJellyDragonEntity entity) {
        return this.animator;
    }

    public void setupAnim(@NotNull LatexCrystalJellyDragonEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
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
