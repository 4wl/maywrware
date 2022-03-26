package xyz.maywr.hack.client.command.commands;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import xyz.maywr.hack.api.util.MessageUtil;
import xyz.maywr.hack.api.util.Timer;
import xyz.maywr.hack.client.command.Command;
import xyz.maywr.hack.client.command.CommandManifest;

@CommandManifest(label = "timer", aliases = "t")
public class TimerCommand extends Command {

    Timer timer;
    @Override
    public void execute(String[] args) {
        timer = new Timer();
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onTick (TickEvent.ClientTickEvent event) {
        if (timer == null) return;
        if (timer.hasReached(1000)) MessageUtil.sendClientMessage("has", 11111);
    }
}
