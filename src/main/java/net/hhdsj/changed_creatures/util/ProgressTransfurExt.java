package net.hhdsj.changed_creatures.util;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.ltxprogrammer.changed.entity.TransfurCause;
import net.ltxprogrammer.changed.entity.TransfurContext;
import net.ltxprogrammer.changed.entity.variant.TransfurVariant;
import net.ltxprogrammer.changed.init.ChangedRegistry;
import net.ltxprogrammer.changed.process.ProcessTransfur;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

/**
 * Tf
 */
public class ProgressTransfurExt {

    public static void execute(LivingEntity livingEntity,String text) {
        final ResourceLocation FORM_VARIANT = ChangedCreature.CCRLP(text);
        try {
            final var variant = ChangedRegistry.TRANSFUR_VARIANT.get().getValue(FORM_VARIANT);

            if (variant != null) {
                ProcessTransfur.progressTransfur(livingEntity, 5f, variant, TransfurContext.hazard(TransfurCause.GRAB_REPLICATE));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void tf(LivingEntity livingEntity,float progress_speed,String text,TransfurCause transfurCause) {
        final ResourceLocation FORM_VARIANT = ChangedCreature.CCRLP(text);
        try {
            final var variant = ChangedRegistry.TRANSFUR_VARIANT.get().getValue(FORM_VARIANT);
            if (variant != null) {
                ProcessTransfur.progressTransfur(livingEntity, progress_speed, variant, TransfurContext.hazard(transfurCause));
            }
        } catch (Exception e) {
            //抛出错误
            e.printStackTrace();
        }
    }

    public static void tf(LivingEntity livingEntity, float progress_speed, TransfurVariant transfurVariant, TransfurCause transfurCause) {
        ProcessTransfur.progressTransfur(livingEntity, progress_speed, transfurVariant, TransfurContext.hazard(transfurCause));

    }
}
