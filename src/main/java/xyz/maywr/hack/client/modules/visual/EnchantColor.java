package xyz.maywr.hack.client.modules.visual;

import xyz.maywr.hack.api.property.Setting;
import xyz.maywr.hack.api.util.render.RenderUtil;
import xyz.maywr.hack.client.modules.Module;
import xyz.maywr.hack.client.modules.ModuleManifest;

import java.awt.*;

@ModuleManifest(label = "EnchantColor", listen = false, category = Module.Category.VISUAL, color = 0xff9933)
public class EnchantColor extends Module {

    public  final Setting<Boolean> rainbow = register(new Setting<>("Rainbow", true));
    private final Setting<Integer> red = register(new Setting<>("Red", 255, 0, 255));
    private final Setting<Integer> green = register(new Setting<>("Green", 255, 0, 255));
    private final Setting<Integer> blue = register(new Setting<>("Blue", 255, 0, 255));


    public static EnchantColor INSTANCE;

    public EnchantColor() {
        INSTANCE = this;
    }

    public int getColor() {
        if(rainbow.getValue()) return RenderUtil.generateRainbowFadingColor(2, true);
        return new Color(red.getValue(), green.getValue(), blue.getValue()).getRGB();
    }

}
