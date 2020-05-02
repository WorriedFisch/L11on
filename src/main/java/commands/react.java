package commands;

import core.LiteSQL;
import core.Main;
import emoji4j.Emoji;
import emoji4j.EmojiUtils;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;
import net.dv8tion.jda.api.requests.ErrorResponse;
import net.dv8tion.jda.api.requests.RestAction;
import util.Config;

import java.util.Arrays;
import java.util.List;

public class react {

    public static void react(MessageReceivedEvent event){

        //.react #channel messageid :ok: @Role type

        List<String> args = Arrays.asList(event.getMessage().getContentDisplay().split(" "));
        List<TextChannel> channels = event.getMessage().getMentionedChannels();
        List<Role> roles = event.getMessage().getMentionedRoles();

        if (event.getAuthor().getIdLong() == Config.OwnerId){

            TextChannel channel = channels.get(0);


            List<Emote> emotes = event.getMessage().getEmotes();
            long messageid = Long.parseLong(args.get(2));


            if (emotes.size() != 0){
                channel.retrieveMessageById(messageid).queue((message) -> {
                    message.addReaction(emotes.get(0)).queue();
                });

                LiteSQL.onUpdate("INSERT INTO reactroles(guildid, channelid, messageid, emote, cemoteid, rollenid, type) VALUES(" +
                        // guildid                           channelid                              Messageid           emote       cemoteid                            roleid
                        event.getGuild().getIdLong() + ", " + channels.get(0).getIdLong()  + ", " + messageid + ", " + null + ", " + emotes.get(0).getIdLong() + ", " + roles.get(0).getIdLong() + ", '" + args.get(4) +"')" );


            }else{


                channel.retrieveMessageById(messageid).queue((message) -> {
                    message.addReaction(args.get(3)).queue();
                });

                String emote = EmojiUtils.shortCodify(args.get(3));

                LiteSQL.onUpdate("INSERT INTO reactroles(guildid, channelid, messageid, emote, cemoteid, rollenid, type) VALUES(" +
                        // guildid                           channelid                              Messageid           emote      cemoteid                              roleid
                        event.getGuild().getIdLong() + ", " + channels.get(0).getIdLong()  + ", " + messageid + ", " + "'" + emote + "'" +", " + null + ", " + roles.get(0).getIdLong() + ", '" + args.get(4) + "')" );



            }







        }

    }
}
