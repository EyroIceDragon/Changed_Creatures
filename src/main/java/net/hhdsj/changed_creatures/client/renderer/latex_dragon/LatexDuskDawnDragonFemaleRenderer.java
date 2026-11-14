package net.hhdsj.changed_creatures.client.renderer.latex_dragon;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.client.models.latex_dragon.ModelLatexDuskDawnDragonFemale;
import net.hhdsj.changed_creatures.entity.simple.LatexDuskDawnDragonFemaleEntity;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;
import net.ltxprogrammer.changed.client.renderer.layers.CustomEyesLayer;
import net.ltxprogrammer.changed.client.renderer.layers.GasMaskLayer;
import net.ltxprogrammer.changed.client.renderer.layers.LatexParticlesLayer;
import net.ltxprogrammer.changed.client.renderer.layers.TransfurCapeLayer;
import net.ltxprogrammer.changed.client.renderer.model.armor.ArmorLatexFemaleWingedDragonModel;
import net.ltxprogrammer.changed.util.Color3;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;


public class LatexDuskDawnDragonFemaleRenderer extends AdvancedHumanoidRenderer<LatexDuskDawnDragonFemaleEntity, ModelLatexDuskDawnDragonFemale> {

	ResourceLocation DrakTexture = ChangedCreature.CCRL("textures/entities/latex/latex_dusk_dawn_dragon_female_black.png");
	ResourceLocation WhiteTexture = ChangedCreature.CCRL("textures/entities/latex/latex_dusk_dawn_dragon_female_white.png");

	public LatexDuskDawnDragonFemaleRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelLatexDuskDawnDragonFemale(context.bakeLayer(ModelLatexDuskDawnDragonFemale.LAYER_LOCATION)),
				ArmorLatexFemaleWingedDragonModel.MODEL_SET,
				0.5f);
		this.addLayer(new LatexParticlesLayer<>(this, getModel()));
		this.addLayer(TransfurCapeLayer.normalCape(this, context.getModelSet()));
		this.addLayer(new CustomEyesLayer<>(
				this,
				context.getModelSet(),
				CustomEyesLayer::scleraColor,
				(entity, info) -> CustomEyesLayer.ColorData.ofEmissiveColor(Color3.parseHex(entity.eyes_color)),
				(entity, info) -> CustomEyesLayer.ColorData.ofEmissiveColor(Color3.parseHex(entity.eyes_color)),
				(entity, info) -> CustomEyesLayer.ColorData.ofTranslucentColor(Color3.parseHex(entity.eyes_color), 0.6f),
				(entity, info) -> CustomEyesLayer.ColorData.ofTranslucentColor(Color3.parseHex(entity.eyes_color),0.6f)));
		this.addLayer(GasMaskLayer.forSnouted(this, context.getModelSet()));
	}

	@Override
	public @NotNull ResourceLocation getTextureLocation(LatexDuskDawnDragonFemaleEntity entity) {
		Level level = entity.level();
		boolean isDaytime = (level.getDayTime() >= 1000 && level.getDayTime() <= 13000);
		if (!entity.can_changed_color) {
			if (Objects.equals(entity.eyes_color, "#FFFFFF")){
				return WhiteTexture;
			}else{
				return DrakTexture;
			}
		}

		if (!isDaytime) {
			entity.eyes_color = "#FFFFFF";
			return WhiteTexture;
		} else {
			entity.eyes_color = "#222222";
			return DrakTexture;
		}
	}
}