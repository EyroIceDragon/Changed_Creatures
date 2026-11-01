
package net.hhdsj.changed_creatures.client.renderer.latex_wolf_dragon;

import net.hhdsj.changed_creatures.client.models.armors.changed.ArmorLatexMaleWolfWingedDragonModel;
import net.hhdsj.changed_creatures.client.models.latex_wolf_dragon.ModelLatexXuanJinWolfDragon;
import net.hhdsj.changed_creatures.entity.simple.LatexXuanJinWolfDragonEntity;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;
import net.ltxprogrammer.changed.client.renderer.layers.*;
import net.ltxprogrammer.changed.util.Color3;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class LatexXuanJinWolfDragonRenderer extends AdvancedHumanoidRenderer<LatexXuanJinWolfDragonEntity, ModelLatexXuanJinWolfDragon> {

	ResourceLocation TextureEntity = new ResourceLocation("changed_creatures:textures/entities/latex/latex_xuan_jin_wolfdragon.png");

	public LatexXuanJinWolfDragonRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelLatexXuanJinWolfDragon(context.bakeLayer(ModelLatexXuanJinWolfDragon.LAYER_LOCATION)),
				ArmorLatexMaleWolfWingedDragonModel.MODEL_SET,
		0.5f);
		this.addLayer(new LatexParticlesLayer<>(this, this.model));
		this.addLayer(TransfurCapeLayer.normalCape(this, context.getModelSet()));
		this.addLayer(new CustomEyesLayer<>(this, context.getModelSet(), CustomEyesLayer::scleraColor,
				CustomEyesLayer.fixedColorGlowing(Color3.parseHex("#e6ab00")), CustomEyesLayer.fixedColorGlowing(Color3.parseHex("#e6ab00")),
				CustomEyesLayer::noRender, CustomEyesLayer::noRender));
		this.addLayer(GasMaskLayer.forSnouted(this, context.getModelSet()));
	}

	@Override
	public @NotNull ResourceLocation getTextureLocation(LatexXuanJinWolfDragonEntity entity) {
		return TextureEntity;
	}
}
