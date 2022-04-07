package xyz.maywr.hack.client.command;

import xyz.maywr.hack.MaywrWare;
import xyz.maywr.hack.api.interfaces.Minecraftable;
import xyz.maywr.hack.client.modules.client.ClickGui;

public class Command implements Minecraftable {

    String label;
    String[] aliases;
    private String prefix;

    public Command() {
        if (getClass().isAnnotationPresent(CommandManifest.class)) {
            CommandManifest manifest = getClass().getAnnotation(CommandManifest.class);
            label = manifest.label();
            aliases = manifest.aliases();
            prefix = ClickGui.getInstance().prefix.getValue();
        }
    }

    public void execute(String[] args) {}

    public String getLabel() {
        return label;
    }

    public String[] getAliases() {
        return aliases;
    }
}
