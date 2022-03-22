package xyz.maywr.hack.client.modules.visual;

import xyz.maywr.hack.client.modules.Module;
import xyz.maywr.hack.client.modules.ModuleManifest;

@ModuleManifest(name = "ShulkerPreview", listen = false, category = Module.Category.VISUAL, color = 0xcc9900)
public class ShulkerPreview extends Module {

    private static ShulkerPreview INSTANCE;

    public ShulkerPreview() {
        INSTANCE = this;
    }

    public static ShulkerPreview getInstance() {
        return INSTANCE;
    }

}
