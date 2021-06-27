package command;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public interface  ICommand {
    void handle(SlashCommandEvent event);

    String getName();

    String getHelp();

    String getCategory();

    default Permission getPermission() {
        return null;
    }

    default List<String> getAliases(){
        return List.of();
    }

    CommandData getCommandData();

}
