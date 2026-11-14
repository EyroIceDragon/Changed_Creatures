package net.hhdsj.changed_creatures.entity.simple;

import net.hhdsj.changed_creatures.init.ChangedCreatureModEntities;
import net.ltxprogrammer.changed.entity.TransfurCause;
import net.ltxprogrammer.changed.entity.TransfurContext;
import net.ltxprogrammer.changed.init.ChangedItems;
import net.ltxprogrammer.changed.init.ChangedRegistry;
import net.ltxprogrammer.changed.process.ProcessTransfur;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;


public class LatexthreemonthwolfEntityProjectile extends AbstractArrow {

    private static final EntityDataAccessor<String> FORM_VARIANT =
            SynchedEntityData.defineId(LatexthreemonthwolfEntityProjectile.class, EntityDataSerializers.STRING);
    
    private static final EntityDataAccessor<Boolean> PARRYABLE =
            SynchedEntityData.defineId(LatexthreemonthwolfEntityProjectile.class, EntityDataSerializers.BOOLEAN);
    
    private static final EntityDataAccessor<Float> PROJECTILE_DAMAGE =
            SynchedEntityData.defineId(LatexthreemonthwolfEntityProjectile.class, EntityDataSerializers.FLOAT);
    
    private int life = 1200;
    private ResourceLocation formVariant = ChangedCreature.CCRL("form_latex_three_month_wolf");
    private float projectileDamage = 1.0f;
    private int shakeTime = 0;
    private boolean inGround = false;
    private int inGroundTime = 0;

    public LatexthreemonthwolfEntityProjectile(EntityType<? extends LatexthreemonthwolfEntityProjectile> type, Level level) {
        super(type, level);
        this.setNoGravity(false);
        this.setBaseDamage(projectileDamage);
    }
    
    public LatexthreemonthwolfEntityProjectile(PlayMessages.SpawnEntity packet, Level world) {
        this(ChangedCreatureModEntities.LATEXTHREEMONTHWOLF_PROJECTILE.get(), world);
    }
    
    public LatexthreemonthwolfEntityProjectile(Level world, LivingEntity shooter) {
        this(ChangedCreatureModEntities.LATEXTHREEMONTHWOLF_PROJECTILE.get(), world);
        this.setOwner(shooter);
        this.setPos(shooter.getX(), shooter.getEyeY() - 0.1, shooter.getZ());
        
        this.setYRot(shooter.getYRot());
        this.setXRot(shooter.getXRot());
        
        Vec3 look = shooter.getLookAngle();
        this.shoot(look.x, look.y, look.z, 1.5f, 1.0f);
    }
    
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(FORM_VARIANT, "changed_creatures:form_latex_three_month_wolf");
        this.entityData.define(PARRYABLE, false);
        this.entityData.define(PROJECTILE_DAMAGE, 1.0f);
    }
    
    @Override
    public void tick() {
        super.tick();
        
        if (this.tickCount > life) {
            this.discard();
            return;
        }
        
        if (this.inGround) {
            this.inGroundTime++;
            if (this.inGroundTime > 1200) { // 60秒后消失
                this.discard();
                return;
            }
        }
        
        // 粒子效果
        if (!this.inGround && this.level().isClientSide && this.tickCount % 2 == 0) {
            this.level().addParticle(ParticleTypes.ENCHANT, 
                this.getX(), this.getY(), this.getZ(), 
                0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        Entity target = result.getEntity();

        // 增加有效性检查
        if (target == null || !target.isAlive()) {
            this.discard();
            return;
        }

        if (!this.level().isClientSide && target instanceof LivingEntity livingTarget) {
            // 检查目标是否有效且不是自己
            if (target == this.getOwner()) {
                return;
            }

            // 检查目标是否可攻击
            if (!livingTarget.isAttackable()) {
                return;
            }

            // 格挡检查
            if (this.isParryable() && livingTarget.isBlocking() &&
                    livingTarget.hasLineOfSight(this)) {
                this.handleParry(livingTarget);
                return;
            }

            try {
                // 使用正确的伤害类型
                DamageSource source;
                if (this.getOwner() instanceof LivingEntity owner) {
                    source = this.damageSources().arrow(this, owner);
                } else {
                    source = this.damageSources().arrow(this, null);
                }

                float damage = this.getProjectileDamage();

                // 应用伤害
                if (livingTarget.hurt(source, damage)) {
                    this.applyTransfurEffect(livingTarget);
                    this.playSound(SoundEvents.ARROW_HIT, 1.0F, 1.2F);

                    if (this.getKnockback() > 0) {
                        Vec3 knockbackVec = this.getDeltaMovement().normalize().scale(this.getKnockback() * 0.6);
                        livingTarget.setDeltaMovement(livingTarget.getDeltaMovement().add(knockbackVec));
                        livingTarget.hurtMarked = true;
                    }

                    if (this.getPierceLevel() <= 0) {
                        this.discard();
                    }
                } else {
                    this.setDeltaMovement(this.getDeltaMovement().scale(-0.1D));
                    this.setYRot(this.getYRot() + 180.0F);
                    this.yRotO += 180.0F;
                    if (!this.level().isClientSide && this.getDeltaMovement().lengthSqr() < 1.0E-7D) {
                        this.discard();
                    }
                }
            } catch (Exception e) {
                // 捕获异常避免崩溃
                this.discard();
            }
        }
    }
    
    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        
        this.playSound(SoundEvents.ARROW_HIT, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
        
        // 设置在地面状态
        this.inGround = true;
        this.inGroundTime = 0;
        
        // 震动
        this.shakeTime = 7;
        
        // 设置位置和运动
        Vec3 hitVec = result.getLocation();
        this.setPos(hitVec.x, hitVec.y, hitVec.z);
        this.setDeltaMovement(Vec3.ZERO);
        this.hasImpulse = true;
    }
    
    protected void applyTransfurEffect(LivingEntity target) {
        // 从数据中获取"变体"
        String variantStr = this.entityData.get(FORM_VARIANT);
        ResourceLocation variantLoc = new ResourceLocation(variantStr);
        
        final var variant = ChangedRegistry.TRANSFUR_VARIANT.get().getValue(variantLoc);
        
        if (variant != null && target.canBeSeenAsEnemy()) {
            ProcessTransfur.progressTransfur(target, 2f, variant,
                    TransfurContext.hazard(TransfurCause.GRAB_REPLICATE));
        }
    }
    
    protected void handleParry(LivingEntity blocker) {
        // 反转方向
        Vec3 motion = this.getDeltaMovement().scale(-1.2);
        this.setDeltaMovement(motion);
        
        // 改变所有者
        this.setOwner(blocker);
        
        // 增加伤害
        this.setProjectileDamage(this.getProjectileDamage() * 1.5f);
        
        // 播放格挡音效
        this.playSound(SoundEvents.SHIELD_BLOCK, 1.0F, 1.0F);
        
        // 粒子效果
        if (this.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.CRIT, 
                this.getX(), this.getY(), this.getZ(), 
                10, 0.5, 0.5, 0.5, 0.1);
        }
    }
    
    public boolean isParryable() {
        return this.entityData.get(PARRYABLE);
    }
    
    public void setParryable(boolean parryable) {
        this.entityData.set(PARRYABLE, parryable);
    }
    
    public void setFormVariant(ResourceLocation variant) {
        this.formVariant = variant;
        this.entityData.set(FORM_VARIANT, variant.toString());
    }
    
    public ResourceLocation getFormVariant() {
        return this.formVariant;
    }
    
    public float getProjectileDamage() {
        return this.entityData.get(PROJECTILE_DAMAGE);
    }
    
    public void setProjectileDamage(float damage) {
        this.projectileDamage = damage;
        this.entityData.set(PROJECTILE_DAMAGE, damage);
        this.setBaseDamage(damage);
    }
    
    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag); 
        
        if (tag.contains("FormVariant")) {
            this.setFormVariant(new ResourceLocation(tag.getString("FormVariant")));
        }
        if (tag.contains("ProjectileDamage")) {
            this.setProjectileDamage(tag.getFloat("ProjectileDamage"));
        }
        if (tag.contains("Parryable")) {
            this.setParryable(tag.getBoolean("Parryable"));
        }
        if (tag.contains("Life")) {
            this.life = tag.getInt("Life");
        }
        if (tag.contains("InGround")) {
            this.inGround = tag.getBoolean("InGround");
        }
    }
    
    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag); 
        
        tag.putString("FormVariant", this.formVariant.toString());
        tag.putFloat("ProjectileDamage", this.getProjectileDamage());
        tag.putBoolean("Parryable", this.isParryable());
        tag.putInt("Life", this.life);
        tag.putBoolean("InGround", this.inGround);
        
        if (this.getOwner() != null) {
            tag.putUUID("Owner", this.getOwner().getUUID());
        }
        tag.putBoolean("inGround", this.inGround);
        tag.putByte("shake", (byte)this.shakeTime);
    }
    
    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
    
    @Override
    protected ItemStack getPickupItem() {
        ItemStack stack = new ItemStack(ChangedItems.LATEX_TIPPED_ARROW.get());
        CompoundTag tag = stack.getOrCreateTag();
        tag.putString("form", this.formVariant.toString());
        return stack;
    }
    
    @Override
    public void playerTouch(net.minecraft.world.entity.player.Player player) {
        if (!this.level().isClientSide && (this.inGround || this.isNoPhysics()) && 
            this.shakeTime <= 0 && player.getInventory().add(this.getPickupItem())) {
            player.take(this, 1);
            this.discard();
        }
    }
    
    @Override
    protected float getWaterInertia() {
        return 0.6F; // 水中阻力
    }
    
    @Override
    public boolean isAttackable() {
        return false;
    }
    
    @Override
    public boolean isPickable() {
        return true;
    }
    
    @Override
    public void setBaseDamage(double damage) {
        super.setBaseDamage(damage);
        this.projectileDamage = (float) damage;
    }
    
    @Override
    public double getBaseDamage() {
        return this.projectileDamage;
    }

}