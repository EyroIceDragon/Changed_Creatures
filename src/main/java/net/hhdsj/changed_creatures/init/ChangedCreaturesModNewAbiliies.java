package net.hhdsj.changed_creatures.init;

import net.hhdsj.changed_creatures.ChangedCreature;
import net.hhdsj.changed_creatures.ability.DamageResistanceAbility;
import net.hhdsj.changed_creatures.ability.data.AbstractAbility;
import net.hhdsj.changed_creatures.ability.ElectricResistanceAbility;
import net.hhdsj.changed_creatures.ability.FlyAbility;
import net.hhdsj.changed_creatures.ability.HypnotizeAbility;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ChangedCreaturesModNewAbiliies {

    public static final DeferredRegister<AbstractAbility> ABILITIES =
            DeferredRegister.create(ChangedCreatureRegistry.ABILITY,
                    ChangedCreature.MODID);

    /** 每个能力一个 RegistryObject */
    public static final RegistryObject<AbstractAbility> FLY =
            ABILITIES.register("fly", FlyAbility::new);

    public static final RegistryObject<AbstractAbility> ELECTRIC_RESISTANCE =
            ABILITIES.register("electric_resistance", ElectricResistanceAbility::new);

    public static final RegistryObject<AbstractAbility> HYPNOSIE =
            ABILITIES.register("hypnosis", HypnotizeAbility::new);

    public static final RegistryObject<AbstractAbility> DAMAGE_RESISTANCE =
            ABILITIES.register("damage_resistance", DamageResistanceAbility::new);

    /** 在主类里调用，挂到 mod 事件总线 */
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