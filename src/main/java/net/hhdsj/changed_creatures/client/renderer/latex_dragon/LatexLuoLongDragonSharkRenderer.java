package net.hhdsj.changed_creatures.client.renderer.latex_dragon;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.client.models.latex_dragon.ModelLatexLuoLongDragonShark;
import net.hhdsj.changed_creatures.entity.simple.LatexLuoLongDragonSharkEntity;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;
import net.ltxprogrammer.changed.client.renderer.layers.*;
import net.ltxprogrammer.changed.client.renderer.model.armor.ArmorLatexMaleWingedDragonModel;
import net.ltxprogrammer.changed.util.Color3;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class LatexLuoLongDragonSharkRenderer extends AdvancedHumanoidRenderer<LatexLuoLongDragonSharkEntity, ModelLatexLuoLongDragonShark> {
    public LatexLuoLongDragonSharkRenderer(EntityRendererProvider.Context context) {

        super(context, new ModelLatexLuoLongDragonShark(context.bakeLayer(ModelLatexLuoLongDragonShark.LAYER_LOCATION)),
                ArmorLatexMaleWingedDragonModel.MODEL_SET,
                0.5f);
        this.addLayer(new EmissiveBodyLayer<>(this, ChangedCreature.CCRL("textures/entities/latex/light_latex_luolong_dragon_shark.png")));
        this.addLayer(new LatexParticlesLayer<>(this, getModel()));
        this.addLayer(TransfurCapeLayer.normalCape(this, context.getModelSet()));
        this.addLayer(new CustomEyesLayer<>(this, context.getModelSet()));
        this.addLayer(new CustomEyesLayer<>(this, context.getModelSet(), CustomEyesLayer::scleraColor, CustomEyesLayer.fixedColorGlowing(Color3.parseHex("#e2506c")), CustomEyesLayer.fixedColorGlowing(Color3.parseHex("#e2506c")), CustomEyesLayer::noRender, CustomEyesLayer::noRender));
        this.addLayer(GasMaskLayer.forSnouted(this, context.getModelSet()));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull LatexLuoLongDragonSharkEntity entity) {
        return ChangedCreature.CCRL("textures/entities/latex/latex_luolong_dragon_shark.png");
    }
}