package net.hhdsj.changed_creatures.event.changed;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.ability.data.PlayerAbilitiesCapability;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.hhdsj.changed_creatures.ability.data.AbilitySyncPacket.SendAllAbilitiesPack;

@Mod.EventBusSubscriber(modid = ChangedCreature.MODID)
public class WhatPlayerLoginHandler {

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        SendAllAbilitiesPack(event.getEntity());
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        SendAllAbilitiesPack(event.getEntity());
    }

    @SubscribeEvent
    public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        SendAllAbilitiesPack(event.getEntity());
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        event.getOriginal().revive();

        var original = PlayerAbilitiesCapability.get(event.getOriginal());
        var clone = PlayerAbilitiesCapability.get(event.getEntity());
        clone.copyFrom(original);
    }
}