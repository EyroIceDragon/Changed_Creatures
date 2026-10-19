
package net.hhdsj.changed_creatures.entity.simple;

import net.ltxprogrammer.changed.entity.variant.TransfurVariantInstance;
import net.ltxprogrammer.changed.process.ProcessTransfur;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.ltxprogrammer.changed.util.Color3;
import net.minecraft.world.entity.*;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.Difficulty;
import net.minecraft.network.protocol.Packet;

import net.hhdsj.changed_creatures.init.ChangedCreatureModEntities;


import net.ltxprogrammer.changed.entity.*;
import net.minecraft.world.entity.EntityType;
import org.jetbrains.annotations.NotNull;

import net.ltxprogrammer.changed.entity.TransfurMode;

@Mod.EventBusSubscriber
public class LatexthreemonthwolfEntity extends ChangedEntity implements RangedAttackMob {
    @Override
    public TransfurMode getTransfurMode() {
        return TransfurMode.REPLICATION;
    }

	public LatexthreemonthwolfEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(ChangedCreatureModEntities.LATEX_THREE_MONTH_WOLF.get(), world);
	}

	public Color3 getTransfurColor(TransfurCause cause) {
		return Color3.getColor("#FF62FF");
	}

	public LatexthreemonthwolfEntity(EntityType<LatexthreemonthwolfEntity> type, Level world) {
		super(type, world);
		xpReward = 0;
		setNoAi(false);
		this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true, true));
		this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1));
		this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(4, new FloatGoal(this));
		this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.25, 20, 15f) {
			@Override
			public boolean canContinueToUse() {
				return this.canUse();
			}
		});
	}

	@Override
	public @NotNull MobType getMobType() {
		return MobType.UNDEFINED;
	}

	protected void dropCustomDeathLoot(@NotNull DamageSource source, int looting, boolean recentlyHitIn) {
		super.dropCustomDeathLoot(source, looting, recentlyHitIn);
		this.spawnAtLocation(new ItemStack(Blocks.ALLIUM));
	}

	@Override
    public void performRangedAttack(@NotNull LivingEntity target, float flval) {
		//Fix #10 issues
		if (target instanceof Player player) {
			TransfurVariantInstance<?> variant = ProcessTransfur.getPlayerTransfurVariant(player);
			if (variant != null) {
				return;
			}
		}

		var entityarrow = new LatexthreemonthwolfEntityProjectile(this.level(), this);
		double d0 = target.getY() + target.getEyeHeight() - 1.1;
		double d1 = target.getX() - this.getX();
		double d3 = target.getZ() - this.getZ();
		entityarrow.shoot(d1, d0 - entityarrow.getY() + Math.sqrt(d1 * d1 + d3 * d3) * 0.2F, d3, 1.6F, 12.0F);

		if (this.level() != null) {
			this.level().addFreshEntity(entityarrow);
		}
	}

	public static void init() {
		SpawnPlacements.register(ChangedCreatureModEntities.LATEX_THREE_MONTH_WOLF.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos, random) -> (world.getDifficulty() != Difficulty.PEACEFUL && Monster.isDarkEnoughToSpawn(world, pos, random) && Mob.checkMobSpawnRules(entityType, world, reason, pos, random)));
	}
}