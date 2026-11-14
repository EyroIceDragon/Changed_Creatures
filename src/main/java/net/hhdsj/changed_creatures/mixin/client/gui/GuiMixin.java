package net.hhdsj.changed_creatures.mixin.client.gui;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.mojang.blaze3d.systems.RenderSystem;
import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.init.ChangedCreatureModTransfurVariants;
import net.ltxprogrammer.changed.entity.variant.TransfurVariantInstance;
import net.ltxprogrammer.changed.process.ProcessTransfur;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.MobEffectTextureManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.extensions.common.IClientMobEffectExtensions;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;
import java.util.List;

@Mixin(value = Gui.class, priority = 1100)
public abstract class GuiMixin {

    @Shadow
    @Final
    protected Minecraft minecraft;

    @Shadow
    protected int screenWidth;

    @Shadow
    protected int screenHeight;

    @Shadow
    @Final
    protected static ResourceLocation GUI_ICONS_LOCATION;

    @Shadow
    @Final
    protected static ResourceLocation WIDGETS_LOCATION;

    @Unique
    private static final ResourceLocation CUSTOM_HEART_TEXTURE = ChangedCreature.CCRL( "textures/gui/protogen_hearts.png");

    @Unique
    private static final ResourceLocation CUSTOM_INVENTORY_TEXTURE = ChangedCreature.CCRL("changed_creatures", "textures/gui/protogen_inventory.png");

    @Unique
    private static final ResourceLocation CUSTOM_WIDGETS_TEXTURE = ChangedCreature.CCRL("changed_creatures", "textures/gui/protogen_widgets.png");

    @Unique
    private static final float DARK_RED = 5.0F / 255.0F;
    @Unique
    private static final float DARK_GREEN = 5.0F / 255.0F;
    @Unique
    private static final float DARK_BLUE = 5.0F / 255.0F;

    // 发光色 #00ffff
    @Unique
    private static final float CYAN_RED = 1.0F;
    @Unique
    private static final float CYAN_GREEN = 1.0F;
    @Unique
    private static final float CYAN_BLUE = 1.0F;

    @Unique
    private boolean changed_creatures$shouldApplyCustomGui(Player player) {
        if (player == null) return false;
        try {
            TransfurVariantInstance<?> variant = ProcessTransfur.getPlayerTransfurVariant(player);
            if (variant != null) {
                return variant.getParent() == ChangedCreatureModTransfurVariants.PROTOGEN_NYXARIX.get();
            }
        } catch (Exception ignored) {}
        return false;
    }

    @Redirect(
            method = "renderHeart",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIIII)V"
            )
    )
    private void redirectHeartBlit(
            GuiGraphics graphics,
            ResourceLocation texture,
            int x, int y,
            int u, int v,
            int width, int height
    ) {
        Player player = this.minecraft.player;

        if (!changed_creatures$shouldApplyCustomGui(player)) {
            graphics.blit(texture, x, y, u, v, width, height);
            return;
        }

        graphics.setColor(DARK_RED, DARK_GREEN, DARK_BLUE, 1.0F);
        graphics.blit(CUSTOM_HEART_TEXTURE, x, y, u, v, width, height);

        graphics.setColor(CYAN_RED, CYAN_GREEN, CYAN_BLUE, 0.3F);
        graphics.blit(CUSTOM_HEART_TEXTURE, x - 1, y - 1, u, v, width, height);

        graphics.setColor(CYAN_RED, CYAN_GREEN, CYAN_BLUE, 0.9F);
        graphics.blit(CUSTOM_HEART_TEXTURE, x, y, u, v, width, height);

        graphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Inject(
            method = "renderEffects",
            at = @At("HEAD"),
            cancellable = true
    )
    protected void renderEffects(GuiGraphics graphics, CallbackInfo callback) {
        Player player = this.minecraft.player;
        if (player == null) return;

        if (!changed_creatures$shouldApplyCustomGui(player)) return;

        Collection<MobEffectInstance> activeEffects = player.getActiveEffects();
        if (activeEffects.isEmpty()) return;

        RenderSystem.enableBlend();

        int beneficialCount = 0;
        int harmfulCount = 0;
        MobEffectTextureManager textureManager = this.minecraft.getMobEffectTextures();
        List<Runnable> renderTasks = Lists.newArrayListWithExpectedSize(activeEffects.size());

        for (MobEffectInstance effectInstance : Ordering.natural().reverse().sortedCopy(activeEffects)) {
            MobEffect effect = effectInstance.getEffect();
            IClientMobEffectExtensions renderer = IClientMobEffectExtensions.of(effectInstance);

            if (!renderer.isVisibleInGui(effectInstance) || !effectInstance.showIcon()) continue;

            int x = this.screenWidth;
            int y = 1;

            if (this.minecraft.isDemo()) y += 15;

            if (effect.isBeneficial()) {
                beneficialCount++;
                x -= 25 * beneficialCount;
            } else {
                harmfulCount++;
                x -= 25 * harmfulCount;
                y += 26;
            }

            graphics.setColor(DARK_RED, DARK_GREEN, DARK_BLUE, 1.0F);

            if (effectInstance.isAmbient()) {
                graphics.blit(CUSTOM_INVENTORY_TEXTURE, x, y, 165, 166, 24, 24);
                graphics.setColor(CYAN_RED, CYAN_GREEN, CYAN_BLUE, 0.3F);
                graphics.blit(CUSTOM_INVENTORY_TEXTURE, x, y, 677, 166, 24, 24);
            } else {
                graphics.blit(CUSTOM_INVENTORY_TEXTURE, x, y, 141, 166, 24, 24);
                graphics.setColor(CYAN_RED, CYAN_GREEN, CYAN_BLUE, 0.3F);
                graphics.blit(CUSTOM_INVENTORY_TEXTURE, x, y, 653, 166, 24, 24);

                if (effectInstance.endsWithin(200)) {
                    int duration = effectInstance.getDuration();
                    float alpha = Mth.clamp(
                            (float) duration / 10.0F / 5.0F * 0.5F, 0.0F, 0.5F
                    ) + Mth.cos((float) duration * (float) Math.PI / 5.0F) *
                            Mth.clamp((float) (10 - duration / 20) / 10.0F * 0.25F, 0.0F, 0.25F);
                    graphics.setColor(CYAN_RED, CYAN_GREEN, CYAN_BLUE, alpha * 0.5F);
                    graphics.blit(CUSTOM_INVENTORY_TEXTURE, x, y, 653, 166, 24, 24);
                }
            }

            graphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);

            TextureAtlasSprite iconSprite = textureManager.get(effect);
            int finalX = x;
            int finalY = y;

            float alpha = 1.0F;
            if (effectInstance.endsWithin(200)) {
                int duration = effectInstance.getDuration();
                alpha = Mth.clamp(
                        (float) duration / 10.0F / 5.0F * 0.5F, 0.0F, 0.5F
                ) + Mth.cos((float) duration * (float) Math.PI / 5.0F) *
                        Mth.clamp((float) (10 - duration / 20) / 10.0F * 0.25F, 0.0F, 0.25F);
            }

            float finalAlpha = alpha;
            renderTasks.add(() -> {
                graphics.setColor(CYAN_RED, CYAN_GREEN, CYAN_BLUE, 0.3F);
                graphics.blit(finalX + 1, finalY + 1, 0, 18, 18, iconSprite);

                graphics.setColor(1.0F, 1.0F, 1.0F, finalAlpha);
                graphics.blit(finalX + 3, finalY + 3, 0, 18, 18, iconSprite);

                graphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
            });

            renderer.renderGuiIcon(effectInstance, (Gui) (Object) this, graphics, x, y, 0, alpha);
        }

        renderTasks.forEach(Runnable::run);
        graphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);

        callback.cancel();
    }

    @Inject(
            method = "renderHotbar",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIIII)V"
            )
    )
    private void onHotbarBlit(float partialTicks, GuiGraphics graphics, CallbackInfo ci) {
        Player player = this.minecraft.player;
        if (changed_creatures$shouldApplyCustomGui(player)) {
            graphics.setColor(CYAN_RED, CYAN_GREEN, CYAN_BLUE, 0.7F);
        }
    }

    @ModifyArg(
            method = "renderHotbar",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIIII)V"
            ),
            index = 0
    )
    private ResourceLocation redirectHotbarTexture(ResourceLocation original) {
        Player player = this.minecraft.player;
        if (changed_creatures$shouldApplyCustomGui(player) && original == WIDGETS_LOCATION) {
            return CUSTOM_WIDGETS_TEXTURE;
        }
        return original;
    }

    @Inject(
            method = "renderHotbar",
            at = @At("RETURN")
    )
    private void onHotbarEnd(float partialTicks, GuiGraphics graphics, CallbackInfo ci) {
        graphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
    }
}