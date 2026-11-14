
package net.hhdsj.changed_creatures.client.renderer.latex_wolf;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.client.models.latex_wolf.ModelLatexPurpleCoocWolf;
import net.hhdsj.changed_creatures.entity.simple.LatexPurplecoocwolfEntity;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;
import net.ltxprogrammer.changed.client.renderer.layers.*;
import net.ltxprogrammer.changed.client.renderer.model.armor.ArmorLatexMaleWolfModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class LatexPurpleCoocWolfRenderer extends AdvancedHumanoidRenderer<LatexPurplecoocwolfEntity, ModelLatexPurpleCoocWolf>  {//public class LatexOrangeFoxRenderer extends AdvancedHumanoidRenderer<LatexPurplecoocwolfEntity, ModelLatexPurpleCoocWolf, ArmorLatexMaleWolfModel<LatexPurplecoocwolfEntity>> { //extends MobRenderer<LatexPurplecoocwolfEntity, ModelLatexPurpleCoocWolf<LatexPurplecoocwolfEntity>> {
	public LatexPurpleCoocWolfRenderer(EntityRendererProvider.Context context) {
	
		super(context, new ModelLatexPurpleCoocWolf(context.bakeLayer(ModelLatexPurpleCoocWolf.LAYER_LOCATION)),
            ArmorLatexMaleWolfModel.MODEL_SET,
            0.5f);

		this.addLayer(new EmissiveBodyLayer<>(this, ChangedCreature.CCRL("textures/entities/latex/latex_purple_cooc_wolf_light.png")));
		this.addLayer(new LatexParticlesLayer<>(this, this.getModel()));
        this.addLayer(TransfurCapeLayer.normalCape(this, context.getModelSet()));
		this.addLayer(new CustomEyesLayer<>(this, context.getModelSet(), CustomEyesLayer::scleraColor, CustomEyesLayer::glowingIrisColorLeft, CustomEyesLayer::glowingIrisColorRight, CustomEyesLayer::noRender, CustomEyesLayer::noRender));
		this.addLayer(GasMaskLayer.forSnouted(this, context.getModelSet()));

	}

	@Override
	public @NotNull ResourceLocation getTextureLocation(@NotNull LatexPurplecoocwolfEntity entity) {
		return ChangedCreature.CCRL("textures/entities/latex/latex_purple_cooc_wolf.png");
	}
}
