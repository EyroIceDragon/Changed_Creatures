package net.hhdsj.changed_creatures.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.client.models.other.ModelLatexWing;
import net.hhdsj.changed_creatures.util.PlayerDataGetHelper;
import net.ltxprogrammer.changed.entity.BasicPlayerInfo;
import net.ltxprogrammer.changed.entity.PlayerDataExtension;
import net.ltxprogrammer.changed.util.Color3;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AbstractLatexWingRenderer extends ElytraLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

    private static final ResourceLocation WINGS_TEXTURE =
            ChangedCreature.CCRL("textures/entities/latex_wind.png");
    private final EntityModelSet modelSet;

    private final Map<UUID, ModelLatexWing> playerModels = new HashMap<>();

    public AbstractLatexWingRenderer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> renderer,
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
            poseStack.translate(0.0F, 0.125F, 0.0F);
        }

        this.getParentModel().copyPropertiesTo(wingsModel);
        wingsModel.setupAnim(player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        float[] wingColors = getWingColorsFromPlayer(player);

        VertexConsumer vertexConsumer = ItemRenderer.getArmorFoilBuffer(
                buffer,
                RenderType.armorCutoutNoCull(WINGS_TEXTURE),
                false,
                chestItem.hasFoil()
        );

        wingsModel.renderToBuffer(
                poseStack,
                vertexConsumer,
                packedLight,
                OverlayTexture.NO_OVERLAY,
                wingColors[0],wingColors[1],wingColors[2], 1.0F
        );

        poseStack.popPose();
    }

    @Override
    public boolean shouldRender(ItemStack stack, AbstractClientPlayer entity) {
        if (entity.hasEffect(MobEffects.INVISIBILITY)) {
            return false;
        }
        return PlayerDataGetHelper.GetPlayerCanFly(entity);
    }

    @Override
    public ResourceLocation getElytraTexture(ItemStack stack, AbstractClientPlayer entity) {
        return WINGS_TEXTURE;
    }

    public float[] getWingColorsFromPlayer(AbstractClientPlayer player) {
        try {
            BasicPlayerInfo bpi = getBasicPlayerInfo(player);
            if (bpi != null) {
                Color3 hairColor = bpi.getHairColor();
                if (hairColor != null) {
                    return new float[]{hairColor.red(), hairColor.green(), hairColor.blue()};
                }
            }
        } catch (Exception ignored) {}

        return new float[]{1.0F, 1.0F, 1.0F};
    }

    private BasicPlayerInfo getBasicPlayerInfo(AbstractClientPlayer player) {
        if (player instanceof PlayerDataExtension ext) {
            return ext.getBasicPlayerInfo();
        }
        return null;
    }
}