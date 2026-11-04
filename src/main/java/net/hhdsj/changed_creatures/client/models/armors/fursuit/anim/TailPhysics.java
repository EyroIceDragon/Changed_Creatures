package net.hhdsj.changed_creatures.client.models.armors.fursuit.anim;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;


public class TailPhysics {
    private float drag = 0.0F;
    private float vertical = 0.0F;
    private float lastBodyRot = 0.0F;
    private boolean initialized = false;

    public float[] update(LivingEntity entity, float partialTicks) {
        if (!initialized) {
            lastBodyRot = entity.yBodyRot;
            initialized = true;
        }

        float deltaRot = Mth.wrapDegrees(entity.yBodyRot - lastBodyRot);
        lastBodyRot = entity.yBodyRot;
        float targetDrag = Mth.clamp(deltaRot * 0.15F, -1.0F, 1.0F);
        this.drag += (targetDrag - this.drag) * 0.15F;

        double dy = entity.getDeltaMovement().y;
        float targetVertical = Mth.clamp((float) dy * 2.0F, -1.0F, 1.0F);
        this.vertical += (targetVertical - this.vertical) * 0.25F;

        return new float[]{ this.drag, this.vertical };
    }
}