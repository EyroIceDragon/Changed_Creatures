package net.hhdsj.changed_creatures.network;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.ability.data.*;
import net.ltxprogrammer.changed.entity.variant.TransfurVariant;
import net.ltxprogrammer.changed.init.ChangedRegistry;
import net.ltxprogrammer.changed.process.ProcessTransfur;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
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
		if (player == null || !player.isAlive() || player.isSpectator()) return;
		if (!player.level().hasChunkAt(player.blockPosition())) return;

		AbstractAbility ability = AbilityRegistry.get(abilityId);
		if (ability == null) return;

		if (!isAbilityAllowedForPlayer(player, abilityId)) return;

		PlayerAbilities abilities = PlayerAbilitiesCapability.get(player);

		if (!abilities.hasAbility(abilityId)) return;

		AbilityData data = abilities.get(abilityId);

		switch (action) {
			case 0 -> {
				if (data.level <= 0) return;
				abilities.setLevel(abilityId, data.level - 1);
			}
			case 1 -> {
				if (data.level >= ability.getMaxLevel()) return;
				if (!abilities.enoughExp(ability, data.level)) return;
				abilities.consumeExp(ability, data.level);
				abilities.setLevel(abilityId, data.level + 1);
			}
			default -> {
				return;
			}
		}

		ChangedCreature.PACKET_HANDLER.send(
				PacketDistributor.PLAYER.with(() -> player),
				new AbilitySyncPacket(abilityId, abilities.get(abilityId),abilities.getPlayerExp())
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

	private static boolean isAbilityAllowedForPlayer(ServerPlayer player, ResourceLocation abilityId) {
		var instance = ProcessTransfur.getPlayerTransfurVariant(player);
		if (instance == null) return false;
		TransfurVariant<?> variant = instance.getParent();
		if (variant == null) return false;

		ResourceLocation variantId = ChangedRegistry.TRANSFUR_VARIANT.get().getKey(variant);
		if (variantId == null) return false;

		List<RegistryObject<AbstractAbility>> list = VariantAbilityMap.getFor(variantId);
		return list.stream().anyMatch(obj -> obj.getId().equals(abilityId));
	}
}