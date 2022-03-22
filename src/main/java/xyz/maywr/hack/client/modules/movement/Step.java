package xyz.maywr.hack.client.modules.movement;

import xyz.maywr.hack.api.property.Setting;
import xyz.maywr.hack.client.modules.Module;
import xyz.maywr.hack.client.modules.ModuleManifest;

@ModuleManifest(name = "Step", listen = false, category = Module.Category.MOVEMENT, color = 0xffffff00)
public class Step extends Module {

    private final Setting<Boolean> placeHolder = register(new Setting("PlaceHolder", true));

    @Override
    public void onEnable() {
        if (!placeHolder.getValue())
        mc.player.stepHeight = 2;
    }

    @Override
    public void onDisable() {
        if (!placeHolder.getValue())
        mc.player.stepHeight = 0.6f;
    }

}
