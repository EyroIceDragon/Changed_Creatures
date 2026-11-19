package net.hhdsj.changed_creatures.mixin.changed;

import net.foxyas.changedaddon.entity.bosses.Experiment009Entity;
import net.ltxprogrammer.changed.entity.ChangedEntity;
import net.ltxprogrammer.changed.entity.TransfurMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Experiment009Entity.class)
public class ChangedEntityMixin {
    @Inject(method = "getTransfurMode",remap = false, cancellable = true,at = @At("RETURN"))
    public void getTransfurMode(CallbackInfoReturnable<TransfurMode> cir) {
        ChangedEntity self = (ChangedEntity) (Object) this;
        boolean isLowHealth = self.computeHealthRatio() <= 0.15F;
        cir.setReturnValue(isLowHealth ? TransfurMode.REPLICATION : TransfurMode.NONE);
    }
}
