package xyz.maywr.hack.client.modules.visual;

import net.minecraft.network.play.server.SPacketUpdateBossInfo;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.maywr.hack.api.property.Setting;
import xyz.maywr.hack.client.events.PacketEvent;
import xyz.maywr.hack.client.modules.Module;
import xyz.maywr.hack.client.modules.ModuleManifest;

@ModuleManifest(name = "NoRender", category = Module.Category.VISUAL, description = "cancels some rendering shit")
public class NoRender extends Module {

    public static NoRender INSTANCE;

    public NoRender() {
        INSTANCE = this;
    }

    public final Setting<Boolean> armor = register(new Setting<>("Armor", false));
    public final Setting<Boolean> hurtCamera = register(new Setting<>("HurtCam", false));
    public final Setting<Boolean> bossbar = register(new Setting<>("BossBar", false));
    public final Setting<Boolean> weather = register(new Setting<>("Weather", false));
    public final Setting<Boolean> fireOverlay = register(new Setting<>("Fire Overlay", false));

    @SubscribeEvent
    public void onPacket(PacketEvent.Receive event) {
        if (!bossbar.getValue()) return;
        if (event.getPacket() instanceof SPacketUpdateBossInfo) {
            event.setCanceled(true);
        }
    }

    @Override
    public void onTick() {
        if (weather.getValue()) {
            mc.world.setRainStrength(0f);
        }
    }
}
