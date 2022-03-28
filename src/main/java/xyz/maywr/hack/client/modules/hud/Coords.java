package xyz.maywr.hack.client.modules.hud;

import net.minecraft.util.math.MathHelper;
import net.minecraft.world.WorldType;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.maywr.hack.MaywrWare;
import xyz.maywr.hack.api.property.Setting;
import xyz.maywr.hack.api.util.MathUtil;
import xyz.maywr.hack.client.modules.Module;
import xyz.maywr.hack.client.modules.ModuleManifest;

@ModuleManifest(name = "Coords", category = Module.Category.HUD, description = "shows ur coords on screen")
public class Coords extends Module {

    private final Setting<Boolean> direction = register(new Setting<>("Direction", true));
    private final Setting<Float> x = register(new Setting<>("X", 5F, 1F, 960F));
    private final Setting<Float> y = register(new Setting<>("Y", 5F, 1F, 540F));

    @SubscribeEvent
    public void onRender2D(RenderGameOverlayEvent event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.TEXT) return;
        String coords = "";
        switch (mc.player.world.provider.getDimensionType()) {
            case NETHER: coords = String.format("X:%s Y:%s Z:%s | OW: [X:%s Y:%s Z:%s]",
                    (int) mc.player.posX, (int) mc.player.posY, (int) mc.player.posZ,
                    (int) mc.player.posX * 8, (int) mc.player.posY * 8, (int) mc.player.posZ * 8);
            break;
            default: coords = String.format("X:%s Y:%s Z:%s | NETHER: [X:%s Y:%s Z:%s]",
                    (int) mc.player.posX, (int) mc.player.posY, (int) mc.player.posZ,
                    (int) mc.player.posX / 8, (int) mc.player.posY / 8, (int) mc.player.posZ / 8);
            break;
        }
        MaywrWare.fontManager.drawChromoShadowString(coords, x.getValue(), y.getValue());

        if (direction.getValue()) {
            MaywrWare.fontManager.drawChromoShadowString(String.format("%s %s", getFacing(), getTowards()), x.getValue(), y.getValue() - MaywrWare.fontManager.getFontHeight());
        }

    }

    private String getFacing() {
        switch (MathHelper.floor((double) (mc.player.rotationYaw * 8.0F / 360.0F) + 0.5D) & 7) {
            case 0:
                return "South";
            case 1:
                return "South West";
            case 2:
                return "West";
            case 3:
                return "North West";
            case 4:
                return "North";
            case 5:
                return "North East";
            case 6:
                return "East";
            case 7:
                return "South East";
        }
        return "Invalid";
    }

    private String getTowards() {
        switch (MathHelper.floor((double) (mc.player.rotationYaw * 8.0F / 360.0F) + 0.5D) & 7) {
            case 0:
                return "+Z";
            case 1:
                return "-X +Z";
            case 2:
                return "-X";
            case 3:
                return "-X -Z";
            case 4:
                return "-Z";
            case 5:
                return "+X -Z";
            case 6:
                return "+X";
            case 7:
                return "+X +Z";
        }
        return "Invalid";
    }

}
