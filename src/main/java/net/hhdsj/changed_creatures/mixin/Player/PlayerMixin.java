package net.hhdsj.changed_creatures.mixin.Player;

import net.hhdsj.changed_creatures.ability.DamageResistanceAbility;
import net.hhdsj.changed_creatures.ability.data.*;
import net.hhdsj.changed_creatures.util.PlayerDataGetHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(value = Player.class, priority = 990)
public class PlayerMixin {
    @Inject(
            method = {"tryToStartFallFlying"},
            at = {@At("HEAD")},
            cancellable = true
    )
    protected void tryToStartFallFlying(CallbackInfoReturnable<Boolean> ci) {
        Player player = (Player) (Object) this;
        System.out.print(PlayerDataGetHelper.GetPlayerCanFly(player) + " / " + PlayerDataGetHelper.GetPlayerCanGliding(player));
        if (PlayerDataGetHelper.GetPlayerCanFly(player) &&
                PlayerDataGetHelper.GetPlayerCanGliding(player) &&
                !player.onGround() &&
                !player.isInWater() &&
                !player.isFallFlying() &&
                !player.hasEffect(MobEffects.LEVITATION)) {

            player.startFallFlying();
            ci.setReturnValue(true);
            ci.cancel();
        }
    }

    @Inject(
            method = {"attack"},
            at = {@At("HEAD")}
    )
    protected void onAttack(Entity target, CallbackInfo ci) {
        Player player = (Player) (Object) this;//攻击者
        if (!(target instanceof LivingEntity liv)) return;
        if (liv.level().isClientSide()) return;

        PlayerAbilities abilities = PlayerAbilitiesCapability.get(player);

        for (Map.Entry<ResourceLocation, AbilityData> entry : abilities.getAll().entrySet()) {
            AbilityData data = entry.getValue();

            if (data.level <= 0) continue;

            AbstractAbility ability = AbilityRegistry.get(entry.getKey());
            if (ability == null) continue;

            ability.onAttack(player, liv, data.level);
        }
    }

    @Inject(
            method = {"hurt"},
            at = {@At("HEAD")}
    )
    protected void onHurt(DamageSource Ds, float p_36155_, CallbackInfoReturnable<Boolean> cir) {
        Player player = (Player) (Object) this;;//受害者
        if (!(Ds.getEntity() instanceof LivingEntity liv)) return;//攻击者
        if (liv.level().isClientSide()) return;

        PlayerAbilities abilities = PlayerAbilitiesCapability.get(player);

        for (Map.Entry<ResourceLocation, AbilityData> entry : abilities.getAll().entrySet()) {
            AbilityData data = entry.getValue();

            if (data.level <= 0) continue;

            AbstractAbility ability = AbilityRegistry.get(entry.getKey());
            if (ability == null) continue;

            ability.onHurt(player, liv, data.level);
        }
    }

    @ModifyVariable(
            method = "hurt",
            at = @At("HEAD"),
            argsOnly = true,
            ordinal = 0
    )
    private float modifyHurtAmount(float amount, DamageSource source) {
        Player player = (Player) (Object) this;
        if (player.level().isClientSide) return amount;

        PlayerAbilities abilities = PlayerAbilitiesCapability.get(player);
        float totalReduction = 0.0F;

        for (Map.Entry<ResourceLocation, AbilityData> entry : abilities.getAll().entrySet()) {
            AbilityData data = entry.getValue();
            if (data.level <= 0) continue;

            AbstractAbility ability = AbilityRegistry.get(entry.getKey());
            if (ability == null) continue;

            if (ability instanceof DamageResistanceAbility dr) {
                totalReduction += dr.getReduction(data.level);
            }
        }

        totalReduction = Math.min(totalReduction, 0.90F);
        return amount * (1.0F - totalReduction);
    }
}
