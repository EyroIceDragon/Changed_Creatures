package net.hhdsj.changed_creatures.client.models.armors;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.ltxprogrammer.changed.client.renderer.animate.AnimatorPresets;
import net.ltxprogrammer.changed.client.renderer.animate.HumanoidAnimator;
import net.ltxprogrammer.changed.client.renderer.model.armor.ArmorModel;
import net.ltxprogrammer.changed.client.renderer.model.armor.LatexHumanoidArmorModel;
import net.ltxprogrammer.changed.entity.ChangedEntity;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class GoodBlockModelMaleWingedDragonArmor<T extends ChangedEntity> extends LatexHumanoidArmorModel<T, GoodBlockModelMaleWingedDragonArmor<T>> {
	public static final ModelLayerLocation LAYER_LOCATION =
			ChangedCreature.ChangedCreatureModelResourceLocation("model_unified_male_winged_dragon_armor");
	/*public static final ArmorModelSet<ChangedEntity, GoodBlockModelMaleWingedDragonArmor<ChangedEntity>> MODEL_SET =
			ArmorModelSet.of(Changed.modResource("armor_latex_male_winged_dragon_armor"),
					GoodBlockModelMaleWingedDragonArmor::createBodyLayer,
					GoodBlockModelMaleWingedDragonArmor::new);*/

	public final ModelPart RightLeg;
	public final ModelPart RightLowerLeg;
	public final ModelPart RightFoot;
	public final ModelPart RightPad;
	public final ModelPart LeftLeg;
	public final ModelPart LeftLowerLeg;
	public final ModelPart LeftFoot;
	public final ModelPart LeftPad;
	public final ModelPart Torso;
	public final ModelPart Tail;
	public final ModelPart TailPrimary;
	public final ModelPart TailSecondary;
	public final ModelPart RightWing;
	public final ModelPart rightWingRoot;
	public final ModelPart rightSecondaries;
	public final ModelPart rightTertiaries;
	public final ModelPart LeftWing;
	public final ModelPart leftWingRoot;
	public final ModelPart leftSecondaries;
	public final ModelPart leftTertiaries;
	public final ModelPart Plantoids;
	public final ModelPart Head;
	public final ModelPart RightArm;
	public final ModelPart LeftArm;
	private final HumanoidAnimator<T, GoodBlockModelMaleWingedDragonArmor<T>> animator;

	public GoodBlockModelMaleWingedDragonArmor(ModelPart modelPart, ArmorModel model) {
        super(modelPart,model);
        this.RightLeg = modelPart.getChild("RightLeg");
		this.RightLowerLeg = this.RightLeg.getChild("RightLowerLeg");
		this.RightFoot = this.RightLowerLeg.getChild("RightFoot");
		this.RightPad = this.RightFoot.getChild("RightPad");
		this.LeftLeg = modelPart.getChild("LeftLeg");
		this.LeftLowerLeg = this.LeftLeg.getChild("LeftLowerLeg");
		this.LeftFoot = this.LeftLowerLeg.getChild("LeftFoot");
		this.LeftPad = this.LeftFoot.getChild("LeftPad");
		this.Torso = modelPart.getChild("Torso");
		this.Tail = this.Torso.getChild("Tail");
		this.TailPrimary = this.Tail.getChild("TailPrimary");
		this.TailSecondary = this.TailPrimary.getChild("TailSecondary");
		this.RightWing = this.Torso.getChild("RightWing");
		this.rightWingRoot = this.RightWing.getChild("rightWingRoot");
		this.rightSecondaries = this.rightWingRoot.getChild("rightSecondaries");
		this.rightTertiaries = this.rightSecondaries.getChild("rightTertiaries");
		this.LeftWing = this.Torso.getChild("LeftWing");
		this.leftWingRoot = this.LeftWing.getChild("leftWingRoot");
		this.leftSecondaries = this.leftWingRoot.getChild("leftSecondaries");
		this.leftTertiaries = this.leftSecondaries.getChild("leftTertiaries");
		this.Plantoids = this.Torso.getChild("Plantoids");
		this.Head = modelPart.getChild("Head");
		this.RightArm = modelPart.getChild("RightArm");
		this.LeftArm = modelPart.getChild("LeftArm");

		ModelPart tailPrimary = this.Tail.getChild("TailPrimary");
		ModelPart tailSecondary = tailPrimary.getChild("TailSecondary");
		ModelPart leftLowerLeg = this.LeftLeg.getChild("LeftLowerLeg");
		ModelPart leftFoot = leftLowerLeg.getChild("LeftFoot");
		ModelPart rightLowerLeg = this.RightLeg.getChild("RightLowerLeg");
		ModelPart rightFoot = rightLowerLeg.getChild("RightFoot");
		ModelPart leftWingRoot = this.LeftWing.getChild("leftWingRoot");
		ModelPart rightWingRoot = this.RightWing.getChild("rightWingRoot");

		this.animator = HumanoidAnimator.of(this)
				.hipOffset(-1.5F)
				.addPreset(AnimatorPresets.wingedDragonLike(
						this.Head, this.Torso, this.LeftArm, this.RightArm,
						this.Tail, List.of(tailPrimary, tailSecondary),
						this.LeftLeg, leftLowerLeg, leftFoot, leftFoot.getChild("LeftPad"),
						this.RightLeg, rightLowerLeg, rightFoot, rightFoot.getChild("RightPad"),
						leftWingRoot, leftWingRoot.getChild("leftSecondaries"),
						leftWingRoot.getChild("leftSecondaries").getChild("leftTertiaries"),
						rightWingRoot, rightWingRoot.getChild("rightSecondaries"),
						rightWingRoot.getChild("rightSecondaries").getChild("rightTertiaries")
				));
	}

	@Override
	public void renderForSlot(T entity, RenderLayerParent<? super T, ?> parent, ItemStack stack,
							  EquipmentSlot slot, PoseStack poseStack, VertexConsumer buffer,
							  int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		poseStack.pushPose();
		this.scaleForSlot(parent, slot, poseStack);

		switch (slot) {
			case HEAD:
				this.Head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
				break;
			case CHEST:
				this.Torso.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
				this.LeftArm.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
				this.RightArm.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
				break;
			case LEGS:
				this.Torso.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
				this.LeftLeg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
				this.RightLeg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
				break;
			case FEET:
				this.LeftLeg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
				this.RightLeg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
				break;
			default:
				break;
		}

		poseStack.popPose();
	}

	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create(), PartPose.offset(-2.5F, 10.5F, 0.0F));

		PartDefinition RightThigh_r1 = RightLeg.addOrReplaceChild("RightThigh_r1", CubeListBuilder.create().texOffs(0, 32).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.45F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition cube_r1 = RightLeg.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(24, 45).addBox(1.0F, -8.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.175F, -1.0F, 5.0F, 2.0945F, -0.0151F, -0.0087F));

		PartDefinition cube_r2 = RightLeg.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(6, 52).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 3.25F, -2.175F, -0.2618F, 0.0F, 0.0F));

		PartDefinition RightLowerLeg = RightLeg.addOrReplaceChild("RightLowerLeg", CubeListBuilder.create(), PartPose.offset(0.0F, 6.375F, -3.45F));

		PartDefinition cube_r3 = RightLowerLeg.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(14, 46).addBox(0.0F, -2.0F, -1.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.65F, 0.1F, 2.5F, 0.8772F, -0.1009F, 0.0835F));

		PartDefinition RightCalf_r1 = RightLowerLeg.addOrReplaceChild("RightCalf_r1", CubeListBuilder.create().texOffs(40, 29).mirror().addBox(-1.99F, -0.125F, -2.9F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.45F)).mirror(false), PartPose.offsetAndRotation(0.0F, -2.125F, 1.95F, 0.8727F, 0.0F, 0.0F));

		PartDefinition RightFoot = RightLowerLeg.addOrReplaceChild("RightFoot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.8F, 7.175F));

		PartDefinition cube_r4 = RightFoot.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(50, 7).mirror().addBox(-1.0F, -5.0F, -1.0F, 0.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, -0.5F, -0.5F, -2.3998F, 0.0F, 0.0F));

		PartDefinition RightArch_r1 = RightFoot.addOrReplaceChild("RightArch_r1", CubeListBuilder.create().texOffs(0, 43).addBox(-2.0F, -8.45F, -0.725F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.461F)), PartPose.offsetAndRotation(0.0F, 7.075F, -4.975F, -0.3491F, 0.0F, 0.0F));

		PartDefinition RightPad = RightFoot.addOrReplaceChild("RightPad", CubeListBuilder.create().texOffs(32, 9).mirror().addBox(-2.0F, 0.0F, -2.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.453F)).mirror(false), PartPose.offset(0.0F, 4.325F, -4.425F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create(), PartPose.offset(2.5F, 10.5F, 0.0F));

		PartDefinition LeftThigh_r1 = LeftLeg.addOrReplaceChild("LeftThigh_r1", CubeListBuilder.create().texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition cube_r5 = LeftLeg.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(24, 45).mirror().addBox(-1.0F, -8.0F, -3.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.175F, -1.0F, 5.0F, 2.0945F, 0.0151F, 0.0087F));

		PartDefinition cube_r6 = LeftLeg.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(6, 52).mirror().addBox(0.0F, -2.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.5F, 3.25F, -2.175F, -0.2618F, 0.0F, 0.0F));

		PartDefinition LeftLowerLeg = LeftLeg.addOrReplaceChild("LeftLowerLeg", CubeListBuilder.create(), PartPose.offset(0.0F, 6.375F, -3.45F));

		PartDefinition cube_r7 = LeftLowerLeg.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(14, 46).mirror().addBox(-1.0F, -2.0F, -1.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.65F, 0.1F, 2.5F, 0.8772F, 0.1009F, -0.0835F));

		PartDefinition LeftCalf_r1 = LeftLowerLeg.addOrReplaceChild("LeftCalf_r1", CubeListBuilder.create().texOffs(40, 29).addBox(-2.01F, -0.125F, -2.9F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offsetAndRotation(0.0F, -2.125F, 1.95F, 0.8727F, 0.0F, 0.0F));

		PartDefinition LeftFoot = LeftLowerLeg.addOrReplaceChild("LeftFoot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.8F, 7.175F));

		PartDefinition cube_r8 = LeftFoot.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(50, 7).addBox(1.0F, -5.0F, -1.0F, 0.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -0.5F, -0.5F, -2.3998F, 0.0F, 0.0F));

		PartDefinition LeftArch_r1 = LeftFoot.addOrReplaceChild("LeftArch_r1", CubeListBuilder.create().texOffs(0, 43).mirror().addBox(-2.0F, -8.45F, -0.725F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.45F)).mirror(false), PartPose.offsetAndRotation(0.0F, 7.075F, -4.975F, -0.3491F, 0.0F, 0.0F));

		PartDefinition LeftPad = LeftFoot.addOrReplaceChild("LeftPad", CubeListBuilder.create().texOffs(32, 9).addBox(-2.0F, 0.0F, -2.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.453F)), PartPose.offset(0.0F, 4.325F, -4.425F));

		PartDefinition Torso = partdefinition.addOrReplaceChild("Torso", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.45F))
				.texOffs(38, 47).addBox(-2.0F, 7.1F, -2.225F, 4.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 0.0F));

		PartDefinition cube_r9 = Torso.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(0, 52).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.75F, 8.0F, -1.7F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r10 = Torso.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(24, 16).addBox(-5.0F, -5.0F, -1.0F, 10.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.025F, 3.725F, 0.0654F, 0.0F, 0.0F));

		PartDefinition Tail = Torso.addOrReplaceChild("Tail", CubeListBuilder.create(), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition TailPrimary = Tail.addOrReplaceChild("TailPrimary", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Base_r1 = TailPrimary.addOrReplaceChild("Base_r1", CubeListBuilder.create().texOffs(40, 39).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(0.0F, 0.4206F, 2.5285F, -2.0944F, 0.0F, 0.0F));

		PartDefinition TailSecondary = TailPrimary.addOrReplaceChild("TailSecondary", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 3.5F));

		PartDefinition Base_r2 = TailSecondary.addOrReplaceChild("Base_r2", CubeListBuilder.create().texOffs(32, 0).addBox(-1.5F, -1.5F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 1.2072F, 2.7389F, 2.7489F, 0.0F, 0.0F));

		PartDefinition RightWing = Torso.addOrReplaceChild("RightWing", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.0F, 5.0F, 2.0F, 0.0F, 0.48F, 0.0F));

		PartDefinition rightWingRoot = RightWing.addOrReplaceChild("rightWingRoot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition rightSecondaries = rightWingRoot.addOrReplaceChild("rightSecondaries", CubeListBuilder.create(), PartPose.offsetAndRotation(-7.3F, -7.0F, -0.5F, 0.0F, 0.0F, 0.5236F));

		PartDefinition rightTertiaries = rightSecondaries.addOrReplaceChild("rightTertiaries", CubeListBuilder.create(), PartPose.offsetAndRotation(0.3F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9599F));

		PartDefinition LeftWing = Torso.addOrReplaceChild("LeftWing", CubeListBuilder.create(), PartPose.offsetAndRotation(2.0F, 5.0F, 2.0F, 0.0F, -0.48F, 0.0F));

		PartDefinition leftWingRoot = LeftWing.addOrReplaceChild("leftWingRoot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leftSecondaries = leftWingRoot.addOrReplaceChild("leftSecondaries", CubeListBuilder.create(), PartPose.offsetAndRotation(7.3F, -7.0F, -0.5F, 0.0F, 0.0F, -0.5236F));

		PartDefinition leftTertiaries = leftSecondaries.addOrReplaceChild("leftTertiaries", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.3F, 0.0F, 0.0F, 0.0F, 0.0F, -0.9599F));

		PartDefinition Plantoids = Torso.addOrReplaceChild("Plantoids", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, -2.0F));

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(46, 47).addBox(-1.0F, -9.575F, -4.675F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, -0.5F, 0.0F));

		PartDefinition cube_r11 = Head.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(32, 45).addBox(-1.0F, -6.0F, -1.0F, 0.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.125F, -6.0F, 1.0F, -0.4396F, 0.1186F, -0.0556F));

		PartDefinition cube_r12 = Head.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(32, 45).mirror().addBox(1.0F, -6.0F, -1.0F, 0.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.125F, -6.0F, 1.0F, -0.4396F, -0.1186F, 0.0556F));

		PartDefinition cube_r13 = Head.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(20, 46).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -7.0F, 5.0F, -1.5708F, -1.3788F, 1.5708F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(24, 29).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.451F)), PartPose.offset(-5.0F, 1.5F, 0.0F));

		PartDefinition cube_r14 = RightArm.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(44, 16).mirror().addBox(1.0F, -2.0F, -2.0F, 0.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.075F, 0.25F, 0.0F, 2.3562F, 0.0F, 0.0F));

		PartDefinition cube_r15 = RightArm.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(50, 0).addBox(-1.0F, -5.0F, -1.0F, 0.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, -0.7418F, 0.0F, 0.0F));

		PartDefinition cube_r16 = RightArm.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(16, 32).addBox(1.0F, -2.0F, -2.0F, 0.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.125F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0524F));

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(24, 29).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.451F)).mirror(false), PartPose.offset(5.0F, 1.5F, 0.0F));

		PartDefinition cube_r17 = LeftArm.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(16, 32).mirror().addBox(-1.0F, -2.0F, -2.0F, 0.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.125F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0524F));

		PartDefinition cube_r18 = LeftArm.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(44, 16).addBox(-1.0F, -2.0F, -2.0F, 0.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.05F, 0.25F, 0.0F, 2.3562F, 0.0F, 0.0F));

		PartDefinition cube_r19 = LeftArm.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(50, 0).mirror().addBox(1.0F, -5.0F, -1.0F, 0.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, -0.7418F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		RightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Torso.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public HumanoidAnimator<T, GoodBlockModelMaleWingedDragonArmor<T>> getAnimator(T entity) {
		return this.animator;
	}

	@Override
	public ModelPart getArm(HumanoidArm arm) {
		return arm == HumanoidArm.LEFT ? this.LeftArm : this.RightArm;
	}

	public ModelPart getLeg(HumanoidArm leg) {
		return leg == HumanoidArm.LEFT ? this.LeftLeg : this.RightLeg;
	}

	@Override
	public ModelPart getHead() {
		return this.Head;
	}

	@Override
	public ModelPart getTorso() {
		return this.Torso;
	}

}
