package xyz.maywr.hack.client.modules.player;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import xyz.maywr.hack.api.property.Setting;
import xyz.maywr.hack.api.util.MessageUtil;
import xyz.maywr.hack.client.events.JumpEvent;
import xyz.maywr.hack.client.modules.Module;
import xyz.maywr.hack.client.modules.ModuleManifest;

//nice legit module!!!!
@ModuleManifest (label = "BuildHelper", category = Module.Category.PLAYER)
public class BuildHelper extends Module {

    private final Setting<Boolean> jumpToggle = register(new Setting<>("Off on jump", false));

    @Override
    public void onTick () {
        BlockPos pos = new BlockPos(mc.player.posX, mc.player.posY - 1.0, mc.player.posZ);
        KeyBinding.setKeyBindState(mc.gameSettings.keyBindSneak.getKeyCode(), mc.world.getBlockState(pos).getBlock() == Blocks.AIR);
    }

    @SubscribeEvent
    public void onJump (JumpEvent event) {
        if(!jumpToggle.getValue()) return;
        MessageUtil.sendClientMessage("detected jump.. turnin", 228);
        this.disable();
    }
}
