
package net.hhdsj.changed_creatures.network;

import net.hhdsj.changed_creatures.init.ChangedCreatureModTags;
import net.hhdsj.changed_creatures.util.PlayerDataGetHelper;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.hhdsj.changed_creatures.world.inventory.FINDGUIMenu;
import net.hhdsj.changed_creatures.ChangedCreature;

import java.util.function.Supplier;
import java.util.HashMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CCGuiButtonMessage {
	private final int buttonID, x, y, z;

	public CCGuiButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
	}

	public CCGuiButtonMessage(int buttonID, int x, int y, int z) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static void buffer(CCGuiButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(CCGuiButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			Player entity = context.getSender();
			int buttonID = message.buttonID;
			int x = message.x;
			int y = message.y;
			int z = message.z;
			handleButtonAction(entity, buttonID, x, y, z);
		});
		context.setPacketHandled(true);
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level();

		if (world.isClientSide()) return;

		HashMap<String, Object> guistate = FINDGUIMenu.guistate;
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {;
			System.out.println(ChangedCreatureModTags.slowInfectionVariants().location());
			GoodblockModVariables.PlayerVariables data = PlayerDataGetHelper.get(entity);
			data.Player_Is_Infection = !data.Player_Is_Infection;
			PlayerDataGetHelper.SetPlayerCanFly(entity,!PlayerDataGetHelper.GetPlayerCanFly(entity));
			System.out.println(PlayerDataGetHelper.GetPlayerCanFly(entity));

		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		ChangedCreature.addNetworkMessage(CCGuiButtonMessage.class, CCGuiButtonMessage::buffer, CCGuiButtonMessage::new, CCGuiButtonMessage::handler);
	}
}
