package net.hhdsj.changed_creatures.event.changed;

import net.hhdsj.changed_creatures.ability.data.AbilityEvents;
import net.ltxprogrammer.changed.process.ProcessTransfur;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PlayerTransfurHandler {

    @SubscribeEvent
    public static void onVariantChanged(ProcessTransfur.EntityVariantAssigned.ChangedVariant event) {
        if (event.livingEntity instanceof Player player) {
            AbilityEvents.sendPlayerAbilityData(player);
        }
    }
}
