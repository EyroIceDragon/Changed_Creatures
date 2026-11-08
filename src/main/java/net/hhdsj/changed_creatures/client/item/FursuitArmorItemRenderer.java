package net.hhdsj.changed_creatures.client.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.client.MathUtil;
import net.hhdsj.changed_creatures.client.models.armors.fursuit.HumanoidFursuitModel;
import net.hhdsj.changed_creatures.item.HidePlayerPartArmor;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public final class FursuitArmorItemRenderer extends BlockEntityWithoutLevelRenderer {
    private final Map<Item, HumanoidFursuitModel> models = new HashMap<>();

    public FursuitArmorItemRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelSet modelSet) {
        super(dispatcher, modelSet);
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext displayContext, PoseStack poseStack,
                             MultiBufferSource buffer, int packedLight, int packedOverlay) {
        if (!(stack.getItem() instanceof HidePlayerPartArmor item)) {
            return;
        }

        EquipmentSlot slot = item.getEquipmentSlot();
        if (slot != EquipmentSlot.HEAD && slot != EquipmentSlot.CHEST && slot != EquipmentSlot.LEGS) {
            return;
        }

        HumanoidFursuitModel model = models.computeIfAbsent(
                item, ignored -> FursuitArmorModelRegistry.createModel(item));
        model.setVisibilityForItem(slot);

        poseStack.pushPose();
        poseStack.translate(0.5F, 0.5F, 0.5F);
        poseStack.mulPose(Axis.XP.rotation(MathUtil.toRadians(180f)));
        if (displayContext != ItemDisplayContext.GUI) {
            poseStack.mulPose(Axis.YP.rotation(MathUtil.toRadians(180f)));
        }else{
            poseStack.mulPose(Axis.YP.rotation(MathUtil.toRadians(-12f)));
        }
        poseStack.scale(0.95F, 0.95F, 0.95F);
        switch (slot) {
            case HEAD -> poseStack.translate(0.0F, 0.3F, 0.0F);
            case CHEST -> poseStack.translate(0.0F, -0.4F, 0.0F);
            case LEGS -> poseStack.translate(0.0F, -1.05F, 0.0F);
            default -> { }
        }

        ResourceLocation texture = ChangedCreature.ChangedCreatureResourceLocationParse(
                item.getArmorTexturePath(slot));
        VertexConsumer baseConsumer = buffer.getBuffer(RenderType.armorCutoutNoCull(texture));
        model.renderToBuffer(poseStack, baseConsumer, packedLight, packedOverlay,
                1.0F, 1.0F, 1.0F, 1.0F);

        String glowPath = item.getArmorTextureGlowPath();
        if (glowPath != null && !glowPath.isBlank()) {
            ResourceLocation glow = ChangedCreature.ChangedCreatureResourceLocationParse(glowPath);
            VertexConsumer glowConsumer = buffer.getBuffer(RenderType.entityTranslucentEmissive(glow));
            model.renderToBuffer(poseStack, glowConsumer, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY,
                    1.0F, 1.0F, 1.0F, 1.0F);
        }
        poseStack.popPose();
    }
}
