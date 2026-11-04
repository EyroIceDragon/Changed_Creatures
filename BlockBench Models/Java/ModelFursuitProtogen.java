// Made with Blockbench 5.2.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class ModelFursuitProtogen<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "modelfursuitprotogen"), "main");
	private final ModelPart head;
	private final ModelPart hair;
	private final ModelPart left_ear;
	private final ModelPart right_ear;
	private final ModelPart bone;
	private final ModelPart body;
	private final ModelPart Tail;
	private final ModelPart TailPrimary;
	private final ModelPart TailSecondary;
	private final ModelPart TailTertiary;
	private final ModelPart Plantoids;
	private final ModelPart right_arm;
	private final ModelPart right_Pat;
	private final ModelPart left_arm;
	private final ModelPart left_Pat;
	private final ModelPart right_leg;
	private final ModelPart RightLowerLeg;
	private final ModelPart RightFoot;
	private final ModelPart RightPad;
	private final ModelPart right_Pat2;
	private final ModelPart left_leg;
	private final ModelPart LeftLowerLeg;
	private final ModelPart LeftFoot;
	private final ModelPart LeftPad;
	private final ModelPart left_Pat2;
	private final ModelPart hat;

	public ModelFursuitProtogen(ModelPart root) {
		this.head = root.getChild("head");
		this.hair = this.head.getChild("hair");
		this.left_ear = this.head.getChild("left_ear");
		this.right_ear = this.head.getChild("right_ear");
		this.bone = this.head.getChild("bone");
		this.body = root.getChild("body");
		this.Tail = this.body.getChild("Tail");
		this.TailPrimary = this.Tail.getChild("TailPrimary");
		this.TailSecondary = this.TailPrimary.getChild("TailSecondary");
		this.TailTertiary = this.TailSecondary.getChild("TailTertiary");
		this.Plantoids = this.body.getChild("Plantoids");
		this.right_arm = root.getChild("right_arm");
		this.right_Pat = this.right_arm.getChild("right_Pat");
		this.left_arm = root.getChild("left_arm");
		this.left_Pat = this.left_arm.getChild("left_Pat");
		this.right_leg = root.getChild("right_leg");
		this.RightLowerLeg = this.right_leg.getChild("RightLowerLeg");
		this.RightFoot = this.RightLowerLeg.getChild("RightFoot");
		this.RightPad = this.RightFoot.getChild("RightPad");
		this.right_Pat2 = this.RightPad.getChild("right_Pat2");
		this.left_leg = root.getChild("left_leg");
		this.LeftLowerLeg = this.left_leg.getChild("LeftLowerLeg");
		this.LeftFoot = this.LeftLowerLeg.getChild("LeftFoot");
		this.LeftPad = this.LeftFoot.getChild("LeftPad");
		this.left_Pat2 = this.LeftPad.getChild("left_Pat2");
		this.hat = root.getChild("hat");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.02F))
		.texOffs(34, 52).addBox(-3.5F, -7.5F, -5.6F, 7.0F, 7.0F, 2.0F, new CubeDeformation(0.1F))
		.texOffs(32, 0).addBox(-2.5F, -7.0F, -12.7F, 5.0F, 6.0F, 7.0F, new CubeDeformation(0.02F)), PartPose.offset(0.0F, 0.0F, 1.0F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(46, 70).addBox(-0.5F, -2.0F, -2.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(36, 70).addBox(5.1F, -2.0F, -2.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.3F, -2.6F, -3.7F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(68, 58).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(56, 70).addBox(6.9F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7F, -2.7F, -3.5F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r3 = head.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(46, 32).addBox(-1.0F, -4.0F, -8.0F, 1.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.225F, -3.0F, -4.625F, 0.0F, -0.0873F, 0.0F));

		PartDefinition cube_r4 = head.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(16, 46).addBox(0.0F, -4.0F, -8.0F, 1.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.225F, -3.0F, -4.625F, 0.0F, 0.0873F, 0.0F));

		PartDefinition hair = head.addOrReplaceChild("hair", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -4.1F, -1.3F, 8.0F, 14.0F, 5.0F, new CubeDeformation(0.08F)), PartPose.offset(0.0F, -4.0F, 0.5F));

		PartDefinition left_ear = head.addOrReplaceChild("left_ear", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r5 = left_ear.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(26, 70).addBox(-1.0F, -5.0F, -2.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6F, -3.9F, -1.5F, -1.9398F, 0.6414F, -0.5125F));

		PartDefinition cube_r6 = left_ear.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(26, 70).addBox(-1.0F, -5.0F, -2.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6F, -5.5F, -1.5F, -0.9829F, 0.3045F, 0.5692F));

		PartDefinition cube_r7 = left_ear.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(66, 69).addBox(-1.0F, -5.0F, -2.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.6F, -3.9F, -1.5F, -1.9398F, -0.6414F, 0.5125F));

		PartDefinition right_ear = head.addOrReplaceChild("right_ear", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r8 = right_ear.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(16, 70).addBox(-1.0F, -5.0F, -2.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4F, -6.5F, -2.1F, -0.5236F, 0.1745F, -0.2618F));

		PartDefinition cube_r9 = right_ear.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(16, 35).addBox(-1.0F, -5.0F, -2.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4F, -6.5F, -2.1F, -0.5236F, -0.1745F, 0.2618F));

		PartDefinition cube_r10 = right_ear.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(26, 70).mirror().addBox(-1.0F, -5.0F, -2.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.6F, -5.5F, -1.5F, -0.9829F, -0.3045F, -0.5692F));

		PartDefinition bone = head.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(26, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.05F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition fur_r1 = body.addOrReplaceChild("fur_r1", CubeListBuilder.create().texOffs(34, 46).addBox(-4.0F, -0.5F, -3.0F, 8.0F, 1.0F, 5.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.3F, 0.1047F, 0.0F, 0.0F));

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

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 35).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.02F))
		.texOffs(64, 36).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition cube_r11 = right_arm.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 71).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.7F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition right_Pat = right_arm.addOrReplaceChild("right_Pat", CubeListBuilder.create().texOffs(32, 13).addBox(-1.0F, 1.05F, -0.6F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(16, 43).addBox(-2.0F, 1.125F, -1.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.08F))
		.texOffs(20, 43).addBox(1.0F, 1.125F, -1.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.08F))
		.texOffs(50, 29).addBox(-1.0F, 1.125F, -2.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.08F))
		.texOffs(54, 29).addBox(0.0F, 1.125F, -2.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.08F)), PartPose.offset(-1.0F, 8.225F, 0.4F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(50, 13).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.02F))
		.texOffs(64, 44).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition cube_r12 = left_arm.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(6, 71).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.7F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition left_Pat = left_arm.addOrReplaceChild("left_Pat", CubeListBuilder.create().texOffs(32, 13).mirror().addBox(-1.0F, 1.05F, -0.6F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(16, 43).mirror().addBox(1.0F, 1.125F, -1.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.08F)).mirror(false)
		.texOffs(20, 43).mirror().addBox(-2.0F, 1.125F, -1.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.08F)).mirror(false)
		.texOffs(50, 29).mirror().addBox(0.0F, 1.125F, -2.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.08F)).mirror(false)
		.texOffs(54, 29).mirror().addBox(-1.0F, 1.125F, -2.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.08F)).mirror(false), PartPose.offset(1.0F, 8.225F, 0.4F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition cube_r13 = right_leg.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(72, 0).addBox(0.0F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.7F, 1.8F, -0.65F, -0.9163F, 0.0F, 0.0F));

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

		PartDefinition cube_r14 = left_leg.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(72, 4).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.7F, 1.8F, -0.65F, -0.9163F, 0.0F, 0.0F));

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
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		hat.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}