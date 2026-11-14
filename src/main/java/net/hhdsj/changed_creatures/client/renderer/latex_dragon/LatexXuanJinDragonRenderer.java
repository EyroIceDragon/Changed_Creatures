package net.hhdsj.changed_creatures.client.renderer.latex_dragon;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.client.models.armors.changed.ArmorLatexMaleWolfWingedDragonModel;
import net.hhdsj.changed_creatures.client.models.latex_dragon.ModelLatexXuanJinDragon;
import net.hhdsj.changed_creatures.entity.simple.LatexXuanJinDragonEntity;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;
import net.ltxprogrammer.changed.client.renderer.layers.CustomEyesLayer;
import net.ltxprogrammer.changed.client.renderer.layers.GasMaskLayer;
import net.ltxprogrammer.changed.client.renderer.layers.TransfurCapeLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class LatexXuanJinDragonRenderer extends AdvancedHumanoidRenderer<LatexXuanJinDragonEntity, ModelLatexXuanJinDragon> {

    ResourceLocation TextureEntity = ChangedCreature.CCRL("textures/entities/latex/latex_xuan_jin_dragon.png");

    public LatexXuanJinDragonRenderer(EntityRendererProvider.Context context) {
        super(context, new ModelLatexXuanJinDragon(context.bakeLayer(ModelLatexXuanJinDragon.LAYER_LOCATION)),
                ArmorLatexMaleWolfWingedDragonModel.MODEL_SET,
                0.5f);
        this.addLayer(TransfurCapeLayer.normalCape(this, context.getModelSet()));
        this.addLayer(new CustomEyesLayer<>(this, context.getModelSet(), CustomEyesLayer::scleraColor, CustomEyesLayer::glowingIrisColorLeft, CustomEyesLayer::glowingIrisColorRight, CustomEyesLayer::noRender, CustomEyesLayer::noRender));
        this.addLayer(GasMaskLayer.forSnouted(this, context.getModelSet()));

    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(LatexXuanJinDragonEntity entity) {
        return TextureEntity;
    }
}
