package commands;

import core.Main;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class exit {
    public static void exit(MessageReceivedEvent event){

        if (event.getAuthor().getId().equals("418861325435797514")){
            event.getMessage().delete().queue();
            Main.shutdown();
        }
    }
}
