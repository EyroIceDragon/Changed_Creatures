package net.hhdsj.changed_creatures.client.renderer.projectile;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.entity.simple.CrystalArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class CrystalArrowRenderer extends ArrowRenderer<CrystalArrow> {
    public static final ResourceLocation TEXTURE = ChangedCreature.CCRL("textures/entity/projectiles/arrow.png");

    public CrystalArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(CrystalArrow entity) {
        return TEXTURE;
    }
}