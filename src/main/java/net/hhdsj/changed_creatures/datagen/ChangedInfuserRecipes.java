package net.hhdsj.changed_creatures.datagen;

import net.hhdsj.changed_creatures.init.ChangedCreatureModTransfurVariants;
import net.ltxprogrammer.changed.datagen.builders.InfuserRecipeBuilder;
import net.ltxprogrammer.changed.entity.variant.TransfurVariant;
import net.ltxprogrammer.changed.init.ChangedItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ChangedInfuserRecipes {

    public static void build(Consumer<FinishedRecipe> writer) {
        Map<TransfurVariant<?>, Ingredient[]> recipes = new LinkedHashMap<>();

        // 1. Etan Kind
        recipes.put(ChangedCreatureModTransfurVariants.LATEX_ETAN_KIND.get(), new Ingredient[]{
                Ingredient.of(ChangedItems.WHITE_LATEX_GOO.get()),
                Ingredient.of(Items.LAPIS_LAZULI),
                Ingredient.of(Items.BONE)
        });

        // 2. Orange Fox
        recipes.put(ChangedCreatureModTransfurVariants.LATEX_ORANGE_FOX.get(), new Ingredient[]{
                Ingredient.of(Items.ORANGE_DYE)
        });

        // 3. Kcahra Shark
        recipes.put(ChangedCreatureModTransfurVariants.LATEX_KCAHRA_SHARK.get(), new Ingredient[]{
                Ingredient.of(Items.PRISMARINE_SHARD),
                Ingredient.of(Items.INK_SAC)
        });

        // 4. Dusk Dawn Dragon (带性别)
        recipes.put(ChangedCreatureModTransfurVariants.LATEX_DUSK_DAWN_DRAGON.get(), new Ingredient[]{
                Ingredient.of(ChangedItems.WHITE_LATEX_GOO.get()),
                Ingredient.of(Items.PURPLE_DYE)
        });

        for (Map.Entry<TransfurVariant<?>, Ingredient[]> entry : recipes.entrySet()) {
            InfuserRecipeBuilder builder;
            // 根据 form ID 路径里是否带 "/" 来判断是否区分性别
            if (entry.getKey().getFormId().getPath().contains("/")) {
                builder = InfuserRecipeBuilder.gendered(entry.getKey());
            } else {
                builder = InfuserRecipeBuilder.genderLess(entry.getKey());
            }

            builder.requires(ChangedItems.LATEX_BASE.get());

            for (Ingredient ing : entry.getValue()) {
                builder.requires(ing);
            }

            builder.unlockedBy("has_latex_base", hasItem(ChangedItems.LATEX_BASE.get()))
                    .save(writer);
        }
    }

    // 替代 RecipeProvider.has()，用于构建解锁条件
    private static InventoryChangeTrigger.TriggerInstance hasItem(Item item) {
        return InventoryChangeTrigger.TriggerInstance.hasItems(item);
    }
}