package xyz.maywr.hack.api.mixin.mixins.client;

import net.minecraftforge.common.MinecraftForge;
import xyz.maywr.hack.MaywrWare;
import xyz.maywr.hack.api.mixin.accessors.IMinecraft;
import xyz.maywr.hack.client.events.KeyEvent;
import xyz.maywr.hack.client.modules.Module;
import xyz.maywr.hack.client.modules.client.MiddleClick;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Minecraft.class, priority = 1001)
public abstract class MixinMinecraft implements IMinecraft {

    @Override @Accessor
    public abstract void setRightClickDelayTimer(int delay);

    @Inject(method = "runTickKeyboard", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;getEventKey()I", ordinal = 0, shift = At.Shift.BEFORE))
    private void onKeyboard(CallbackInfo callbackInfo) {
        if (Keyboard.getEventKeyState()) {
            for (final Module m : MaywrWare.moduleManager.getModules()) {
                if (m.getKey() == Keyboard.getEventKey()) {
                    m.toggle();
                }
            }
            MinecraftForge.EVENT_BUS.post(new KeyEvent(Keyboard.getEventKey()));
        }
    }

    @Inject(method = "runTickMouse", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getEventButton()I", ordinal = 0, shift = At.Shift.BEFORE))
    private void mouseClick(CallbackInfo ci) {
        if (Mouse.getEventButtonState()) {
            MiddleClick.getInstance().run(Mouse.getEventButton());
        }
    }


    @Inject(method = "init", at = @At("RETURN"))
    public void init(CallbackInfo ci) {
        MaywrWare.INSTANCE.init();
    }

}
