package net.hhdsj.changed_creatures.client.models.armors.fursuit;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.hhdsj.changed_creatures.ChangedCreature;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

public class ModelFursuitFox extends HumanoidFursuitModel {

	public static final ModelLayerLocation LAYER_LOCATION =
			ChangedCreature.ChangedCreatureModelResourceLocation("model_fursuit_protogen");

	// 原版标准字段（HumanoidModel 已有，这里保留引用方便阅读；实际可直接用 super 的）
	private final ModelPart head;
	private final ModelPart hat;
	private final ModelPart body;
	private final ModelPart rightArm;
	private final ModelPart leftArm;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;

	// 自定义骨骼
	private final ModelPart hair;
	private final ModelPart left_ear;
	private final ModelPart right_ear;
	private final ModelPart Tail;
	private final ModelPart TailPrimary;
	private final ModelPart TailSecondary;
	private final ModelPart TailTertiary;

	public ModelFursuitFox(ModelPart root) {
		super(root);

		this.head = root.getChild("head");
		this.hat = root.getChild("hat");
		this.body = root.getChild("body");
		this.rightArm = root.getChild("right_arm");
		this.leftArm = root.getChild("left_arm");
		this.rightLeg = root.getChild("right_leg");
		this.leftLeg = root.getChild("left_leg");

		this.hair = this.head.getChild("hair");
		this.left_ear = this.head.getChild("left_ear");
		this.right_ear = this.head.getChild("right_ear");

		this.Tail = this.body.getChild("Tail");
		this.TailPrimary = this.Tail.getChild("TailPrimary");
		this.TailSecondary = this.TailPrimary.getChild("TailSecondary");
		this.TailTertiary = this.TailSecondary.getChild("TailTertiary");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.7F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.02F))
				.texOffs(114, 0).addBox(-2.0F, -3.0F, -6.7F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(100, 0).addBox(-1.5F, -1.0F, -5.7F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(2, 1).addBox(-0.5F, -3.4F, -7.1F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.3F)), PartPose.offset(0.0F, 0.0F, 1.0F));

		PartDefinition hair = head.addOrReplaceChild("hair", CubeListBuilder.create().texOffs(0, 96).addBox(-4.0F, -4.0F, -4.5F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.1F))
				.texOffs(0, 16).addBox(-4.0F, -4.1F, -1.3F, 8.0F, 14.0F, 5.0F, new CubeDeformation(0.08F)), PartPose.offset(0.0F, -4.0F, -0.2F));

		PartDefinition left_ear = head.addOrReplaceChild("left_ear", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -0.7F));

		PartDefinition cube_r1 = left_ear.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(16, 70).addBox(-1.0F, -5.0F, -2.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4F, -6.5F, -2.1F, -0.5236F, 0.1745F, -0.2618F));

		PartDefinition cube_r2 = left_ear.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(26, 70).mirror().addBox(-1.0F, -5.0F, -2.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.6F, -5.5F, -1.5F, -0.9829F, -0.3045F, -0.5692F));

		PartDefinition right_ear = head.addOrReplaceChild("right_ear", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -0.7F));

		PartDefinition cube_r3 = right_ear.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(26, 70).addBox(-1.0F, -5.0F, -2.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6F, -5.5F, -1.5F, -0.9829F, 0.3045F, 0.5692F));

		PartDefinition cube_r4 = right_ear.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(16, 35).addBox(-1.0F, -5.0F, -2.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4F, -6.5F, -2.1F, -0.5236F, -0.1745F, 0.2618F));

		PartDefinition bone = head.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -0.7F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(26, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.05F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Tail = body.addOrReplaceChild("Tail", CubeListBuilder.create(), PartPose.offset(0.0F, 10.0F, 0.0F));

		PartDefinition TailPrimary = Tail.addOrReplaceChild("TailPrimary", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition Base_r1 = TailPrimary.addOrReplaceChild("Base_r1", CubeListBuilder.create().texOffs(32, 61).addBox(-2.0F, 0.75F, -1.5F, 4.0F, 5.0F, 4.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.1781F, 0.0F, 0.0F));

		PartDefinition TailSecondary = TailPrimary.addOrReplaceChild("TailSecondary", CubeListBuilder.create(), PartPose.offset(0.0F, 1.25F, 4.5F));

		PartDefinition Base_r2 = TailSecondary.addOrReplaceChild("Base_r2", CubeListBuilder.create().texOffs(26, 32).addBox(-2.5F, -0.45F, -2.1F, 5.0F, 9.0F, 5.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.5F, 0.0F, 1.4835F, 0.0F, 0.0F));

		PartDefinition TailTertiary = TailSecondary.addOrReplaceChild("TailTertiary", CubeListBuilder.create(), PartPose.offset(0.0F, 0.75F, 2.5F));

		PartDefinition Base_r3 = TailTertiary.addOrReplaceChild("Base_r3", CubeListBuilder.create().texOffs(0, 62).addBox(-2.0F, -1.2F, -1.95F, 4.0F, 5.0F, 4.0F, new CubeDeformation(-0.15F)), PartPose.offsetAndRotation(0.0F, 0.0F, 6.0F, 1.8326F, 0.0F, 0.0F));

		PartDefinition Plantoids = body.addOrReplaceChild("Plantoids", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -2.0F));

		PartDefinition RightPlantoid_r1 = Plantoids.addOrReplaceChild("RightPlantoid_r1", CubeListBuilder.create().texOffs(68, 52).addBox(-4.25F, -1.9F, -0.6F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.03F))
				.texOffs(66, 63).addBox(0.25F, -1.9F, -0.6F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.03F)), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, -0.2793F, 0.0F, 0.0F));

		PartDefinition Center_r1 = Plantoids.addOrReplaceChild("Center_r1", CubeListBuilder.create().texOffs(60, 46).addBox(-0.5F, -1.5F, -0.1F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, -0.192F, 0.0F, 0.0F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 35).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.02F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition right_Pat = right_arm.addOrReplaceChild("right_Pat", CubeListBuilder.create().texOffs(32, 13).addBox(-1.0F, 1.05F, -0.6F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(16, 43).addBox(-2.0F, 1.125F, -1.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.08F))
				.texOffs(20, 43).addBox(1.0F, 1.125F, -1.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.08F))
				.texOffs(50, 29).addBox(-1.0F, 1.125F, -2.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.08F))
				.texOffs(54, 29).addBox(0.0F, 1.125F, -2.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.08F)), PartPose.offset(-1.0F, 8.225F, 0.4F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(50, 13).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.02F)), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition left_Pat = left_arm.addOrReplaceChild("left_Pat", CubeListBuilder.create().texOffs(32, 13).mirror().addBox(-1.0F, 1.05F, -0.6F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(16, 43).mirror().addBox(1.0F, 1.125F, -1.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.08F)).mirror(false)
				.texOffs(20, 43).mirror().addBox(-2.0F, 1.125F, -1.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.08F)).mirror(false)
				.texOffs(50, 29).mirror().addBox(0.0F, 1.125F, -2.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.08F)).mirror(false)
				.texOffs(54, 29).mirror().addBox(-1.0F, 1.125F, -2.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.08F)).mirror(false), PartPose.offset(1.0F, 8.225F, 0.4F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition RightThigh_r1 = right_leg.addOrReplaceChild("RightThigh_r1", CubeListBuilder.create().texOffs(52, 52).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.02F)), PartPose.offsetAndRotation(-0.6F, -1.5F, 0.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition RightLowerLeg = right_leg.addOrReplaceChild("RightLowerLeg", CubeListBuilder.create(), PartPose.offset(-0.6F, 4.875F, -3.45F));

		PartDefinition RightCalf_r1 = RightLowerLeg.addOrReplaceChild("RightCalf_r1", CubeListBuilder.create().texOffs(56, 0).addBox(-1.99F, -0.125F, -2.9F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.125F, 1.95F, 0.8727F, 0.0F, 0.0F));

		PartDefinition RightFoot = RightLowerLeg.addOrReplaceChild("RightFoot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.8F, 7.175F));

		PartDefinition RightArch_r1 = RightFoot.addOrReplaceChild("RightArch_r1", CubeListBuilder.create().texOffs(66, 10).addBox(-2.0F, -8.45F, -0.725F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.005F)), PartPose.offsetAndRotation(0.0F, 7.075F, -4.975F, -0.3491F, 0.0F, 0.0F));

		PartDefinition RightPad = RightFoot.addOrReplaceChild("RightPad", CubeListBuilder.create().texOffs(48, 63).addBox(-2.0F, 0.0F, -2.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.325F, -4.425F));

		PartDefinition right_Pat2 = RightPad.addOrReplaceChild("right_Pat2", CubeListBuilder.create().texOffs(40, 13).addBox(6.4F, 1.05F, 0.1F, 2.0F, 1.0F, 2.0F, new CubeDeformation(-0.1F))
				.texOffs(56, 10).addBox(5.4F, 1.125F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
				.texOffs(56, 10).addBox(8.4F, 1.125F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
				.texOffs(56, 10).addBox(6.4F, 1.125F, -1.6F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
				.texOffs(56, 10).addBox(7.4F, 1.125F, -1.6F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offset(-7.4F, 0.125F, -0.7F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition LeftThigh_r1 = left_leg.addOrReplaceChild("LeftThigh_r1", CubeListBuilder.create().texOffs(0, 51).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.02F)), PartPose.offsetAndRotation(0.6F, -1.5F, 0.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition LeftLowerLeg = left_leg.addOrReplaceChild("LeftLowerLeg", CubeListBuilder.create(), PartPose.offset(0.6F, 4.875F, -3.45F));

		PartDefinition LeftCalf_r1 = LeftLowerLeg.addOrReplaceChild("LeftCalf_r1", CubeListBuilder.create().texOffs(16, 60).addBox(-2.01F, -0.125F, -2.9F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.125F, 1.95F, 0.8727F, 0.0F, 0.0F));

		PartDefinition LeftFoot = LeftLowerLeg.addOrReplaceChild("LeftFoot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.8F, 7.175F));

		PartDefinition LeftArch_r1 = LeftFoot.addOrReplaceChild("LeftArch_r1", CubeListBuilder.create().texOffs(66, 19).addBox(-2.0F, -8.45F, -0.725F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.005F)), PartPose.offsetAndRotation(0.0F, 7.075F, -4.975F, -0.3491F, 0.0F, 0.0F));

		PartDefinition LeftPad = LeftFoot.addOrReplaceChild("LeftPad", CubeListBuilder.create().texOffs(64, 29).addBox(-2.0F, 0.0F, -2.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.325F, -4.425F));

		PartDefinition left_Pat2 = LeftPad.addOrReplaceChild("left_Pat2", CubeListBuilder.create().texOffs(40, 13).mirror().addBox(-8.4F, 1.05F, 0.1F, 2.0F, 1.0F, 2.0F, new CubeDeformation(-0.1F)).mirror(false)
				.texOffs(56, 10).mirror().addBox(-6.4F, 1.125F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)).mirror(false)
				.texOffs(56, 10).mirror().addBox(-9.4F, 1.125F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)).mirror(false)
				.texOffs(56, 10).mirror().addBox(-7.4F, 1.125F, -1.6F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)).mirror(false)
				.texOffs(56, 10).mirror().addBox(-8.4F, 1.125F, -1.6F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)).mirror(false), PartPose.offset(7.4F, 0.125F, -0.7F));

		PartDefinition hat = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount,
	                      float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		float swing = Mth.sin(ageInTicks * 0.15F) * 0.35F;
		this.Tail.yRot = swing * 0.5F;
		this.TailPrimary.yRot = swing * 0.8F;
		this.TailSecondary.yRot = swing * 1.0F;
		this.TailTertiary.yRot = swing * 1.2F;


	}


	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight,
	                           int packedOverlay, float red, float green, float blue, float alpha) {
		super.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}