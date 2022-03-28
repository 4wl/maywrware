package xyz.maywr.hack.client.modules.visual;


import xyz.maywr.hack.api.property.Setting;
import xyz.maywr.hack.client.modules.Module;
import xyz.maywr.hack.client.modules.ModuleManifest;

@ModuleManifest (name = "Brightness", category = Module.Category.VISUAL)
public class Gamma extends Module {

    public final Setting<Boolean> fullBright = register(new Setting<>("FullBright", false));
    public final Setting<Integer> value = register(new Setting<>("Value", 100, 0, 100, visible -> !fullBright.getValue()));

    @Override
    public void onTick() {
        if (fullBright.getValue()) {
            mc.gameSettings.gammaSetting = 1337.228F;
        } else {
            mc.gameSettings.gammaSetting = (float) value.getValue() / 70;
        }
    }
}
