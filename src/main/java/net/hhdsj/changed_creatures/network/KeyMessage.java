
package net.hhdsj.changed_creatures.network;

import net.hhdsj.changed_creatures.entity.PartiallyTransfurVariant;
import net.hhdsj.changed_creatures.util.PlayerDataGetHelper;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;

import net.hhdsj.changed_creatures.procedures.LOOKGUIProcedure;
import net.hhdsj.changed_creatures.ChangedCreature;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class KeyMessage {
	int type, pressedms;

	public KeyMessage(int type, int pressedms) {
		this.type = type;
		this.pressedms = pressedms;
	}

	public KeyMessage(FriendlyByteBuf buffer) {
		this.type = buffer.readInt();
		this.pressedms = buffer.readInt();
	}

	public static void buffer(KeyMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.type);
		buffer.writeInt(message.pressedms);
	}

	public static void handler(KeyMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			pressAction(context.getSender(), message.type, message.pressedms);
		});
		context.setPacketHandled(true);
	}

	public static void pressAction(Player entity, int type, int pressedms) {
		Level world = entity.level();
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(entity.blockPosition()))
			return;

		switch (type) {
			case 0:
				LOOKGUIProcedure.execute(x, y, z, entity);
				break;
			case 1:
				if (!world.isClientSide && entity != null) {
					PartiallyTransfurVariant.setServerJumpPressed(entity.getUUID());
				}
				break;
			case 2:
				GoodblockModVariables.PlayerVariables data = PlayerDataGetHelper.get(entity);
				data.syncPlayerVariables(entity);
				boolean can_fly = PlayerDataGetHelper.GetPlayerCanFly(entity);
				boolean currentGlide = PlayerDataGetHelper.GetPlayerCanGliding(entity);
				if (can_fly) {
					PlayerDataGetHelper.SetPlayerCanGliding(entity, !currentGlide);
					PartiallyTransfurVariant.manageGliding(entity);
				}

				break;
			default :
				break;
		}

	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		ChangedCreature.addNetworkMessage(KeyMessage.class, KeyMessage::buffer, KeyMessage::new, KeyMessage::handler);
	}
}
