
/*
 *    这些为注册类,请将注册写在这里,而不是其它地方!
 */
package net.hhdsj.changed_creatures.init;

import net.hhdsj.changed_creatures.block.CryoSubjectChamberRenderer;
import net.hhdsj.changed_creatures.client.renderer.ProtogenNyxarixRenderer;
import net.hhdsj.changed_creatures.client.renderer.block.LatexDeepCrystalDragonDollRenderer;
import net.hhdsj.changed_creatures.client.renderer.boss.LatexNightOwlBossRenderer;
import net.hhdsj.changed_creatures.client.renderer.boss.LatexNightOwlRenderer;
import net.hhdsj.changed_creatures.client.renderer.cat.LatexBlueveCatRenderer;
import net.hhdsj.changed_creatures.client.renderer.latex_dragon.*;
import net.hhdsj.changed_creatures.client.renderer.latex_fox.*;
import net.hhdsj.changed_creatures.client.renderer.latex_pup.BlackPupMaleRenderer;
import net.hhdsj.changed_creatures.client.renderer.latex_pup.InkSugerRenderer;
import net.hhdsj.changed_creatures.client.renderer.latex_pup.WhiteBluePupRenderer;
import net.hhdsj.changed_creatures.client.renderer.latex_shark.LatexEnkangDragonSharkRenderer;
import net.hhdsj.changed_creatures.client.renderer.latex_shark.LatexKcahraSharkRenderer;
import net.hhdsj.changed_creatures.client.renderer.latex_shark.LatexQianGuiSharkRenderer;
import net.hhdsj.changed_creatures.client.renderer.latex_taur.LatexDarkPurpleDragonTaurRenderer;
import net.hhdsj.changed_creatures.client.renderer.latex_taur.LatexFrostScaleDragonTaurRenderer;
import net.hhdsj.changed_creatures.client.renderer.latex_wolf.*;
import net.hhdsj.changed_creatures.client.renderer.latex_wolf_dragon.LatexXuanJinWolfDragonRenderer;
import net.hhdsj.changed_creatures.client.renderer.projectile.BlueCrystalProjectileRenderer;
import net.hhdsj.changed_creatures.client.renderer.projectile.CrystalArrowRenderer;
import net.hhdsj.changed_creatures.client.renderer.projectile.LatexArrowRenderer;
import net.hhdsj.changed_creatures.client.renderer.projectile.ThrownCrystalJavelinTridentRenderer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ChangedCreatureModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEX_ORANGE_FOX.get(), LatexOrangeFoxRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEX_KCAHRA_SHARK.get(), LatexKcahraSharkRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.DARKFU_LATEX_WOLF_MALE.get(), DarkfuLatexWolfMaleRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEXICE_DRAGON.get(), LatexIceDragonRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.INKSUGER.get(), InkSugerRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.BLACKPUPMALE.get(), BlackPupMaleRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.WHITEBLUEPUP.get(), WhiteBluePupRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEXPURPLEWSWOLF.get(), LatexpurplewswolfRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEXTHREEMONTHWOLF.get(), LatexthreemonthwolfRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEXYUNXQHOTDRAGON.get(), LatexYunXqHotDragonRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEXYUNXQICEDRAGON.get(), LatexYunXqIceDragonRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEXBLUEDRAGON.get(), LatexBlueDragonRenderer::new);
		//event.registerEntityRenderer(ChangedCreatureModEntities.LATEX_ICE_FIELD_WOLF_DRAGON.get(), LatexIceFieldWolfDragonRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEXPRTPLECOOCWOLF.get(), LatexPurpleCoocWolfRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEXTHREEMONTHWOLF_PROJECTILE.get(), LatexArrowRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEXYUNQIICEDRAGON.get(), LatexYunQiIceDragonRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEXRADIATIONFOX.get(), LatexRadiationFoxRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEXNIGHTOWLDRAGONBOSS.get(), LatexNightOwlBossRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEXNIGHTOWLDRAGON.get(), LatexNightOwlRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEXIQGOLDDRAGON.get(), LatexIqGoldDragonRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEXDARKPURPLEDRAGONTAUR.get(), LatexDarkPurpleDragonTaurRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEXDARKPURPLEDRAGON.get(), LatexDarkPurpleDragonRenderer::new);
		//event.registerEntityRenderer(ChangedCreatureModEntities.LATEX_ICE_FIELD_WOLF_DRAGON_BOSS.get(), LatexIceFieldWolfDragonBossRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEXLUOHONGEARLYSPRINGFOXDRAGON.get(), LatexLuoHongEarlySpringFoxDragonRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEXFRUITDRAGONWOLF.get(), LatexFruitDragonRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEXNEONDIMNESSWOLF.get(), LatexNeondimnessWolfRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEX_CHENG_XI.get(), LatexChengXiRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEXYAOLINGDRAGON.get(),LatexYaoLingDragonRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEX_DIVINE_SWORD_KITSUNE.get(), LatexDivineSwordKitsuneRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.BLUECRYSTALPROJECTILE.get(), BlueCrystalProjectileRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.CRYSTAL_ARROW.get(), CrystalArrowRenderer::new);

		event.registerEntityRenderer(ChangedCreatureModEntities.THROWN_CRYSTAL_JAVELIN.get(), ThrownCrystalJavelinTridentRenderer::new);

		event.registerEntityRenderer(ChangedCreatureModEntities.LATEX_YUE_XI_FOX_FEMALE.get(), LatexYueXiFoxFemaleRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEX_GAO_HUI_FOX.get(), LatexGaoHuiFoxRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEX_DAWN_WOLF.get(), LatexDawnWolfRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEX_ENKANG_DRAGON_SHARK.get(), LatexEnkangDragonSharkRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEX_HYAZINTHE_FOX.get(), LatexHyazintheFoxRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEX_BLUEVE_CAT.get(), LatexBlueveCatRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEX_QIAN_GUI_SHARK.get(), LatexQianGuiSharkRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEX_FLORA_FOX.get(), LatexFloraFoxRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.LUOLONG_DRAGON_SHARK.get(), LatexLuoLongDragonSharkRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEX_DUSK_DAWN_DRAGON.get(), LatexDuskDawnDragonRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEX_DUSK_DAWN_DRAGON_FEMALE.get(), LatexDuskDawnDragonFemaleRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEX_CRYSTAL_JELLY.get(), LatexCrystalJellyDragonRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEX_FROST_SCALE_DRAGON_TAUR.get(), LatexFrostScaleDragonTaurRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEX_EMBERSCALES_MAGMA_DRAGON_MALE.get(), LatexEmberscalesMagmaDragonMaleRenderer::new);
		//ModelLatexEtanKind
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEX_ETAN_KIND.get(), LatexEtanKingRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.PROTOGEN_NYXARIX.get(), ProtogenNyxarixRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEX_STEPPEN_WOLF.get(), LatexSteppenWolfRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEX_PEAUNT_BUTTER_WOLF.get(), LatexPeanutButterWolfRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEX_XUAN_JIN_WOLF_DRAGON.get(), LatexXuanJinWolfDragonRenderer::new);
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEX_XUAN_JIN_DRAGON.get(), LatexXuanJinDragonRenderer::new);
		//LatexEyroEndDragonRenderer
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEX_EYRO_END_DRAGON.get(), LatexEyroEndDragonRenderer::new);
		//LatexIngerteFoxRenderer
		event.registerEntityRenderer(ChangedCreatureModEntities.LATEX_INGERTE_FOX.get(), LatexIngerteFoxRenderer::new);

	}
	@SubscribeEvent
	public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(ChangedCreatureBlockEntities.CRYO_SUBJECT_CHAMBER_BE.get(), CryoSubjectChamberRenderer::new);
		event.registerBlockEntityRenderer(ChangedCreatureBlockEntities.LATEX_DEEP_CRYSTAL_DRAGON_DOLL_BE.get(), LatexDeepCrystalDragonDollRenderer::new);
	}
}
