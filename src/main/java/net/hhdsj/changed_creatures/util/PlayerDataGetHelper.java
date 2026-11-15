package net.hhdsj.changed_creatures.util;

import net.hhdsj.changed_creatures.network.ChangedCreaturesModVariables;
import net.minecraft.world.entity.player.Player;

public class PlayerDataGetHelper {
    public static ChangedCreaturesModVariables.PlayerVariables get(Player player) {
        return player.getCapability(ChangedCreaturesModVariables.PLAYER_VARIABLES_CAPABILITY, null)
                .orElse(new ChangedCreaturesModVariables.PlayerVariables());
    }

    public static boolean GetPlayerCanFly(Player player){
        ChangedCreaturesModVariables.PlayerVariables data = PlayerDataGetHelper.get(player);
        return data.Player_Can_Fly;
    }

    public static void SetPlayerCanFly(Player player,Boolean bool){
        ChangedCreaturesModVariables.PlayerVariables data = PlayerDataGetHelper.get(player);
        data.Player_Can_Fly = bool;
        data.syncPlayerVariables(player);
    }

    public static boolean GetPlayerCanGliding(Player player){
        ChangedCreaturesModVariables.PlayerVariables data = PlayerDataGetHelper.get(player);
        return data.Player_Can_Gliding;
    }

    public static void SetPlayerCanGliding(Player player,Boolean bool){
        ChangedCreaturesModVariables.PlayerVariables data = PlayerDataGetHelper.get(player);
        data.Player_Can_Gliding = bool;
        data.syncPlayerVariables(player);
    }

    public static boolean GetPlayerIsFly(Player player){
        ChangedCreaturesModVariables.PlayerVariables data = PlayerDataGetHelper.get(player);
        return data.Player_Is_Fly;
    }

    public static void SetPlayerIsFly(Player player,Boolean bool){
        ChangedCreaturesModVariables.PlayerVariables data = PlayerDataGetHelper.get(player);
        data.Player_Is_Fly = bool;
        data.syncPlayerVariables(player);
    }
}
