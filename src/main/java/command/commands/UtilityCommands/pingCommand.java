package command.commands.UtilityCommands;

import command.ICommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

import java.util.List;

public class pingCommand implements ICommand {
    @Override
    public void handle(SlashCommandEvent event) {
        JDA jda = event.getJDA();



        event.getChannel().sendMessage("ping ist Kacke ").queue();



    }

    public String getName() {
        return "ping";
    }

    public String getHelp(){
        return "Shows the current Ping from the bot to the discord servers";
    }

    public String getCategory() {
        return "UtilityCmd";
    }

    public CommandData getCommandData() {
        return new CommandData(this.getName(), this.getHelp()).setDefaultEnabled(false);
    }

    public List<String> getAliases() {
        return List.of("test");
    }

    public Permission getPermission() {
        return null;
    }
}
