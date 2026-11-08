package net.hhdsj.changed_creatures.ability.data;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = ChangedCreature.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VariantAbilitySyncPacket {
    private final List<ResourceLocation> abilityIds;

    public VariantAbilitySyncPacket(List<ResourceLocation> abilityIds) {
        this.abilityIds = List.copyOf(abilityIds);
    }

    public VariantAbilitySyncPacket(FriendlyByteBuf buf) {
        int size = buf.readVarInt();
        List<ResourceLocation> ids = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            ids.add(buf.readResourceLocation());
        }
        this.abilityIds = List.copyOf(ids);
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeVarInt(abilityIds.size());
        for (ResourceLocation abilityId : abilityIds) {
            buf.writeResourceLocation(abilityId);
        }
    }

    public void handle(Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            if (context.getDirection().getReceptionSide().isServer()) return;
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () ->
                    net.hhdsj.changed_creatures.client.gui.AbilityScreen
                            .setSyncedVariantAbilities(abilityIds));
        });
        context.setPacketHandled(true);
    }

    @SubscribeEvent
    public static void register(FMLCommonSetupEvent event) {
        ChangedCreature.addNetworkMessage(
                VariantAbilitySyncPacket.class,
                VariantAbilitySyncPacket::encode,
                VariantAbilitySyncPacket::new,
                VariantAbilitySyncPacket::handle);
    }

    public static void send(ServerPlayer player, List<ResourceLocation> abilityIds) {
        ChangedCreature.PACKET_HANDLER.send(
                PacketDistributor.PLAYER.with(() -> player),
                new VariantAbilitySyncPacket(abilityIds));
    }
}
