package net.hhdsj.changed_creatures.block;

import net.ltxprogrammer.changed.block.TransfurCrystalBlock;
import net.ltxprogrammer.changed.entity.latex.SpreadingLatexType;
import net.ltxprogrammer.changed.init.ChangedBlocks;
import net.ltxprogrammer.changed.init.ChangedLatexTypes;
import net.ltxprogrammer.changed.world.LatexCoverState;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.IPlantable;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Supplier;

public class LatexMoltenBlock extends AbstractLatexBlock {
    public static final List<Supplier<? extends TransfurCrystalBlock>> SMALL_CRYSTALS;
    public static final List<Supplier<? extends TransfurCrystalBlock>> LARGE_CRYSTALS;

    public @NotNull LatexCoverState getLatexCoverState(BlockState blockState, BlockPos blockPos) {
        return ((SpreadingLatexType.DarkLatex)ChangedLatexTypes.DARK_LATEX.get()).sourceCoverState();
    }

    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
        BlockState plant = plantable.getPlant(world, pos.relative(facing));
        return plant.getBlock() instanceof TransfurCrystalBlock;
    }

    static {
        SMALL_CRYSTALS = List.of(ChangedBlocks.LATEX_CRYSTAL, ChangedBlocks.DARK_DRAGON_CRYSTAL, ChangedBlocks.BEIFENG_CRYSTAL_SMALL, ChangedBlocks.WOLF_CRYSTAL_SMALL);
        LARGE_CRYSTALS = List.of(ChangedBlocks.DARK_LATEX_CRYSTAL_LARGE, ChangedBlocks.BEIFENG_CRYSTAL, ChangedBlocks.WOLF_CRYSTAL, ChangedBlocks.LATEX_PUP_CRYSTAL);
    }
}
