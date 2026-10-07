package net.hhdsj.changed_creatures.client.renderer.animate;

import net.hhdsj.changed_creatures.client.renderer.animate.wing.DragonOnlyRootWingCreativeFlyAnimator;
import net.hhdsj.changed_creatures.client.renderer.animate.wing.DragonOnlyRootWingFallFlyAnimator;
import net.hhdsj.changed_creatures.client.renderer.animate.wing.DragonOnlyRootWingInitAnimator;
import net.hhdsj.changed_creatures.client.renderer.animate.wing.FrostScaleDragonTaurWingInitAnimator;
import net.ltxprogrammer.changed.client.renderer.animate.AnimatorPresets;
import net.ltxprogrammer.changed.client.renderer.animate.HumanoidAnimator;
import net.ltxprogrammer.changed.client.renderer.animate.arm.ArmBobAnimator;
import net.ltxprogrammer.changed.client.renderer.animate.arm.ArmRideAnimator;
import net.ltxprogrammer.changed.client.renderer.animate.arm.ArmSwimAnimator;
import net.ltxprogrammer.changed.client.renderer.animate.arm.DoubleArmBobAnimator;
import net.ltxprogrammer.changed.client.renderer.animate.bipedal.DragonBipedalCreativeFlyAnimator;
import net.ltxprogrammer.changed.client.renderer.animate.camera.DragonCameraCreativeFlyAnimator;
import net.ltxprogrammer.changed.client.renderer.animate.tail.DragonTailCreativeFlyAnimator;
import net.ltxprogrammer.changed.client.renderer.animate.upperbody.DragonHeadCreativeFlyAnimator;
import net.ltxprogrammer.changed.client.renderer.animate.upperbody.DragonHeadInitAnimator;
import net.ltxprogrammer.changed.client.renderer.animate.upperbody.WolfHeadInitAnimator;
import net.ltxprogrammer.changed.client.renderer.animate.wing.DragonWingCreativeFlyAnimator;
import net.ltxprogrammer.changed.client.renderer.animate.wing.DragonWingFallFlyAnimator;
import net.ltxprogrammer.changed.client.renderer.animate.wing.DragonWingInitAnimator;
import net.ltxprogrammer.changed.client.renderer.model.AdvancedHumanoidModel;
import net.ltxprogrammer.changed.entity.ChangedEntity;
import net.minecraft.client.model.geom.ModelPart;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

public class GoodBlockAnimatorPresets extends AnimatorPresets {
    public GoodBlockAnimatorPresets() {

    }

    public static <T extends ChangedEntity, M extends AdvancedHumanoidModel<T>> @NotNull Consumer<HumanoidAnimator<T, M>>
    dragontaurLike(ModelPart head, ModelPart leftEar, ModelPart rightEar, ModelPart torso, ModelPart leftArm, ModelPart rightArm,
                   ModelPart lowerTorso, ModelPart frontLeftLeg, ModelPart frontLeftLegLower, ModelPart frontLeftFoot,
                   ModelPart frontRightLeg, ModelPart frontRightLegLower, ModelPart frontRightFoot, ModelPart backLeftLeg,
                   ModelPart backLeftLegLower, ModelPart backLeftFoot, ModelPart backLeftPad, ModelPart backRightLeg,
                   ModelPart backRightLegLower, ModelPart backRightFoot, ModelPart backRightPad, ModelPart leftWingRoot,
                   ModelPart leftWingBone1, ModelPart leftWingBone2, ModelPart rightWingRoot, ModelPart rightWingBone1, ModelPart rightWingBone2,
                   ModelPart tail, List<ModelPart> tailJoints) {
        return (animator) -> {
            animator.addPreset(
                    taurLegs(lowerTorso, frontLeftLeg, frontLeftLegLower, frontLeftFoot,
                    frontRightLeg, frontRightLegLower, frontRightFoot, backLeftLeg,
                    backLeftLegLower, backLeftFoot, backLeftPad, backRightLeg,
                    backRightLegLower, backRightFoot, backRightPad)
                    )
                    .addPreset(taurUpperBody(head, torso, leftArm, rightArm))
                    .addPreset(dragonTail(tail, tailJoints))
                    .addPreset(dragonWinged(leftWingRoot, leftWingBone1, leftWingBone2, rightWingRoot, rightWingBone1, rightWingBone2))
                    .addAnimator(new WolfHeadInitAnimator<>(head))
                    .addAnimator(new ArmSwimAnimator<>(leftArm, rightArm))
                    .addAnimator(new ArmBobAnimator<>(leftArm, rightArm))
                    .addAnimator(new ArmRideAnimator<>(leftArm, rightArm));

        };
    }

    public static <T extends ChangedEntity, M extends AdvancedHumanoidModel<T>> @NotNull Consumer<HumanoidAnimator<T, M>>
    LesstaurLike(ModelPart head, ModelPart torso, ModelPart leftArm, ModelPart rightArm,
                   ModelPart lowerTorso, ModelPart frontLeftLeg, ModelPart frontLeftLegLower, ModelPart frontLeftFoot,
                   ModelPart frontRightLeg, ModelPart frontRightLegLower, ModelPart frontRightFoot, ModelPart backLeftLeg,
                   ModelPart backLeftLegLower, ModelPart backLeftFoot, ModelPart backLeftPad, ModelPart backRightLeg,
                   ModelPart backRightLegLower, ModelPart backRightFoot, ModelPart backRightPad,
                   ModelPart tail, List<ModelPart> tailJoints) {
        return (animator) -> {
            animator.addPreset(
                            taurLegs(lowerTorso, frontLeftLeg, frontLeftLegLower, frontLeftFoot,
                                    frontRightLeg, frontRightLegLower, frontRightFoot, backLeftLeg,
                                    backLeftLegLower, backLeftFoot, backLeftPad, backRightLeg,
                                    backRightLegLower, backRightFoot, backRightPad)
                    )
                    .addPreset(taurUpperBody(head, torso, leftArm, rightArm))
                    .addPreset(dragonTail(tail, tailJoints))
                    .addAnimator(new WolfHeadInitAnimator<>(head))
                    .addAnimator(new ArmSwimAnimator<>(leftArm, rightArm))
                    .addAnimator(new ArmBobAnimator<>(leftArm, rightArm))
                    .addAnimator(new ArmRideAnimator<>(leftArm, rightArm));

        };
    }

    public static <T extends ChangedEntity, M extends AdvancedHumanoidModel<T>> @NotNull Consumer<HumanoidAnimator<T, M>>
    WingedWolfDragonLike(ModelPart head, ModelPart torso, ModelPart leftArm, ModelPart rightArm,
                     ModelPart tail, List<ModelPart> tailJoints, ModelPart leftLeg, ModelPart leftLegLower,
                     ModelPart leftFoot, ModelPart leftPad, ModelPart rightLeg, ModelPart rightLegLower,
                     ModelPart rightFoot, ModelPart rightPad, ModelPart leftWingRoot, ModelPart leftWingBone1,
                     ModelPart leftWingBone2, ModelPart rightWingRoot, ModelPart rightWingBone1, ModelPart rightWingBone2) {
        return (animator) -> {
            animator.addPreset(dragonBipedal(leftLeg, leftLegLower, leftFoot, leftPad,
                            rightLeg, rightLegLower, rightFoot, rightPad))
                    .addPreset(dragonWingedUpperBody(head, torso, leftArm, rightArm))
                    .addPreset(wolfTail(tail, tailJoints))
                    .addPreset(dragonWinged(leftWingRoot, leftWingBone1, leftWingBone2,
                            rightWingRoot, rightWingBone1, rightWingBone2))
                    .addAnimator(new DragonBipedalCreativeFlyAnimator<>(leftLeg, leftLegLower, leftFoot, leftPad,
                            rightLeg, rightLegLower, rightFoot, rightPad))
                    .addAnimator(new DragonTailCreativeFlyAnimator<>(tail, tailJoints))
                    .addAnimator(new DragonHeadCreativeFlyAnimator<>(head))
                    .addAnimator(new DragonHeadInitAnimator<>(head))
                    .addAnimator(new ArmSwimAnimator<>(leftArm, rightArm))
                    .addAnimator(new ArmBobAnimator<>(leftArm, rightArm))
                    .addAnimator(new ArmRideAnimator<>(leftArm, rightArm))
                    .addCameraAnimator(new DragonCameraCreativeFlyAnimator<>());
        };
    }

    public static <T extends ChangedEntity, M extends AdvancedHumanoidModel<T>> @NotNull Consumer<HumanoidAnimator<T, M>>
    WingedWolfDragonArmor(ModelPart head, ModelPart torso, ModelPart leftArm, ModelPart rightArm,
                          ModelPart leftLeg, ModelPart leftLegLower,
                         ModelPart leftFoot, ModelPart leftPad, ModelPart rightLeg, ModelPart rightLegLower,
                         ModelPart rightFoot, ModelPart rightPad, ModelPart leftWingRoot, ModelPart leftWingBone1,
                         ModelPart leftWingBone2, ModelPart rightWingRoot, ModelPart rightWingBone1, ModelPart rightWingBone2) {
        return (animator) -> {
            animator.addPreset(dragonBipedal(leftLeg, leftLegLower, leftFoot, leftPad,
                            rightLeg, rightLegLower, rightFoot, rightPad))
                    .addPreset(dragonWingedUpperBody(head, torso, leftArm, rightArm))
                    .addPreset(dragonWinged(leftWingRoot, leftWingBone1, leftWingBone2,
                            rightWingRoot, rightWingBone1, rightWingBone2))
                    .addAnimator(new DragonBipedalCreativeFlyAnimator<>(leftLeg, leftLegLower, leftFoot, leftPad,
                            rightLeg, rightLegLower, rightFoot, rightPad))
                    .addAnimator(new DragonHeadCreativeFlyAnimator<>(head))
                    .addAnimator(new DragonHeadInitAnimator<>(head))
                    .addAnimator(new ArmSwimAnimator<>(leftArm, rightArm))
                    .addAnimator(new ArmBobAnimator<>(leftArm, rightArm))
                    .addAnimator(new ArmRideAnimator<>(leftArm, rightArm))
                    .addCameraAnimator(new DragonCameraCreativeFlyAnimator<>());
        };
    }

    public static <T extends ChangedEntity, M extends AdvancedHumanoidModel<T>> @NotNull Consumer<HumanoidAnimator<T, M>>
    FrostScaleDragonTaurLike(ModelPart head, ModelPart leftEar, ModelPart rightEar, ModelPart torso, ModelPart leftArm, ModelPart rightArm,
                   ModelPart lowerTorso, ModelPart frontLeftLeg, ModelPart frontLeftLegLower, ModelPart frontLeftFoot,
                   ModelPart frontRightLeg, ModelPart frontRightLegLower, ModelPart frontRightFoot, ModelPart backLeftLeg,
                   ModelPart backLeftLegLower, ModelPart backLeftFoot, ModelPart backLeftPad, ModelPart backRightLeg,
                   ModelPart backRightLegLower, ModelPart backRightFoot, ModelPart backRightPad, ModelPart leftWingRoot, ModelPart leftWingBone1, ModelPart leftWingBone2,ModelPart leftWingBone3,
                             ModelPart rightWingRoot, ModelPart rightWingBone1, ModelPart rightWingBone2,ModelPart rightWingBone3,
                   ModelPart tail, List<ModelPart> tailJoints) {
        return (animator) -> {
            animator.addPreset(
                            taurLegs(lowerTorso, frontLeftLeg, frontLeftLegLower, frontLeftFoot,
                                    frontRightLeg, frontRightLegLower, frontRightFoot, backLeftLeg,
                                    backLeftLegLower, backLeftFoot, backLeftPad, backRightLeg,
                                    backRightLegLower, backRightFoot, backRightPad)
                    )
                    .addPreset(taurUpperBody(head, torso, leftArm, rightArm))
                    .addPreset(dragonTail(tail, tailJoints))
                    .addPreset(FrostScaleTaurDragonWinged(leftWingRoot,leftWingBone1,leftWingBone2,leftWingBone3, rightWingRoot,rightWingBone1,rightWingBone2,rightWingBone3))
                    .addAnimator(new WolfHeadInitAnimator<>(head))
                    .addAnimator(new ArmSwimAnimator<>(leftArm, rightArm))
                    .addAnimator(new ArmBobAnimator<>(leftArm, rightArm))
                    .addAnimator(new ArmRideAnimator<>(leftArm, rightArm));

        };
    }

    public static <T extends ChangedEntity, M extends AdvancedHumanoidModel<T>> Consumer<HumanoidAnimator<T, M>>
    ProtogenNyxarixWaterLike(ModelPart head, ModelPart leftEar, ModelPart rightEar, ModelPart torso,
                        ModelPart upperLeftArm, ModelPart upperRightArm, ModelPart lowerLeftArm,
                        ModelPart lowerRightArm, ModelPart tail, List<ModelPart> tailJoints,
                        List<ModelPart> upperLeftTentacle, List<ModelPart> upperRightTentacle,
                        List<ModelPart> lowerLeftTentacle, List<ModelPart> lowerRightTentacle,
                        ModelPart leftLeg, ModelPart leftLegLower, ModelPart leftFoot,
                        ModelPart leftPad, ModelPart rightLeg, ModelPart rightLegLower,
                        ModelPart rightFoot, ModelPart rightPad) {
        return (animator) ->
                animator
                .addPreset(wolfBipedal(leftLeg, leftLegLower, leftFoot, leftPad, rightLeg, rightLegLower, rightFoot, rightPad))
                .addPreset(doubleArmUpperBody(head, torso, upperLeftArm, upperRightArm, lowerLeftArm, lowerRightArm))
                .addPreset(sharkTail(tail,tailJoints))
                .addPreset(wolfEars(leftEar, rightEar))
                .addPreset(squidDogTentacles(upperLeftTentacle, upperRightTentacle, lowerLeftTentacle, lowerRightTentacle))
                .addAnimator(new WolfHeadInitAnimator<>(head))
                .addAnimator(new ArmSwimAnimator<>(upperLeftArm, upperRightArm))
                .addAnimator(new DoubleArmBobAnimator<>(upperLeftArm, upperRightArm, lowerLeftArm, lowerRightArm))
                .addAnimator(new ArmRideAnimator<>(upperLeftArm, upperRightArm));
    }

    public static <T extends ChangedEntity, M extends AdvancedHumanoidModel<T>> Consumer<HumanoidAnimator<T, M>> wingedonlyrootDragonLike(ModelPart head, ModelPart torso, ModelPart leftArm, ModelPart rightArm, ModelPart tail, List<ModelPart> tailJoints, ModelPart leftLeg, ModelPart leftLegLower, ModelPart leftFoot, ModelPart leftPad, ModelPart rightLeg, ModelPart rightLegLower, ModelPart rightFoot, ModelPart rightPad, ModelPart leftWingRoot, ModelPart rightWingRoot) {
        return (animator) ->
                animator
                .addPreset(dragonBipedal(leftLeg, leftLegLower, leftFoot, leftPad,
                        rightLeg, rightLegLower, rightFoot, rightPad))
                .addPreset(dragonWingedUpperBody(head, torso, leftArm, rightArm))
                .addPreset(dragonTail(tail, tailJoints))
                .addPreset(dragononlyrootWinged(leftWingRoot ,rightWingRoot))
                .addAnimator(new DragonBipedalCreativeFlyAnimator<>(leftLeg, leftLegLower,
                        leftFoot, leftPad,
                        rightLeg, rightLegLower,
                        rightFoot, rightPad))
                .addAnimator(new DragonTailCreativeFlyAnimator<>(tail, tailJoints))
                .addAnimator(new DragonHeadCreativeFlyAnimator<>(head))
                .addAnimator(new DragonHeadInitAnimator<>(head))
                .addAnimator(new ArmSwimAnimator<>(leftArm, rightArm))
                .addAnimator(new ArmBobAnimator<>(leftArm, rightArm))
                .addAnimator(new ArmRideAnimator<>(leftArm, rightArm))
                .addCameraAnimator(new DragonCameraCreativeFlyAnimator<>());
    }
    public static <T extends ChangedEntity, M extends AdvancedHumanoidModel<T>> Consumer<HumanoidAnimator<T, M>> dragononlyrootWinged(ModelPart leftWingRoot, ModelPart rightWingRoot) {
        return (animator) -> animator
                .addAnimator(new DragonOnlyRootWingInitAnimator<>(leftWingRoot, rightWingRoot))
                .addAnimator(new DragonOnlyRootWingCreativeFlyAnimator<>(leftWingRoot, rightWingRoot))
                .addAnimator(new DragonOnlyRootWingFallFlyAnimator<>(leftWingRoot, rightWingRoot));
    }






























    public static <T extends ChangedEntity, M extends AdvancedHumanoidModel<T>> Consumer<HumanoidAnimator<T, M>> FrostScaleTaurDragonWinged
            (ModelPart leftWingRoot, ModelPart leftWingBone1, ModelPart leftWingBone2,ModelPart leftWingBone3,
             ModelPart rightWingRoot, ModelPart rightWingBone1, ModelPart rightWingBone2,ModelPart rightWingBone3)
    {
        return (animator) ->
                animator.addAnimator(
                        new FrostScaleDragonTaurWingInitAnimator<>(leftWingRoot,leftWingBone1,leftWingBone2,leftWingBone3,
                                                                    rightWingRoot,rightWingBone1,rightWingBone2,rightWingBone3));
    }
}