package net.hhdsj.changed_creatures.client.renderer.latex_taur;

import com.mojang.blaze3d.vertex.PoseStack;
import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.client.models.latex_taur.ModelLatexDarkPurpleDragonTaur;
import net.hhdsj.changed_creatures.entity.simple.LatexDarkPurpleDragonTaurEntity;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;
import net.ltxprogrammer.changed.client.renderer.layers.*;
import net.ltxprogrammer.changed.client.renderer.model.armor.ArmorLatexCentaurLowerModel;
import net.ltxprogrammer.changed.client.renderer.model.armor.ArmorLatexMaleTaurUpperModel;
import net.ltxprogrammer.changed.client.renderer.model.armor.ArmorModelPicker;
import net.ltxprogrammer.changed.util.Color3;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class LatexDarkPurpleDragonTaurRenderer extends AdvancedHumanoidRenderer<LatexDarkPurpleDragonTaurEntity, ModelLatexDarkPurpleDragonTaur> {
    public LatexDarkPurpleDragonTaurRenderer(EntityRendererProvider.Context context) {
        super(context, new ModelLatexDarkPurpleDragonTaur(context.bakeLayer(ModelLatexDarkPurpleDragonTaur.LAYER_LOCATION)),
                ArmorModelPicker.centaur(context.getModelSet(), ArmorLatexMaleTaurUpperModel.MODEL_SET, ArmorLatexCentaurLowerModel.MODEL_SET_WITH_TORSO), 0.7f);
        this.addLayer(new LatexParticlesLayer<>(this, getModel()));
        this.addLayer(new EmissiveBodyLayer<>(this, ChangedCreature.CCRL("textures/entities/latex/latex_taur/dark_purple_latex_dragon_taur_light.png")));
        this.addLayer(new CustomEyesLayer<>(this, context.getModelSet(), CustomEyesLayer::scleraColor,
                CustomEyesLayer.fixedColorGlowing(Color3.parseHex("#ca00ff")), CustomEyesLayer.fixedColorGlowing(Color3.parseHex("#ca00ff")),
                CustomEyesLayer::noRender, CustomEyesLayer::noRender));
        this.addLayer(new SaddleLayer<>(this, getModel(), ChangedCreature.CCRL("textures/entities/latex/latex_taur/dark_purple_latex_dragon_taur_saddle.png")));
        this.addLayer(new TaurChestPackLayer<>(this, context.getModelSet()));
        this.addLayer(TransfurCapeLayer.shortCape(this, context.getModelSet()));
        this.addLayer(GasMaskLayer.forSnouted(this, context.getModelSet()));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull LatexDarkPurpleDragonTaurEntity p_114482_) {
        return ChangedCreature.CCRL("textures/entities/latex/latex_taur/dark_purple_latex_dragon_taur.png");
    }

    @Override
    protected void scale(LatexDarkPurpleDragonTaurEntity entity, PoseStack poseStack, float partialTickTime) {
        float scaleFactor = 1.3F;
        poseStack.scale(scaleFactor, scaleFactor, scaleFactor);
        super.scale(entity, poseStack, partialTickTime);
    }
}