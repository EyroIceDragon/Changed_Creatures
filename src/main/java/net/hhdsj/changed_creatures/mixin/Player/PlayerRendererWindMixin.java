package net.hhdsj.changed_creatures.mixin.Player;

import net.hhdsj.changed_creatures.client.renderer.LatexWindRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerRenderer.class)
public abstract class PlayerRendererWindMixin {

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(EntityRendererProvider.Context context, boolean useSlimArms, CallbackInfo ci) {
        PlayerRenderer renderer = (PlayerRenderer) (Object) this;

        renderer.addLayer(new LatexWindRenderer(renderer, context.getModelSet()));
    }
}