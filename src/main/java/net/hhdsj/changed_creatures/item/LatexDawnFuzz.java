package net.hhdsj.changed_creatures.item;

import net.ltxprogrammer.changed.process.ProcessTransfur;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

//latex_dawn_fuzz
public class LatexDawnFuzz extends Item {
    public LatexDawnFuzz() {
        super(new Item.Properties().stacksTo(64));
    }

    @Override
    public void inventoryTick(ItemStack itemstack, Level world, @NotNull Entity entity, int slot, boolean selected) {
        super.inventoryTick(itemstack, world, entity, slot, selected);
        if (entity instanceof Player player) {
            if (ProcessTransfur.getPlayerTransfurVariant(player) == null) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 0));
            }else{
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20, 1));
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 20, 0));
            }
        }
    }
}
