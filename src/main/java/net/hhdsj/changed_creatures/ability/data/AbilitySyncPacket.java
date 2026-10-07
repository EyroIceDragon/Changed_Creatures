package net.hhdsj.changed_creatures.ability.data;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AbilitySyncPacket {

    private final ResourceLocation abilityId;
    private final int level;
    private final int exp;
    private final long cooldown;
    private final int playerExp;

    public AbilitySyncPacket(ResourceLocation abilityId, AbilityData data, int playerExp) {
        this.abilityId = abilityId;
        this.level = data.level;
        this.exp = data.exp;
        this.cooldown = data.cooldown;
        this.playerExp = playerExp;
    }

    public AbilitySyncPacket(FriendlyByteBuf buf) {
        this.abilityId = buf.readResourceLocation();
        this.level = buf.readInt();
        this.exp = buf.readInt();
        this.cooldown = buf.readLong();
        this.playerExp = buf.readInt();
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeResourceLocation(abilityId);
        buf.writeInt(level);
        buf.writeInt(exp);
        buf.writeLong(cooldown);
        buf.writeInt(playerExp);
    }

    public void handle(Supplier<NetworkEvent.Context> ctxSup) {
        NetworkEvent.Context ctx = ctxSup.get();
        ctx.enqueueWork(() -> {
            if (ctx.getDirection().getReceptionSide().isServer()) return;

            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () ->
                    ClientHandler.handle(this));
        });
        ctx.setPacketHandled(true);
    }

    @SubscribeEvent
    public static void register(FMLCommonSetupEvent event) {
        ChangedCreature.addNetworkMessage(
                AbilitySyncPacket.class,
                AbilitySyncPacket::encode,
                AbilitySyncPacket::new,
                AbilitySyncPacket::handle);
    }



    public static void SendAllAbilitiesPack(Player player) {
        if (!(player instanceof ServerPlayer sp)) return;
        PlayerAbilities abilities = PlayerAbilitiesCapability.get(sp);
        int exp = abilities.getPlayerExp();
        for (var entry : abilities.getAll().entrySet()) {
            ChangedCreature.PACKET_HANDLER.send(
                    PacketDistributor.PLAYER.with(() -> sp),
                    new AbilitySyncPacket(entry.getKey(), entry.getValue(), exp));
        }
    }

    private static class ClientHandler {
        static void handle(AbilitySyncPacket packet) {
            Player player = net.minecraft.client.Minecraft.getInstance().player;
            if (player == null) return;

            PlayerAbilities abilities = PlayerAbilitiesCapability.get(player);
            AbilityData data = abilities.get(packet.abilityId);
            data.level = packet.level;
            data.exp = packet.exp;
            data.cooldown = packet.cooldown;
            abilities.setPlayerExp(packet.playerExp);
        }
    }
}