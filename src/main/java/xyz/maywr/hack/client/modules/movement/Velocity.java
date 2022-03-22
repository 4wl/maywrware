package xyz.maywr.hack.client.modules.movement;

import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.network.play.server.SPacketExplosion;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.maywr.hack.client.events.PacketEvent;
import xyz.maywr.hack.client.modules.Module;
import xyz.maywr.hack.client.modules.ModuleManifest;

@ModuleManifest(name = "Velocity", category = Module.Category.MOVEMENT)
public class Velocity extends Module {

    @SubscribeEvent
    public void onPacket(final PacketEvent.Receive event) {
        if (mc.player == null || mc.world == null)
            return;
        if (event.getPacket() instanceof SPacketEntityVelocity
                && ((SPacketEntityVelocity) event.getPacket()).getEntityID() == mc.player.getEntityId()) {
            event.setCanceled(true);
        }
        if (event.getPacket() instanceof SPacketExplosion) {
            event.setCanceled(true);
        }
    }

    @Override
    public void onTick() {
        if (mc.player == null || mc.world == null) return;
        mc.player.entityCollisionReduction = 1.0F;
    }
}
