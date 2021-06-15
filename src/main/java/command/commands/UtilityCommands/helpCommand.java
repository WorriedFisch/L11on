package command.commands.UtilityCommands;

import command.CommandContext;
import command.ICommand;
import core.CommandManager;
import emoji4j.EmojiUtils;
import net.dv8tion.jda.api.EmbedBuilder;

import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.channel.text.GenericTextChannelEvent;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.interactions.components.ButtonStyle;
import util.Config;

import javax.annotation.Nullable;
import java.util.List;

public class helpCommand implements ICommand {

    private final CommandManager manager;

    public helpCommand(CommandManager manager) {
        this.manager = manager;
    }


    @Override
    public void handle(@Nullable CommandContext ctx) {

        List<String> args = ctx.getArgs();
        TextChannel channel = ctx.getChannel();

        EmbedBuilder builder2 = new EmbedBuilder();

        builder2
                .setTitle("L11ons all Commands")
                .setColor(10181046)
                .setDescription("Help for all commands")
                .addField(":tools: Utilities", "`" + Config.PREFIX + "help utility`" , true)
                .addField(":gear: Settings", "`"+ Config.PREFIX + "help settings`", true)
                .addField(":game_die: Minigames", "`"+ Config.PREFIX + "help settings`", true);

        channel.sendMessage(builder2.build()).setActionRow(
                Button.success("helpUtil", Emoji.fromUnicode("U+1F6E0")),
                Button.success("helpSettings", Emoji.fromUnicode("U+2699")),
                Button.success("helpMinigames", Emoji.fromUnicode("U+1F3B2"))
        ).queue();


        
        if (args.isEmpty()) {
            StringBuilder builder = new StringBuilder();

            builder.append("List of commands\n");

            manager.getCommands().stream().map(ICommand::getName).forEach(
                    (it) -> builder.append('`').append(Config.PREFIX).append(it).append("`\n")
            );
            channel.sendMessage(builder.toString()).queue();
            return;
        }

        String search = args.get(0);
        ICommand command = manager.getCommand(search);

        if (command == null) {
            channel.sendMessage("Nothing found for " + search).queue();
            return;
        }

        channel.sendMessage(command.getHelp() + "   " + command.getPermission()).queue();
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getHelp() {
        return "Shows the list with commands in the bot\n" +
                "Usage: `!!help [command]`";
    }

    @Override
    public String getCategory() {
        return "";
    }


    @Override
    public List<String> getAliases() {
        return List.of("commands", "cmds", "commandlist");
    }

    public static void categorieHelp(Member member, Message message){


        
    }


}
