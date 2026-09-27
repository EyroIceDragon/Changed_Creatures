package net.hhdsj.changed_creatures.mixin.Player;

import net.hhdsj.changed_creatures.ability.data.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(FoodData.class)
public class FoodDataMixin {

    @Inject(method = "eat(Lnet/minecraft/world/item/Item;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/LivingEntity;)V",
            at = @At("TAIL"),remap = false)
    private void afterEat(Item item, ItemStack stack, LivingEntity entity, CallbackInfo ci) {
        if (entity instanceof Player player){
            if (player.level().isClientSide()) return;
            PlayerAbilities abilities = PlayerAbilitiesCapability.get(player);
            for (Map.Entry<ResourceLocation, AbilityData> entry : abilities.getAll().entrySet()) {
                AbilityData data = entry.getValue();
                if (data.level <= 0) continue;
                AbstractAbility ability = AbilityRegistry.get(entry.getKey());
                if (ability == null) continue;
                ability.onFinishEat(player,data.level,stack);
            }
        }
    }
}
