package xyz.maywr.hack.api.mixin.mixins.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.maywr.hack.api.interfaces.Minecraftable;
import xyz.maywr.hack.client.gui.mainmenu.CustomMainMenu;

@Mixin(GuiMainMenu.class)
public class MixinMainMenu {

/*    @Inject(method = "initGui", at = @At("HEAD"), cancellable = true)
    public void initGui(CallbackInfo info) {
        Minecraft.getMinecraft().displayGuiScreen(new CustomMainMenu());
        info.cancel();
    }*/



}
