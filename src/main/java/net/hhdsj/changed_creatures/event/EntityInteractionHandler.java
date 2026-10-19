package net.hhdsj.changed_creatures.event;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.init.ChangedCreatureModEntities;
import net.hhdsj.changed_creatures.init.ChangedCreatureModItems;
import net.ltxprogrammer.changed.init.ChangedDamageSources;
import net.ltxprogrammer.changed.init.ChangedEntities;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ChangedCreature.MODID)
public class EntityInteractionHandler {

    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        Player player = event.getEntity();
        Entity target = event.getTarget();
        InteractionHand hand = event.getHand();
        ItemStack itemInHand = player.getItemInHand(hand);

        if (player.level().isClientSide) {
            return;
        }

        if (!itemInHand.isEmpty()) {
            handleItemInHand(player, target, itemInHand, hand);
        }
    }

    private static void handleItemInHand(Player player, Entity target, ItemStack item, InteractionHand hand) {
        EntityType<?> target_type = target.getType();

        if (item.is(Items.LAPIS_LAZULI) && target_type == ChangedEntities.POOLTOY_WOLF.get()) {
            AddFreshEntity(player,target,item, ChangedCreatureModEntities.LATEX_ENKANG_DRAGON_SHARK.get());
        }
        if (item.is(Items.REDSTONE) && target_type == ChangedEntities.LATEX_GOLDEN_DRAGON.get()) {
            AddFreshEntity(player,target,item, ChangedCreatureModEntities.LATEX_YUE_XI_FOX_FEMALE.get());
        }
        if (item.is(ChangedCreatureModItems.Latex_Night_Owl_Goo_Item.get()) && (target_type == EntityType.VILLAGER || target_type == EntityType.PLAYER || target_type == EntityType.ZOMBIE || target_type == EntityType.ZOMBIE_VILLAGER || target_type == EntityType.ZOMBIFIED_PIGLIN)) {
            AddFreshEntity(player,target,item, ChangedCreatureModEntities.LATEX_NIGHT_OWL_DRAGON_BOSS.get());
        }
    }

    private static void AddFreshEntity(Player player,Entity target,ItemStack item, EntityType tf_target ){
        double x = target.getX();
        double y = target.getY();
        double z = target.getZ();

        float yRot = target.getYRot();
        float xRot = target.getXRot();
        if (target instanceof Player) {
            DamageSource source = ChangedDamageSources.ABSORB.source(
                    target.level().registryAccess()
            );
            target.hurt(source, 1000.0F);
        }else {
            target.discard();
        }

        Entity newEntity = tf_target.create(player.level());

        if (newEntity != null) {
            newEntity.setPos(x, y, z);
            newEntity.setYRot(yRot);
            newEntity.setXRot(xRot);
            player.level().addFreshEntity(newEntity);
            item.shrink(1);
        }
    }
}