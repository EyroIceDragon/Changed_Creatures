package net.hhdsj.changed_creatures.abilities;

import net.hhdsj.changed_creatures.init.ChangedCreatureModTransfurVariants;
import net.ltxprogrammer.changed.ability.AbstractAbility;
import net.ltxprogrammer.changed.ability.AbstractAbilityInstance;
import net.ltxprogrammer.changed.ability.IAbstractChangedEntity;
import net.ltxprogrammer.changed.entity.TransfurCause;
import net.ltxprogrammer.changed.entity.TransfurContext;
import net.ltxprogrammer.changed.entity.variant.TransfurVariant;
import net.ltxprogrammer.changed.entity.variant.TransfurVariantInstance;
import net.ltxprogrammer.changed.init.ChangedSounds;
import net.ltxprogrammer.changed.process.ProcessTransfur;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;

public class AToBTurnAbilityInstance extends AbstractAbilityInstance {

    public AToBTurnAbilityInstance(AbstractAbility<?> ability, IAbstractChangedEntity entity) {
        super(ability, entity);
    }

    @Override
    public boolean canUse() {
        return true;
    }

    @Override
    public boolean canKeepUsing() {
        return true;
    }

    @Override
    public void startUsing() {
        if (entity.getEntity() instanceof Player player) {
            TransfurVariantInstance<?> currentInstance = ProcessTransfur.getPlayerTransfurVariant(player);
            if (currentInstance == null)
                return;

            TransfurVariant<?> current = currentInstance.getParent();
            TransfurVariant<?> target = determineNextVariant(current);

            ProcessTransfur.setPlayerTransfurVariant(
                    player,
                    target,
                    TransfurContext.hazard(TransfurCause.GRAB_REPLICATE),
                    1
            );

            player.level().playSound(null, player, ChangedSounds.TRANSFUR_BY_LATEX.get(), SoundSource.PLAYERS, 1, 1);
        }
    }

    private TransfurVariant<?> determineNextVariant(TransfurVariant<?> current) {
        if (current == ChangedCreatureModTransfurVariants.LATEX_DARK_PURPLE_DRAGON.get()) {
            return ChangedCreatureModTransfurVariants.LATEX_DARK_PURPLE_DRAGON_TAUR.get();
        } else {
            return ChangedCreatureModTransfurVariants.LATEX_DARK_PURPLE_DRAGON.get();
        }
    }

    @Override
    public void tick() {
    }

    @Override
    public void stopUsing() {
    }
}