package xyz.maywr.hack.client.modules.hud;

import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.maywr.hack.MaywrWare;
import xyz.maywr.hack.api.property.Setting;
import xyz.maywr.hack.api.util.render.RenderUtil;
import xyz.maywr.hack.client.modules.Module;
import xyz.maywr.hack.client.modules.ModuleManifest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@ModuleManifest(name = "ModuleList", category = Module.Category.HUD)
public class ModuleList extends Module {

    private final Setting<Mode> mode = register(new Setting<>("Rendering", Mode.UP));
    private final Setting<Float> x = register(new Setting<>("X", 5F, 1F, 960F));
    private final Setting<Float> y = register(new Setting<>("Y", 5F, 1F, 540F));

    @SubscribeEvent
    public void onRender2D (RenderGameOverlayEvent event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            List<String> enabledModules = new ArrayList<>();
            MaywrWare.moduleManager.getModules().forEach(module -> {
                if (module.isEnabled()) enabledModules.add(module.getName());
            });

            enabledModules.sort(Comparator.comparing(MaywrWare.fontManager::getStringWidth));
            Collections.reverse(enabledModules);

            float X = x.getValue() , Y = y.getValue();
            float offset = 1f;
            for (String moduleName : enabledModules) {
                if (moduleName.equals("ClickGUI")) continue;
                MaywrWare.fontManager.drawString(moduleName, X - MaywrWare.fontManager.getStringWidth(moduleName), Y, RenderUtil.generateRainbowFadingColor(offset, true));
                if (mode.getValue() == Mode.UP) {
                    offset += 0.5f;
                } else if (mode.getValue() == Mode.DOWN) {
                    offset -= 0.5f;
                }
                    Y += MaywrWare.fontManager.getFontHeight();
                }
            }
        }

    public enum Mode {
        UP, DOWN;
    }

}
