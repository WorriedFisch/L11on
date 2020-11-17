package commands;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.File;

public class sendpic {
    public static void sendpic(MessageReceivedEvent event){
        Guild guild = event.getGuild();
        TextChannel channel = event.getTextChannel();

        File file = new File("src/test.jpg");

        channel.sendFile(file).queue();


    }
}
