package net.hhdsj.changed_creatures.event;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.item.HidePlayerPartArmor;
import net.ltxprogrammer.changed.process.ProcessTransfur;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ChangedCreature.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ArmorSlotRestrictionHandler {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.side != LogicalSide.SERVER) return;
        if (event.phase != TickEvent.Phase.END) return;

        Player player = event.player;
        if (player.isCreative() || player.isSpectator()) return;

        boolean transfurred = ProcessTransfur.getPlayerTransfurVariant(player) != null;

        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.getType() != EquipmentSlot.Type.ARMOR) continue;

            ItemStack stack = player.getItemBySlot(slot);
            if (stack.isEmpty()) continue;

            boolean shouldRemove = false;

            // transfur 状态下，任何 HidePlayerPartArmor 都不许穿
            if (transfurred && stack.getItem() instanceof HidePlayerPartArmor) {
                shouldRemove = true;
            }

            // 任何情况下，只要 LEGS 槽是兽装护腿，就把 FEET 槽的鞋子脱掉
            if (!shouldRemove && slot == EquipmentSlot.FEET
                    && player.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof HidePlayerPartArmor) {
                shouldRemove = true;
            }

            if (shouldRemove) {
                player.setItemSlot(slot, ItemStack.EMPTY);
                if (!player.getInventory().add(stack.copy())) {
                    player.drop(stack.copy(), false);
                }
                player.containerMenu.broadcastChanges();
            }
        }
    }
}