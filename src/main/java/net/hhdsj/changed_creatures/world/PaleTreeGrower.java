package net.hhdsj.changed_creatures.world;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class PaleTreeGrower extends AbstractTreeGrower {

    public PaleTreeGrower() {
    }

    @Override
    protected @Nullable ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean hasFlowers) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ChangedCreature.CCRL("changed_creatures", "pale_tree"));
    }
}