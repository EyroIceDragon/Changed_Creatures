package net.hhdsj.changed_creatures.mixin.changed;

import net.hhdsj.changed_creatures.item.HidePlayerPartArmor;
import net.ltxprogrammer.changed.entity.decoration.AbstractArmorStand;
import net.ltxprogrammer.changed.entity.variant.EntityShape;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractArmorStand.class)
public abstract class AbstractArmorStandMixin extends ArmorStand implements EntityShape.Provider {
    public AbstractArmorStandMixin(EntityType<? extends ArmorStand> p_31553_, Level p_31554_) {
        super(p_31553_, p_31554_);
    }

    @Inject(method = "swapItem",at = @At("HEAD"), cancellable = true)
    protected void swapItem(Player player, EquipmentSlot slot, ItemStack itemStack, InteractionHand hand, CallbackInfoReturnable<Boolean> cir) {
        if (itemStack.getItem() instanceof HidePlayerPartArmor) {
            player.displayClientMessage(Component.literal("你无法装备这个给盔甲架"), true);
            cir.cancel();
        }
    }
}
