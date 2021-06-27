package command.commands.UtilityCommands;

import command.CommandContext;
import command.ICommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public class pingCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        JDA jda = ctx.getJDA();
        GuildMessageReceivedEvent event = ctx.getEvent();



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

    public List<String> getAliases() {
        return List.of("test");
    }

    public List<OptionData> getOptionData() {
        return null;
    }

    public Permission getPermission() {
        return null;
    }
}
