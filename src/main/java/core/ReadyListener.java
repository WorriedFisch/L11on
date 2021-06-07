package core;

import com.jagrosh.jdautilities.command.CommandBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.privileges.CommandPrivilege;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

import java.util.List;
import java.util.function.BooleanSupplier;

import static core.DiscordBot.jda;

public class ReadyListener extends ListenerAdapter {
    @Override
    public void onReady(ReadyEvent event){

        Guild guild = event.getJDA().getGuildById(790885170411601920l);


        guild.upsertCommand(new CommandData("test","say hello")
                .addOptions(new OptionData(OptionType.USER, "user" , "usertest").setRequired(true))
                .setDefaultEnabled(false)
        ).queue((command) ->
        {
            command.updatePrivileges(guild ,CommandPrivilege.enableRole(791265130489839628l)).queue();

            System.out.println(command.getName());
        });


    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        if (event.getName().equals("test")) {
            event.reply("Click the button to say hello "+ event.getOption("user").getAsMember().getNickname())
                    .addActionRow(
                            Button.primary("hello", "Click Me"), // Button with only a label
                            Button.success("emoji", Emoji.fromMarkdown("<:minn:245267426227388416>"))) // Button with only an emoji
                    .queue();
        }
    }

    @Override
    public void onButtonClick(ButtonClickEvent event) {
        if (event.getComponentId().equals("hello")) {
            event.reply("Hello :)").queue(); // send a message in the channel
        } else if (event.getComponentId().equals("emoji")) {
            event.editMessage("That button didn't say click me").queue(); // update the message
        }
    }
}
