package net.hhdsj.changed_creatures.ability.data;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.SubscribeEvent;
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

    public AbilitySyncPacket(ResourceLocation abilityId, AbilityData data) {
        this.abilityId = abilityId;
        this.level = data.level;
        this.exp = data.exp;
        this.cooldown = data.cooldown;
    }

    public AbilitySyncPacket(FriendlyByteBuf buf) {
        this.abilityId = buf.readResourceLocation();
        this.level = buf.readInt();
        this.exp = buf.readInt();
        this.cooldown = buf.readLong();
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeResourceLocation(abilityId);
        buf.writeInt(level);
        buf.writeInt(exp);
        buf.writeLong(cooldown);
    }

    public void handle(Supplier<NetworkEvent.Context> ctxSup) {
        NetworkEvent.Context ctx = ctxSup.get();
        ctx.enqueueWork(() -> {
            if (ctx.getDirection().getReceptionSide().isServer()) return;

            Player player = Minecraft.getInstance().player;
            if (player == null) return;

            PlayerAbilities abilities = PlayerAbilitiesCapability.get(player);
            AbilityData data = abilities.get(abilityId);
            data.level = level;
            data.exp = exp;
            data.cooldown = cooldown;
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



    public static void SendAllAbilitiesPack (Player player){
        if (!(player instanceof ServerPlayer sp)) return;
        PlayerAbilities abilities = PlayerAbilitiesCapability.get(sp);
        for (var entry : abilities.getAll().entrySet()) {
            ChangedCreature.PACKET_HANDLER.send(
                    PacketDistributor.PLAYER.with(() -> sp),
                    new AbilitySyncPacket(entry.getKey(), entry.getValue()));
        }
    }
}