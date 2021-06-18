package listeners;

import command.commands.UtilityCommands.helpCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import util.Config;


public class ButtonListener extends ListenerAdapter {
    @Override
    public void onButtonClick(ButtonClickEvent event) {
        if (event.getComponentId().equals("hello")) {
            event.reply("Hello :)").queue(); // send a message in the channel

        } else if (event.getComponentId().equals("emoji")) {
            event.editMessage("That button didn't say click me").queue(); // update the message

        }else if (Config.commandCategories.contains(event.getComponentId())) {
            helpCommand.categorieHelp(event.getMember(), event.getComponentId(), event.getMessage());


        }
    }
}
