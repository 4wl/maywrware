package xyz.maywr.hack.client.modules.movement;

import net.minecraft.util.MovementInput;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.maywr.hack.client.modules.Module;
import xyz.maywr.hack.client.modules.ModuleManifest;

@ModuleManifest(name = "NoSlow", category = Module.Category.MOVEMENT)
public class NoSlow extends Module {

    @SubscribeEvent
    public void onInput(final InputUpdateEvent event) {
        if (mc.player.isHandActive() && !mc.player.isRiding()) {
            final MovementInput movementInput = event.getMovementInput();
            movementInput.moveStrafe *= 5.0f;
            final MovementInput movementInput2 = event.getMovementInput();
            movementInput2.moveForward *= 5.0f;
        }
    }
}
