package command.commands;


import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import util.Config;



public class help {

    public static void help(MessageReceivedEvent event){
        MessageChannel channel = event.getChannel();
        String messageId = event.getMessageId();

        if (event.isFromType(ChannelType.TEXT)){
            channel.purgeMessagesById(messageId);
            channel.sendMessage("verfügbare command.commands sind: sendpic, sendnudes").queue();
        }
        else if (event.isFromType(ChannelType.PRIVATE)){
            PrivateChannel privateChannel = event.getPrivateChannel();
            privateChannel.sendMessage(Config.HELPMASSAGE.build()).queue();
        }

    }
}
