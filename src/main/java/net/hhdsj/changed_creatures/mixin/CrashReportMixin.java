package net.hhdsj.changed_creatures.mixin;

import net.minecraft.CrashReport;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CrashReport.class)
public class CrashReportMixin {

    @Inject(method = "getDetails(Ljava/lang/StringBuilder;)V", at = @At("TAIL"))
    private void addChangedCreaturesInfo(StringBuilder stringBuilder, CallbackInfo ci) {
        try {
            stringBuilder.append("\n\n-- Changed Creatures Mod --\n");
            stringBuilder.append("If you crash due to the Changed Creatures mod, please report it at:\n");
            stringBuilder.append("https://github.com/hhdsjgit/Good-blocks/issues\n");
        }catch(Throwable ignored){
            //No do anything
        }
    }
}