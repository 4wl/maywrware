package xyz.maywr.hack.client.managers;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import xyz.maywr.hack.MaywrWare;
import xyz.maywr.hack.api.interfaces.Minecraftable;
import xyz.maywr.hack.client.modules.client.DiscordPresence;

public class RPCManager extends Thread implements Minecraftable {

    private static final DiscordRPC rpcInstance = DiscordRPC.INSTANCE;
    private static final DiscordRichPresence richPresence = new DiscordRichPresence();
    private static final DiscordEventHandlers presenceHandlers = new DiscordEventHandlers();

    private static String largeImageTextKey = "maywr cute";
    private static String picture = "";

    private static final long time = MaywrWare.timeFromRun / 1000L;

    public RPCManager () {
        try {
            rpcInstance.Discord_Initialize("952616642733547652", presenceHandlers, true, "");
            richPresence.startTimestamp = time;
        } catch (Exception ignored) {}
    }

    public void startRPC () {
        this.start();
    }

    public void stopRPC () {
        this.interrupt();
        rpcInstance.Discord_ClearPresence();
        rpcInstance.Discord_Shutdown();
    }


    private void update (String imageKey, String largeImageTextKey, String details, String state) {
        richPresence.largeImageKey = imageKey;
        richPresence.largeImageText = largeImageTextKey;
        richPresence.details = details;
        richPresence.state = state;

        rpcInstance.Discord_UpdatePresence(richPresence);
    }

    private String getActivity () {
        return mc.isIntegratedServerRunning() ? "синглплеер" : (mc.getCurrentServerData() != null ? mc.getCurrentServerData().serverIP.toLowerCase() : "в меню");
    }

    @Override
    public void run() {
        while (true) {
            try {
                picture = DiscordPresence.getInstance().pic.getValue().getPicName();
                update(picture, largeImageTextKey, getActivity(), String.format("%s %s | %s", MaywrWare.modid, MaywrWare.VERSION, mc.player.getName()));
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {e.printStackTrace();}
        }
    }
}