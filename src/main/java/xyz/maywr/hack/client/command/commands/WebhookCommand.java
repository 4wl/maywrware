package xyz.maywr.hack.client.command.commands;

import xyz.maywr.hack.api.util.MessageUtil;
import xyz.maywr.hack.client.command.Command;
import xyz.maywr.hack.client.command.CommandManifest;
import xyz.maywr.hack.client.modules.misc.AutoFish;

@CommandManifest(label = "webhook")
public class WebhookCommand extends Command {

    @Override
    public void execute(String[] args) {
        try {
            if (!args[1].matches("https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)")) {
                MessageUtil.sendClientMessage("kinda seems to be not a webhook bro", false);
                return;
            }
            AutoFish.webhook = args[1];
            MessageUtil.sendClientMessage("webhook changed", false);
        } catch (Exception e) {
            MessageUtil.sendClientMessage("something went wrong. check da console", false);
            e.printStackTrace();
        }
    }
}
