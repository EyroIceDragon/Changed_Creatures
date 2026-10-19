package net.hhdsj.changed_creatures.procedures;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public class DIEProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		try {
			if (entity == null || world == null) return;

			ResourceKey<DamageType> customDamageType = ResourceKey.create(
					Registries.DAMAGE_TYPE,
					ChangedCreature.ChangedCreatureResourceLocation( "furry_cat_kill")
			);

			var damageType = world.registryAccess()
					.registryOrThrow(Registries.DAMAGE_TYPE)
					.getHolderOrThrow(customDamageType);

			entity.hurt(new DamageSource(damageType), 80);
		} catch (Exception e) {
			System.err.println("Goodblock Error: " + e.getMessage());
		}
	}
}