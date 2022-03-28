package xyz.maywr.hack.api.mixin.mixins.render;

import net.minecraft.client.renderer.ItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.maywr.hack.client.modules.visual.NoRender;

@Mixin(ItemRenderer.class)
public class MixinItemRenderer {

    @Inject(method = "renderFireInFirstPerson", at = @At("HEAD"), cancellable = true)
    public void renderFireInFirstPerson(CallbackInfo ci) {
        if (NoRender.INSTANCE.fireOverlay.getValue() && NoRender.INSTANCE.isEnabled()) {
            ci.cancel();
        }
    }

}
