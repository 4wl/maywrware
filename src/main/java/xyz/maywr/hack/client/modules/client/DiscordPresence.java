package xyz.maywr.hack.client.modules.client;

import xyz.maywr.hack.MaywrWare;
import xyz.maywr.hack.api.property.Setting;
import xyz.maywr.hack.client.modules.Module;
import xyz.maywr.hack.client.modules.ModuleManifest;

@ModuleManifest(name = "RPC", category = Module.Category.CLIENT)
public class DiscordPresence extends Module {

    private static DiscordPresence INSTANCE;

    public DiscordPresence() {
        INSTANCE = this;
    }

    public static DiscordPresence getInstance() {
        return INSTANCE;
    }

    public final Setting<Picture> pic = register(new Setting<>("Picture", Picture.TRANS));

    @Override
    public void onEnable() {
        if(mc.world == null) return; //thats kinda lame but rpc bugs if no world dunno why
        MaywrWare.rpcManager.startRPC();
    }

    @Override
    public void onDisable() {
        MaywrWare.rpcManager.stopRPC();
    }

    public enum Picture {

        TRANS("trans"),
        MORGEN("morgen"),
        CUTE("nya");

        private String picName;

        Picture (String picName) {
            this.picName = picName;
        }

        public String getPicName() {
            return picName;
        }
    }

}
