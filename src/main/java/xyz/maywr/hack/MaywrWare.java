package xyz.maywr.hack;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.maywr.hack.client.managers.*;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Mod(name = MaywrWare.NAME, modid = MaywrWare.modid, version = MaywrWare.VERSION)
public final class MaywrWare {

    public static final String NAME = "MaywrWare";
    public static final String modid = "maywrware";
    public static final String VERSION = "0.2";
    public static final Logger logger = LogManager.getLogger(NAME);

    private final File directory = new File(Minecraft.getMinecraft().gameDir, "maywrware");

    public static FontManager fontManager;
    public static CommandManager commandManager;
    public static RPCManager rpcManager;
    public static ModuleManager moduleManager;
    public static ConfigManager configManager;
    public static FriendManager friendManager;
    public static EventManager eventManager;
    public static TPSManager tpsManager;

    public static final long timeFromRun = System.currentTimeMillis();

    @Mod.EventHandler
    public void preInit (FMLPreInitializationEvent event) {
        logger.info("Pre-Init started :3");
    }

    @Mod.EventHandler
    public void init (FMLInitializationEvent event) {
        logger.info(new String(Base64.getDecoder().decode("CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKIF8gX18gX19fICAgX18gXyBfICAgX19fICAgICAgX19fIF9fX18gICAgICBfX19fIF8gXyBfXyBfX18gCnwgJ18gYCBfIFwgLyBfYCB8IHwgfCBcIFwgL1wgLyAvICdfX1wgXCAvXCAvIC8gX2AgfCAnX18vIF8gXAp8IHwgfCB8IHwgfCAoX3wgfCB8X3wgfFwgViAgViAvfCB8ICAgXCBWICBWIC8gKF98IHwgfCB8ICBfXy8KfF98IHxffCB8X3xcX18sX3xcX18sIHwgXF8vXF8vIHxffCAgICBcXy9cXy8gXF9fLF98X3wgIFxfX198CiAgICAgICAgICAgICAgICAgIF9fLyB8ICAgICAgICAgICAgICAgICAgICAgICBieSBtYXl3ciA6MyAgCiAgICAgICAgICAgICAgICAgfF9fXy8gICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAo="), StandardCharsets.UTF_8));
        logger.info("loading hak");
        Display.setTitle(MaywrWare.modid + " loading");
        fontManager = new FontManager();
        moduleManager = new ModuleManager();
        commandManager = new CommandManager();
        eventManager = new EventManager();
        friendManager = new FriendManager(new File(directory, "friends.json"));
        tpsManager = new TPSManager();
        rpcManager = new RPCManager();
        configManager = new ConfigManager();
        logger.info("loaded hak :3");
        Display.setTitle(MaywrWare.modid + " " + MaywrWare.VERSION);
    }

    @Mod.EventHandler
    public void postInit (FMLPostInitializationEvent event) {
        logger.info("post-init :3");
    }

}
