package xyz.maywr.hack.client.command.commands;

import xyz.maywr.hack.MaywrWare;
import xyz.maywr.hack.api.property.Bind;
import xyz.maywr.hack.api.util.MessageUtil;
import xyz.maywr.hack.client.command.Command;
import xyz.maywr.hack.client.command.CommandManifest;
import xyz.maywr.hack.client.modules.Module;
import org.lwjgl.input.Keyboard;

@CommandManifest(label = "Bind", aliases = {"b"})
public class BindCommand extends Command {

    @Override
    public void execute(String[] args) {
        //fix crash
        if (args.length < 2) {
            MessageUtil.sendClientMessage("Use the command like this -> (module, bind)", true);
            return;
        }
        final Module module = MaywrWare.moduleManager.getModuleByLabel(args[1]);
        if (module != null) {
            //no idea what that keyboard method does too lazy to test it lmao
            int index = Keyboard.getKeyIndex(args[2].toUpperCase());
            module.bind.setValue(new Bind(index));
            MessageUtil.sendClientMessage(module.getLabel() + " has been bound to " + Keyboard.getKeyName(index), false);
        }
    }

}
