package net.hhdsj.changed_creatures.event;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.init.ChangedCreatureModMobEffects;
import net.hhdsj.changed_creatures.init.ChangedCreatureModTags;
import net.hhdsj.changed_creatures.init.ChangedCreatureModTransfurVariants;
import net.ltxprogrammer.changed.entity.TransfurCause;
import net.ltxprogrammer.changed.entity.TransfurContext;
import net.ltxprogrammer.changed.entity.variant.TransfurVariant;
import net.ltxprogrammer.changed.init.ChangedRegistry;
import net.ltxprogrammer.changed.process.ProcessTransfur;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = "changed_creatures")
public class PlayerDeathHandler {

    private static final Random RANDOM = new Random();

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (player.level().isClientSide) return;
        if (ProcessTransfur.getPlayerTransfurVariant(player) != null) return;
        if (!player.hasEffect(ChangedCreatureModMobEffects.SLOWINFECTION.get())) return;

        handleSlowInfectionDeath(player, event);
    }

    private static void handleSlowInfectionDeath(Player player, LivingDeathEvent event) {
        TransfurVariant<?> selectedVariant = getRandomVariantFromTag();

        if (selectedVariant == null) {
            ChangedCreature.LOGGER.warn("No variants found in tag, using default");
            selectedVariant = ChangedCreatureModTransfurVariants.LATEX_DARK_PURPLE_DRAGON.get();
        }

        ProcessTransfur.setPlayerTransfurVariant(
                player,
                selectedVariant,
                TransfurContext.hazard(TransfurCause.GRAB_REPLICATE),
                1.0f
        );

        event.setCanceled(true);
        player.setHealth(1.0f);
        player.removeEffect(ChangedCreatureModMobEffects.SLOWINFECTION.get());
    }

    private static TransfurVariant<?> getRandomVariantFromTag() {
        try {
            var registry = ChangedRegistry.TRANSFUR_VARIANT.get();
            if (registry == null) return null;

            // 直接获取所有注册的变体，然后筛选
            var allVariants = registry.getValues().stream()
                    .filter(variant -> variant.is(ChangedCreatureModTags.slowInfectionVariants()))
                    .toList();

            if (allVariants.isEmpty()) {
                ChangedCreature.LOGGER.warn("Tag {} is empty", ChangedCreatureModTags.slowInfectionVariants());
                return null;
            }

            return allVariants.get(RANDOM.nextInt(allVariants.size()));

        } catch (Exception e) {
            ChangedCreature.LOGGER.error("Failed to get random variant from tag", e);
            return null;
        }
    }
}