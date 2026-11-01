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

public class ModelFursuitProtogen extends HumanoidFursuitModel {

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
	private final ModelPart v;
	private final ModelPart hair2;
	private final ModelPart left_ear;
	private final ModelPart right_ear;
	private final ModelPart v2;
	private final ModelPart Tail;
	private final ModelPart TailPrimary;
	private final ModelPart TailSecondary;
	private final ModelPart TailTertiary;
	private final ModelPart right_Pat;
	private final ModelPart left_Pat;
	private final ModelPart right_Pat2;
	private final ModelPart left_Pat2;

	public ModelFursuitProtogen(ModelPart root) {
		super(root);

		this.head = root.getChild("head");
		this.hat = root.getChild("hat");
		this.body = root.getChild("body");
		this.rightArm = root.getChild("right_arm");
		this.leftArm = root.getChild("left_arm");
		this.rightLeg = root.getChild("right_leg");
		this.leftLeg = root.getChild("left_leg");

		this.v = this.head.getChild("v");
		this.hair2 = this.head.getChild("hair2");
		this.left_ear = this.head.getChild("left_ear");
		this.right_ear = this.head.getChild("right_ear");
		this.v2 = this.head.getChild("v2");

		this.Tail = this.body.getChild("Tail");
		this.TailPrimary = this.Tail.getChild("TailPrimary");
		this.TailSecondary = this.TailPrimary.getChild("TailSecondary");
		this.TailTertiary = this.TailSecondary.getChild("TailTertiary");

		this.right_Pat = this.rightArm.getChild("right_Pat");
		this.left_Pat = this.leftArm.getChild("left_Pat");
		this.right_Pat2 = this.rightLeg.getChild("right_Pat2");
		this.left_Pat2 = this.leftLeg.getChild("left_Pat2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.02F))
				.texOffs(60, 53).addBox(-3.5F, -7.5F, -5.6F, 7.0F, 7.0F, 2.0F, new CubeDeformation(0.1F))
				.texOffs(32, 16).addBox(-2.5F, -7.0F, -12.7F, 5.0F, 6.0F, 7.0F, new CubeDeformation(0.02F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(68, 70).addBox(-0.5F, -2.0F, -2.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(58, 70).addBox(5.1F, -2.0F, -2.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.3F, -2.6F, -3.7F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(18, 51).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(18, 55).addBox(6.9F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7F, -2.7F, -3.5F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r3 = head.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 51).addBox(-1.0F, -4.0F, -8.0F, 1.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.225F, -3.0F, -4.625F, 0.0F, -0.0873F, 0.0F));

		PartDefinition cube_r4 = head.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(42, 46).addBox(0.0F, -4.0F, -8.0F, 1.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.225F, -3.0F, -4.625F, 0.0F, 0.0873F, 0.0F));

		PartDefinition v = head.addOrReplaceChild("v", CubeListBuilder.create(), PartPose.offset(4.225F, -3.0F, -4.625F));

		PartDefinition hair2 = head.addOrReplaceChild("hair2", CubeListBuilder.create().texOffs(0, 32).addBox(-4.0F, -4.1F, -1.3F, 8.0F, 14.0F, 5.0F, new CubeDeformation(0.08F)), PartPose.offset(0.0F, -4.0F, 0.5F));

		PartDefinition left_ear = head.addOrReplaceChild("left_ear", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		left_ear.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(68, 62).addBox(-1.0F, -5.0F, -2.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6F, -5.5F, -1.5F, -0.9829F, 0.3045F, 0.5692F));
		left_ear.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(58, 62).addBox(-1.0F, -5.0F, -2.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.6F, -5.5F, -1.5F, -0.9829F, -0.3045F, -0.5692F));

		PartDefinition right_ear = head.addOrReplaceChild("right_ear", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		right_ear.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 65).addBox(-1.0F, -5.0F, -2.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4F, -6.5F, -2.1F, -0.5236F, 0.1745F, -0.2618F));
		right_ear.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(62, 37).addBox(-1.0F, -5.0F, -2.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4F, -6.5F, -2.1F, -0.5236F, -0.1745F, 0.2618F));

		PartDefinition v2 = head.addOrReplaceChild("v2", CubeListBuilder.create(), PartPose.offset(-4.225F, -3.0F, -4.625F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.05F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		body.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(56, 0).addBox(-4.0F, -0.5F, -3.0F, 8.0F, 1.0F, 5.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 0.2F, 0.3F, 0.1047F, 0.0F, 0.0F));

		PartDefinition Tail = body.addOrReplaceChild("Tail", CubeListBuilder.create(), PartPose.offset(0.0F, 10.0F, 0.0F));
		PartDefinition TailPrimary = Tail.addOrReplaceChild("TailPrimary", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1309F, 0.0F, 0.0F));
		TailPrimary.addOrReplaceChild("Base_r1", CubeListBuilder.create().texOffs(18, 62).addBox(-2.0F, 0.75F, -1.5F, 4.0F, 5.0F, 4.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.1781F, 0.0F, 0.0F));
		PartDefinition TailSecondary = TailPrimary.addOrReplaceChild("TailSecondary", CubeListBuilder.create(), PartPose.offset(0.0F, 1.25F, 4.5F));
		TailSecondary.addOrReplaceChild("Base_r2", CubeListBuilder.create().texOffs(26, 32).addBox(-2.5F, -0.45F, -2.1F, 5.0F, 9.0F, 5.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.5F, 0.0F, 1.4835F, 0.0F, 0.0F));
		PartDefinition TailTertiary = TailSecondary.addOrReplaceChild("TailTertiary", CubeListBuilder.create(), PartPose.offset(0.0F, 0.75F, 2.5F));
		TailTertiary.addOrReplaceChild("Base_r3", CubeListBuilder.create().texOffs(62, 28).addBox(-2.0F, -1.2F, -1.95F, 4.0F, 5.0F, 4.0F, new CubeDeformation(-0.15F)), PartPose.offsetAndRotation(0.0F, 0.0F, 6.0F, 1.8326F, 0.0F, 0.0F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(26, 46).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.05F)), PartPose.offset(-5.0F, 2.0F, 0.0F));
		right_arm.addOrReplaceChild("right_Pat", CubeListBuilder.create().texOffs(32, 29).addBox(-1.0F, 1.05F, -0.6F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(40, 29).addBox(-2.0F, 1.125F, -1.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.08F))
				.texOffs(18, 59).addBox(1.0F, 1.125F, -1.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.08F))
				.texOffs(22, 59).addBox(-1.0F, 1.125F, -2.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.08F))
				.texOffs(34, 62).addBox(0.0F, 1.125F, -2.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.08F)), PartPose.offset(-1.0F, 8.225F, 0.4F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(46, 29).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.05F)), PartPose.offset(5.0F, 2.0F, 0.0F));
		left_arm.addOrReplaceChild("left_Pat", CubeListBuilder.create().texOffs(32, 29).mirror().addBox(-1.0F, 1.05F, -0.6F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(40, 29).mirror().addBox(1.0F, 1.125F, -1.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.08F)).mirror(false)
				.texOffs(18, 59).mirror().addBox(-2.0F, 1.125F, -1.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.08F)).mirror(false)
				.texOffs(22, 59).mirror().addBox(0.0F, 1.125F, -2.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.08F)).mirror(false)
				.texOffs(34, 62).mirror().addBox(-1.0F, 1.125F, -2.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.08F)).mirror(false), PartPose.offset(1.0F, 8.225F, 0.4F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(56, 6).addBox(-2.2F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.1F))
				.texOffs(56, 20).addBox(-2.2F, 10.0F, -4.0F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.1F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
		right_leg.addOrReplaceChild("right_Pat2", CubeListBuilder.create().texOffs(0, 73).addBox(6.8F, 1.05F, -0.6F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(6, 73).addBox(5.8F, 1.125F, -1.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(6, 73).addBox(8.8F, 1.125F, -1.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(6, 73).addBox(6.8F, 1.125F, -2.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(6, 73).addBox(7.8F, 1.125F, -2.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, 10.125F, -1.4F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(42, 60).addBox(-1.8F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.1F))
				.texOffs(60, 45).addBox(-1.8F, 10.0F, -4.0F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.1F)), PartPose.offset(1.9F, 12.0F, 0.0F));
		left_leg.addOrReplaceChild("left_Pat2", CubeListBuilder.create().texOffs(0, 73).mirror().addBox(-8.8F, 1.05F, -0.6F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(6, 73).mirror().addBox(-6.8F, 1.125F, -1.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(6, 73).mirror().addBox(-9.8F, 1.125F, -1.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(6, 73).mirror().addBox(-7.8F, 1.125F, -2.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(6, 73).mirror().addBox(-8.8F, 1.125F, -2.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(8.0F, 10.125F, -1.4F));

		partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

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

		float speedFactor = Mth.clamp(limbSwingAmount * 2.0F, 0.0F, 1.5F);
		this.TailPrimary.xRot += speedFactor * 0.1F;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight,
	                           int packedOverlay, float red, float green, float blue, float alpha) {
		super.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}