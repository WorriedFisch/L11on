package commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import util.Config;

import static core.Main.jda;

public class leave {
    public static void leave(MessageReceivedEvent event){

        if (event.getAuthor().getIdLong() == (Config.OwnerId)){
            System.out.println("leave gleich");

            event.getGuild().leave().queue();
        }else {
            event.getTextChannel().sendMessage("du darfst nicht").queue();
            System.out.println("leave gleich nicht");
        }
    }
}
