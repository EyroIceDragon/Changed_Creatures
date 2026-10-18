package net.hhdsj.changed_creatures.datagen;

import net.hhdsj.changed_creatures.init.ChangedCreatureModBlocks;
import net.hhdsj.changed_creatures.init.ChangedCreatureModItems;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.HashSet;
import java.util.Set;

public class ChangedCreaturesBlockLootProvider extends BlockLootSubProvider {

    private final Set<Block> added = new HashSet<>();

    public ChangedCreaturesBlockLootProvider() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        // ==== 矿石 ====
        addLoot(ChangedCreatureModBlocks.BLUE_CRYSTALORE.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(AlternativesEntry.alternatives(
                                LootItem.lootTableItem(ChangedCreatureModBlocks.BLUE_CRYSTALORE.get())
                                        .when(MatchTool.toolMatches(
                                                ItemPredicate.Builder.item().hasEnchantment(
                                                        new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))))),
                                LootItem.lootTableItem(ChangedCreatureModItems.BlueCrystaloreItem.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))
                                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 1))
                        )))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ChangedCreatureModItems.Impure_Frost_Condensed_Crystal_Cluster.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 1)))))
        );

        addLoot(ChangedCreatureModBlocks.URANIUM_ORE.get(), LootTable.lootTable()
                .withPool(pool(1, 2, ChangedCreatureModItems.IMPUREURANIUM.get()))
        );

        // ==== 矿物块 ====
        addLoot(ChangedCreatureModBlocks.SULPHURBLOCK.get(), LootTable.lootTable()
                .withPool(pool(1, 4, ChangedCreatureModItems.SULPHUR.get()))
        );

        addLoot(ChangedCreatureModBlocks.NITREBLOCK.get(), LootTable.lootTable()
                .withPool(pool(1, 6, ChangedCreatureModItems.NITRICACID.get()))
        );

        addLoot(ChangedCreatureModBlocks.URANIUMBLOCK.get(), LootTable.lootTable()
                .withPool(pool(1, 1, ChangedCreatureModItems.IMPUREURANIUMINGOT.get()))
        );

        addLoot(ChangedCreatureModBlocks.BLUEBLOCK.get(), LootTable.lootTable()
                .withPool(pool(1, 1, ChangedCreatureModItems.BlueCrystaloreItem.get()))
        );

        addLoot(ChangedCreatureModBlocks.BLACKBLOCK.get(), LootTable.lootTable()
                .withPool(pool(1, 1, Items.COAL))
        );

        // ==== 装饰/功能方块 ====
        addLoot(ChangedCreatureModBlocks.PALE_WOOD_LEAVES.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .setBonusRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ChangedCreatureModItems.PALETREE.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 1)))))
        );
        simpleDrop(ChangedCreatureModBlocks.LATEX_DEEP_CRYSTAL_DRAGON_DOLL.get());

        // ==== 普通方块：自掉落 ====
        simpleDrop(ChangedCreatureModBlocks.PALE_WOOD_PLANKS.get());
        simpleDrop(ChangedCreatureModBlocks.PALE_WOOD_LOG.get());
        simpleDrop(ChangedCreatureModBlocks.PALE_WOOD_WOOD.get());
        simpleDrop(ChangedCreatureModBlocks.PALE_WOOD_STAIRS.get());
        simpleDrop(ChangedCreatureModBlocks.PALE_WOOD_SLAB.get());
        simpleDrop(ChangedCreatureModBlocks.PALE_WOOD_BUTTON.get());
        simpleDrop(ChangedCreatureModBlocks.PALE_WOOD_FENCE.get());
        simpleDrop(ChangedCreatureModBlocks.PALE_WOOD_FENCE_GATE.get());
        simpleDrop(ChangedCreatureModBlocks.PALE_WOOD_PRESSURE_PLATE.get());
        simpleDrop(ChangedCreatureModBlocks.PALETREESPA_1.get());
        simpleDrop(ChangedCreatureModBlocks.IRONHOT.get());
        simpleDrop(ChangedCreatureModBlocks.CATALYZER.get());
        simpleDrop(ChangedCreatureModBlocks.CRYO_SUBJECT_CHAMBER.get());
        simpleDrop(ChangedCreatureModBlocks.MOLTEN_CRYSTAL.get());

        checkMissingBlockLootTables();
    }

    private void addLoot(Block block, LootTable.Builder builder) {
        super.add(block, builder);
        added.add(block);
    }

    private void simpleDrop(Block block) {
        addLoot(block, LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(block))
                        .when(ExplosionCondition.survivesExplosion()))
        );
    }

    private static LootPool.Builder pool(int min, int max, Item item) {
        return LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max), false)));
    }

    private void checkMissingBlockLootTables() {
        Set<Block> all = ChangedCreatureModBlocks.REGISTRY.getEntries().stream()
                .flatMap(net.minecraftforge.registries.RegistryObject::stream)
                .collect(java.util.stream.Collectors.toSet());
        all.removeAll(added);
        if (all.isEmpty()) {
            System.out.println("[ChangedCreatures] 所有方块均已添加战利品表。");
            return;
        }
        System.out.println("[ChangedCreatures] ========== 以下方块缺少战利品表 ==========");
        all.stream()
                .map(net.minecraftforge.registries.ForgeRegistries.BLOCKS::getKey)
                .sorted(java.util.Comparator.nullsLast(net.minecraft.resources.ResourceLocation::compareTo))
                .forEach(id -> System.out.println("[ChangedCreatures]   - " + id));
        System.out.println("[ChangedCreatures] 共 " + all.size() + " 个方块缺少战利品表。");
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return added;
    }
}