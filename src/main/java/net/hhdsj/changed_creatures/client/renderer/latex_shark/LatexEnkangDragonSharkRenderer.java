
package net.hhdsj.changed_creatures.client.renderer.latex_shark;

import com.mojang.blaze3d.vertex.PoseStack;
import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.client.models.latex_shark.ModelLatexEnkangDragonShark;
import net.hhdsj.changed_creatures.entity.simple.LatexEnkangDragonSharkEntity;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;
import net.ltxprogrammer.changed.client.renderer.layers.CustomEyesLayer;
import net.ltxprogrammer.changed.client.renderer.layers.GasMaskLayer;
import net.ltxprogrammer.changed.client.renderer.layers.LatexParticlesLayer;
import net.ltxprogrammer.changed.client.renderer.layers.TransfurCapeLayer;
import net.ltxprogrammer.changed.client.renderer.model.armor.ArmorLatexMaleSharkModel;
import net.ltxprogrammer.changed.entity.SpringType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

//public class LatexKcahraSharkRenderer extends MobRenderer<LatexEnkangDragonSharkEntity, ModelLatexEnkangDragonShark> {
public class LatexEnkangDragonSharkRenderer extends AdvancedHumanoidRenderer<LatexEnkangDragonSharkEntity, ModelLatexEnkangDragonShark>{
	public LatexEnkangDragonSharkRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelLatexEnkangDragonShark(context.bakeLayer(ModelLatexEnkangDragonShark.LAYER_LOCATION)),
			//ArmorLatexMaleSharkModel::new, 
            ArmorLatexMaleSharkModel.MODEL_SET,
            0.5f);
		this.addLayer(new LatexParticlesLayer<>(this, this.getModel()));
        this.addLayer(TransfurCapeLayer.normalCape(this, context.getModelSet()));
		this.addLayer(new CustomEyesLayer<>(this,
				context.getModelSet(), CustomEyesLayer::scleraColor,
				CustomEyesLayer::glowingIrisColorLeft,
				CustomEyesLayer::glowingIrisColorRight,
				CustomEyesLayer::noRender,
				CustomEyesLayer::noRender));
		this.addLayer(GasMaskLayer.forSnouted(this, context.getModelSet()));
	}

	@Override
	public @NotNull ResourceLocation getTextureLocation(@NotNull LatexEnkangDragonSharkEntity entity) {
		return ChangedCreature.CCRL("textures/entities/latex/latex_enkang_dragon_shark.png");
	}

	@Override
	protected void scale(@NotNull LatexEnkangDragonSharkEntity entity, @NotNull PoseStack pose, float deltaTime) {
		super.scale(entity, pose, deltaTime);
		float spring = entity.getSimulatedSpring(SpringType.MODERATE_STRONG, SpringType.Direction.VERTICAL, deltaTime) * -0.125f;
		pose.scale(LatexEnkangDragonSharkEntity.SCALE - spring, LatexEnkangDragonSharkEntity.SCALE + spring, LatexEnkangDragonSharkEntity.SCALE - spring);
	}
}
