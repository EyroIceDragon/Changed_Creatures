package net.hhdsj.changed_creatures.event;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

// 翅膀渲染状态清理仅存在于客户端，逻辑放在独立的、带 Dist.CLIENT 限制的订阅类中，
// 避免服务端专用环境下加载任何客户端类（RuntimeDistCleaner）。
@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class ClientWingCleanupHandler {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (player.level().isClientSide && player.tickCount % 20 == 0) {
            // 预留：客户端翅膀渲染状态清理钩子
        }
    }
}
