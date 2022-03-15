package xyz.maywr.hack.client.modules.visual;

import xyz.maywr.hack.api.property.Setting;
import xyz.maywr.hack.client.modules.Module;
import xyz.maywr.hack.client.modules.ModuleManifest;

@ModuleManifest(label = "TimeChanger", category = Module.Category.VISUAL, listen = false, color = 0x9966ff)
public final class TimeChanger extends Module {

    public final Setting<Integer> timeSetting = register(new Setting<>("Time", 12000, 0, 23000));

    public static TimeChanger INSTANCE; //pasted from Pooloo

    public TimeChanger() {
        INSTANCE = this;
    }

}
