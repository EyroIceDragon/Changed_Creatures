package net.hhdsj.changed_creatures.event;

import net.hhdsj.changed_creatures.init.ChangedCreatureModEntities;
import net.hhdsj.changed_creatures.network.ChangedCreaturesModVariables;
import net.hhdsj.changed_creatures.util.PlayerDataGetHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber
public class CrystalJellyAttackHandler {

    private static int tickCounter = 0;
    private static final int CHECK_INTERVAL = 10;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (event.player.level().isClientSide) return;

        tickCounter++;
        if (tickCounter % CHECK_INTERVAL != 0) return;

        Vec3 center = event.player.position();
        double halfSize = 10.0;
        AABB box = AABB.ofSize(center, halfSize * 2, halfSize * 2, halfSize * 2);

        List<LivingEntity> entities = event.player.level().getEntitiesOfClass(
                LivingEntity.class,
                box,
                entity -> entity != event.player  // 排除玩家自己
        );

        for (LivingEntity entity : entities) {
            double distance = event.player.distanceTo(entity);
            if (entity.getType() == ChangedCreatureModEntities.LATEX_CRYSTAL_JELLY.get()) {
                onCrystalJellyAttackPlayer(event.player);
            }
        }
    }

    private static void onCrystalJellyAttackPlayer(Player player) {
        ChangedCreaturesModVariables.PlayerVariables data = PlayerDataGetHelper.get(player);

        if (!data.Crystal_Jelly_Infection) {
            data.Crystal_Jelly_Infection = true;
        }
    }
}