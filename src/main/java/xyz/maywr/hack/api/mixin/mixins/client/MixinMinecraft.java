package xyz.maywr.hack.api.mixin.mixins.client;

import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.crash.CrashReport;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Shadow;
import xyz.maywr.hack.MaywrWare;
import xyz.maywr.hack.api.mixin.accessors.IMinecraft;
import xyz.maywr.hack.client.events.KeyEvent;
import xyz.maywr.hack.client.gui.mainmenu.CustomMainMenu;
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

import javax.annotation.Nullable;

@Mixin(value = Minecraft.class, priority = 1001)
public abstract class MixinMinecraft implements IMinecraft {

    @Shadow public abstract void setConnectedToRealms(boolean isConnected);

    @Shadow public GuiIngame ingameGUI;

    @Shadow private static Minecraft instance;

    @Shadow public abstract void displayGuiScreen(@Nullable GuiScreen guiScreenIn);

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

    @Inject(method = "crashed", at = @At("HEAD"))
    public void crashed (CrashReport report, CallbackInfo ci) {
        MaywrWare.configManager.saveConfig(MaywrWare.configManager.config.replaceFirst("maywrware/", ""));
        MaywrWare.friendManager.unload();
    }

    @Inject(method = "shutdown", at = @At("HEAD"))
    public void shutdown (CallbackInfo ci) {
        MaywrWare.configManager.saveConfig(MaywrWare.configManager.config.replaceFirst("maywrware/", ""));
        MaywrWare.friendManager.unload();
    }

}
