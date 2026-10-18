
package net.hhdsj.changed_creatures.entity.simple;

import net.ltxprogrammer.changed.entity.*;
import net.ltxprogrammer.changed.util.Color3;

import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.Packet;

import net.minecraft.world.entity.ai.attributes.AttributeMap;

import net.hhdsj.changed_creatures.init.ChangedCreatureModEntities;

import net.minecraftforge.common.ForgeMod;


public class LatexyunxqicedragonEntity extends ChangedEntity {

	@Override
    protected void setAttributes(AttributeMap attributes) {
        super.setAttributes(attributes);
        attributes.getInstance(Attributes.MOVEMENT_SPEED).setBaseValue(1.1);
        attributes.getInstance(ForgeMod.SWIM_SPEED.get()).setBaseValue(0.93);
        attributes.getInstance(Attributes.MAX_HEALTH).setBaseValue(30.0);
    }

	public Color3 getTransfurColor(TransfurCause cause) {
		return Color3.getColor("#0073ff");
	}

    @Override
    public TransfurMode getTransfurMode() {
        return TransfurMode.REPLICATION;
    }

	public LatexyunxqicedragonEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(ChangedCreatureModEntities.LATEX_YUN_XQ_ICE_DRAGON.get(), world);
	}

	public LatexyunxqicedragonEntity(EntityType<LatexyunxqicedragonEntity> type, Level world) {
		super(type, world);
		xpReward = 0;
		setNoAi(false);
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
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
	public boolean hurt(DamageSource source, float amount) {
		if (source.is(DamageTypes.FALL))
			return false;
		return super.hurt(source, amount);
	}

	public static void init() {
	}
}
