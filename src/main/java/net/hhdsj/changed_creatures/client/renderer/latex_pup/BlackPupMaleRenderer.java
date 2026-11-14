
package net.hhdsj.changed_creatures.client.renderer.latex_pup;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.client.models.latex_pup.ModelBlackPupMale;
import net.hhdsj.changed_creatures.entity.simple.BlackpupmaleEntity;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;
import net.ltxprogrammer.changed.client.renderer.layers.CustomEyesLayer;
import net.ltxprogrammer.changed.client.renderer.layers.LatexParticlesLayer;
import net.ltxprogrammer.changed.client.renderer.model.armor.ArmorNoneModel;
import net.ltxprogrammer.changed.util.Color3;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Pose;


public class BlackPupMaleRenderer extends AdvancedHumanoidRenderer<BlackpupmaleEntity, ModelBlackPupMale> {
	public BlackPupMaleRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelBlackPupMale(context.bakeLayer(ModelBlackPupMale.LAYER_LOCATION)),
				//ArmorNoneModel::new, 
				ArmorNoneModel.MODEL_SET, 
				0.4F);
		this.addLayer(new LatexParticlesLayer<>(this, this.model));

		this.addLayer(CustomEyesLayer.builder(this, context.getModelSet())
				.withSclera(Color3.fromInt(0x242424))
				.withIris(CustomEyesLayer.fixedIfNotDarkLatexOverrideLeft(Color3.BLACK),
						CustomEyesLayer.fixedIfNotDarkLatexOverrideRight(Color3.BLACK))
				.build()
				.setHeadShape(CustomEyesLayer.HeadShape.PUP_CONCAVE));
	}

	@Override
	protected float getFlipDegrees(BlackpupmaleEntity entity) {
		return entity.getPose() == Pose.SLEEPING ? 0.0F : super.getFlipDegrees(entity);
	}

	@Override
	public ResourceLocation getTextureLocation(BlackpupmaleEntity entity) {
		return ChangedCreature.CCRL("textures/entities/latex/texture.png");
	}

}
