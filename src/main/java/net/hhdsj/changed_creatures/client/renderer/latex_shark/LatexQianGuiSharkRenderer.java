
package net.hhdsj.changed_creatures.client.renderer.latex_shark;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.client.models.latex_shark.ModelLatexQianGuiShark;
import net.hhdsj.changed_creatures.entity.simple.LatexQianGuiSharkEntity;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;
import net.ltxprogrammer.changed.client.renderer.layers.*;
import net.ltxprogrammer.changed.client.renderer.model.armor.ArmorLatexMaleSharkModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class LatexQianGuiSharkRenderer extends AdvancedHumanoidRenderer<LatexQianGuiSharkEntity, ModelLatexQianGuiShark>{
	public LatexQianGuiSharkRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelLatexQianGuiShark(context.bakeLayer(ModelLatexQianGuiShark.LAYER_LOCATION)),
			//ArmorLatexMaleSharkModel::new, 
            ArmorLatexMaleSharkModel.MODEL_SET,
            0.5f);
		this.addLayer(new LatexParticlesLayer<>(this, this.getModel()));
        this.addLayer(TransfurCapeLayer.normalCape(this, context.getModelSet()));
		this.addLayer(new EmissiveBodyLayer<>(this, ChangedCreature.CCRL("textures/entities/latex/latex_qian_gui_shark_light.png")));
		this.addLayer(new CustomEyesLayer<>(this,
				context.getModelSet(), CustomEyesLayer::scleraColor,
				CustomEyesLayer::glowingIrisColorLeft,
				CustomEyesLayer::glowingIrisColorRight,
				CustomEyesLayer::noRender,
				CustomEyesLayer::noRender));
		this.addLayer(GasMaskLayer.forSnouted(this, context.getModelSet()));
	}

	@Override
	public ResourceLocation getTextureLocation(LatexQianGuiSharkEntity entity) {
		return ChangedCreature.CCRL("textures/entities/latex/latex_qian_gui_shark.png");
	}
}
