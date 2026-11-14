
package net.hhdsj.changed_creatures.client.renderer.latex_fox;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.client.models.latex_fox.ModelLatexGaoHuiFox;
import net.hhdsj.changed_creatures.entity.simple.LatexGaoHuiFoxEntity;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;
import net.ltxprogrammer.changed.client.renderer.layers.*;
import net.ltxprogrammer.changed.client.renderer.model.armor.ArmorLatexMaleWolfModel;
import net.ltxprogrammer.changed.util.Color3;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class LatexGaoHuiFoxRenderer extends
		AdvancedHumanoidRenderer<LatexGaoHuiFoxEntity, ModelLatexGaoHuiFox> {
	public LatexGaoHuiFoxRenderer(EntityRendererProvider.Context context) {
	
		super(context, new ModelLatexGaoHuiFox(context.bakeLayer(ModelLatexGaoHuiFox.LAYER_LOCATION)),
            ArmorLatexMaleWolfModel.MODEL_SET,
            0.5f);	
		this.addLayer(new LatexParticlesLayer<>(this, this.getModel()));
        this.addLayer(TransfurCapeLayer.normalCape(this, context.getModelSet()));
		this.addLayer(new EmissiveBodyLayer<>(this,
				ChangedCreature.CCRL("textures/entities/latex/latex_gao_hui_fox_light.png")));
		this.addLayer(new CustomEyesLayer<>(
				this, context.getModelSet(),
				CustomEyesLayer::scleraColor,
				CustomEyesLayer.fixedColorGlowing(Color3.parseHex("#1a12a3")),
				CustomEyesLayer.fixedColorGlowing(Color3.parseHex("#1a12a3")),
				CustomEyesLayer::noRender, CustomEyesLayer::noRender));
		this.addLayer(GasMaskLayer.forSnouted(this, context.getModelSet()));
	}

	@Override
	public ResourceLocation getTextureLocation(LatexGaoHuiFoxEntity entity) {
		return ChangedCreature.CCRL("textures/entities/latex/latex_gao_hui_fox.png");
	}
}
