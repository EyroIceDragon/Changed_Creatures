package net.hhdsj.changed_creatures.client.renderer.projectile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.entity.simple.ThrownCrystalJavelinTrident;
import net.hhdsj.changed_creatures.init.ChangedCreatureModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ThrownCrystalJavelinTridentRenderer extends EntityRenderer<ThrownCrystalJavelinTrident> {

    // 纹理路径
    private static final ResourceLocation TEXTURE =
            ChangedCreature.CCRL("textures/item/thrown_crystal_javelin_trident.png");

    private static final ResourceLocation MODEL =
            ChangedCreature.CCRL("custom/thrown_crystal_javelin_trident");

    private final ItemRenderer itemRenderer;

    public ThrownCrystalJavelinTridentRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(ThrownCrystalJavelinTrident entity, float entityYaw, float partialTicks,
                       @NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();

        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot()) - 90.0F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, entity.xRotO, entity.getXRot()) + 90.0F));

        BakedModel model = Minecraft.getInstance().getModelManager().getModel(MODEL);
        ItemStack itemStack = new ItemStack(ChangedCreatureModItems.CRYSTAL_JAVELIN.get());

        this.itemRenderer.render(
                itemStack,
                ItemDisplayContext.FIXED,
                false,
                poseStack,
                buffer,
                packedLight,
                OverlayTexture.NO_OVERLAY,
                model
        );

        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    @NotNull
    public ResourceLocation getTextureLocation(@NotNull ThrownCrystalJavelinTrident entity) {
        return TEXTURE;
    }
}