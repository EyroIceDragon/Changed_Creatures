package net.hhdsj.changed_creatures.block;

import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class MoltenCrystalBlock extends Block {
    public MoltenCrystalBlock() {
        super(BlockBehaviour.Properties.of()
                .sound(SoundType.GLASS)
                .strength(0.8F)
                .isValidSpawn((state, level, pos, type) -> type == EntityType.POLAR_BEAR)
                .friction(0.98F)
                .destroyTime(0.7F)
                .explosionResistance(0.5F)
                .lightLevel(state -> 0)
        );
    }

    @Override
    public boolean skipRendering(BlockState state, BlockState adjacentState, Direction side) {
        return adjacentState.is(this) || super.skipRendering(state, adjacentState, side);
    }
}
