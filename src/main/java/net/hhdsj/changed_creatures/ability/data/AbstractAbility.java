package net.hhdsj.changed_creatures.ability.data;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public abstract class AbstractAbility {

    private final String displayName;
    private final int displayColor;
    private final int maxLevel;

    public AbstractAbility(String displayName, int displayColor, int maxLevel) {
        this.displayName = displayName;
        this.displayColor = displayColor;
        this.maxLevel = maxLevel;
    }

    public String getDisplayName() { return displayName; }
    public int getDisplayColor() { return displayColor; }
    public int getMaxLevel() { return maxLevel; }
    public ResourceLocation getAbilityTexture() { return null; }

    public float useExp(int level) {return 10 + 15 * level * 0.5F;}
    public void onTick(Player player, int level) {}
    public void onHurt(Player player, LivingEntity attack, int level) {}
    public void onAttack(Player player, LivingEntity attack, int level) {}
    public void appendHoverText(List<Component> list, Player player, int level) {}
}