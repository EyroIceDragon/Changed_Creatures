/*
 *
 *   MCR MAKE
 *
 */
package net.hhdsj.changed_creatures.potion;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class SlowinfectionMobEffect extends MobEffect {
	public SlowinfectionMobEffect() {
		super(MobEffectCategory.NEUTRAL, -65281);
	}

	@Override
	public String getDescriptionId() {
		return "effect.changed_creatures.slowinfection";
	}

	@Override
	public void applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
		/*final var variant = ChangedCreatureModTransfurVariants.LATEX_FRUIT_DRAGON_WOLF.get();
		ProcessTransfur.progressTransfur(livingEntity, 5f, variant, TransfurContext.hazard(TransfurCause.GRAB_REPLICATE));*/
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
