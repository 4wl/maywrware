package xyz.maywr.hack.api.mixin.mixins.client;

import xyz.maywr.hack.client.modules.visual.TimeChanger;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(World.class)
public class MixinWorld {

    @Inject(method = "getWorldTime", at = @At("HEAD"), cancellable = true)
    public void getWorldTime(CallbackInfoReturnable<Long> cir) {
        if (TimeChanger.INSTANCE.isEnabled()) {
            cir.cancel();
            cir.setReturnValue((long)TimeChanger.INSTANCE.timeSetting.getValue());
        }
    }

}
