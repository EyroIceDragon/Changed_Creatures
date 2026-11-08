
package net.hhdsj.changed_creatures.client.renderer.latex_shark;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.ltxprogrammer.changed.client.renderer.layers.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.ltxprogrammer.changed.client.renderer.model.armor.ArmorLatexMaleSharkModel;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;

import net.hhdsj.changed_creatures.entity.simple.LatexKcahraSharkEntity;
import net.hhdsj.changed_creatures.client.models.latex_shark.ModelLatexKcahraShark;

//public class LatexKcahraSharkRenderer extends MobRenderer<LatexKcahraSharkEntity, ModelLatexKcahraShark> {
public class LatexKcahraSharkRenderer extends AdvancedHumanoidRenderer<LatexKcahraSharkEntity, ModelLatexKcahraShark>{
	public LatexKcahraSharkRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelLatexKcahraShark(context.bakeLayer(ModelLatexKcahraShark.LAYER_LOCATION)),
			//ArmorLatexMaleSharkModel::new, 
            ArmorLatexMaleSharkModel.MODEL_SET,
            0.5f);
		this.addLayer(new EmissiveBodyLayer<>(this, ChangedCreature.CCRL("textures/entities/latex/latex_kcahra_shark_light.png")));
		this.addLayer(new LatexParticlesLayer<>(this, this.getModel()));
        this.addLayer(TransfurCapeLayer.normalCape(this, context.getModelSet()));
		this.addLayer(new CustomEyesLayer<>(this, context.getModelSet(), CustomEyesLayer::scleraColor, CustomEyesLayer::glowingIrisColorLeft, CustomEyesLayer::glowingIrisColorRight, CustomEyesLayer::noRender, CustomEyesLayer::noRender));
		this.addLayer(GasMaskLayer.forSnouted(this, context.getModelSet()));
	}

	@Override
	public ResourceLocation getTextureLocation(LatexKcahraSharkEntity entity) {
		return new ResourceLocation("changed_creatures:textures/entities/latex/latex_kcahra_shark.png");
	}
}
