// Made with Blockbench 5.2.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class protogenheadmodel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "protogenheadmodel"), "main");
	private final ModelPart Head;
	private final ModelPart v;
	private final ModelPart hair2;
	private final ModelPart left_ear;
	private final ModelPart right_ear;
	private final ModelPart v2;
	private final ModelPart Body;
	private final ModelPart Tail;
	private final ModelPart TailPrimary;
	private final ModelPart TailSecondary;
	private final ModelPart TailTertiary;
	private final ModelPart Right Arm;
	private final ModelPart right_Pat;
	private final ModelPart Left Arm;
	private final ModelPart left_Pat;
	private final ModelPart Right Leg;
	private final ModelPart right_Pat2;
	private final ModelPart Left Leg;
	private final ModelPart left_Pat2;

	public protogenheadmodel(ModelPart root) {
		this.Head = root.getChild("Head");
		this.v = this.Head.getChild("v");
		this.hair2 = this.Head.getChild("hair2");
		this.left_ear = this.Head.getChild("left_ear");
		this.right_ear = this.Head.getChild("right_ear");
		this.v2 = this.Head.getChild("v2");
		this.Body = root.getChild("Body");
		this.Tail = this.Body.getChild("Tail");
		this.TailPrimary = this.Tail.getChild("TailPrimary");
		this.TailSecondary = this.TailPrimary.getChild("TailSecondary");
		this.TailTertiary = this.TailSecondary.getChild("TailTertiary");
		this.Right Arm = root.getChild("Right Arm");
		this.right_Pat = this.Right Arm.getChild("right_Pat");
		this.Left Arm = root.getChild("Left Arm");
		this.left_Pat = this.Left Arm.getChild("left_Pat");
		this.Right Leg = root.getChild("Right Leg");
		this.right_Pat2 = this.Right Leg.getChild("right_Pat2");
		this.Left Leg = root.getChild("Left Leg");
		this.left_Pat2 = this.Left Leg.getChild("left_Pat2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.02F))
		.texOffs(60, 53).addBox(-3.5F, -7.5F, -5.6F, 7.0F, 7.0F, 2.0F, new CubeDeformation(0.1F))
		.texOffs(32, 16).addBox(-2.5F, -7.0F, -12.7F, 5.0F, 6.0F, 7.0F, new CubeDeformation(0.02F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = Head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(68, 70).addBox(-0.5F, -2.0F, -2.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(58, 70).addBox(5.1F, -2.0F, -2.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.3F, -2.6F, -3.7F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r2 = Head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(18, 51).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(18, 55).addBox(6.9F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7F, -2.7F, -3.5F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r3 = Head.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 51).addBox(-1.0F, -4.0F, -8.0F, 1.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.225F, -3.0F, -4.625F, 0.0F, -0.0873F, 0.0F));

		PartDefinition cube_r4 = Head.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(42, 46).addBox(0.0F, -4.0F, -8.0F, 1.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.225F, -3.0F, -4.625F, 0.0F, 0.0873F, 0.0F));

		PartDefinition v = Head.addOrReplaceChild("v", CubeListBuilder.create(), PartPose.offset(4.225F, -3.0F, -4.625F));

		PartDefinition hair2 = Head.addOrReplaceChild("hair2", CubeListBuilder.create().texOffs(0, 32).addBox(-4.0F, -4.1F, -1.3F, 8.0F, 14.0F, 5.0F, new CubeDeformation(0.08F)), PartPose.offset(0.0F, -4.0F, 0.5F));

		PartDefinition left_ear = Head.addOrReplaceChild("left_ear", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r5 = left_ear.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(68, 62).addBox(-1.0F, -5.0F, -2.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6F, -5.5F, -1.5F, -0.9829F, 0.3045F, 0.5692F));

		PartDefinition cube_r6 = left_ear.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(58, 62).addBox(-1.0F, -5.0F, -2.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.6F, -5.5F, -1.5F, -0.9829F, -0.3045F, -0.5692F));

		PartDefinition right_ear = Head.addOrReplaceChild("right_ear", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r7 = right_ear.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 65).addBox(-1.0F, -5.0F, -2.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4F, -6.5F, -2.1F, -0.5236F, 0.1745F, -0.2618F));

		PartDefinition cube_r8 = right_ear.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(62, 37).addBox(-1.0F, -5.0F, -2.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4F, -6.5F, -2.1F, -0.5236F, -0.1745F, 0.2618F));

		PartDefinition v2 = Head.addOrReplaceChild("v2", CubeListBuilder.create(), PartPose.offset(-4.225F, -3.0F, -4.625F));

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.05F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r9 = Body.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(56, 0).addBox(-4.0F, -0.5F, -3.0F, 8.0F, 1.0F, 5.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 0.2F, 0.3F, 0.1047F, 0.0F, 0.0F));

		PartDefinition Tail = Body.addOrReplaceChild("Tail", CubeListBuilder.create(), PartPose.offset(0.0F, 10.0F, 0.0F));

		PartDefinition TailPrimary = Tail.addOrReplaceChild("TailPrimary", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition Base_r1 = TailPrimary.addOrReplaceChild("Base_r1", CubeListBuilder.create().texOffs(18, 62).addBox(-2.0F, 0.75F, -1.5F, 4.0F, 5.0F, 4.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.1781F, 0.0F, 0.0F));

		PartDefinition TailSecondary = TailPrimary.addOrReplaceChild("TailSecondary", CubeListBuilder.create(), PartPose.offset(0.0F, 1.25F, 4.5F));

		PartDefinition Base_r2 = TailSecondary.addOrReplaceChild("Base_r2", CubeListBuilder.create().texOffs(26, 32).addBox(-2.5F, -0.45F, -2.1F, 5.0F, 9.0F, 5.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 0.5F, 0.0F, 1.4835F, 0.0F, 0.0F));

		PartDefinition TailTertiary = TailSecondary.addOrReplaceChild("TailTertiary", CubeListBuilder.create(), PartPose.offset(0.0F, 0.75F, 2.5F));

		PartDefinition Base_r3 = TailTertiary.addOrReplaceChild("Base_r3", CubeListBuilder.create().texOffs(62, 28).addBox(-2.0F, -1.2F, -1.95F, 4.0F, 5.0F, 4.0F, new CubeDeformation(-0.15F)), PartPose.offsetAndRotation(0.0F, 0.0F, 6.0F, 1.8326F, 0.0F, 0.0F));

		PartDefinition Right Arm = partdefinition.addOrReplaceChild("Right Arm", CubeListBuilder.create().texOffs(26, 46).addBox(9.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.05F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition right_Pat = Right Arm.addOrReplaceChild("right_Pat", CubeListBuilder.create().texOffs(32, 29).addBox(11.0F, 1.05F, -0.6F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(40, 29).addBox(10.0F, 1.125F, -1.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.08F))
		.texOffs(18, 59).addBox(13.0F, 1.125F, -1.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.08F))
		.texOffs(22, 59).addBox(11.0F, 1.125F, -2.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.08F))
		.texOffs(34, 62).addBox(12.0F, 1.125F, -2.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.08F)), PartPose.offset(-1.0F, 8.225F, 0.4F));

		PartDefinition Left Arm = partdefinition.addOrReplaceChild("Left Arm", CubeListBuilder.create().texOffs(46, 29).addBox(-13.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.05F)), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition left_Pat = Left Arm.addOrReplaceChild("left_Pat", CubeListBuilder.create().texOffs(32, 29).mirror().addBox(-13.0F, 1.05F, -0.6F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(40, 29).mirror().addBox(-11.0F, 1.125F, -1.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.08F)).mirror(false)
		.texOffs(18, 59).mirror().addBox(-14.0F, 1.125F, -1.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.08F)).mirror(false)
		.texOffs(22, 59).mirror().addBox(-12.0F, 1.125F, -2.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.08F)).mirror(false)
		.texOffs(34, 62).mirror().addBox(-13.0F, 1.125F, -2.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.08F)).mirror(false), PartPose.offset(1.0F, 8.225F, 0.4F));

		PartDefinition Right Leg = partdefinition.addOrReplaceChild("Right Leg", CubeListBuilder.create().texOffs(56, 6).addBox(2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.03F))
		.texOffs(56, 20).addBox(2.0F, 10.0F, -4.0F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.04F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition right_Pat2 = Right Leg.addOrReplaceChild("right_Pat2", CubeListBuilder.create().texOffs(32, 29).addBox(11.0F, 1.05F, -0.6F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(40, 29).addBox(10.0F, 1.125F, -1.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.08F))
		.texOffs(18, 59).addBox(13.0F, 1.125F, -1.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.08F))
		.texOffs(22, 59).addBox(11.0F, 1.125F, -2.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.08F))
		.texOffs(34, 62).addBox(12.0F, 1.125F, -2.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.08F)), PartPose.offset(-8.0F, 10.225F, -1.4F));

		PartDefinition Left Leg = partdefinition.addOrReplaceChild("Left Leg", CubeListBuilder.create().texOffs(42, 60).addBox(-6.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.03F))
		.texOffs(60, 45).addBox(-6.0F, 10.0F, -4.0F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.04F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition left_Pat2 = Left Leg.addOrReplaceChild("left_Pat2", CubeListBuilder.create().texOffs(32, 29).mirror().addBox(-13.0F, 1.05F, -0.6F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(40, 29).mirror().addBox(-11.0F, 1.125F, -1.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.08F)).mirror(false)
		.texOffs(18, 59).mirror().addBox(-14.0F, 1.125F, -1.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.08F)).mirror(false)
		.texOffs(22, 59).mirror().addBox(-12.0F, 1.125F, -2.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.08F)).mirror(false)
		.texOffs(34, 62).mirror().addBox(-13.0F, 1.125F, -2.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.08F)).mirror(false), PartPose.offset(8.0F, 10.225F, -1.4F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Right Arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Left Arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Right Leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Left Leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}