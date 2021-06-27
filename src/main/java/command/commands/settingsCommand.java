package command.commands;

import command.ICommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

import java.util.List;

public abstract class settingsCommand implements ICommand {

    public void handle(SlashCommandEvent event) {

    }

    public String getName() {
        return "settings";
    }

    public String getHelp() {
        return null;
    }

    public String getCategory() {
        return null;
    }

    public Permission getPermission() {
        return null;
    }

    public List<String> getAliases(){
        return List.of();
    }

}
