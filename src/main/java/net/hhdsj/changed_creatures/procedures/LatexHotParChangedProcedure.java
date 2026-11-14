package net.hhdsj.changed_creatures.procedures;

import net.hhdsj.changed_creatures.init.ChangedCreatureModTransfurVariants;
import net.ltxprogrammer.changed.entity.TransfurCause;
import net.ltxprogrammer.changed.entity.TransfurContext;
import net.ltxprogrammer.changed.process.ProcessTransfur;
import net.minecraft.world.entity.LivingEntity;


public class LatexHotParChangedProcedure {
    public static void execute(LivingEntity livingEntity) {
            final var variant = ChangedCreatureModTransfurVariants.LATEX_YUN_XQ_HOT_DRAGON.get();
            ProcessTransfur.progressTransfur(livingEntity, 3f, variant, TransfurContext.hazard(TransfurCause.GRAB_REPLICATE));
    }
}