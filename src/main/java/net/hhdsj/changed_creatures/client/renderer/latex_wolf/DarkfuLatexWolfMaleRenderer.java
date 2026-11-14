
package net.hhdsj.changed_creatures.client.renderer.latex_wolf;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.client.models.latex_wolf.ModelDarkfuLatexWolfMale;
import net.hhdsj.changed_creatures.entity.simple.DarkfuLatexWolfMaleEntity;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;
import net.ltxprogrammer.changed.client.renderer.layers.CustomEyesLayer;
import net.ltxprogrammer.changed.client.renderer.layers.GasMaskLayer;
import net.ltxprogrammer.changed.client.renderer.layers.LatexParticlesLayer;
import net.ltxprogrammer.changed.client.renderer.layers.TransfurCapeLayer;
import net.ltxprogrammer.changed.client.renderer.model.armor.ArmorLatexMaleWolfModel;
import net.ltxprogrammer.changed.util.Color3;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class DarkfuLatexWolfMaleRenderer extends AdvancedHumanoidRenderer<DarkfuLatexWolfMaleEntity , ModelDarkfuLatexWolfMale>{
	public DarkfuLatexWolfMaleRenderer(EntityRendererProvider.Context context) {
	  //super(context, new LatexBeifengModel(context.bakeLayer(LatexBeifengModel.LAYER_LOCATION)), ArmorLatexMaleDragonModel.MODEL_SET, 0.5f);
		super(context, new ModelDarkfuLatexWolfMale(context.bakeLayer(ModelDarkfuLatexWolfMale.LAYER_LOCATION)),
		//	ArmorLatexMaleWolfModel::new, 
            ArmorLatexMaleWolfModel.MODEL_SET, 
            0.5f);	
		this.addLayer(new LatexParticlesLayer<>(this, this.getModel()));
        this.addLayer(TransfurCapeLayer.normalCape(this, context.getModelSet()));
        this.addLayer(CustomEyesLayer.builder(this, context.getModelSet())
                .withSclera(Color3.WHITE).withIris(Color3.fromInt(0x43b44e)).build());
        this.addLayer(GasMaskLayer.forSnouted(this, context.getModelSet()));
	}

	@Override
	public ResourceLocation getTextureLocation(DarkfuLatexWolfMaleEntity entity) {
		return ChangedCreature.CCRL("textures/entities/latex/dark_latex_wolf_male.png");
	}
}
