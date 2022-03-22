package xyz.maywr.hack.client.modules.hud;

import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.maywr.hack.api.property.Setting;
import xyz.maywr.hack.api.util.render.RenderUtil;
import xyz.maywr.hack.client.modules.Module;
import xyz.maywr.hack.client.modules.ModuleManifest;

import java.awt.*;

@ModuleManifest(name = "TargetOverlay", category = Module.Category.HUD)
public class TargetOverlay extends Module {

    public final Setting<Mode> mode = register(new Setting<>("Mode", Mode.ATTACKING));
    public final Setting<Integer> x = register(new Setting<>("X", 50, 1, 1920));
    public final Setting<Integer> y = register(new Setting<>("Y", 50, 1, 1920));
    private EntityLivingBase current = null;

    @SubscribeEvent
    public void onRender2D (RenderGameOverlayEvent event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {

            switch (mode.getValue()) {
                case POINTED: current = (EntityLivingBase) mc.pointedEntity; break;
                case ATTACKING: {
                    //TODO
                } break;
                case CLOSEST: {
                    //TODO
                } break;
            }

            if (current == null || !(current instanceof EntityOtherPlayerMP)) return;

            GuiInventory.drawEntityOnScreen(x.getValue() + 15, y.getValue() + 55, 25, -30, 0, current);
            Gui.drawRect(x.getValue(), y.getValue(), x.getValue() + 140, y.getValue() + 70, new Color(80, 80, 80, 255).getRGB());
            RenderUtil.drawGradientSideways(x.getValue(), y.getValue(), x.getValue() + 140, y.getValue() + 5, RenderUtil.generateRainbowFadingColor(1, true), RenderUtil.generateRainbowFadingColor(3, true));
            //TODO do an actual target hud thing
        }
    }

    private enum Mode {
        CLOSEST,
        ATTACKING,
        POINTED
    }
}
