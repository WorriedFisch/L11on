package listeners;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import util.Config;

public class MessageListener extends ListenerAdapter
{

    public void onMessageReceived(MessageReceivedEvent event)
    {
        JDA jda = event.getJDA();                       //JDA, the core of the api.
        long responseNumber = event.getResponseNumber();//The amount of discord events that JDA has received since the last reconnect.
        Message message = event.getMessage();

        String msg = message.getContentDisplay();              //This returns a human readable version of the Message. Similar to


        msg = msg.replace(Config.PREFIX,"");
        if(msg.startsWith("help")){

            commands.help.help(event);
        }else if(msg.startsWith("clear")) {
            commands.clear.clear(event);
        }








    }
}