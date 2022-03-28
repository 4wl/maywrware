package xyz.maywr.hack.api.mixin.mixins.player;

import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.MovementInput;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.MovementInputFromOptions;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.*;
import xyz.maywr.hack.MaywrWare;
import xyz.maywr.hack.api.interfaces.Minecraftable;
import xyz.maywr.hack.client.gui.clickgui.TrollGui;
import xyz.maywr.hack.client.modules.misc.GuiMove;
import xyz.maywr.hack.client.modules.movement.Sprint;

@Mixin(value = MovementInputFromOptions.class, priority = 10000)
public class MixinMovementInput extends MovementInput implements Minecraftable {

    @Mutable
    @Shadow
    @Final
    private final GameSettings gameSettings;

    protected MixinMovementInput(GameSettings gameSettings) {
        this.gameSettings = gameSettings;
    }

    /**
     * @author popbob
     */

    @Overwrite
    public void updatePlayerMoveState() {
        this.moveStrafe = 0.0F;
        this.moveForward = 0.0F;
        if (isKeyHeld(this.gameSettings.keyBindForward)) {
            ++this.moveForward;
            this.forwardKeyDown = true;
            if (MaywrWare.moduleManager.getModuleByClass(Sprint.class).isEnabled()) mc.player.setSprinting(true);
            if (isKeyHeld(this.gameSettings.keyBindSprint)) mc.player.setSprinting(true);
        }
        else {
            this.forwardKeyDown = false;
        }
        if (isKeyHeld(this.gameSettings.keyBindBack)) {
            --this.moveForward;
            this.backKeyDown = true;
        }
        else {
            this.backKeyDown = false;
        }
        if (isKeyHeld(this.gameSettings.keyBindLeft)) {
            ++this.moveStrafe;
            this.leftKeyDown = true;
        }
        else {
            this.leftKeyDown = false;
        }
        if (isKeyHeld(this.gameSettings.keyBindRight)) {
            --this.moveStrafe;
            this.rightKeyDown = true;
        }
        else {
            this.rightKeyDown = false;
        }
        this.jump = isKeyHeld(this.gameSettings.keyBindJump);
        this.sneak = isKeyHeld(this.gameSettings.keyBindSneak);
        if (this.sneak) {
            this.moveStrafe = (float) ((double) this.moveStrafe * 0.3D);
            this.moveForward = (float) ((double) this.moveForward * 0.3D);
        }
    }

    public boolean isKeyHeld(KeyBinding keyBinding) {
        if (MaywrWare.moduleManager.getModuleByClass(GuiMove.class).isEnabled() && mc.currentScreen != null) {
            if (!TrollGui.isListeningForText) {
                if (mc.ingameGUI.getChatGUI().getChatOpen()) return keyBinding.isKeyDown();
                return Keyboard.isKeyDown(keyBinding.getKeyCode());
            }
        }
        return keyBinding.isKeyDown();
    }
}