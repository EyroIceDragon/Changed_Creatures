package net.hhdsj.changed_creatures.mixin.changed;

import net.hhdsj.changed_creatures.ability.data.AbstractAbility;
import net.hhdsj.changed_creatures.ability.data.PlayerAbilities;
import net.hhdsj.changed_creatures.ability.data.PlayerAbilitiesCapability;
import net.hhdsj.changed_creatures.init.ChangedCreaturesModNewAbiliies;
import net.ltxprogrammer.changed.entity.LivingEntityDataExtension;
import net.ltxprogrammer.changed.util.EntityUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.registries.RegistryObject;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityUtil.class)
public class EntityUtilMixin {

    @Inject(
            method = "setNoControlTicks",
            at = @At("HEAD"),
            cancellable = true,
            remap = false
    )
    private static void injectSetNoControlTicks(
            LivingEntity entity, int ticks, CallbackInfo ci) {
        if (!(entity instanceof LivingEntityDataExtension ext)) return;

        int modified = ticks;

        if (entity instanceof Player player) {
            RegistryObject<AbstractAbility> obj = ChangedCreaturesModNewAbiliies.ELECTRIC_RESISTANCE;
            PlayerAbilities abilities = PlayerAbilitiesCapability.get(player);
            ResourceLocation id = obj.getId();
            int level = abilities.getLevel(id);

            if (level > 0) {
                modified = ticks / level;
            }
        }

        ext.setNoControlTicks(modified);

        ci.cancel();
    }
}