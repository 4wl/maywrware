package xyz.maywr.hack.client.modules.visual;


import xyz.maywr.hack.client.modules.Module;
import xyz.maywr.hack.client.modules.ModuleManifest;

@ModuleManifest (label = "Brightness", category = Module.Category.VISUAL)
public class Gamma extends Module {

    @Override
    public void onTick() {
    mc.gameSettings.gammaSetting = 1000;
    }
}
