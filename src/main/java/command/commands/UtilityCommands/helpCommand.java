package command.commands.UtilityCommands;

import command.ICommand;
import core.CommandManager;
import net.dv8tion.jda.api.EmbedBuilder;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.Button;
import util.Config;

import java.util.ArrayList;
import java.util.List;

public class helpCommand implements ICommand{

    private final CommandManager manager;

    public helpCommand(CommandManager manager) {
        this.manager = manager;
    }

    @Override
    public void handle(SlashCommandEvent event) {

        TextChannel channel = event.getTextChannel();

        EmbedBuilder builder2 = new EmbedBuilder();

        if (true){
            builder2
                    .setTitle("All Commands of L11on")
                    .setColor(10181046)
                    .setDescription("Use /help <command> for specific command help")
                    .setFooter("Bot managed by " + event.getJDA().getUserById(Config.ownerId).getAsTag(),event.getJDA().getUserById(Config.ownerId).getAvatarUrl());


            event.replyEmbeds(builder2.build()).queue();
        }

        if (event.getOption("cmd") != null){
            String search = event.getOption("cmd").getAsString();
            ICommand command = manager.getCommand(search);
            event.reply(command.getHelp() + "   ").queue();

        }


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

    @Override
    public CommandData getCommandData() {

        List<Command.Choice> choices = new ArrayList<>();
        for (ICommand cmd:CommandManager.commands) {
            choices.add(new Command.Choice(cmd.getName(), cmd.getName()));
        }

        return new CommandData(this.getName(), this.getHelp())
                                .addOptions(List.of(
                                        new OptionData(OptionType.STRING,"command","Get hel pto the specified Command", false)
                                                .addChoices(choices))
                                ).setDefaultEnabled(true);
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


    public Permission getPermission() {
        return null;
    }
}
