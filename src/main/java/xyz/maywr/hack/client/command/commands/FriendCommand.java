package xyz.maywr.hack.client.command.commands;

import xyz.maywr.hack.MaywrWare;
import xyz.maywr.hack.client.command.Command;
import xyz.maywr.hack.client.command.CommandManifest;

@CommandManifest(label = "Friend", aliases = {"friends", "friend, f"})
public class FriendCommand extends Command {

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            return;
        }

        try {
            String name = args[2];
            switch (args[1].toUpperCase()) {
                case ("ADD"):
                    MaywrWare.friendManager.addFriend(name);
                    break;
                case ("DEL"):
                    MaywrWare.friendManager.removeFriend(name);
                    break;
                case ("DELETE"):
                    MaywrWare.friendManager.removeFriend(name);
                    break;
                case ("CLEAR"):
                    MaywrWare.friendManager.clearFriends();
                    break;
                case ("INSIDE"):
                    MaywrWare.friendManager.clearFriends();
                    break;
            }
        } catch (Exception e) {
        }
    }

}
