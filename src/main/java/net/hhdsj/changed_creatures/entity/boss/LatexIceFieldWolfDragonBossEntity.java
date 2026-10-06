package net.hhdsj.changed_creatures.entity.boss;

import com.mojang.blaze3d.vertex.PoseStack;
import net.foxyas.changedaddon.client.renderer.layers.features.SonarOutlineLayer;
import net.foxyas.changedaddon.client.renderer.renderTypes.ChangedAddonRenderTypes;
import net.foxyas.changedaddon.entity.projectile.VoidFoxParticleProjectile;
import net.foxyas.changedaddon.init.ChangedAddonEntities;
import net.foxyas.changedaddon.init.ChangedAddonParticleTypes;
import net.hhdsj.changed_creatures.init.ChangedCreatureModEntities;
import net.hhdsj.changed_creatures.init.ChangedCreatureModSounds;
import net.ltxprogrammer.changed.entity.ChangedEntity;
//import net.ltxprogrammer.changed.entity.LatexType;
import net.minecraft.network.chat.Component;
import net.ltxprogrammer.changed.entity.TransfurMode;
import net.ltxprogrammer.changed.init.ChangedAttributes;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.chat.Style;
//import net.minecraft.network.chat.TextComponent;
//import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LatexIceFieldWolfDragonBossEntity extends ChangedEntity{

    private int obsidianBreakCooldown = 0;
    private int AttackInUse = 1;
    private int ticksInUse = 300;
    // 删除这行: private Level level;  // 不需要，使用父类的 level()

    public LatexIceFieldWolfDragonBossEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(ChangedCreatureModEntities.LATEX_ICE_FIELD_WOLF_DRAGON_BOSS.get(), world);
    }

    @Override
    protected void setAttributes(AttributeMap attributes) {
        super.setAttributes(attributes);
        attributes.getInstance(ChangedAttributes.TRANSFUR_DAMAGE.get()).setBaseValue(1);
        attributes.getInstance(Attributes.MOVEMENT_SPEED).setBaseValue(1.1);
        attributes.getInstance(ChangedAttributes.JUMP_STRENGTH.get()).setBaseValue(1.5);
        attributes.getInstance(Attributes.MAX_HEALTH).setBaseValue(500);
        attributes.getInstance(Attributes.ARMOR).setBaseValue(35);
        attributes.getInstance(Attributes.ARMOR_TOUGHNESS).setBaseValue(15);
        attributes.getInstance(Attributes.KNOCKBACK_RESISTANCE).setBaseValue(1);
        attributes.getInstance(Attributes.ATTACK_DAMAGE).setBaseValue(6.0);
        attributes.getInstance(Attributes.FOLLOW_RANGE).setBaseValue(64);
        attributes.getInstance(ForgeMod.SWIM_SPEED.get()).setBaseValue(1.1);
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public TransfurMode getTransfurMode() {
        return TransfurMode.REPLICATION;
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
    public boolean hurt(@NotNull DamageSource source, float amount) {
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
        if (target != null) {
            Level targetLevel = target.level();

            if (targetLevel instanceof ServerLevel serverLevel) {
                int randomValue = this.random.nextInt(11); // 0,1,2,3,4...,10 //
                if (randomValue >= 7) {
                    serverLevel.playSound(null,
                            this.position().x, this.position().y, this.position().z,
                            SoundEvents.SHIELD_BLOCK,
                            SoundSource.MASTER, 1.0F, 1.0F);
                    serverLevel.sendParticles(
                            ParticleTypes.END_ROD,
                            this.getX(),
                            this.getY() + this.getBbHeight() / 2,
                            this.getZ(),
                            300,
                            1, 1, 1,
                            0.8
                    );
                    LatexIceFieldWolfDragonBossEntity.this.teleportTo(target.getX(), target.getY(), target.getZ());
                    return false; // 免疫伤害
                }
            }
        } else if (this.getTarget() == null){
            // 修复：使用 level()
            if (this.level() == null) return false;
            Player nearestPlayer = this.level().getNearestPlayer(this, 16.0);
            if (nearestPlayer != null) {
                this.setTarget(nearestPlayer);
            }
        }
        return super.hurt(source, amount);
    }

    public void doClawsAttackEffect() {// Efeito visual
        double d0 = (double) (-Mth.sin(this.getYRot() * 0.017453292F)) * 1;
        double d1 = (double) Mth.cos(this.getYRot() * 0.017453292F) * 1;
        // 修复：使用 level()
        if (this.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.SWEEP_ATTACK, this.getX() + d0, this.getY(0.5), this.getZ() + d1, 0, d0, 0.0, d1, 0.0);
            serverLevel.sendParticles(ParticleTypes.SWEEP_ATTACK, this.getX() + d0, this.getY(0.6), this.getZ() + d1, 0, d0, 0.0, d1, 0.0);
            serverLevel.sendParticles(ParticleTypes.SWEEP_ATTACK, this.getX() + d0, this.getY(0.7), this.getZ() + d1, 0, d0, 0.0, d1, 0.0);
            this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS, 1f, 0.75f);
        }
    }

    public LatexIceFieldWolfDragonBossEntity(EntityType<LatexIceFieldWolfDragonBossEntity> type, Level world) {
        super(type, world);
        xpReward = 3000;
        setNoAi(false);
        this.bossEvent = new ServerBossEvent(
                Component.translatable("entity.changed_creatures.boss_heath.ice_field_wolf_dragon"),
                BossEvent.BossBarColor.BLUE,
                BossEvent.BossBarOverlay.NOTCHED_10
        );
        // 默认设置为可见
        this.bossEvent.setVisible(true);
    }

    public void CheckobsidianBreak() {
        if (this.level() == null) return;

        if (LatexIceFieldWolfDragonBossEntity.this.getHealth() <= LatexIceFieldWolfDragonBossEntity.this.getMaxHealth() * 0.95) {
            LivingEntity target = LatexIceFieldWolfDragonBossEntity.this.getTarget();
            if (target != null) {
                // 传送
                LatexIceFieldWolfDragonBossEntity.this.teleportTo(target.getX(), target.getY(), target.getZ());

                // 挖掘周围5x5x5区域的黑曜石
                Level level = LatexIceFieldWolfDragonBossEntity.this.level();  // 修复
                BlockPos centerPos = LatexIceFieldWolfDragonBossEntity.this.blockPosition();

                // 遍历5x5x5区域
                for (int x = -2; x <= 2; x++) {
                    for (int y = -2; y <= 2; y++) {
                        for (int z = -2; z <= 2; z++) {
                            BlockPos checkPos = centerPos.offset(x, y, z);
                            BlockState blockState = level.getBlockState(checkPos);

                            // 判断是否为黑曜石
                            if (blockState.is(Blocks.OBSIDIAN)) {
                                if (!level.isClientSide) {
                                    // 获取方块掉落物
                                    Block block = blockState.getBlock();
                                    List<ItemStack> drops = Block.getDrops(blockState, (ServerLevel) level, checkPos, null);

                                    // 破坏方块（设置为空气）
                                    level.setBlock(checkPos, Blocks.AIR.defaultBlockState(), 3);

                                    // 生成掉落物
                                    for (ItemStack drop : drops) {
                                        Block.popResource(level, checkPos, drop);
                                    }

                                    // 添加粒子效果
                                    if (level instanceof ServerLevel serverLevel) {
                                        serverLevel.sendParticles(
                                                ParticleTypes.EXPLOSION,
                                                checkPos.getX() + 0.5,
                                                checkPos.getY() + 0.5,
                                                checkPos.getZ() + 0.5,
                                                10, 0.2, 0.2, 0.2, 0.1
                                        );
                                    }

                                    // 播放声音
                                    level.playSound(null, checkPos,
                                            SoundEvents.STONE_BREAK,
                                            SoundSource.BLOCKS, 1.0F, 1.0F);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
        // 修复：使用 level()
        if (this.level() == null) {
            return;
        }
        if (obsidianBreakCooldown > 0) {
            obsidianBreakCooldown--;
        }

        if (obsidianBreakCooldown == 0) {
            CheckobsidianBreak();
            obsidianBreakCooldown = 100; // 5秒冷却
        }

        // 更新血条进度
        if (!this.level().isClientSide) {
            float healthPercent = this.getHealth() / this.getMaxHealth();
            this.bossEvent.setProgress(healthPercent);
        }
    }

    @Override
    public void startSeenByPlayer(@NotNull ServerPlayer player) {
        super.startSeenByPlayer(player);
        this.bossEvent.addPlayer(player);
        this.bossEvent.setDarkenScreen(true);      // 屏幕变暗
        this.bossEvent.setCreateWorldFog(true);    // 添加环境雾
        player.displayClientMessage(
                Component.literal("You can't escape the ice field!").withStyle((style -> {
                    Style returnStyle = style.withColor(ChatFormatting.AQUA);
                    returnStyle = returnStyle.withItalic(true);
                    return returnStyle;
                })),
                false
        );
    }

    @Override
    public void stopSeenByPlayer(@NotNull ServerPlayer player) {
        super.stopSeenByPlayer(player);
        this.bossEvent.removePlayer(player);
        // 修复：使用 level()
        if (this.level() == null) {
            return;
        }
        if (this.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(
                    ParticleTypes.SNOWFLAKE,
                    this.getX(),
                    this.getY() + this.getBbHeight() / 2,
                    this.getZ(),
                    400,
                    2, 2, 2,
                    0.6
            );
        }
        player.displayClientMessage(
                Component.literal("The ice field retreats...").withStyle((style -> {
                    Style returnStyle = style.withColor(ChatFormatting.AQUA);
                    returnStyle = returnStyle.withItalic(true);
                    return returnStyle;
                })),
                false
        );
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.getHealth() < this.getMaxHealth() * 0.5) {
            this.bossEvent.setName(
                    Component.translatable("entity.changed_creatures.boss_heath.ice_field_wolf_dragon")
                            .withStyle(ChatFormatting.RED)
            );
        }
    }

    @OnlyIn(Dist.CLIENT)
    public boolean handleSonarRenderForCamera(
            @NotNull SonarOutlineLayer<?, ?> sonarOutlineLayer,
            @NotNull LivingEntity livingEntity,
            @NotNull PoseStack poseStack,
            @NotNull MultiBufferSource buffer,
            int packedLight,
            float limbSwing,
            float limbSwingAmount,
            float partialTicks,
            float ageInTicks,
            float netHeadYaw,
            float headPitch
    ) {
        // 调用带 alpha 的版本，传入默认值
        return handleSonarRenderForCamera(
                sonarOutlineLayer, livingEntity, poseStack, buffer,
                packedLight, limbSwing, limbSwingAmount, partialTicks,
                ageInTicks, netHeadYaw, headPitch, 1.0f
        );
    }

    @OnlyIn(Dist.CLIENT)
    public boolean handleSonarRenderForCamera(
            @NotNull SonarOutlineLayer<?, ?> sonarOutlineLayer,
            @NotNull LivingEntity livingEntity,
            @NotNull PoseStack poseStack,
            @NotNull MultiBufferSource buffer,
            int packedLight,
            float limbSwing,
            float limbSwingAmount,
            float partialTicks,
            float ageInTicks,
            float netHeadYaw,
            float headPitch,
            float alpha
    ) {
        float r = 0.0f, g = 0.5f, b = 1.0f; // 冰蓝色

        Minecraft minecraft = Minecraft.getInstance();
        EntityRenderDispatcher entityRenderDispatcher = minecraft.getEntityRenderDispatcher();
        EntityRenderer<? super LivingEntity> renderer = entityRenderDispatcher.getRenderer(livingEntity);
        RenderType outline = ChangedAddonRenderTypes.outlineWithTranslucencyCull(renderer.getTextureLocation(livingEntity));

        sonarOutlineLayer.getParentModel().renderToBuffer(
                poseStack,
                buffer.getBuffer(outline),
                packedLight,
                OverlayTexture.NO_OVERLAY,
                r, g, b, alpha
        );
        return true;
    }

    public void tickAttackTicks() {
        if (!this.isNoAi()) {
            ticksInUse++;
        }
    }

    @Override
    public void baseTick() {//Form changed-addon
        super.baseTick();
        // 获取当前目标
        LivingEntity target = this.getTarget();

        // 只有在有目标（找到玩家）时才执行以下代码
        if (target == null) {
            return;
        }

        tickAttackTicks();
        if (ticksInUse > 260) {
            AttackInUse = 0;
            ticksInUse = 0;
        } else {
            return;
        }

        if (this.level() == null) return;

        double radius = 2.5;

        for (int theta = 0; theta < 360; theta += 45) {
            double angleTheta = Math.toRadians(theta);
            for (int phi = 0; phi <= 180; phi += 45) {
                double anglePhi = Math.toRadians(phi);

                double dx = Math.sin(anglePhi) * Math.cos(angleTheta);
                double dy = Math.cos(anglePhi);
                double dz = Math.sin(anglePhi) * Math.sin(angleTheta);

                double px = this.getX() + dx * radius;
                double py = this.getY() + dy * radius + 1.0;
                double pz = this.getZ() + dz * radius;

                // 修复：使用 level()
                VoidFoxParticleProjectile projectile = new VoidFoxParticleProjectile(ChangedAddonEntities.PARTICLE_PROJECTILE.get(), this.level());
                projectile.setSmoothMotion(true);
                projectile.setPos(px, py, pz);
                projectile.shoot(dx, dy, dz, 1.0f, 0.0f);
                projectile.setOwner(this);
                projectile.setTarget(this.getTarget());
                projectile.setParryAble(true);

                this.level().addFreshEntity(projectile);
            }
        }
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, ServerPlayer.class, true, true));
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(4, new FloatGoal(this));

        // 添加远程技能目标
        this.goalSelector.addGoal(1, new Goal() {
            @Override
            public boolean canUse() {
                LivingEntity target = LatexIceFieldWolfDragonBossEntity.this.getTarget();
                if (target == null) return false;

                double distance = LatexIceFieldWolfDragonBossEntity.this.distanceTo(target);
                return distance >= 15;
            }

            @Override
            public void start() {
                LivingEntity target = LatexIceFieldWolfDragonBossEntity.this.getTarget();
                if (target == null) return;

                double distance = LatexIceFieldWolfDragonBossEntity.this.distanceTo(target);

                if (distance >= 8) {
                    int randomIntBound = LatexIceFieldWolfDragonBossEntity.this.random.nextInt(20);
                    if (randomIntBound >= 10) {
                        if (getTarget() instanceof Player player) {
                            player.displayClientMessage(Component.literal("Feel the ice!").withStyle((style -> {
                                Style returnStyle = style.withColor(ChatFormatting.AQUA);
                                returnStyle = returnStyle.withItalic(true);
                                return returnStyle;
                            })), true);
                        }
                        double oldX = LatexIceFieldWolfDragonBossEntity.this.getX();
                        double oldY = LatexIceFieldWolfDragonBossEntity.this.getY();
                        double oldZ = LatexIceFieldWolfDragonBossEntity.this.getZ();

                        // 修复：使用 level()
                        if (LatexIceFieldWolfDragonBossEntity.this.level() == null) {
                            return;
                        }
                        if (LatexIceFieldWolfDragonBossEntity.this.level() instanceof ServerLevel serverLevel) {
                            serverLevel.sendParticles(
                                    ParticleTypes.SNOWFLAKE,
                                    oldX,
                                    oldY + 1,
                                    oldZ,
                                    200,
                                    1, 1, 1,
                                    0.3
                            );
                        }

                        LatexIceFieldWolfDragonBossEntity.this.teleportTo(target.getX(), target.getY(), target.getZ());

                        if (LatexIceFieldWolfDragonBossEntity.this.level() instanceof ServerLevel serverLevel) {
                            serverLevel.sendParticles(
                                    ParticleTypes.ITEM_SNOWBALL,
                                    target.getX(),
                                    target.getY() + target.getBbHeight() / 2,
                                    target.getZ(),
                                    300,
                                    5, 5, 5,
                                    0.5
                            );
                        }

                        target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 3));
                    } else {
                        if (getTarget() instanceof Player player) {
                            player.displayClientMessage(Component.literal("Freeze!").withStyle((style -> {
                                Style returnStyle = style.withColor(ChatFormatting.AQUA);
                                returnStyle = returnStyle.withItalic(true);
                                return returnStyle;
                            })), true);
                        }

                        target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100, 1));
                        target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 300, 2));
                    }
                }
            }

            public void stop() {
                if (getTarget() instanceof Player player) {
                    player.displayClientMessage(Component.literal("...").withStyle((style -> {
                        Style returnStyle = style.withColor(ChatFormatting.AQUA);
                        returnStyle = returnStyle.withItalic(true);
                        return returnStyle;
                    })), true);
                }
            }
        });
    }

    @Override
    public boolean doHurtTarget(@NotNull Entity target) {
        boolean hurt = super.doHurtTarget(target);

        if (hurt && target instanceof LivingEntity livingTarget) {
            double knockback = 0.5;
            livingTarget.setDeltaMovement(
                    livingTarget.getDeltaMovement().add(
                            Math.sin(this.getYRot() * Math.PI / 180.0) * knockback,
                            0.5,
                            -Math.cos(this.getYRot() * Math.PI / 180.0) * knockback
                    )
            );

            // 修复：使用 level()
            if (this.level() instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(
                        ParticleTypes.SNOWFLAKE,
                        target.getX(),
                        target.getY() + target.getBbHeight() / 2,
                        target.getZ(),
                        600,
                        2, 2, 2,
                        0.4
                );

                doClawsAttackEffect();

                SimpleParticleType solventParticle = (SimpleParticleType) ChangedAddonParticleTypes.SOLVENT_PARTICLE.get();

                serverLevel.sendParticles(
                        solventParticle,
                        this.getX(),
                        this.getY() + this.getEyeHeight(),
                        this.getZ(),
                        200,
                        0.5, 0.5, 0.5,
                        0.2
                );
            }

            livingTarget.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600, 2));
            livingTarget.addEffect(new MobEffectInstance(MobEffects.WITHER, 200, 1));
        }

        return hurt;
    }

    @Override
    public void remove(@NotNull RemovalReason reason) {
        this.bossEvent.setVisible(false);
        super.remove(reason);
    }

    public static void init() {
    }

    private final ServerBossEvent bossEvent;

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        if (!this.level().isClientSide) {
            // 播放Boss音乐
            this.level().playSound(null,
                    this.getX(), this.getY(), this.getZ(),
                    ChangedCreatureModSounds.INK_SANS.get(),
                    SoundSource.HOSTILE,
                    2.0f, 0.8f);
        }
    }
}