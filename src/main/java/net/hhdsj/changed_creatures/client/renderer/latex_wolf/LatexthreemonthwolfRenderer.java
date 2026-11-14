
package net.hhdsj.changed_creatures.client.renderer.latex_wolf;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.client.models.latex_wolf.ModelLatexThreeMonthWolf;
import net.hhdsj.changed_creatures.entity.simple.LatexthreemonthwolfEntity;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;
import net.ltxprogrammer.changed.client.renderer.layers.CustomEyesLayer;
import net.ltxprogrammer.changed.client.renderer.layers.GasMaskLayer;
import net.ltxprogrammer.changed.client.renderer.layers.LatexParticlesLayer;
import net.ltxprogrammer.changed.client.renderer.layers.TransfurCapeLayer;
import net.ltxprogrammer.changed.client.renderer.model.armor.ArmorLatexMaleCatModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;


public class LatexthreemonthwolfRenderer extends AdvancedHumanoidRenderer<LatexthreemonthwolfEntity, ModelLatexThreeMonthWolf> {

	public LatexthreemonthwolfRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelLatexThreeMonthWolf(context.bakeLayer(ModelLatexThreeMonthWolf.LAYER_LOCATION)),
		ArmorLatexMaleCatModel.MODEL_SET, 0.5f);
        this.addLayer(new LatexParticlesLayer<>(this, getModel()));
        this.addLayer(TransfurCapeLayer.normalCape(this, context.getModelSet()));
		this.addLayer(new CustomEyesLayer<>(this, context.getModelSet(), CustomEyesLayer::scleraColor, CustomEyesLayer::glowingIrisColorLeft, CustomEyesLayer::glowingIrisColorRight, CustomEyesLayer::noRender, CustomEyesLayer::noRender));
		this.addLayer(GasMaskLayer.forSnouted(this, context.getModelSet()));
	}

	@Override
	public ResourceLocation getTextureLocation(LatexthreemonthwolfEntity entity) {
		return ChangedCreature.CCRL("textures/entities/latex/latex_purple_ws_wolf1.png");
	}

	@Override
	protected boolean isShaking(LatexthreemonthwolfEntity _ent) {
        return 6 > _ent.getHealth();
	}
}
