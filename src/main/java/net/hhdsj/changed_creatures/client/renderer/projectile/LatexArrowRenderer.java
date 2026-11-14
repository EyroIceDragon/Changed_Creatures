package net.hhdsj.changed_creatures.client.renderer.projectile;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.entity.simple.LatexthreemonthwolfEntityProjectile;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class LatexArrowRenderer extends ArrowRenderer<LatexthreemonthwolfEntityProjectile> {
    private static final ResourceLocation TEXTURE = ChangedCreature.CCRL("textures/entity/projectiles/arrow.png");

    public LatexArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(LatexthreemonthwolfEntityProjectile entity) {
        return TEXTURE;
    }
}