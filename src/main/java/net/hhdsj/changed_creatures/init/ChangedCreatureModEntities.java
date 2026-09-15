/*
 *    这些为注册类,请将注册写在这里,而不是其它地方!
 */
package net.hhdsj.changed_creatures.init;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.entity.boss.*;
import net.hhdsj.changed_creatures.entity.simple.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeSpawnEggItem;

import net.ltxprogrammer.changed.entity.ChangedEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ChangedCreatureModEntities {
    public interface VoidConsumer { void accept(); }

    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ChangedCreature.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ChangedCreature.MODID);
    
    // 存储实体颜色（用于刷怪蛋）
    private static final Map<ResourceLocation, Pair<Integer, Integer>> ENTITY_COLOR_MAP = new HashMap<>();

    // 存储属性构建器
    private static final List<Pair<Supplier<EntityType<? extends ChangedEntity>>, Supplier<AttributeSupplier.Builder>>> ATTR_FUNC_REGISTRY = new ArrayList<>();

    // 存储初始化函数（如生成规则）
    private static final List<VoidConsumer> INIT_FUNC_REGISTRY = new ArrayList<>();

    // 存储刷怪蛋
    public static final Map<RegistryObject<? extends EntityType<?>>, RegistryObject<ForgeSpawnEggItem>> SPAWN_EGGS = new HashMap<>();

    // 简单的Pair类
    public static class Pair<F, S> {
        private final F first;
        private final S second;

        public Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }

        public F getFirst() { return first; }
        public S getSecond() { return second; }
    }

    // 狐狸变体
    public static final RegistryObject<EntityType<LatexOrangeFoxEntity>> LATEX_ORANGE_FOX;
    public static final RegistryObject<EntityType<LatexRadiationFoxEntity>> LATEXRADIATIONFOX;
    public static final RegistryObject<EntityType<LatexDivineSwordKitsuneEntity>> LATEX_DIVINE_SWORD_KITSUNE;
    public static final RegistryObject<EntityType<LatexGaoHuiFoxEntity>> LATEX_GAO_HUI_FOX;
    public static final RegistryObject<EntityType<LatexHyazintheFoxEntity>> LATEX_HYAZINTHE_FOX;
    public static final RegistryObject<EntityType<LatexYueXiFoxFemaleEntity>> LATEX_YUE_XI_FOX_FEMALE;
    public static final RegistryObject<EntityType<LatexFloraFoxEntity>> LATEX_FLORA_FOX;
    public static final RegistryObject<EntityType<LatexEtanKindEntity>> LATEX_ETAN_KIND;
    public static final RegistryObject<EntityType<LatexIngerteFoxEntity>> LATEX_INGERTE_FOX;
    // 鲨鱼变体
    public static final RegistryObject<EntityType<LatexKcahraSharkEntity>> LATEX_KCAHRA_SHARK;
    public static final RegistryObject<EntityType<LatexEnkangDragonSharkEntity>> LATEX_ENKANG_DRAGON_SHARK;
    public static final RegistryObject<EntityType<LatexQianGuiSharkEntity>> LATEX_QIAN_GUI_SHARK;

    // 狼变体
    public static final RegistryObject<EntityType<DarkfuLatexWolfMaleEntity>> DARKFU_LATEX_WOLF_MALE;
    public static final RegistryObject<EntityType<LatexpurplewswolfEntity>> LATEXPURPLEWSWOLF;
    public static final RegistryObject<EntityType<LatexthreemonthwolfEntity>> LATEXTHREEMONTHWOLF;
    public static final RegistryObject<EntityType<LatexPurplecoocwolfEntity>> LATEXPRTPLECOOCWOLF;
    //public static final RegistryObject<EntityType<LatexIceFieldWolfDragonEntity>> LATEX_ICE_FIELD_WOLF_DRAGON;
    public static final RegistryObject<EntityType<LatexFruitDragonEntity>> LATEXFRUITDRAGONWOLF;
    public static final RegistryObject<EntityType<LatexNeondimnessWolfEntity>> LATEXNEONDIMNESSWOLF;
    public static final RegistryObject<EntityType<LatexChengXiEntity>> LATEX_CHENG_XI;
    public static final RegistryObject<EntityType<LatexDawnWolfEntity>> LATEX_DAWN_WOLF;
    public static final RegistryObject<EntityType<LatexDuskDawnDragonEntity>> LATEX_DUSK_DAWN_DRAGON;
    public static final RegistryObject<EntityType<LatexDuskDawnDragonFemaleEntity>> LATEX_DUSK_DAWN_DRAGON_FEMALE;
    public static final RegistryObject<EntityType<LatexSteppenWolfEntity>> LATEX_STEPPEN_WOLF;
    public static final RegistryObject<EntityType<LatexPeanutButterWolfEntity>> LATEX_PEAUNT_BUTTER_WOLF;

    // 幼崽变体
    public static final RegistryObject<EntityType<BlackpupmaleEntity>> BLACKPUPMALE;
    public static final RegistryObject<EntityType<WhitebluepupEntity>> WHITEBLUEPUP;

    // 龙变体
    public static final RegistryObject<EntityType<LatexiceDragonEntity>> LATEXICE_DRAGON;
    public static final RegistryObject<EntityType<LatexyunxqhotdragonEntity>> LATEXYUNXQHOTDRAGON;
    public static final RegistryObject<EntityType<LatexyunxqicedragonEntity>> LATEXYUNXQICEDRAGON;
    public static final RegistryObject<EntityType<LatexbluedragonEntity>> LATEXBLUEDRAGON;
    public static final RegistryObject<EntityType<LatexYunQiIceDragonEntity>> LATEXYUNQIICEDRAGON;
    public static final RegistryObject<EntityType<LatexFrostScaleDragonTaurEntity>> LATEX_FROST_SCALE_DRAGON_TAUR;
    public static final RegistryObject<EntityType<LatexDarkPurpleDragonTaurEntity>> LATEXDARKPURPLEDRAGONTAUR;
    public static final RegistryObject<EntityType<DarkPurpleLatexDragonEntity>> LATEXDARKPURPLEDRAGON;
    public static final RegistryObject<EntityType<LatexLuoHongEarlySpringFoxDragonEntity>> LATEXLUOHONGEARLYSPRINGFOXDRAGON;
    public static final RegistryObject<EntityType<LatexYaoLingDragonEntity>> LATEXYAOLINGDRAGON;
    public static final RegistryObject<EntityType<LatexCrystalJellyDragonEntity>> LATEX_CRYSTAL_JELLY;
    public static final RegistryObject<EntityType<LatexEmberscalesMagmaDragonMaleEntity>> LATEX_EMBERSCALES_MAGMA_DRAGON_MALE;
    public static final RegistryObject<EntityType<LatexXuanJinDragonEntity>> LATEX_XUAN_JIN_DRAGON;
    public static final RegistryObject<EntityType<LatexEyroEndDragonEntity>> LATEX_EYRO_END_DRAGON;


    // BOSS变体
    //public static final RegistryObject<EntityType<LatexIceFieldWolfDragonBossEntity>> LATEX_ICE_FIELD_WOLF_DRAGON_BOSS;
    public static final RegistryObject<EntityType<LatexNightOwlBossEntity>> LATEXNIGHTOWLDRAGONBOSS;
    public static final RegistryObject<EntityType<LatexNightOwlEntity>> LATEXNIGHTOWLDRAGON;
    public static final RegistryObject<EntityType<LatexIqGoldDragonEntity>> LATEXIQGOLDDRAGON;

    // 其他变体
    public static final RegistryObject<EntityType<InksugerEntity>> INKSUGER;
    public static final RegistryObject<EntityType<LatexthreemonthwolfEntityProjectile>> LATEXTHREEMONTHWOLF_PROJECTILE;
    public static final RegistryObject<EntityType<BlueCrystalProjectile>> BLUECRYSTALPROJECTILE;
    public static final RegistryObject<EntityType<ThrownCrystalJavelinTrident>> THROWN_CRYSTAL_JAVELIN;
    public static final RegistryObject<EntityType<CrystalArrow>> CRYSTAL_ARROW;
    //龙鲨变体
    public static final RegistryObject<EntityType<LatexLuoLongDragonSharkEntity>> LUOLONG_DRAGON_SHARK;
    //Cat Latex
    public static final RegistryObject<EntityType<LatexBlueveCatEntity>> LATEX_BLUEVE_CAT;

    public static final RegistryObject<EntityType<ProtogenNyxarixEntity>> PROTOGEN_NYXARIX;

    //龙狼变体
    public static final RegistryObject<EntityType<LatexXuanJinWolfDragonEntity>> LATEX_XUAN_JIN_WOLF_DRAGON;

    static {
        LATEX_INGERTE_FOX = registerSpawning("latex_ingerte_fox", 0xfeb46b, 0xffeb7b,
                EntityType.Builder.of(LatexIngerteFoxEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .sized(0.6f, 1.92f),
                LatexIngerteFoxEntity::createLatexAttributes);

        LATEX_EYRO_END_DRAGON = registerSpawning("latex_eyro_end_dragon", 0x57e4ff, 0x98ffff,
                EntityType.Builder.of(LatexEyroEndDragonEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .sized(0.6f, 1.92f),
                LatexEyroEndDragonEntity::createLatexAttributes);

        LATEX_XUAN_JIN_DRAGON = registerSpawning("latex_xuan_jin_dragon", 0x1c2126, 0xe6ab00,
                EntityType.Builder.<LatexXuanJinDragonEntity>of(LatexXuanJinDragonEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .sized(0.6f, 1.92f),
                LatexXuanJinDragonEntity::createLatexAttributes);
        LATEX_XUAN_JIN_WOLF_DRAGON = registerSpawning("latex_xuan_jin_wolf_dragon", 0x1c2126, 0xe6ab00,
                EntityType.Builder.<LatexXuanJinWolfDragonEntity>of(LatexXuanJinWolfDragonEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .sized(0.6f, 1.92f),
                LatexXuanJinWolfDragonEntity::createLatexAttributes);
        LATEX_PEAUNT_BUTTER_WOLF = registerSpawning("latex_peaunt_butter_wolf", 0xFDCD6E, 0xFEF19B,
                EntityType.Builder.<LatexPeanutButterWolfEntity>of(LatexPeanutButterWolfEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .sized(0.6f, 1.92f),
                LatexPeanutButterWolfEntity::createLatexAttributes);

        LATEX_STEPPEN_WOLF = registerSpawning("latex_steppen_wolf", 0x303030, 0xffffff,
                EntityType.Builder.<LatexSteppenWolfEntity>of(LatexSteppenWolfEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .sized(0.6f, 1.92f),
                LatexSteppenWolfEntity::createLatexAttributes);
        
        PROTOGEN_NYXARIX = registerSpawning("protogen_nyxarix", 0xFFFFFF, 0x00ffff,
                EntityType.Builder.<ProtogenNyxarixEntity>of(ProtogenNyxarixEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .setCustomClientFactory(ProtogenNyxarixEntity::new)
                        .sized(0.6f, 1.92f),
                ProtogenNyxarixEntity::createLatexAttributes);

        // 在 static 块中
        LATEX_EMBERSCALES_MAGMA_DRAGON_MALE = registerSpawning("latex_emberscales_magma_dragon_male", 0x8d0d01, 0xfa2303,
                EntityType.Builder.<LatexEmberscalesMagmaDragonMaleEntity>of(LatexEmberscalesMagmaDragonMaleEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .setCustomClientFactory(LatexEmberscalesMagmaDragonMaleEntity::new)
                        .sized(0.6f, 1.92f),
                LatexEmberscalesMagmaDragonMaleEntity::createLatexAttributes);

        LATEX_FROST_SCALE_DRAGON_TAUR = registerSpawning("latex_frost_scale_dragon_taur", 0x232323, 0x00ffff,
                EntityType.Builder.<LatexFrostScaleDragonTaurEntity>of(LatexFrostScaleDragonTaurEntity::new, MobCategory.MONSTER)
                        .clientTrackingRange(10)
                        .sized(1.4f, 2.5f),
                LatexFrostScaleDragonTaurEntity::createLatexAttributes);

        LATEX_CRYSTAL_JELLY = registerSpawning("latex_crystal_jelly_dragon", 0x1c408f, 0x42d3ff,
                EntityType.Builder.<LatexCrystalJellyDragonEntity>of(LatexCrystalJellyDragonEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .setCustomClientFactory(LatexCrystalJellyDragonEntity::new)
                        .sized(0.6f, 1.92f),
                LatexCrystalJellyDragonEntity::createLatexAttributes);
        
        LATEX_DUSK_DAWN_DRAGON_FEMALE = registerSpawning("latex_dusk_dawn_wolf_female", 0x000000, 0xffffff,
                EntityType.Builder.<LatexDuskDawnDragonFemaleEntity>of(LatexDuskDawnDragonFemaleEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .setCustomClientFactory(LatexDuskDawnDragonFemaleEntity::new)
                        .sized(0.6f, 1.92f),
                LatexDuskDawnDragonFemaleEntity::createLatexAttributes);

        LATEX_DUSK_DAWN_DRAGON = registerSpawning("latex_dusk_dawn_wolf", 0x000000, 0xffffff,
                EntityType.Builder.<LatexDuskDawnDragonEntity>of(LatexDuskDawnDragonEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .setCustomClientFactory(LatexDuskDawnDragonEntity::new)
                        .sized(0.6f, 1.92f),
                LatexDuskDawnDragonEntity::createLatexAttributes);

        LATEX_ETAN_KIND = registerSpawning("latex_etan_kind", 0x3c6a7d, 0xe9e564,
                EntityType.Builder.<LatexEtanKindEntity>of(LatexEtanKindEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .setCustomClientFactory(LatexEtanKindEntity::new)
                        .sized(0.6f, 1.95f),
                LatexEtanKindEntity::createAttributes);

        LUOLONG_DRAGON_SHARK = registerSpawning("latex_luo_long_dragon_shark", 0x282b30, 0xe2506c,
                EntityType.Builder.<LatexLuoLongDragonSharkEntity>of(LatexLuoLongDragonSharkEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .setCustomClientFactory(LatexLuoLongDragonSharkEntity::new)
                        .sized(0.6f, 1.95f),
                LatexLuoLongDragonSharkEntity::createLatexAttributes);

        LATEX_FLORA_FOX = registerSpawning("latex_flora_fox_female", 0xd72454, 0xfdb1ef,
                EntityType.Builder.<LatexFloraFoxEntity>of(LatexFloraFoxEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .setCustomClientFactory(LatexFloraFoxEntity::new)
                        .sized(0.6f, 1.95f),
                LatexFloraFoxEntity::createLatexAttributes);

        LATEX_YUE_XI_FOX_FEMALE = registerSpawning("latex_yue_xi_fox_female", 0xff9f0d, 0xffee86,
                EntityType.Builder.<LatexYueXiFoxFemaleEntity>of(LatexYueXiFoxFemaleEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .setCustomClientFactory(LatexYueXiFoxFemaleEntity::new)
                        .sized(0.6f, 1.95f),
                LatexYueXiFoxFemaleEntity::createLatexAttributes);
        
        LATEX_QIAN_GUI_SHARK = registerSpawning("latex_qian_gui_shark", 0xcce063, 0x435150,
                EntityType.Builder.<LatexQianGuiSharkEntity>of(LatexQianGuiSharkEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .setCustomClientFactory(LatexQianGuiSharkEntity::new)
                        .sized(0.6f, 1.95f),
                LatexQianGuiSharkEntity::createLatexAttributes);
        
        LATEX_GAO_HUI_FOX = registerSpawning("latex_gao_hui_fox", 0x7478fe, 0x8c74fe,
                EntityType.Builder.<LatexGaoHuiFoxEntity>of(LatexGaoHuiFoxEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .setCustomClientFactory(LatexGaoHuiFoxEntity::new)
                        .sized(0.6f, 1.95f),
                LatexGaoHuiFoxEntity::createLatexAttributes);
        
        LATEX_HYAZINTHE_FOX = registerSpawning("latex_hyazinthe_fox", 0xFF62FF, 0xFF84FF,
                EntityType.Builder.<LatexHyazintheFoxEntity>of(LatexHyazintheFoxEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .setCustomClientFactory(LatexHyazintheFoxEntity::new)
                        .sized(0.6f, 2f),
                LatexHyazintheFoxEntity::createLatexAttributes);

        LATEX_ENKANG_DRAGON_SHARK = registerSpawning("latex_enkang_dragon_shark", 0x00b4ff, 0x0071a0,
                EntityType.Builder.<LatexEnkangDragonSharkEntity>of(LatexEnkangDragonSharkEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .fireImmune()
                        .setCustomClientFactory(LatexEnkangDragonSharkEntity::new)
                        .sized(0.6f, 1.95f),
                LatexEnkangDragonSharkEntity::createLatexAttributes);

        LATEX_DAWN_WOLF = registerSpawning("latex_dawn_wolf", 0xa2eaff, 0x42c7ed,
                EntityType.Builder.<LatexDawnWolfEntity>of(LatexDawnWolfEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .setCustomClientFactory(LatexDawnWolfEntity::new)
                        .sized(0.6f, 1.95f),
                LatexDawnWolfEntity::createLatexAttributes);

        LATEX_BLUEVE_CAT = registerSpawning("latex_blueve_cat", 0xf0f0f0, 0x4ea1ef,
                EntityType.Builder.<LatexBlueveCatEntity>of(LatexBlueveCatEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .setCustomClientFactory(LatexBlueveCatEntity::new)
                        .sized(0.6f, 1.95f),
                LatexBlueveCatEntity::createLatexAttributes);

        LATEX_CHENG_XI = registerSpawning("latex_cheng_xi_fox", 0xFDCD6E, 0xFEF19B,
                EntityType.Builder.<LatexChengXiEntity>of(LatexChengXiEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .setCustomClientFactory(LatexChengXiEntity::new)
                        .sized(0.6f, 1.95f),
                LatexChengXiEntity::createLatexAttributes);  // 使用父类默认属性
        // 狐狸变体 - 使用注册方法
        LATEX_ORANGE_FOX = registerSpawning("latex_orange_fox", 0xFF8C42, 0xFF5500,
                EntityType.Builder.<LatexOrangeFoxEntity>of(LatexOrangeFoxEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .setCustomClientFactory(LatexOrangeFoxEntity::new)
                        .sized(0.6f, 1.95f),
                LatexOrangeFoxEntity::createLatexAttributes);  // 使用父类默认属性

        LATEXFRUITDRAGONWOLF = registerSpawning("latex_fruit_dragon_wolf", 0x5AD700, 0x4EBA00,
                EntityType.Builder.<LatexFruitDragonEntity>of(LatexFruitDragonEntity::new, MobCategory.MONSTER)
                    .setShouldReceiveVelocityUpdates(true)
                    .setTrackingRange(64)
                        .setUpdateInterval(3)
                    .setCustomClientFactory(LatexFruitDragonEntity::new)
                    .sized(0.7f, 1.93f),
                LatexRadiationFoxEntity::createLatexAttributes);

        LATEXRADIATIONFOX = registerSpawning("latex_radiation_fox", 0x7CFC00, 0x32CD32,
                EntityType.Builder.<LatexRadiationFoxEntity>of(LatexRadiationFoxEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .setCustomClientFactory(LatexRadiationFoxEntity::new)
                        .sized(0.7f, 1.93f),
                LatexRadiationFoxEntity::createLatexAttributes);

        LATEXNEONDIMNESSWOLF = registerSpawning("latex_neondimness_wolf", 0x333333, 0x444444,
                EntityType.Builder.<LatexNeondimnessWolfEntity>of(LatexNeondimnessWolfEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .setCustomClientFactory(LatexNeondimnessWolfEntity::new)
                        .sized(0.7f, 1.93f),
                LatexNeondimnessWolfEntity::createLatexAttributes);

        // 鲨鱼变体
        LATEX_KCAHRA_SHARK = registerAquaticSpawning("latex_kcahra_shark", 0x222222, 0xffffff,
                EntityType.Builder.<LatexKcahraSharkEntity>of(LatexKcahraSharkEntity::new, MobCategory.WATER_CREATURE)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .setCustomClientFactory(LatexKcahraSharkEntity::new)
                        .sized(0.6f, 1.8f),
                        LatexKcahraSharkEntity::createLatexAttributes,
                2);

        // 狼变体
        DARKFU_LATEX_WOLF_MALE = registerSpawning("darkfu_latex_wolf_male", 0x4A4A4A, 0x2D2D2D,
                EntityType.Builder.<DarkfuLatexWolfMaleEntity>of(DarkfuLatexWolfMaleEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .setCustomClientFactory(DarkfuLatexWolfMaleEntity::new)
                        .sized(0.6f, 1.99f),
                DarkfuLatexWolfMaleEntity::createLatexAttributes);

        LATEXPURPLEWSWOLF = registerSpawning("latexpurplewswolf", 0x800080, 0x9932CC,
                EntityType.Builder.<LatexpurplewswolfEntity>of(LatexpurplewswolfEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .setCustomClientFactory(LatexpurplewswolfEntity::new)
                        .sized(0.6f, 1.9f),
                LatexpurplewswolfEntity::createLatexAttributes);

        LATEXTHREEMONTHWOLF = registerSpawning("latexthreemonthwolf", 0xFF62FF, 0xFF84FF,
                EntityType.Builder.<LatexthreemonthwolfEntity>of(LatexthreemonthwolfEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .setCustomClientFactory(LatexthreemonthwolfEntity::new)
                        .sized(0.9f, 2.15f),
                LatexthreemonthwolfEntity::createLatexAttributes);

        LATEXPRTPLECOOCWOLF = registerSpawning("latex_purple_cooc_wolf", 0x9370DB, 0xBA55D3,
                EntityType.Builder.<LatexPurplecoocwolfEntity>of(LatexPurplecoocwolfEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .setCustomClientFactory(LatexPurplecoocwolfEntity::new)
                        .sized(0.6f, 1.8f),
                LatexPurplecoocwolfEntity::createLatexAttributes);
        /*
        LATEX_ICE_FIELD_WOLF_DRAGON = registerSpawning("latex_ice_field_wolf_dragon", 0x87CEEB, 0x4682B4,
                EntityType.Builder.<LatexIceFieldWolfDragonEntity>of(LatexIceFieldWolfDragonEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .setCustomClientFactory(LatexIceFieldWolfDragonEntity::new)
                        .sized(0.7f, 1.93f),
                LatexIceFieldWolfDragonEntity::createLatexAttributes);
        */
        LATEX_DIVINE_SWORD_KITSUNE = registerSpawning("latex_divine_sword_kitsune", 0xFD1C0A, 0xFFFFFF,
                EntityType.Builder.<LatexDivineSwordKitsuneEntity>of(LatexDivineSwordKitsuneEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .setCustomClientFactory(LatexDivineSwordKitsuneEntity::new)
                        .sized(0.7f, 1.93f),
                LatexDivineSwordKitsuneEntity::createLatexAttributes);

        // 幼崽变体
        BLACKPUPMALE = registerSpawning("blackpupmale", 0x000000, 0x333333,
                EntityType.Builder.<BlackpupmaleEntity>of(BlackpupmaleEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .setCustomClientFactory(BlackpupmaleEntity::new)
                        .sized(0.4f, 0.7f),
                BlackpupmaleEntity::createLatexAttributes);

        WHITEBLUEPUP = registerSpawning("whitebluepup", 0xFFFFFF, 0x87CEEB,
                EntityType.Builder.<WhitebluepupEntity>of(WhitebluepupEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .setCustomClientFactory(WhitebluepupEntity::new)
                        .sized(0.6f, 0.6f),
                WhitebluepupEntity::createLatexAttributes);

        // 龙变体
        LATEXICE_DRAGON = registerSpawning("latex_ko_qi_ice_dragon", 0x00CED1, 0x48D1CC,
                EntityType.Builder.<LatexiceDragonEntity>of(LatexiceDragonEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .setCustomClientFactory(LatexiceDragonEntity::new)
                        .sized(0.6f, 1.8f),
                LatexiceDragonEntity::createLatexAttributes);

        LATEXYUNXQHOTDRAGON = registerSpawning("latexyunxqhotdragon", 0xFF4500, 0xFF6347,
                EntityType.Builder.<LatexyunxqhotdragonEntity>of(LatexyunxqhotdragonEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .setCustomClientFactory(LatexyunxqhotdragonEntity::new)
                        .fireImmune()
                        .sized(0.6f, 1.9f),
                LatexyunxqhotdragonEntity::createLatexAttributes);

        LATEXYAOLINGDRAGON = registerSpawning("latex_yao_ling_dragon", 0xFFDD4F,0xF9CACA,
                EntityType.Builder.<LatexYaoLingDragonEntity>of(LatexYaoLingDragonEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .setCustomClientFactory(LatexYaoLingDragonEntity::new)
                        .fireImmune()
                        .sized(0.6f, 1.9f),
                LatexyunxqhotdragonEntity::createLatexAttributes);

        LATEXYUNXQICEDRAGON = registerSpawning("latexyunxqicedragon", 0x87CEFA, 0xB0E2FF,
                EntityType.Builder.<LatexyunxqicedragonEntity>of(LatexyunxqicedragonEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .setCustomClientFactory(LatexyunxqicedragonEntity::new)
                        .sized(0.6f, 2f),
                LatexyunxqicedragonEntity::createLatexAttributes);

        LATEXBLUEDRAGON = registerSpawning("latexbluedragon", 0x1E90FF, 0x4169E1,
                EntityType.Builder.<LatexbluedragonEntity>of(LatexbluedragonEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .setCustomClientFactory(LatexbluedragonEntity::new)
                        .sized(0.6f, 1.8f),
                LatexbluedragonEntity::createLatexAttributes);

        LATEXYUNQIICEDRAGON = registerSpawning("latex_yun_qi_ice_dragon", 0x00BFFF, 0x87CEEB,
                EntityType.Builder.<LatexYunQiIceDragonEntity>of(LatexYunQiIceDragonEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .setCustomClientFactory(LatexYunQiIceDragonEntity::new)
                        .sized(0.7f, 1.93f),
                LatexYunQiIceDragonEntity::createLatexAttributes);

        LATEXDARKPURPLEDRAGONTAUR = registerSpawning("latex_dark_purple_dragon_taur", 0x4B0082, 0x800080,
                EntityType.Builder.<LatexDarkPurpleDragonTaurEntity>of(LatexDarkPurpleDragonTaurEntity::new, MobCategory.MONSTER)
                        .clientTrackingRange(10)
                        .sized(1.6f, 2.5f),
                LatexDarkPurpleDragonTaurEntity::createLatexAttributes);

        LATEXDARKPURPLEDRAGON = registerSpawning("latex_dark_purple_dragon", 0x483D8B, 0x6A5ACD,
                EntityType.Builder.<DarkPurpleLatexDragonEntity>of(DarkPurpleLatexDragonEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .setCustomClientFactory(DarkPurpleLatexDragonEntity::new)
                        .sized(0.7f, 1.92f),
                DarkPurpleLatexDragonEntity::createLatexAttributes);

        LATEXLUOHONGEARLYSPRINGFOXDRAGON = registerSpawning("latex_luo_hong_early_spring_fox_dragon", 0xADD8E6, 0x87CEEB,
                EntityType.Builder.<LatexLuoHongEarlySpringFoxDragonEntity>of(LatexLuoHongEarlySpringFoxDragonEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .setCustomClientFactory(LatexLuoHongEarlySpringFoxDragonEntity::new)
                        .fireImmune()
                        .sized(0.48f, 1.52f),
                LatexLuoHongEarlySpringFoxDragonEntity::createLatexAttributes);

        /*
        LATEX_ICE_FIELD_WOLF_DRAGON_BOSS = registerSpawning("latex_ice_field_wolf_dragon_boss", 0x4682B4, 0x5F9EA0,
                EntityType.Builder.<LatexIceFieldWolfDragonBossEntity>of(LatexIceFieldWolfDragonBossEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .fireImmune()
                        .setCustomClientFactory(LatexIceFieldWolfDragonBossEntity::new)
                        .sized(1.0f, 2.5f),
                () -> {
                    AttributeSupplier.Builder builder = ChangedEntity.createLatexAttributes();
                    builder.add(Attributes.MAX_HEALTH, 500);
                    builder.add(Attributes.ARMOR, 35);
                    builder.add(Attributes.ATTACK_DAMAGE, 6.0);
                    builder.add(Attributes.KNOCKBACK_RESISTANCE, 1.0);
                    return builder;
                });
        */

        LATEXNIGHTOWLDRAGONBOSS = registerSpawning("latex_night_owl_dragon_boss", 0x2C3E50, 0x34495E,
                EntityType.Builder.<LatexNightOwlBossEntity>of(LatexNightOwlBossEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .fireImmune()
                        .setCustomClientFactory(LatexNightOwlBossEntity::new)
                        .sized(0.9f, 2.2f),
                LatexNightOwlBossEntity::createLatexAttributes);

        LATEXNIGHTOWLDRAGON = registerSpawning("latex_night_owl_dragon", 0x4A6E8B, 0x5D7A9C,
                EntityType.Builder.<LatexNightOwlEntity>of(LatexNightOwlEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .fireImmune()
                        .setUpdateInterval(3)
                        .setCustomClientFactory(LatexNightOwlEntity::new)
                        .sized(0.7f, 1.92f),
                LatexNightOwlEntity::createLatexAttributes);

        LATEXIQGOLDDRAGON = registerSpawning("latex_iq_gold_dragon", 0xFFD700, 0xDAA520,
                EntityType.Builder.<LatexIqGoldDragonEntity>of(LatexIqGoldDragonEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .setCustomClientFactory(LatexIqGoldDragonEntity::new)
                        .sized(0.7f, 1.92f),
                LatexIqGoldDragonEntity::createLatexAttributes);



        // 其他变体
        INKSUGER = registerSpawning("inksuger", 0x000000, 0x333333,
                EntityType.Builder.<InksugerEntity>of(InksugerEntity::new, MobCategory.MONSTER)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(3)
                        .setCustomClientFactory(InksugerEntity::new)
                        .sized(0.6f, 0.6f),
                InksugerEntity::createLatexAttributes);

        // 抛射物不需要刷怪蛋和生成规则
        LATEXTHREEMONTHWOLF_PROJECTILE = registerProjectile("projectile_latexthreemonthwolf",
                EntityType.Builder.<LatexthreemonthwolfEntityProjectile>of(LatexthreemonthwolfEntityProjectile::new, MobCategory.MISC)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(1)
                        .setCustomClientFactory(LatexthreemonthwolfEntityProjectile::new)
                        .sized(0.5f, 0.5f));

        BLUECRYSTALPROJECTILE = registerProjectile("projectile_blue_crystal",
                EntityType.Builder.<BlueCrystalProjectile>of(BlueCrystalProjectile::new, MobCategory.MISC)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(1)
                        .setCustomClientFactory(BlueCrystalProjectile::new)
                        .sized(0.5f, 0.5f));

        CRYSTAL_ARROW = registerProjectile("projectile_crystal",
                EntityType.Builder.<CrystalArrow>of(CrystalArrow::new, MobCategory.MISC)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(1)
                        .sized(0.5f, 0.5f));

        THROWN_CRYSTAL_JAVELIN = registerProjectile("thrown_crystal_javelin_trident",
                EntityType.Builder.<ThrownCrystalJavelinTrident>of(ThrownCrystalJavelinTrident::new, MobCategory.MISC)
                        .setShouldReceiveVelocityUpdates(true)
                        .setTrackingRange(64)
                        .setUpdateInterval(1)
                        .sized(0.5f, 0.5f));  // 原版三叉戟碰撞箱大小

    }

    /**
     * 注册可生成实体（带刷怪蛋）
     */
    private static <T extends ChangedEntity> RegistryObject<EntityType<T>> registerSpawning(
            String name,
            int eggBack,
            int eggHighlight,
            EntityType.Builder<T> builder,
            Supplier<AttributeSupplier.Builder> attributes) {

        ResourceLocation resourceLocation = new ResourceLocation(ChangedCreature.MODID, name);
        ENTITY_COLOR_MAP.put(resourceLocation, new Pair<>(eggBack, eggHighlight));

        String regName = resourceLocation.toString();
        RegistryObject<EntityType<T>> entityType = REGISTRY.register(name, () -> builder.build(regName));

        // 存储属性构建器
        ATTR_FUNC_REGISTRY.add(new Pair<>(entityType::get, attributes));

        // 创建刷怪蛋 - 移除 .tab()
        String result = createSpawnEggPath(name);

        RegistryObject<ForgeSpawnEggItem> spawnEggItem = ITEMS.register(result,
                () -> new ForgeSpawnEggItem(entityType, eggBack, eggHighlight, new Item.Properties()));

        SPAWN_EGGS.put(entityType, spawnEggItem);

        INIT_FUNC_REGISTRY.add(() -> {
            SpawnPlacements.register(entityType.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    (entityType1, level, spawnType, pos, random) -> {
                        // 检查是否为自然生成
                        if (spawnType != MobSpawnType.NATURAL) {
                            return true;
                        }
                        // 检查亮度是否小于4
                        if (level instanceof Level serverLevel) {
                            int brightness = serverLevel.getMaxLocalRawBrightness(pos);
                            if (brightness > 3) {
								return false;
							}

                            // 降低生成在矿洞的可能性.(同时会减少刷怪塔刷出的概率)
                            if (!serverLevel.canSeeSky(pos)) {
                                if (random.nextFloat() > 0.7f) {
                                    return false;
                                }
                            }
                        }


                        return true;
                    });
        });

        return entityType;
    }

    // 在同一个类中添加事件监听器
    @SubscribeEvent
    public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
        // 检查是否是自定义的实体Tab
        if (tabData.getTabKey() == ChangedCreatureModTabs.GOODBLOCK_ENTITY.getKey()) {
            // 添加所有刷怪蛋，并检查是否存在
            SPAWN_EGGS.values().forEach(egg -> {
                try {
                    if (egg != null && egg.isPresent()) {
                        tabData.accept(egg.get());
                    }
                } catch (Exception e) {
                    //No do anythings
                }
            });
        }
    }

    private static <T extends ChangedEntity> RegistryObject<EntityType<T>> registerAquaticSpawning(
            String name,
            int eggBack,
            int eggHighlight,
            EntityType.Builder<T> builder,
            Supplier<AttributeSupplier.Builder> attributes,
            int requiredWaterHeight) {

        ResourceLocation resourceLocation = new ResourceLocation(ChangedCreature.MODID, name);
        ENTITY_COLOR_MAP.put(resourceLocation, new Pair<>(eggBack, eggHighlight));

        String regName = resourceLocation.toString();
        RegistryObject<EntityType<T>> entityType = REGISTRY.register(name, () -> builder.build(regName));

        // 存储属性构建器
        ATTR_FUNC_REGISTRY.add(new Pair<>(entityType::get, attributes));

        // 创建刷怪蛋
        String result = createSpawnEggPath(name);
        RegistryObject<ForgeSpawnEggItem> spawnEggItem = ITEMS.register(
                result,
                () -> new ForgeSpawnEggItem(entityType, eggBack, eggHighlight, new Item.Properties())
        );

        SPAWN_EGGS.put(entityType, spawnEggItem);

        INIT_FUNC_REGISTRY.add(() -> {
            SpawnPlacements.register(entityType.get(),
                    SpawnPlacements.Type.IN_WATER,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    (entityType1, level, spawnType, pos, random) -> {
                        // 检查是否为自然生成
                        if (spawnType != MobSpawnType.NATURAL) {
                            return true;
                        }

                        for (int i = 0; i < requiredWaterHeight; i++) {
                            if (!level.isWaterAt(pos.above(i))) {
                                return false;
                            }
                        }
                        // 检查水面上的空间
                        if (!level.getBlockState(pos.above(requiredWaterHeight)).isAir()) {
                            return false;
                        }
                        // 检查底部是否有固体方块
                        if (!level.getBlockState(pos.below()).isSolid()) {
                            return false;
                        }
                        return true;
                    });
        });

        return entityType;
    }

    /**
     * 注册抛射物（不带刷怪蛋）
     */
    private static <T extends Entity> RegistryObject<EntityType<T>> registerProjectile(
            String name,
            EntityType.Builder<T> builder) {

        String regName = new ResourceLocation(ChangedCreature.MODID, name).toString();
        return REGISTRY.register(name, () -> builder.build(regName));
    }

    private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
        return REGISTRY.register(registryname, () -> entityTypeBuilder.build(registryname));
    }

    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            // 执行所有初始化函数
            INIT_FUNC_REGISTRY.forEach(VoidConsumer::accept);
        });
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        // 使用存储的属性构建器注册所有实体
        ATTR_FUNC_REGISTRY.forEach((pair) ->
                event.put(pair.getFirst().get(), pair.getSecond().get().build())
        );
    }

    public static String createSpawnEggPath(String entityPath) {
        if (entityPath.contains("/")) {
            return entityPath.replace("/", "_spawn_egg_");
        }
        return entityPath + "_spawn_egg";
    }
}