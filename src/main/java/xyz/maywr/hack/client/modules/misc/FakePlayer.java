package xyz.maywr.hack.client.modules.misc;

import com.mojang.authlib.GameProfile;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.maywr.hack.api.property.Setting;
import xyz.maywr.hack.client.events.PacketEvent;
import xyz.maywr.hack.client.modules.Module;
import xyz.maywr.hack.client.modules.ModuleManifest;
import net.minecraft.client.entity.EntityOtherPlayerMP;

import java.util.UUID;

@ModuleManifest(label = "FakePlayer", listen = false, category = Module.Category.MISC, color = 0xff00ff00)
public class FakePlayer extends Module {

    private final Setting<String> nameSetting = register(new Setting<>("Name", "maywr"));
    private EntityOtherPlayerMP faleplayer = null;

    @Override
    public void onEnable() {
        if (mc.player == null) return;

        String name = nameSetting.getValueAsString();
        faleplayer = new EntityOtherPlayerMP(mc.world, new GameProfile(UUID.fromString("cc72ff00-a113-48f4-be18-2dda8db52355"), name));
        faleplayer.copyLocationAndAnglesFrom(mc.player);
        faleplayer.inventory = mc.player.inventory;
        mc.world.spawnEntity(faleplayer);
    }

    @Override
    public void onDisable() {
        if (faleplayer != null)
        mc.world.removeEntity(faleplayer);
    }

    @SubscribeEvent
    public void onAttack (PacketEvent.Send event) {
        if (event.getPacket() instanceof CPacketUseEntity) {
            CPacketUseEntity packet = ((CPacketUseEntity) event.getPacket());

            if (packet.getAction() == CPacketUseEntity.Action.ATTACK) {
                //TODO fakeplayer that takes damage
            }
        }

    }
}
