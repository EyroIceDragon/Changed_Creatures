package net.hhdsj.changed_creatures.client.models.armors.fursuit;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;

public abstract class HumanoidFursuitModel extends HumanoidModel<LivingEntity> {

    public HumanoidFursuitModel(ModelPart root) {
        super(root);
    }

    public ModelPart getArm(HumanoidArm arm) {
        return arm == HumanoidArm.LEFT ? this.leftArm : this.rightArm;
    }

    public void setVisibilityForSlot(EquipmentSlot slot) {
        this.setAllVisible(false);
        switch (slot) {
            case HEAD -> {
                this.head.visible = true;
                this.hat.visible = true;
            }
            case CHEST -> {
                this.body.visible = true;
                this.rightArm.visible = true;
                this.leftArm.visible = true;
            }
            case LEGS, FEET -> {
                this.rightLeg.visible = true;
                this.leftLeg.visible = true;
            }
            default -> { }
        }
    }

    public void setVisibilityForItem(EquipmentSlot slot) {
        this.setAllVisible(false);
        switch (slot) {
            case HEAD -> this.head.visible = true;
            case CHEST -> {
                this.body.visible = true;
                this.rightArm.visible = true;
                this.leftArm.visible = true;
            }
            case LEGS -> {
                this.rightLeg.visible = true;
                this.leftLeg.visible = true;
            }
            default -> { }
        }
    }

    @Override
    public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
}