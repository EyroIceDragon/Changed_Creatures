package net.hhdsj.changed_creatures.network;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.event.CrystalRingHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;
import java.util.function.Supplier;

public class SyncCrystalRingPacket {
    private final int entityId;
    private final boolean active;

    public SyncCrystalRingPacket(int entityId, boolean active) {
        this.entityId = entityId;
        this.active = active;
    }

    public SyncCrystalRingPacket(FriendlyByteBuf buf) {
        this.entityId = buf.readInt();
        this.active = buf.readBoolean();
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(entityId);
        buf.writeBoolean(active);
    }

    public static void register() {
        ChangedCreature.addNetworkMessage(
                SyncCrystalRingPacket.class,
                SyncCrystalRingPacket::encode,
                SyncCrystalRingPacket::new,
                SyncCrystalRingPacket::handle
        );
    }

    private static void handle(SyncCrystalRingPacket pkt, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Entity e = null;
            if (net.minecraftforge.fml.loading.FMLEnvironment.dist.isClient()) {
                e = ClientRingHandler.getEntity(pkt.entityId);
            }
            if (e instanceof Player p)
                p.getCapability(CrystalRingHandler.CAPABILITY).ifPresent(c -> c.setActive(pkt.active));
        });
        ctx.get().setPacketHandled(true);
    }

    // 客户端侧取实体逻辑单独成类，避免服务端专用环境下加载 Minecraft/Client 类。
    static class ClientRingHandler {
        static Entity getEntity(int entityId) {
            net.minecraft.client.Minecraft mc = net.minecraft.client.Minecraft.getInstance();
            return mc.level != null ? mc.level.getEntity(entityId) : null;
        }
    }

    public static void sync(ServerPlayer player) {
        player.getCapability(CrystalRingHandler.CAPABILITY).ifPresent(c -> {
            SyncCrystalRingPacket pkt = new SyncCrystalRingPacket(player.getId(), c.isActive());
            ChangedCreature.PACKET_HANDLER.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player), pkt);
        });
    }
}