package xyz.maywr.hack.client.command.commands;

import xyz.maywr.hack.MaywrWare;
import xyz.maywr.hack.client.command.Command;
import xyz.maywr.hack.client.command.CommandManifest;

@CommandManifest(label = "Save", aliases = "s")
public class SaveCommand extends Command {

    @Override
    public void execute(String[] args) {
        MaywrWare.configManager.saveConfig(MaywrWare.configManager.config.replaceFirst("maywrware/", ""));
    }
}
