package commands;

import core.Main;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import util.Config;

import static core.Main.jda;

public class exit {
    public static void exit(MessageReceivedEvent event){
        System.out.println(jda);
        System.out.println(event.getAuthor() + "" + Config.owner);
        if (event.getAuthor().equals(Config.owner)){
            event.getMessage().delete().queue();
            Main.shutdown();
        }
    }
}
