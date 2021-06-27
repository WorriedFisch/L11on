package command.commands.UtilityCommands;

import command.ICommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandGroupData;

import java.util.List;

public class purgeCommand implements ICommand {

    @Override
    public void handle(SlashCommandEvent event) {

    }

    @Override
    public String getName() {
        return "purge";
    }

    @Override
    public String getHelp() {
        return "purge messages sorted by Member or amount";
    }

    @Override
    public String getCategory() {
        return "UtilityCmd";
    }

    @Override
    public Permission getPermission() {
        return null;
    }

    @Override
    public List<String> getAliases() {
        return List.of("clear");
    }

    @Override
    public CommandData getCommandData() {
        return new CommandData(this.getName(), this.getHelp()).addOption(OptionType.INTEGER,"amount","amount of messages to purge", true);
    }
}
