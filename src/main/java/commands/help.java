package commands;


import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import util.Config;



public class help {
    public static void main(){

    }

    public static void help(MessageReceivedEvent event){

        MessageChannel channel = event.getChannel();
    if (event.isFromType(ChannelType.TEXT)){
        channel.sendMessage(Config.HELPMASSAGE).queue();

    }
    else if (event.isFromType(ChannelType.PRIVATE)){
        PrivateChannel privateChannel = event.getPrivateChannel();
        privateChannel.sendMessage(Config.HELPMASSAGE).queue();
    }

    }

}
