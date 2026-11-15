package net.hhdsj.changed_creatures.init;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.block.*;
import net.hhdsj.changed_creatures.block.pale_blocks.*;
import net.ltxprogrammer.changed.block.FloorSignBlock;
import net.ltxprogrammer.changed.block.FluidCanisterBlock;
import net.ltxprogrammer.changed.block.GasFluidBlock;
import net.ltxprogrammer.changed.item.GasCanister;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ChangedCreatureModBlocks {
	public static final DeferredRegister<Block> REGISTRY =
			DeferredRegister.create(ForgeRegistries.BLOCKS, ChangedCreature.MODID);

	// ==================== 普通方块（物品在 ChangedCreatureModItems 里注册） ====================
	public static final RegistryObject<Block> LATEX_DEEP_CRYSTAL_DRAGON_DOLL = registerNoItem("latex_crystal_jelly_dragon_doll", LatexDeepCrystalDragonDollBlock::new);
	public static final RegistryObject<Block> LATEX_DAWN_FUZZ_WALL = registerNoItem("latex_dawn_fuzz_wall", LatexDawnFuzzWallBlock::new);
	public static final RegistryObject<Block> SULPHURBLOCK = registerNoItem("sulphur_block", SulphurblockBlock::new);
	public static final RegistryObject<Block> NITREBLOCK = registerNoItem("nitre_block", NitreblockBlock::new);
	public static final RegistryObject<Block> URANIUM_ORE = registerNoItem("uranium_ore", UraniummineralBlock::new);
	public static final RegistryObject<Block> PALE_WOOD_PLANKS = registerNoItem("pale_wood_planks", Pale_woodPlanksBlock::new);
	public static final RegistryObject<Block> PALE_WOOD_LEAVES = registerNoItem("pale_wood_leaves", Pale_woodLeavesBlock::new);
	public static final RegistryObject<Block> PALE_WOOD_LOG = registerNoItem("pale_wood_log", Pale_woodLogBlock::new);
	public static final RegistryObject<Block> PALE_WOOD_WOOD = registerNoItem("pale_wood_wood", Pale_woodWoodBlock::new);
	public static final RegistryObject<Block> URANIUMBLOCK = registerNoItem("uranium_block", UraniumblockBlock::new);
	public static final RegistryObject<Block> BLUEBLOCK = registerNoItem("blueblock", BlueblockBlock::new);
	public static final RegistryObject<Block> IRONHOT = registerNoItem("ironhot", IronhotBlock::new);
	public static final RegistryObject<Block> PALE_WOOD_STAIRS = registerNoItem("pale_wood_stairs", Pale_woodStairsBlock::new);
	public static final RegistryObject<Block> PALE_WOOD_SLAB = registerNoItem("pale_wood_slab", Pale_woodSlabBlock::new);
	public static final RegistryObject<Block> PALE_WOOD_BUTTON = registerNoItem("pale_wood_button", Pale_woodButtonBlock::new);
	public static final RegistryObject<Block> PALETREESPA_1 = registerNoItem("paletreespa_1", Paletreespa1Block::new);
	public static final RegistryObject<Block> BLACKBLOCK = registerNoItem("blackblock", BlackblockBlock::new);
	public static final RegistryObject<Block> PALE_WOOD_FENCE = registerNoItem("pale_wood_fence", Pale_woodFenceBlock::new);
	public static final RegistryObject<Block> PALE_WOOD_FENCE_GATE = registerNoItem("pale_wood_fence_gate", Pale_woodFenceGateBlock::new);
	public static final RegistryObject<Block> PALE_WOOD_PRESSURE_PLATE = registerNoItem("pale_wood_pressure_plate", Pale_woodPressurePlateBlock::new);
	public static final RegistryObject<Block> BLUE_CRYSTALORE = registerNoItem("blue_crystal_ore", BlueCrystaloreBlock::new);
	public static final RegistryObject<Block> CATALYZER = registerNoItem("catalyzer", CatalyzerBlock::new);
	public static final RegistryObject<Block> CRYO_SUBJECT_CHAMBER = registerNoItem("cryo_subject_chamber", CryoSubjectChamberBlock::new);
	public static final RegistryObject<Block> LATEX_MOLTEN_CRYSTAL = registerNoItem("latex_molten_crtstal_block", MoltenCrystalBlock::new);

	// ==================== 告示牌 ====================
	public static final RegistryObject<FloorSignBlock> FLOOR_SIGN_ECOLOGICAL_AREA =
			registerNoItem("ecological_area_sign", () -> new FloorSignBlock(Properties.of().sound(SoundType.WOOD)));
	public static final RegistryObject<FloorSignBlock> FLOOR_SIGN_GAS_AREA_WARNING =
			registerNoItem("gas_area_warning_sign", () -> new FloorSignBlock(Properties.of().sound(SoundType.WOOD)));
	public static final RegistryObject<FloorSignBlock> FLOOR_SIGN_IDENTIFICATION =
			registerNoItem("identification_sign", () -> new FloorSignBlock(Properties.of().sound(SoundType.WOOD)));
	public static final RegistryObject<FloorSignBlock> FLOOR_SIGN_LIGHTING =
			registerNoItem("lighting_sign", () -> new FloorSignBlock(Properties.of().sound(SoundType.WOOD)));
	public static final RegistryObject<FloorSignBlock> FLOOR_SIGN_NO_LIGHTING =
			registerNoItem("no_lighting_sign", () -> new FloorSignBlock(Properties.of().sound(SoundType.WOOD)));
	public static final RegistryObject<FloorSignBlock> FLOOR_SIGN_REPAIR =
			registerNoItem("repair_sign", () -> new FloorSignBlock(Properties.of().sound(SoundType.WOOD)));
	public static final RegistryObject<FloorSignBlock> FLOOR_SIGN_WATER_AREA =
			registerNoItem("water_area_sign", () -> new FloorSignBlock(Properties.of().sound(SoundType.WOOD)));

	// ==================== 流体/气体方块 ====================
	public static final RegistryObject<GasFluidBlock> LATEX_CRYSTAL_JELLY_GAS =
			registerNoItem("latex_crystal_jelly_gas",
					() -> new GasFluidBlock(ChangedCreatureModFluids.LATEX_CRYSTAL_JELLY_GAS));
	public static final RegistryObject<FluidCanisterBlock> LATEX_CRYSTAL_JELLY_GAS_CANISTER =
			registerNoItem("latex_crystal_jelly_gas_canister",
					() -> new FluidCanisterBlock(ChangedCreatureModFluids.LATEX_CRYSTAL_JELLY_GAS));

	// ==================== 辅助方法 ====================
	private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> block) {
		return REGISTRY.register(name, block);
	}
}