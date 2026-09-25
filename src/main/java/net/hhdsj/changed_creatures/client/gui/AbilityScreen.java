package net.hhdsj.changed_creatures.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.ability.data.*;
import net.hhdsj.changed_creatures.network.AbilitiesMessage;
import net.ltxprogrammer.changed.client.gui.AbstractRadialScreen;
import net.ltxprogrammer.changed.init.ChangedRegistry;
import net.ltxprogrammer.changed.process.ProcessTransfur;
import net.ltxprogrammer.changed.util.Color3;
import net.ltxprogrammer.changed.entity.variant.TransfurVariant;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(modid = ChangedCreature.MODID, value = Dist.CLIENT)
public class AbilityScreen extends Screen {

    private static final int PANEL_WIDTH = 320;
    private static final int PANEL_HEIGHT = 206;

    private static final int ROW_START_X = 15;
    private static final int ROW_START_Y = 50;
    private static final int ROW_HEIGHT = 30;
    private static final int BTN_SIZE = 14;
    private static final int BTN_GAP = 6;

    private static final int IMG_WIDTH = 114;
    private static final int IMG_HEIGHT = 26;

    private static final int FRAME_WIDTH = 330;
    private static final int FRAME_HEIGHT = 216;
    private static final int FRAME_OFFSET = 0;

    private static final int BG_WIDTH = 340;
    private static final int BG_HEIGHT = 226;

    private static final int HOVER_BG_COLOR = 0x40FFFFFF;
    private static final int MIN_LEVEL = 0;

    private static final int SLOT_COUNT = 4;

    private int panelX;
    private int panelY;

    private final Map<ResourceLocation, Button[]> abilityButtons = new HashMap<>();

    public AbilityScreen() {
        super(Component.literal("Ability"));
    }

    private List<RegistryObject<AbstractAbility>> getVariantAbilities() {
        Player player = Minecraft.getInstance().player;
        if (player == null) return List.of();

        var instance = ProcessTransfur.getPlayerTransfurVariant(player);
        if (instance == null) return List.of();

        TransfurVariant<?> variant = instance.getParent();
        if (variant == null) return List.of();

        ResourceLocation variantId = ChangedRegistry.TRANSFUR_VARIANT.get().getKey(variant);
        if (variantId == null) return List.of();

        List<RegistryObject<AbstractAbility>> abilities = VariantAbilityMap.getFor(variantId);

        return abilities.size() > SLOT_COUNT
                ? abilities.subList(0, SLOT_COUNT)
                : abilities;
    }

    @Override
    protected void init() {
        Player player = Minecraft.getInstance().player;
        super.init();

        if (player == null || ProcessTransfur.getPlayerTransfurVariant(player) == null) {
            this.onClose();
            return;
        }

        this.panelX = (this.width - PANEL_WIDTH) / 2;
        this.panelY = (this.height - PANEL_HEIGHT) / 2;

        abilityButtons.clear();

        List<RegistryObject<AbstractAbility>> abilities = getVariantAbilities();

        for (int i = 0; i < abilities.size(); i++) {
            RegistryObject<AbstractAbility> obj = abilities.get(i);
            AbstractAbility ability = obj.get();

            int rowY = this.panelY + ROW_START_Y + i * ROW_HEIGHT;
            int btnX = this.panelX + ROW_START_X + IMG_WIDTH + BTN_GAP - 4;
            int btnY = rowY + IMG_HEIGHT / 2;

            Button[] pair = addAbilityButtons(btnX, btnY, obj, ability);
            abilityButtons.put(obj.getId(), pair);
        }
    }

    private boolean playerHasAbility(ResourceLocation id) {
        Player player = Minecraft.getInstance().player;
        if (player == null) return false;
        return PlayerAbilitiesCapability.get(player).hasAbility(id);
    }

    private int getLevel(ResourceLocation id) {
        Player player = Minecraft.getInstance().player;
        if (player == null) return 0;
        return PlayerAbilitiesCapability.get(player).getLevel(id);
    }

    private Button[] addAbilityButtons(int btnX, int btnY,
                                       RegistryObject<AbstractAbility> obj,
                                       AbstractAbility ability) {
        ResourceLocation id = obj.getId();
        int level = getLevel(id);
        Player player = Minecraft.getInstance().player;
        boolean owned = playerHasAbility(id);


        Button minusBtn = Button.builder(
                        Component.literal("-"),
                        btn -> ChangedCreature.PACKET_HANDLER.sendToServer(
                                new AbilitiesMessage(id, 0))
                )
                .bounds(btnX, btnY, BTN_SIZE, BTN_SIZE)
                .tooltip(Tooltip.create(Component.literal("§c降低 " + ability.getDisplayName() + " 等级")))
                .build();
        minusBtn.active = owned && (level > MIN_LEVEL);
        this.addRenderableWidget(minusBtn);

        Button plusBtn = Button.builder(
                        Component.literal("+"),
                        btn -> ChangedCreature.PACKET_HANDLER.sendToServer(
                                new AbilitiesMessage(id, 1))
                )
                .bounds(btnX, btnY - BTN_SIZE - 1, BTN_SIZE, BTN_SIZE)
                .tooltip(Tooltip.create(Component.literal("§提升 " + ability.getDisplayName() + " 等级")))
                .build();
        plusBtn.active = owned && (level < ability.getMaxLevel());
        this.addRenderableWidget(plusBtn);

        return new Button[]{minusBtn, plusBtn};
    }

    private void updateButtonStates() {
        Player player = Minecraft.getInstance().player;
        if (player == null) return;

        PlayerAbilities abilities = PlayerAbilitiesCapability.get(player);

        for (RegistryObject<AbstractAbility> obj : getVariantAbilities()) {
            ResourceLocation id = obj.getId();
            AbstractAbility ability = obj.get();
            Button[] pair = abilityButtons.get(id);
            if (pair == null) continue;

            boolean owned = abilities.hasAbility(id);
            int level = abilities.getLevel(id);
            boolean enough_exp = AbilityUseExp.enoughExp(player,ability,level);
            Component text = Component.literal("§提升 " + ability.getDisplayName() + " 等级");
            if (!enough_exp) {
                text = Component.literal("经验不足");
            }
            pair[1].setTooltip(Tooltip.create(text));
            pair[0].active = owned && (level > MIN_LEVEL);
            pair[1].active = owned && (level < ability.getMaxLevel()) && enough_exp;
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        Player player = Minecraft.getInstance().player;
        if (player == null || ProcessTransfur.getPlayerTransfurVariant(player) == null) {
            this.onClose();
            return;
        }
        updateButtonStates();

        this.renderBackground(graphics);

        ResourceLocation TEXTURE = new ResourceLocation(
                ChangedCreature.MODID, "textures/gui/ability/background_latex.png");

        float[] norm = getMouseNormalized(mouseX, mouseY);
        float maxOffset = 5f;
        float scrollY = (System.currentTimeMillis() % 15000) / 15000f * 220f;
        float offsetX = norm[0] * maxOffset;
        float offsetY = scrollY;

        int baseX = this.panelX + (int) offsetX;
        int baseY = this.panelY + (int) offsetY;

        AbstractRadialScreen.ColorScheme colorPair = AbstractRadialScreen.getColors(
                ProcessTransfur.getPlayerTransfurVariant(player));
        Color3 primary = colorPair.background();
        Color3 secondary = colorPair.foreground();

        RenderSystem.setShaderColor(primary.red(), primary.green(), primary.blue(), 1.0F);
        graphics.enableScissor(this.panelX + 10, this.panelY + 10,
                this.panelX + PANEL_WIDTH, this.panelY + PANEL_HEIGHT);
        graphics.blit(TEXTURE, baseX, baseY, 0, 0, BG_WIDTH, BG_HEIGHT, BG_WIDTH, BG_HEIGHT);
        graphics.blit(TEXTURE, baseX - BG_WIDTH, baseY, 0, 0, BG_WIDTH, BG_HEIGHT, BG_WIDTH, BG_HEIGHT);
        graphics.blit(TEXTURE, baseX, baseY - 220, 0, 0, BG_WIDTH, BG_HEIGHT, BG_WIDTH, BG_HEIGHT);
        graphics.blit(TEXTURE, baseX - BG_WIDTH, baseY - 220, 0, 0, BG_WIDTH, BG_HEIGHT, BG_WIDTH, BG_HEIGHT);
        graphics.disableScissor();

        RenderSystem.setShaderColor(secondary.red(), secondary.green(), secondary.blue(), 1.0F);

        graphics.blit(new ResourceLocation(ChangedCreature.MODID, "textures/gui/ability/ability_gui_1.png"),
                this.panelX - FRAME_OFFSET, this.panelY - FRAME_OFFSET,
                0, 0, FRAME_WIDTH, FRAME_HEIGHT, FRAME_WIDTH, FRAME_HEIGHT);

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        Component title = Component.literal("能力");
        int titleWidth = this.font.width(title);
        graphics.drawString(this.font, title,
                this.panelX + (PANEL_WIDTH - titleWidth) / 2,
                this.panelY + 15, 0xFFFFFF, false);

        graphics.drawString(this.font,
                Component.literal("Check Button awa to test"),
                this.panelX + 15, this.panelY + 40, 0xAAAAAA, false);

        List<RegistryObject<AbstractAbility>> abilities = getVariantAbilities();

        for (int i = 0; i < SLOT_COUNT; i++) {
            int rowY = this.panelY + ROW_START_Y + i * ROW_HEIGHT;
            int rowX = this.panelX + ROW_START_X;

            if (i >= abilities.size()) {
                renderEmptySlot(graphics, rowX, rowY);
                continue;
            }

            RegistryObject<AbstractAbility> obj = abilities.get(i);
            AbstractAbility ability = obj.get();
            ResourceLocation id = obj.getId();
            boolean owned = playerHasAbility(id);

            int level = getLevel(id);
            String text = ability.getDisplayName() + " Lv." + level;

            boolean rowHovered = mouseX >= rowX && mouseX <= rowX + IMG_WIDTH
                    && mouseY >= rowY && mouseY <= rowY + IMG_HEIGHT;

            if (rowHovered) {
                graphics.fill(rowX - 2, rowY - 2,
                        rowX + IMG_WIDTH + 2, rowY + IMG_HEIGHT + 2, HOVER_BG_COLOR);
            }

            ResourceLocation icon = ability.getAbilityTexture() != null
                    ? ability.getAbilityTexture()
                    : new ResourceLocation(ChangedCreature.MODID, "textures/gui/ability/latex_ability_0.png");

            if (owned) {
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                graphics.blit(icon, rowX, rowY, 0, 0, IMG_WIDTH, IMG_HEIGHT, IMG_WIDTH, IMG_HEIGHT);
            } else {
                RenderSystem.setShaderColor(0.35F, 0.35F, 0.35F, 1.0F);
                graphics.blit(icon, rowX, rowY, 0, 0, IMG_WIDTH, IMG_HEIGHT, IMG_WIDTH, IMG_HEIGHT);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            }

            int textColor = owned ? ability.getDisplayColor() : 0xFF808080;

            int outlineColor = 0xFF000000;
            int draw_x = 3, draw_y = 3;
            graphics.drawString(this.font, text, rowX + draw_x - 1, rowY + draw_y, outlineColor, false);
            graphics.drawString(this.font, text, rowX + draw_x + 1, rowY + draw_y, outlineColor, false);
            graphics.drawString(this.font, text, rowX + draw_x, rowY + draw_y - 1, outlineColor, false);
            graphics.drawString(this.font, text, rowX + draw_x, rowY + draw_y + 1, outlineColor, false);
            graphics.drawString(this.font, text, rowX + draw_x, rowY + draw_y, textColor, false);

            if (rowHovered) {
                List<Component> tooltip = new ArrayList<>();
                tooltip.add(Component.literal("§b§l" + ability.getDisplayName()));

                if (!owned) {
                    tooltip.add(Component.literal("§c未拥有该能力"));
                } else {
                    ability.appendHoverText(tooltip, player, level);
                    tooltip.add(Component.literal("§7Level: §f" + level + " §7/ §f" + ability.getMaxLevel()));
                }

                graphics.renderComponentTooltip(this.font, tooltip, mouseX, mouseY);
            }
        }

        super.render(graphics, mouseX, mouseY, partialTick);
    }

    private void renderEmptySlot(GuiGraphics graphics, int rowX, int rowY) {
        ResourceLocation emptyIcon = new ResourceLocation(
                ChangedCreature.MODID, "textures/gui/ability/latex_ability_0.png");

        RenderSystem.setShaderColor(0.2F, 0.2F, 0.2F, 1.0F);
        graphics.blit(emptyIcon, rowX, rowY, 0, 0, IMG_WIDTH, IMG_HEIGHT, IMG_WIDTH, IMG_HEIGHT);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        graphics.drawString(this.font, "§7— 空 —", rowX + 3, rowY + 3, 0xFF808080, false);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    public float[] getMouseNormalized(float mouseX, float mouseY) {
        float centerX = this.width / 2f;
        float centerY = this.height / 2f;

        float normX = (mouseX - centerX) / centerX;
        float normY = (mouseY - centerY) / centerY;

        normX = Math.max(-1f, Math.min(1f, normX));
        normY = Math.max(-1f, Math.min(1f, normY));

        return new float[]{normX, normY};
    }
}