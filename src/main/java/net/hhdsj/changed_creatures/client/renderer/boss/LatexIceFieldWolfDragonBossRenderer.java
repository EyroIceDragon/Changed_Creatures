package net.hhdsj.changed_creatures.client.renderer.boss;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.client.models.boss.ModelLatexIceFieldWolfDragonBoss;
import net.hhdsj.changed_creatures.entity.boss.LatexIceFieldWolfDragonBossEntity;
import net.ltxprogrammer.changed.client.renderer.AdvancedHumanoidRenderer;
import net.ltxprogrammer.changed.client.renderer.layers.*;
import net.ltxprogrammer.changed.client.renderer.model.armor.ArmorLatexMaleWingedDragonModel;
import net.ltxprogrammer.changed.util.Color3;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class LatexIceFieldWolfDragonBossRenderer extends AdvancedHumanoidRenderer<LatexIceFieldWolfDragonBossEntity, ModelLatexIceFieldWolfDragonBoss> {
    public LatexIceFieldWolfDragonBossRenderer(EntityRendererProvider.Context context) {

        super(context, new ModelLatexIceFieldWolfDragonBoss(context.bakeLayer(ModelLatexIceFieldWolfDragonBoss.LAYER_LOCATION)),
                ArmorLatexMaleWingedDragonModel.MODEL_SET,
                0.5f);
        this.addLayer(new LatexParticlesLayer<>(this, getModel()));
        this.addLayer(TransfurCapeLayer.normalCape(this, context.getModelSet()));
        this.addLayer(new CustomEyesLayer<>(this, context.getModelSet()));
		this.addLayer(new EmissiveBodyLayer<>(this, ChangedCreature.CCRL("textures/entities/latex/latex_kamona_hsguy_dragonwolf_png_light.png")));
        this.addLayer(new CustomEyesLayer<>(this, context.getModelSet(), CustomEyesLayer::scleraColor,
				CustomEyesLayer.fixedColorGlowing(Color3.parseHex("#006400")), CustomEyesLayer.fixedColorGlowing(Color3.parseHex("#006400")),
				CustomEyesLayer::noRender, CustomEyesLayer::noRender));
        this.addLayer(GasMaskLayer.forSnouted(this, context.getModelSet()));
    }

    @Override
    public ResourceLocation getTextureLocation(LatexIceFieldWolfDragonBossEntity entity) {
        return ChangedCreature.CCRL("textures/entities/latex/latex_kamona_hsguy_dragonwolf_png1.png");
    }

    @Override
    protected boolean isShaking(@NotNull LatexIceFieldWolfDragonBossEntity _ent) {
        if (_ent.getHealth() < _ent.getMaxHealth() * 0.3)
            return true;
        else
            return false;
    }
}
