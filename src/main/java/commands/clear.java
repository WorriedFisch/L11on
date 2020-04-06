package commands;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class clear {

    public static void clear(MessageReceivedEvent event){
        Message msg = event.getMessage();
        String message = msg.getContentDisplay();
        String clearmsg = message.replace("clear ", "");

        if(clearmsg == ""){
            System.out.println("int");
            



        }


    }
}
