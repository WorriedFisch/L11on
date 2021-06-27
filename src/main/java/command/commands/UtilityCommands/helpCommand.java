package command.commands.UtilityCommands;

import command.CommandContext;
import command.ICommand;
import command.commands.MusicCommands.joinCommand;
import core.CommandManager;
import net.dv8tion.jda.api.EmbedBuilder;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.Button;
import util.Config;

import javax.annotation.Nullable;
import java.util.List;

public class helpCommand implements ICommand{

    private final CommandManager manager;

    public helpCommand(CommandManager manager) {
        this.manager = manager;
    }

    @Override
    public void handle(@Nullable CommandContext ctx) {

        List<String> args = ctx.getArgs();
        TextChannel channel = ctx.getChannel();

        EmbedBuilder builder2 = new EmbedBuilder();

        if (args.size() == 0){
            builder2
                    .setTitle("L11ons all Commands")
                    .setColor(10181046)
                    .setDescription("Help for all commands")
                    .setFooter("Use " + Config.PREFIX + "help <command> for command help")
                    .addField(":tools: Utilities", "`" + Config.PREFIX + "help utility`" , true)
                    .addField(":gear: Settings", "`"+ Config.PREFIX + "help settings`", true)
                    .addField(":game_die: Minigames", "`"+ Config.PREFIX + "help settings`", true);


            channel.sendMessage(builder2.build()).setActionRow(
                    Button.success("UtilityCmd", Emoji.fromUnicode("U+1F6E0")),
                    Button.success("SettingCmd", Emoji.fromUnicode("U+2699")),
                    Button.success("MinigameCmd", Emoji.fromUnicode("U+1F3B2"))
            ).queue();
        }


        String search = args.get(0);
        ICommand command = manager.getCommand(search);

        if (command == null) {
            channel.sendMessage("Nothing found for " + search).queue();
            return;
        }

        channel.sendMessage(command.getHelp() + "   ").queue();
    }

    public String getName() {
        return "help";
    }

    public String getHelp() {
        return "Shows the list with commands in the bot\n" +
                "Usage: `!!help [command]`";
    }

    public String getCategory() {
        return "";
    }


    public List<String> getAliases() {
        return List.of("commands", "cmds", "commandlist");
    }

    public static void categorieHelp(Member member,String categorie,Message message){

        TextChannel channel = message.getTextChannel();

        EmbedBuilder builder = new EmbedBuilder();

        builder
                .setTitle("L11ons all Commands")
                .setColor(10181046)
                .setDescription("Help for all commands")
                .setFooter("Use " + Config.PREFIX + "help <command> for command help");

        /*
        for (ICommand cmd:CommandManager.commands) {
            if (cmd.getCategory().equalsIgnoreCase(categorie)){
                builder.addField(cmd.getName(),"`" + cmd.getHelp() + "`",true);
            }
        }
        */

        StringBuilder commandList = new StringBuilder();

        for (ICommand cmd:CommandManager.commands) {
            if (cmd.getCategory().equalsIgnoreCase(categorie)){
                commandList.append("`" + cmd.getName() + "`, ");
            }
        }
        commandList.deleteCharAt(commandList.length() - 1);
        
        builder.setTitle(Config.emojiForCategorie.get(categorie) + " " + categorie.replace("Cmd","") + " Commands");
        builder.setDescription(commandList);


        message.editMessage(builder.build()).queue();


    }


    public List<OptionData> getOptionData() {
        return null;
    }

    public Permission getPermission() {
        return null;
    }
}
