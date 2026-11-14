package net.hhdsj.changed_creatures.client.renderer.latex_fox;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.client.models.latex_wolf.ModelLatexDivineSwordKitsune;
import net.hhdsj.changed_creatures.entity.simple.LatexDivineSwordKitsuneEntity;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;
import net.ltxprogrammer.changed.client.renderer.layers.CustomEyesLayer;
import net.ltxprogrammer.changed.client.renderer.layers.GasMaskLayer;
import net.ltxprogrammer.changed.client.renderer.layers.LatexParticlesLayer;
import net.ltxprogrammer.changed.client.renderer.layers.TransfurCapeLayer;
import net.ltxprogrammer.changed.client.renderer.model.armor.ArmorLatexMaleWolfModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class LatexDivineSwordKitsuneRenderer extends AdvancedHumanoidRenderer<LatexDivineSwordKitsuneEntity, ModelLatexDivineSwordKitsune> {
    public LatexDivineSwordKitsuneRenderer(EntityRendererProvider.Context context) {
        super(context, new ModelLatexDivineSwordKitsune(context.bakeLayer(ModelLatexDivineSwordKitsune.LAYER_LOCATION)), ArmorLatexMaleWolfModel.MODEL_SET, 0.5f);
        this.addLayer(new LatexParticlesLayer<>(this, getModel()));
        this.addLayer(new CustomEyesLayer<>(this, context.getModelSet(), CustomEyesLayer::scleraColor, CustomEyesLayer::glowingIrisColorLeft, CustomEyesLayer::glowingIrisColorRight, CustomEyesLayer::noRender, CustomEyesLayer::noRender));
        this.addLayer(TransfurCapeLayer.normalCape(this, context.getModelSet()));
        this.addLayer(GasMaskLayer.forSnouted(this, context.getModelSet()));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull LatexDivineSwordKitsuneEntity entity) {
        return ChangedCreature.CCRL("textures/entities/latex/latex_divine_sword_kitsune.png");
    }
}
