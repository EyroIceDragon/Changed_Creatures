
/*
 *    这些为注册类,请将注册写在这里,而不是其它地方!
 */
package net.hhdsj.changed_creatures.init;

import net.hhdsj.changed_creatures.client.models.ModelProtogenNyxarix;
import net.hhdsj.changed_creatures.client.models.armors.ArmorLatexMaleWolfWingedDragonModel;
import net.hhdsj.changed_creatures.client.models.armors.ArmorProtogenNyxarixWaterSquidDogModel;
import net.hhdsj.changed_creatures.client.models.latex_taur.ModelLatexFrostScaleDragonTaur;
import net.hhdsj.changed_creatures.client.models.latex_wolf_dragon.ModelLatexXuanJinWolfDragon;
import net.hhdsj.changed_creatures.client.models.other.ModelLatexWing;
import net.hhdsj.changed_creatures.client.models.other.TailModel;
import net.hhdsj.changed_creatures.client.models.boss.ModelLatexNightOwl;
import net.hhdsj.changed_creatures.client.models.boss.ModelLatexNightOwlBoss;
import net.hhdsj.changed_creatures.client.models.cat.ModelLatexBlueveCat;
import net.hhdsj.changed_creatures.client.models.latex_dragon.*;
import net.hhdsj.changed_creatures.client.models.latex_fox.*;
import net.hhdsj.changed_creatures.client.models.latex_pup.ModelInksuger;
import net.hhdsj.changed_creatures.client.models.latex_pup.ModelBlackPupMale;
import net.hhdsj.changed_creatures.client.models.latex_pup.ModelWhiteBluePup;
import net.hhdsj.changed_creatures.client.models.latex_shark.ModelLatexEnkangDragonShark;
import net.hhdsj.changed_creatures.client.models.latex_shark.ModelLatexKcahraShark;
import net.hhdsj.changed_creatures.client.models.latex_shark.ModelLatexQianGuiShark;
import net.hhdsj.changed_creatures.client.models.latex_taur.ModelLatexDarkPurpleDragonTaur;
import net.hhdsj.changed_creatures.client.models.latex_wolf.*;
import net.hhdsj.changed_creatures.client.models.other.CrystalJavelinModel;
import net.hhdsj.changed_creatures.client.models.armors.GoodBlockModelMaleWingedDragonArmor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class ChangedCreatureModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ModelLatexOrangeFox.LAYER_LOCATION, ModelLatexOrangeFox::createBodyLayer);
		event.registerLayerDefinition(ModelBlueDragon.LAYER_LOCATION, ModelBlueDragon::createBodyLayer);
		event.registerLayerDefinition(ModelLatexKcahraShark.LAYER_LOCATION, ModelLatexKcahraShark::createBodyLayer);
		event.registerLayerDefinition(ModelLatexIceDragon.LAYER_LOCATION, ModelLatexIceDragon::createBodyLayer);
		event.registerLayerDefinition(ModelDarkfuLatexWolfMale.LAYER_LOCATION, ModelDarkfuLatexWolfMale::createBodyLayer);
		event.registerLayerDefinition(ModelInksuger.LAYER_LOCATION, ModelInksuger::createBodyLayer);
		//event.registerLayerDefinition(ModelIceFieldWolfDragon.LAYER_LOCATION, ModelIceFieldWolfDragon::createBodyLayer);
		event.registerLayerDefinition(ModelWhiteBluePup.LAYER_LOCATION, ModelWhiteBluePup::createBodyLayer);
		event.registerLayerDefinition(ModelBlackPupMale.LAYER_LOCATION, ModelBlackPupMale::createBodyLayer);
		event.registerLayerDefinition(ModelLatexPurpleWsWolf.LAYER_LOCATION, ModelLatexPurpleWsWolf::createBodyLayer);
		event.registerLayerDefinition(ModelLatexYunXqHotDragon.LAYER_LOCATION, ModelLatexYunXqHotDragon::createBodyLayer);
		event.registerLayerDefinition(ModelLatexThreeMonthWolf.LAYER_LOCATION, ModelLatexThreeMonthWolf::createBodyLayer);
		event.registerLayerDefinition(ModelLatexYunQiIceDragon.LAYER_LOCATION, ModelLatexYunQiIceDragon::createBodyLayer);
		event.registerLayerDefinition(ModelLatexBlueIceDragon.LAYER_LOCATION, ModelLatexBlueIceDragon::createBodyLayer);
		event.registerLayerDefinition(ModelLatexRadiationFox.LAYER_LOCATION, ModelLatexRadiationFox::createBodyLayer);
		event.registerLayerDefinition(ModelLatexPurpleCoocWolf.LAYER_LOCATION, ModelLatexPurpleCoocWolf::createBodyLayer);
		event.registerLayerDefinition(ModelLatexNightOwlBoss.LAYER_LOCATION, ModelLatexNightOwlBoss::createBodyLayer);
		event.registerLayerDefinition(ModelLatexNightOwl.LAYER_LOCATION, ModelLatexNightOwl::createBodyLayer);
		event.registerLayerDefinition(ModelLatexIqGoldDragon.LAYER_LOCATION, ModelLatexIqGoldDragon::createBodyLayer);
		event.registerLayerDefinition(ModelLatexDarkPurpleDragonTaur.LAYER_LOCATION, ModelLatexDarkPurpleDragonTaur::createBodyLayer);
		event.registerLayerDefinition(ModelDarkPurpleLatexDragon.LAYER_LOCATION, ModelDarkPurpleLatexDragon::createBodyLayer);
		//event.registerLayerDefinition(ModelLatexIceFieldWolfDragonBoss.LAYER_LOCATION, ModelLatexIceFieldWolfDragonBoss::createBodyLayer);
		event.registerLayerDefinition(ModelLuoHongEarlySpringFoxDragon.LAYER_LOCATION, ModelLuoHongEarlySpringFoxDragon::createBodyLayer);
		event.registerLayerDefinition(ModelLatexFruitDragonWolf.LAYER_LOCATION, ModelLatexFruitDragonWolf::createBodyLayer);
		event.registerLayerDefinition(ModelNeondimnessWolf.LAYER_LOCATION, ModelNeondimnessWolf::createBodyLayer);
		event.registerLayerDefinition(ModelLatexChengXi.LAYER_LOCATION, ModelLatexChengXi::createBodyLayer);
		event.registerLayerDefinition(ModelLatexYaoLingDragon.LAYER_LOCATION, ModelLatexYaoLingDragon::createBodyLayer);
		event.registerLayerDefinition(ModelLatexDivineSwordKitsune.LAYER_LOCATION, ModelLatexDivineSwordKitsune::createBodyLayer);

		event.registerLayerDefinition(ModelLatexYueXiFoxFemale.LAYER_LOCATION, ModelLatexYueXiFoxFemale::createBodyLayer);
		event.registerLayerDefinition(ModelLatexGaoHuiFox.LAYER_LOCATION, ModelLatexGaoHuiFox::createBodyLayer);
		event.registerLayerDefinition(ModelLatexQianGuiShark.LAYER_LOCATION, ModelLatexQianGuiShark::createBodyLayer);
		event.registerLayerDefinition(ModelLatexHyazintheFox.LAYER_LOCATION, ModelLatexHyazintheFox::createBodyLayer);
		event.registerLayerDefinition(ModelLatexEnkangDragonShark.LAYER_LOCATION, ModelLatexEnkangDragonShark::createBodyLayer);
		event.registerLayerDefinition(ModelLatexDawnWolf.LAYER_LOCATION, ModelLatexDawnWolf::createBodyLayer);
		event.registerLayerDefinition(ModelLatexBlueveCat.LAYER_LOCATION, ModelLatexBlueveCat::createBodyLayer);
		event.registerLayerDefinition(ModelLatexFloraFox.LAYER_LOCATION, ModelLatexFloraFox::createBodyLayer);
		event.registerLayerDefinition(ModelLatexDuskDawnDragon.LAYER_LOCATION, ModelLatexDuskDawnDragon::createBodyLayer);
		event.registerLayerDefinition(ModelLatexDuskDawnDragonFemale.LAYER_LOCATION, ModelLatexDuskDawnDragonFemale::createBodyLayer);
		event.registerLayerDefinition(ModelLatexCrystalJellyDragon.LAYER_LOCATION, ModelLatexCrystalJellyDragon::createBodyLayer);
		event.registerLayerDefinition(ModelLatexFrostScaleDragonTaur.LAYER_LOCATION, ModelLatexFrostScaleDragonTaur::createBodyLayer);
		event.registerLayerDefinition(ModelLatexEmberscalesMagmaDragonMale.LAYER_LOCATION, ModelLatexEmberscalesMagmaDragonMale::createBodyLayer);
		event.registerLayerDefinition(ModelLatexLuoLongDragonShark.LAYER_LOCATION, ModelLatexLuoLongDragonShark::createBodyLayer);
		event.registerLayerDefinition(ModelProtogenNyxarix.LAYER_LOCATION, ModelProtogenNyxarix::createBodyLayer);
		event.registerLayerDefinition(ModelLatexEtanKind.LAYER_LOCATION, ModelLatexEtanKind::createBodyLayer);
		event.registerLayerDefinition(ModelLatexSteppenWolf.LAYER_LOCATION, ModelLatexSteppenWolf::createBodyLayer);
		event.registerLayerDefinition(ModelLatexXuanJinWolfDragon.LAYER_LOCATION, ModelLatexXuanJinWolfDragon::createBodyLayer);
		event.registerLayerDefinition(ModelLatexXuanJinDragon.LAYER_LOCATION, ModelLatexXuanJinDragon::createBodyLayer);
		event.registerLayerDefinition(ModelLatexEyroEndDragon.LAYER_LOCATION, ModelLatexEyroEndDragon::createBodyLayer);

		event.registerLayerDefinition(ModelLatexWing.LAYER_LOCATION, ModelLatexWing::createBodyLayer);
		event.registerLayerDefinition(CrystalJavelinModel.LAYER_LOCATION, CrystalJavelinModel::createBodyLayer);

		event.registerLayerDefinition(GoodBlockModelMaleWingedDragonArmor.LAYER_LOCATION, GoodBlockModelMaleWingedDragonArmor::createBodyLayer);
		ArmorLatexMaleWolfWingedDragonModel.MODEL_SET.registerDefinitions(event::registerLayerDefinition);
		ArmorProtogenNyxarixWaterSquidDogModel.MODEL_SET.registerDefinitions(event::registerLayerDefinition);

		event.registerLayerDefinition(ChangedCreatureModModelLayers.TAIL_LAYER, TailModel::createBodyLayer);

		event.registerLayerDefinition(ModelLatexPeanutButterWolf.LAYER_LOCATION, ModelLatexPeanutButterWolf::createBodyLayer);
		event.registerLayerDefinition(ModelLatexIngerteFox.LAYER_LOCATION, ModelLatexIngerteFox::createBodyLayer);
	}

}
