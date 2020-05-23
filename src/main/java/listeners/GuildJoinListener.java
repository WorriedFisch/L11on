package listeners;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import util.Config;


public class GuildJoinListener extends ListenerAdapter {



    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event){
        Member member = event.getMember();
        Guild guild = event.getGuild();
        for (String roleId: Config.CategorieRoles) {

            guild.addRoleToMember(member, event.getGuild().getRoleById(roleId)).queue();

        }



    }
}
