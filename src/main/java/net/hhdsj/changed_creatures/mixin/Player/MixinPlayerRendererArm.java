package net.hhdsj.changed_creatures.mixin.Player;

import com.mojang.blaze3d.vertex.PoseStack;
import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.client.models.armors.fursuit.HumanoidFursuitModel;
import net.hhdsj.changed_creatures.item.HidePlayerPartArmor;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerRenderer.class)
public abstract class MixinPlayerRendererArm {

    @Unique
    private static HumanoidFursuitModel changed_creatures$cachedModel = null;

    @Inject(method = "renderRightHand", at = @At("HEAD"), cancellable = true)
    private void onRenderRightHand(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, AbstractClientPlayer player, CallbackInfo ci) {
        if (changed_creatures$renderCustomArm(poseStack, bufferSource, packedLight, player, HumanoidArm.RIGHT)) {
            ci.cancel();
        }
    }

    @Inject(method = "renderLeftHand", at = @At("HEAD"), cancellable = true)
    private void onRenderLeftHand(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, AbstractClientPlayer player, CallbackInfo ci) {
        if (changed_creatures$renderCustomArm(poseStack, bufferSource, packedLight, player, HumanoidArm.LEFT)) {
            ci.cancel();
        }
    }

    @Unique
    private static boolean changed_creatures$renderCustomArm(PoseStack poseStack, MultiBufferSource bufferSource,
                                                             int packedLight, AbstractClientPlayer player, HumanoidArm arm) {
        if (!(player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof HidePlayerPartArmor item)) {
            changed_creatures$cachedModel = null;
            return false;
        }

        if (changed_creatures$cachedModel == null) {
            changed_creatures$cachedModel = item.createModelInstance();
        }

        changed_creatures$cachedModel.attackTime = 0.0F;
        changed_creatures$cachedModel.crouching = false;
        changed_creatures$cachedModel.swimAmount = 0.0F;
        changed_creatures$cachedModel.setupAnim(player, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);

        changed_creatures$cachedModel.setAllVisible(false);
        ModelPart armPart = changed_creatures$cachedModel.getArm(arm);
        armPart.visible = true;

        armPart.xRot = 0.0F;

        ResourceLocation texture = ChangedCreature.ChangedCreatureResourceLocationParse(item.getArmorTexturePath(EquipmentSlot.CHEST));
        armPart.render(poseStack, bufferSource.getBuffer(RenderType.entityCutout(texture)), packedLight, OverlayTexture.NO_OVERLAY);
        return true;
    }
}