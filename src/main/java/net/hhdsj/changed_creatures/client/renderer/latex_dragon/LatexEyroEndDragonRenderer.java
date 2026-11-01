
package net.hhdsj.changed_creatures.client.renderer.latex_dragon;

import net.hhdsj.changed_creatures.client.models.armors.changed.ArmorLatexMaleWolfWingedDragonModel;
import net.hhdsj.changed_creatures.client.models.latex_dragon.ModelLatexEyroEndDragon;
import net.hhdsj.changed_creatures.entity.simple.LatexEyroEndDragonEntity;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;
import net.ltxprogrammer.changed.client.renderer.layers.*;
import net.ltxprogrammer.changed.util.Color3;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class LatexEyroEndDragonRenderer extends AdvancedHumanoidRenderer<LatexEyroEndDragonEntity, ModelLatexEyroEndDragon> {
	public LatexEyroEndDragonRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelLatexEyroEndDragon(context.bakeLayer(ModelLatexEyroEndDragon.LAYER_LOCATION)),
			ArmorLatexMaleWolfWingedDragonModel.MODEL_SET,
			0.5f);
		this.addLayer(new EmissiveBodyLayer<>(this,
				new ResourceLocation("changed_creatures:textures/entities/latex/latex_eyro_end_dragon_light.png")));
		this.addLayer(new LatexParticlesLayer<>(this, this.model));
		this.addLayer(TransfurCapeLayer.normalCape(this, context.getModelSet()));
		this.addLayer(new CustomEyesLayer<>(this, context.getModelSet(), CustomEyesLayer::scleraColor, CustomEyesLayer.fixedColorGlowing(Color3.parseHex("#00ffff")), CustomEyesLayer.fixedColorGlowing(Color3.parseHex("#00ffff")), CustomEyesLayer::noRender, CustomEyesLayer::noRender));
		this.addLayer(GasMaskLayer.forSnouted(this, context.getModelSet()));
	}

	@Override
	public ResourceLocation getTextureLocation(LatexEyroEndDragonEntity entity) {
		return new ResourceLocation("changed_creatures:textures/entities/latex/latex_eyro_end_dragon.png");
	}
}
