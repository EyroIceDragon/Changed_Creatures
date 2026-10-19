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
import net.minecraftforge.registries.RegistryObject;

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

            if (data.level <= 0) continue;

            AbstractAbility ability = AbilityRegistry.get(id);
            if (ability == null) continue;

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

    private static List<RegistryObject<AbstractAbility>> getDefaultAbilitiesForPlayer(Player player) {
        return VariantAbilityMap.DEFAULT_ABILITIES;
    }

    public static void sendPlayerAbilityData(Player player) {
        if (player.level().isClientSide()) return;
        if (!(player instanceof ServerPlayer sp)) return;

        var instance = ProcessTransfur.getPlayerTransfurVariant(player);

        if (instance == null) {
            ChangedCreature.LOGGER.info("[ChangedCreatures] Player {} logged in without transfur variant, skipping ability init", player.getName().getString());
            return;
        }

        PlayerAbilities abilities = PlayerAbilitiesCapability.get(player);

        List<RegistryObject<AbstractAbility>> defaultAbilities =
                VariantAbilityMap.getFor(instance.getFormId());

        boolean needsSync = false;
        for (RegistryObject<AbstractAbility> obj : defaultAbilities) {
            ResourceLocation id = obj.getId();
            if (!abilities.getAll().containsKey(id)) {
                abilities.get(id);
                needsSync = true;
            }
        }

        if (needsSync) {
            AbilitySyncPacket.SendAllAbilitiesPack(player);
            GlobalExpSyncPacket.send(sp);
        }
    }
}