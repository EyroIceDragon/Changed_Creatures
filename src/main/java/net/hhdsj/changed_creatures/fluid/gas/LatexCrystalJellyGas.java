package net.hhdsj.changed_creatures.fluid.gas;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.init.ChangedCreatureModBlocks;
import net.hhdsj.changed_creatures.init.ChangedCreatureModFluids;
import net.hhdsj.changed_creatures.init.ChangedCreatureModTransfurVariants;
import net.ltxprogrammer.changed.fluid.Gas;
import net.ltxprogrammer.changed.fluid.TransfurGas;
import net.ltxprogrammer.changed.util.Color3;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class LatexCrystalJellyGas extends TransfurGas {
    public static final ForgeFlowingFluid.Properties PROPERTIES;

    public static FluidType createFluidType() {
        return new Gas.GasFluidType(Gas.createProperties().descriptionId("latex_crystal_jelly_gas").rarity(Rarity.RARE)) {
            public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                consumer.accept(new IClientFluidTypeExtensions() {
                    private static final ResourceLocation GAS_STILL = ChangedCreature.CCRL("block/latex_crystal_jelly_gas");
                    private static final ResourceLocation GAS_FLOW = ChangedCreature.CCRL("block/latex_crystal_jelly_gas");

                    public ResourceLocation getStillTexture() {
                        return GAS_STILL;
                    }

                    public ResourceLocation getFlowingTexture() {
                        return GAS_FLOW;
                    }

                    public int getTintColor() {
                        return 0x7FFFFFFF;
                    }
                });
            }
        };
    }

    protected LatexCrystalJellyGas() {
        super(PROPERTIES, ChangedCreatureModTransfurVariants.LATEX_CRYSTAL_JELLY);
    }

    public Color3 getColor() {
        return Color3.getColor("#34d8f9");
    }

    @Override
    public @NotNull BlockState createLegacyBlock(FluidState fluidState) {
        return ChangedCreatureModBlocks.LATEX_CRYSTAL_JELLY_GAS.get().defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(fluidState));
    }

    static {
        PROPERTIES = (new ForgeFlowingFluid.Properties(
                ChangedCreatureModFluids.LATEX_CRYSTAL_JELLY_TRANSFUR_GAS,
                ChangedCreatureModFluids.LATEX_CRYSTAL_JELLY_GAS,
                ChangedCreatureModFluids.LATEX_CRYSTAL_JELLY_GAS_FLOWING
        )).tickRate(4)
                .levelDecreasePerBlock(1)
                .explosionResistance(100.0F)
                .block(ChangedCreatureModBlocks.LATEX_CRYSTAL_JELLY_GAS);
    }

    @Override
    public boolean isSource(FluidState p_76140_) {
        return true;
    }

    @Override
    public int getAmount(FluidState p_164509_) {
        return 8;
    }

    public static class Flowing extends LatexCrystalJellyGas {
        public Flowing() {
            this.registerDefaultState(this.getStateDefinition().any().setValue(LEVEL, 7));
        }

        @Override
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getAmount(FluidState state) {
            return state.getValue(LEVEL);
        }

        @Override
        public boolean isSource(FluidState state) {
            return false;
        }
    }

    public static class Source extends LatexCrystalJellyGas {
        public Source() {
        }

        @Override
        public int getAmount(FluidState state) {
            return 8;
        }

        @Override
        public boolean isSource(FluidState state) {
            return true;
        }
    }
}
