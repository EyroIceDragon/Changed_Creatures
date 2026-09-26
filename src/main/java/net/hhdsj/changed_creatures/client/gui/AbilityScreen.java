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

    private static final int BAR_WIDTH = 226;
    private static final int BAR_HEIGHT = 5;

    private static final int HOVER_BG_COLOR = 0x40FFFFFF;
    private static final int MIN_LEVEL = 0;

    private static final int SLOT_COUNT = 4;

    // ---- 语言键常量 ----
    private static final String KEY_TITLE = "gui.changed_creatures.ability.title";
    private static final String KEY_BTN_DECREASE = "gui.changed_creatures.ability.button.decrease";
    private static final String KEY_BTN_INCREASE = "gui.changed_creatures.ability.button.increase";
    private static final String KEY_NOT_ENOUGH_EXP = "gui.changed_creatures.ability.not_enough_exp";
    private static final String KEY_PLAYER_EXP = "gui.changed_creatures.ability.player_exp";
    private static final String KEY_EMPTY_SLOT = "gui.changed_creatures.ability.empty_slot";
    private static final String KEY_NOT_OWNED = "gui.changed_creatures.ability.not_owned";
    private static final String KEY_LEVEL = "gui.changed_creatures.ability.level";
    private static final String KEY_LEVEL_SHORT = "gui.changed_creatures.ability.level_short";

    private int panelX;
    private int panelY;
    private int check_exp;
    private final Map<ResourceLocation, Button[]> abilityButtons = new HashMap<>();

    public AbilityScreen() {
        super(Component.translatable(KEY_TITLE));
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
                .tooltip(Tooltip.create(Component.translatable(
                        KEY_BTN_DECREASE, ability.getDisplayName())))
                .build();
        minusBtn.active = owned;
        this.addRenderableWidget(minusBtn);

        Button plusBtn = Button.builder(
                        Component.literal("+"),
                        btn -> ChangedCreature.PACKET_HANDLER.sendToServer(
                                new AbilitiesMessage(id, 1))
                )
                .bounds(btnX, btnY - BTN_SIZE - 1, BTN_SIZE, BTN_SIZE)
                .tooltip(Tooltip.create(Component.translatable(
                        KEY_BTN_INCREASE, ability.getDisplayName())))
                .build();
        plusBtn.active = owned;
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
            boolean enough_exp = AbilityUseExp.enoughExp(player, ability, level);

            Component text;
            if (!enough_exp && level < ability.getMaxLevel()) {
                text = Component.translatable(KEY_NOT_ENOUGH_EXP,
                        AbilityUseExp.missExp(player, ability, level));
            } else {
                text = Component.translatable(KEY_BTN_INCREASE, ability.getDisplayName());
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

        ResourceLocation TEXTURE = ChangedCreature.ChangedCreatureResourceLocation("textures/gui/ability/background_latex.png");

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

        graphics.blit(ChangedCreature.ChangedCreatureResourceLocation("textures/gui/ability/ability_gui_1.png"),
                this.panelX - FRAME_OFFSET, this.panelY - FRAME_OFFSET,
                0, 0, FRAME_WIDTH, FRAME_HEIGHT, FRAME_WIDTH, FRAME_HEIGHT);

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        Component title = Component.translatable(KEY_TITLE);
        int titleWidth = this.font.width(title);
        graphics.drawString(this.font, title,
                this.panelX + (PANEL_WIDTH - titleWidth) / 2,
                this.panelY + 15, 0xFFFFFF, false);

        for (Map.Entry<ResourceLocation, Button[]> entry : abilityButtons.entrySet()) {
            ResourceLocation id = entry.getKey();
            Button plusBtn = entry.getValue()[1];

            if (plusBtn.isMouseOver(mouseX, mouseY) && plusBtn.active) {
                AbstractAbility ability = AbilityRegistry.get(id);
                if (ability == null) continue;
                int level = PlayerAbilitiesCapability.get(player).getLevel(id);
                this.check_exp = Math.round(ability.useExp(level + 1));
            }
        }

        drawExpBar(graphics, AbilityUseExp.getPlayerExp(player), primary, this.check_exp);

        graphics.drawString(this.font,
                Component.translatable(KEY_PLAYER_EXP, AbilityUseExp.getPlayerExp(player)),
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
            String text = ability.getDisplayName() + " " + Component.translatable(KEY_LEVEL_SHORT).getString() + level;

            boolean rowHovered = mouseX >= rowX && mouseX <= rowX + IMG_WIDTH
                    && mouseY >= rowY && mouseY <= rowY + IMG_HEIGHT;

            if (rowHovered) {
                graphics.fill(rowX - 2, rowY - 2,
                        rowX + IMG_WIDTH + 2, rowY + IMG_HEIGHT + 2, HOVER_BG_COLOR);
            }

            ResourceLocation icon = ability.getAbilityTexture() != null
                    ? ability.getAbilityTexture()
                    : ChangedCreature.ChangedCreatureResourceLocation("textures/gui/ability/latex_ability_0.png");

            if (owned) {
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                graphics.blit(icon, rowX, rowY, 0, 0, IMG_WIDTH, IMG_HEIGHT, IMG_WIDTH, IMG_HEIGHT);
            } else {
                RenderSystem.setShaderColor(0.35F, 0.35F, 0.35F, 1.0F);
                graphics.blit(icon, rowX, rowY, 0, 0, IMG_WIDTH, IMG_HEIGHT, IMG_WIDTH, IMG_HEIGHT);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            }

            int textColor = owned ? ability.getDisplayColor() : 0xFF808080;

            drawStringWithBorder(graphics, text, rowX + 3, rowY + 3, textColor, 0xFF000000);

            if (rowHovered) {
                List<Component> tooltip = new ArrayList<>();
                tooltip.add(Component.literal("§b§l").append(ability.getDisplayName()));

                if (!owned) {
                    tooltip.add(Component.translatable(KEY_NOT_OWNED));
                } else {
                    ability.appendHoverText(tooltip, player, level);
                    tooltip.add(Component.translatable(KEY_LEVEL, level, ability.getMaxLevel()));
                }

                graphics.renderComponentTooltip(this.font, tooltip, mouseX, mouseY);
            }
        }
        this.check_exp = 0;
        super.render(graphics, mouseX, mouseY, partialTick);
    }

    private void renderEmptySlot(GuiGraphics graphics, int rowX, int rowY) {
        ResourceLocation emptyIcon = ChangedCreature.ChangedCreatureResourceLocation("textures/gui/ability/latex_ability_0.png");

        RenderSystem.setShaderColor(0.2F, 0.2F, 0.2F, 1.0F);
        graphics.blit(emptyIcon, rowX, rowY, 0, 0, IMG_WIDTH, IMG_HEIGHT, IMG_WIDTH, IMG_HEIGHT);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        graphics.drawString(this.font, Component.translatable(KEY_EMPTY_SLOT),
                rowX + 3, rowY + 3, 0xFF808080, false);
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

    private void drawExpBar(GuiGraphics graphics, int exp, Color3 color, int cost) {
        int barX = this.panelX - BAR_WIDTH / 2 + PANEL_WIDTH / 2;
        int barY = this.panelY - BAR_HEIGHT / 2 + 35;

        graphics.blit(ChangedCreature.ChangedCreatureResourceLocation("textures/gui/ability/latex_exp_bar.png"), barX, barY, 0, 0, BAR_WIDTH, BAR_HEIGHT, BAR_WIDTH, BAR_HEIGHT);
        int draw_level = exp / 100;
        int draw_exp = exp % 100;
        float progress = (float) draw_exp / 100;
        int draw_long = (int) (BAR_WIDTH * progress);

        if (draw_long > 0) {
            RenderSystem.setShaderColor(color.red(), color.green(), color.blue(), 1.0F);
            graphics.blit(ChangedCreature.ChangedCreatureResourceLocation("textures/gui/ability/latex_exp_bar_0.png"), barX, barY, 0, 0, draw_long, BAR_HEIGHT, BAR_WIDTH, BAR_HEIGHT);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        }

        if (cost > 0) {
            int costLong = (int) (BAR_WIDTH * ((float) Math.min(cost, draw_exp) / 100));
            int costW = Math.min(costLong, draw_long);
            if (costW > 0) {
                RenderSystem.setShaderColor(0.5F, 0.0F, 0.0F, 0.2F);
                graphics.blit(ChangedCreature.ChangedCreatureResourceLocation("textures/gui/ability/latex_exp_bar_0.png"), barX + draw_long - costW, barY, 0, 0, costW, BAR_HEIGHT, BAR_WIDTH, BAR_HEIGHT);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            }
        }

        drawStringWithBorder(graphics, String.valueOf(draw_level), barX + BAR_WIDTH / 2 - 3, barY - 3, 0xFFFFFF, 0x000000);
    }

    private void drawStringWithBorder(GuiGraphics graphics, String text, int x, int y, int textColor, int borderColor) {
        graphics.drawString(this.font, text, x - 1, y, borderColor, false);
        graphics.drawString(this.font, text, x + 1, y, borderColor, false);
        graphics.drawString(this.font, text, x, y - 1, borderColor, false);
        graphics.drawString(this.font, text, x, y + 1, borderColor, false);

        graphics.drawString(this.font, text, x, y, textColor, false);
    }
}