package listeners;

import core.LiteSQL;
import functions.stickyRoles;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;


public class GuildJoinListener extends ListenerAdapter {




    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event){
        stickyRoles.guildMemberJoin(event);


    }

    @Override
    public void onGuildMemberLeave(GuildMemberLeaveEvent event) {
        stickyRoles.guildMemberLeave(event);


    }

    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        Long guildId = event.getGuild().getIdLong();

        //LiteSQL.onUpdate("INSERT IGNORE INTO guildSettings (guildId, stickyRoles) VALUES(" + guildId + ", 0");


    }
}
