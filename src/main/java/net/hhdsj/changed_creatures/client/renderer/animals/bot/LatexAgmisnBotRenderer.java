package net.hhdsj.changed_creatures.client.renderer.animals.bot;

import com.mojang.blaze3d.vertex.PoseStack;
import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.client.models.animals.bot.ModelLatexAgmisnBot;
import net.hhdsj.changed_creatures.entity.animals.bot.LatexAgmisnBotEntity;
import net.ltxprogrammer.changed.Changed;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;
import net.ltxprogrammer.changed.client.renderer.layers.LatexParticlesLayer;
import net.ltxprogrammer.changed.client.renderer.layers.TaurChestPackLayer;
import net.ltxprogrammer.changed.client.renderer.layers.TransfurCapeLayer;
import net.ltxprogrammer.changed.client.renderer.model.armor.ArmorLatexCentaurLowerModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.resources.ResourceLocation;

public class LatexAgmisnBotRenderer extends AdvancedHumanoidRenderer<LatexAgmisnBotEntity, ModelLatexAgmisnBot> {
    public static final ResourceLocation DEFAULT_SKIN_LOCATION =
            ChangedCreature.ChangedCreatureResourceLocation("textures/entities/animals/latex_agmisn_bot.png");

    public LatexAgmisnBotRenderer(EntityRendererProvider.Context context) {
        super(
                context,
                new ModelLatexAgmisnBot(context.bakeLayer(ModelLatexAgmisnBot.LAYER_LOCATION)),
                ArmorLatexCentaurLowerModel.MODEL_SET_NO_TORSO,
                0.7F
        );

        this.addLayer(new LatexParticlesLayer<>(this, this.getModel()));
        this.addLayer(TransfurCapeLayer.normalCape(this, context.getModelSet()));
        this.addLayer(new SaddleLayer(this, this.getModel(), Changed.modResource("textures/white_latex_centaur_saddle.png")));
        this.addLayer(new TaurChestPackLayer(this, context.getModelSet()));
    }

    @Override
    public ResourceLocation getTextureLocation(LatexAgmisnBotEntity entity) {
        return DEFAULT_SKIN_LOCATION;
    }

    @Override
    protected void scale(LatexAgmisnBotEntity entity, PoseStack pose, float partialTick) {
        super.scale(entity, pose, partialTick);
        pose.scale(1.05F, 1.05F, 1.05F);
    }
}