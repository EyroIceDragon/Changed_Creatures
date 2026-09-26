package net.hhdsj.changed_creatures.ability.data;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.registries.RegistryObject;

public class AbilityUseExp {
    public static boolean enoughExp(Player player, AbstractAbility ability, int level) {
        if (ability == null) return false;
        PlayerAbilities abilities = PlayerAbilitiesCapability.get(player);
        return abilities.enoughExp(ability, level);
    }

    public static void useExp(Player player, AbstractAbility ability, int level) {
        if (ability == null) return;
        PlayerAbilities abilities = PlayerAbilitiesCapability.get(player);
        abilities.consumeExp(ability, level);
    }

    public static int getPlayerExp(Player player) {
        if (player == null) return 0;
        return PlayerAbilitiesCapability.get(player).getPlayerExp();
    }
}
