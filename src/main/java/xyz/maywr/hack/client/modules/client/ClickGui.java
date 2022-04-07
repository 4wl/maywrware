package xyz.maywr.hack.client.modules.client;

import xyz.maywr.hack.api.property.Setting;
import xyz.maywr.hack.client.gui.clickgui.ClickGUI;
import xyz.maywr.hack.client.modules.Module;
import xyz.maywr.hack.client.modules.ModuleManifest;
import net.minecraft.client.settings.GameSettings;
import org.lwjgl.input.Keyboard;
import java.awt.*;

@ModuleManifest(name = "ClickGui", category = Module.Category.CLIENT, key = Keyboard.KEY_INSERT)
public class ClickGui extends Module {

    public final Setting<String> prefix = register(new Setting<>("Prefix", "."));

    public final Setting<Boolean> gradientHeader = register(new Setting<>("Gradient Header", true));
    public final Setting<Boolean> blur = register(new Setting<>("Blur", true));

    public final Setting<Boolean> customFov = register(new Setting<>("Custom Fov", false));
    public final Setting<Float> fov = register(new Setting<>("Fov", 150.0f, -180.0f, 180.0f));

    public final Setting<Integer> red = register(new Setting<>("Red", 255, 0, 255));
    public final Setting<Integer> green = register(new Setting<>("Green", 255, 0, 255));
    public final Setting<Integer> blue = register(new Setting<>("Blue", 255, 0, 255));

    public final Setting<Integer> hoverAlpha = register(new Setting<>("Hover Alpha", 60, 0, 255));
    public final Setting<Integer> enabledAlpha = register(new Setting<>( "Enabled Alpha", 60, 0, 255));

    public final Setting<Integer> categoryRed = register(new Setting<>("Category Red", 0, 0, 255));
    public final Setting<Integer> categoryGreen = register(new Setting<>("Category Green", 0, 0, 255));
    public final Setting<Integer> categoryBlue = register(new Setting<>("Category Blue", 0, 0, 255));
    public final Setting<Integer> categoryAlpha = register(new Setting<>("Category Alpha", 255, 0, 255));

    public final Setting<Integer> alpha = register(new Setting<>("Alpha", 255, 0, 255));

    private static ClickGui INSTANCE;

    public ClickGui() {
        INSTANCE = this;
    }

    public static ClickGui getInstance() {
        return INSTANCE;
    }

    public final int getColor(boolean hover) {
        return new Color(red.getValue(), green.getValue(), blue.getValue(), hover ? hoverAlpha.getValue() : enabledAlpha.getValue()).getRGB();
    }

    @Override
    public void onTick() {
    if (!(mc.currentScreen instanceof ClickGUI)) {
            setEnabled(false); //wthat the fuck is that hollow :sob:
        }

        if (customFov.getValue()) {
            mc.gameSettings.setOptionFloatValue(GameSettings.Options.FOV, fov.getValue());
        }
    }

    @Override
    public void onEnable() {
        if (mc.player != null) {
            mc.displayGuiScreen(new ClickGUI());
        }
    }

    @Override
    public void onDisable() {
        if (mc.currentScreen instanceof ClickGUI) {
            mc.displayGuiScreen(null);
        }
    }

    @Override
    public boolean shouldNotify() {
        return false;
    }
}
