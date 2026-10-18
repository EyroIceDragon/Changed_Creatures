package net.hhdsj.changed_creatures.datagen;

import net.hhdsj.changed_creatures.init.ChangedCreatureModEntities;
import net.hhdsj.changed_creatures.init.ChangedCreatureModItems;
import net.ltxprogrammer.changed.init.ChangedItems;
import net.minecraft.advancements.critereon.EntityFlagsPredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChangedCreaturesEntityLootProvider extends EntityLootSubProvider {

    private final Set<EntityType<?>> added = new HashSet<>();

    public ChangedCreaturesEntityLootProvider() {
        super(FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    public void generate() {
        // ==== 幼崽 ====
        addLoot(ChangedCreatureModEntities.BLACKPUPMALE.get(), LootTable.lootTable()
                .withPool(pool(0, 2, ChangedItems.DARK_LATEX_GOO.get(), 1))
                .withPool(pool(0, 1, ChangedItems.ORANGE.get(), 1))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ChangedItems.DARK_LATEX_MASK.get()))
                        .when(LootItemKilledByPlayerCondition.killedByPlayer())
                        .when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.05f, 0.02f)))
        );

        addLoot(ChangedCreatureModEntities.WHITEBLUEPUP.get(), LootTable.lootTable()
                .withPool(pool(0, 2, ChangedItems.WHITE_LATEX_GOO.get(), 1))
                .withPool(pool(0, 1, ChangedItems.ORANGE.get(), 1))
        );

        addLoot(ChangedCreatureModEntities.INKSUGER.get(), LootTable.lootTable()
                .withPool(pool(0, 1, Items.HONEYCOMB, 1))
                .withPool(pool(0, 2, Items.SPORE_BLOSSOM, 1))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.HONEY_BLOCK))
                        .add(LootItem.lootTableItem(ChangedItems.LATEX_BASE.get()))
                        .when(LootItemKilledByPlayerCondition.killedByPlayer())
                        .when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.075f, 0.02f)))
        );

        // ==== 狼 ====
        addLoot(ChangedCreatureModEntities.DARKFU_LATEX_WOLF_MALE.get(), LootTable.lootTable()
                .withPool(pool(1, 3, ChangedItems.DARK_LATEX_GOO.get(), 1))
                .withPool(pool(0, 2, ChangedItems.DARK_LATEX_CRYSTAL_FRAGMENT.get(), 1))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ChangedItems.ORANGE.get()))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 2), false))
                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0, 1)))
                        .when(LootItemKilledByPlayerCondition.killedByPlayer()))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ChangedItems.DARK_LATEX_MASK.get()))
                        .when(LootItemKilledByPlayerCondition.killedByPlayer())
                        .when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.075f, 0.03f)))
        );

        addLoot(ChangedCreatureModEntities.LATEXPURPLEWSWOLF.get(), LootTable.lootTable()
                .withPool(pool(0, 1, Items.MAGENTA_DYE, 2))
                .withPool(pool(0, 2, ChangedItems.SYRINGE.get(), 1))
                .withPool(pool(0, 2, Items.BONE, 1))
        );

        addLoot(ChangedCreatureModEntities.LATEXTHREEMONTHWOLF.get(), LootTable.lootTable()
                .withPool(pool(0, 1, Items.MAGENTA_DYE, 2))
                .withPool(pool(0, 2, ChangedItems.SYRINGE.get(), 1))
                .withPool(pool(0, 2, Items.BONE, 1))
        );

        addLoot(ChangedCreatureModEntities.LATEXPRTPLECOOCWOLF.get(), LootTable.lootTable()
                .withPool(salmonPool())
        );

        addLoot(ChangedCreatureModEntities.LATEX_ICE_FIELD_WOLF_DRAGON.get(), LootTable.lootTable()
                .withPool(salmonPool())
                .withPool(pool(0, 2, Items.INK_SAC, 1))
                .withPool(pool(0, 2, Items.LAPIS_LAZULI, 1))
        );

        // ==== 龙 ====
        addLoot(ChangedCreatureModEntities.LATEXICE_DRAGON.get(), LootTable.lootTable()
                .withPool(salmonPool())
                .withPool(pool(0, 2, Items.SNOWBALL, 1))
                .withPool(pool(0, 1, Items.ICE, 1))
        );

        addLoot(ChangedCreatureModEntities.LATEXYUNXQHOTDRAGON.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .setBonusRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ChangedCreatureModItems.LATEX_LAVA_CRYSTAL.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 1), false))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 2)))))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .setBonusRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.MAGMA_BLOCK)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4), false))
                                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0, 2)))))
        );

        addLoot(ChangedCreatureModEntities.LATEXYUNXQICEDRAGON.get(), LootTable.lootTable()
                .withPool(salmonPool())
                .withPool(pool(0, 2, Items.SNOWBALL, 1))
                .withPool(pool(0, 1, Items.ICE, 1))
        );

        addLoot(ChangedCreatureModEntities.LATEXBLUEDRAGON.get(), LootTable.lootTable()
                .withPool(salmonPool())
                .withPool(pool(0, 2, Items.SNOWBALL, 1))
                .withPool(pool(0, 1, Items.ICE, 1))
        );

        addLoot(ChangedCreatureModEntities.LATEXYUNQIICEDRAGON.get(), LootTable.lootTable()
                .withPool(salmonPool())
                .withPool(pool(0, 8, Items.SNOWBALL, 1))
                .withPool(pool(0, 8, Items.ICE, 4))
        );

        addLoot(ChangedCreatureModEntities.LATEX_EYRO_END_DRAGON.get(), LootTable.lootTable()
                .withPool(pool(0, 2, ChangedItems.WHITE_LATEX_GOO.get(), 1))
                .withPool(pool(0, 1, Items.ROTTEN_FLESH, 1))
                .withPool(pool(1, 3, Items.ICE, 1))
                .withPool(pool(1, 2, Items.BLUE_DYE, 1))
        );

        addLoot(ChangedCreatureModEntities.LATEXDARKPURPLEDRAGON.get(), LootTable.lootTable()
                .withPool(pool(1, 3, ChangedItems.LATEX_BASE.get(), 1))
                .withPool(pool(0, 2, Items.PURPLE_DYE, 1))
                .withPool(pool(0, 1, Items.DRAGON_BREATH, 1))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.ENCHANTED_GOLDEN_APPLE))
                        .when(LootItemRandomChanceCondition.randomChance(0.0001f)))
        );

        addLoot(ChangedCreatureModEntities.LATEXDARKPURPLEDRAGONTAUR.get(), LootTable.lootTable()
                .withPool(pool(1, 3, ChangedItems.LATEX_BASE.get(), 1))
                .withPool(pool(0, 2, Items.PURPLE_DYE, 1))
                .withPool(pool(0, 1, Items.DRAGON_BREATH, 1))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.ENCHANTED_GOLDEN_APPLE))
                        .when(LootItemRandomChanceCondition.randomChance(0.0001f)))
        );

        addLoot(ChangedCreatureModEntities.LATEXLUOHONGEARLYSPRINGFOXDRAGON.get(), LootTable.lootTable()
                .withPool(pool(1, 3, Items.RABBIT_HIDE, 1))
                .withPool(pool(0, 2, Items.FEATHER, 1))
                .withPool(pool(0, 1, Items.CHERRY_LEAVES, 1))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.CHERRY_SAPLING))
                        .when(LootItemRandomChanceCondition.randomChance(0.1f)))
        );

        addLoot(ChangedCreatureModEntities.LATEXFRUITDRAGONWOLF.get(), LootTable.lootTable()
                .withPool(pool(1, 2, ChangedCreatureModItems.DRAGONFRUIT.get(), 1))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ChangedItems.ORANGE.get()))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 5), false)))
        );

        addLoot(ChangedCreatureModEntities.LATEXNIGHTOWLDRAGON.get(), LootTable.lootTable()
                .withPool(poolWithBonus(0, 1, 1, Items.BLAZE_POWDER, 2))
                .withPool(poolWithBonus(1, 1, 4, Items.MAGMA_BLOCK, 2))
        );

        addLoot(ChangedCreatureModEntities.LATEX_ORANGE_FOX.get(), LootTable.lootTable()
                .withPool(poolWithBonus(0, 1, 1, Items.BLAZE_POWDER, 2))
        );

        addLoot(ChangedCreatureModEntities.LATEXNEONDIMNESSWOLF.get(), LootTable.lootTable()
                .withPool(poolWithBonus(1, 1, 3, Items.COAL, 4))
        );

        addLoot(ChangedCreatureModEntities.LATEXRADIATIONFOX.get(), LootTable.lootTable()
                .withPool(pool(0, 1, ChangedCreatureModItems.IMPUREURANIUM.get(), 2))
        );

        addLoot(ChangedCreatureModEntities.LATEXIQGOLDDRAGON.get(), LootTable.lootTable()
                .withPool(pool(1, 7, Items.GOLD_NUGGET, 2))
                .withPool(pool(0, 2, Items.SAND, 1))
                .withPool(pool(0, 1, Items.YELLOW_DYE, 1))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.PHANTOM_MEMBRANE))
                        .when(LootItemKilledByPlayerCondition.killedByPlayer())
                        .when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.15f, 0.02f)))
        );

        addLoot(ChangedCreatureModEntities.LATEX_CHENG_XI.get(), LootTable.lootTable()
                .withPool(pool(1, 6, Items.GOLD_INGOT, 1))
                .withPool(pool(1, 9, Items.GOLD_NUGGET, 1))
        );

        addLoot(ChangedCreatureModEntities.LATEX_ETAN_KIND.get(), LootTable.lootTable()
                .withPool(pool(0, 2, Items.LAPIS_LAZULI, 1))
                .withPool(pool(0, 2, Items.BONE, 1))
        );

        addLoot(ChangedCreatureModEntities.LATEX_INGERTE_FOX.get(), LootTable.lootTable()
                .withPool(pool(0, 2, ChangedItems.WHITE_LATEX_GOO.get(), 1))
                .withPool(pool(0, 1, Items.ROTTEN_FLESH, 1))
        );

        addLoot(ChangedCreatureModEntities.LATEX_ENKANG_DRAGON_SHARK.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.PHANTOM_MEMBRANE)))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.BLUE_DYE)))
        );

        addLoot(ChangedCreatureModEntities.LATEX_KCAHRA_SHARK.get(), LootTable.lootTable()
                .withPool(salmonPool())
        );

        // ==== BOSS ====
        addLoot(ChangedCreatureModEntities.LATEX_ICE_FIELD_WOLF_DRAGON_BOSS.get(), LootTable.lootTable());
        addLoot(ChangedCreatureModEntities.LATEXNIGHTOWLDRAGONBOSS.get(), LootTable.lootTable());

        checkMissingMobLootTables();
    }

    /**
     * 检查并输出缺少战利品表的生物实体。
     * <p>
     * 逻辑：取 {@link ChangedCreatureModEntities#REGISTRY} 中所有 {@link MobCategory} 不为
     * {@link MobCategory#MISC} 的实体（即排除箭、投掷物、三叉戟等非生物实体），
     * 减去本类已经通过 {@link #addLoot} 添加过战利品表的 {@link #added} 集合，
     * 剩下的就是"已注册为生物但没写战利品表"的实体，逐个打印到控制台。
     * <p>
     * 不抛异常，避免因新实体尚未补表而中断 {@code runData}；如需强制失败，
     * 可把输出改为抛出 {@link IllegalStateException}。
     */
    private void checkMissingMobLootTables() {
        Set<EntityType<?>> allMobs = ChangedCreatureModEntities.REGISTRY.getEntries().stream()
                .flatMap(RegistryObject::stream)
                .filter(type -> type.getCategory() != MobCategory.MISC)
                .collect(Collectors.toSet());

        allMobs.removeAll(added);

        if (allMobs.isEmpty()) {
            System.out.println("[ChangedCreatures] 所有生物实体均已添加战利品表。");
            return;
        }

        System.out.println("[ChangedCreatures] ========== 以下生物实体缺少战利品表 ==========");
        allMobs.stream()
                .map(ForgeRegistries.ENTITY_TYPES::getKey)
                .sorted(java.util.Comparator.nullsLast(ResourceLocation::compareTo))
                .forEach(id -> System.out.println("[ChangedCreatures]   - " + id));
        System.out.println("[ChangedCreatures] ==============================================");
        System.out.println("[ChangedCreatures] 共 " + allMobs.size() + " 个生物实体缺少战利品表。");
    }

    /**
     * 添加战利品表并记录实体。
     * <p>
     * 先调用父类 {@link EntityLootSubProvider#add(EntityType, LootTable.Builder)} 完成实际的表注册，
     * 再把该 {@link EntityType} 记入 {@link #added}。{@link #added} 有两个用途：
     * <ul>
     *   <li>{@link #getKnownEntityTypes()} 返回它，Forge 只校验"已添加的实体"，不会因为
     *       未写表的实体而抛 {@code IllegalStateException}；</li>
     *   <li>{@link #checkMissingMobLootTables()} 用它做差集，找出缺失表的生物。</li>
     * </ul>
     *
     * @param type    实体类型
     * @param builder 战利品表构建器
     */
    private void addLoot(EntityType<?> type, LootTable.Builder builder) {
        super.add(type, builder);
        added.add(type);
    }

    /**
     * 生成"单物品池"：抽取 1 次，物品数量在 {@code [min, max]} 之间随机（直接设置，不叠加），
     * 并附加抢夺附魔加成，每级抢夺额外增加 {@code [0, lootingMax]} 个。
     * <p>
     * 对应 JSON 结构：
     * <pre>{@code
     * {
     *   "rolls": 1.0,
     *   "entries": [{
     *     "type": "minecraft:item",
     *     "name": "...",
     *     "functions": [
     *       { "function": "minecraft:set_count",
     *         "count": { "type": "minecraft:uniform", "min": min, "max": max },
     *         "add": false },
     *       { "function": "minecraft:looting_enchant",
     *         "count": { "type": "minecraft:uniform", "min": 0, "max": lootingMax } }
     *     ]
     *   }]
     * }
     * }</pre>
     *
     * @param min        基础数量下限
     * @param max        基础数量上限
     * @param item       掉落物品
     * @param lootingMax 每级抢夺额外增加的最大数量
     * @return 配置好的战利品池构建器
     */
    private static LootPool.Builder pool(int min, int max, Item item, int lootingMax) {
        return LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max), false))
                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0, lootingMax))));
    }

    /**
     * 生成"带额外抽取次数的单物品池"：在 {@link #pool} 的基础上增加 {@code bonus_rolls}。
     * <p>
     * 对应 JSON 的 {@code "bonus_rolls": bonus}，用于少数龙类（如夜鸮龙、橙狐）的额外掉落。
     * 数量与抢夺逻辑同 {@link #pool}。
     *
     * @param min        基础数量下限
     * @param bonus      额外抽取次数
     * @param max        基础数量上限
     * @param item       掉落物品
     * @param lootingMax 每级抢夺额外增加的最大数量
     * @return 配置好的战利品池构建器
     */
    private static LootPool.Builder poolWithBonus(int min, int bonus, int max, Item item, int lootingMax) {
        return LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))
                .setBonusRolls(ConstantValue.exactly(bonus))
                .add(LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max), false))
                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0, lootingMax))));
    }

    /**
     * 生成"生鲑鱼池"：掉落 0~2 条生鲑鱼，带抢夺加成（每级 0~1），
     * 且当实体死亡时处于着火状态，通过 {@code minecraft:furnace_smelt} 熔炼为熟鲑鱼。
     * <p>
     * 对应 JSON 结构：
     * <pre>{@code
     * {
     *   "rolls": 1.0,
     *   "entries": [{
     *     "type": "minecraft:item",
     *     "name": "minecraft:salmon",
     *     "functions": [
     *       { "function": "minecraft:set_count",
     *         "count": { "type": "minecraft:uniform", "min": 0, "max": 2 }, "add": false },
     *       { "function": "minecraft:looting_enchant",
     *         "count": { "type": "minecraft:uniform", "min": 0, "max": 1 } },
     *       { "function": "minecraft:furnace_smelt",
     *         "conditions": [{
     *           "condition": "minecraft:entity_properties",
     *           "entity": "this",
     *           "predicate": { "flags": { "is_on_fire": true } }
     *         }]
     *       }
     *     ]
     *   }]
     * }
     * }</pre>
     *
     * @return 配置好的生鲑鱼战利品池构建器
     */
    private static LootPool.Builder salmonPool() {
        return LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(Items.SALMON)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 2), false))
                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0, 1)))
                        .apply(SmeltItemFunction.smelted()
                                .when(LootItemEntityPropertyCondition.hasProperties(
                                        LootContext.EntityTarget.THIS,
                                        EntityPredicate.Builder.entity().flags(
                                                EntityFlagsPredicate.Builder.flags().setOnFire(true).build())))));
    }

    /**
     * 返回本类已添加战利品表的实体集合。
     * <p>
     * 故意只返回 {@link #added} 而非全部注册实体，这样 Forge 的校验不会因为
     * 未写表的实体（如箭、投掷物）而中断 {@code runData}；缺失情况由
     * {@link #checkMissingMobLootTables()} 自行打印。
     *
     * @return 已添加战利品表的实体流
     */
    @Override
    protected Stream<EntityType<?>> getKnownEntityTypes() {
        return added.stream();
    }
}