package net.hhdsj.changed_creatures.datagen;

import net.foxyas.changedaddon.init.ChangedAddonItems;
import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.init.ChangedCreatureModItems;
import net.ltxprogrammer.changed.init.ChangedItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

public class ChangedCreaturesRecipeProvider extends RecipeProvider {

    public ChangedCreaturesRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> writer) {

        // ==================== 工作台：有序合成 ====================
        addShaped(ChangedCreatureModItems.LATEX_DAWN_FUZZ_WALL.get(), 1, "###", "#S#", "###")
                .define('#', ChangedCreatureModItems.LATEX_DAWN_FUZZ.get())
                .define('S', ItemTags.WOOL)
                .unlockedBy("has_latex_dawn_fuzz_wall", has(ChangedCreatureModItems.LATEX_DAWN_FUZZ.get()))
                .save(writer, ChangedCreature.CCRL("latex_dawn_fuzz_wall_recipe"));

        addShaped(ChangedCreatureModItems.LUNARDAWNEAXE.get(), 1, "##", "#S", " S")
                .define('#', ChangedCreatureModItems.LATEX_DAWN_FUZZ.get())
                .define('S', Items.STICK)
                .unlockedBy("has_dawn_fuzz", has(ChangedCreatureModItems.LATEX_DAWN_FUZZ.get()))
                .save(writer, ChangedCreature.CCRL("lunar_dawn_axe_recipe"));

        // 镐
        addShaped(ChangedCreatureModItems.LUNARDAWNEMANUSCRIPT.get(), 1, "###", " S ", " S ")
                .define('#', ChangedCreatureModItems.LATEX_DAWN_FUZZ.get())
                .define('S', Items.STICK)
                .unlockedBy("has_dawn_fuzz", has(ChangedCreatureModItems.LATEX_DAWN_FUZZ.get()))
                .save(writer, ChangedCreature.CCRL("lunar_dawn_manu_script_recipe"));

        // 锹
        addShaped(ChangedCreatureModItems.LUNARDAWNESHOVEL.get(), 1, "#", "S", "S")
                .define('#', ChangedCreatureModItems.LATEX_DAWN_FUZZ.get())
                .define('S', Items.STICK)
                .unlockedBy("has_dawn_fuzz", has(ChangedCreatureModItems.LATEX_DAWN_FUZZ.get()))
                .save(writer, ChangedCreature.CCRL("lunar_dawn_shovel_recipe"));

        // 锄
        addShaped(ChangedCreatureModItems.LUNARDAWNEHOE.get(), 1, "##", " S", " S")
                .define('#', ChangedCreatureModItems.LATEX_DAWN_FUZZ.get())
                .define('S', Items.STICK)
                .unlockedBy("has_dawn_fuzz", has(ChangedCreatureModItems.LATEX_DAWN_FUZZ.get()))
                .save(writer, ChangedCreature.CCRL("lunar_dawn_hoe_recipe"));

        addShaped(ChangedCreatureModItems.LatexCrystalJellyItem.get(), 3, "ABC", "BAC")
                .define('A', Items.AMETHYST_SHARD)
                .define('B', Items.ICE)
                .define('C', ChangedItems.LATEX_BASE.get())
                .unlockedBy("has_amethyst", has(Items.AMETHYST_SHARD))
                .save(writer, ChangedCreature.CCRL("blue_crystal_recipe"));

        addShaped(ChangedCreatureModItems.Frost_Condensed_Crystal_Cluster.get(), 3, "###", "BBB")
                .define('#', ChangedCreatureModItems.LatexCrystalJellyItem.get())
                .define('B', Items.ICE)
                .unlockedBy("has_jelly", has(ChangedCreatureModItems.LatexCrystalJellyItem.get()))
                .save(writer, ChangedCreature.CCRL("frost_condensed_crystal_cluster_recipe"));

        addShaped(ChangedCreatureModItems.CURSED_SCYTHE.get(), 1, "ABC", "DEF", "GHI")
                .define('A', Items.NETHERITE_INGOT)
                .define('B', Items.CRYING_OBSIDIAN)
                .define('C', Items.NETHER_STAR)
                .define('D', Items.BLAZE_POWDER)
                .define('E', Items.LIME_DYE)
                .define('F', ChangedAddonItems.THE_DECIMATOR.get())
                .define('G', Items.PURPLE_CARPET)
                .define('H', Items.PURPLE_WOOL)
                .define('I', Items.NETHER_STAR)
                .unlockedBy("has_decimator", has(ChangedAddonItems.THE_DECIMATOR.get()))
                .save(writer, ChangedCreature.CCRL("cursed_scythe_recipe"));

        addShaped(ChangedCreatureModItems.LATEX_LAVA_CRYSTAL.get(), 1, "BA ", "CB ")
                .define('A', Items.LAVA_BUCKET)
                .define('B', ChangedItems.LATEX_BASE.get())
                .define('C', Items.AMETHYST_SHARD)
                .unlockedBy("has_lava_bucket", has(Items.LAVA_BUCKET))
                .save(writer, ChangedCreature.CCRL("latex_lava_crystal_recipe"));

        addShaped(ChangedCreatureModItems.URANIUMBLOCK.get(), 1, "###", "###", "###")
                .define('#', ChangedCreatureModItems.IMPUREURANIUMINGOT.get())
                .unlockedBy("has_ingot", has(ChangedCreatureModItems.IMPUREURANIUMINGOT.get()))
                .save(writer, ChangedCreature.CCRL("uraniumblocks"));

        addShaped(ChangedCreatureModItems.BLUEBLOCK.get(), 1, "###", "#I#", "###")
                .define('#', Items.IRON_BARS)
                .define('I', Items.IRON_INGOT)
                .unlockedBy("has_iron", has(Items.IRON_INGOT))
                .save(writer, ChangedCreature.CCRL("tieblock_tab"));

        // ==================== 工作台：无序合成 ====================
        addShapeless(Items.GUNPOWDER, 4)
                .requires(ChangedCreatureModItems.NITRICACID.get())
                .requires(ChangedCreatureModItems.SULPHUR.get())
                .requires(Items.COAL)
                .unlockedBy("has_nitric", has(ChangedCreatureModItems.NITRICACID.get()))
                .save(writer, ChangedCreature.CCRL("syntheticpowderformula"));

        addShapeless(ChangedCreatureModItems.Latex_Night_Owl_Goo_Item.get(), 1)
                .requires(Items.MAGMA_BLOCK, 2)
                .requires(Items.BLAZE_ROD)
                .requires(Items.TOTEM_OF_UNDYING)
                .requires(Items.NETHER_STAR)
                .requires(ChangedAddonItems.BIOMASS.get(), 2)
                .unlockedBy("has_totem", has(Items.TOTEM_OF_UNDYING))
                .save(writer, ChangedCreature.CCRL("latex_night_owl_goo_recipe"));

        addShaped(ChangedCreatureModItems.IMPUREURANIUMINGOT.get(), 1, "###", "###", "###")
                .define('#', ChangedCreatureModItems.IMPUREURANIUM.get())
                .unlockedBy("has_uranium", has(ChangedCreatureModItems.IMPUREURANIUM.get()))
                .save(writer, ChangedCreature.CCRL("x_impureuraniumingot"));

        // ==================== 切石机 ====================
        stonecutting(writer, Items.BLACK_CONCRETE, ChangedCreatureModItems.BLACKBLOCK.get(), "blackblocks");

        // ==================== 锻造台 ====================
        addSmithing(writer, Items.NETHERITE_SWORD, ChangedAddonItems.PAINITE_BLOCK.get(), ChangedCreatureModItems.PAINITESWORD.get(), "painite_sword_recipe");
        addSmithing(writer, Items.NETHERITE_AXE, ChangedAddonItems.PAINITE_BLOCK.get(), ChangedCreatureModItems.PAINITEAXE.get(), "painite_axe_recipe_1");
        addSmithing(writer, Items.NETHERITE_HOE, ChangedAddonItems.PAINITE_BLOCK.get(), ChangedCreatureModItems.PAINITEHOE.get(), "painite_hoe_recipe");
        addSmithing(writer, Items.NETHERITE_SHOVEL, ChangedAddonItems.PAINITE_BLOCK.get(), ChangedCreatureModItems.PAINITESHOVEL.get(), "painite_shovel_recipe");
        addSmithing(writer, Items.NETHERITE_PICKAXE, ChangedAddonItems.PAINITE_BLOCK.get(), ChangedCreatureModItems.PAINITEMANUSCRIPT.get(), "painite_pick_axe_recipe");

        addSmithing(writer, Items.NETHERITE_HELMET, ChangedAddonItems.PAINITE_BLOCK.get(), ChangedCreatureModItems.PAINITE_ARMOR_HELMET.get(), "painite_armor_helmet_recipe");
        addSmithing(writer, Items.NETHERITE_CHESTPLATE, ChangedAddonItems.PAINITE_BLOCK.get(), ChangedCreatureModItems.PAINITE_ARMOR_CHESTPLATE.get(), "painite_armor_chest_plate_recipe");
        addSmithing(writer, Items.NETHERITE_LEGGINGS, ChangedAddonItems.PAINITE_BLOCK.get(), ChangedCreatureModItems.PAINITE_ARMOR_LEGGINGS.get(), "painite_armor_leggings_recipe");
        addSmithing(writer, Items.NETHERITE_BOOTS, ChangedAddonItems.PAINITE_BLOCK.get(), ChangedCreatureModItems.PAINITE_ARMOR_BOOTS.get(), "painite_armor_boots_recipe");

        addSmithing(writer, ChangedCreatureModItems.PAINITE_ARMOR_HELMET.get(), Items.GOLD_BLOCK, Items.NETHERITE_HELMET, "painite_armor_helmet_recipe_change");
        addSmithing(writer, ChangedCreatureModItems.PAINITE_ARMOR_CHESTPLATE.get(), Items.GOLD_BLOCK, Items.NETHERITE_CHESTPLATE, "painite_armor_changed_recipe_2");
        addSmithing(writer, ChangedCreatureModItems.PAINITE_ARMOR_LEGGINGS.get(), Items.GOLD_BLOCK, Items.NETHERITE_LEGGINGS, "painite_armor_changed_recipe_3");
        addSmithing(writer, ChangedCreatureModItems.PAINITE_ARMOR_BOOTS.get(), Items.GOLD_BLOCK, Items.NETHERITE_BOOTS, "painite_armor_changed_recipe_4");

        // ==================== 白木系列 ====================
        addShaped(Items.CRAFTING_TABLE, 1, "##", "##")
                .define('#', ChangedCreatureModItems.PALE_WOOD_PLANKS.get())
                .unlockedBy("has_planks", has(ChangedCreatureModItems.PALE_WOOD_PLANKS.get()))
                .save(writer, ChangedCreature.CCRL("palegonzuotai"));

        addShapeless(ChangedCreatureModItems.PALE_WOOD_BUTTON.get(), 1)
                .requires(ChangedCreatureModItems.PALE_WOOD_PLANKS.get())
                .unlockedBy("has_planks", has(ChangedCreatureModItems.PALE_WOOD_PLANKS.get()))
                .save(writer, ChangedCreature.CCRL("pale_wood_button_recipe"));

        addShaped(ChangedCreatureModItems.PALE_WOOD_PRESSURE_PLATE.get(), 1, "##")
                .define('#', ChangedCreatureModItems.PALE_WOOD_PLANKS.get())
                .unlockedBy("has_planks", has(ChangedCreatureModItems.PALE_WOOD_PLANKS.get()))
                .save(writer, ChangedCreature.CCRL("pale_wood_pressure_plate_recipe"));

        addShaped(ChangedCreatureModItems.PALE_WOOD_SLAB.get(), 6, "###")
                .define('#', ChangedCreatureModItems.PALE_WOOD_PLANKS.get())
                .unlockedBy("has_planks", has(ChangedCreatureModItems.PALE_WOOD_PLANKS.get()))
                .save(writer, ChangedCreature.CCRL("pale_wood_slab_recipe"));

        addShaped(ChangedCreatureModItems.PALE_WOOD_STAIRS.get(), 4, "#  ", "## ", "###")
                .define('#', ChangedCreatureModItems.PALE_WOOD_PLANKS.get())
                .unlockedBy("has_planks", has(ChangedCreatureModItems.PALE_WOOD_PLANKS.get()))
                .save(writer, ChangedCreature.CCRL("pale_wood_stairs_recipe"));

        addShaped(Items.STICK, 4, "#", "#")
                .define('#', ChangedCreatureModItems.PALE_WOOD_PLANKS.get())
                .unlockedBy("has_planks", has(ChangedCreatureModItems.PALE_WOOD_PLANKS.get()))
                .save(writer, ChangedCreature.CCRL("pale_wood_stick_recipe"));

        addShaped(ChangedCreatureModItems.PALE_WOOD_WOOD.get(), 3, "##", "##")
                .define('#', ChangedCreatureModItems.PALE_WOOD_LOG.get())
                .unlockedBy("has_logs", has(ChangedCreatureModItems.PALE_WOOD_LOG.get()))
                .save(writer, ChangedCreature.CCRL("pale_wood_wood_recipe"));

        addShaped(ChangedCreatureModItems.PALE_WOOD_FENCE.get(), 3, "#S#", "#S#")
                .define('#', ChangedCreatureModItems.PALE_WOOD_PLANKS.get())
                .define('S', Items.STICK)
                .unlockedBy("has_planks", has(ChangedCreatureModItems.PALE_WOOD_PLANKS.get()))
                .save(writer, ChangedCreature.CCRL("pale_wood_fence_recipe"));

        addShaped(ChangedCreatureModItems.PALE_WOOD_FENCE_GATE.get(), 1, "S#S", "S#S")
                .define('S', Items.STICK)
                .define('#', ChangedCreatureModItems.PALE_WOOD_PLANKS.get())
                .unlockedBy("has_planks", has(ChangedCreatureModItems.PALE_WOOD_PLANKS.get()))
                .save(writer, ChangedCreature.CCRL("pale_wood_fence_gate_recipe"));

        addShapeless(ChangedCreatureModItems.PALE_WOOD_PLANKS.get(), 4)
                .requires(ChangedCreatureModItems.PALE_WOOD_LOG.get())
                .unlockedBy("has_logs", has(ChangedCreatureModItems.PALE_WOOD_LOG.get()))
                .save(writer, ChangedCreature.CCRL("pale_wood_planks_recipe"));

        // ==================== 模组配方调用 ====================
        ChangedInfuserRecipes.build(writer);
    }

    // ==================== 辅助方法 ====================

    private static ShapedRecipeBuilder addShaped(Item result, int count, String... pattern) {
        ShapedRecipeBuilder builder = ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, count);
        for (String row : pattern) {
            builder.pattern(row);
        }
        return builder;
    }

    private static ShapelessRecipeBuilder addShapeless(Item result, int count) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, count);
    }

    private static void stonecutting(Consumer<FinishedRecipe> writer, Item input, Item result, String id) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(input), RecipeCategory.MISC, result)
                .unlockedBy("has_material", has(input))
                .save(writer, ChangedCreature.CCRL(id));
    }

    private static void addSmithing(Consumer<FinishedRecipe> writer, Item base, Item addition, Item result, String id) {
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(base),
                        Ingredient.of(addition),
                        RecipeCategory.MISC,
                        result
                )
                .unlocks("has_base", has(base))
                .save(writer, ChangedCreature.CCRL(id));
    }
}