/*
 *    这些为注册类,请将注册写在这里,而不是其它地方!
 */
package net.hhdsj.changed_creatures.init;
//原版我的世界导入
import net.foxyas.changedaddon.init.ChangedAddonAbilities;
import net.hhdsj.changed_creatures.entity.boss.LatexNightOwlEntity;
import net.hhdsj.changed_creatures.entity.simple.*;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.monster.*;
//FORGE导入
import net.minecraft.world.entity.npc.Villager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
//changed-mc mod导入
import net.ltxprogrammer.changed.entity.*;
import net.ltxprogrammer.changed.init.*;
import net.ltxprogrammer.changed.entity.variant.*;

//import net.foxyas.changedaddon.init.ChangedAddonAbilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public class ChangedCreatureModTransfurVariants {
    public static final DeferredRegister<TransfurVariant<?>> REGISTRY = ChangedRegistry.TRANSFUR_VARIANT.createDeferred("changed_creatures");
    //LATEX_INGERTE_FOX
    public static final RegistryObject<TransfurVariant<LatexIngerteFoxEntity>> LATEX_INGERTE_FOX =
            REGISTRY.register("form_latex_ingerte_wolf", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEX_INGERTE_FOX)
                    .addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
                    .scares(List.of(Creeper.class, Villager.class))
                    .build());

	public static final RegistryObject<TransfurVariant<LatexEyroEndDragonEntity>> LATEX_EYRO_END_DRAGON =
			REGISTRY.register("form_latex_eyro_end_dragon", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEX_EYRO_END_DRAGON)
					.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
					.glide()
					.scares(List.of(Creeper.class, Villager.class, Chicken.class))
					.build());

	public static final RegistryObject<TransfurVariant<LatexXuanJinDragonEntity>> LATEX_XUAN_JIN_DRAGON =
			REGISTRY.register("form_latex_xuan_jin_dragon", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEX_XUAN_JIN_DRAGON)
					.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
					.glide()
					.scares(List.of(Creeper.class, Villager.class))
					.build());
	//LATEX_XUAN_JIN_WOLF_DRAGON
	public static final RegistryObject<TransfurVariant<LatexXuanJinWolfDragonEntity>> LATEX_XUAN_JIN_WOLF_DRAGON =
			REGISTRY.register("form_latex_xuan_jin_wolf_dragon", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEX_XUAN_JIN_WOLF_DRAGON)
					.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
					.scares(List.of(Creeper.class, Villager.class))
					.build());
	//LATEX_PEAUNT_BUTTER_WOLF
	public static final RegistryObject<TransfurVariant<LatexPeanutButterWolfEntity>> LATEX_PEAUNT_BUTTER_WOLF =
			REGISTRY.register("form_latex_peaunt_butter_wolf", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEX_PEAUNT_BUTTER_WOLF)
					.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
					.scares(List.of(Creeper.class, Villager.class))
					.build());

	//LATEX_STEPPEN_WOLF
	public static final RegistryObject<TransfurVariant<LatexSteppenWolfEntity>> LATEX_STEPPEN_WOLF =
			REGISTRY.register("form_latex_steppen_wolf", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEX_STEPPEN_WOLF)
					.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
					.scares(List.of(Creeper.class, Zoglin.class, ZombieVillager.class, EnderMan.class))
					.build());

	//PROTOGEN_NYXARIX
	public static final RegistryObject<TransfurVariant<ProtogenNyxarixEntity>> PROTOGEN_NYXARIX =
			REGISTRY.register("form_protogen_nyxarix", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.PROTOGEN_NYXARIX)
					.gills()
					.extraHands() // The creature has two (pairs of) arms.
					.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
					.addAbility(ChangedCreatureModAbilities.BERRER_HYPNOSIE)
					.addAbility(ChangedCreatureModAbilities.LATEX_CONFUSED_ABILITY)
					.scares(List.of(Creeper.class, Zoglin.class, ZombieVillager.class, EnderMan.class))
					.build());

	//LATEX_CRYSTAL_JELLY
	public static final RegistryObject<TransfurVariant<LatexFrostScaleDragonTaurEntity>> LATEX_FROST_SCALE_DRAGON_TAUR =
			REGISTRY.register("form_latex_frost_scale_dragon_taur", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEX_FROST_SCALE_DRAGON_TAUR)
					.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
					.addAbility(ChangedCreatureModAbilities.LATEX_CONFUSED_ABILITY)
					.addAbility(ChangedCreatureModAbilities.LATEX_FROST_SPIKE_ABLILTY)
					.scares(List.of(Creeper.class, Zoglin.class, ZombieVillager.class, EnderMan.class, Villager.class))
					.build());

	public static final RegistryObject<TransfurVariant<LatexCrystalJellyDragonEntity>> LATEX_CRYSTAL_JELLY =
			REGISTRY.register("form_latex_crystal_jelly_dragon", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEX_CRYSTAL_JELLY)
					.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
					.build());

	public static final RegistryObject<TransfurVariant<LatexEmberscalesMagmaDragonMaleEntity>> LATEX_EMBERSCALES_MAGMA_DRAGON_MALE =
			REGISTRY.register("form_latex_emberscales_magma_dragon_male", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEX_EMBERSCALES_MAGMA_DRAGON_MALE)
					.glide()
					.transfurMode(TransfurMode.ABSORPTION)
					.addAbility(ChangedAddonAbilities.CLAWS)
					.addAbility(ChangedCreatureModAbilities.LATEX_CONFUSED_ABILITY)
					.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
					.addAbility(ChangedAddonAbilities.CARRY)
					.addAbility(ChangedAbilities.GRAB_ENTITY_ABILITY)
					.build());

	public static final RegistryObject<TransfurVariant<LatexDuskDawnDragonEntity>> LATEX_DUSK_DAWN_DRAGON =
			REGISTRY.register("form_latex_dusk_dawn_dragon", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEX_DUSK_DAWN_DRAGON)
                    .glide()
					.transfurMode(TransfurMode.ABSORPTION)
					.addAbility(ChangedAddonAbilities.CLAWS)
					.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
					.addAbility(ChangedAddonAbilities.CARRY)
					.addAbility(ChangedAbilities.GRAB_ENTITY_ABILITY)
					.addAbility(ChangedCreatureModAbilities.DUSK_DAWN_CHANGED_ABILITY)
					.build());

	public static final RegistryObject<TransfurVariant<LatexDuskDawnDragonFemaleEntity>> LATEX_DUSK_DAWN_DRAGON_FEMALE =
			REGISTRY.register("form_latex_dusk_dawn_dragon/female", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEX_DUSK_DAWN_DRAGON_FEMALE)
                    .glide()
					.transfurMode(TransfurMode.ABSORPTION)
					.addAbility(ChangedAddonAbilities.CLAWS)
					.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
					.addAbility(ChangedAddonAbilities.CARRY)
					.addAbility(ChangedAbilities.GRAB_ENTITY_ABILITY)
					.addAbility(ChangedCreatureModAbilities.DUSK_DAWN_CHANGED_ABILITY)
					.build());

	public static final RegistryObject<TransfurVariant<LatexEtanKindEntity>> LATEX_ETAN_KIND =
			REGISTRY.register("form_latex_etan_kind", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEX_ETAN_KIND)
					.nightVision()
					.transfurMode(TransfurMode.ABSORPTION)
					.addAbility(ChangedAddonAbilities.CLAWS)
					.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
					.addAbility(ChangedAddonAbilities.CARRY)
					.addAbility(ChangedAbilities.GRAB_ENTITY_ABILITY)
					.extraJumps(2)
					.build());

    public static final RegistryObject<TransfurVariant<LatexOrangeFoxEntity>> LATEX_ORANGE_FOX = 
			REGISTRY.register("form_latex_orange_fox", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEX_ORANGE_FOX)
				.nightVision()
				.transfurMode(TransfurMode.ABSORPTION)
				.addAbility(ChangedAddonAbilities.CLAWS)
				.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
				.build());

	public static final RegistryObject<TransfurVariant<LatexKcahraSharkEntity>> LATEX_KCAHRA_SHARK = 
			REGISTRY.register("form_latex_kcahra_shark", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEX_KCAHRA_SHARK)
				.gills()
				.breatheMode(TransfurVariant.BreatheMode.ANY)
				.transfurMode(TransfurMode.ABSORPTION)
				.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
				.replicating()
				.build());

	public static final RegistryObject<TransfurVariant<LatexbluedragonEntity>> LATEX_BLUE_DRAGON =
			REGISTRY.register("form_blue_dragon", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEXBLUEDRAGON)
				.glide()
				.transfurMode(TransfurMode.ABSORPTION)
				.replicating()
				.nightVision()
				.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
				.build());

	public static final RegistryObject<TransfurVariant<DarkfuLatexWolfMaleEntity>> LATEX_DARKFU_LATEX_WOLF_MALE =
			REGISTRY.register("form_darkfu_latex_wolf_male", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.DARKFU_LATEX_WOLF_MALE)
				//.stepSize(0.7f)
				//.faction(ChangedLatexTypes.DARK_LATEX)
				.breatheMode(TransfurVariant.BreatheMode.NORMAL)
				.transfurMode(TransfurMode.ABSORPTION)
				.replicating()
				.build());

	public static final RegistryObject<TransfurVariant<LatexiceDragonEntity>> LATEX_ICE_DRAGON =
			REGISTRY.register("form_latex_ice_dragon", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEXICE_DRAGON)
				//.stepSize(0.7f)
				.nightVision()
				.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
				.replicating()
				.build());

	public static final RegistryObject<TransfurVariant<InksugerEntity>> INKSUGER = 
			REGISTRY.register("form_ink_suger", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.INKSUGER)
				//.stepSize(0.7f)
				.nightVision()
				.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
				.transfurMode(TransfurMode.ABSORPTION)
				.holdItemsInMouth()
				//.reducedFall()
				.build());
	/*
	public static final RegistryObject<TransfurVariant<LatexIceFieldWolfDragonEntity>> LATEX_KAMONA_HSGUY_DRAGON_WOLF = 
			REGISTRY.register("form_latex_ice_field_wolf_dragon", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEX_ICE_FIELD_WOLF_DRAGON)
				.addAbility(ChangedAddonAbilities.DODGE)
				.nightVision()
				.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
				.addAbility(ChangedAddonAbilities.CLAWS)
				.transfurMode(TransfurMode.ABSORPTION)
				.replicating()
				.build());
	*/
	public static final RegistryObject<TransfurVariant<BlackpupmaleEntity>> BLACKPUPMALE = 
			REGISTRY.register("form_black_pup", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.BLACKPUPMALE)
				//.stepSize(0.7f)
				.nightVision()
				.transfurMode(TransfurMode.NONE)
				.holdItemsInMouth()
				//.reducedFall()
				.build());

	public static final RegistryObject<TransfurVariant<WhitebluepupEntity>> WHITEBLUEPUP = 
			REGISTRY.register("form_white_blue_pup", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.WHITEBLUEPUP)
				//.stepSize(0.7f)
				.nightVision()
				.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
				.holdItemsInMouth()
				//.reducedFall()
				.build());

	public static final RegistryObject<TransfurVariant<LatexpurplewswolfEntity>> LATEXPURPLEWSWOLF = 
			REGISTRY.register("form_latex_purplews_wolf", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEXPURPLEWSWOLF)
				.addAbility(ChangedAddonAbilities.CLAWS)
				.scares(Creeper.class)
				.nightVision()
				.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
				.extraJumps(2)
				.build());
	
	public static final RegistryObject<TransfurVariant<LatexthreemonthwolfEntity>> LATEXTHREEMONTHWOLF = 
			REGISTRY.register("form_latex_three_month_wolf", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEXTHREEMONTHWOLF)
				.addAbility(ChangedAddonAbilities.CLAWS)
				.scares(Creeper.class)
				.nightVision()
				.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
				.extraJumps(2)
				.build());

	public static final RegistryObject<TransfurVariant<LatexyunxqhotdragonEntity>> LATEXYUNXQHOTDRAGON = 
			REGISTRY.register("form_latex_yunxq_hot_dragon", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEXYUNXQHOTDRAGON)
				//.stepSize(0.7f)
				.glide()
				.scares(List.of(Creeper.class))
				.addAbility(entityType -> ChangedAddonAbilities.WING_FLAP_ABILITY.get())
				.transfurMode(TransfurMode.ABSORPTION)
				.replicating()
				.nightVision()
				.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
				.build());

	//LATEXFRUITDRAGONWOLF
	public static final RegistryObject<TransfurVariant<LatexFruitDragonEntity>> LATEX_FRUIT_DRAGON_WOLF =
			REGISTRY.register("form_latex_fruit_dragon_wolf", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEXFRUITDRAGONWOLF)
					.scares(List.of(Creeper.class))
					.transfurMode(TransfurMode.ABSORPTION)
					.replicating()
					.nightVision()
					.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
					.build());

	public static final RegistryObject<TransfurVariant<LatexYaoLingDragonEntity>> LATEXYAOLINGDRAGON =
			REGISTRY.register("form_latex_yao_ling_dragon", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEXYAOLINGDRAGON)
					//.stepSize(0.7f)
					.glide()
					.scares(List.of(Creeper.class))
					.addAbility(entityType -> ChangedAddonAbilities.WING_FLAP_ABILITY.get())
					.replicating()
					.nightVision()
					.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
					.build());

	public static final RegistryObject<TransfurVariant<LatexyunxqicedragonEntity>> LATEXYUNXQICEDRAGON = 
			REGISTRY.register("form_latex_yun_xq_ice_dragon", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEXYUNXQICEDRAGON)
			//.stepSize(0.7f)
				.glide()
				.addAbility(entityType -> ChangedAddonAbilities.WING_FLAP_ABILITY.get())
				.transfurMode(TransfurMode.ABSORPTION)
				.replicating()
				.nightVision()
				.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
				.build());

	public static final RegistryObject<TransfurVariant<LatexPurplecoocwolfEntity>> LATEXPRTPLECOOCWOLF = 
			REGISTRY.register("form_latex_purple_cooc_wolf", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEXPRTPLECOOCWOLF)
				//.stepSize(0.7f)
				.transfurMode(TransfurMode.ABSORPTION)
				.replicating()
				.nightVision()
				.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
				.build());

	public static final RegistryObject<TransfurVariant<LatexYunQiIceDragonEntity>> LATEXYUNQIICEDRAGON =
			REGISTRY.register("form_latex_yun_qi_ice_dragon", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEXYUNQIICEDRAGON)
					//.stepSize(0.7f)
					.addAbility(entityType -> ChangedAddonAbilities.WING_FLAP_ABILITY.get())
					.glide()
					.extraJumps(2)
					.transfurMode(TransfurMode.ABSORPTION)
					.replicating()
					.nightVision()
					.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
					.build());

	public static final RegistryObject<TransfurVariant<LatexRadiationFoxEntity>> LATEXRADIATIONFOX =
			REGISTRY.register("form_latex_radiation_fox", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEXRADIATIONFOX)
					//.stepSize(0.7f)
					.transfurMode(TransfurMode.ABSORPTION)
					.replicating()
					.nightVision()
					.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
					.build());

	public static final RegistryObject<TransfurVariant<LatexNightOwlEntity>> LATEXNIGHTOWL =
			REGISTRY.register("form_latex_night_owl_dragon", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEXNIGHTOWLDRAGON)
					//.stepSize(0.7f)
					.addAbility(ChangedAddonAbilities.DODGE)
					.addAbility(entityType -> ChangedAddonAbilities.WING_FLAP_ABILITY.get())
					.addAbility(ChangedAddonAbilities.CLAWS)
					.scares(List.of(Creeper.class))
					.glide()
					.transfurMode(TransfurMode.ABSORPTION)
					.addAbility(ChangedCreatureModAbilities.LATEX_CONFUSED_ABILITY)
					.replicating()
					.nightVision()
					.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
					.extraJumps(2)
					.build());

	public static final RegistryObject<TransfurVariant<LatexLuoHongEarlySpringFoxDragonEntity>> LATEXLUOHONGEARLYSPRINGFOXDRAGON =
			REGISTRY.register("form_latex_luo_hong_early_spring_fox_dragon", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEXLUOHONGEARLYSPRINGFOXDRAGON)
					//.stepSize(0.7f)
					.addAbility(ChangedAddonAbilities.DODGE)
					.addAbility(entityType -> ChangedAddonAbilities.WING_FLAP_ABILITY.get())
					.addAbility(ChangedAddonAbilities.CLAWS)
					.scares(List.of(Creeper.class))
					.glide()
					.transfurMode(TransfurMode.ABSORPTION)
					.replicating()
					.nightVision()
					.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
					.extraJumps(2)
					.build());

    public static final RegistryObject<TransfurVariant<LatexIqGoldDragonEntity>> LATEXIQGOLDDRAGON =
            REGISTRY.register("form_latex_iq_gold_dragon", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEXIQGOLDDRAGON)
                    //.stepSize(0.7f)
                    .addAbility(entityType -> ChangedAddonAbilities.WING_FLAP_ABILITY.get())
                    .addAbility(ChangedAddonAbilities.CLAWS)
                    .scares(List.of(Creeper.class))
                    .glide()
                    .transfurMode(TransfurMode.ABSORPTION)
                    .replicating()
                    .nightVision()
                    .addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
                    .extraJumps(1)
                    .build());

	public static final RegistryObject<TransfurVariant<LatexDarkPurpleDragonTaurEntity>> LATEXDARKPURPLEDRAGONTAUR =
			REGISTRY.register("form_latex_dark_purple_dragon_taur", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEXDARKPURPLEDRAGONTAUR)
					.addAbility(entityType -> ChangedCreatureModAbilities.TURN_FERA_LATEX_DARK_PURPLE.get())
					.addAbility(ChangedCreatureModAbilities.BERRER_HYPNOSIE)
					.scares(List.of(Creeper.class))
					.replicating()
					.nightVision()
					.quadrupedal()
					.cameraZOffset(7.0f / 16.0f)
					.extraJumps(2)
					.rideable()
					.breatheMode(TransfurVariant.BreatheMode.ANY)
					//.reducedFall()
					.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
					.build());

	public static final RegistryObject<TransfurVariant<DarkPurpleLatexDragonEntity>> LATEXDARKPURPLEDRAGON =
			REGISTRY.register("form_latex_dark_purple_dragon", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEXDARKPURPLEDRAGON)
					//.stepSize(0.7f)
					.addAbility(entityType -> ChangedAddonAbilities.WING_FLAP_ABILITY.get())
					.addAbility(ChangedCreatureModAbilities.BERRER_HYPNOSIE)
					.addAbility(entityType -> ChangedCreatureModAbilities.TURN_FERA_LATEX_DARK_PURPLE.get())
					.scares(List.of(Creeper.class))
					.glide()
					.transfurMode(TransfurMode.ABSORPTION)
					.replicating()
					.nightVision()
					.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
					.extraJumps(2)
					.build());

	public static final RegistryObject<TransfurVariant<LatexNeondimnessWolfEntity>> LATEXNEONDIMNESSWOLF =
			REGISTRY.register("form_latex_neondimness_wolf", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEXNEONDIMNESSWOLF)
					.scares(List.of(Creeper.class))
					.transfurMode(TransfurMode.ABSORPTION)
					.replicating()
					.nightVision()
					.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
					.addAbility(ChangedAddonAbilities.TELEPORT)
					.addAbility(ChangedAddonAbilities.CARRY)
					.addAbility(ChangedAbilities.GRAB_ENTITY_ABILITY)
					.addAbility(ChangedAddonAbilities.DODGE)
					.addAbility(ChangedAddonAbilities.CUSTOM_INTERACTION)
					.build());

	public static final RegistryObject<TransfurVariant<LatexChengXiEntity>> LATEX_CHENG_XI =
			REGISTRY.register("form_latex_cheng_xi_fox", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEX_CHENG_XI)
					.scares(List.of(Creeper.class))
					.transfurMode(TransfurMode.ABSORPTION)
					.replicating()
					.nightVision()
					.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
					.extraJumps(2)
					.build());
	//

	public static final RegistryObject<TransfurVariant<LatexDivineSwordKitsuneEntity>> LATEX_DIVINE_SWORD_KITSUNE =
			REGISTRY.register("form_latex_divine_sword_kitsune", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEX_DIVINE_SWORD_KITSUNE)
					.addAbility(ChangedAddonAbilities.CLAWS)
					.addAbility(ChangedAddonAbilities.DODGE)
					.addAbility(ChangedAbilities.GRAB_ENTITY_ABILITY)
					.scares(List.of(Creeper.class))
					.glide()
					.transfurMode(TransfurMode.ABSORPTION)
					.replicating()
					.nightVision()
					.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
					.build());

	// 高辉狐 - 基础狐狸型
	public static final RegistryObject<TransfurVariant<LatexGaoHuiFoxEntity>> LATEX_GAO_HUI_FOX =
			REGISTRY.register("form_latex_gao_hui_fox", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEX_GAO_HUI_FOX)
					.addAbility(ChangedAddonAbilities.CLAWS)
					.addAbility(ChangedAddonAbilities.DODGE)
					.transfurMode(TransfurMode.ABSORPTION)
					.replicating()
					.nightVision()
					.glide()
					.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
					.scares(List.of(Creeper.class))
					.build());

	// 风信子狐 - 优雅型
	public static final RegistryObject<TransfurVariant<LatexHyazintheFoxEntity>> LATEX_HYAZINTHE_FOX =
			REGISTRY.register("form_latex_hyazinthe_fox", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEX_HYAZINTHE_FOX)
					.addAbility(ChangedAddonAbilities.CLAWS)
					.addAbility(ChangedCreatureModAbilities.CREATEBLUECRYSTALENTITYABILITY)
					.addAbility(ChangedAddonAbilities.DODGE)
					.transfurMode(TransfurMode.ABSORPTION)
					.replicating()
					.nightVision()
					.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
					.scares(List.of(Creeper.class))
					.build());

	// 月夕雌狐 - 雌性狐狸型
	public static final RegistryObject<TransfurVariant<LatexYueXiFoxFemaleEntity>> LATEX_YUE_XI_FOX_FEMALE =
			REGISTRY.register("form_latex_yue_xi_fox_female", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEX_YUE_XI_FOX_FEMALE)
					.addAbility(ChangedAddonAbilities.CLAWS)
					.addAbility(ChangedAddonAbilities.DODGE)
					.transfurMode(TransfurMode.ABSORPTION)
					.replicating()
					.nightVision()
					.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
					.scares(List.of(Creeper.class))
					.build());

	// 恩康龙鲨 - 水生龙鲨型
	public static final RegistryObject<TransfurVariant<LatexEnkangDragonSharkEntity>> LATEX_ENKANG_DRAGON_SHARK =
			REGISTRY.register("form_latex_enkang_dragon_shark", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEX_ENKANG_DRAGON_SHARK)
					.addAbility(ChangedAddonAbilities.DODGE)
					.addAbility(ChangedAbilities.GRAB_ENTITY_ABILITY)
					.gills()
					.breatheMode(TransfurVariant.BreatheMode.ANY)
					.transfurMode(TransfurMode.ABSORPTION)
					.replicating()
					.nightVision()
					.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
					.build());

	// 千龟鲨 - 水生鲨鱼型
	public static final RegistryObject<TransfurVariant<LatexQianGuiSharkEntity>> LATEX_QIAN_GUI_SHARK =
			REGISTRY.register("form_latex_qian_gui_shark", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEX_QIAN_GUI_SHARK)
					.addAbility(ChangedAddonAbilities.CLAWS)
					.addAbility(ChangedAddonAbilities.DODGE)
					.addAbility(ChangedAbilities.GRAB_ENTITY_ABILITY)
					.gills()
					.breatheMode(TransfurVariant.BreatheMode.ANY)
					.transfurMode(TransfurMode.ABSORPTION)
					.replicating()
					.nightVision()
					.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
					.build());


	// 黎明狼 - 基础狼型
	public static final RegistryObject<TransfurVariant<LatexDawnWolfEntity>> LATEX_DAWN_WOLF =
			REGISTRY.register("form_latex_dawn_wolf", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEX_DAWN_WOLF)
					.addAbility(ChangedAddonAbilities.CLAWS)
					.addAbility(ChangedAddonAbilities.DODGE)
					.addAbility(ChangedAbilities.GRAB_ENTITY_ABILITY)
					.transfurMode(TransfurMode.ABSORPTION)
					.replicating()
					.nightVision()
					.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
					.scares(List.of(Creeper.class))
					.breatheMode(TransfurVariant.BreatheMode.ANY)
					.build());

	// 蓝脉猫 - 猫型
	public static final RegistryObject<TransfurVariant<LatexBlueveCatEntity>> LATEX_BLUEVE_CAT =
			REGISTRY.register("form_latex_blueve_cat", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEX_BLUEVE_CAT)
					.addAbility(ChangedAddonAbilities.CLAWS)
					.addAbility(ChangedAddonAbilities.DODGE)
					.addAbility(ChangedAbilities.GRAB_ENTITY_ABILITY)
					.transfurMode(TransfurMode.ABSORPTION)
					.replicating()
					.nightVision()
					.canClimb()
					.addAbility(ChangedAddonAbilities.TOGGLE_CLIMB)
					.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
					.scares(List.of(Creeper.class))
					.build());

	public static final RegistryObject<TransfurVariant<LatexFloraFoxEntity>> LATEX_FLORA_FOX =
			REGISTRY.register("form_latex_flora_fox/female", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LATEX_FLORA_FOX)
					.scares(List.of(Creeper.class))
					.addAbility(ChangedAbilities.GRAB_ENTITY_ABILITY)
					.addAbility(ChangedAddonAbilities.SOFTEN_ABILITY)
					.addAbility(ChangedAddonAbilities.DODGE)
					.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
					.addAbility(ChangedAddonAbilities.TELEPORT)
					.build());

	public static final RegistryObject<TransfurVariant<LatexLuoLongDragonSharkEntity>> LATEX_LUOLONG_DRAGON_SHARK =
			REGISTRY.register("form_latex_luolong_dragon_shark", () -> TransfurVariant.Builder.of(ChangedCreatureModEntities.LUOLONG_DRAGON_SHARK)
					.scares(List.of(Creeper.class))
					.addAbility(ChangedAbilities.GRAB_ENTITY_ABILITY)
					.addAbility(ChangedAddonAbilities.SOFTEN_ABILITY)
					.addAbility(ChangedAddonAbilities.DODGE)
					.addAbility(ChangedAbilities.TOGGLE_NIGHT_VISION)
					.addAbility(ChangedAddonAbilities.TELEPORT)
					.addAbility(ChangedCreatureModAbilities.SHOOT_CRYSTAL_ARROW)
					.breatheMode(TransfurVariant.BreatheMode.ANY)
					.glide()
					.build());

	public static void register(IEventBus bus) {
        REGISTRY.register(bus);
    }

	public static class Gendered {
		private static final List<GenderedPair<?, ?>> PAIRS = new ArrayList<>();
		public static final GenderedPair<LatexDuskDawnDragonEntity, LatexDuskDawnDragonFemaleEntity> LATEX_DUSK_DAWN_DRAGON;

		public Gendered() {
		}

		public static <M extends ChangedEntity, F extends ChangedEntity> GenderedPair<M, F> registerPair(Supplier<? extends TransfurVariant<M>> maleVariant, Supplier<? extends TransfurVariant<F>> femaleVariant) {
			GenderedPair<M, F> pair = new GenderedPair<>(maleVariant, femaleVariant);
			PAIRS.add(pair);
			return pair;
		}

		public static Stream<GenderedPair<?, ?>> getPairs() {
			return PAIRS.stream();
		}

		public static Optional<Object> getOpposite(TransfurVariant<?> variant) {
			return getPairs().mapMulti((pair, consumer) -> {
				if (pair.getMaleVariant() == variant) {
					consumer.accept(pair.getFemaleVariant());
				} else if (pair.getFemaleVariant() == variant) {
					consumer.accept(pair.getMaleVariant());
				}

			}).findAny();
		}

		public static boolean hasOpposite(TransfurVariant<?> variant) {
			return getPairs().anyMatch((pair) -> pair.contains(variant));
		}

		static {
			LATEX_DUSK_DAWN_DRAGON = registerPair(ChangedCreatureModTransfurVariants.LATEX_DUSK_DAWN_DRAGON, ChangedCreatureModTransfurVariants.LATEX_DUSK_DAWN_DRAGON_FEMALE);
		}
	}

}












