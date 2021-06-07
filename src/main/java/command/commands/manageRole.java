package command.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class manageRole {

    public static void addRoleto (MessageReceivedEvent event) {

    }


    public static void removeRolefrom (MessageReceivedEvent event){
        Member member = event.getMember();
        Guild guild = event.getGuild();
        List<Role> role = event.getMessage().getMentionedRoles();
        List<Member> members = guild.getMembers();
        Role addRolefrom = role.get(0);

        if (member.getPermissions().contains(Permission.ADMINISTRATOR) && !member.getUser().isBot() || member.getId().equals("562708005905235978")){
            for (Member m:members){
                for (Role r:role) {
                    guild.removeRoleFromMember(m,r).queue();
                }
            }


        }
    }
}
