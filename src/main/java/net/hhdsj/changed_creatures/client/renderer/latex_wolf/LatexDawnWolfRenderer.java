package net.hhdsj.changed_creatures.client.renderer.latex_wolf;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.client.models.latex_wolf.ModelLatexDawnWolf;
import net.hhdsj.changed_creatures.entity.simple.LatexDawnWolfEntity;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;
import net.ltxprogrammer.changed.client.renderer.layers.CustomEyesLayer;
import net.ltxprogrammer.changed.client.renderer.layers.GasMaskLayer;
import net.ltxprogrammer.changed.client.renderer.layers.LatexParticlesLayer;
import net.ltxprogrammer.changed.client.renderer.layers.TransfurCapeLayer;
import net.ltxprogrammer.changed.client.renderer.model.armor.ArmorLatexMaleWolfModel;
import net.ltxprogrammer.changed.util.Color3;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class LatexDawnWolfRenderer extends
        AdvancedHumanoidRenderer<LatexDawnWolfEntity, ModelLatexDawnWolf> {
    public LatexDawnWolfRenderer(EntityRendererProvider.Context context) {

        super(context, new ModelLatexDawnWolf(context.bakeLayer(ModelLatexDawnWolf.LAYER_LOCATION)),
                ArmorLatexMaleWolfModel.MODEL_SET,
                0.5f);
        this.addLayer(new LatexParticlesLayer<>(this, this.getModel()));
        this.addLayer(TransfurCapeLayer.normalCape(this, context.getModelSet()));
        this.addLayer(new CustomEyesLayer<>(this,
                context.getModelSet(),
                CustomEyesLayer::scleraColor,
                CustomEyesLayer.fixedColorGlowing(Color3.parseHex("#f2dc29")),
                CustomEyesLayer.fixedColorGlowing(Color3.parseHex("#f2dc29")),
                CustomEyesLayer::noRender, CustomEyesLayer::noRender));
        this.addLayer(GasMaskLayer.forSnouted(this, context.getModelSet()));
    }

    @Override
    public ResourceLocation getTextureLocation(LatexDawnWolfEntity entity) {
        return ChangedCreature.CCRL("textures/entities/latex/latex_dawn_wolf.png");
    }
}
