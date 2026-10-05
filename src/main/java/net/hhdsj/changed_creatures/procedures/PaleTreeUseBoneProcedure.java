package net.hhdsj.changed_creatures.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.hhdsj.changed_creatures.init.ChangedCreatureModBlocks;

public class PaleTreeUseBoneProcedure {
	public static InteractionResult execute(LevelAccessor world, double x, double y, double z, Direction direction, ItemStack itemstack) {
		if (direction == null)
			return InteractionResult.PASS;
		if (direction == Direction.UP && ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.GRASS_BLOCK || (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.DIRT)) {
			if (ChangedCreatureModBlocks.PALETREESPA_1.get().defaultBlockState().canSurvive(world, BlockPos.containing(x, y + 1, z)) && (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.AIR) {
				(itemstack).shrink(1);
				world.setBlock(BlockPos.containing(x, y + 1, z), ChangedCreatureModBlocks.PALETREESPA_1.get().defaultBlockState(), 3);
				return InteractionResult.SUCCESS;
			}
		}
		return InteractionResult.FAIL;
	}
}
