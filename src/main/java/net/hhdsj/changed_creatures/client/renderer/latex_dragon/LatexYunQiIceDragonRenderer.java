
package net.hhdsj.changed_creatures.client.renderer.latex_dragon;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.client.models.latex_dragon.ModelLatexYunQiIceDragon;
import net.hhdsj.changed_creatures.entity.simple.LatexYunQiIceDragonEntity;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;
import net.ltxprogrammer.changed.client.renderer.layers.CustomEyesLayer;
import net.ltxprogrammer.changed.client.renderer.layers.GasMaskLayer;
import net.ltxprogrammer.changed.client.renderer.layers.LatexParticlesLayer;
import net.ltxprogrammer.changed.client.renderer.layers.TransfurCapeLayer;
import net.ltxprogrammer.changed.client.renderer.model.armor.ArmorLatexMaleWingedDragonModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

public class LatexYunQiIceDragonRenderer extends AdvancedHumanoidRenderer<LatexYunQiIceDragonEntity, ModelLatexYunQiIceDragon> {
	public LatexYunQiIceDragonRenderer(EntityRendererProvider.Context context) {
		
		super(context, new ModelLatexYunQiIceDragon(context.bakeLayer(ModelLatexYunQiIceDragon.LAYER_LOCATION)),
		ArmorLatexMaleWingedDragonModel.MODEL_SET, 
		0.5f);
		this.addLayer(new EyesLayer<LatexYunQiIceDragonEntity, ModelLatexYunQiIceDragon>(this) {
			@Override
			public RenderType renderType() {
				return RenderType.eyes(ChangedCreature.CCRL("textures/entities/latex/latex_yunqi_ice_dragon_light.png"));
			}
		});
        this.addLayer(new LatexParticlesLayer<>(this, getModel()));
        this.addLayer(TransfurCapeLayer.normalCape(this, context.getModelSet()));
        this.addLayer(new CustomEyesLayer<>(this, context.getModelSet()));
		this.addLayer(new CustomEyesLayer<>(this, context.getModelSet(), CustomEyesLayer::scleraColor, CustomEyesLayer::glowingIrisColorLeft, CustomEyesLayer::glowingIrisColorRight, CustomEyesLayer::noRender, CustomEyesLayer::noRender));
		this.addLayer(GasMaskLayer.forSnouted(this, context.getModelSet()));
	}

	@Override
	public ResourceLocation getTextureLocation(LatexYunQiIceDragonEntity entity) {
		return ChangedCreature.CCRL("textures/entities/latex/latex_yunqi_ice_dragon.png");
	}
}
