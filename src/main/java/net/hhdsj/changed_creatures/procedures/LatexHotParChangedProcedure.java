package net.hhdsj.changed_creatures.procedures;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.ltxprogrammer.changed.entity.TransfurCause;
import net.ltxprogrammer.changed.entity.TransfurContext;
import net.ltxprogrammer.changed.init.ChangedRegistry;
import net.ltxprogrammer.changed.process.ProcessTransfur;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;


public class LatexHotParChangedProcedure {
    private static final ResourceLocation FORM_VARIANT = ChangedCreature.ChangedCreatureResourceLocation("form_latex_yunxq_hot_dragon");

    public static void execute(LivingEntity livingEntity) {
        try {
            final var variant = ChangedRegistry.TRANSFUR_VARIANT.get().getValue(FORM_VARIANT);
            
            if (variant != null) {
                ProcessTransfur.progressTransfur(livingEntity, 3f, variant, TransfurContext.hazard(TransfurCause.GRAB_REPLICATE));
            }
        } catch (Exception e) {
            //System.err.println("Error during transfur process: " + e.getMessage());
            e.printStackTrace();
        }
    }

}