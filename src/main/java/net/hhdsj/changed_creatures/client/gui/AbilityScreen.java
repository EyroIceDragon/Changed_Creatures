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
    private static final int PANEL_HEIGHT = 220;

    private static final int ROW_START_X = 15;
    private static final int ROW_START_Y = 70;
    private static final int ROW_HEIGHT = 30;
    private static final int BTN_SIZE = 14;
    private static final int BTN_GAP = 6;
    private static final int BTN_SPACING = 2;

    // 图片尺寸
    private static final int IMG_WIDTH = 114;
    private static final int IMG_HEIGHT = 26;

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
            int btnX = this.panelX + ROW_START_X + IMG_WIDTH + BTN_GAP;
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
                .bounds(btnX + BTN_SIZE + BTN_SPACING, btnY, BTN_SIZE, BTN_SIZE)
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

        graphics.fill(this.panelX, this.panelY,
                this.panelX + PANEL_WIDTH, this.panelY + PANEL_HEIGHT, 0xFF2B2B2B);

        // 边框
        int borderColor = 0xFF00AAFF;
        graphics.fill(this.panelX, this.panelY,
                this.panelX + PANEL_WIDTH, this.panelY + 1, borderColor);
        graphics.fill(this.panelX, this.panelY + PANEL_HEIGHT - 1,
                this.panelX + PANEL_WIDTH, this.panelY + PANEL_HEIGHT, borderColor);
        graphics.fill(this.panelX, this.panelY, this.panelX + 1,
                this.panelY + PANEL_HEIGHT, borderColor);
        graphics.fill(this.panelX + PANEL_WIDTH - 1, this.panelY,
                this.panelX + PANEL_WIDTH, this.panelY + PANEL_HEIGHT, borderColor);

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

            //如果可以获取到技能的图片那么就使用技能的图片.
            if (ability.getAbilityTexture() == null) {
                graphics.blit(new ResourceLocation(ChangedCreature.MODID, "textures/gui/ability/latex_ability_0.png"),
                        rowX, rowY, 0, 0, imgWidth, imgHeight, imgWidth, imgHeight);
            }else{
                graphics.blit(ability.getAbilityTexture(), rowX, rowY, 0, 0, imgWidth, imgHeight, imgWidth, imgHeight);
            }

            graphics.drawString(this.font, text, rowX, rowY + 3,
                    ability.getDisplayColor(), false);

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
                tooltip.add(Component.literal("§7等级: §f" + level + " §7/ §f" + ability.getMaxLevel()));
                graphics.renderComponentTooltip(this.font, tooltip, mouseX, mouseY);
            }
        }

        super.render(graphics, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}