package command.commands.UtilityCommands;

import command.CommandContext;
import command.ICommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import util.Config;

import java.util.List;

public class addRoleCommand implements ICommand{
    @Override
    public void handle(CommandContext ctx) {


        GuildMessageReceivedEvent event = ctx.getEvent();

        Member member = event.getMember();
        Guild guild = event.getGuild();
        List<Role> role = event.getMessage().getMentionedRoles();
        List<Member> members = guild.getMembers();
        Role addRolefrom = role.get(0);

        if ((member.getPermissions().contains(Permission.ADMINISTRATOR) && !member.getUser().isBot()) || member.getId().equals(Config.ownerId)) {
            for (Member m : members) {
                for (Role r : role) {
                    if (!m.getUser().isBot()){
                        guild.addRoleToMember(m, r).queue();
                    }
                }
            }


        }
    }

    @Override
    public String getName() {
        return "addRole";
    }

    @Override
    public String getHelp() {
        return "add Role to all members";
    }

    @Override
    public String getCategory() {
        return "UtilityCmd";
    }

    @Override
    public List<String> getAliases() {
        return List.of("aR");
    }
    
}

