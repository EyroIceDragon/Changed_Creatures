package net.hhdsj.changed_creatures.mixin.Player;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.hhdsj.changed_creatures.client.models.other.TailModel;
import net.hhdsj.changed_creatures.init.ChangedCreatureModModelLayers;
import net.hhdsj.changed_creatures.network.GoodblockModVariables;
import net.hhdsj.changed_creatures.util.PlayerDataGetHelper;
import net.ltxprogrammer.changed.entity.variant.TransfurVariantInstance;
import net.ltxprogrammer.changed.process.ProcessTransfur;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Pose;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mixin(PlayerRenderer.class)
public abstract class MixinPlayerRenderer extends LivingEntityRenderer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

    @Unique
    private static final Map<UUID, TailModel> good_blocks$tailModelCache = new HashMap<>();

    public MixinPlayerRenderer(EntityRendererProvider.Context context, PlayerModel<AbstractClientPlayer> model, float shadowRadius) {
        super(context, model, shadowRadius);
    }

    @Inject(
            method = "render*",
            at = @At(value = "TAIL")
    )
    private void onRenderTail(
            AbstractClientPlayer player,
            float entityYaw,
            float partialTicks,
            PoseStack poseStack,
            MultiBufferSource buffer,
            int packedLight,
            CallbackInfo ci
    ) {

        if (player.isInvisible() || !player.isAlive() || player.getPose() == Pose.SLEEPING) {
            return;
        }

        GoodblockModVariables.PlayerVariables data = PlayerDataGetHelper.get(player);

        if (!data.Player_Is_Infection) {
            return;
        }

        TransfurVariantInstance<?> variant = ProcessTransfur.getPlayerTransfurVariant(player);

        if (variant != null) {
            return;
        }


        TailModel tailModel = good_blocks$tailModelCache.computeIfAbsent(
                player.getUUID(),
                uuid -> {
                    ModelPart root = Minecraft.getInstance()
                            .getEntityModels()
                            .bakeLayer(ChangedCreatureModModelLayers.TAIL_LAYER);
                    return new TailModel(root);
                }
        );

        float ageInTicks = player.tickCount + partialTicks;
        float limbSwing = player.walkAnimation.position(partialTicks);
        float limbSwingAmount = player.walkAnimation.speed(partialTicks);

        // 游泳/滑翔/飞行参数
        float swimAmount = player.getSwimAmount(partialTicks);
        float fallFlyingTicks = (float) player.getFallFlyingTicks();
        float fallFlyingAmount = Mth.clamp(fallFlyingTicks * fallFlyingTicks / 100.0F, 0.0F, 1.0F);

        // ===== 3. 设置尾巴动画（只设置目标旋转） =====
        tailModel.setupAnim(limbSwing, limbSwingAmount, ageInTicks);
        tailModel.applySmoothRotation(partialTicks);

        // ===== 4. 应用姿势特定动画 =====
        if (player.isCrouching()) {
            tailModel.setupCrouchAnim();
        }
        if (swimAmount > 0.0F) {
            tailModel.setupSwimAnim(swimAmount);
        }
        if (player.isPassenger()) {
            tailModel.setupRideAnim();
        }
        if (player.isFallFlying()) {
            tailModel.setupFallFlyAnim(fallFlyingTicks);
        }
        if (player.getPose() == Pose.SLEEPING) {
            tailModel.setupSleepAnim();
        }

        // ===== 5. 开始渲染变换 =====
        poseStack.pushPose();

        // ===== 6. 身体Y轴旋转（左右转身） =====
        float bodyYaw = Mth.rotLerp(partialTicks, player.yBodyRotO, player.yBodyRot);

        // 骑乘时：加入头部旋转的补偿
        if (player.isPassenger()) {
            float headYaw = Mth.lerp(partialTicks, player.yHeadRotO, player.yHeadRot);
            float headYawRelative = headYaw - bodyYaw;
            headYawRelative = Mth.clamp(headYawRelative, -5.0F, 5.0F);
            bodyYaw = bodyYaw + headYawRelative * 0.5F;
        }

        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - bodyYaw));

        // ===== 7. 姿势特定的身体倾斜 =====
        // 游泳：身体水平前倾
        if (swimAmount > 0.0F) {
            poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F * swimAmount));
        }

        // 滑翔：身体前倾
        if (fallFlyingAmount > 0.0F) {
            poseStack.mulPose(Axis.XP.rotationDegrees(-60.0F * fallFlyingAmount));
        }

        // 睡觉：侧躺
        if (player.getPose() == Pose.SLEEPING) {
            poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
            poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
        }

        double tailX = 0.0;
        double tailY = 1.5;
        double tailZ = 0;

        if (player.isCrouching()) {
            tailY = 1.4;
            tailZ = 0.15;
        }
        if (swimAmount > 0.0F) {
            tailY = 0.7 * swimAmount;
            tailZ = -0.5 * (1.0F - swimAmount);
        }
        if (player.isPassenger()) {
            tailY = 1.4;
            tailZ = 0;
        }
        if (fallFlyingAmount > 0.0F) {
            tailY = 1.8 - 0.1 * fallFlyingAmount;
            tailZ = -0.5 * (1.0F - fallFlyingAmount);
        }
        if (player.getPose() == Pose.SLEEPING) {
            tailY = -0.3;
            tailZ = 0.2;
        }

        poseStack.translate(tailX, tailY, tailZ);

        // ===== 9. 尾巴基准变换 =====
        poseStack.mulPose(Axis.ZP.rotationDegrees(180F));
        //poseStack.translate(0, -1.5, -0.1);

        // ===== 10. 渲染尾巴 =====
        VertexConsumer vertexConsumer = buffer.getBuffer(
                RenderType.entityCutoutNoCull(tailModel.getTexture())
        );
        //tailModel.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        poseStack.popPose();
    }
}