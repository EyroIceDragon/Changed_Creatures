
package net.hhdsj.changed_creatures.client.renderer.latex_dragon;


import net.hhdsj.changed_creatures.ChangedCreature;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.ltxprogrammer.changed.client.renderer.layers.CustomEyesLayer;
import net.ltxprogrammer.changed.client.renderer.layers.GasMaskLayer;
import net.ltxprogrammer.changed.client.renderer.layers.LatexParticlesLayer;
import net.ltxprogrammer.changed.client.renderer.layers.TransfurCapeLayer;
import net.ltxprogrammer.changed.client.renderer.model.armor.ArmorLatexMaleWingedDragonModel;
import net.ltxprogrammer.changed.util.Color3;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;

import net.hhdsj.changed_creatures.client.models.latex_dragon.ModelBlueDragon;
import net.hhdsj.changed_creatures.entity.simple.LatexbluedragonEntity;

public class LatexBlueDragonRenderer extends AdvancedHumanoidRenderer<LatexbluedragonEntity, ModelBlueDragon> {
	public LatexBlueDragonRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelBlueDragon(context.bakeLayer(ModelBlueDragon.LAYER_LOCATION)),
		ArmorLatexMaleWingedDragonModel.MODEL_SET, 
		0.5f);
        this.addLayer(new LatexParticlesLayer<>(this, getModel()));
        this.addLayer(TransfurCapeLayer.normalCape(this, context.getModelSet()));
        this.addLayer(CustomEyesLayer.builder(this, context.getModelSet())
                .withSclera(Color3.WHITE).withIris(Color3.fromInt(0x7889f3)).build());
        this.addLayer(GasMaskLayer.forSnouted(this, context.getModelSet()));
	}


	@Override
	public ResourceLocation getTextureLocation(LatexbluedragonEntity entity) {
		return ResourceLocation.fromNamespaceAndPath(ChangedCreature.MODID,"textures/entities/latex/latex_bluegreen_texture_new1.png");
	}
}
