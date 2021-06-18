package command.commands.UtilityCommands;

import command.CommandContext;
import command.ICommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public class pingCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        JDA jda = ctx.getJDA();
        GuildMessageReceivedEvent event = ctx.getEvent();



        event.getChannel().sendMessage("ping ist Kacke ").queue();



    }

    @Override
    public String getName() {
        return "ping";
    }

    @Override
    public String getHelp(){
        return "Shows the current Ping from the bot to the discord servers";
    }

    @Override
    public String getCategory() {
        return "UtilityCmd";
    }

    @Override
    public List<String> getAliases() {
        return List.of("test");
    }
}
