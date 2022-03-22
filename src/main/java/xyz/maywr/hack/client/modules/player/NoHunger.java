package xyz.maywr.hack.client.modules.player;

import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.maywr.hack.api.mixin.accessors.ICPacketPlayer;
import xyz.maywr.hack.api.property.Setting;
import xyz.maywr.hack.client.events.PacketEvent;
import xyz.maywr.hack.client.modules.Module;
import xyz.maywr.hack.client.modules.ModuleManifest;

@ModuleManifest(name = "NoHunger", category = Module.Category.PLAYER)
public class NoHunger extends Module {

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send event) {
        if (event.getPacket() instanceof CPacketPlayer) {

            CPacketPlayer packet = (CPacketPlayer) event.getPacket();
            ICPacketPlayer shit = (ICPacketPlayer) packet;
            shit.setOnGround(mc.player.fallDistance >= 0.0f || mc.playerController.getIsHittingBlock());

        }
    }
}
