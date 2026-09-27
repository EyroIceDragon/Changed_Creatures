package net.hhdsj.changed_creatures.ability.data;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class GlobalExpSyncPacket {

    private final int playerExp;

    public GlobalExpSyncPacket(int playerExp) {
        this.playerExp = playerExp;
    }

    public GlobalExpSyncPacket(FriendlyByteBuf buf) {
        this.playerExp = buf.readInt();
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(playerExp);
    }

    public void handle(Supplier<NetworkEvent.Context> ctxSup) {
        NetworkEvent.Context ctx = ctxSup.get();
        ctx.enqueueWork(() -> {
            if (ctx.getDirection().getReceptionSide().isServer()) return;

            Player player = Minecraft.getInstance().player;
            if (player == null) return;

            PlayerAbilities abilities = PlayerAbilitiesCapability.get(player);
            abilities.setPlayerExp(playerExp);
        });
        ctx.setPacketHandled(true);
    }

    @SubscribeEvent
    public static void register(FMLCommonSetupEvent event) {
        ChangedCreature.addNetworkMessage(
                GlobalExpSyncPacket.class,
                GlobalExpSyncPacket::encode,
                GlobalExpSyncPacket::new,
                GlobalExpSyncPacket::handle);
    }

    public static void send(ServerPlayer player) {
        PlayerAbilities abilities = PlayerAbilitiesCapability.get(player);
        ChangedCreature.PACKET_HANDLER.send(
                PacketDistributor.PLAYER.with(() -> player),
                new GlobalExpSyncPacket(abilities.getPlayerExp()));
    }
}