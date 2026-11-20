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

    @Override
    protected boolean isShaking(@NotNull LatexNightOwlBossEntity _ent) {
        if (_ent.getHealth() < _ent.getMaxHealth() * 0.3)
            return true;
        else
            return false;
    }
}
