package xyz.maywr.hack.api.mixin.mixins.render;

import xyz.maywr.hack.MaywrWare;
import xyz.maywr.hack.client.modules.Module;
import xyz.maywr.hack.client.modules.visual.Nametags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public abstract class MixinEntityRenderer {

    @Final
    @Shadow
    private Minecraft mc;

    @Inject(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/EntityRenderer;renderHand(FI)V"))
    public void onRender3D(int pass, float partialTicks, long finishTimeNano, CallbackInfo ci) {
        if (mc.player != null || mc.world != null) {
            for (final Module mod : MaywrWare.moduleManager.getModules()) {
                if (mod.isEnabled()) {
                    mod.onRender3D();
                }
            }
        }
    }

    @Inject(method = "drawNameplate", at = @At("HEAD"), cancellable = true)
    private static void renderName(FontRenderer fontRendererIn, String str, float x, float y, float z, int verticalShift, float viewerYaw, float viewerPitch, boolean isThirdPersonFrontal, boolean isSneaking, CallbackInfo ci) {
        if (Nametags.INSTANCE.isEnabled()) {
            ci.cancel();
        }
    }

}
