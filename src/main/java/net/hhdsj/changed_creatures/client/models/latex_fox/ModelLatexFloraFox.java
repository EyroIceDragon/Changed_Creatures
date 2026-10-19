package net.hhdsj.changed_creatures.client.models.latex_fox;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.entity.simple.LatexFloraFoxEntity;
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

// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class ModelLatexFloraFox extends AdvancedHumanoidModel<LatexFloraFoxEntity> {//implements AdvancedHumanoidModelInterface<LatexFloraFoxEntity, ModelLatexOrangeFox> {//<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this models's constructor
	public static final ModelLayerLocation LAYER_LOCATION =
			ChangedCreature.ChangedCreatureModelResourceLocation("latex_flora_fox");
	public final ModelPart RightLeg;
	public final ModelPart LeftLeg;
	public final ModelPart Head;
	public final ModelPart Torso;
	public final ModelPart RightArm;
	public final ModelPart LeftArm;
	public final ModelPart Tail;
	public final HumanoidAnimator<LatexFloraFoxEntity, ModelLatexFloraFox> animator;

	public ModelLatexFloraFox(ModelPart root) {
		super(root);
		this.RightLeg = root.getChild("RightLeg");
		this.LeftLeg = root.getChild("LeftLeg");
		this.Head = root.getChild("Head");
		this.Torso = root.getChild("Torso");
		this.RightArm = root.getChild("RightArm");
		this.LeftArm = root.getChild("LeftArm");
		this.Tail = this.Torso.getChild("Tail");

		var tailPrimary = Tail.getChild("TailPrimary");

        var leftLowerLeg = LeftLeg.getChild("LeftLowerLeg");
        var leftFoot = leftLowerLeg.getChild("LeftFoot");
        var rightLowerLeg = RightLeg.getChild("RightLowerLeg");
        var rightFoot = rightLowerLeg.getChild("RightFoot");

        // The animator is a helper class that reduces the duplicate code the many of Changed:MC's entities have.
        // It has quite a few presets to just plug your models's parts into to bring it to life.
        this.animator = HumanoidAnimator.of(this).hipOffset(-1.5f)
                .addPreset(AnimatorPresets.wolfLike(
                        Head, Head.getChild("LeftEar"), Head.getChild("RightEar"),
                        Torso, LeftArm, RightArm,
                        Tail, List.of(tailPrimary),
                        LeftLeg, leftLowerLeg, leftFoot, leftFoot.getChild("LeftPad"), 
                        RightLeg, rightLowerLeg, 
                        rightFoot, 
                        rightFoot.getChild("RightPad")));
	}

	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create(), PartPose.offset(-2.4F, 10.5F, 0.0F));

		PartDefinition RightThigh_r1 = RightLeg.addOrReplaceChild("RightThigh_r1", CubeListBuilder.create().texOffs(52, 59).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(-0.1F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition RightLowerLeg = RightLeg.addOrReplaceChild("RightLowerLeg", CubeListBuilder.create(), PartPose.offset(-0.1F, 6.375F, -3.45F));

		PartDefinition RightCalf_r1 = RightLowerLeg.addOrReplaceChild("RightCalf_r1", CubeListBuilder.create().texOffs(62, 0).addBox(-1.9F, -0.125F, -2.9F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.125F, 1.95F, 0.8727F, 0.0F, 0.0F));

		PartDefinition RightFoot = RightLowerLeg.addOrReplaceChild("RightFoot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.8F, 7.175F));

		PartDefinition RightArch_r1 = RightFoot.addOrReplaceChild("RightArch_r1", CubeListBuilder.create().texOffs(68, 48).addBox(-1.9F, -8.45F, -0.725F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.005F)), PartPose.offsetAndRotation(0.0F, 7.075F, -4.975F, -0.3491F, 0.0F, 0.0F));

		PartDefinition RightPad = RightFoot.addOrReplaceChild("RightPad", CubeListBuilder.create().texOffs(68, 10).addBox(-1.9F, 0.0F, -2.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(44, 73).addBox(-1.625F, 1.325F, -1.8F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(44, 73).addBox(-0.375F, 1.325F, -2.125F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(44, 73).addBox(0.875F, 1.325F, -1.8F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(60, 70).addBox(-0.875F, 1.325F, -0.05F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.325F, -4.425F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create(), PartPose.offset(2.4F, 10.5F, 0.0F));

		PartDefinition LeftThigh_r1 = LeftLeg.addOrReplaceChild("LeftThigh_r1", CubeListBuilder.create().texOffs(52, 48).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.1F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition LeftLowerLeg = LeftLeg.addOrReplaceChild("LeftLowerLeg", CubeListBuilder.create(), PartPose.offset(0.1F, 6.375F, -3.45F));

		PartDefinition LeftCalf_r1 = LeftLowerLeg.addOrReplaceChild("LeftCalf_r1", CubeListBuilder.create().texOffs(32, 61).addBox(-2.1F, -0.125F, -2.9F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.125F, 1.95F, 0.8727F, 0.0F, 0.0F));

		PartDefinition LeftFoot = LeftLowerLeg.addOrReplaceChild("LeftFoot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.8F, 7.175F));

		PartDefinition LeftArch_r1 = LeftFoot.addOrReplaceChild("LeftArch_r1", CubeListBuilder.create().texOffs(18, 68).addBox(-2.1F, -8.45F, -0.725F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.005F)), PartPose.offsetAndRotation(0.0F, 7.075F, -4.975F, -0.3491F, 0.0F, 0.0F));

		PartDefinition LeftPad = LeftFoot.addOrReplaceChild("LeftPad", CubeListBuilder.create().texOffs(0, 68).addBox(-2.1F, 0.0F, -2.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(44, 73).addBox(0.625F, 1.325F, -1.8F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(44, 73).addBox(-0.625F, 1.325F, -2.125F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(44, 73).addBox(-1.875F, 1.325F, -1.8F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(60, 70).addBox(-1.125F, 1.325F, -0.05F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.325F, -4.425F));

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(32, 25).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(64, 37).addBox(-2.0F, -3.0F, -6.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(24, 50).addBox(-1.5F, -1.0F, -5.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Snout_r1 = Head.addOrReplaceChild("Snout_r1", CubeListBuilder.create().texOffs(48, 65).addBox(-1.0F, -29.625F, -0.95F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 26.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition RightEar = Head.addOrReplaceChild("RightEar", CubeListBuilder.create(), PartPose.offset(-3.0F, -7.5F, 0.0F));

		PartDefinition RightEarPivot = RightEar.addOrReplaceChild("RightEarPivot", CubeListBuilder.create().texOffs(24, 46).addBox(-1.9F, -1.2F, -1.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.05F))
				.texOffs(60, 73).addBox(-0.9F, -1.6F, -0.4F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.04F))
				.texOffs(12, 75).addBox(-0.9F, -2.3F, -1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.05F))
				.texOffs(44, 71).addBox(0.1F, -3.1F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.05F)), PartPose.offsetAndRotation(0.5F, -1.25F, 0.0F, -0.1309F, 0.5236F, -0.3491F));

		PartDefinition LeftEar = Head.addOrReplaceChild("LeftEar", CubeListBuilder.create(), PartPose.offset(3.0F, -7.5F, 0.0F));

		PartDefinition LeftEarPivot = LeftEar.addOrReplaceChild("LeftEarPivot", CubeListBuilder.create().texOffs(24, 42).addBox(-1.1F, -1.2F, -1.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.05F))
				.texOffs(62, 10).addBox(-1.1F, -1.6F, -0.4F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.04F))
				.texOffs(58, 23).addBox(-1.1F, -2.3F, -1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.05F))
				.texOffs(48, 67).addBox(-1.1F, -3.1F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.05F)), PartPose.offsetAndRotation(-0.5F, -1.25F, 0.0F, -0.1309F, -0.5236F, 0.3491F));

		PartDefinition Hair = Head.addOrReplaceChild("Hair", CubeListBuilder.create().texOffs(0, 25).addBox(-4.0F, -33.25F, -4.0F, 8.0F, 9.0F, 8.0F, new CubeDeformation(0.35F)), PartPose.offset(0.0F, 25.0F, 0.0F));

		PartDefinition cube_r1 = Hair.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 14).addBox(-5.0F, 0.0F, -5.0F, 11.0F, 0.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -37.25F, -0.5F, 0.1446F, 0.1142F, -0.0324F));

		PartDefinition Torso = partdefinition.addOrReplaceChild("Torso", CubeListBuilder.create().texOffs(44, 14).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 42).addBox(-4.0F, 4.0F, -2.0F, 8.0F, 6.0F, 4.0F, new CubeDeformation(-0.3F))
				.texOffs(52, 41).addBox(-4.0F, 8.6F, -2.0F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r2 = Torso.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(32, 71).addBox(0.25F, -1.7F, -0.8F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.03F)), PartPose.offsetAndRotation(0.0F, 2.0F, -2.0F, -0.2793F, 0.0F, 0.0F));

		PartDefinition cube_r3 = Torso.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(44, 23).addBox(-0.75F, -1.7F, 0.2F, 6.0F, 1.0F, 1.0F, new CubeDeformation(-0.03F)), PartPose.offsetAndRotation(-2.0F, 1.5F, -3.25F, -0.2793F, 0.0F, 0.0F));

		PartDefinition cube_r4 = Torso.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(76, 27).addBox(0.25F, -1.7F, 0.2F, 4.0F, 1.0F, 1.0F, new CubeDeformation(-0.03F)), PartPose.offsetAndRotation(-2.0F, 2.35F, -3.45F, -0.2793F, 0.0F, 0.0F));

		PartDefinition Plantoids = Torso.addOrReplaceChild("Plantoids", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -2.0F));

		PartDefinition RightPlantoid_r1 = Plantoids.addOrReplaceChild("RightPlantoid_r1", CubeListBuilder.create().texOffs(0, 75).addBox(-4.25F, -1.7F, -0.8F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.03F)), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, -0.2793F, 0.0F, 0.0F));

		PartDefinition Center_r1 = Plantoids.addOrReplaceChild("Center_r1", CubeListBuilder.create().texOffs(48, 61).addBox(-0.5F, -1.3F, -0.1F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, -0.192F, 0.0F, 0.0F));

		PartDefinition Tail = Torso.addOrReplaceChild("Tail", CubeListBuilder.create(), PartPose.offset(0.0F, 10.5F, 0.5F));

		PartDefinition TailPrimary = Tail.addOrReplaceChild("TailPrimary", CubeListBuilder.create(), PartPose.offsetAndRotation(8.0F, -2.0F, -6.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r5 = TailPrimary.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(76, 22).mirror().addBox(0.0F, -1.0F, 1.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-12.0F, -1.0F, 17.0F, 0.2934F, -0.3384F, -0.1287F));

		PartDefinition cube_r6 = TailPrimary.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(76, 22).addBox(-1.0F, -1.0F, 1.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 2.0F, 17.0F, -0.1191F, -0.0104F, 0.0255F));

		PartDefinition cube_r7 = TailPrimary.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(76, 22).addBox(-1.0F, -1.0F, 1.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, -3.75F, 16.0F, 0.6407F, 0.0194F, 0.0083F));

		PartDefinition cube_r8 = TailPrimary.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(76, 22).addBox(-1.0F, -1.0F, 1.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.75F, -1.0F, 17.0F, 0.2919F, 0.3396F, 0.1242F));

		PartDefinition cube_r9 = TailPrimary.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(68, 57).addBox(-3.0F, -8.0F, 0.0F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.275F, 0.425F, 5.25F, -1.2631F, 0.3701F, 0.1609F));

		PartDefinition cube_r10 = TailPrimary.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(48, 70).addBox(-3.0F, -7.0F, 0.0F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.45F, 1.2F, 7.0F, -1.7646F, 0.0442F, -0.0123F));

		PartDefinition cube_r11 = TailPrimary.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, 0.0F, -8.0F, 17.0F, 0.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.025F, -1.2F, 17.25F, -1.3382F, -0.0174F, 0.0188F));

		PartDefinition cube_r12 = TailPrimary.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(32, 41).addBox(-4.0F, -15.0F, -1.0F, 5.0F, 15.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.775F, -0.45F, 6.5F, -1.3382F, -0.0174F, 0.0188F));

		PartDefinition cube_r13 = TailPrimary.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(68, 68).addBox(-3.0F, -8.0F, 0.0F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.675F, -1.8F, 6.25F, -0.9701F, -0.0125F, 0.0116F));

		PartDefinition cube_r14 = TailPrimary.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(64, 23).addBox(-3.0F, -11.0F, 0.0F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.525F, 0.8F, 7.0F, -1.1981F, -0.4183F, -0.023F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(16, 52).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(44, 73).addBox(-2.75F, 9.325F, -1.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(44, 73).addBox(-1.5F, 9.325F, -1.825F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(44, 73).addBox(-0.25F, 9.325F, -1.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(60, 70).addBox(-2.0F, 9.325F, -0.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition cube_r15 = RightArm.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(68, 17).mirror().addBox(-4.0F, 0.0F, -2.0F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.75F, -0.25F, 1.5F, 3.1416F, -0.829F, -1.5708F));

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(0, 52).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(60, 70).addBox(0.0F, 9.325F, -0.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(44, 73).addBox(-0.75F, 9.325F, -1.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(44, 73).addBox(0.5F, 9.325F, -1.825F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(44, 73).addBox(1.75F, 9.325F, -1.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition cube_r16 = LeftArm.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(68, 17).addBox(-1.0F, 0.0F, -2.0F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.75F, -0.25F, 1.5F, 3.1416F, 0.829F, 1.5708F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

    public void setupHand(LatexFloraFoxEntity entity) {
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
    public HumanoidAnimator<LatexFloraFoxEntity, ModelLatexFloraFox> getAnimator(LatexFloraFoxEntity entity) {
        return this.animator;
    }

    public void setupAnim(@NotNull LatexFloraFoxEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
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
