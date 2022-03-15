package xyz.maywr.hack.client.events;

import net.minecraftforge.fml.common.eventhandler.Event;
import xyz.maywr.hack.api.property.Setting;
import xyz.maywr.hack.client.modules.Module;

public class ClientEvent extends Event {

    private Module module;
    private Setting setting;
    private int stage;

    public ClientEvent(Setting setting) {
        this.setting = setting;
    }

    public ClientEvent(int stage) {
        this.stage = stage;
    }

    public Module getModule() {
        return module;
    }

    public Setting getSetting() {
        return setting;
    }

    public int getStage() {
        return stage;
    }

}
