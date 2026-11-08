package net.hhdsj.changed_creatures.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.hhdsj.changed_creatures.client.models.ModelProtogenNyxarix;
import net.hhdsj.changed_creatures.client.models.armors.changed.ArmorProtogenNyxarixWaterSquidDogModel;
import net.hhdsj.changed_creatures.entity.simple.ProtogenNyxarixEntity;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;
import net.ltxprogrammer.changed.client.renderer.layers.*;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class ProtogenNyxarixRenderer extends AdvancedHumanoidRenderer<ProtogenNyxarixEntity, ModelProtogenNyxarix> {
    public static final ResourceLocation DEFAULT_SKIN_LOCATION = new ResourceLocation("changed_creatures:textures/entities/latex/protogen_nyxarix.png");
    public static final ResourceLocation DEFAULT_LIGHT_SKIN_LOCATION = new ResourceLocation("changed_creatures:textures/entities/latex/protogen_nyxarix_light.png");

    public ProtogenNyxarixRenderer(EntityRendererProvider.Context context) {
        super(context, new ModelProtogenNyxarix(context.bakeLayer(ModelProtogenNyxarix.LAYER_LOCATION)), ArmorProtogenNyxarixWaterSquidDogModel.MODEL_SET, 0.65f);
        this.addLayer(new DoubleItemInHandLayer<>(this, context.getItemInHandRenderer()));
        this.addLayer(new EmissiveBodyLayer<>(this, DEFAULT_LIGHT_SKIN_LOCATION));
        this.addLayer(new LatexParticlesLayer<>(this, getModel()));
        this.addLayer(TransfurCapeLayer.normalCape(this, context.getModelSet()));
        this.addLayer(GasMaskLayer.forSnouted(this, context.getModelSet()));
    }

    @Override
    public ResourceLocation getTextureLocation(ProtogenNyxarixEntity entity) {
        return DEFAULT_SKIN_LOCATION;
    }

    @Override
    protected void scale(ProtogenNyxarixEntity entity, PoseStack pose, float partialTick) {
        float f = 1.0525F;
        pose.scale(1.0525F, 1.0525F, 1.0525F);
    }
}