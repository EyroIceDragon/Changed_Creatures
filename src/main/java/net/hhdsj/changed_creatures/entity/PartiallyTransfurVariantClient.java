package net.hhdsj.changed_creatures.entity;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.network.KeyMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = ChangedCreature.MODID, value = Dist.CLIENT)
public class PartiallyTransfurVariantClient {

    private static final Map<UUID, Boolean> lastJumpStateMap = new HashMap<>();

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null || mc.screen != null) return;

        UUID playerId = player.getUUID();
        boolean currentJump = mc.options.keyJump.isDown();
        boolean lastState = lastJumpStateMap.getOrDefault(playerId, false);
        boolean pressed = currentJump && !lastState;

        PartiallyTransfurVariant.setClientJumpPressed(playerId, pressed);

        if (pressed) {
            ChangedCreature.PACKET_HANDLER.sendToServer(new KeyMessage(1, 0));
        }

        lastJumpStateMap.put(playerId, currentJump);
    }
}