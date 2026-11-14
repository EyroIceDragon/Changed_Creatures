
package net.hhdsj.changed_creatures.client.renderer.latex_fox;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.client.models.latex_fox.ModelLatexYueXiFoxFemale;
import net.hhdsj.changed_creatures.entity.simple.LatexYueXiFoxFemaleEntity;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;
import net.ltxprogrammer.changed.client.renderer.layers.CustomEyesLayer;
import net.ltxprogrammer.changed.client.renderer.layers.GasMaskLayer;
import net.ltxprogrammer.changed.client.renderer.layers.LatexParticlesLayer;
import net.ltxprogrammer.changed.client.renderer.layers.TransfurCapeLayer;
import net.ltxprogrammer.changed.client.renderer.model.armor.ArmorLatexMaleWolfModel;
import net.ltxprogrammer.changed.util.Color3;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class LatexYueXiFoxFemaleRenderer extends
		AdvancedHumanoidRenderer<LatexYueXiFoxFemaleEntity, ModelLatexYueXiFoxFemale> {
	public LatexYueXiFoxFemaleRenderer(EntityRendererProvider.Context context) {
	
		super(context, new ModelLatexYueXiFoxFemale(context.bakeLayer(ModelLatexYueXiFoxFemale.LAYER_LOCATION)),
            ArmorLatexMaleWolfModel.MODEL_SET,
            0.5f);	
		this.addLayer(new LatexParticlesLayer<>(this, this.getModel()));
        this.addLayer(TransfurCapeLayer.normalCape(this, context.getModelSet()));
		this.addLayer(new CustomEyesLayer<>(
				this, context.getModelSet(),
				CustomEyesLayer::scleraColor,
				CustomEyesLayer.fixedColorGlowing(Color3.parseHex("#efb501")),
				CustomEyesLayer.fixedColorGlowing(Color3.parseHex("#efb501")),
				CustomEyesLayer::noRender, CustomEyesLayer::noRender));
		this.addLayer(GasMaskLayer.forSnouted(this, context.getModelSet()));
	}

	@Override
	public ResourceLocation getTextureLocation(LatexYueXiFoxFemaleEntity entity) {
		return ChangedCreature.CCRL("textures/entities/latex/latex_yue_xi_fox_female.png");
	}
}
