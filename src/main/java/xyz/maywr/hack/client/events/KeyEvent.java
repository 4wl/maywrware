package xyz.maywr.hack.client.events;

import net.minecraftforge.fml.common.eventhandler.Event;

public final class KeyEvent extends Event {

    private final int key;

    public KeyEvent(int key) {
        this.key = key;
    }

    public final int getKey() {
        return key;
    }
}
