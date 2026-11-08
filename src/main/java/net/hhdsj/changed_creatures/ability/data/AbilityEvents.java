package net.hhdsj.changed_creatures.ability.data;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.ltxprogrammer.changed.process.ProcessTransfur;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(modid = ChangedCreature.MODID)
public class AbilityEvents {
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Player player = event.player;

        if (player.level().isClientSide()) return;

        PlayerAbilities abilities = PlayerAbilitiesCapability.get(player);

        for (Map.Entry<ResourceLocation, AbilityData> entry : abilities.getAll().entrySet()) {

            var id = entry.getKey();
            AbilityData data = entry.getValue();

            AbstractAbility ability = AbilityRegistry.get(id);
            if (ability == null) continue;

            if (data.level <= 0) {
                ability.onRemove(player);
                continue;
            }

            ability.onTick(player, data.level);
        }
    }

    @SubscribeEvent
    public void onAttackEntity(AttackEntityEvent event) {

    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (player.level().isClientSide()) return;
        PlayerAbilities abilities = PlayerAbilitiesCapability.get(player);
        abilities.addPlayerExp(10);
        if (player instanceof ServerPlayer sp) {
            GlobalExpSyncPacket.send(sp);
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        sendPlayerAbilityData(player);
    }

    public static void sendPlayerAbilityData(Player player) {
        if (player.level().isClientSide()) return;
        if (!(player instanceof ServerPlayer sp)) return;

        var instance = ProcessTransfur.getPlayerTransfurVariant(player);

        if (instance == null) {
            ChangedCreature.LOGGER.debug(
                    "[ChangedCreatures] Deferring ability sync for {} until a transfur variant is available",
                    player.getName().getString());
            return;
        }

        PlayerAbilities abilities = PlayerAbilitiesCapability.get(player);
        List<ResourceLocation> variantAbilities =
                VariantAbilityMap.getFor(instance.getFormId());

        for (ResourceLocation id : variantAbilities) {
            if (!abilities.getAll().containsKey(id)) {
                abilities.get(id);
            }
        }

        VariantAbilitySyncPacket.send(sp, variantAbilities);
        AbilitySyncPacket.SendAllAbilitiesPack(sp);
        GlobalExpSyncPacket.send(sp);
    }
}