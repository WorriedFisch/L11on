package core;

import command.ICommand;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.privileges.CommandPrivilege;
import net.dv8tion.jda.api.interactions.components.Button;

public class ReadyListener extends ListenerAdapter {
    @Override
    public void onReady(ReadyEvent event){

        Guild guild = event.getJDA().getGuildById(790885170411601920l);

        for (ICommand cmd:CommandManager.commands) {
            CommandData commandData = new CommandData(cmd.getName(), cmd.getHelp());

            commandData.addOptions(cmd.getOptionData());

            guild.upsertCommand(commandData);

        }


        guild.retrieveCommands().queue(commands -> {

            for (Command cmd:commands) {
                cmd.delete().queue();
            }

        });





        guild.upsertCommand(new CommandData("test","say hello")
                //.addOptions(new OptionData(OptionType.USER, "user" , "usertest").setRequired(true))
                //.addOptions(new OptionData(OptionType.STRING, "string", "string"))
                .setDefaultEnabled(false)
        ).queue((command) ->
        {
            command.updatePrivileges(guild ,CommandPrivilege.enableRole(844122765547143229l)).queue();
        });


    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {

        if (event.getName().equals("test")) {
            event.reply("Click the button to say hello ")//event.getOption("user").getAsMember().getNickname()
                    .addActionRow(
                            Button.primary("hello", "Click Me"), // Button with only a label
                            Button.danger("hello2","Klick lieber nicht"),
                            Button.success("emoji", Emoji.fromMarkdown("<:minn:245267426227388416>"))) // Button with only an emoji
                    .queue();
        }
    }


}
