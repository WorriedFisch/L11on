package listeners;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import util.Config;

import java.io.File;


public class GuildJoinListener extends ListenerAdapter {



    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event){
        Member member = event.getMember();

    }
}
