package net.hhdsj.changed_creatures.client.renderer.latex_fox;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.client.models.latex_fox.ModelLatexFloraFox;
import net.hhdsj.changed_creatures.entity.simple.LatexFloraFoxEntity;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;
import net.ltxprogrammer.changed.client.renderer.layers.CustomEyesLayer;
import net.ltxprogrammer.changed.client.renderer.layers.GasMaskLayer;
import net.ltxprogrammer.changed.client.renderer.layers.LatexParticlesLayer;
import net.ltxprogrammer.changed.client.renderer.layers.TransfurCapeLayer;
import net.ltxprogrammer.changed.client.renderer.model.armor.ArmorLatexMaleWolfModel;
import net.ltxprogrammer.changed.util.Color3;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class LatexFloraFoxRenderer extends
        AdvancedHumanoidRenderer<LatexFloraFoxEntity, ModelLatexFloraFox> {
    public LatexFloraFoxRenderer(EntityRendererProvider.Context context) {

        super(context, new ModelLatexFloraFox(context.bakeLayer(ModelLatexFloraFox.LAYER_LOCATION)),
                ArmorLatexMaleWolfModel.MODEL_SET,
                0.5f);
        this.addLayer(new LatexParticlesLayer<>(this, this.getModel()));
        this.addLayer(TransfurCapeLayer.normalCape(this, context.getModelSet()));
        this.addLayer(new CustomEyesLayer<>(
                this, context.getModelSet(),
                CustomEyesLayer::scleraColor,
                CustomEyesLayer.fixedColorGlowing(Color3.parseHex("#65d7f1")),
                CustomEyesLayer.fixedColorGlowing(Color3.parseHex("#cd1a2f")),
                CustomEyesLayer::noRender, CustomEyesLayer::noRender));
        this.addLayer(GasMaskLayer.forSnouted(this, context.getModelSet()));
    }

    @Override
    public ResourceLocation getTextureLocation(LatexFloraFoxEntity entity) {
        return ChangedCreature.CCRL("textures/entities/latex/latex_flora_fox.png");
    }
}
