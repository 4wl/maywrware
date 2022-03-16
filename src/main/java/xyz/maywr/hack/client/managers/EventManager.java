package xyz.maywr.hack.client.managers;

import xyz.maywr.hack.api.interfaces.Minecraftable;
import xyz.maywr.hack.api.util.Timer;
import xyz.maywr.hack.client.events.PacketEvent;
import xyz.maywr.hack.client.modules.combat.AutoCrystal;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class EventManager implements Minecraftable {

    public EventManager () {
        MinecraftForge.EVENT_BUS.register(this);
    }

    private int size = -1;
    private final Timer timer = new Timer();

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onTickHighest(TickEvent.ClientTickEvent event) {
        if (AutoCrystal.INSTANCE.isEnabled())
            AutoCrystal.INSTANCE.onTick();
    }

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send event) {
        if (event.getPacket() instanceof CPacketHeldItemChange) {
            timer.reset();
        }
    }

    public boolean switchTimerPassed(long time) {
        return timer.hasReached(time);
    }

}
