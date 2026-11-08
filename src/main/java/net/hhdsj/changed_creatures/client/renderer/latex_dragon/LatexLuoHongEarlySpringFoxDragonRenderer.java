package net.hhdsj.changed_creatures.client.renderer.latex_dragon;

import com.mojang.blaze3d.vertex.PoseStack;
import net.hhdsj.changed_creatures.client.models.latex_dragon.ModelLuoHongEarlySpringFoxDragon;
import net.hhdsj.changed_creatures.entity.simple.LatexLuoHongEarlySpringFoxDragonEntity;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;
import net.ltxprogrammer.changed.client.renderer.layers.*;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import net.hhdsj.changed_creatures.client.models.armors.changed.ArmorLatexMaleWolfWingedDragonModel;

public class LatexLuoHongEarlySpringFoxDragonRenderer extends AdvancedHumanoidRenderer<LatexLuoHongEarlySpringFoxDragonEntity, ModelLuoHongEarlySpringFoxDragon> {
    public LatexLuoHongEarlySpringFoxDragonRenderer(EntityRendererProvider.Context context) {
        super(context, new ModelLuoHongEarlySpringFoxDragon(context.bakeLayer(ModelLuoHongEarlySpringFoxDragon.LAYER_LOCATION)),
                ArmorLatexMaleWolfWingedDragonModel.MODEL_SET,
                0.5f);
        this.addLayer(TransfurCapeLayer.normalCape(this, context.getModelSet()));
        this.addLayer(new CustomEyesLayer<>(this, context.getModelSet(), CustomEyesLayer::scleraColor, CustomEyesLayer::glowingIrisColorLeft, CustomEyesLayer::glowingIrisColorRight, CustomEyesLayer::noRender, CustomEyesLayer::noRender));
        this.addLayer(GasMaskLayer.forSnouted(this, context.getModelSet()));

    }

    @Override
    protected void scale(LatexLuoHongEarlySpringFoxDragonEntity entity, PoseStack poseStack, float partialTickTime) {
        float scaleFactor = 0.75F;
        poseStack.scale(scaleFactor, scaleFactor, scaleFactor);
        super.scale(entity, poseStack, partialTickTime);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(LatexLuoHongEarlySpringFoxDragonEntity entity) {
        return new ResourceLocation("changed_creatures:textures/entities/latex/latex_luohong_early_spring_fox_dragon.png");
    }
}
