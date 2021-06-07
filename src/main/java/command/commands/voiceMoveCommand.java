package command.commands;

import command.CommandContext;
import command.ICommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public class voiceMoveCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        GuildMessageReceivedEvent event = ctx.getEvent();
        Member member = event.getMember();
        TextChannel channel = event.getChannel();
        Guild guild = event.getGuild();

        List<String> args = ctx.getArgs();

        if (!member.hasPermission(Permission.VOICE_MOVE_OTHERS)){
            channel.sendMessage("**This Command requires you to have the `Move Members` permission to use it**").queue();
            return;
        }

        if (args.size() < 2){
            channel.sendMessage("**" + getName() + " <voiceChanneltoMovefrom> <voiceChanneltoMoveto>**\nPlease give two Channels one to move from and one to move to").queue();
            return;
        }

        if (guild.getVoiceChannelsByName(args.get(0), true).size() == 0){
            channel.sendMessage("Can't find a Channel to move from").queue();
            return;
        }

        if (guild.getVoiceChannelsByName(args.get(1), true).size() == 0) {
            channel.sendMessage("Can't find a Channel to move to").queue();
            return;
        }

        VoiceChannel voiceChannelFrom = guild.getVoiceChannelsByName(args.get(0), true).get(0);
        VoiceChannel voiceChannelTo = guild.getVoiceChannelsByName(args.get(1), true).get(0);

        for (Member m :voiceChannelFrom.getMembers()) {
           guild.moveVoiceMember(m, voiceChannelTo).queue();
        }



    }

    @Override
    public String getName() {
        return "voicemove";
    }

    @Override
    public String getHelp() {
        return "Moves all Users of a Voice to another Voice";
    }

    @Override
    public List<String> getAliases() {
        return List.of("vm");
    }
}
