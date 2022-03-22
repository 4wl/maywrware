package xyz.maywr.hack.client.modules.misc;

import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.maywr.hack.api.mixin.mixins.network.AccessorCPacketChatMessage;
import xyz.maywr.hack.api.property.Setting;
import xyz.maywr.hack.client.events.PacketEvent;
import xyz.maywr.hack.client.modules.Module;
import xyz.maywr.hack.client.modules.ModuleManifest;

import java.util.Arrays;
import java.util.List;

@ModuleManifest(name = "ChatSuffix", category = Module.Category.MISC)
public class ChatSuffix extends Module {

    public final Setting<Boolean> greenChat = register(new Setting<>("Green Chat", false));
    List<String> prefixies = Arrays.asList(
        "/",
        ".",
        "$",
        "!",
        "#");


    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send event) {
        if (event.getPacket() instanceof CPacketChatMessage) {
            AccessorCPacketChatMessage packet = (AccessorCPacketChatMessage) event.getPacket();
            for (String prefix : prefixies) {
                if (packet.getMessage().startsWith(prefix)) return;
            }

            packet.setMessage(greenChat.getValue() ? "> " + ((CPacketChatMessage) event.getPacket()).getMessage()+ " | ｍａｙｗｒｗａｒｅ" : "" + ((CPacketChatMessage) event.getPacket()).getMessage()+ " | ｍａｙｗｒｗａｒｅ");
        }
    }
}
