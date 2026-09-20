package net.hhdsj.changed_creatures.ability.data;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;

@Mod.EventBusSubscriber(modid = ChangedCreature.MODID)
public class AbilityEvents {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Player player = event.player;

        if (player.level().isClientSide()) return;

        PlayerAbilities abilities = PlayerAbilitiesCapability.get(player);

        for (Map.Entry<net.minecraft.resources.ResourceLocation, AbilityData> entry : abilities.getAll().entrySet()) {

            var id = entry.getKey();
            AbilityData data = entry.getValue();

            if (data.level <= 0) continue;

            AbstractAbility ability = AbilityRegistry.get(id);
            if (ability == null) continue;

            ability.onTick(player, data.level);
        }
    }

    @SubscribeEvent
    public static void onPlayerDamage(LivingDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) return;

        Entity attacker = event.getSource().getEntity();
        Player player = (Player) event.getEntity();
        if (player.level().isClientSide()) return;

        PlayerAbilities abilities = PlayerAbilitiesCapability.get(player);

        for (Map.Entry<net.minecraft.resources.ResourceLocation, AbilityData> entry : abilities.getAll().entrySet()) {

            var id = entry.getKey();
            AbilityData data = entry.getValue();
            if (data.level <= 0) continue;
            AbstractAbility ability = AbilityRegistry.get(id);
            if (ability == null) continue;

            ability.onHurt(player,attacker,data.level);
        }
    }
}