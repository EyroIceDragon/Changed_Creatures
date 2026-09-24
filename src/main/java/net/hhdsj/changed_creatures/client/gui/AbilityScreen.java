package net.hhdsj.changed_creatures.client.gui;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.ability.data.AbstractAbility;
import net.hhdsj.changed_creatures.ability.data.PlayerAbilities;
import net.hhdsj.changed_creatures.ability.data.PlayerAbilitiesCapability;
import net.hhdsj.changed_creatures.init.ChangedCreaturesModNewAbiliies;
import net.hhdsj.changed_creatures.network.AbilitiesMessage;
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
    private static final int BTN_SPACING = 2;

    // 图片尺寸
    private static final int IMG_WIDTH = 114;
    private static final int IMG_HEIGHT = 26;

    // 边框贴图尺寸（340x226，内框相对图片左上角偏移 20）
    private static final int FRAME_WIDTH = 330;
    private static final int FRAME_HEIGHT = 216;
    private static final int FRAME_OFFSET = 0;

    private static final int BG_WIDTH = 340;
    private static final int BG_HEIGHT = 226;

    private static final int HOVER_BG_COLOR = 0x40FFFFFF;
    private static final int MIN_LEVEL = 0;

    private int panelX;
    private int panelY;

    private static RegistryObject<AbstractAbility>[] ABILITY_LIST = new RegistryObject[]{
            ChangedCreaturesModNewAbiliies.HYPNOSIE,
            ChangedCreaturesModNewAbiliies.ELECTRIC_RESISTANCE,
    };

    private final Map<ResourceLocation, Button[]> abilityButtons = new HashMap<>();

    public AbilityScreen() {
        super(Component.literal("Test"));
    }

    @Override
    protected void init() {
        super.init();
        this.panelX = (this.width - PANEL_WIDTH) / 2;
        this.panelY = (this.height - PANEL_HEIGHT) / 2;

        abilityButtons.clear();

        for (int i = 0; i < ABILITY_LIST.length; i++) {
            RegistryObject<AbstractAbility> obj = ABILITY_LIST[i];
            AbstractAbility ability = obj.get();

            int rowY = this.panelY + ROW_START_Y + i * ROW_HEIGHT;
            int btnX = this.panelX + ROW_START_X + IMG_WIDTH + BTN_GAP - 4;
            int btnY = rowY + IMG_HEIGHT / 2;

            Button[] pair = addAbilityButtons(btnX, btnY, obj, ability);
            abilityButtons.put(obj.getId(), pair);
        }
    }

    private String getAbilityText(RegistryObject<AbstractAbility> obj, AbstractAbility ability) {
        Player player = Minecraft.getInstance().player;
        if (player == null) {
            return ability.getDisplayName() + " Lv.0";
        }

        PlayerAbilities abilities = PlayerAbilitiesCapability.get(player);
        ResourceLocation id = obj.getId();
        int level = abilities.getLevel(id);
        return ability.getDisplayName() + " Lv." + level;
    }

    private int getLevel(RegistryObject<AbstractAbility> obj) {
        Player player = Minecraft.getInstance().player;
        if (player == null) return 0;
        return PlayerAbilitiesCapability.get(player).getLevel(obj.getId());
    }

    private Button[] addAbilityButtons(int btnX, int btnY,
                                       RegistryObject<AbstractAbility> obj,
                                       AbstractAbility ability) {
        int level = getLevel(obj);
        ResourceLocation id = obj.getId();

        Button minusBtn = Button.builder(
                        Component.literal("-"),
                        btn -> ChangedCreature.PACKET_HANDLER.sendToServer(
                                new AbilitiesMessage(id, 0))
                )
                .bounds(btnX, btnY, BTN_SIZE, BTN_SIZE)
                .tooltip(Tooltip.create(Component.literal(
                        "§c降低 " + ability.getDisplayName() + " 等级")))
                .build();
        minusBtn.active = (level > MIN_LEVEL);
        this.addRenderableWidget(minusBtn);

        Button plusBtn = Button.builder(
                        Component.literal("+"),
                        btn -> ChangedCreature.PACKET_HANDLER.sendToServer(
                                new AbilitiesMessage(id, 1))
                )
                .bounds(btnX , btnY - BTN_SIZE - 1, BTN_SIZE, BTN_SIZE)
                .tooltip(Tooltip.create(Component.literal(
                        "§a提升 " + ability.getDisplayName() + " 等级")))
                .build();
        plusBtn.active = (level < ability.getMaxLevel());
        this.addRenderableWidget(plusBtn);

        return new Button[]{minusBtn, plusBtn};
    }

    private void updateButtonStates() {
        Player player = Minecraft.getInstance().player;
        if (player == null) return;

        PlayerAbilities abilities = PlayerAbilitiesCapability.get(player);

        for (int i = 0; i < ABILITY_LIST.length; i++) {
            RegistryObject<AbstractAbility> obj = ABILITY_LIST[i];
            ResourceLocation id = obj.getId();
            AbstractAbility ability = obj.get();
            Button[] pair = abilityButtons.get(id);
            if (pair == null) continue;

            int level = abilities.getLevel(id);
            pair[0].active = (level > MIN_LEVEL);                  // "-"
            pair[1].active = (level < ability.getMaxLevel());      // "+"
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        updateButtonStates();

        this.renderBackground(graphics);

        ResourceLocation TEXTURE = new ResourceLocation(
                ChangedCreature.MODID, "textures/gui/ability/dark_latex.png");

        float[] norm = getMouseNormalized(mouseX, mouseY);

        float maxOffset = 5f;

        float scrollY = (System.currentTimeMillis() % 15000) / 15000f * 220f;

        float offsetX = norm[0] * maxOffset;
        float offsetY = scrollY;

        int baseX = this.panelX + (int) offsetX;
        int baseY = this.panelY + (int) offsetY;

        graphics.enableScissor(this.panelX + 10, this.panelY + 10,
                this.panelX + PANEL_WIDTH, this.panelY + PANEL_HEIGHT);

        graphics.blit(TEXTURE, baseX,            baseY,                0, 0, BG_WIDTH, BG_HEIGHT, BG_WIDTH, BG_HEIGHT);
        graphics.blit(TEXTURE, baseX - BG_WIDTH, baseY,                0, 0, BG_WIDTH, BG_HEIGHT, BG_WIDTH, BG_HEIGHT);
        graphics.blit(TEXTURE, baseX,            baseY - 220, 0, 0, BG_WIDTH, BG_HEIGHT, BG_WIDTH, BG_HEIGHT);
        graphics.blit(TEXTURE, baseX - BG_WIDTH, baseY - 220, 0, 0, BG_WIDTH, BG_HEIGHT, BG_WIDTH, BG_HEIGHT);

        graphics.disableScissor();

        graphics.blit(new ResourceLocation(ChangedCreature.MODID, "textures/gui/ability/ability_gui_1.png"),
                this.panelX - FRAME_OFFSET, this.panelY - FRAME_OFFSET,
                0, 0, FRAME_WIDTH, FRAME_HEIGHT, FRAME_WIDTH, FRAME_HEIGHT);

        Component title = Component.literal("能力");
        int titleWidth = this.font.width(title);
        graphics.drawString(this.font, title,
                this.panelX + (PANEL_WIDTH - titleWidth) / 2,
                this.panelY + 15, 0xFFFFFF, false);

        graphics.drawString(this.font,
                Component.literal("Check Button awa to test"),
                this.panelX + 15, this.panelY + 40, 0xAAAAAA, false);

        for (int i = 0; i < ABILITY_LIST.length; i++) {
            RegistryObject<AbstractAbility> obj = ABILITY_LIST[i];
            AbstractAbility ability = obj.get();
            ResourceLocation id = obj.getId();

            int rowY = this.panelY + ROW_START_Y + i * ROW_HEIGHT;
            int rowX = this.panelX + ROW_START_X;
            String text = getAbilityText(obj, ability);

            int imgWidth = IMG_WIDTH;
            int imgHeight = IMG_HEIGHT;

            boolean rowHovered = mouseX >= rowX && mouseX <= rowX + imgWidth
                    && mouseY >= rowY && mouseY <= rowY + imgHeight;

            if (rowHovered) {
                graphics.fill(rowX - 2, rowY - 2,
                        rowX + imgWidth + 2, rowY + imgHeight + 2, HOVER_BG_COLOR);
            }

            if (ability.getAbilityTexture() == null) {
                graphics.blit(new ResourceLocation(ChangedCreature.MODID, "textures/gui/ability/latex_ability_0.png"),
                        rowX, rowY, 0, 0, imgWidth, imgHeight, imgWidth, imgHeight);
            } else {
                graphics.blit(ability.getAbilityTexture(), rowX, rowY, 0, 0, imgWidth, imgHeight, imgWidth, imgHeight);
            }

            // 描边
            int outlineColor = 0xFF000000;
            int draw_x = 3, draw_y = 3;
            graphics.drawString(this.font, text, rowX + draw_x - 1, rowY + draw_y, outlineColor, false);
            graphics.drawString(this.font, text, rowX + draw_x + 1, rowY + draw_y, outlineColor, false);
            graphics.drawString(this.font, text, rowX + draw_x, rowY + draw_y - 1, outlineColor, false);
            graphics.drawString(this.font, text, rowX + draw_x, rowY + draw_y + 1, outlineColor, false);

            graphics.drawString(this.font, text, rowX + draw_x, rowY + draw_y, ability.getDisplayColor(), false);

            if (rowHovered) {
                Player player = Minecraft.getInstance().player;
                int level = 0, exp = 0;
                if (player != null) {
                    PlayerAbilities pa = PlayerAbilitiesCapability.get(player);
                    level = pa.getLevel(id);
                    exp = pa.get(id).exp;
                }

                List<Component> tooltip = new ArrayList<>();
                tooltip.add(Component.literal("§b§l" + ability.getDisplayName()));
                ability.appendHoverText(tooltip, player, level);
                tooltip.add(Component.literal("§7Level: §f" + level + " §7/ §f" + ability.getMaxLevel()));
                graphics.renderComponentTooltip(this.font, tooltip, mouseX, mouseY);
            }
        }

        super.render(graphics, mouseX, mouseY, partialTick);
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