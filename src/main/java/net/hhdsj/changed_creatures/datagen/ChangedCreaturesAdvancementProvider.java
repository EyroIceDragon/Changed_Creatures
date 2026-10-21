package net.hhdsj.changed_creatures.datagen;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.init.ChangedCreatureModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.function.Consumer;

public class ChangedCreaturesAdvancementProvider implements ForgeAdvancementProvider.AdvancementGenerator {

    @Override
    public void generate(HolderLookup.Provider registries, Consumer<Advancement> writer, ExistingFileHelper existingFileHelper) {
        // ==================== 根成就 ====================
        Advancement root = Advancement.Builder.advancement()
                .display(
                        new ItemStack(ChangedCreatureModItems.PALETREE.get()),
                        Component.translatable("advancements.changed_creatures.root.title"),
                        Component.translatable("advancements.changed_creatures.root.descr"),
                        new ResourceLocation("textures/gui/advancements/backgrounds/stone.png"),
                        FrameType.TASK,
                        true,
                        true,
                        false
                )

                .addCriterion("has_mod_item", PlayerTrigger.TriggerInstance.tick())
                .save(writer, ChangedCreature.CCRL("root"), existingFileHelper);

        // ==================== 基础材料 ====================
        Advancement sulphur = Advancement.Builder.advancement()
                .parent(root)
                .display(
                        new ItemStack(ChangedCreatureModItems.SULPHUR.get()),
                        Component.translatable("advancements.changed_creatures.sulphur.title"),
                        Component.translatable("advancements.changed_creatures.sulphur.descr"),
                        null,
                        FrameType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_sulphur", InventoryChangeTrigger.TriggerInstance.hasItems(ChangedCreatureModItems.SULPHUR.get()))
                .rewards(AdvancementRewards.Builder.experience(1))
                .save(writer, ChangedCreature.CCRL("sulphur"), existingFileHelper);

        Advancement nitricAcid = Advancement.Builder.advancement()
                .parent(root)
                .display(
                        new ItemStack(ChangedCreatureModItems.NITRICACID.get()),
                        Component.translatable("advancements.changed_creatures.nitric_acid.title"),
                        Component.translatable("advancements.changed_creatures.nitric_acid.descr"),
                        null,
                        FrameType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_nitric_acid", InventoryChangeTrigger.TriggerInstance.hasItems(ChangedCreatureModItems.NITRICACID.get()))
                .rewards(AdvancementRewards.Builder.experience(1))
                .save(writer, ChangedCreature.CCRL("nitric_acid"), existingFileHelper);

        // ==================== 黎明系列 ====================
        Advancement painiteHoe = Advancement.Builder.advancement()
                .parent(root)
                .display(
                        new ItemStack(ChangedCreatureModItems.PAINITEHOE.get()),
                        Component.translatable("advancements.changed_creatures.painite_hoe.title"),
                        Component.translatable("advancements.changed_creatures.painite_hoe.descr"),
                        null,
                        FrameType.CHALLENGE,
                        true,
                        true,
                        false
                )
                .addCriterion("has_painite_hoe", InventoryChangeTrigger.TriggerInstance.hasItems(ChangedCreatureModItems.PAINITEHOE.get()))
                .save(writer, ChangedCreature.CCRL("painite_hoe"), existingFileHelper);

        // ==================== 水晶簇 ====================
        Advancement crystalCluster = Advancement.Builder.advancement()
                .parent(root)
                .display(
                        new ItemStack(ChangedCreatureModItems.Frost_Condensed_Crystal_Cluster.get()),
                        Component.translatable("advancements.changed_creatures.crystal_cluster.title"),
                        Component.translatable("advancements.changed_creatures.crystal_cluster.descr"),
                        null,
                        FrameType.TASK,
                        true,
                        false,
                        false
                )
                .addCriterion("has_crystal_cluster", InventoryChangeTrigger.TriggerInstance.hasItems(ChangedCreatureModItems.Frost_Condensed_Crystal_Cluster.get()))
                .save(writer, ChangedCreature.CCRL("crystal_cluster"), existingFileHelper);

        Advancement latexFuzz = Advancement.Builder.advancement()
                .parent(root)
                .display(
                        new ItemStack(ChangedCreatureModItems.LATEX_DAWN_FUZZ.get()),
                        Component.translatable("advancements.changed_creatures.latex_fuzz.title"),
                        Component.translatable("advancements.changed_creatures.latex_fuzz.descr"),
                        null,
                        FrameType.TASK,
                        true,
                        false,
                        false
                )
                .addCriterion("has_latex_fuzz", InventoryChangeTrigger.TriggerInstance.hasItems(ChangedCreatureModItems.LATEX_DAWN_FUZZ.get()))
                .save(writer, ChangedCreature.CCRL("latex_fuzz"), existingFileHelper);


    }
}