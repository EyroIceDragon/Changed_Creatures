package net.hhdsj.changed_creatures.mixin.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.init.ChangedCreatureModTransfurVariants;
import net.ltxprogrammer.changed.entity.variant.TransfurVariantInstance;
import net.ltxprogrammer.changed.process.ProcessTransfur;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeUpdateListener;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@OnlyIn(Dist.CLIENT)
@Mixin(InventoryScreen.class)
public abstract class InventoryScreenMixin extends EffectRenderingInventoryScreen<InventoryMenu> implements RecipeUpdateListener {

    @Shadow
    private float xMouse;

    @Shadow
    private float yMouse;

    @Shadow
    public static void renderEntityInInventoryFollowsMouse(GuiGraphics graphics, int x, int y, int scale, float mouseX, float mouseY, LivingEntity entity) {
    }

    @Unique
    private static final ResourceLocation INVENTORY_TEXTURE = ChangedCreature.CCRL("textures/gui/protogen_inventory.png");

    @Unique
    private static final float DARK_RED = 5.0F / 255.0F;
    @Unique
    private static final float DARK_GREEN = 5.0F / 255.0F;
    @Unique
    private static final float DARK_BLUE = 5.0F / 255.0F;

    @Unique
    private static final float CYAN_RED = 0.9F;
    @Unique
    private static final float CYAN_GREEN = 0.9F;
    @Unique
    private static final float CYAN_BLUE = 0.95F;

    public InventoryScreenMixin(InventoryMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
    }

    @Unique
    private boolean changed_creatures$shouldApplyCustomGui() {
        try {
            var player = Minecraft.getInstance().player;
            if (player == null) return true;
            TransfurVariantInstance<?> variant = ProcessTransfur.getPlayerTransfurVariant(player);
            if (variant != null && variant.getParent() != null) {
                return variant.getParent() != ChangedCreatureModTransfurVariants.PROTOGEN_NYXARIX.get();
            }
        } catch (Exception ignored) {}
        return true;
    }

    @Inject(method = "renderBg", at = @At("HEAD"), cancellable = true)
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY, CallbackInfo callback) {
        if (this.minecraft == null) return;
        if (changed_creatures$shouldApplyCustomGui()) return;

        int i = this.leftPos;
        int j = this.topPos;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, INVENTORY_TEXTURE);

        graphics.setColor(DARK_RED, DARK_GREEN, DARK_BLUE, 0.3F);
        graphics.blit(INVENTORY_TEXTURE, i, j, 256, 0, this.imageWidth, this.imageHeight, 768, 256);

        graphics.setColor(CYAN_RED, CYAN_GREEN, CYAN_BLUE, 0.3F);
        graphics.blit(INVENTORY_TEXTURE, i, j, 0, 0, this.imageWidth, this.imageHeight, 768, 256);

        graphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);

        renderEntityInInventoryFollowsMouse(
                graphics,
                i + 51,
                j + 75,
                30,
                (float) (i + 51) - this.xMouse,
                (float) (j + 75 - 50) - this.yMouse,
                this.minecraft.player
        );

        callback.cancel();
    }

    @Inject(method = "renderLabels", at = @At("HEAD"), cancellable = true)
    protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY, CallbackInfo callback) {
        if (this.minecraft == null) return;
        if (changed_creatures$shouldApplyCustomGui()) return;

        graphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 0x00FFFF, false);
        callback.cancel();
    }
}