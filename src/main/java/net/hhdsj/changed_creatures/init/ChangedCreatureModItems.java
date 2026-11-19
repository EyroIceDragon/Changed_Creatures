/*
 *    这些为物品注册类,请将注册写在这里,而不是其它地方!
 * 	        写的时候注意代码的美观qwp
 */
package net.hhdsj.changed_creatures.init;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.item.*;
import net.hhdsj.changed_creatures.item.tool.*;
import net.ltxprogrammer.changed.block.FloorSignBlock;
import net.ltxprogrammer.changed.item.GasCanister;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ChangedCreatureModItems {
	public static final DeferredRegister<Item> ITEMS =
			DeferredRegister.create(ForgeRegistries.ITEMS, ChangedCreature.MODID);

	// ==================== 方块物品声明 (Block Items Declaration) ====================
	public static RegistryObject<Item> LATEX_DEEP_CRYSTAL_DRAGON_DOLL;
	public static RegistryObject<Item> CRYO_SUBJECT_CHAMBER;
	public static RegistryObject<Item> LATEX_DAWN_FUZZ_WALL;
	public static RegistryObject<Item> LATEX_MOLTEN_CRYSTAL;
	// 矿物块
	public static RegistryObject<Item> SULPHURBLOCK;
	public static RegistryObject<Item> NITREBLOCK;
	public static RegistryObject<Item> URANIUMBLOCK;
	public static RegistryObject<Item> BLUEBLOCK;
	public static RegistryObject<Item> BLACKBLOCK;

	// 矿石
	public static RegistryObject<Item> URANIUM_ORE;
	public static RegistryObject<Item> BLUE_CRYSTALORE;

	// pale_wood 系列方块
	public static RegistryObject<Item> PALE_WOOD_PLANKS;
	public static RegistryObject<Item> PALE_WOOD_LEAVES;
	public static RegistryObject<Item> PALE_WOOD_LOG;
	public static RegistryObject<Item> PALE_WOOD_WOOD;
	public static RegistryObject<Item> PALE_WOOD_STAIRS;
	public static RegistryObject<Item> PALE_WOOD_SLAB;
	public static RegistryObject<Item> PALE_WOOD_BUTTON;
	public static RegistryObject<Item> PALE_WOOD_FENCE;
	public static RegistryObject<Item> PALE_WOOD_FENCE_GATE;
	public static RegistryObject<Item> PALE_WOOD_PRESSURE_PLATE;

	// 告示牌
	public static RegistryObject<Item> FLOOR_SIGN_ECOLOGICAL_AREA;
	public static RegistryObject<Item> FLOOR_SIGN_GAS_AREA_WARNING;
	public static RegistryObject<Item> FLOOR_SIGN_IDENTIFICATION;
	public static RegistryObject<Item> FLOOR_SIGN_LIGHTING;
	public static RegistryObject<Item> FLOOR_SIGN_NO_LIGHTING;
	public static RegistryObject<Item> FLOOR_SIGN_REPAIR;
	public static RegistryObject<Item> FLOOR_SIGN_WATER_AREA;

	// 功能方块
	public static RegistryObject<Item> IRONHOT;
	public static RegistryObject<Item> CATALYZER;
	public static RegistryObject<Item> LATEX_CRYSTAL_JELLY_GAS_CANISTER;

	// ==================== 普通物品声明 (Normal Items Declaration) ====================

	// 材料类
	public static RegistryObject<Item> SULPHUR;
	public static RegistryObject<Item> NITRICACID;
	public static RegistryObject<Item> IMPUREURANIUM;
	public static RegistryObject<Item> IMPUREURANIUMINGOT;
	public static RegistryObject<Item> BlueCrystaloreItem;
	public static RegistryObject<Item> LatexCrystalJellyItem;
	public static RegistryObject<Item> LATEX_LAVA_CRYSTAL;
	public static RegistryObject<Item> LATEX_DAWN_FUZZ;

	// 食物类
	public static RegistryObject<Item> DRAGONFRUIT;
	public static RegistryObject<Item> Frost_Condensed_Crystal_Cluster;
	public static RegistryObject<Item> Impure_Frost_Condensed_Crystal_Cluster;
	public static RegistryObject<Item> LATEX_FILLED_ALL_MUG_ITEM;

	// 工具类
	public static RegistryObject<Item> Latex_Night_Owl_Goo_Item;

	// 植物/生成类
	public static RegistryObject<Item> PALETREE;

	// ==================== 武器工具声明 (Weapons & Tools Declaration) ====================

	public static RegistryObject<Item> PAINITE_SWORD;
	public static RegistryObject<Item> PAINITE_AXE;
	public static RegistryObject<Item> PAINITE_HOE;
	public static RegistryObject<Item> PAINITE_SHOVEL;
	public static RegistryObject<Item> PAINITE_PICKAXE;

	public static RegistryObject<Item> LUNAR_DAWNE_AXE;
	public static RegistryObject<Item> LUNAR_DAWNE_HOE;
	public static RegistryObject<Item> LUNAR_DAWNE_SHOVEL;
	public static RegistryObject<Item> LUNAR_DAWNE_PICKAXE;
	public static RegistryObject<Item> CURSED_SCYTHE;

	public static RegistryObject<Item> CRYSTAL_JAVELIN;

	// ==================== 盔甲声明 (Armors Declaration) ====================

	// armora_1 系列
	public static RegistryObject<Item> ARMORA_1_ARMOR_HELMET;
	public static RegistryObject<Item> ARMORA_1_ARMOR_CHESTPLATE;
	public static RegistryObject<Item> ARMORA_1_ARMOR_LEGGINGS;
	public static RegistryObject<Item> ARMORA_1_ARMOR_BOOTS;

	// painite 系列
	public static RegistryObject<Item> PAINITE_ARMOR_HELMET;
	public static RegistryObject<Item> PAINITE_ARMOR_CHESTPLATE;
	public static RegistryObject<Item> PAINITE_ARMOR_LEGGINGS;
	public static RegistryObject<Item> PAINITE_ARMOR_BOOTS;

	public static RegistryObject<Item> FURSUIT_PROTOGEN_HELMET;
	public static RegistryObject<Item> FURSUIT_PROTOGEN_CHESTPLATE;
	public static RegistryObject<Item> FURSUIT_PROTOGEN_LEGGINGS;

	public static RegistryObject<Item> FURSUIT_FOX_HELMET;
	public static RegistryObject<Item> FURSUIT_FOX_CHESTPLATE;
	public static RegistryObject<Item> FURSUIT_FOX_LEGGINGS;

	public static RegistryObject<Item> FURSUIT_EYRO_ICE_DRAGON_HELMET;
	public static RegistryObject<Item> FURSUIT_EYRO_ICE_DRAGON_CHESTPLATE;
	public static RegistryObject<Item> FURSUIT_EYRO_ICE_DRAGON_LEGGINGS;

	// ==================== 静态代码块初始化 (Static Initialization) ====================

	static {
		LATEX_FILLED_ALL_MUG_ITEM = register("latex_mug", () -> new LatexFilledAllMugItem((new Item.Properties()).stacksTo(16)));

		// -------------------- 方块物品初始化 --------------------
		FLOOR_SIGN_ECOLOGICAL_AREA = blockItem("ecological_area_sign", ChangedCreatureModBlocks.FLOOR_SIGN_ECOLOGICAL_AREA);
		FLOOR_SIGN_GAS_AREA_WARNING = blockItem("gas_area_warning_sign", ChangedCreatureModBlocks.FLOOR_SIGN_GAS_AREA_WARNING);
		FLOOR_SIGN_IDENTIFICATION = blockItem("identification_sign", ChangedCreatureModBlocks.FLOOR_SIGN_IDENTIFICATION);
		FLOOR_SIGN_LIGHTING = blockItem("lighting_sign", ChangedCreatureModBlocks.FLOOR_SIGN_LIGHTING);
		FLOOR_SIGN_NO_LIGHTING = blockItem("no_lighting_sign", ChangedCreatureModBlocks.FLOOR_SIGN_NO_LIGHTING);
		FLOOR_SIGN_REPAIR = blockItem("repair_sign", ChangedCreatureModBlocks.FLOOR_SIGN_REPAIR);
		FLOOR_SIGN_WATER_AREA = blockItem("water_area_sign", ChangedCreatureModBlocks.FLOOR_SIGN_WATER_AREA);

		CRYO_SUBJECT_CHAMBER = blockItem("cryo_subject_chamber", ChangedCreatureModBlocks.CRYO_SUBJECT_CHAMBER);
		LATEX_DEEP_CRYSTAL_DRAGON_DOLL = blockItem("latex_crystal_jelly_dragon_doll", ChangedCreatureModBlocks.LATEX_DEEP_CRYSTAL_DRAGON_DOLL);
		SULPHURBLOCK = blockItem("sulphur_block", ChangedCreatureModBlocks.SULPHURBLOCK);
		NITREBLOCK = blockItem("nitre_block", ChangedCreatureModBlocks.NITREBLOCK);
		URANIUMBLOCK = blockItem("uranium_block", ChangedCreatureModBlocks.URANIUMBLOCK);
		BLUEBLOCK = blockItem("blueblock", ChangedCreatureModBlocks.BLUEBLOCK);
		BLACKBLOCK = blockItem("blackblock", ChangedCreatureModBlocks.BLACKBLOCK);
		LATEX_DAWN_FUZZ_WALL = blockItem("latex_dawn_fuzz_wall", ChangedCreatureModBlocks.LATEX_DAWN_FUZZ_WALL);
		LATEX_MOLTEN_CRYSTAL = blockItem("latex_molten_crtstal_block", ChangedCreatureModBlocks.LATEX_MOLTEN_CRYSTAL);

		URANIUM_ORE = blockItem("uranium_ore", ChangedCreatureModBlocks.URANIUM_ORE);
		BLUE_CRYSTALORE = blockItem("blue_crystal_ore", ChangedCreatureModBlocks.BLUE_CRYSTALORE);

		PALE_WOOD_PLANKS = blockItem("pale_wood_planks", ChangedCreatureModBlocks.PALE_WOOD_PLANKS);
		PALE_WOOD_LEAVES = blockItem("pale_wood_leaves", ChangedCreatureModBlocks.PALE_WOOD_LEAVES);
		PALE_WOOD_LOG = blockItem("pale_wood_log", ChangedCreatureModBlocks.PALE_WOOD_LOG);
		PALE_WOOD_WOOD = blockItem("pale_wood_wood", ChangedCreatureModBlocks.PALE_WOOD_WOOD);
		PALE_WOOD_STAIRS = blockItem("pale_wood_stairs", ChangedCreatureModBlocks.PALE_WOOD_STAIRS);
		PALE_WOOD_SLAB = blockItem("pale_wood_slab", ChangedCreatureModBlocks.PALE_WOOD_SLAB);
		PALE_WOOD_BUTTON = blockItem("pale_wood_button", ChangedCreatureModBlocks.PALE_WOOD_BUTTON);
		PALE_WOOD_FENCE = blockItem("pale_wood_fence", ChangedCreatureModBlocks.PALE_WOOD_FENCE);
		PALE_WOOD_FENCE_GATE = blockItem("pale_wood_fence_gate", ChangedCreatureModBlocks.PALE_WOOD_FENCE_GATE);
		PALE_WOOD_PRESSURE_PLATE = blockItem("pale_wood_pressure_plate", ChangedCreatureModBlocks.PALE_WOOD_PRESSURE_PLATE);

		IRONHOT = blockItem("ironhot", ChangedCreatureModBlocks.IRONHOT);
		CATALYZER = blockItem("catalyzer", ChangedCreatureModBlocks.CATALYZER);

		// 气体罐物品
		LATEX_CRYSTAL_JELLY_GAS_CANISTER = ITEMS.register("latex_crystal_jelly_gas_canister",
				() -> new GasCanister(ChangedCreatureModBlocks.LATEX_CRYSTAL_JELLY_GAS_CANISTER.get(),
						ChangedCreatureModFluids.LATEX_CRYSTAL_JELLY_GAS));

		// -------------------- 普通物品初始化 --------------------
		Latex_Night_Owl_Goo_Item = ITEMS.register("latex_night_owl_goo", LatexNghtOwlGooItem::new);
		SULPHUR = ITEMS.register("sulphur", SulphurItem::new);
		NITRICACID = ITEMS.register("nitricacid", NitricacidItem::new);
		IMPUREURANIUM = ITEMS.register("impureuranium", ImpureuraniumItem::new);
		IMPUREURANIUMINGOT = ITEMS.register("impureuraniumingot", ImpureuraniumingotItem::new);
		BlueCrystaloreItem = ITEMS.register("blue_crystal", BlueCrystaloreItem::new);
		LATEX_DAWN_FUZZ = ITEMS.register("latex_dawn_fuzz", LatexDawnFuzz::new);

		DRAGONFRUIT = ITEMS.register("latex_dragon_fruit", LatexDragonFruitItem::new);
		Frost_Condensed_Crystal_Cluster = ITEMS.register("frost_condensed_crystal_clustert", FrostCondensedCrystalClusterItem::new);
		Impure_Frost_Condensed_Crystal_Cluster = ITEMS.register("impure_frost_condensed_crystal_clustert", ImpureFrostCondensedCrystalClusterItem::new);

		PALETREE = ITEMS.register("pale_tree", PaleTreeItem::new);
		LatexCrystalJellyItem = ITEMS.register("latex_crystal_jelly", LatexCrystalJellyItem::new);
		LATEX_LAVA_CRYSTAL = ITEMS.register("latex_lava_crystal", LatexLavaCrystalItem::new);

		// -------------------- 武器工具初始化 --------------------
		PAINITE_SWORD = ITEMS.register("painite_sword", PainiteSwordItem::new);
		PAINITE_AXE = ITEMS.register("painite_axe", PainiteAxeItem::new);
		PAINITE_HOE = ITEMS.register("painite_hoe", PainiteHoeItem::new);
		PAINITE_SHOVEL = ITEMS.register("painite_shovel", PainiteShovelItem::new);
		PAINITE_PICKAXE = ITEMS.register("painite_pickaxe", PainiteManuScriptItem::new);

		LUNAR_DAWNE_AXE = ITEMS.register("lunar_dawn_axe", LunarDawnAxeItem::new);
		LUNAR_DAWNE_HOE = ITEMS.register("lunar_dawn_hoe", LunarDawnHoeItem::new);
		LUNAR_DAWNE_SHOVEL = ITEMS.register("lunar_dawn_shovel", LunarDawnShovelItem::new);
		LUNAR_DAWNE_PICKAXE = ITEMS.register("lunar_dawn_pickaxe", LunarDawnManuScriptItem::new);

		CURSED_SCYTHE = ITEMS.register("cursed_scythe", CursedScytheItem::new);
		CRYSTAL_JAVELIN = ITEMS.register("thrown_crystal_javelin_trident",
				() -> new CrystalJavelinTridentItem(
						new Item.Properties()
								.stacksTo(1)
								.durability(250)
				));

		// -------------------- 盔甲初始化 --------------------
		ARMORA_1_ARMOR_HELMET = ITEMS.register("armora_1_armor_helmet", Armora1ArmorItem.Helmet::new);
		ARMORA_1_ARMOR_CHESTPLATE = ITEMS.register("armora_1_armor_chestplate", Armora1ArmorItem.Chestplate::new);
		ARMORA_1_ARMOR_LEGGINGS = ITEMS.register("armora_1_armor_leggings", Armora1ArmorItem.Leggings::new);
		ARMORA_1_ARMOR_BOOTS = ITEMS.register("armora_1_armor_boots", Armora1ArmorItem.Boots::new);

		PAINITE_ARMOR_HELMET = ITEMS.register("painite_armor_helmet", PainiteArmorItem.Helmet::new);
		PAINITE_ARMOR_CHESTPLATE = ITEMS.register("painite_armor_chestplate", PainiteArmorItem.Chestplate::new);
		PAINITE_ARMOR_LEGGINGS = ITEMS.register("painite_armor_leggings", PainiteArmorItem.Leggings::new);
		PAINITE_ARMOR_BOOTS = ITEMS.register("painite_armor_boots", PainiteArmorItem.Boots::new);

		FURSUIT_PROTOGEN_HELMET = ITEMS.register("fursuit_protogen_helmet", FursuitProtogenItem.Helmet::new);
		FURSUIT_PROTOGEN_CHESTPLATE = ITEMS.register("fursuit_protogen_chestplate", FursuitProtogenItem.Chestplate::new);
		FURSUIT_PROTOGEN_LEGGINGS = ITEMS.register("fursuit_protogen_leggings", FursuitProtogenItem.Leggings::new);

		FURSUIT_FOX_HELMET = ITEMS.register("fursuit_fox_helmet", FursuitFoxItem.Helmet::new);
		FURSUIT_FOX_CHESTPLATE = ITEMS.register("fursuit_fox_chestplate", FursuitFoxItem.Chestplate::new);
		FURSUIT_FOX_LEGGINGS = ITEMS.register("fursuit_fox_leggings", FursuitFoxItem.Leggings::new);

		FURSUIT_EYRO_ICE_DRAGON_HELMET = ITEMS.register("fursuit_eyro_end_dragon_helmet", FursuitEyroIceDragonItem.Helmet::new);
		FURSUIT_EYRO_ICE_DRAGON_CHESTPLATE = ITEMS.register("fursuit_eyro_end_dragon_chestplate", FursuitEyroIceDragonItem.Chestplate::new);
		FURSUIT_EYRO_ICE_DRAGON_LEGGINGS = ITEMS.register("fursuit_eyro_end_dragon_leggings", FursuitEyroIceDragonItem.Leggings::new);
	}

	// ==================== 辅助方法 (Helper Methods) ====================

	private static RegistryObject<MusicRecordItem> registerRecord(String name, Supplier<SoundEvent> soundEventSupplier) {
		return register(name, () -> new MusicRecordItem(8, soundEventSupplier,
				new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
	}

	private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> item) {
		return ITEMS.register(name, item);
	}

	private static RegistryObject<Item> blockItem(String name, RegistryObject<? extends Block> block) {
		return ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
	}
}