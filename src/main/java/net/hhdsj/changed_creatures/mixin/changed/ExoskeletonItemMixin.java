package net.hhdsj.changed_creatures.mixin.changed;

import net.hhdsj.changed_creatures.item.HidePlayerPartArmor;
import net.ltxprogrammer.changed.data.AccessorySlotType;
import net.ltxprogrammer.changed.entity.robot.AbstractRobot;
import net.ltxprogrammer.changed.item.AccessoryItem;
import net.ltxprogrammer.changed.item.ExoskeletonItem;
import net.ltxprogrammer.changed.item.ExtendedItemProperties;
import net.ltxprogrammer.changed.item.PlaceableEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Supplier;

@Mixin(ExoskeletonItem.class)
public class ExoskeletonItemMixin<T extends AbstractRobot> extends PlaceableEntity<T> implements AccessoryItem, ExtendedItemProperties {
    public ExoskeletonItemMixin(Properties builder, Supplier<EntityType<T>> entityType) {
        super(builder, entityType);
    }

    @Inject(method = "allowedInSlot",at = @At("HEAD"), cancellable = true,remap = false)
    public void allowedInSlot(ItemStack itemStack, LivingEntity wearer, AccessorySlotType slot, CallbackInfoReturnable<Boolean> cir) {
        if (itemStack.getItem() instanceof HidePlayerPartArmor){
            cir.setReturnValue(false);
        }
    }
}
