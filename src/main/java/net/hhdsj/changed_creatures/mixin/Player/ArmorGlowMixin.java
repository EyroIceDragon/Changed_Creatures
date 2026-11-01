package net.hhdsj.changed_creatures.mixin.Player;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.item.HidePlayerPartArmor;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidArmorLayer.class)
public class ArmorGlowMixin {

    @Inject(method = "renderModel", at = @At("TAIL"))
    private void onRenderModelTail(PoseStack poseStack, MultiBufferSource bufferSource,
                                   int packedLight, ArmorItem armorItem,
                                   net.minecraft.client.model.Model model,
                                   boolean innerModel, float red, float green, float blue,
                                   ResourceLocation armorResource, CallbackInfo ci) {
        // 只处理你的 HidePlayerPartArmor
        if (!(armorItem instanceof HidePlayerPartArmor fursuitItem)) return;
        if (fursuitItem.getArmorTextureGlowPath() == null) return;
        ResourceLocation glow = ChangedCreature.ChangedCreatureResourceLocationParse(fursuitItem.getArmorTextureGlowPath());

        VertexConsumer glowConsumer = bufferSource.getBuffer(RenderType.eyes(glow));
        model.renderToBuffer(poseStack, glowConsumer, packedLight,
                OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }
}