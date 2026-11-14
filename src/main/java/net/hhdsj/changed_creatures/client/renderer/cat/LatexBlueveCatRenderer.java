package net.hhdsj.changed_creatures.client.renderer.cat;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.client.models.cat.ModelLatexBlueveCat;
import net.hhdsj.changed_creatures.entity.simple.LatexBlueveCatEntity;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;
import net.ltxprogrammer.changed.client.renderer.layers.CustomEyesLayer;
import net.ltxprogrammer.changed.client.renderer.layers.GasMaskLayer;
import net.ltxprogrammer.changed.client.renderer.layers.LatexParticlesLayer;
import net.ltxprogrammer.changed.client.renderer.layers.TransfurCapeLayer;
import net.ltxprogrammer.changed.client.renderer.model.armor.ArmorLatexMaleWolfModel;
import net.ltxprogrammer.changed.util.Color3;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class LatexBlueveCatRenderer extends
        AdvancedHumanoidRenderer<LatexBlueveCatEntity, ModelLatexBlueveCat> {
    public LatexBlueveCatRenderer(EntityRendererProvider.Context context) {

        super(context, new ModelLatexBlueveCat(context.bakeLayer(ModelLatexBlueveCat.LAYER_LOCATION)),
                ArmorLatexMaleWolfModel.MODEL_SET,
                0.5f);
        this.addLayer(new LatexParticlesLayer<>(this, this.getModel()));
        this.addLayer(TransfurCapeLayer.normalCape(this, context.getModelSet()));
        this.addLayer(new CustomEyesLayer<>(this,
                context.getModelSet(),
                CustomEyesLayer::scleraColor,
                CustomEyesLayer.fixedColorGlowing(Color3.parseHex("#6e6b70")),
                CustomEyesLayer.fixedColorGlowing(Color3.parseHex("#2174be")),
                CustomEyesLayer::noRender, CustomEyesLayer::noRender));
        this.addLayer(GasMaskLayer.forSnouted(this, context.getModelSet()));
    }

    @Override
    public ResourceLocation getTextureLocation(LatexBlueveCatEntity entity) {
        return ChangedCreature.CCRL("textures/entities/latex/latex_blueve_cat.png");
    }
}
