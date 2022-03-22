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

    private final Setting<Float> x = register(new Setting<>("X", 5F, 1F, 1920F));
    private final Setting<Float> y = register(new Setting<>("Y", 5F, 1F, 1080F));

    public  final Setting<Boolean> chromo = register(new Setting<>("Chromo", true));
    public  final Setting<Boolean> rainbow = register(new Setting<>("Rainbow", false));
    private final Setting<Integer> red = register(new Setting<>("Red", 255, 0, 255));
    private final Setting<Integer> green = register(new Setting<>("Green", 255, 0, 255));
    private final Setting<Integer> blue = register(new Setting<>("Blue", 255, 0, 255));

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
            float offset = 1f, rainbowInt = RenderUtil.generateRainbowFadingColor(offset, true);
            for (String moduleName : enabledModules) {
                //chromo
                if (chromo.getValue()) {
                    MaywrWare.fontManager.drawChromoShadowString(moduleName, X - MaywrWare.fontManager.getStringWidth(moduleName), Y);
                    Y += MaywrWare.fontManager.getFontHeight();
                } else if (rainbow.getValue()) {
                    //rainbow
                    MaywrWare.fontManager.drawString(moduleName, X - MaywrWare.fontManager.getStringWidth(moduleName), Y, (int) rainbowInt);
                    Y += MaywrWare.fontManager.getFontHeight();
                    offset += 0.5f;
                }

            }
        }
    }


}
