package xyz.maywr.hack.client.modules.movement;

import xyz.maywr.hack.api.property.Setting;
import xyz.maywr.hack.client.modules.Module;
import xyz.maywr.hack.client.modules.ModuleManifest;

@ModuleManifest(name = "Sprint", category = Module.Category.MOVEMENT)
public class Sprint extends Module {

    private final Setting<Mode> mode = register(new Setting<>("Mode", Mode.FORWARD));
    private final Setting<Boolean> ignoreShift = register(new Setting<>("Ignore Shift", true));

    @Override
    public void onTick() {
        if (mc.gameSettings.keyBindSneak.isKeyDown() && !ignoreShift.getValue()) return;
        if (mode.getValue() == Mode.FORWARD) {
            if(mc.gameSettings.keyBindForward.isKeyDown()) mc.player.setSprinting(true);
        }
        if (mode.getValue() == Mode.EVERYWHERE) {
            if(mc.gameSettings.keyBindForward.isKeyDown() || mc.gameSettings.keyBindBack.isKeyDown() || mc.gameSettings.keyBindRight.isKeyDown() || mc.gameSettings.keyBindLeft.isKeyDown()) {
                mc.player.setSprinting(true);
            }
        }
    }

    public enum Mode {

        FORWARD,
        EVERYWHERE

    }
}
