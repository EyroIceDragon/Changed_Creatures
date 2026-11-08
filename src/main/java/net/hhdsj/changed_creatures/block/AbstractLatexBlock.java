package net.hhdsj.changed_creatures.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;

public class AbstractLatexBlock extends Block {
    public AbstractLatexBlock() {
        super(Properties.of().sound(SoundType.SLIME_BLOCK));
    }
}
