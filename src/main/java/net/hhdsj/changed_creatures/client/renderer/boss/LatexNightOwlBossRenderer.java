package net.hhdsj.changed_creatures.client.renderer.boss;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.client.models.boss.ModelLatexNightOwlBoss;
import net.hhdsj.changed_creatures.entity.boss.LatexNightOwlBossEntity;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;
import net.ltxprogrammer.changed.client.renderer.layers.CustomEyesLayer;
import net.ltxprogrammer.changed.client.renderer.layers.GasMaskLayer;
import net.ltxprogrammer.changed.client.renderer.layers.LatexParticlesLayer;
import net.ltxprogrammer.changed.client.renderer.layers.TransfurCapeLayer;
import net.ltxprogrammer.changed.client.renderer.model.armor.ArmorLatexMaleWingedDragonModel;
import net.ltxprogrammer.changed.util.Color3;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class LatexNightOwlBossRenderer extends AdvancedHumanoidRenderer<LatexNightOwlBossEntity, ModelLatexNightOwlBoss> {
    public LatexNightOwlBossRenderer(EntityRendererProvider.Context context) {

        super(context, new ModelLatexNightOwlBoss(context.bakeLayer(ModelLatexNightOwlBoss.LAYER_LOCATION)),
                ArmorLatexMaleWingedDragonModel.MODEL_SET,
                0.5f);
        this.addLayer(new LatexParticlesLayer<>(this, getModel()));
        this.addLayer(TransfurCapeLayer.normalCape(this, context.getModelSet()));
        this.addLayer(new CustomEyesLayer<>(this, context.getModelSet()));
        this.addLayer(new CustomEyesLayer<>(this, context.getModelSet(), CustomEyesLayer::scleraColor,
                CustomEyesLayer.fixedColorGlowing(Color3.parseHex("#ffed7c")), CustomEyesLayer.fixedColorGlowing(Color3.parseHex("#ffed7c")),
                CustomEyesLayer::noRender, CustomEyesLayer::noRender));
        this.addLayer(GasMaskLayer.forSnouted(this, context.getModelSet()));
    }

    @Override
    public ResourceLocation getTextureLocation(LatexNightOwlBossEntity entity) {
        return ChangedCreature.CCRL("textures/entities/boss/latex_night_owl_boss_entity.png");
    }

    /*
    @Override
    public void render(@NotNull LatexNightOwlBossEntity entity, float entityYaw, float partialTicks,
                       @NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int packedLight) {

        // 在渲染前设置雾颜色（仅客户端）
        RenderSystem.setShaderFogColor(0.8f, 0.2f, 0.2f); // 红色雾
        RenderSystem.setShaderFogStart(-8.0f);
        RenderSystem.setShaderFogEnd(24.0f);


        // 调用父类的render方法（注意参数顺序和数量）
        super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLight);

        // 渲染后重置为默认颜色
        RenderSystem.setShaderFogColor(1.0f, 1.0f, 1.0f);
    }*/

    @Override
    protected boolean isShaking(@NotNull LatexNightOwlBossEntity _ent) {
        if (_ent.getHealth() < _ent.getMaxHealth() * 0.3)
            return true;
        else
            return false;
    }
}
