package xyz.maywr.hack.client.modules.misc;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.gui.Gui;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
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
    private EntityOtherPlayerMP fakeplayer = null;
    private ResourceLocation loc = null;

    @Override
    public void onEnable() {
        if (mc.player == null) return;

        String name = nameSetting.getValueAsString();
        fakeplayer = new EntityOtherPlayerMP(mc.world, new GameProfile(UUID.fromString("cc72ff00-a113-48f4-be18-2dda8db52355"), name));
        fakeplayer.copyLocationAndAnglesFrom(mc.player);
        fakeplayer.inventory = mc.player.inventory;
        loc = fakeplayer.getLocationSkin();
        mc.world.spawnEntity(fakeplayer);
    }

    @Override
    public void onDisable() {
        if (fakeplayer != null)
        mc.world.removeEntity(fakeplayer);
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

    @SubscribeEvent
    public void onRender2D (RenderGameOverlayEvent event) {
        if (loc == null) return;
        System.out.println(loc.getPath());
        Gui.drawScaledCustomSizeModalRect(100, 100, 50, 50, 50, 50, 50, 50,50, 50);
    }
}
