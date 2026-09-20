package net.hhdsj.changed_creatures.ability;

import net.hhdsj.changed_creatures.ability.data.AbstractAbility;

public class FlyAbility extends AbstractAbility {

    public static final int UNLOCK_LEVEL = 3;

    public FlyAbility() {
        super("飞行", 0xFFFFFF, 10);
    }
}