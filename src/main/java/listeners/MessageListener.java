package listeners;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import util.Config;

public class MessageListener extends ListenerAdapter
{
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        JDA jda = event.getJDA();                       //JDA, the core of the api.
        long responseNumber = event.getResponseNumber();//The amount of discord events that JDA has received since the last reconnect
        Message message = event.getMessage();

        String msg = message.getContentDisplay();              //This returns a human readable version of the Message. Similar to

        msg = msg.replace(Config.PREFIX,"");
        if(msg.startsWith("help")){
            commands.help.help(event);
        }else if(msg.startsWith("clear")) {
            commands.clear.clear(event);
        }else if(msg.startsWith("exit")){
            commands.exit.exit(event);
        }else if(msg.startsWith("react")){
            //commands.react.react(event);
        }else if(msg.startsWith("sendEmoji")){
            commands.sendEmoji.sendEmoji(event);
        }else if(msg.startsWith("sendpic")){
            commands.sendpic.sendpic(event);
        }else if(msg.startsWith("leave")){
            commands.leave.leave(event);
        }

        TextChannel channel = event.getTextChannel();
        String channelid = event.getChannel().getId();

        if (channelid.equals("704308005847433286")){
            String sendetMessages = channel.getTopic().replace("<a:lol:575001295589605394>", "").replace(" Anzahl der gesendeten Nachrichten: ","").replace(" ","");
            int counter = Integer.parseInt(sendetMessages);
            counter++;
            channel.getManager().setTopic("<a:lol:575001295589605394> Anzahl der gesendeten Nachrichten: " + counter + " <a:lol:575001295589605394>").queue();
        }









    }
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){

    }


}