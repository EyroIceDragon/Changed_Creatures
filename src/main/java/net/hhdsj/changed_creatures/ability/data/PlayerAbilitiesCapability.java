package net.hhdsj.changed_creatures.ability.data;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ChangedCreature.MODID)
public class PlayerAbilitiesCapability {

    public static final Capability<PlayerAbilities> PLAYER_ABILITIES =
            CapabilityManager.get(new CapabilityToken<PlayerAbilities>() {});

    public static final ResourceLocation CAP_ID =
            new ResourceLocation(ChangedCreature.MODID, "player_abilities");

    // ---------- 注册 Capability 类 ----------
    @Mod.EventBusSubscriber(modid = ChangedCreature.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModBus {
        @SubscribeEvent
        public static void register(RegisterCapabilitiesEvent event) {
            event.register(PlayerAbilities.class);
        }
    }

    // ---------- 挂到玩家身上 ----------
    @SubscribeEvent
    public static void onAttach(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player && !(event.getObject() instanceof FakePlayer)) {
            event.addCapability(CAP_ID, new PlayerAbilitiesProvider());
        }
    }

    // ---------- 便捷访问 ----------
    public static PlayerAbilities get(Player player) {
        return player.getCapability(PLAYER_ABILITIES, null)
                .orElse(new PlayerAbilities());
    }
}