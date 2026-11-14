package net.hhdsj.changed_creatures.client.renderer.latex_dragon;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.client.models.latex_dragon.ModelDarkPurpleLatexDragon;
import net.hhdsj.changed_creatures.entity.simple.DarkPurpleLatexDragonEntity;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;
import net.ltxprogrammer.changed.client.renderer.layers.CustomEyesLayer;
import net.ltxprogrammer.changed.client.renderer.layers.EmissiveBodyLayer;
import net.ltxprogrammer.changed.client.renderer.layers.GasMaskLayer;
import net.ltxprogrammer.changed.client.renderer.layers.LatexParticlesLayer;
import net.ltxprogrammer.changed.client.renderer.model.armor.ArmorLatexMaleWingedDragonModel;
import net.ltxprogrammer.changed.util.Color3;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class LatexDarkPurpleDragonRenderer extends AdvancedHumanoidRenderer<DarkPurpleLatexDragonEntity, ModelDarkPurpleLatexDragon> {
    public LatexDarkPurpleDragonRenderer(EntityRendererProvider.Context context) {
        super(context, new ModelDarkPurpleLatexDragon(context.bakeLayer(ModelDarkPurpleLatexDragon.LAYER_LOCATION)),
                ArmorLatexMaleWingedDragonModel.MODEL_SET,
                0.5f);
        this.addLayer(new LatexParticlesLayer<>(this, getModel()));
        this.addLayer(new EmissiveBodyLayer<>(this, ChangedCreature.CCRL("textures/entities/latex/latex_taur/dark_purple_latex_dragon_light.png")));
        this.addLayer(new CustomEyesLayer<>(this, context.getModelSet(), CustomEyesLayer::scleraColor,
                CustomEyesLayer.fixedColorGlowing(Color3.parseHex("#ca00ff")), CustomEyesLayer.fixedColorGlowing(Color3.parseHex("#ca00ff")),
                CustomEyesLayer::noRender, CustomEyesLayer::noRender));
        this.addLayer(GasMaskLayer.forSnouted(this, context.getModelSet()));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull DarkPurpleLatexDragonEntity p_114482_) {
        return ChangedCreature.CCRL("textures/entities/latex/latex_taur/dark_purple_latex_dragon.png");
    }
}