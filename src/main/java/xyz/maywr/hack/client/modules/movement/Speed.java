package xyz.maywr.hack.client.modules.movement;

import xyz.maywr.hack.client.modules.Module;
import xyz.maywr.hack.client.modules.ModuleManifest;

@ModuleManifest(name = "Speed", listen = false, category = Module.Category.MOVEMENT, color = 0xAE85DE)
public class Speed extends Module {

    @Override
    public void onEnable() {
        setSuffix("Strafe");
    }

}
