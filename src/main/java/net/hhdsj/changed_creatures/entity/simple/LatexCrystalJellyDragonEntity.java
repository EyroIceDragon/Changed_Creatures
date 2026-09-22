package net.hhdsj.changed_creatures.entity.simple;

import net.hhdsj.changed_creatures.init.ChangedCreatureModEntities;
import net.ltxprogrammer.changed.entity.ChangedEntity;
import net.ltxprogrammer.changed.entity.TransfurCause;
import net.ltxprogrammer.changed.entity.TransfurMode;
import net.ltxprogrammer.changed.util.Color3;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class LatexCrystalJellyDragonEntity extends ChangedEntity {

    private BlockPos homePos = null;
    private static final int TERRITORY_RADIUS = 25;

    public LatexCrystalJellyDragonEntity(EntityType<? extends ChangedEntity> type, Level level) {
        super(type, level);
        this.xpReward = 30;
    }

    public LatexCrystalJellyDragonEntity(PlayMessages.SpawnEntity spawnEntity, Level world) {
        this(ChangedCreatureModEntities.LATEX_CRYSTAL_JELLY.get(), world);
    }

    @Override
    public TransfurMode getTransfurMode() {
        return TransfurMode.NONE;
    }

    @Override
    protected void registerGoals() {
        // 基础AI
        this.goalSelector.addGoal(0, new FloatGoal(this));                              // 游泳
        this.goalSelector.addGoal(1, new ReturnToTerritoryGoal());                      // 返回领地
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 0.8D, 30));            // 随机漫步
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));  // 看向玩家
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));                   // 随机转头

        // 目标选择
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 80.0D)
                .add(Attributes.FOLLOW_RANGE, 20.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.6D)
                .add(Attributes.ARMOR, 4.0D);
    }

    @Override
    public void tick() {
        super.tick();

        if (!level().isClientSide && random.nextInt(40) == 0) {
            spawnAmbientParticles();
        }
    }

    private void spawnAmbientParticles() {
        if (level().isClientSide) {
            for (int i = 0; i < 3; i++) {
                double x = getX() + (random.nextDouble() - 0.5) * 1.2;
                double y = getY() + random.nextDouble() * 1.5;
                double z = getZ() + (random.nextDouble() - 0.5) * 1.2;
                level().addParticle(ParticleTypes.GLOW, x, y, z, 0, 0.02, 0);
            }
        }
    }

    public Color3 getTransfurColor(TransfurCause cause) {
        return Color3.getColor("#1c408f");
    }

    public void setHomePos(BlockPos pos) {
        this.homePos = pos;
    }

    @Nullable
    public BlockPos getHomePos() {
        return homePos;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        if (homePos != null) {
            tag.putInt("HomeX", homePos.getX());
            tag.putInt("HomeY", homePos.getY());
            tag.putInt("HomeZ", homePos.getZ());
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("HomeX")) {
            homePos = new BlockPos(tag.getInt("HomeX"), tag.getInt("HomeY"), tag.getInt("HomeZ"));
        }
    }

    @Override
    public boolean hurt(@NotNull DamageSource source, float amount) {
        // 受伤时播放破碎音效
        if (!level().isClientSide && random.nextFloat() < 0.3f) {
            this.playSound(SoundEvents.AMETHYST_BLOCK_BREAK, 0.8f, 0.6f + random.nextFloat() * 0.5f);
        }
        return super.hurt(source, amount);
    }

    class ReturnToTerritoryGoal extends Goal {
        @Override
        public boolean canUse() {
            if (homePos == null) return false;

            double distSq = distanceToSqr(homePos.getX(), homePos.getY(), homePos.getZ());
            return distSq > TERRITORY_RADIUS * TERRITORY_RADIUS * 2.25;
        }

        @Override
        public void start() {
            navigation.moveTo(homePos.getX(), homePos.getY(), homePos.getZ(), 1.0);
        }

        @Override
        public boolean canContinueToUse() {
            return !navigation.isDone() && homePos != null &&
                    distanceToSqr(homePos.getX(), homePos.getY(), homePos.getZ()) > TERRITORY_RADIUS * TERRITORY_RADIUS;
        }
    }
}