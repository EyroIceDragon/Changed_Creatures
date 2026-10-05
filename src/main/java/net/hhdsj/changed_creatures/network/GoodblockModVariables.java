package net.hhdsj.changed_creatures.network;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.util.PlayerDataGetHelper;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraft.client.Minecraft;

import java.util.UUID;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class GoodblockModVariables {

	public static double _Progress = 0;

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		ChangedCreature.addNetworkMessage(PlayerVariablesSyncMessage.class,
				PlayerVariablesSyncMessage::buffer,
				PlayerVariablesSyncMessage::new,
				PlayerVariablesSyncMessage::handler);
	}

	@SubscribeEvent
	public static void init(RegisterCapabilitiesEvent event) {
		event.register(PlayerVariables.class);
	}

	public static final Capability<PlayerVariables> PLAYER_VARIABLES_CAPABILITY =
			CapabilityManager.get(new CapabilityToken<PlayerVariables>() {});

	public static class PlayerVariables {
		public double Player_die = 0.0;
		public long Player_Infection_tick = 0;
		public Float Crystal_Jelly_Infection_Progress = 0f;
		public boolean Crystal_Jelly_Infection = false;
		public boolean Player_Is_Infection = false;
		public boolean Player_Can_Fly = false;
		public boolean Player_Can_Gliding = false;
		public boolean Player_Is_Fly = false;
		public String Latex_Contamination = "";

		public void syncPlayerVariables(Entity entity) {
			if (entity instanceof ServerPlayer serverPlayer) {
				ChangedCreature.LOGGER.info("Syncing variables for: {}", serverPlayer.getName().getString());
				ChangedCreature.LOGGER.info("Player_Can_Fly = {}", this.Player_Can_Fly);

				ChangedCreature.PACKET_HANDLER.send(
						PacketDistributor.TRACKING_ENTITY.with(() -> serverPlayer),
						new PlayerVariablesSyncMessage(this, serverPlayer.getUUID()));

				ChangedCreature.PACKET_HANDLER.send(
						PacketDistributor.PLAYER.with(() -> serverPlayer),
						new PlayerVariablesSyncMessage(this, serverPlayer.getUUID()));

				ChangedCreature.LOGGER.info("Sync complete for: {}", serverPlayer.getName().getString());
			}
		}
		public Tag writeNBT() {
			CompoundTag nbt = new CompoundTag();
			nbt.putDouble("Player_die", Player_die);
			nbt.putLong("Player_Infection_tick", Player_Infection_tick);
			nbt.putBoolean("Player_Is_Infection", Player_Is_Infection);
			nbt.putFloat("Crystal_Jelly_Infection_Progress", Crystal_Jelly_Infection_Progress);
			nbt.putBoolean("Crystal_Jelly_Infection", Crystal_Jelly_Infection);
			nbt.putBoolean("can_fly", Player_Can_Fly);
			nbt.putBoolean("can_gliding", Player_Can_Gliding);
			nbt.putBoolean("is_fly", Player_Is_Fly);
			nbt.putString("latex_contamination", Latex_Contamination);
			//
			return nbt;
		}

		public void readNBT(Tag Tag) {
			CompoundTag nbt = (CompoundTag) Tag;
			Player_die = nbt.getDouble("Player_die");
			Player_Infection_tick = nbt.getLong("Player_Infection_tick");
			Player_Is_Infection = nbt.getBoolean("Player_Is_Infection");
			Crystal_Jelly_Infection_Progress = nbt.getFloat("Crystal_Jelly_Infection_Progress");
			Crystal_Jelly_Infection = nbt.getBoolean("Crystal_Jelly_Infection");
			Player_Can_Fly = nbt.getBoolean("can_fly");
			Player_Can_Gliding = nbt.getBoolean("can_gliding");
			Player_Is_Fly = nbt.getBoolean("is_fly");
			Latex_Contamination = nbt.getString("latex_contamination");
		}
	}

	@Mod.EventBusSubscriber
	private static class PlayerVariablesProvider implements ICapabilitySerializable<Tag> {

		@SubscribeEvent
		public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
			if (event.getObject() instanceof Player && !(event.getObject() instanceof FakePlayer))
				event.addCapability(ChangedCreature.ChangedCreatureResourceLocation( "player_variables"), new PlayerVariablesProvider());
		}

		private final PlayerVariables playerVariables = new PlayerVariables();
		private final LazyOptional<PlayerVariables> instance = LazyOptional.of(() -> playerVariables);

		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
			return cap == PLAYER_VARIABLES_CAPABILITY ? instance.cast() : LazyOptional.empty();
		}

		@Override
		public Tag serializeNBT() {
			return playerVariables.writeNBT();
		}

		@Override
		public void deserializeNBT(Tag nbt) {
			playerVariables.readNBT(nbt);
		}
	}

	@Mod.EventBusSubscriber
	public static class EventBusVariableHandlers {

		@SubscribeEvent
		public static void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
			if (!event.getEntity().level().isClientSide())
				event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new PlayerVariables()).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
			if (!event.getEntity().level().isClientSide())
				event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new PlayerVariables()).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (!event.getEntity().level().isClientSide())
				event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new PlayerVariables()).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void clonePlayer(PlayerEvent.Clone event) {
			event.getOriginal().revive();

			PlayerVariables original = event.getOriginal().getCapability(PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new PlayerVariables());
			PlayerVariables clone = event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new PlayerVariables());

			clone.Player_die = original.Player_die;
			clone.Player_Infection_tick = original.Player_Infection_tick;
			clone.Player_Is_Infection = original.Player_Is_Infection;
			clone.Crystal_Jelly_Infection_Progress = original.Crystal_Jelly_Infection_Progress;
			clone.Crystal_Jelly_Infection = original.Crystal_Jelly_Infection;
			clone.Player_Can_Fly = original.Player_Can_Fly;
			clone.Player_Can_Gliding = original.Player_Can_Gliding;
			clone.Player_Is_Fly = original.Player_Is_Fly;
			clone.Latex_Contamination = original.Latex_Contamination;
		}
	}

	public static class PlayerVariablesSyncMessage {
		public PlayerVariables data;
		public UUID playerUUID;  // 添加玩家标识

		public PlayerVariablesSyncMessage(FriendlyByteBuf buffer) {
			this.playerUUID = buffer.readUUID();
			this.data = new PlayerVariables();
			this.data.readNBT(buffer.readNbt());
		}

		public PlayerVariablesSyncMessage(PlayerVariables data, UUID playerUUID) {
			this.data = data;
			this.playerUUID = playerUUID;
		}

		public static void buffer(PlayerVariablesSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeUUID(message.playerUUID);
			buffer.writeNbt((CompoundTag) message.data.writeNBT());
		}

		public static void handler(PlayerVariablesSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					Player targetPlayer = Minecraft.getInstance().level.getPlayerByUUID(message.playerUUID);
					if (targetPlayer != null) {
						PlayerVariables variables = targetPlayer
								.getCapability(PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new PlayerVariables());
						variables.Player_die = message.data.Player_die;
						variables.Player_Infection_tick = message.data.Player_Infection_tick;
						variables.Player_Is_Infection = message.data.Player_Is_Infection;
						variables.Crystal_Jelly_Infection_Progress = message.data.Crystal_Jelly_Infection_Progress;
						variables.Crystal_Jelly_Infection = message.data.Crystal_Jelly_Infection;
						variables.Player_Can_Fly = message.data.Player_Can_Fly;
						variables.Player_Can_Gliding = message.data.Player_Can_Gliding;
						variables.Player_Is_Fly = message.data.Player_Is_Fly;
						variables.Latex_Contamination = message.data.Latex_Contamination;

					}
				}
			});
			context.setPacketHandled(true);
		}
	}
}