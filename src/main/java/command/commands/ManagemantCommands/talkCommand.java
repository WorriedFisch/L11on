package command.commands.ManagemantCommands;

import command.ICommand;
import core.CommandManager;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.components.Button;

import java.util.ArrayList;
import java.util.List;

public class talkCommand implements ICommand {

    private static List<String > buttonNames = new ArrayList<String>();

    @Override
    public void handle(SlashCommandEvent event) {
        if (!event.getMember().getVoiceState().inVoiceChannel()){
            event.reply("You need to be in a Voicechannel to use this Command").queue();
        }


        VoiceChannel channel = event.getMember().getVoiceState().getChannel();
        List<Member>  members = channel.getMembers();

        for (Member m:members) {
            if (!m.hasPermission(Permission.VOICE_MUTE_OTHERS)){
                m.mute(true).queue();
            }
        }
        event.reply("muted everyone in the voice channel").addActionRow(Button.success("unmute_" + channel.getId(), "press to unmute everyone")).queue();
        buttonNames.add("unmute_" + channel.getId());
    }

    @Override
    public void ButtonAction(ButtonClickEvent event) {

        Guild guild = event.getGuild();
        String componentId = event.getComponentId();
        componentId = componentId.replace("unmute_","");
        VoiceChannel channel = guild.getVoiceChannelById(componentId);
        List<Member>  members = channel.getMembers();

        for (Member m:members) {
            if (!m.hasPermission(Permission.VOICE_MUTE_OTHERS)){
                m.mute(false).queue();
            }
        }

        event.deferReply().queue();

    }

    @Override
    public String getName() {
        return "talk";
    }

    @Override
    public String getHelp() {
        return "Mute everyone in the voicechannel excluding Members that could entmute theirselfs";
    }

    @Override
    public String getCategory() {
        return "ManagemantCmd";
    }


    @Override
    public List<String> getAliases() {
        return List.of("mute");
    }

    @Override
    public CommandData getCommandData() {
        return new CommandData(getName(), getHelp()).setDefaultEnabled(false);
    }
    @Override
    public List<String> getButtons(){

        return buttonNames;
    }




}
