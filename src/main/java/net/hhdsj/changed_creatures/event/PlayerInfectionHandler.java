package net.hhdsj.changed_creatures.event;

import net.hhdsj.changed_creatures.network.ChangedCreaturesModVariables;
import net.hhdsj.changed_creatures.util.PlayerDataGetHelper;
import net.ltxprogrammer.changed.init.ChangedTags;
import net.ltxprogrammer.changed.process.ProcessTransfur;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PlayerInfectionHandler {
    @SubscribeEvent
    public static void onPlayerDamage(LivingDamageEvent event) {

        if (!(event.getEntity() instanceof Player player)) return;
        if (event.getSource().is(ChangedTags.DamageTypes.IS_TRANSFUR)) {
            if (ProcessTransfur.getPlayerTransfurVariant(player) == null) {
                ChangedCreaturesModVariables.PlayerVariables data = PlayerDataGetHelper.get(player);
                data.Player_Is_Infection = true;
            }
        }
    }
}
