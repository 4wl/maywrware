package xyz.maywr.hack.client.modules.client;

import xyz.maywr.hack.api.property.Setting;
import xyz.maywr.hack.api.util.MessageUtil;
import xyz.maywr.hack.client.modules.Module;
import xyz.maywr.hack.client.modules.ModuleManifest;

@ModuleManifest(name = "Font", category = Module.Category.CLIENT, description = "changes client's font")
public class Font extends Module {

    public static Font INSTANCE;

    public Font() {
        INSTANCE = this;
    }

    public final Setting<Boolean> antiAlias = register(new Setting<>("AntiAlias", true));
    public final Setting<Boolean> fractionalMetrics = register(new Setting<>("Fractional Metrics", true));
    public final Setting<Float> size = register(new Setting<>("Size", 18F, 12F, 22F));

    @Override
    public void onEnable() {
        MessageUtil.sendClientMessage("to change the font name do " + ClickGui.getInstance().prefix + "font <name>", false);
    }


}
