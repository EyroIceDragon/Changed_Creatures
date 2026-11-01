package net.hhdsj.changed_creatures.event;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ChangedCreature.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ArmorSlotRestrictionHandler {

    @SubscribeEvent
    public static void onEquipmentChange(LivingEquipmentChangeEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (event.getSlot() != EquipmentSlot.FEET) return;

        ItemStack newBoots = event.getTo();
        if (newBoots.isEmpty()) return; // 不是穿靴子，忽略

        ItemStack leggings = player.getItemBySlot(EquipmentSlot.LEGS);
        if (leggings.isEmpty()) return; // 没穿裤子，允许穿靴子

        // 有裤子，拒绝靴子：把靴子退回背包，清空脚部槽位
        player.setItemSlot(EquipmentSlot.FEET, ItemStack.EMPTY);
        if (!player.getInventory().add(newBoots.copy())) {
            player.drop(newBoots.copy(), false);
        }
        player.containerMenu.broadcastChanges(); // 同步给客户端
    }
}