package net.hhdsj.changed_creatures.network;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.ability.data.AbilitySyncPacket;
import net.hhdsj.changed_creatures.ability.data.PlayerAbilities;
import net.hhdsj.changed_creatures.ability.data.PlayerAbilitiesCapability;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AbilitiesMessage {

	private final ResourceLocation abilityId;  // ★ 用 ResourceLocation 而不是 int
	private final int action;                   // 0=减，1=加

	public AbilitiesMessage(ResourceLocation abilityId, int action) {
		this.abilityId = abilityId;
		this.action = action;
	}

	public AbilitiesMessage(FriendlyByteBuf buffer) {
		this.abilityId = buffer.readResourceLocation();
		this.action = buffer.readInt();
	}

	public static void buffer(AbilitiesMessage msg, FriendlyByteBuf buffer) {
		buffer.writeResourceLocation(msg.abilityId);
		buffer.writeInt(msg.action);
	}

	public static void handler(AbilitiesMessage msg,
	                           Supplier<NetworkEvent.Context> ctxSup) {
		NetworkEvent.Context ctx = ctxSup.get();
		ctx.enqueueWork(() -> {
			ServerPlayer player = ctx.getSender();
			if (player == null) return;
			Action(player, msg.abilityId, msg.action);
		});
		ctx.setPacketHandled(true);
	}

	// ---------- 服务端处理 ----------
	public static void Action(ServerPlayer player, ResourceLocation abilityId, int action) {
		if (!player.level().hasChunkAt(player.blockPosition())) return;

		PlayerAbilities abilities = PlayerAbilitiesCapability.get(player);

		switch (action) {
			case 0 -> {
				int lv = abilities.getLevel(abilityId);
				abilities.setLevel(abilityId, Math.max(0, lv - 1));
			}
			case 1 -> {
				int lv = abilities.getLevel(abilityId);
				abilities.setLevel(abilityId, Math.min(10, lv + 1));
			}
			default -> {
				return;
			}
		}

		// 同步回客户端
		ChangedCreature.PACKET_HANDLER.send(
				PacketDistributor.PLAYER.with(() -> player),
				new AbilitySyncPacket(abilityId, abilities.get(abilityId))
		);
	}

	@SubscribeEvent
	public static void register(FMLCommonSetupEvent event) {
		ChangedCreature.addNetworkMessage(
				AbilitiesMessage.class,
				AbilitiesMessage::buffer,
				AbilitiesMessage::new,
				AbilitiesMessage::handler);
	}
}