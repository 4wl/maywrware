package xyz.maywr.hack.client.modules.movement;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import xyz.maywr.hack.api.property.Setting;
import xyz.maywr.hack.client.modules.Module;
import xyz.maywr.hack.client.modules.ModuleManifest;

@ModuleManifest(label = "ReverseStep", category = Module.Category.MOVEMENT, color = 0xAE85DE)
public class ReverseStep extends Module {

    private final Setting<Integer> speed = register(new Setting<>("Speed", 10, 1, 20));

    @Override
    public void onTick() {

    if (mc.player.isInWater() || mc.player.isInLava() || mc.player.isOnLadder()) {
            return;
        }
        if (mc.player.onGround) {
            mc.player.motionY -= (float)speed.getValue() / 10;
        }
    }

}
