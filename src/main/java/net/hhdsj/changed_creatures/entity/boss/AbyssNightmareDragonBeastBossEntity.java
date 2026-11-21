package net.hhdsj.changed_creatures.entity.boss;

import net.hhdsj.changed_creatures.init.ChangedCreatureModEntities;
import net.ltxprogrammer.changed.entity.ChangedEntity;
import net.ltxprogrammer.changed.entity.TransfurMode;
import net.ltxprogrammer.changed.init.ChangedAttributes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AbyssNightmareDragonBeastBossEntity extends ChangedEntity {
    private final List<Entity> otherEntities = new ArrayList<>();
    public boolean is_main = true;
    private int time = 0;
    private final ServerBossEvent bossEvent;

    public AbyssNightmareDragonBeastBossEntity(EntityType<? extends ChangedEntity> type, Level level) {
        super(type, level);
        setNoAi(false);
        xpReward = 3000;
        this.bossEvent = new ServerBossEvent(
                Component.literal("Abyss Nightmare"),
                BossEvent.BossBarColor.WHITE,
                BossEvent.BossBarOverlay.NOTCHED_6
        );
        this.bossEvent.setVisible(true);
    }

    @Override
    protected void setAttributes(AttributeMap attributes) {
        super.setAttributes(attributes);
        attributes.getInstance(ChangedAttributes.TRANSFUR_DAMAGE.get()).setBaseValue(1);
        attributes.getInstance(Attributes.MOVEMENT_SPEED).setBaseValue(1.1);
        attributes.getInstance(ChangedAttributes.JUMP_STRENGTH.get()).setBaseValue(1.5);
        attributes.getInstance(Attributes.MAX_HEALTH).setBaseValue(500);
        attributes.getInstance(Attributes.ARMOR).setBaseValue(5);
        attributes.getInstance(Attributes.ARMOR_TOUGHNESS).setBaseValue(3);
        attributes.getInstance(Attributes.KNOCKBACK_RESISTANCE).setBaseValue(1);
        attributes.getInstance(Attributes.ATTACK_DAMAGE).setBaseValue(6.0);
        attributes.getInstance(Attributes.FOLLOW_RANGE).setBaseValue(64);
        attributes.getInstance(ForgeMod.SWIM_SPEED.get()).setBaseValue(1.1);
    }

    @Override
    public TransfurMode getTransfurMode() {
        return TransfurMode.NONE;
    }

    @Override
    public boolean hurt(@NotNull DamageSource source, float amount) {
        if (!is_main) {
            return super.hurt(source, amount);
        }
        if (source.is(DamageTypes.FALL))
            return false;
        if (source.is(DamageTypes.IN_FIRE))
            return false;
        if (source.is(DamageTypes.HOT_FLOOR))
            return false;
        if (source.getDirectEntity() instanceof ThrownPotion || source.getDirectEntity() instanceof AreaEffectCloud)
            return false;
        if (source.is(DamageTypes.CACTUS))
            return false;
        if (source.is(DamageTypes.DROWN))
            return false;
        if (source.is(DamageTypes.LIGHTNING_BOLT))
            return false;

        LivingEntity target = this.getTarget();
        if (target == null) {
            Player nearestPlayer = this.level().getNearestPlayer(this, 16.0);
            if (nearestPlayer != null) {
                this.setTarget(nearestPlayer);
            }
        }
        return super.hurt(source, amount);
    }

    @Override
    public boolean doHurtTarget(@NotNull Entity target) {
        doClawsAttackEffect();
        return true;
    }

    @Override
    public void startSeenByPlayer(@NotNull ServerPlayer player) {
        super.startSeenByPlayer(player);
        if (is_main) {
            this.bossEvent.addPlayer(player);
            this.bossEvent.setDarkenScreen(true);
            this.bossEvent.setCreateWorldFog(true);
        }
    }

    @Override
    public void stopSeenByPlayer(@NotNull ServerPlayer player) {
        super.stopSeenByPlayer(player);
        if (is_main) {
            this.bossEvent.removePlayer(player);
        }
    }

    @Override
    public void die(@NotNull DamageSource source) {
        super.die(source);
        if (is_main) {
            bossEvent.removeAllPlayers();
            for (Entity e : otherEntities) {
                if (e.isAlive() && !level().isClientSide()) {
                    e.remove(Entity.RemovalReason.DISCARDED);
                }
            }
            otherEntities.clear();
        }
    }

    public void doClawsAttackEffect() {
        double d0 = (double) (-Mth.sin(this.getYRot() * 0.017453292F)) * 1;
        double d1 = (double) Mth.cos(this.getYRot() * 0.017453292F) * 1;
        if (this.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.SWEEP_ATTACK, this.getX() + d0, this.getY(0.5), this.getZ() + d1, 0, d0, 0.0, d1, 0.0);
            serverLevel.sendParticles(ParticleTypes.SWEEP_ATTACK, this.getX() + d0, this.getY(0.6), this.getZ() + d1, 0, d0, 0.0, d1, 0.0);
            serverLevel.sendParticles(ParticleTypes.SWEEP_ATTACK, this.getX() + d0, this.getY(0.7), this.getZ() + d1, 0, d0, 0.0, d1, 0.0);
            this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS, 1f, 0.75f);
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (is_main)
        {
            bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
            otherEntities.removeIf(entity -> !entity.isAlive() || entity.isRemoved());

            time++;
            if (time >= 20 * 30)
            {
                time = 0;
                createOtherEntity();
            }
            Player nearestPlayer = level().getNearestPlayer(this, 24);
            if (nearestPlayer != null)
            {
                setTarget(nearestPlayer);
            }
        }else
        {
            Player p = level().getNearestPlayer(this, 64);
            if (p != null)
            {
                float yaw = getYawTowards(this.position(), p.position());
                this.setYBodyRot(yaw);
                this.setYRot(yaw);
            }
        }
    }

    private void createOtherEntity() {
        if (level().isClientSide()) return;

        EntityType<?> minionType = ChangedCreatureModEntities.ABYSS_NIGHT_MARE_DRAGON_BEAST_BOSS.get();
        double bossX = this.getX();
        double bossY = this.getY();
        double bossZ = this.getZ();

        for (Entity otherEntity : otherEntities) {
            if (otherEntity.isAlive()) {
                otherEntity.remove(Entity.RemovalReason.DISCARDED);
            }
        }
        otherEntities.clear();

        for (int i = 0; i <= 10; i++) {
            for (int a = 0; a <= 10; a++) {
                Vec3 xyz = new Vec3(bossX + a, bossY, bossZ + i);
                BlockPos pos = BlockPos.containing(xyz);
                BlockPos downPos = pos.below();
                BlockPos upPos = pos.above();

                boolean isAir = level().getBlockState(pos).isAir();
                boolean groundSolid = !level().getBlockState(downPos).isAir();
                boolean upAir = level().getBlockState(upPos).isAir();

                if (isAir && groundSolid && upAir) {
                    Entity newEntity = minionType.create(this.level());
                    if (newEntity instanceof AbyssNightmareDragonBeastBossEntity entity) {
                        entity.setPos(xyz);
                        entity.is_main = false;
                        entity.setNoAi(true);
                        entity.setHealth(1);
                        entity.xpReward = 0;
                        level().addFreshEntity(entity);
                        otherEntities.add(entity);
                    }
                }
            }
        }
    }

    private float getYawTowards(Vec3 from, Vec3 to) {
        double dx = to.x - from.x;
        double dz = to.z - from.z;
        return (float) (Mth.atan2(-dx, dz) * (180F / Math.PI));
    }
}