// Made with Blockbench 5.2.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class LatexKobold<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "latexkobold"), "main");
	private final ModelPart RightLeg;
	private final ModelPart RightLowerLeg;
	private final ModelPart RightFoot;
	private final ModelPart RightPad;
	private final ModelPart LeftLeg;
	private final ModelPart LeftLowerLeg;
	private final ModelPart LeftFoot;
	private final ModelPart LeftPad;
	private final ModelPart Head;
	private final ModelPart CenterHorns;
	private final ModelPart RightHorn;
	private final ModelPart LeftHorn;
	private final ModelPart RightSideHorns;
	private final ModelPart LeftSideHorns;
	private final ModelPart Torso;
	private final ModelPart Tail;
	private final ModelPart TailPrimary;
	private final ModelPart TailSecondary;
	private final ModelPart TailTertiary;
	private final ModelPart TailQuaternary;
	private final ModelPart TailEnd;
	private final ModelPart bone3;
	private final ModelPart RightArm;
	private final ModelPart LeftArm;

	public LatexKobold(ModelPart root) {
		this.RightLeg = root.getChild("RightLeg");
		this.RightLowerLeg = this.RightLeg.getChild("RightLowerLeg");
		this.RightFoot = this.RightLowerLeg.getChild("RightFoot");
		this.RightPad = this.RightFoot.getChild("RightPad");
		this.LeftLeg = root.getChild("LeftLeg");
		this.LeftLowerLeg = this.LeftLeg.getChild("LeftLowerLeg");
		this.LeftFoot = this.LeftLowerLeg.getChild("LeftFoot");
		this.LeftPad = this.LeftFoot.getChild("LeftPad");
		this.Head = root.getChild("Head");
		this.CenterHorns = this.Head.getChild("CenterHorns");
		this.RightHorn = this.Head.getChild("RightHorn");
		this.LeftHorn = this.Head.getChild("LeftHorn");
		this.RightSideHorns = this.Head.getChild("RightSideHorns");
		this.LeftSideHorns = this.Head.getChild("LeftSideHorns");
		this.Torso = root.getChild("Torso");
		this.Tail = this.Torso.getChild("Tail");
		this.TailPrimary = this.Tail.getChild("TailPrimary");
		this.TailSecondary = this.TailPrimary.getChild("TailSecondary");
		this.TailTertiary = this.TailSecondary.getChild("TailTertiary");
		this.TailQuaternary = this.TailTertiary.getChild("TailQuaternary");
		this.TailEnd = this.TailQuaternary.getChild("TailEnd");
		this.bone3 = this.TailEnd.getChild("bone3");
		this.RightArm = root.getChild("RightArm");
		this.LeftArm = root.getChild("LeftArm");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create(), PartPose.offset(-2.5F, 10.5F, 0.0F));

		PartDefinition RightThigh_r1 = RightLeg.addOrReplaceChild("RightThigh_r1", CubeListBuilder.create().texOffs(34, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition RightLowerLeg = RightLeg.addOrReplaceChild("RightLowerLeg", CubeListBuilder.create(), PartPose.offset(0.0F, 6.375F, -3.45F));

		PartDefinition RightCalf_r1 = RightLowerLeg.addOrReplaceChild("RightCalf_r1", CubeListBuilder.create().texOffs(16, 41).addBox(-1.99F, -0.125F, -2.9F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.125F, 1.95F, 0.8727F, 0.0F, 0.0F));

		PartDefinition RightFoot = RightLowerLeg.addOrReplaceChild("RightFoot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.8F, 7.175F));

		PartDefinition RightArch_r1 = RightFoot.addOrReplaceChild("RightArch_r1", CubeListBuilder.create().texOffs(50, 30).addBox(-2.0F, -8.45F, -0.725F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.005F)), PartPose.offsetAndRotation(0.0F, 7.075F, -4.975F, -0.3491F, 0.0F, 0.0F));

		PartDefinition RightPad = RightFoot.addOrReplaceChild("RightPad", CubeListBuilder.create().texOffs(48, 43).addBox(-2.0F, 0.0F, -2.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.325F, -4.425F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create(), PartPose.offset(2.5F, 10.5F, 0.0F));

		PartDefinition LeftThigh_r1 = LeftLeg.addOrReplaceChild("LeftThigh_r1", CubeListBuilder.create().texOffs(40, 9).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition LeftLowerLeg = LeftLeg.addOrReplaceChild("LeftLowerLeg", CubeListBuilder.create(), PartPose.offset(0.0F, 6.375F, -3.45F));

		PartDefinition LeftCalf_r1 = LeftLowerLeg.addOrReplaceChild("LeftCalf_r1", CubeListBuilder.create().texOffs(32, 43).addBox(-2.01F, -0.125F, -2.9F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.125F, 1.95F, 0.8727F, 0.0F, 0.0F));

		PartDefinition LeftFoot = LeftLowerLeg.addOrReplaceChild("LeftFoot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.8F, 7.175F));

		PartDefinition LeftArch_r1 = LeftFoot.addOrReplaceChild("LeftArch_r1", CubeListBuilder.create().texOffs(48, 50).addBox(-2.0F, -8.45F, -0.725F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.005F)), PartPose.offsetAndRotation(0.0F, 7.075F, -4.975F, -0.3491F, 0.0F, 0.0F));

		PartDefinition LeftPad = LeftFoot.addOrReplaceChild("LeftPad", CubeListBuilder.create().texOffs(50, 0).addBox(-2.0F, 0.0F, -2.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.325F, -4.425F));

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(50, 39).addBox(-2.0F, -3.0F, -6.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(40, 30).addBox(-1.5F, -1.0F, -5.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 0.0F));

		PartDefinition CenterHorns = Head.addOrReplaceChild("CenterHorns", CubeListBuilder.create(), PartPose.offset(0.0F, -2.5F, 0.0F));

		PartDefinition Horn_r1 = CenterHorns.addOrReplaceChild("Horn_r1", CubeListBuilder.create().texOffs(56, 64).addBox(-1.0F, -5.4F, -1.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(-0.03F)), PartPose.offsetAndRotation(0.0F, 0.9F, -0.7F, -1.2217F, 0.0F, 0.0F));

		PartDefinition Horn_r2 = CenterHorns.addOrReplaceChild("Horn_r2", CubeListBuilder.create().texOffs(62, 55).addBox(-1.0F, -6.4F, 1.8F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.02F)), PartPose.offsetAndRotation(0.0F, -3.2F, -2.6F, -1.0472F, 0.0F, 0.0F));

		PartDefinition Horn_r3 = CenterHorns.addOrReplaceChild("Horn_r3", CubeListBuilder.create().texOffs(56, 27).addBox(-1.0F, -5.2F, 1.9F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.11F)), PartPose.offsetAndRotation(0.0F, 0.1F, 1.5F, 0.2618F, 0.0F, 0.0F));

		PartDefinition Horn_r4 = CenterHorns.addOrReplaceChild("Horn_r4", CubeListBuilder.create().texOffs(44, 59).addBox(-1.0F, -5.2F, 1.9F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.07F)), PartPose.offsetAndRotation(0.0F, 0.2F, -1.5F, 0.4363F, 0.0F, 0.0F));

		PartDefinition Horn_r5 = CenterHorns.addOrReplaceChild("Horn_r5", CubeListBuilder.create().texOffs(56, 59).addBox(-1.0F, -6.1F, 1.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(-0.02F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.1F, 0.6545F, 0.0F, 0.0F));

		PartDefinition RightHorn = Head.addOrReplaceChild("RightHorn", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.4F, 25.1F, -5.3F, -0.1309F, -0.0436F, 0.0F));

		PartDefinition Horn_r6 = RightHorn.addOrReplaceChild("Horn_r6", CubeListBuilder.create().texOffs(62, 50).addBox(-3.0F, -31.3F, 18.9F, 2.0F, 2.0F, 3.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.48F, -0.1745F, 0.0F));

		PartDefinition Horn_r7 = RightHorn.addOrReplaceChild("Horn_r7", CubeListBuilder.create().texOffs(64, 27).addBox(-3.0F, -35.2F, 8.2F, 2.0F, 2.0F, 3.0F, new CubeDeformation(-0.15F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2182F, -0.1745F, 0.0F));

		PartDefinition Horn_r8 = RightHorn.addOrReplaceChild("Horn_r8", CubeListBuilder.create().texOffs(32, 9).addBox(-3.0F, -30.75F, -19.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5672F, -0.1745F, 0.0F));

		PartDefinition LeftHorn = Head.addOrReplaceChild("LeftHorn", CubeListBuilder.create(), PartPose.offsetAndRotation(0.4F, 25.1F, -5.3F, -0.1309F, 0.0436F, 0.0F));

		PartDefinition Horn_r9 = LeftHorn.addOrReplaceChild("Horn_r9", CubeListBuilder.create().texOffs(0, 63).addBox(1.0F, -31.3F, 18.9F, 2.0F, 2.0F, 3.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.48F, 0.1745F, 0.0F));

		PartDefinition Horn_r10 = LeftHorn.addOrReplaceChild("Horn_r10", CubeListBuilder.create().texOffs(64, 32).addBox(1.0F, -35.2F, 8.2F, 2.0F, 2.0F, 3.0F, new CubeDeformation(-0.15F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2182F, 0.1745F, 0.0F));

		PartDefinition Horn_r11 = LeftHorn.addOrReplaceChild("Horn_r11", CubeListBuilder.create().texOffs(48, 64).addBox(1.0F, -30.75F, -19.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5672F, 0.1745F, 0.0F));

		PartDefinition RightSideHorns = Head.addOrReplaceChild("RightSideHorns", CubeListBuilder.create(), PartPose.offset(0.1F, 25.0F, 0.0F));

		PartDefinition SideHorn_r1 = RightSideHorns.addOrReplaceChild("SideHorn_r1", CubeListBuilder.create().texOffs(38, 64).addBox(0.0F, -0.2F, -0.7F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.16F)), PartPose.offsetAndRotation(-3.8F, -28.275F, -0.3F, -0.6545F, -0.1309F, 0.6545F));

		PartDefinition SideHorn_r2 = RightSideHorns.addOrReplaceChild("SideHorn_r2", CubeListBuilder.create().texOffs(56, 14).addBox(-1.6F, -3.4F, 0.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.04F)), PartPose.offsetAndRotation(-2.7F, -28.0F, -0.7F, 0.1745F, -0.6109F, 0.2618F));

		PartDefinition SideHorn_r3 = RightSideHorns.addOrReplaceChild("SideHorn_r3", CubeListBuilder.create().texOffs(16, 51).addBox(-1.0F, -2.0F, -1.1F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.12F)), PartPose.offsetAndRotation(-3.9F, -27.8F, -0.2F, -0.1745F, -0.6109F, 0.5672F));

		PartDefinition LeftSideHorns = Head.addOrReplaceChild("LeftSideHorns", CubeListBuilder.create(), PartPose.offset(-0.1F, 25.0F, 0.0F));

		PartDefinition SideHorn_r4 = LeftSideHorns.addOrReplaceChild("SideHorn_r4", CubeListBuilder.create().texOffs(30, 53).addBox(-1.0F, -2.0F, -1.1F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.12F)), PartPose.offsetAndRotation(3.9F, -27.8F, -0.2F, -0.1745F, 0.6109F, -0.5672F));

		PartDefinition SideHorn_r5 = LeftSideHorns.addOrReplaceChild("SideHorn_r5", CubeListBuilder.create().texOffs(56, 7).addBox(0.6F, -3.4F, 0.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.04F)), PartPose.offsetAndRotation(2.7F, -28.0F, -0.7F, 0.1745F, 0.6109F, -0.2618F));

		PartDefinition SideHorn_r6 = LeftSideHorns.addOrReplaceChild("SideHorn_r6", CubeListBuilder.create().texOffs(64, 37).addBox(-1.0F, -0.2F, -0.7F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.16F)), PartPose.offsetAndRotation(3.8F, -28.275F, -0.3F, -0.6545F, 0.1309F, -0.6545F));

		PartDefinition Torso = partdefinition.addOrReplaceChild("Torso", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(40, 20).addBox(-3.0F, 0.3F, 1.0F, 6.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 56).addBox(-3.0F, 0.5F, 3.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 0.0F));

		PartDefinition Tail = Torso.addOrReplaceChild("Tail", CubeListBuilder.create(), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition TailPrimary = Tail.addOrReplaceChild("TailPrimary", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Base_r1 = TailPrimary.addOrReplaceChild("Base_r1", CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, -2.9F, 0.4F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition TailSecondary = TailPrimary.addOrReplaceChild("TailSecondary", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 3.5F));

		PartDefinition Base_r2 = TailSecondary.addOrReplaceChild("Base_r2", CubeListBuilder.create().texOffs(32, 0).addBox(-1.5F, -1.4F, -2.7F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 1.0F, 2.5F, -0.3927F, 0.0F, 0.0F));

		PartDefinition TailTertiary = TailSecondary.addOrReplaceChild("TailTertiary", CubeListBuilder.create(), PartPose.offset(0.0F, 2.5F, 5.0F));

		PartDefinition Base_r3 = TailTertiary.addOrReplaceChild("Base_r3", CubeListBuilder.create().texOffs(16, 32).addBox(-1.5F, -13.225F, 6.6F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.32F)), PartPose.offsetAndRotation(0.0F, 10.5F, -8.5F, -0.1309F, 0.0F, 0.0F));

		PartDefinition TailQuaternary = TailTertiary.addOrReplaceChild("TailQuaternary", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, 4.5F));

		PartDefinition Base_r4 = TailQuaternary.addOrReplaceChild("Base_r4", CubeListBuilder.create().texOffs(56, 21).addBox(-1.0F, -10.45F, 13.5F, 2.0F, 2.0F, 4.0F, new CubeDeformation(-0.05F)), PartPose.offsetAndRotation(0.0F, 10.0F, -13.0F, 0.0436F, 0.0F, 0.0F));

		PartDefinition TailEnd = TailQuaternary.addOrReplaceChild("TailEnd", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -0.5F, 4.2F, -0.0873F, 0.0F, 0.0F));

		PartDefinition bone3 = TailEnd.addOrReplaceChild("bone3", CubeListBuilder.create(), PartPose.offset(0.0F, -0.5F, 2.9F));

		PartDefinition cube_r1 = bone3.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(22, 58).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(-0.3F)).mirror(false), PartPose.offsetAndRotation(0.0F, 1.1F, -4.6F, 1.7728F, -0.8625F, -0.1543F));

		PartDefinition cube_r2 = bone3.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(30, 60).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(-0.5F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.9F, -4.6F, 2.2195F, -1.353F, -0.6372F));

		PartDefinition cube_r3 = bone3.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(14, 58).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(-0.3F)).mirror(false), PartPose.offsetAndRotation(0.0F, 1.1F, -4.6F, 1.708F, -0.3027F, -0.0411F));

		PartDefinition cube_r4 = bone3.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(14, 58).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(0.0F, 1.1F, -4.6F, 1.708F, 0.3027F, 0.0411F));

		PartDefinition cube_r5 = bone3.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(30, 60).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, 0.9F, -4.6F, 2.2195F, 1.353F, 0.6372F));

		PartDefinition cube_r6 = bone3.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(22, 58).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(0.0F, 1.1F, -4.6F, 1.7728F, 0.8625F, 0.1543F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(0, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 1.5F, 0.0F));

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(24, 16).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 1.5F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

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