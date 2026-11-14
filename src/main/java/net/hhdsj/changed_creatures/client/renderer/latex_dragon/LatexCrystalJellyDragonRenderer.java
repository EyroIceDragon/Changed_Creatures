
package net.hhdsj.changed_creatures.client.renderer.latex_dragon;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.client.models.latex_dragon.ModelLatexCrystalJellyDragon;
import net.hhdsj.changed_creatures.entity.simple.LatexCrystalJellyDragonEntity;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;
import net.ltxprogrammer.changed.client.renderer.layers.*;
import net.ltxprogrammer.changed.client.renderer.model.armor.ArmorLatexBigTailDragonModel;
import net.ltxprogrammer.changed.util.Color3;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class LatexCrystalJellyDragonRenderer extends AdvancedHumanoidRenderer<LatexCrystalJellyDragonEntity, ModelLatexCrystalJellyDragon> {

	ResourceLocation TextureEntity = ChangedCreature.CCRL("textures/entities/latex/latex_crystal_jelly_dragon.png");
	ResourceLocation TextureLightEntity = ChangedCreature.CCRL("textures/entities/latex/latex_crystal_jelly_dragon_light.png");

	public LatexCrystalJellyDragonRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelLatexCrystalJellyDragon(context.bakeLayer(ModelLatexCrystalJellyDragon.LAYER_LOCATION)), 
		ArmorLatexBigTailDragonModel.MODEL_SET,
		0.5f);
		this.addLayer(new LatexParticlesLayer<>(this, this.model));
		this.addLayer(TransfurCapeLayer.normalCape(this, context.getModelSet()));
		this.addLayer(new EmissiveBodyLayer<>(this, TextureLightEntity));
		this.addLayer(new CustomEyesLayer<>(this, context.getModelSet(), CustomEyesLayer::scleraColor,
				CustomEyesLayer.fixedColorGlowing(Color3.parseHex("#49daff")), CustomEyesLayer.fixedColorGlowing(Color3.parseHex("#49daff")),
				CustomEyesLayer::noRender, CustomEyesLayer::noRender));
		this.addLayer(GasMaskLayer.forSnouted(this, context.getModelSet()));
	}

	@Override
	public ResourceLocation getTextureLocation(LatexCrystalJellyDragonEntity entity) {
		return TextureEntity;
	}
}
