
package net.hhdsj.changed_creatures.client.renderer.latex_dragon;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.client.models.latex_dragon.ModelLatexBlueIceDragon;
import net.hhdsj.changed_creatures.entity.simple.LatexyunxqicedragonEntity;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;
import net.ltxprogrammer.changed.client.renderer.layers.*;
import net.ltxprogrammer.changed.client.renderer.model.armor.ArmorLatexMaleWingedDragonModel;
import net.ltxprogrammer.changed.util.Color3;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class LatexYunXqIceDragonRenderer extends AdvancedHumanoidRenderer<LatexyunxqicedragonEntity, ModelLatexBlueIceDragon> {
	public LatexYunXqIceDragonRenderer(EntityRendererProvider.Context context) {
		
		super(context, new ModelLatexBlueIceDragon(context.bakeLayer(ModelLatexBlueIceDragon.LAYER_LOCATION)),
		ArmorLatexMaleWingedDragonModel.MODEL_SET, 
		0.5f);
		this.addLayer(new EmissiveBodyLayer<>(this, ChangedCreature.CCRL("textures/entities/latex/latex_blueice_dragon_light.png")));
        this.addLayer(new LatexParticlesLayer<>(this, getModel()));
        this.addLayer(TransfurCapeLayer.normalCape(this, context.getModelSet()));
        this.addLayer(new CustomEyesLayer<>(this, context.getModelSet()));
		this.addLayer(new CustomEyesLayer<>(this, context.getModelSet(), CustomEyesLayer::scleraColor, CustomEyesLayer.fixedColorGlowing(Color3.parseHex("#8dcfff")), CustomEyesLayer.fixedColorGlowing(Color3.parseHex("#8dcfff")), CustomEyesLayer::noRender, CustomEyesLayer::noRender));
		this.addLayer(GasMaskLayer.forSnouted(this, context.getModelSet()));
	}

	@Override
	public ResourceLocation getTextureLocation(LatexyunxqicedragonEntity entity) {
		return ChangedCreature.CCRL("textures/entities/latex/latex_blueice_dragon.png");
	}
}
