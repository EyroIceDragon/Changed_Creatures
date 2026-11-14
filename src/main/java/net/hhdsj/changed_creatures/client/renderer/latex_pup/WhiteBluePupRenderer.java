package net.hhdsj.changed_creatures.client.renderer.latex_pup;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.client.models.latex_pup.ModelWhiteBluePup;
import net.hhdsj.changed_creatures.entity.simple.WhitebluepupEntity;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;
import net.ltxprogrammer.changed.client.renderer.layers.CustomEyesLayer;
import net.ltxprogrammer.changed.client.renderer.layers.LatexParticlesLayer;
import net.ltxprogrammer.changed.client.renderer.model.armor.ArmorNoneModel;
import net.ltxprogrammer.changed.util.Color3;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class WhiteBluePupRenderer extends AdvancedHumanoidRenderer<WhitebluepupEntity, ModelWhiteBluePup> {
	public WhiteBluePupRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelWhiteBluePup(context.bakeLayer(ModelWhiteBluePup.LAYER_LOCATION)),
				ArmorNoneModel.MODEL_SET, 
				0.4F);
		this.addLayer(new LatexParticlesLayer<>(this, this.model));
		this.addLayer(CustomEyesLayer.builder(this, context.getModelSet())
				.withSclera(Color3.fromInt(0x242424))
				.withIris(CustomEyesLayer.fixedIfNotDarkLatexOverrideLeft(Color3.WHITE),
						CustomEyesLayer.fixedIfNotDarkLatexOverrideRight(Color3.WHITE))
				.build().setHeadShape(CustomEyesLayer.HeadShape.PUP_CONCAVE));
	}


	@Override
	public ResourceLocation getTextureLocation(WhitebluepupEntity entity) {
		return ChangedCreature.CCRL("textures/entities/latex/wihteblue_latex_wolf_pup.png");
	}
}
