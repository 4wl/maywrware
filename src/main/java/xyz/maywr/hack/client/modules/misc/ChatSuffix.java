package xyz.maywr.hack.client.modules.misc;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.maywr.hack.MaywrWare;
import xyz.maywr.hack.api.mixin.mixins.network.AccessorCPacketChatMessage;
import xyz.maywr.hack.client.events.PacketEvent;
import xyz.maywr.hack.client.modules.Module;
import xyz.maywr.hack.client.modules.ModuleManifest;
import net.minecraft.network.play.client.CPacketChatMessage;

@ModuleManifest(label = "ChatSuffix", category = Module.Category.MISC)
public class ChatSuffix extends Module {

    String[] prefixies = {
        "/",
        ".",
        "$",
        "!",
        "#"
    };

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send event) {
        if (event.getPacket() instanceof CPacketChatMessage) {
            AccessorCPacketChatMessage packet = (AccessorCPacketChatMessage) event.getPacket();
            for (String prefix : prefixies) {
                if (packet.getMessage().startsWith(prefix)) return;
            }

            packet.setMessage(((CPacketChatMessage) event.getPacket()).getMessage() + " | " + MaywrWare.NAME + " " + MaywrWare.VERSION);
        }
    }
}