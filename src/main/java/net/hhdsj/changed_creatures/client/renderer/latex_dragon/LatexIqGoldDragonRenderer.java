package net.hhdsj.changed_creatures.client.renderer.latex_dragon;

/*
public class LatexIqGoldDragonRenderer {
}*/

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.client.models.latex_dragon.ModelLatexIqGoldDragon;
import net.hhdsj.changed_creatures.entity.simple.LatexIqGoldDragonEntity;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;
import net.ltxprogrammer.changed.client.renderer.layers.*;
import net.ltxprogrammer.changed.client.renderer.model.armor.ArmorLatexMaleWingedDragonModel;
import net.ltxprogrammer.changed.util.Color3;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class LatexIqGoldDragonRenderer extends AdvancedHumanoidRenderer<LatexIqGoldDragonEntity, ModelLatexIqGoldDragon> {
    public LatexIqGoldDragonRenderer(EntityRendererProvider.Context context) {
        super(context, new ModelLatexIqGoldDragon(context.bakeLayer(ModelLatexIqGoldDragon.LAYER_LOCATION)),
                ArmorLatexMaleWingedDragonModel.MODEL_SET,
                0.5f);
        this.addLayer(new EmissiveBodyLayer<>(this, ChangedCreature.CCRL("textures/entities/latex/latex_iq_gold_dragon_light.png")));
        this.addLayer(new LatexParticlesLayer<>(this, getModel()));
        this.addLayer(TransfurCapeLayer.normalCape(this, context.getModelSet()));
        //this.addLayer(CustomEyesLayer.builder(this, context.getModelSet())
        this.addLayer(new CustomEyesLayer<>(this, context.getModelSet(), CustomEyesLayer::scleraColor, CustomEyesLayer.fixedColorGlowing(Color3.parseHex("#8dcfff")), CustomEyesLayer.fixedColorGlowing(Color3.parseHex("#8dcfff")), CustomEyesLayer::noRender, CustomEyesLayer::noRender));
        this.addLayer(GasMaskLayer.forSnouted(this, context.getModelSet()));

    }

    @Override
    public ResourceLocation getTextureLocation(LatexIqGoldDragonEntity entity) {
        return ChangedCreature.CCRL("textures/entities/latex/latex_iq_gold_dragon.png");
    }
}
