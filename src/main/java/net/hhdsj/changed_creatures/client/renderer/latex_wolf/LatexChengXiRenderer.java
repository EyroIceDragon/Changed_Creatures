package net.hhdsj.changed_creatures.client.renderer.latex_wolf;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.client.models.latex_wolf.ModelLatexChengXi;
import net.hhdsj.changed_creatures.entity.simple.LatexChengXiEntity;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;
import net.ltxprogrammer.changed.client.renderer.layers.CustomEyesLayer;
import net.ltxprogrammer.changed.client.renderer.layers.GasMaskLayer;
import net.ltxprogrammer.changed.client.renderer.layers.LatexParticlesLayer;
import net.ltxprogrammer.changed.client.renderer.layers.TransfurCapeLayer;
import net.ltxprogrammer.changed.client.renderer.model.armor.ArmorLatexMaleWolfModel;
import net.ltxprogrammer.changed.util.Color3;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class LatexChengXiRenderer extends
        AdvancedHumanoidRenderer<LatexChengXiEntity, ModelLatexChengXi> {
    public LatexChengXiRenderer(EntityRendererProvider.Context context) {

        super(context, new ModelLatexChengXi(context.bakeLayer(ModelLatexChengXi.LAYER_LOCATION)),
                ArmorLatexMaleWolfModel.MODEL_SET,
                0.5f);
        this.addLayer(new LatexParticlesLayer<>(this, this.getModel()));
        this.addLayer(TransfurCapeLayer.normalCape(this, context.getModelSet()));
        this.addLayer(new CustomEyesLayer<>(this,
                context.getModelSet(),
                CustomEyesLayer::scleraColor,
                CustomEyesLayer.fixedColorGlowing(Color3.parseHex("#fef19b")),
                CustomEyesLayer.fixedColorGlowing(Color3.parseHex("#fef19b")),
                CustomEyesLayer::noRender, CustomEyesLayer::noRender));
        this.addLayer(GasMaskLayer.forSnouted(this, context.getModelSet()));
    }

    @Override
    public ResourceLocation getTextureLocation(LatexChengXiEntity entity) {
        return ChangedCreature.CCRL("textures/entities/latex/chengxi.png");
    }
}
