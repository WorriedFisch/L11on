package listeners;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import net.dv8tion.jda.api.hooks.ListenerAdapter;
import util.Config;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MessageListener extends ListenerAdapter
{

    public void onMessageReceived(MessageReceivedEvent event)
    {
        JDA jda = event.getJDA();                       //JDA, the core of the api.
        long responseNumber = event.getResponseNumber();//The amount of discord events that JDA has received since the last reconnect.

        //Event specific information
        User author = event.getAuthor();                //The user that sent the message
        Message message = event.getMessage();           //The message that was received.
        MessageChannel channel = event.getChannel();    //This is the MessageChannel that the message was sent to.
        String messageId = message.getId();             //This is the Id of the Message
        String msg = message.getContentDisplay();              //This returns a human readable version of the Message. Similar to
        ChannelType channelType = event.getChannelType();      //THis is the Type of the channel
        Member member = event.getMember();          //This Member that sent the message. Contains Guild specific information about the User!


        msg = msg.replace(Config.PREFIX,"");
        if(msg.startsWith("help")){
            commands.help.help(event);
        }else if(msg.startsWith("")){

        }



    }
}