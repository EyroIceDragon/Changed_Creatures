package net.hhdsj.changed_creatures.ability.data;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
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
    public static void onPlayerHurt(LivingHurtEvent event) {
//        LivingEntity liv = event.getEntity();//受伤者
//        Entity attacker = event.getSource().getEntity();//攻击者
//        if (liv.level().isClientSide()) return;
//        if (!(attacker instanceof Player player)) return;
//
//        PlayerAbilities abilities = PlayerAbilitiesCapability.get(player);
//
//        for (Map.Entry<ResourceLocation, AbilityData> entry : abilities.getAll().entrySet()) {
//            AbilityData data = entry.getValue();
//
//            if (data.level <= 0) continue;
//
//            AbstractAbility ability = AbilityRegistry.get(entry.getKey());
//            if (ability == null) continue;
//
//            ability.onHurt(player, liv, data.level);
//        }
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
        AbilitySyncPacket.SendAllAbilitiesPack(player);
    }
}