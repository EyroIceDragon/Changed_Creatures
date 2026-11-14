package net.hhdsj.changed_creatures.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.block.LatexDeepCrystalDragonDollBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class LatexDeepCrystalDragonDollRenderer implements BlockEntityRenderer<LatexDeepCrystalDragonDollBlockEntity> {

    private static final ResourceLocation MODEL_LOCATION =
            ChangedCreature.CCRL("block/latex_crystal_jelly_dragon_doll");

    private final ItemRenderer itemRenderer;

    public LatexDeepCrystalDragonDollRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(LatexDeepCrystalDragonDollBlockEntity entity, float partialTick,
                       PoseStack poseStack, MultiBufferSource buffer,
                       int packedLight, int packedOverlay) {

        BakedModel model = Minecraft.getInstance().getModelManager().getModel(MODEL_LOCATION);
        ItemStack stack = new ItemStack(Blocks.AIR);

        poseStack.pushPose();

        poseStack.translate(0.5, 0.0, 0.5);

        float scale = 0.0625f;

        poseStack.scale(scale, scale, scale);

        poseStack.translate(0, -0.5, 0);

        poseStack.mulPose(Axis.YP.rotationDegrees(entity.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot()));

        itemRenderer.render(stack, ItemDisplayContext.NONE, false,
                poseStack, buffer,
                packedLight, OverlayTexture.NO_OVERLAY, model);

        poseStack.popPose();
    }
}