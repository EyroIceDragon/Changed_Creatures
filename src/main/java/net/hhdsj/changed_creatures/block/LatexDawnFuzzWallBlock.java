package net.hhdsj.changed_creatures.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

//latex_dawn_fuzz_wall
public class LatexDawnFuzzWallBlock extends Block {
    public LatexDawnFuzzWallBlock() {
        super(BlockBehaviour.Properties.of()
                .instrument(NoteBlockInstrument.BASS)
                .sound(SoundType.WOOL)
                .strength(0.8f, 0.8f)
                .noOcclusion()
                .dynamicShape()
        );
    }
}
