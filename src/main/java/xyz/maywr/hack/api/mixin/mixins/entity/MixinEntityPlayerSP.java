package xyz.maywr.hack.api.mixin.mixins.entity;

import net.minecraftforge.common.MinecraftForge;
import xyz.maywr.hack.MaywrWare;
import xyz.maywr.hack.api.util.Timer;
import xyz.maywr.hack.client.events.UpdateEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayerSP.class)
public abstract class MixinEntityPlayerSP {

    @Shadow
    protected Minecraft mc;

    @Inject(method = "onUpdate", at = @At("RETURN"))
    public void onUpdatePost(CallbackInfo ci) {
        MinecraftForge.EVENT_BUS.post(new UpdateEvent());
    }

    private final Timer timer = new Timer();
}
