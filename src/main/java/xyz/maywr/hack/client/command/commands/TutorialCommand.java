package xyz.maywr.hack.client.command.commands;

import xyz.maywr.hack.api.util.MessageUtil;
import xyz.maywr.hack.client.command.Command;
import xyz.maywr.hack.client.command.CommandManifest;
import net.minecraft.client.Minecraft;
import net.minecraft.client.tutorial.TutorialSteps;

@CommandManifest(label = "Tutorial", aliases = {"tut"})
public class TutorialCommand extends Command {

    @Override
    public void execute(String[] args) {
        Minecraft.getMinecraft().gameSettings.tutorialStep = TutorialSteps.NONE;
        Minecraft.getMinecraft().getTutorial().setStep(TutorialSteps.NONE);
        MessageUtil.sendClientMessage("Set tutorial step to none!", -11114);
    }

}
