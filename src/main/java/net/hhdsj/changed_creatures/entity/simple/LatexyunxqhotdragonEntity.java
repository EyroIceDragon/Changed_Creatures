
package net.hhdsj.changed_creatures.entity.simple;

import net.ltxprogrammer.changed.entity.*;
import net.ltxprogrammer.changed.util.Color3;

import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.fml.common.Mod;


import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.Difficulty;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.Packet;

import net.minecraft.world.entity.ai.attributes.AttributeMap;

import net.hhdsj.changed_creatures.init.ChangedCreatureModEntities;

import net.minecraftforge.common.ForgeMod;

import java.util.Objects;


@Mod.EventBusSubscriber
public class LatexyunxqhotdragonEntity extends ChangedEntity {

	@Override
    protected void setAttributes(AttributeMap attributes) {
        super.setAttributes(attributes);
        Objects.requireNonNull(attributes.getInstance(Attributes.MOVEMENT_SPEED)).setBaseValue(1.12);
        Objects.requireNonNull(attributes.getInstance(ForgeMod.SWIM_SPEED.get())).setBaseValue(0.93);
    }

    @Override
    public TransfurMode getTransfurMode() {
        return TransfurMode.REPLICATION;
    }

	public Color3 getTransfurColor(TransfurCause cause) {
		return Color3.getColor("#ff3500");
	}

	public LatexyunxqhotdragonEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(ChangedCreatureModEntities.LATEX_YUN_XQ_HOT_DRAGON.get(), world);
	}

	public LatexyunxqhotdragonEntity(EntityType<LatexyunxqhotdragonEntity> type, Level world) {
		super(type, world);
		xpReward = 0;
		setNoAi(false);
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEFINED;
	}

	protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHitIn) {
		super.dropCustomDeathLoot(source, looting, recentlyHitIn);
		this.spawnAtLocation(new ItemStack(Items.BLAZE_ROD));
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.death"));
	}

	@Override
	public boolean fireImmune() {
		return true;
	}

	@Override
	public void setRemainingFireTicks(int ticks) {
		super.setRemainingFireTicks(0);
	}

	public static void init() {
		SpawnPlacements.register(ChangedCreatureModEntities.LATEX_YUN_XQ_HOT_DRAGON.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos, random) -> (world.getDifficulty() != Difficulty.PEACEFUL && Monster.isDarkEnoughToSpawn(world, pos, random) && Mob.checkMobSpawnRules(entityType, world, reason, pos, random)));
	}
}
