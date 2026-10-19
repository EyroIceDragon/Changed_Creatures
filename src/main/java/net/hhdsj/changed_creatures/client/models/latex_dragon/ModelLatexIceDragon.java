package net.hhdsj.changed_creatures.client.models.latex_dragon;


import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.entity.simple.LatexiceDragonEntity;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.ltxprogrammer.changed.client.renderer.animate.AnimatorPresets;
import net.ltxprogrammer.changed.client.renderer.animate.HumanoidAnimator;

import net.minecraft.world.entity.HumanoidArm;
//Changed-mc mod import 我的世界转变MOD导入
import net.ltxprogrammer.changed.client.renderer.model.AdvancedHumanoidModel;


//Goodblock mod import

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ModelLatexIceDragon extends AdvancedHumanoidModel<LatexiceDragonEntity>{
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this models's constructor
    public static final ModelLayerLocation LAYER_LOCATION =
            ChangedCreature.ChangedCreatureModelResourceLocation("latex_ice_dragon");
    private final ModelPart LeftLeg;
    public final ModelPart RightLeg;
    private final ModelPart RightArm;
    private final ModelPart LeftArm;
    private final ModelPart Head;
    private final ModelPart Torso;
    private final ModelPart Tail;
    private final HumanoidAnimator<LatexiceDragonEntity, ModelLatexIceDragon> animator;

    public ModelLatexIceDragon(ModelPart root) {
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

        PartDefinition RightCalf_r1 = RightLowerLeg.addOrReplaceChild("RightCalf_r1", CubeListBuilder.create().texOffs(32, 43).addBox(-1.99F, -0.125F, -2.9F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.125F, 1.95F, 0.8727F, 0.0F, 0.0F));

        PartDefinition RightFoot = RightLowerLeg.addOrReplaceChild("RightFoot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.8F, 7.175F));

        PartDefinition RightArch_r1 = RightFoot.addOrReplaceChild("RightArch_r1", CubeListBuilder.create().texOffs(48, 45).addBox(-2.0F, -8.45F, -0.725F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.005F)), PartPose.offsetAndRotation(0.0F, 7.075F, -4.975F, -0.3491F, 0.0F, 0.0F));

        PartDefinition RightPad = RightFoot.addOrReplaceChild("RightPad", CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, 0.0F, -2.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.325F, -4.425F));

        PartDefinition bone3 = RightLeg.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(20, 94).addBox(0.0F, 9.2F, -1.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(16, 94).addBox(-1.0F, 9.2F, -1.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(12, 94).addBox(-2.0F, 9.2F, -1.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(8, 94).addBox(-3.0F, 9.2F, -1.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(0, 93).addBox(-2.0F, 9.1F, -0.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 3.5F, -1.3F));

        PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create(), PartPose.offset(2.5F, 10.5F, 0.0F));

        PartDefinition LeftThigh_r1 = LeftLeg.addOrReplaceChild("LeftThigh_r1", CubeListBuilder.create().texOffs(16, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

        PartDefinition LeftLowerLeg = LeftLeg.addOrReplaceChild("LeftLowerLeg", CubeListBuilder.create(), PartPose.offset(0.0F, 6.375F, -3.45F));

        PartDefinition LeftCalf_r1 = LeftLowerLeg.addOrReplaceChild("LeftCalf_r1", CubeListBuilder.create().texOffs(16, 43).addBox(-2.01F, -0.125F, -2.9F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.125F, 1.95F, 0.8727F, 0.0F, 0.0F));

        PartDefinition LeftFoot = LeftLowerLeg.addOrReplaceChild("LeftFoot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.8F, 7.175F));

        PartDefinition LeftArch_r1 = LeftFoot.addOrReplaceChild("LeftArch_r1", CubeListBuilder.create().texOffs(52, 0).addBox(-2.0F, -8.45F, -0.725F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.005F)), PartPose.offsetAndRotation(0.0F, 7.075F, -4.975F, -0.3491F, 0.0F, 0.0F));

        PartDefinition LeftPad = LeftFoot.addOrReplaceChild("LeftPad", CubeListBuilder.create().texOffs(44, 38).addBox(-2.0F, 0.0F, -2.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.325F, -4.425F));

        PartDefinition bone4 = LeftLeg.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(20, 85).addBox(0.0F, 9.2F, -1.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(16, 85).addBox(-1.0F, 9.2F, -1.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(12, 85).addBox(-2.0F, 9.2F, -1.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(8, 85).addBox(-3.0F, 9.2F, -1.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(0, 84).addBox(-2.0F, 9.1F, -0.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 3.5F, -1.3F));

        PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(44, 31).addBox(-2.0F, -3.0F, -6.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(20, 16).addBox(-1.5F, -1.0F, -5.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 0.0F));

        PartDefinition RightHorns = Head.addOrReplaceChild("RightHorns", CubeListBuilder.create(), PartPose.offset(0.0F, 0.7F, -3.0F));

        PartDefinition Horn_r1 = RightHorns.addOrReplaceChild("Horn_r1", CubeListBuilder.create().texOffs(1, 55).addBox(-1.7F, -9.0F, -5.25F, 1.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -2.0F, 3.0F, -0.9599F, -0.3927F, -0.3054F));

        PartDefinition Horn_r2 = RightHorns.addOrReplaceChild("Horn_r2", CubeListBuilder.create().texOffs(0, 55).addBox(-2.7F, -9.0F, -5.25F, 2.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -3.0F, 3.0F, -0.9599F, -0.3927F, -0.3054F));

        PartDefinition LeftHorns = Head.addOrReplaceChild("LeftHorns", CubeListBuilder.create(), PartPose.offset(0.0F, 0.7F, -3.0F));

        PartDefinition Horn_r3 = LeftHorns.addOrReplaceChild("Horn_r3", CubeListBuilder.create().texOffs(11, 55).addBox(1.7F, -9.0F, -5.25F, 1.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -2.0F, 3.0F, -0.9599F, 0.3927F, 0.3054F));

        PartDefinition Horn_r4 = LeftHorns.addOrReplaceChild("Horn_r4", CubeListBuilder.create().texOffs(10, 55).addBox(0.7F, -9.0F, -5.25F, 2.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -3.0F, 3.0F, -0.9599F, 0.3927F, 0.3054F));

        PartDefinition LeftHorn = Head.addOrReplaceChild("LeftHorn", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.5F, 17.0F, -2.0F, 0.0F, 0.0F, 0.0436F));

        PartDefinition Horn_r5 = LeftHorn.addOrReplaceChild("Horn_r5", CubeListBuilder.create().texOffs(86, 37).addBox(1.0F, -31.3F, 18.8F, 2.0F, 2.0F, 3.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.3F, 7.0F, 1.8F, 0.48F, 0.1745F, 0.0F));

        PartDefinition Horn_r6 = LeftHorn.addOrReplaceChild("Horn_r6", CubeListBuilder.create().texOffs(86, 32).addBox(1.0F, -31.3F, 18.8F, 2.0F, 2.0F, 3.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(0.0F, 8.0F, 0.0F, 0.48F, 0.1745F, 0.0F));

        PartDefinition Horn_r7 = LeftHorn.addOrReplaceChild("Horn_r7", CubeListBuilder.create().texOffs(86, 27).addBox(1.0F, -35.2F, 8.1F, 2.0F, 2.0F, 3.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(0.0F, 8.0F, 0.0F, 0.2182F, 0.1745F, 0.0F));

        PartDefinition Horn_r8 = LeftHorn.addOrReplaceChild("Horn_r8", CubeListBuilder.create().texOffs(88, 21).addBox(1.0F, -30.75F, -19.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, 8.0F, 0.0F, -0.5672F, 0.1745F, 0.0F));

        PartDefinition RightHorn2 = Head.addOrReplaceChild("RightHorn2", CubeListBuilder.create(), PartPose.offsetAndRotation(1.5F, 17.0F, -2.0F, 0.0F, 0.0F, -0.0436F));

        PartDefinition Horn_r9 = RightHorn2.addOrReplaceChild("Horn_r9", CubeListBuilder.create().texOffs(86, 16).mirror().addBox(-3.0F, -31.3F, 18.8F, 2.0F, 2.0F, 3.0F, new CubeDeformation(-0.5F)).mirror(false), PartPose.offsetAndRotation(-0.3F, 7.0F, 1.8F, 0.48F, -0.1745F, 0.0F));

        PartDefinition Horn_r10 = RightHorn2.addOrReplaceChild("Horn_r10", CubeListBuilder.create().texOffs(86, 11).mirror().addBox(-3.0F, -31.3F, 18.8F, 2.0F, 2.0F, 3.0F, new CubeDeformation(-0.3F)).mirror(false), PartPose.offsetAndRotation(0.0F, 8.0F, 0.0F, 0.48F, -0.1745F, 0.0F));

        PartDefinition Horn_r11 = RightHorn2.addOrReplaceChild("Horn_r11", CubeListBuilder.create().texOffs(86, 6).mirror().addBox(-3.0F, -35.2F, 8.1F, 2.0F, 2.0F, 3.0F, new CubeDeformation(-0.25F)).mirror(false), PartPose.offsetAndRotation(0.0F, 8.0F, 0.0F, 0.2182F, -0.1745F, 0.0F));

        PartDefinition Horn_r12 = RightHorn2.addOrReplaceChild("Horn_r12", CubeListBuilder.create().texOffs(88, 0).mirror().addBox(-3.0F, -30.75F, -19.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(-0.1F)).mirror(false), PartPose.offsetAndRotation(0.0F, 8.0F, 0.0F, -0.5672F, -0.1745F, 0.0F));

        PartDefinition Torso = partdefinition.addOrReplaceChild("Torso", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 0.0F));

        PartDefinition Tail = Torso.addOrReplaceChild("Tail", CubeListBuilder.create(), PartPose.offset(0.0F, 10.5F, 0.0F));

        PartDefinition TailPrimary = Tail.addOrReplaceChild("TailPrimary", CubeListBuilder.create(), PartPose.offset(0.0F, -0.5F, 0.0F));

        PartDefinition TailBase_r1 = TailPrimary.addOrReplaceChild("TailBase_r1", CubeListBuilder.create().texOffs(32, 0).addBox(-2.5F, -8.25F, 11.6F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.18F)), PartPose.offsetAndRotation(0.0F, 16.0F, 0.0F, 1.0036F, 0.0F, 0.0F));

        PartDefinition TailSecondary = TailPrimary.addOrReplaceChild("TailSecondary", CubeListBuilder.create(), PartPose.offset(0.0F, 1.5F, 3.5F));

        PartDefinition TailBase_r2 = TailSecondary.addOrReplaceChild("TailBase_r2", CubeListBuilder.create().texOffs(40, 20).addBox(-2.0F, -1.75F, 12.6F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(0.0F, 14.5F, -3.5F, 1.1781F, 0.0F, 0.0F));

        PartDefinition TailTertiary = TailSecondary.addOrReplaceChild("TailTertiary", CubeListBuilder.create(), PartPose.offset(0.0F, 3.0F, 6.5F));

        PartDefinition TailBase_r3 = TailTertiary.addOrReplaceChild("TailBase_r3", CubeListBuilder.create().texOffs(36, 9).addBox(-2.0F, 9.0F, 10.3F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.5F, -10.0F, 1.4835F, 0.0F, 0.0F));

        PartDefinition TailQuaternary = TailTertiary.addOrReplaceChild("TailQuaternary", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 5.5F));

        PartDefinition TailBase_r4 = TailQuaternary.addOrReplaceChild("TailBase_r4", CubeListBuilder.create().texOffs(52, 9).addBox(-1.5F, 17.3F, 7.1F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.15F)), PartPose.offsetAndRotation(0.0F, 10.5F, -15.5F, 1.7017F, 0.0F, 0.0F));

        PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(24, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(16, 66).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(-5.0F, 1.5F, 0.0F));

        PartDefinition bone = RightArm.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(20, 91).addBox(0.0F, 9.2F, -1.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(16, 91).addBox(-1.0F, 9.2F, -1.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(12, 91).addBox(-2.0F, 9.2F, -1.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(8, 91).addBox(-3.0F, 9.2F, -1.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(0, 90).addBox(-2.0F, 9.1F, -0.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(0, 32).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 66).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offset(5.0F, 1.5F, 0.0F));

        PartDefinition bone2 = LeftArm.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(20, 88).addBox(0.0F, 9.2F, -1.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(16, 88).addBox(-1.0F, 9.2F, -1.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(12, 88).addBox(-2.0F, 9.2F, -1.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(8, 88).addBox(-3.0F, 9.2F, -1.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(0, 87).addBox(-2.0F, 9.1F, -0.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 0.0F, 0.0F));

        PartDefinition RightHorn = partdefinition.addOrReplaceChild("RightHorn", CubeListBuilder.create(), PartPose.offsetAndRotation(0.5F, 16.5F, -6.0F, 0.0F, 0.0F, -0.0436F));

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition TailBase_r5 = bb_main.addOrReplaceChild("TailBase_r5", CubeListBuilder.create().texOffs(64, 9).addBox(-1.5F, 17.3F, 7.1F, 3.0F, 6.0F, 3.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, 0.9F, 5.0F, 1.7017F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 96, 96);
    }

    public void setupHand(LatexiceDragonEntity entity) {
        animator.setupHand();
    }

    @Override
    public void setupAnim(@NotNull LatexiceDragonEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
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
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        RightLeg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        LeftLeg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        Head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        Torso.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        RightArm.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        LeftArm.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public HumanoidAnimator<LatexiceDragonEntity, ModelLatexIceDragon> getAnimator(LatexiceDragonEntity entity) {
        return animator;
    }
}