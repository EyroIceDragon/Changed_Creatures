package net.hhdsj.changed_creatures.entity.simple;

import net.hhdsj.changed_creatures.init.ChangedCreatureModEntities;
import net.hhdsj.changed_creatures.init.ChangedCreatureModTransfurVariants;
import net.ltxprogrammer.changed.entity.*;
import net.ltxprogrammer.changed.entity.beast.LatexTaur;
import net.ltxprogrammer.changed.entity.variant.EntityShape;
import net.ltxprogrammer.changed.entity.variant.TransfurVariant;
import net.ltxprogrammer.changed.init.ChangedAttributes;
import net.ltxprogrammer.changed.util.Color3;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class LatexDarkPurpleDragonTaurEntity extends ChangedEntity implements LatexTaur<LatexDarkPurpleDragonTaurEntity> {
    private Object level;

    public LatexDarkPurpleDragonTaurEntity(EntityType<? extends LatexDarkPurpleDragonTaurEntity> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);
    }

    @Override
    protected void setAttributes(AttributeMap attributes) {
        super.setAttributes(attributes);
        Objects.requireNonNull(attributes.getInstance(Attributes.MOVEMENT_SPEED)).setBaseValue(1.2);
        Objects.requireNonNull(attributes.getInstance(ForgeMod.SWIM_SPEED.get())).setBaseValue(0.9);
        Objects.requireNonNull(attributes.getInstance(Attributes.MAX_HEALTH)).setBaseValue(30);
        Objects.requireNonNull(attributes.getInstance(ForgeMod.STEP_HEIGHT_ADDITION.get())).setBaseValue(computeStepHeightOffset(1.1));
        Objects.requireNonNull(attributes.getInstance(ChangedAttributes.JUMP_STRENGTH.get())).setBaseValue(1.25);
        Objects.requireNonNull(attributes.getInstance(ChangedAttributes.FALL_RESISTANCE.get())).setBaseValue(2.5);
    }

    public TransfurVariant<?> getTransfurVariant() {
        return ChangedCreatureModTransfurVariants.LATEX_DARK_PURPLE_DRAGON_TAUR.get();
    }

    public Color3 getTransfurColor(TransfurCause cause) {
        return Color3.getColor("#ca00ff");
    }

    @Override
    public TransfurMode getTransfurMode() {
        return TransfurMode.REPLICATION;
    }

    @Override
    public boolean isSaddleable() {
        return false;
    }

    @Override
    public void equipSaddle(@Nullable SoundSource p_21748_) {
        this.equipSaddle(this, p_21748_);
    }

    @Override
    public boolean isSaddled() {
        return this.isSaddled(this);
    }

    protected void doPlayerRide(Player player) {
        this.doPlayerRide(this, player);
    }

    public double getPassengersRidingOffset() {
        return super.getPassengersRidingOffset() + getTorsoYOffset(this) - (2.0 / 16.0);
    }

    public InteractionResult mobInteract(Player p_30713_, InteractionHand p_30714_) {
        if (isSaddled()) {
            this.doPlayerRide(p_30713_);
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }

        return InteractionResult.PASS;
    }

    public static void init() {
        SpawnPlacements.register(ChangedCreatureModEntities.LATEX_DARK_PURPLE_DRAGON_TAUR.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, world, reason, pos, random) -> (world.getDifficulty() != Difficulty.PEACEFUL && Monster.isDarkEnoughToSpawn(world, pos, random) && Mob.checkMobSpawnRules(entityType, world, reason, pos, random)));
    }

    @Override
    public @NotNull EntityShape getEntityShape() {
        return EntityShape.TAUR;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return ChangedEntity.createLatexAttributes();  // 返回父类的默认属性
    }
}
