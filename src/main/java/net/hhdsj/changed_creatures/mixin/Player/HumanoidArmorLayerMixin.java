package net.hhdsj.changed_creatures.mixin.Player;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.client.item.FursuitGlowRenderer;
import net.hhdsj.changed_creatures.client.models.armors.fursuit.HumanoidFursuitModel;
import net.hhdsj.changed_creatures.item.HidePlayerPartArmor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = HumanoidArmorLayer.class, priority = 900)
public abstract class HumanoidArmorLayerMixin<T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>> extends RenderLayer<T, M> {

    public HumanoidArmorLayerMixin(RenderLayerParent<T, M> p_117346_) {
        super(p_117346_);
    }

    @Inject(method = "renderArmorPiece", at = @At("HEAD"), cancellable = true)
    private void customRenderArmorPiece(
            PoseStack poseStack, MultiBufferSource buffer, LivingEntity entity,
            EquipmentSlot slot, int packedLight, A defaultModel, CallbackInfo ci) {
        //if (true) return;

//        if (!(entity instanceof Player)) {
//            return;
//        }

        ItemStack stack = entity.getItemBySlot(slot);
        if (!(stack.getItem() instanceof HidePlayerPartArmor fursuitItem)) return;
        if (fursuitItem.getEquipmentSlot() != slot) return;

        IClientItemExtensions extensions = IClientItemExtensions.of(fursuitItem);
        HumanoidModel<?> customModel = extensions.getHumanoidArmorModel(entity, stack, slot, defaultModel);
        if (!(customModel instanceof HumanoidFursuitModel fursuitModel)) return;

        this.getParentModel().copyPropertiesTo((HumanoidModel<T>) fursuitModel);
        fursuitModel.setVisibilityForSlot(slot);

        Minecraft mc = Minecraft.getInstance();
        float partialTicks = mc.getPartialTick();
        float ageInTicks = entity.tickCount + partialTicks;
        float limbSwing = entity.walkAnimation.position(partialTicks);
        float limbSwingAmount = entity.walkAnimation.speed(partialTicks);
        float netHeadYaw = entity.getViewYRot(partialTicks);
        float headPitch = entity.getViewXRot(partialTicks);
        fursuitModel.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        changed_creatures$renderCustomModel(poseStack, buffer, packedLight, fursuitItem, slot, fursuitModel);
        ci.cancel();
    }

    private void changed_creatures$renderCustomModel(PoseStack poseStack, MultiBufferSource buffer, int packedLight,
                                                     HidePlayerPartArmor fursuitItem,
                                                     EquipmentSlot slot, HumanoidFursuitModel model) {
        // 基础纹理
        String path = fursuitItem.getArmorTexturePath(slot);
        ResourceLocation texture = ChangedCreature.ChangedCreatureResourceLocationParse(path);
        VertexConsumer baseConsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(texture));
        model.renderToBuffer(poseStack, baseConsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        // 发光层
        String glowPath = fursuitItem.getArmorTextureGlowPath();
        if (glowPath != null && !glowPath.isBlank()) {
            ResourceLocation glow = ChangedCreature.ChangedCreatureResourceLocationParse(glowPath);
            FursuitGlowRenderer.renderGlow(glow,poseStack,buffer,model);
        }
    }
}