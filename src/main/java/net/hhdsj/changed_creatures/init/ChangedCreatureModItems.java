/*
 *    这些为物品注册类,请将注册写在这里,而不是其它地方!
 * 	        写的时候注意代码的美观qwp
 */
package net.hhdsj.changed_creatures.init;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.item.*;
import net.hhdsj.changed_creatures.item.tool.*;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import java.util.function.Supplier;

public class ChangedCreatureModItems {
	public static final DeferredRegister<Item> ITEMS =
			DeferredRegister.create(ForgeRegistries.ITEMS, ChangedCreature.MODID);

	// ==================== 方块物品声明 (Block Items Declaration) ====================
	public static RegistryObject<Item> LATEX_DEEP_CRYSTAL_DRAGON_DOLL;
	public static RegistryObject<Item> CRYO_SUBJECT_CHAMBER;
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

	// 功能方块
	public static RegistryObject<Item> IRONHOT;
	public static RegistryObject<Item> CATALYZER;

	// 装饰/其他方块
	public static RegistryObject<Item> PALETREESPA_1;
	public static RegistryObject<Item> BLOCKBW_1;
	public static RegistryObject<Item> BLOCKBW_2;

	// ==================== 普通物品声明 (Normal Items Declaration) ====================

	// 材料类
	public static RegistryObject<Item> SULPHUR;
	public static RegistryObject<Item> NITRICACID;
	public static RegistryObject<Item> IMPUREURANIUM;
	public static RegistryObject<Item> IMPUREURANIUMINGOT;
	public static RegistryObject<Item> BlueCrystaloreItem;
	public static RegistryObject<Item> LatexCrystalJellyItem;
    public static RegistryObject<Item> LATEX_LAVA_CRYSTAL;

	// 食物类
	public static RegistryObject<Item> DRAGONFRUIT;
	public static RegistryObject<Item> Frost_Condensed_Crystal_Cluster;
	public static RegistryObject<Item> Impure_Frost_Condensed_Crystal_Cluster;
	public static RegistryObject<Item> LATEX_FILLED_ALL_MUG_ITEM;

	// 工具类
	public static RegistryObject<Item> FLUTE;
	public static RegistryObject<Item> Latex_Night_Owl_Goo_Item;

	// 植物/生成类
	public static RegistryObject<Item> PALETREE;

	// ==================== 武器工具声明 (Weapons & Tools Declaration) ====================

	public static RegistryObject<Item> PAINITESWORD;
	public static RegistryObject<Item> PAINITEAXE;
	public static RegistryObject<Item> PAINITEHOE;
	public static RegistryObject<Item> PAINITESHOVEL;
	public static RegistryObject<Item> PAINITEMANUSCRIPT;

	public static RegistryObject<Item> LUNARDAWNEAXE;
	public static RegistryObject<Item> LUNARDAWNEHOE;
	public static RegistryObject<Item> LUNARDAWNESHOVEL;
	public static RegistryObject<Item> LUNARDAWNEMANUSCRIPT;
	public static RegistryObject<Item> CURSED_SCYTHE;

	public static RegistryObject<Item> CRYSTAL_JAVELIN;
	// ==================== 唱片声明 (Music Discs Declaration) ====================

	//public static RegistryObject<MusicRecordItem> INKSANS_4;
	//public static RegistryObject<MusicRecordItem> THEFLUFFYHEARTBEATINTHECUBICLEMUSIC;
	//public static RegistryObject<MusicRecordItem> FLUTEMUSICA_1RECORDS;
	//public static RegistryObject<MusicRecordItem> NOTLISTENTHIS;
	//public static RegistryObject<MusicRecordItem> ANITFRRYMUSIC;

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

	// ==================== 静态代码块初始化 (Static Initialization) ====================

	static {
		LATEX_FILLED_ALL_MUG_ITEM = register("latex_mug", () -> new LatexFilledAllMugItem((new Item.Properties()).stacksTo(16)));
		// -------------------- 方块物品初始化 --------------------
		CRYO_SUBJECT_CHAMBER = block(ChangedCreatureModBlocks.CRYO_SUBJECT_CHAMBER);
		LATEX_DEEP_CRYSTAL_DRAGON_DOLL = block(ChangedCreatureModBlocks.LATEX_DEEP_CRYSTAL_DRAGON_DOLL);
		SULPHURBLOCK = block(ChangedCreatureModBlocks.SULPHURBLOCK);
		NITREBLOCK = block(ChangedCreatureModBlocks.NITREBLOCK);
		URANIUMBLOCK = block(ChangedCreatureModBlocks.URANIUMBLOCK);
		BLUEBLOCK = block(ChangedCreatureModBlocks.BLUEBLOCK);
		BLACKBLOCK = block(ChangedCreatureModBlocks.BLACKBLOCK);

		URANIUM_ORE = block(ChangedCreatureModBlocks.URANIUM_ORE);
		BLUE_CRYSTALORE = block(ChangedCreatureModBlocks.BLUE_CRYSTALORE);

		PALE_WOOD_PLANKS = block(ChangedCreatureModBlocks.PALE_WOOD_PLANKS);
		PALE_WOOD_LEAVES = block(ChangedCreatureModBlocks.PALE_WOOD_LEAVES);
		PALE_WOOD_LOG = block(ChangedCreatureModBlocks.PALE_WOOD_LOG);
		PALE_WOOD_WOOD = block(ChangedCreatureModBlocks.PALE_WOOD_WOOD);
		PALE_WOOD_STAIRS = block(ChangedCreatureModBlocks.PALE_WOOD_STAIRS);
		PALE_WOOD_SLAB = block(ChangedCreatureModBlocks.PALE_WOOD_SLAB);
		PALE_WOOD_BUTTON = block(ChangedCreatureModBlocks.PALE_WOOD_BUTTON);
		PALE_WOOD_FENCE = block(ChangedCreatureModBlocks.PALE_WOOD_FENCE);
		PALE_WOOD_FENCE_GATE = block(ChangedCreatureModBlocks.PALE_WOOD_FENCE_GATE);
		PALE_WOOD_PRESSURE_PLATE = block(ChangedCreatureModBlocks.PALE_WOOD_PRESSURE_PLATE);

		IRONHOT = block(ChangedCreatureModBlocks.IRONHOT);
		CATALYZER = block(ChangedCreatureModBlocks.CATALYZER);

		PALETREESPA_1 = block(ChangedCreatureModBlocks.PALETREESPA_1);
		BLOCKBW_1 = block(ChangedCreatureModBlocks.BLOCKBW_1);
		BLOCKBW_2 = block(ChangedCreatureModBlocks.BLOCKBW_2);

		// -------------------- 普通物品初始化 --------------------
		Latex_Night_Owl_Goo_Item = ITEMS.register("latex_night_owl_goo", LatexNghtOwlGooItem::new);
		SULPHUR = ITEMS.register("sulphur", SulphurItem::new);
		NITRICACID = ITEMS.register("nitricacid", NitricacidItem::new);
		IMPUREURANIUM = ITEMS.register("impureuranium", ImpureuraniumItem::new);
		IMPUREURANIUMINGOT = ITEMS.register("impureuraniumingot", ImpureuraniumingotItem::new);
		BlueCrystaloreItem = ITEMS.register("blue_crystal", BlueCrystaloreItem::new);

		DRAGONFRUIT = ITEMS.register("latex_dragon_fruit", LatexDragonFruitItem::new);
		Frost_Condensed_Crystal_Cluster = ITEMS.register("frost_condensed_crystal_clustert", FrostCondensedCrystalClusterItem::new);
		Impure_Frost_Condensed_Crystal_Cluster = ITEMS.register("impure_frost_condensed_crystal_clustert", ImpureFrostCondensedCrystalClusterItem::new);
		FLUTE = ITEMS.register("flute", FluteItem::new);

		PALETREE = ITEMS.register("pale_tree", PaleTreeItem::new);
		LatexCrystalJellyItem = ITEMS.register("latex_crystal_jelly", LatexCrystalJellyItem::new);
        LATEX_LAVA_CRYSTAL = ITEMS.register("latex_lava_crystal", LatexLavaCrystalItem::new);

		// -------------------- 武器工具初始化 --------------------
		PAINITESWORD = ITEMS.register("painite_sword", PainiteSwordItem::new);
		PAINITEAXE = ITEMS.register("painite_axe", PainiteAxeItem::new);
		PAINITEHOE = ITEMS.register("painite_hoe", PainiteHoeItem::new);
		PAINITESHOVEL = ITEMS.register("painite_shovel", PainiteShovelItem::new);
		PAINITEMANUSCRIPT = ITEMS.register("painite_manu_script", PainiteManuScriptItem::new);

		LUNARDAWNEAXE = ITEMS.register("lunar_dawn_axe", LunarDawnAxeItem::new);
		LUNARDAWNEHOE = ITEMS.register("lunar_dawn_hoe", LunarDawnHoeItem::new);
		LUNARDAWNESHOVEL = ITEMS.register("lunar_dawn_shovel", LunarDawnShovelItem::new);
		LUNARDAWNEMANUSCRIPT = ITEMS.register("lunar_dawn_manu_script", LunarDawnManuScriptItem::new);

		CURSED_SCYTHE = ITEMS.register("cursed_scythe", CursedScytheItem::new);
		CRYSTAL_JAVELIN= ITEMS.register("thrown_crystal_javelin_trident",
				() -> new CrystalJavelinTridentItem(
						new Item.Properties()
								.stacksTo(1)    // 三叉戟不可堆叠
								.durability(250) // 设定耐久度，和原版三叉戟一样
				));

		// -------------------- 盔甲初始化 --------------------
		// armora_1 系列
		ARMORA_1_ARMOR_HELMET = ITEMS.register("armora_1_armor_helmet", Armora1ArmorItem.Helmet::new);
		ARMORA_1_ARMOR_CHESTPLATE = ITEMS.register("armora_1_armor_chestplate", Armora1ArmorItem.Chestplate::new);
		ARMORA_1_ARMOR_LEGGINGS = ITEMS.register("armora_1_armor_leggings", Armora1ArmorItem.Leggings::new);
		ARMORA_1_ARMOR_BOOTS = ITEMS.register("armora_1_armor_boots", Armora1ArmorItem.Boots::new);
		// painite 系列
		PAINITE_ARMOR_HELMET = ITEMS.register("painite_armor_helmet", PainiteArmorItem.Helmet::new);
		PAINITE_ARMOR_CHESTPLATE = ITEMS.register("painite_armor_chestplate", PainiteArmorItem.Chestplate::new);
		PAINITE_ARMOR_LEGGINGS = ITEMS.register("painite_armor_leggings", PainiteArmorItem.Leggings::new);
		PAINITE_ARMOR_BOOTS = ITEMS.register("painite_armor_boots", PainiteArmorItem.Boots::new);

		// -------------------- 唱片初始化 -------------------- //
        /*
		INKSANS_4 = registerRecord("inksans_4", ChangedCreatureModSounds.INK_SANS);
		THEFLUFFYHEARTBEATINTHECUBICLEMUSIC = registerRecord("thefluffyheartbeatinthecubiclemusic", ChangedCreatureModSounds.INK_SANS);
		FLUTEMUSICA_1RECORDS = registerRecord("flutemusica_1records", ChangedCreatureModSounds.THE_FLUFFY_HEARTBEAT_IN_THE_CUBICLE);
		NOTLISTENTHIS = registerRecord("notlistenthis", ChangedCreatureModSounds.ANIT_FURRY_MUSIC);
		ANITFRRYMUSIC = registerRecord("anitfrrymusic", ChangedCreatureModSounds.ANIT_FURRY_MUSIC);
		*/
	}

	// ==================== 辅助方法 (Helper Methods) ====================

	/**
	 * 注册唱片物品的辅助方法
	 * @param name 注册名称
	 * @param soundEventSupplier 声音事件提供者
	 * @return 注册对象
	 */
	private static RegistryObject<MusicRecordItem> registerRecord(String name, Supplier<SoundEvent> soundEventSupplier) {
		return register(name, () -> new MusicRecordItem(8, soundEventSupplier,
				new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
	}

	/**
	 * 通用物品注册辅助方法
	 * @param name 注册名称
	 * @param item 物品提供者
	 * @return 注册对象
	 */
	private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> item) {
		return ITEMS.register(name, item);
	}

	/**
	 * 方块物品注册辅助方法
	 * @param block 对应的方块注册对象
	 * @return 方块物品注册对象
	 */
	private static RegistryObject<Item> block(RegistryObject<Block> block) {
        if (block.getId() != null) {
            return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
        }
        return null;
    }


}