package net.hhdsj.changed_creatures.init;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.ability.*;
import net.hhdsj.changed_creatures.ability.data.AbstractAbility;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ChangedCreaturesModNewAbiliies {

    public static final DeferredRegister<AbstractAbility> ABILITIES =
            DeferredRegister.create(ChangedCreatureRegistry.ABILITY,
                    ChangedCreature.MODID);

    public static final RegistryObject<AbstractAbility> FLY =
            ABILITIES.register("fly", FlyAbility::new);

    public static final RegistryObject<AbstractAbility> ELECTRIC_RESISTANCE =
            ABILITIES.register("electric_resistance", ElectricResistanceAbility::new);

    public static final RegistryObject<AbstractAbility> HYPNOSIE =
            ABILITIES.register("hypnosis", HypnotizeAbility::new);

    public static final RegistryObject<AbstractAbility> DAMAGE_RESISTANCE =
            ABILITIES.register("damage_resistance", DamageResistanceAbility::new);

    public static final RegistryObject<AbstractAbility> ENRAGE =
            ABILITIES.register("enrage", EnrageAbility::new);

    public static final RegistryObject<AbstractAbility> POISON_RESISTANCE =
            ABILITIES.register("poison_resistance", PoisonResistanceAbility::new);


    public static void register(IEventBus modBus) {
        ABILITIES.register(modBus);
    }

    // ---------- 便捷方法 ----------
    public static ResourceLocation idOf(RegistryObject<AbstractAbility> obj) {
        return obj.getId();
    }

    public static AbstractAbility get(RegistryObject<AbstractAbility> obj) {
        return obj.get();
    }
}