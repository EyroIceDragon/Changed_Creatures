package net.hhdsj.changed_creatures.block;

import net.foxyas.changedaddon.block.AbstractPlushyBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LatexDeepCrystalDragonDollBlock extends AbstractPlushyBlock {

    public LatexDeepCrystalDragonDollBlock() {
        super(BlockBehaviour.Properties.of()
                .instrument(NoteBlockInstrument.BASS)
                .sound(SoundType.WOOL)
                .strength(0.8f, 0.8f)
                .noOcclusion()
                .dynamicShape()
        );
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new LatexDeepCrystalDragonDollBlockEntity(pos, state);
    }
}