package net.hhdsj.changed_creatures.ability;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.ability.data.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;

public class PeacefulEntityAbility extends AbstractAbility {
    public PeacefulEntityAbility() {
        super(Component.literal("Peaceful"), 0xd0ffca, 2);
    }

    @Override
    public float useExp(int level) {
        return 50 + 80 * level * 0.5F;
    }

}

@Mod.EventBusSubscriber(modid = ChangedCreature.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
class MobTargetHandler {

    @SubscribeEvent
    public static void onLivingChangeTarget(LivingChangeTargetEvent event) {
        LivingEntity entity = event.getEntity();
        LivingEntity newTarget = event.getNewTarget();

        // 只有当目标是玩家时才处理
        if (newTarget instanceof Player player) {
            PlayerAbilities abilities = PlayerAbilitiesCapability.get(player);

            // 遍历玩家拥有的所有能力
            for (Map.Entry<ResourceLocation, AbilityData> entry : abilities.getAll().entrySet()) {
                var id = entry.getKey();
                AbilityData data = entry.getValue();
                AbstractAbility ability = AbilityRegistry.get(id);

                // 检查是否是和平能力
                if (ability instanceof PeacefulEntityAbility) {
                    int level = data.level;
                    // 使用玩家的随机源
                    int roll = player.getRandom().nextInt(100);
                    if (roll < level * 20) {
                        event.setNewTarget(null); // 清除目标
                        break; // 跳出循环
                    }
                }
            }
        }
    }
}