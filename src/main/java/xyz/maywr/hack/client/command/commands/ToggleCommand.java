package xyz.maywr.hack.client.command.commands;

import xyz.maywr.hack.MaywrWare;
import xyz.maywr.hack.api.util.MessageUtil;
import xyz.maywr.hack.client.command.Command;
import xyz.maywr.hack.client.command.CommandManifest;
import xyz.maywr.hack.client.modules.Module;

@CommandManifest(label = "Toggle", aliases = {"t"})
public class ToggleCommand extends Command {

    @Override
    public void execute(String[] args) {
        //fix crash
        if (args.length < 2) {
            return;
        }
        final Module module = MaywrWare.moduleManager.getModuleByLabel(args[1]);
        if (module != null) {
            module.toggle();
            MessageUtil.sendClientMessage(module.getLabel() + " has been toggled " + (module.isEnabled() ? "on" : "off"), false);
        }
    }
}
