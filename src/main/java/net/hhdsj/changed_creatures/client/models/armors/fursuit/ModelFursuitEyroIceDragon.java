package net.hhdsj.changed_creatures.client.models.armors.fursuit;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.hhdsj.changed_creatures.ChangedCreature;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class ModelFursuitEyroIceDragon extends HumanoidFursuitModel {

	public static final ModelLayerLocation LAYER_LOCATION =
			ChangedCreature.ChangedCreatureModelResourceLocation("model_fursuit_eyro_end_dragon");

	private final ModelPart head;
	private final ModelPart hat;
	private final ModelPart body;
	private final ModelPart rightArm;
	private final ModelPart leftArm;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;

	public ModelFursuitEyroIceDragon(ModelPart root) {
		super(root);

		this.head = root.getChild("head");
		this.hat = root.getChild("hat");
		this.body = root.getChild("body");
		this.rightArm = root.getChild("right_arm");
		this.leftArm = root.getChild("left_arm");
		this.rightLeg = root.getChild("right_leg");
		this.leftLeg = root.getChild("left_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(-2.5F, 10.5F, 0.0F));

		PartDefinition cube_r1 = right_leg.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(22, 48).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 5.5F, -1.2F, -0.3491F, 0.0F, -0.3054F));

		PartDefinition RightThigh_r1 = right_leg.addOrReplaceChild("RightThigh_r1", CubeListBuilder.create().texOffs(48, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition RightLowerLeg = right_leg.addOrReplaceChild("RightLowerLeg", CubeListBuilder.create(), PartPose.offset(0.0F, 6.375F, -3.45F));

		PartDefinition RightCalf_r1 = RightLowerLeg.addOrReplaceChild("RightCalf_r1", CubeListBuilder.create().texOffs(0, 55).addBox(-1.99F, -0.125F, -2.9F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.125F, 1.95F, 0.8727F, 0.0F, 0.0F));

		PartDefinition RightFoot = RightLowerLeg.addOrReplaceChild("RightFoot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.8F, 7.175F));

		PartDefinition RightArch_r1 = RightFoot.addOrReplaceChild("RightArch_r1", CubeListBuilder.create().texOffs(38, 63).addBox(-2.0F, -8.45F, -0.725F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.005F)), PartPose.offsetAndRotation(0.0F, 7.075F, -4.975F, -0.3491F, 0.0F, 0.0F));

		PartDefinition RightPad = RightFoot.addOrReplaceChild("RightPad", CubeListBuilder.create().texOffs(56, 56).addBox(-2.0F, 0.0F, -2.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.325F, -4.425F));

		PartDefinition bone5 = RightPad.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(40, 86).addBox(12.0F, 9.2F, -1.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
				.texOffs(44, 86).addBox(9.0F, 9.2F, -1.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
				.texOffs(28, 52).addBox(10.0F, 9.2F, -1.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
				.texOffs(16, 63).addBox(11.0F, 9.2F, -1.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
				.texOffs(12, 84).addBox(10.0F, 9.1F, -0.1F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-11.0F, -8.0F, -0.55F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(2.5F, 10.5F, 0.0F));

		PartDefinition cube_r2 = left_leg.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(82, 0).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 5.5F, -1.2F, -0.3491F, 0.0F, 0.3054F));

		PartDefinition LeftThigh_r1 = left_leg.addOrReplaceChild("LeftThigh_r1", CubeListBuilder.create().texOffs(22, 54).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition LeftLowerLeg = left_leg.addOrReplaceChild("LeftLowerLeg", CubeListBuilder.create(), PartPose.offset(0.0F, 6.375F, -3.45F));

		PartDefinition LeftCalf_r1 = LeftLowerLeg.addOrReplaceChild("LeftCalf_r1", CubeListBuilder.create().texOffs(56, 46).addBox(-2.01F, -0.125F, -2.9F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.125F, 1.95F, 0.8727F, 0.0F, 0.0F));

		PartDefinition LeftFoot = LeftLowerLeg.addOrReplaceChild("LeftFoot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.8F, 7.175F));

		PartDefinition LeftArch_r1 = LeftFoot.addOrReplaceChild("LeftArch_r1", CubeListBuilder.create().texOffs(52, 63).addBox(-2.0F, -8.45F, -0.725F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.005F)), PartPose.offsetAndRotation(0.0F, 7.075F, -4.975F, -0.3491F, 0.0F, 0.0F));

		PartDefinition LeftPad = LeftFoot.addOrReplaceChild("LeftPad", CubeListBuilder.create().texOffs(62, 11).addBox(-2.0F, 0.0F, -2.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.325F, -4.425F));

		PartDefinition bone6 = LeftPad.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(28, 86).addBox(-10.0F, 9.2F, -1.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
				.texOffs(36, 86).addBox(-13.0F, 9.2F, -1.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
				.texOffs(28, 48).addBox(-12.0F, 9.2F, -1.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
				.texOffs(28, 50).addBox(-11.0F, 9.2F, -1.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
				.texOffs(44, 83).addBox(-12.0F, 9.1F, -0.1F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(11.0F, -8.0F, -0.55F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(80, 24).addBox(-2.0F, -3.0F, -6.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(22, 75).addBox(-1.5F, -1.0F, -5.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 0.0F));

		PartDefinition cube_r3 = head.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(74, 52).addBox(-1.2F, -1.2F, -1.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(72, 46).addBox(-0.3F, 0.3F, -2.2F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -2.25F, -1.4F, 0.1309F, 0.1745F, 0.3927F));

		PartDefinition cube_r4 = head.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(74, 36).addBox(-1.7F, 0.3F, -2.2F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(66, 73).addBox(-0.8F, -1.2F, -1.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -2.25F, -1.4F, 0.1309F, -0.1745F, -0.3927F));

		PartDefinition cube_r5 = head.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(22, 77).addBox(-1.0F, -6.0F, -1.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.35F, -6.2F, 0.35F, -0.9163F, 0.3491F, 0.0F));

		PartDefinition cube_r6 = head.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(82, 62).addBox(-1.0F, -5.0F, -1.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.35F, -5.85F, 0.35F, -0.9163F, 0.3491F, 0.0F));

		PartDefinition cube_r7 = head.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(82, 69).addBox(0.0F, -5.0F, -1.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.35F, -5.85F, 0.35F, -0.9163F, -0.3491F, 0.0F));

		PartDefinition cube_r8 = head.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(12, 75).addBox(-1.0F, -6.0F, -1.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.35F, -6.2F, 0.35F, -0.9163F, -0.3491F, 0.0F));

		PartDefinition bone = head.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r9 = bone.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(44, 77).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.1F))
				.texOffs(32, 77).addBox(4.5F, -1.0F, -1.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(-2.75F, -7.5F, -2.4F, 0.5672F, 0.0F, 0.0F));

		PartDefinition cube_r10 = bone.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(78, 79).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(66, 79).addBox(-5.5F, -1.0F, -1.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.75F, -9.875F, 1.6F, 0.3927F, 0.0F, 0.0F));

		PartDefinition bone2 = head.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offset(0.25F, -8.7276F, -1.4036F));

		PartDefinition cube_r11 = bone2.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 65).addBox(-1.25F, -1.0F, -1.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.2F, 0.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition bone7 = head.addOrReplaceChild("bone7", CubeListBuilder.create(), PartPose.offset(-0.25F, -8.7276F, -1.4036F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 0.0F));

		PartDefinition cube_r12 = body.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(30, 65).addBox(0.0F, -5.0F, -1.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 5.2F, 2.8F, 0.3927F, 0.0F, 0.0F));

		PartDefinition RightWing = body.addOrReplaceChild("RightWing", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.0F, 5.0F, 2.0F, 0.0F, 0.48F, 0.0F));

		PartDefinition rightWingRoot = RightWing.addOrReplaceChild("rightWingRoot", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0678F, 0.4755F, -0.1473F));

		PartDefinition cube_r13 = rightWingRoot.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(108, 123).addBox(-25.975F, -4.475F, 1.65F, 10.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 20.0F, -2.0F, 0.0F, 0.0F, 1.2654F));

		PartDefinition cube_r14 = rightWingRoot.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(80, 12).addBox(-25.0123F, -12.7627F, 1.2462F, 6.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(2.0F, 20.0F, -2.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r15 = rightWingRoot.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(80, 28).addBox(-12.775F, -19.75F, 1.2F, 5.0F, 2.0F, 1.0F, new CubeDeformation(-0.11F)), PartPose.offsetAndRotation(2.0F, 20.0F, -2.0F, 0.0F, 0.0F, 0.3491F));

		PartDefinition rightSecondaries = rightWingRoot.addOrReplaceChild("rightSecondaries", CubeListBuilder.create(), PartPose.offsetAndRotation(-7.3F, -7.0F, -0.5F, 0.0F, 0.0F, 0.0436F));

		PartDefinition cube_r16 = rightSecondaries.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(44, 121).addBox(-22.4F, 8.625F, 1.651F, 11.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.3F, 27.0F, -1.5F, 0.0F, 0.0F, 1.8326F));

		PartDefinition cube_r17 = rightSecondaries.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(4, 72).addBox(0.7886F, -3.5029F, -0.3544F, 1.0F, 15.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.3F, 3.025F, 0.2F, -0.0051F, -0.0001F, -0.2384F));

		PartDefinition cube_r18 = rightSecondaries.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(32, 47).addBox(-24.525F, -13.85F, 1.648F, 12.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.3F, 27.0F, -1.5F, 0.0F, 0.0F, 0.7418F));

		PartDefinition rightTertiaries = rightSecondaries.addOrReplaceChild("rightTertiaries", CubeListBuilder.create(), PartPose.offsetAndRotation(0.3F, 0.0F, 0.0F, 0.0F, 0.0F, 0.4363F));

		PartDefinition cube_r19 = rightTertiaries.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(8, 72).addBox(-0.1005F, -0.7819F, -0.6457F, 1.0F, 15.0F, 1.0F, new CubeDeformation(-0.151F)), PartPose.offsetAndRotation(0.2726F, 0.2793F, 0.5232F, -0.0105F, -0.059F, -0.0727F));

		PartDefinition cube_r20 = rightTertiaries.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(40, 16).addBox(-25.125F, -10.525F, 1.64F, 11.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.0F, 27.0F, -1.5F, 0.0F, 0.0F, 0.8727F));

		PartDefinition LeftWing = body.addOrReplaceChild("LeftWing", CubeListBuilder.create(), PartPose.offsetAndRotation(2.0F, 5.0F, 2.0F, 0.0F, -0.48F, 0.0F));

		PartDefinition leftWingRoot = LeftWing.addOrReplaceChild("leftWingRoot", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0678F, -0.4755F, 0.1473F));

		PartDefinition cube_r21 = leftWingRoot.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(61, 26).addBox(15.975F, -4.475F, 1.65F, 10.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 20.0F, -2.0F, 0.0F, 0.0F, -1.2654F));

		PartDefinition cube_r22 = leftWingRoot.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(80, 9).addBox(19.0123F, -12.7627F, 1.2462F, 6.0F, 2.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-2.0F, 20.0F, -2.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r23 = leftWingRoot.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(80, 15).addBox(7.775F, -19.75F, 1.2F, 5.0F, 2.0F, 1.0F, new CubeDeformation(-0.11F)), PartPose.offsetAndRotation(-2.0F, 20.0F, -2.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition leftSecondaries = leftWingRoot.addOrReplaceChild("leftSecondaries", CubeListBuilder.create(), PartPose.offsetAndRotation(7.3F, -7.0F, -0.5F, 0.0F, 0.0F, -0.0436F));

		PartDefinition cube_r24 = leftSecondaries.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(62, 72).addBox(-1.7886F, -3.5029F, -0.3544F, 1.0F, 15.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-0.3F, 3.025F, 0.2F, -0.0051F, 0.0001F, 0.2384F));

		PartDefinition cube_r25 = leftSecondaries.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(67, 121).addBox(12.525F, -13.85F, 1.648F, 12.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.3F, 27.0F, -1.5F, 0.0F, 0.0F, -0.7418F));

		PartDefinition cube_r26 = leftSecondaries.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(21, 121).addBox(11.4F, 8.625F, 1.651F, 11.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.3F, 27.0F, -1.5F, 0.0F, 0.0F, -1.8326F));

		PartDefinition leftTertiaries = leftSecondaries.addOrReplaceChild("leftTertiaries", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.3F, 0.0F, 0.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition cube_r27 = leftTertiaries.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(32, 32).addBox(14.125F, -10.525F, 1.64F, 11.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, 27.0F, -1.5F, 0.0F, 0.0F, -0.8727F));

		PartDefinition cube_r28 = leftTertiaries.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(0, 72).addBox(-0.8995F, -0.7819F, -0.6457F, 1.0F, 15.0F, 1.0F, new CubeDeformation(-0.151F)), PartPose.offsetAndRotation(-0.2726F, 0.2793F, 0.5232F, -0.0109F, 0.0658F, 0.07F));

		PartDefinition Tail = body.addOrReplaceChild("Tail", CubeListBuilder.create(), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition TailPrimary = Tail.addOrReplaceChild("TailPrimary", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r29 = TailPrimary.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(74, 58).addBox(-2.0F, 0.0F, -2.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(48, 11).addBox(3.0F, 0.0F, -2.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, 3.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition cube_r30 = TailPrimary.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(80, 5).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 3.9F, 0.0F, 0.5236F, 1.5708F));

		PartDefinition Base_r1 = TailPrimary.addOrReplaceChild("Base_r1", CubeListBuilder.create().texOffs(62, 18).addBox(-2.0F, -2.9F, 0.4F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition TailSecondary = TailPrimary.addOrReplaceChild("TailSecondary", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 3.5F));

		PartDefinition cube_r31 = TailSecondary.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(14, 65).addBox(-2.0F, 0.2F, 1.8F, 3.0F, 0.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(64, 5).addBox(3.0F, 0.2F, 1.8F, 3.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -1.0F, -0.5F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r32 = TailSecondary.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(14, 70).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.3F, 3.5F, 0.0F, 0.3927F, 1.5708F));

		PartDefinition Base_r2 = TailSecondary.addOrReplaceChild("Base_r2", CubeListBuilder.create().texOffs(54, 31).addBox(-1.5F, -1.4F, -2.7F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.05F)), PartPose.offsetAndRotation(0.0F, 1.0F, 2.5F, -0.3927F, 0.0F, 0.0F));

		PartDefinition TailTertiary = TailSecondary.addOrReplaceChild("TailTertiary", CubeListBuilder.create(), PartPose.offset(0.0F, 2.5F, 5.0F));

		PartDefinition cube_r33 = TailTertiary.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(66, 68).addBox(-2.0F, 1.8F, 6.3F, 3.0F, 0.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(66, 63).addBox(3.0F, 1.8F, 6.3F, 3.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -3.5F, -5.5F, -0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r34 = TailTertiary.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(30, 72).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.3F, 2.9F, 0.0F, 0.1309F, 1.5708F));

		PartDefinition Base_r3 = TailTertiary.addOrReplaceChild("Base_r3", CubeListBuilder.create().texOffs(38, 54).addBox(-1.5F, -13.225F, 6.6F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.22F)), PartPose.offsetAndRotation(0.0F, 10.5F, -8.5F, -0.1309F, 0.0F, 0.0F));

		PartDefinition TailQuaternary = TailTertiary.addOrReplaceChild("TailQuaternary", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, 4.5F));

		PartDefinition cube_r35 = TailQuaternary.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(54, 40).addBox(-1.5F, 0.0F, -2.0F, 4.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.1F, 3.1F, 0.0F, -0.0436F, 1.5708F));

		PartDefinition cube_r36 = TailQuaternary.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(46, 72).addBox(-1.2F, 4.3F, 10.7F, 3.0F, 0.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(72, 31).addBox(2.2F, 4.3F, 10.7F, 3.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -4.0F, -10.0F, 0.0436F, 0.0F, 0.0F));

		PartDefinition Base_r4 = TailQuaternary.addOrReplaceChild("Base_r4", CubeListBuilder.create().texOffs(84, 85).addBox(0.0F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(-0.5F, -0.8171F, 5.773F, 0.0436F, 0.0F, 0.0F));

		PartDefinition Base_r5 = TailQuaternary.addOrReplaceChild("Base_r5", CubeListBuilder.create().texOffs(78, 18).addBox(-1.0F, -10.45F, 13.5F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.05F)), PartPose.offsetAndRotation(0.0F, 10.0F, -13.0F, 0.0436F, 0.0F, 0.0F));

		PartDefinition hair = body.addOrReplaceChild("hair", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r37 = hair.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(74, 44).addBox(-6.0F, 0.0F, -1.0F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 1.5929F, -1.2929F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r38 = hair.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(74, 42).addBox(-6.0F, 0.0F, -1.0F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 0.7929F, -1.2929F, -0.7854F, 0.0F, 0.0F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(24, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(32, 0).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.08F)), PartPose.offset(-5.0F, 1.5F, 0.0F));

		PartDefinition cube_r39 = right_arm.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(16, 55).addBox(-1.0F, -7.0F, -1.0F, 2.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.65F, -1.65F, 0.7F, -0.1309F, 0.0F, -2.8362F));

		PartDefinition bone3 = right_arm.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(86, 48).addBox(12.0F, 9.2F, -1.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
				.texOffs(86, 50).addBox(9.0F, 9.2F, -1.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
				.texOffs(20, 86).addBox(10.0F, 9.2F, -1.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
				.texOffs(24, 86).addBox(11.0F, 9.2F, -1.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
				.texOffs(36, 83).addBox(10.0F, 9.1F, -0.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-12.0F, 0.0F, 0.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 32).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.08F))
				.texOffs(16, 32).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 1.5F, 0.0F));

		PartDefinition cube_r40 = left_arm.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(56, 77).addBox(-1.0F, -7.0F, -1.0F, 2.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.65F, -1.65F, 0.7F, -0.1309F, 0.0F, 2.8362F));

		PartDefinition bone4 = left_arm.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(86, 46).addBox(-10.0F, 9.2F, -1.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
				.texOffs(48, 86).addBox(-13.0F, 9.2F, -1.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
				.texOffs(52, 83).addBox(-12.0F, 9.2F, -1.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
				.texOffs(58, 85).addBox(-11.0F, 9.2F, -1.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
				.texOffs(80, 76).addBox(-12.0F, 9.1F, -0.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(12.0F, 0.0F, 0.0F));

		PartDefinition hat = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offset(5.0F, 1.5F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount,
	                      float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
	}


	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}