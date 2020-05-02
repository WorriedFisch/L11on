package listeners;


import core.LiteSQL;
import emoji4j.EmojiUtils;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveAllEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReactionRoleListener extends ListenerAdapter {


    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {

        if (event.getChannelType() == ChannelType.TEXT) {
            if (!event.getUser().isBot()) {
                Long guildid = event.getGuild().getIdLong();
                Long channelid = event.getChannel().getIdLong();
                Long messageid = event.getMessageIdLong();


                if (event.getReactionEmote().isEmoji()) {

                    String emote = EmojiUtils.shortCodify(event.getReactionEmote().getEmoji());
                    ResultSet set = LiteSQL.onQuery("SELECT * FROM reactroles WHERE guildid = " + guildid + " AND channelid = " + channelid + " AND messageid = " + messageid + " AND emote = '" + emote + "'");





                    try {



                        if (set.getString("type").equals("normal")){

                            long rollenid = set.getLong("rollenid");
                            Guild guild = event.getGuild();
                            Role role = guild.getRoleById(rollenid);
                            guild.addRoleToMember(event.getMember(),role).queue();


                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }else {

                    Long emoteid = event.getReactionEmote().getIdLong();
                    ResultSet set = LiteSQL.onQuery("SELECT * FROM reactroles WHERE guildid = " + guildid + " AND channelid = " + channelid + " AND messageid = " + messageid + " AND cemoteid = " + emoteid);


                    try {

                        if (set.getString("type").equals("normal")){
                            long rollenid = set.getLong("rollenid");
                            Guild guild = event.getGuild();
                            Role role = guild.getRoleById(rollenid);
                            guild.addRoleToMember(event.getMember(),role).queue();
                        }

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();


                    }
                }
            }
        }
     }
     @Override
    public void onMessageReactionRemove(MessageReactionRemoveEvent event){
        if (event.isFromType(ChannelType.TEXT)){
            if (!event.getUser().isBot()){
                Long guildid = event.getGuild().getIdLong();
                Long channelid = event.getChannel().getIdLong();
                Long messageid = event.getMessageIdLong();

                if (event.getReactionEmote().isEmoji()) {

                    String emote = EmojiUtils.shortCodify(event.getReactionEmote().getEmoji());
                    ResultSet set = LiteSQL.onQuery("SELECT * FROM reactroles WHERE guildid = " + guildid + " AND channelid = " + channelid + " AND messageid = " + messageid + " AND emote = '" + emote + "'");

                    try {
                        if (set.getString("type").equals("normal")) {

                            long rollenid = set.getLong("rollenid");
                            Guild guild = event.getGuild();
                            Role role = guild.getRoleById(rollenid);
                            guild.removeRoleFromMember(event.getMember(), role).queue();
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }else {
                    Long emoteid = event.getReactionEmote().getIdLong();

                    ResultSet set = LiteSQL.onQuery("SELECT * FROM reactroles WHERE guildid = " + guildid + " AND channelid = " + channelid + " AND messageid = " + messageid + " AND cemoteid = " + emoteid);

                    try {
                        if (set.getString("type").equals("normal")) {
                            long rollenid = set.getLong("rollenid");
                            Guild guild = event.getGuild();
                            Role role = guild.getRoleById(rollenid);
                            guild.removeRoleFromMember(event.getMember(), role).queue();
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }







                }
            }
        }
    }
}