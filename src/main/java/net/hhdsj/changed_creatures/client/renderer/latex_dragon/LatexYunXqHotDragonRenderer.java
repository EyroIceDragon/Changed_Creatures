package net.hhdsj.changed_creatures.client.renderer.latex_dragon;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.hhdsj.changed_creatures.entity.simple.LatexyunxqhotdragonEntity;
import net.hhdsj.changed_creatures.client.models.latex_dragon.ModelLatexYunXqHotDragon;

import net.ltxprogrammer.changed.client.renderer.layers.CustomEyesLayer;
import net.ltxprogrammer.changed.client.renderer.layers.GasMaskLayer;
import net.ltxprogrammer.changed.client.renderer.layers.LatexParticlesLayer;
import net.ltxprogrammer.changed.client.renderer.layers.TransfurCapeLayer;
import net.ltxprogrammer.changed.client.renderer.model.armor.ArmorLatexMaleWingedDragonModel;
import net.ltxprogrammer.changed.util.Color3;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;
import net.ltxprogrammer.changed.client.renderer.layers.*;
import org.jetbrains.annotations.NotNull;

public class LatexYunXqHotDragonRenderer extends AdvancedHumanoidRenderer<LatexyunxqhotdragonEntity, ModelLatexYunXqHotDragon> {
	public LatexYunXqHotDragonRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelLatexYunXqHotDragon(context.bakeLayer(ModelLatexYunXqHotDragon.LAYER_LOCATION)),
				ArmorLatexMaleWingedDragonModel.MODEL_SET,
				0.5f);
		this.addLayer(new EmissiveBodyLayer(this, ChangedCreature.CCRL("textures/entities/latex/latex_yunxq_hot_dragon_light.png")));
		this.addLayer(new LatexParticlesLayer<>(this, getModel()));
        this.addLayer(TransfurCapeLayer.normalCape(this, context.getModelSet()));
		this.addLayer(new CustomEyesLayer<>(this, context.getModelSet(), CustomEyesLayer::scleraColor, CustomEyesLayer.fixedColorGlowing(Color3.parseHex("#ff2700")), CustomEyesLayer.fixedColorGlowing(Color3.parseHex("#ff2700")), CustomEyesLayer::noRender, CustomEyesLayer::noRender));
		this.addLayer(GasMaskLayer.forSnouted(this, context.getModelSet()));
		
	}

	@Override
	public @NotNull ResourceLocation getTextureLocation(LatexyunxqhotdragonEntity entity) {
		return ChangedCreature.CCRL("textures/entities/latex/latex_yunxq_hot_dragon.png");
	}
}
//latex_yunqi_ice_dragon_light
