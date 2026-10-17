package net.hhdsj.changed_creatures.init;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.block.*;
import net.hhdsj.changed_creatures.block.pale_blocks.*;
import net.ltxprogrammer.changed.block.FluidCanisterBlock;
import net.ltxprogrammer.changed.block.GasFluidBlock;
import net.ltxprogrammer.changed.item.GasCanister;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.function.Function;
import java.util.function.Supplier;

public class ChangedCreatureModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, ChangedCreature.MODID);

	public static final RegistryObject<Block> LATEX_DEEP_CRYSTAL_DRAGON_DOLL = REGISTRY.register("latex_crystal_jelly_dragon_doll", LatexDeepCrystalDragonDollBlock::new);
	public static final RegistryObject<Block> SULPHURBLOCK = REGISTRY.register("sulphur_block", SulphurblockBlock::new);
	public static final RegistryObject<Block> NITREBLOCK = REGISTRY.register("nitre_block", NitreblockBlock::new);
	public static final RegistryObject<Block> URANIUM_ORE = REGISTRY.register("uranium_ore", UraniummineralBlock::new);
	public static final RegistryObject<Block> PALE_WOOD_PLANKS = REGISTRY.register("pale_wood_planks", Pale_woodPlanksBlock::new);
	public static final RegistryObject<Block> PALE_WOOD_LEAVES = REGISTRY.register("pale_wood_leaves", Pale_woodLeavesBlock::new);
	public static final RegistryObject<Block> PALE_WOOD_LOG = REGISTRY.register("pale_wood_log", Pale_woodLogBlock::new);
	public static final RegistryObject<Block> PALE_WOOD_WOOD = REGISTRY.register("pale_wood_wood", Pale_woodWoodBlock::new);
	public static final RegistryObject<Block> URANIUMBLOCK = REGISTRY.register("uranium_block", UraniumblockBlock::new);
	public static final RegistryObject<Block> BLUEBLOCK = REGISTRY.register("blueblock", BlueblockBlock::new);
	public static final RegistryObject<Block> IRONHOT = REGISTRY.register("ironhot", IronhotBlock::new);
	public static final RegistryObject<Block> PALE_WOOD_STAIRS = REGISTRY.register("pale_wood_stairs", Pale_woodStairsBlock::new);
	public static final RegistryObject<Block> PALE_WOOD_SLAB = REGISTRY.register("pale_wood_slab", Pale_woodSlabBlock::new);
	public static final RegistryObject<Block> PALE_WOOD_BUTTON = REGISTRY.register("pale_wood_button", Pale_woodButtonBlock::new);
	public static final RegistryObject<Block> PALETREESPA_1 = REGISTRY.register("paletreespa_1", Paletreespa1Block::new);
	public static final RegistryObject<Block> BLACKBLOCK = REGISTRY.register("blackblock", BlackblockBlock::new);
	public static final RegistryObject<Block> PALE_WOOD_FENCE = REGISTRY.register("pale_wood_fence", Pale_woodFenceBlock::new);
	public static final RegistryObject<Block> PALE_WOOD_FENCE_GATE = REGISTRY.register("pale_wood_fence_gate", Pale_woodFenceGateBlock::new);
	public static final RegistryObject<Block> PALE_WOOD_PRESSURE_PLATE = REGISTRY.register("pale_wood_pressure_plate", Pale_woodPressurePlateBlock::new);
	public static final RegistryObject<Block> BLOCKBW_1 = REGISTRY.register("blockbw_1", Blockbw1Block::new);
	public static final RegistryObject<Block> BLOCKBW_2 = REGISTRY.register("blockbw_2", Blockbw2Block::new);
	public static final RegistryObject<Block> BLUE_CRYSTALORE = REGISTRY.register("blue_crystal_ore", BlueCrystaloreBlock::new);
	public static final RegistryObject<Block> CATALYZER = REGISTRY.register("catalyzer", CatalyzerBlock::new);
	public static final RegistryObject<Block> CRYO_SUBJECT_CHAMBER = REGISTRY.register("cryo_subject_chamber", CryoSubjectChamberBlock::new);
	public static final RegistryObject<Block> MOLTEN_CRYSTAL = REGISTRY.register("molten_crtstal_block", MoltenCrystalBlock::new);

	public static final RegistryObject<GasFluidBlock> LATEX_CRYSTAL_JELLY_GAS = REGISTRY.register("latex_crystal_jelly_gas", () -> new GasFluidBlock(ChangedCreatureModFluids.LATEX_CRYSTAL_JELLY_GAS));
	public static final RegistryObject<FluidCanisterBlock> LATEX_CRYSTAL_JELLY_GAS_CANISTER = register("latex_crystal_jelly_gas_canister", () -> new FluidCanisterBlock(ChangedCreatureModFluids.LATEX_CRYSTAL_JELLY_GAS),(canister) -> new GasCanister(canister, ChangedCreatureModFluids.LATEX_CRYSTAL_JELLY_GAS));;

	private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> block) {
		return REGISTRY.register(name, block);
	}

	private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockConstructor) {
		return register(name, blockConstructor, block -> new BlockItem(block, new Item.Properties()));
	}

	private static <T extends Block, I extends Item> RegistryObject<T> register(String name, Supplier<T> blockConstructor,
	                                                                            @Nullable Function<T, I> item) {
		RegistryObject<T> block = REGISTRY.register(name, blockConstructor);
		if (item != null)
			ChangedCreatureModItems.ITEMS.register(name, () -> item.apply(block.get()));
		return block;
	}
}
