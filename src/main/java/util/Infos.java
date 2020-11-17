package util;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static core.Main.jda;

public class Infos {
    public static void main() {
        /*
        TimeandDate();
        Useranzahl();
        Online();
         */
    }
    public static void TimeandDate() {


        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Guild guild = jda.getGuildById(Config.GUILDID);
        VoiceChannel channel = guild.getVoiceChannelById(Config.DateChannelId);
        channel.getManager().setName("📅"+ dateFormat.format(new Date())).complete();

    }
    public static void Useranzahl(){
        Guild guild = jda.getGuildById(Config.GUILDID);
        VoiceChannel channel = guild.getVoiceChannelById(Config.memberCountChannelId);
        channel.getManager().setName("\uD83D\uDCC8Useranzahl: "+guild.getMemberCount()).complete();
    }
    public static void Online(){
        int onlineMembers = 0;
        Guild guild = jda.getGuildById(Config.GUILDID);
        VoiceChannel channel = guild.getVoiceChannelById(Config.onlineMemberCountId);
        List<Member> members = guild.getMembers();
        for (Member m: members) {
            if (m.getOnlineStatus() != OnlineStatus.OFFLINE){
                onlineMembers++;
            }
        }
        channel.getManager().setName("\uD83D\uDD35Online: "+ (onlineMembers-6)).complete();
    }
}