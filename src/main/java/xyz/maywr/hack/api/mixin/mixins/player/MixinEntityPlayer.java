package xyz.maywr.hack.api.mixin.mixins.player;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.maywr.hack.MaywrWare;
import xyz.maywr.hack.client.events.JumpEvent;

@Mixin(EntityPlayer.class)
public class MixinEntityPlayer {

    @Inject(method = "jump", at = @At("HEAD"), cancellable = true)
    public void onJump (CallbackInfo ci) {
        JumpEvent jumpEvent = new JumpEvent();
        MinecraftForge.EVENT_BUS.post(jumpEvent);
    }
}
