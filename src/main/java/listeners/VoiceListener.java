package listeners;


import core.LiteSQL;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import util.Config;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;



public class VoiceListener extends ListenerAdapter
{

    /*
    public static List<VoiceChannel> channels = new ArrayList<>();
    public static void createChannel(GuildVoiceJoinEvent event){

        VoiceChannel channeljoined = event.getChannelJoined();

        Guild guild = event.getGuild();
        Long guildid = event.getGuild().getIdLong();
        Long joinedchannelid = event.getChannelJoined().getIdLong();
        ResultSet set = LiteSQL.onQuery("SELECT channelid FROM tempvoices WHERE guildid = " + guildid + " AND channelid = " + joinedchannelid);


        try {

            Long channelid = set.getLong("channelid");
            final VoiceChannel[] voiceChannel = {null};
            if (event.getChannelJoined().getIdLong() == set.getLong("channelid")){

                event.getGuild().createVoiceChannel("Test").queue(channel -> {

                    channel.getManager().setParent(channeljoined.getParent()).queue();
                    channel.getManager().setUserLimit(channeljoined.getUserLimit()).queue();
                    channel.getManager().setName("⌛ " + event.getMember().getEffectiveName()).queue();

                    channel.createPermissionOverride(Verifiziertrole).setAllow(Permission.VOICE_CONNECT,Permission.VOICE_SPEAK).setDeny(Permission.CREATE_INSTANT_INVITE).queue();
                    guild.moveVoiceMember(event.getMember(), channel).queueAfter(1, TimeUnit.SECONDS);



                    channels.add(channel);
                });


            }
        } catch (SQLException throwables) {
            
        }
    }
    public static void deleteChannel(GuildVoiceLeaveEvent event){
        VoiceChannel channel = event.getChannelLeft();

        if(channels.contains(channel)){
            if (channel.getMembers().size() == 0){
                channel.delete().queue();
            }
        }
    }




    @Override
    public void onGuildVoiceJoin(GuildVoiceJoinEvent event){
        //createChannel(event);

    }
    @Override
    public void onGuildVoiceLeave(GuildVoiceLeaveEvent event){

        //deleteChannel(event);
    }
    @Override
    public void onGuildVoiceMove(GuildVoiceMoveEvent event){
        /*

        if (channels.contains(event.getChannelLeft())) {
            VoiceChannel channel = event.getChannelLeft();

            if (channel.getMembers().size() == 0){
                channel.delete().queue();
            }
        }else{

            VoiceChannel voiceChannel;
            VoiceChannel channeljoined = event.getChannelJoined();


            Long guildid = event.getGuild().getIdLong();
            Long joinedchannelid = event.getChannelJoined().getIdLong();



            ResultSet set = LiteSQL.onQuery("SELECT channelid FROM tempvoice WHERE guildid = " + guildid + " AND channelid = " + joinedchannelid);


            try {

                Long channelid = set.getLong("channelid");


                if (event.getChannelJoined().getIdLong() == set.getLong("channelid")){

                    event.getGuild().createVoiceChannel("Test").queue(channelmove -> {

                        channelmove.getManager().setParent(channeljoined.getParent()).queue();
                        channelmove.getManager().setUserLimit(channeljoined.getUserLimit()).queue();
                        channelmove.getManager().setName("⌛ " + event.getMember().getEffectiveName()).queue();

                        channelmove.createPermissionOverride(Verifiziertrole).setAllow(Permission.VOICE_CONNECT,Permission.VOICE_SPEAK).queue();

                        event.getGuild().moveVoiceMember(event.getMember(), channelmove).queue();

                        channels.add(channelmove);
                    });

                }
            } catch (SQLException throwables) {
            }

        }


    }

     */
}