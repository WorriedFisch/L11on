package command.commands.MusicCommands;

import command.CommandContext;
import command.ICommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;


public class joinCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {

        GuildMessageReceivedEvent event = ctx.getEvent();

        Guild guild = event.getGuild();
        Member member = event.getMember();
        TextChannel channel = event.getChannel();

        if (member.getVoiceState().inVoiceChannel()){

            VoiceChannel voiceChannel = member.getVoiceState().getChannel();
            guild.getAudioManager().openAudioConnection(voiceChannel);
        }else {
            channel.sendMessage("**You have to be in a voice channel to use this command.**").queue();
        }

    }

    public String getName() {
        return "join";
    }

    public String getHelp() {
        return null;
    }

    public String getCategory() {
        return "MusicCmd";
    }

    public List<String> getAliases() {
        return List.of("summon");
    }

    public List<OptionData> getOptionData() {
        return null;
    }

    public Permission getPermission() {
        return null;
    }
}
