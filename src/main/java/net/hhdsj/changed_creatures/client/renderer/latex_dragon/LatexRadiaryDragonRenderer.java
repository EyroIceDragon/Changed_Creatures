package net.hhdsj.changed_creatures.client.renderer.latex_dragon;

import com.mojang.blaze3d.vertex.PoseStack;
import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.client.models.latex_dragon.ModelLatexRadiaryDragon;
import net.hhdsj.changed_creatures.entity.simple.LatexRadiaryDragonEntity;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;
import net.ltxprogrammer.changed.client.renderer.layers.*;
import net.ltxprogrammer.changed.client.renderer.model.armor.ArmorLatexMaleWingedDragonModel;
import net.ltxprogrammer.changed.util.Color3;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class LatexRadiaryDragonRenderer extends AdvancedHumanoidRenderer<LatexRadiaryDragonEntity, ModelLatexRadiaryDragon> {
    public LatexRadiaryDragonRenderer(EntityRendererProvider.Context context) {
        super(context, new ModelLatexRadiaryDragon(context.bakeLayer(ModelLatexRadiaryDragon.LAYER_LOCATION)),
                ArmorLatexMaleWingedDragonModel.MODEL_SET,
                0.5f);
        this.addLayer(new EmissiveBodyLayer<>(this,
                ChangedCreature.CCRL("textures/entities/latex/latex_radiary_dragon_light.png")));
        this.addLayer(new LatexParticlesLayer<>(this, getModel()));
        this.addLayer(TransfurCapeLayer.normalCape(this, context.getModelSet()));
        this.addLayer(CustomEyesLayer.builder(this, context.getModelSet())
                .withSclera(Color3.WHITE).withIris(Color3.fromInt(0xff3333)).build());
        this.addLayer(GasMaskLayer.forSnouted(this, context.getModelSet()));

    }

    @Override
    public ResourceLocation getTextureLocation(LatexRadiaryDragonEntity entity) {
        return ChangedCreature.CCRL("textures/entities/latex/latex_radiary_dragon.png");
    }

    @Override
    protected void scale(LatexRadiaryDragonEntity entity, PoseStack poseStack, float partialTickTime) {
        float scaleFactor = 0.75F;
        poseStack.scale(scaleFactor, scaleFactor, scaleFactor);
        super.scale(entity, poseStack, partialTickTime);
    }
}
