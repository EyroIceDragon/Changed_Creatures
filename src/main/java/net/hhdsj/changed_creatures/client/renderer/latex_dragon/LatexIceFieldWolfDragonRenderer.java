
package net.hhdsj.changed_creatures.client.renderer.latex_dragon;

import net.ltxprogrammer.changed.client.renderer.layers.*;
import net.ltxprogrammer.changed.util.Color3;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.hhdsj.changed_creatures.entity.simple.LatexIceFieldWolfDragonEntity;
import net.hhdsj.changed_creatures.client.models.latex_dragon.ModelIceFieldWolfDragon;

import net.ltxprogrammer.changed.client.renderer.model.armor.ArmorLatexBigTailDragonModel;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;

public class LatexIceFieldWolfDragonRenderer extends AdvancedHumanoidRenderer<LatexIceFieldWolfDragonEntity, ModelIceFieldWolfDragon> {
	public LatexIceFieldWolfDragonRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelIceFieldWolfDragon(context.bakeLayer(ModelIceFieldWolfDragon.LAYER_LOCATION)), 
		ArmorLatexBigTailDragonModel.MODEL_SET,
		0.5f);
		this.addLayer(new LatexParticlesLayer<>(this, this.model));
		this.addLayer(TransfurCapeLayer.normalCape(this, context.getModelSet()));
		//this.addLayer(new CustomEyesLayer<>(this, context.getModelSet(), CustomEyesLayer::scleraColor, CustomEyesLayer::glowingIrisColorLeft, CustomEyesLayer::glowingIrisColorRight, CustomEyesLayer::noRender, CustomEyesLayer::noRender));
		this.addLayer(new EmissiveBodyLayer<>(this, new ResourceLocation("changed_creatures", "textures/entities/latex/latex_kamona_hsguy_dragonwolf_png_light.png")));
		this.addLayer(new CustomEyesLayer<>(this, context.getModelSet(), CustomEyesLayer::scleraColor,
				CustomEyesLayer.fixedColorGlowing(Color3.parseHex("#006400")), CustomEyesLayer.fixedColorGlowing(Color3.parseHex("#006400")),
				CustomEyesLayer::noRender, CustomEyesLayer::noRender));
		this.addLayer(GasMaskLayer.forSnouted(this, context.getModelSet()));
	}

	@Override
	public ResourceLocation getTextureLocation(LatexIceFieldWolfDragonEntity entity) {
		return new ResourceLocation("changed_creatures:textures/entities/latex/latex_kamona_hsguy_dragonwolf_png1.png");
	}
}
