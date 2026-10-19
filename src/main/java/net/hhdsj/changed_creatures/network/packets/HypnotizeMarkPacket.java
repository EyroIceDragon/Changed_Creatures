package net.hhdsj.changed_creatures.network.packets;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.network.data.ClientHypnotizeData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.UUID;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class HypnotizeMarkPacket {

    private final UUID attackerUUID;
    private final int level;

    public HypnotizeMarkPacket(UUID attackerUUID, int level) {
        this.attackerUUID = attackerUUID;
        this.level = level;
    }

    public HypnotizeMarkPacket(FriendlyByteBuf buf) {
        this.attackerUUID = buf.readUUID();
        this.level = buf.readInt();
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeUUID(attackerUUID);
        buf.writeInt(level);
    }

    public void handle(Supplier<NetworkEvent.Context> ctxSup) {
        NetworkEvent.Context ctx = ctxSup.get();
        ctx.enqueueWork(() -> {
            if (ctx.getDirection().getReceptionSide().isServer()) return;
            ClientHypnotizeData.setMarked(attackerUUID, level);
        });
        ctx.setPacketHandled(true);
    }

    @SubscribeEvent
    public static void register(FMLCommonSetupEvent event) {
        ChangedCreature.addNetworkMessage(
                HypnotizeMarkPacket.class,
                HypnotizeMarkPacket::encode,
                HypnotizeMarkPacket::new,
                HypnotizeMarkPacket::handle);
    }

    // 发送给特定的受害者玩家
    public static void sendTo(ServerPlayer target, UUID attackerUUID, int level) {
        ChangedCreature.PACKET_HANDLER.send(
                PacketDistributor.PLAYER.with(() -> target),
                new HypnotizeMarkPacket(attackerUUID, level)
        );
    }
}