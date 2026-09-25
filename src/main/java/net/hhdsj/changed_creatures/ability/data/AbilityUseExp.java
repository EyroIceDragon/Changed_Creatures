package net.hhdsj.changed_creatures.ability.data;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.registries.RegistryObject;

public class AbilityUseExp {
    public static boolean enoughExp(Player player, AbstractAbility ability, int level) {
        if (ability == null) return false;
        int total = getPlayerTotalExperience(player);
        int exp = Math.round(ability.useExp(level+1));
        return total >= exp;
    }

    public static void useExp(Player player, AbstractAbility ability, int level) {
        if (ability == null) return;
        int total = getPlayerTotalExperience(player);
        int exp = Math.round(ability.useExp(level+1));
        if (total < exp) return;
        player.giveExperiencePoints(-exp);
    }

    public static int getPlayerTotalExperience(Player player) {
        int level = player.experienceLevel;
        float progress = player.experienceProgress;
        int expForLevel = getExperienceForLevel(level);
        int neededForNext = player.getXpNeededForNextLevel();
        int progressExp = Math.round(progress * neededForNext);
        return expForLevel + progressExp;
    }

    private static int getExperienceForLevel(int level) {
        if (level <= 16) {
            return level * level + 6 * level;
        } else if (level <= 31) {
            return (int) (2.5 * level * level - 40.5 * level + 360);
        } else {
            return (int) (4.5 * level * level - 162.5 * level + 2220);
        }
    }
}
