package net.hhdsj.changed_creatures.client.models.armors.fursuit.anim;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

public class WolfTailAnim {
    private final ModelPart tail;
    private final ModelPart tailPrimary;
    private final ModelPart tailSecondary;
    private final ModelPart tailTertiary;

    public WolfTailAnim(ModelPart tail, ModelPart tailPrimary, ModelPart tailSecondary, ModelPart tailTertiary) {
        this.tail = tail;
        this.tailPrimary = tailPrimary;
        this.tailSecondary = tailSecondary;
        this.tailTertiary = tailTertiary;
    }

    public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float idleSwing = Mth.sin(ageInTicks * 0.1F) * 0.05F;
        float walkSwing = Mth.cos(limbSwing * 0.6662F) * 0.3F * limbSwingAmount;
        float totalSwing = idleSwing + walkSwing;

        this.tail.yRot = totalSwing * 0.2F;
        this.tailPrimary.yRot = totalSwing * 0.3F;
        this.tailSecondary.yRot = totalSwing * 0.4F;
        this.tailTertiary.yRot = totalSwing * 0.5F;
    }
}