package net.hhdsj.changed_creatures.event;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.item.HidePlayerPartArmor;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = ChangedCreature.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientArmorRenderHandler {

    @SubscribeEvent
    public static void onRenderPlayerPre(RenderPlayerEvent.Pre event) {
        if (!(event.getEntity() instanceof AbstractClientPlayer player)) return;

        PlayerModel<AbstractClientPlayer> model = event.getRenderer().getModel();

        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.getType() != EquipmentSlot.Type.ARMOR) continue;

            ItemStack stack = player.getItemBySlot(slot);
            if (stack.getItem() instanceof HidePlayerPartArmor armor
                    && armor.shouldHidePlayerModel(slot)) {
                hideModelPart(model, slot);
            }
        }
    }

    private static void hideModelPart(PlayerModel<?> model, EquipmentSlot slot) {
        switch (slot) {
            case HEAD -> {
                model.head.visible = false;
                model.hat.visible = false;
            }
            case CHEST -> {
                model.body.visible = false;
                model.jacket.visible = false;
                model.rightArm.visible = false;
                model.leftArm.visible = false;
                model.rightSleeve.visible = false;
                model.leftSleeve.visible = false;
            }
            case LEGS -> {
                //model.rightLeg.visible = false;
                //model.leftLeg.visible = false;
                model.rightPants.visible = false;
                model.leftPants.visible = false;
            }
            case FEET -> {
                //model.rightLeg.visible = false;
                //model.leftLeg.visible = false;
            }
            default -> { }
        }
    }
}