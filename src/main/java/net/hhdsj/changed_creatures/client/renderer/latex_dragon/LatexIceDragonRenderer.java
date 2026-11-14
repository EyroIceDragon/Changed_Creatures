
package net.hhdsj.changed_creatures.client.renderer.latex_dragon;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.client.models.latex_dragon.ModelLatexIceDragon;
import net.hhdsj.changed_creatures.entity.simple.LatexiceDragonEntity;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;
import net.ltxprogrammer.changed.client.renderer.layers.*;
import net.ltxprogrammer.changed.client.renderer.model.armor.ArmorLatexBigTailDragonModel;
import net.ltxprogrammer.changed.util.Color3;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class LatexIceDragonRenderer extends AdvancedHumanoidRenderer<LatexiceDragonEntity, ModelLatexIceDragon> {
	public LatexIceDragonRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelLatexIceDragon(context.bakeLayer(ModelLatexIceDragon.LAYER_LOCATION)),
			//ArmorLatexBigTailDragonModel::new,
			ArmorLatexBigTailDragonModel.MODEL_SET,
			0.5f);
		this.addLayer(new EmissiveBodyLayer<>(this, ChangedCreature.CCRL("textures/entities/latex/latex_ice_dragon_light.png")));
		this.addLayer(new LatexParticlesLayer<>(this, this.model));
		this.addLayer(TransfurCapeLayer.normalCape(this, context.getModelSet()));
		this.addLayer(CustomEyesLayer.builder(this, context.getModelSet())
				.withSclera(Color3.WHITE).withIris(Color3.fromInt(0x7889f3)).build());
		this.addLayer(GasMaskLayer.forSnouted(this, context.getModelSet()));
	}

	@Override
	public ResourceLocation getTextureLocation(LatexiceDragonEntity entity) {
		return ChangedCreature.CCRL("textures/entities/latex/latex_ice_dragon.png");
	}
}
