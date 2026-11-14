package net.hhdsj.changed_creatures.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.client.models.other.ModelLatexWing;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LatexWindRenderer extends AbstractLatexWingRenderer {

    private static final ResourceLocation WINGS_TEXTURE =
            ChangedCreature.CCRL("textures/entities/latex_wind.png");
    private static final ResourceLocation WINGS_EMISSIVE_TEXTURE =
            ChangedCreature.CCRL("textures/entities/latex_wind_light.png");

    private final EntityModelSet modelSet;
    private final Map<UUID, ModelLatexWing> playerModels = new HashMap<>();

    public LatexWindRenderer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> renderer,
                             EntityModelSet entityModelSet) {
        super(renderer, entityModelSet);
        this.modelSet = entityModelSet;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight,
                       AbstractClientPlayer player, float limbSwing, float limbSwingAmount,
                       float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {

        ItemStack chestItem = player.getItemBySlot(EquipmentSlot.CHEST);

        if (!shouldRender(chestItem, player)) {
            return;
        }

        UUID playerId = player.getUUID();

        ModelLatexWing wingsModel = playerModels.computeIfAbsent(playerId,
                id -> new ModelLatexWing(this.modelSet.bakeLayer(ModelLatexWing.LAYER_LOCATION))
        );

        poseStack.pushPose();
        if (player.isCrouching()) {
            poseStack.translate(0.0F, 0.25F, 0.0F);
        }else{
            poseStack.translate(0.0F, 0.125F, 0.0F);
        }

        this.getParentModel().copyPropertiesTo(wingsModel);
        wingsModel.setupAnim(player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        float[] wingColors = getWingColorsFromPlayer(player);

        // 先渲染普通层（底层）
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityTranslucent(WINGS_TEXTURE));
        wingsModel.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, wingColors[0], wingColors[1], wingColors[2], 1.0F);

        VertexConsumer emissiveConsumer = buffer.getBuffer(RenderType.entityTranslucentEmissive(WINGS_EMISSIVE_TEXTURE));
        wingsModel.renderToBuffer(poseStack, emissiveConsumer, 15728880, OverlayTexture.NO_OVERLAY, wingColors[0], wingColors[1], wingColors[2], 1.0F);

        poseStack.popPose();
    }
}