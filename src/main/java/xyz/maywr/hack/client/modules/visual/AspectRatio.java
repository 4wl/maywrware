package xyz.maywr.hack.client.modules.visual;

import net.minecraftforge.common.MinecraftForge;
import xyz.maywr.hack.api.property.Setting;
import xyz.maywr.hack.client.modules.Module;
import xyz.maywr.hack.client.modules.ModuleManifest;

@ModuleManifest(name = "AspectRatio", category = Module.Category.VISUAL)
public class AspectRatio extends Module {

    public static AspectRatio INSTANCE;

    public AspectRatio() {
        INSTANCE = this;
    }

    public final Setting<Float> aspectRatio = register(new Setting<>("Ratio", 1.3F, 0.1F, 3.0F));

}
