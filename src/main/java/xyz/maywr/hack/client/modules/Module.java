package xyz.maywr.hack.client.modules;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import xyz.maywr.hack.MaywrWare;
import xyz.maywr.hack.api.property.Bind;
import xyz.maywr.hack.api.property.Setting;
import xyz.maywr.hack.api.util.MessageUtil;
import xyz.maywr.hack.client.gui.TrollGui;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.List;

public class Module {

    protected final Minecraft mc = Minecraft.getMinecraft();

    private final List<Setting> settings = new ArrayList<>();

    public final Setting<Bind> bind = register(new Setting<>("Bind", new Bind(-10000)));
    public final Setting<Boolean> enabled = register(new Setting<>("Enabled", false));

    private String label, suffix;
    private Category category;
    private boolean persistent;
    private int color;

    public Module() {
        suffix = "";
        if (getClass().isAnnotationPresent(ModuleManifest.class)) {
            ModuleManifest moduleManifest = getClass().getAnnotation(ModuleManifest.class);
            label = moduleManifest.label();
            category = moduleManifest.category();
            bind.setValue(new Bind(moduleManifest.key()));
            persistent = moduleManifest.persistent();
            color = moduleManifest.color();
        }
    }

    public final Setting register(Setting setting) {
        this.settings.add(setting);
        if (mc.currentScreen instanceof TrollGui) {
            TrollGui.getInstance().updateModule(this);
        }
        return setting;
    }

    public final List<Setting> getSettings() {
        return this.settings;
    }

    public final void setEnabled(boolean enabled) {
        if (persistent) {
            this.enabled.setValue(true);
            return;
        }

        this.enabled.setValue(enabled);
        onToggle();
        if (enabled) {
            MinecraftForge.EVENT_BUS.register(this);
            onEnable();
            if(!this.getLabel().equalsIgnoreCase("ClickGUI")) {
                MessageUtil.sendClientMessage(this.getLabel() + " was " + ChatFormatting.GREEN + "enabled", -44444);
            }
        } else {
            MinecraftForge.EVENT_BUS.unregister(this);
            onDisable();
            if(!this.getLabel().equalsIgnoreCase("ClickGUI")) {
                MessageUtil.sendClientMessage( this.getLabel() + " was " + ChatFormatting.RED + "disabled", -44444);
            }
        }
    }


    public void toggle() {
        setEnabled(!enabled.getValue());
    }

    public void disable() {
        setEnabled(false);
    }

    public void onRender3D () {}
    public void onRender2D () {}
    public void onToggle () {}
    public void onEnable () {}
    public void onDisable () {}
    public void onLoad () {}
    public void onDisconnect () {}
    public void onTick () {}


    public final boolean isNull() {
        return mc.player == null || mc.world == null;
    }

    public final int getKey() {
        return bind.getValue().getKey();
    }

    public final boolean isEnabled() {
        return enabled.getValue();
    }

    public final boolean isPersistent() {
        return persistent;
    }

    public int getColor() {
        return color;
    }

    public final Category getCategory() {
        return category;
    }

    public final String getLabel() {
        return this.label;
    }

    public final void clearSuffix() {
        suffix = "";
    }

    public final void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public final String getSuffix() {
        if (suffix.length() == 0) {
            return "";
        }
        return " " + ChatFormatting.DARK_GRAY + "[" + ChatFormatting.WHITE + suffix + ChatFormatting.DARK_GRAY + "]";
    }

    @SubscribeEvent
    public void updateEvent (TickEvent.ClientTickEvent event) {
        if(mc.world == null || mc.player == null) return;
        this.onTick();
    }

    public enum Category {
        COMBAT("Combat", 0xFFFF0000),
        MOVEMENT("Movement", 0xFF007CFF),
        PLAYER("Player",0xFF00FF00),
        CLIENT("Client",0xA2A9CE),
        VISUAL("Visual",0xFFFFA200),
        MISC("Misc", 0xFF7C00FF),
        HUD("HUD", 0xFF7C00FF);

        final int color;
        final String label;

        Category(String label, int color) {
            this.color = color;
            this.label = label;
        }

        public final int getColor() {
            return color;
        }

        public final String getLabel() {
            return label;
        }

        }
    }