package net.hhdsj.changed_creatures.block.pale_blocks;

import net.hhdsj.changed_creatures.world.PaleTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class Paletreespa1Block extends SaplingBlock {
	public Paletreespa1Block() {
		super(new PaleTreeGrower(), Properties.of()
				.mapColor(MapColor.PLANT)
				.strength(0.5F)
				.noOcclusion()
				.randomTicks()
				.sound(SoundType.GRASS)
		);
	}

	@Override
	protected boolean mayPlaceOn(BlockState groundState, BlockGetter level, BlockPos pos) {
		return groundState.is(Blocks.GRASS_BLOCK) ||
				groundState.is(Blocks.DIRT) ||
				groundState.is(Blocks.COARSE_DIRT) ||
				groundState.is(Blocks.PODZOL);
	}
}