package commands;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.nio.channels.Channel;


public class sendEmoji {
    public static void sendEmoji(MessageReceivedEvent event){
        String[] args = event.getMessage().getContentDisplay().split(" ");


        MessageChannel channel = event.getChannel();
        Guild guild = event.getGuild();

        System.out.println(args[1]);

        String emoji = guild.getEmotesByName(args[1].replace(":",""), true).get(0).getAsMention();

        if (args.length == 2){
            channel.sendMessage(emoji).queue();
        }else{
            TextChannel mentionedChannel = event.getMessage().getMentionedChannels().get(0);
            mentionedChannel.sendMessage(emoji).queue();
        }


    }
}
