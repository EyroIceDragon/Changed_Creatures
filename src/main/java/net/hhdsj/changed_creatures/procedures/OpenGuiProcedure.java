package net.hhdsj.changed_creatures.procedures;

import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.MenuProvider;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.hhdsj.changed_creatures.world.inventory.FINDGUIMenu;

import io.netty.buffer.Unpooled;
import org.jetbrains.annotations.NotNull;

public class OpenGuiProcedure {
	public static void execute(double x, double y, double z, Entity entity) {
		if (entity == null)
			return;

		// 打开GUI
		if (entity instanceof ServerPlayer serverPlayer) {
			BlockPos blockPos = BlockPos.containing(x, y, z);
			NetworkHooks.openScreen(serverPlayer, new MenuProvider() {
				@Override
				public @NotNull Component getDisplayName() {
					return Component.literal("FINDGUI");
				}

				@Override
				public AbstractContainerMenu createMenu(int id, @NotNull Inventory inventory, @NotNull Player player) {
					return new FINDGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(blockPos));
				}
			}, blockPos);
		}

		Advancement useModAdvancement = null;
		if (entity instanceof ServerPlayer serverPlayer && serverPlayer.level() instanceof ServerLevel) {
			useModAdvancement = serverPlayer.server.getAdvancements().getAdvancement(new ResourceLocation("changed_creatures:usemod"));
			if (useModAdvancement != null) {
				AdvancementProgress progress = serverPlayer.getAdvancements().getOrStartProgress(useModAdvancement);
				if (!progress.isDone()) {
                    Player player = (Player) entity;
                    //ItemStack inkSansItem = new ItemStack(ChangedCreatureModItems.INKSANS_4.get());
                    //inkSansItem.setCount(1);
                    //ItemHandlerHelper.giveItemToPlayer(player, inkSansItem);
                }
			}
		}

		if (entity instanceof ServerPlayer serverPlayer) {
			if (useModAdvancement == null) {
				useModAdvancement = serverPlayer.server.getAdvancements().getAdvancement(new ResourceLocation("changed_creatures:usemod"));
			}
			if (useModAdvancement != null) {
				AdvancementProgress progress = serverPlayer.getAdvancements().getOrStartProgress(useModAdvancement);
				if (!progress.isDone()) {
					for (String criteria : progress.getRemainingCriteria()) {
						serverPlayer.getAdvancements().award(useModAdvancement, criteria);
					}
				}
			}
		}
	}
}