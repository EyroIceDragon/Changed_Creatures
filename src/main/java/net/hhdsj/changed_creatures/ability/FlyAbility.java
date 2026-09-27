package net.hhdsj.changed_creatures.ability;

import net.hhdsj.changed_creatures.ability.data.AbstractAbility;
import net.minecraft.network.chat.Component;

public class FlyAbility extends AbstractAbility {

    public static final int UNLOCK_LEVEL = 3;

    public FlyAbility() {
        super(Component.literal("飞行"), 0xFFFFFF, 10);
    }
}