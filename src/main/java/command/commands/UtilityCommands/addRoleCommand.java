package command.commands.UtilityCommands;

import command.CommandContext;
import command.ICommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import util.Config;

import java.util.List;

public class addRoleCommand implements ICommand {
    public void handle(CommandContext ctx) {


        GuildMessageReceivedEvent event = ctx.getEvent();

        Member member = event.getMember();
        Guild guild = event.getGuild();
        List<Role> role = event.getMessage().getMentionedRoles();
        List<Member> members = guild.getMembers();

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

    public String getName() {
        return "addRole";
    }

    public String getHelp() {
        return "add Role to all members";
    }

    public String getCategory() {
        return "UtilityCmd";
    }

    public List<String> getAliases() {
        return List.of("aR");
    }

    public List<OptionData> getOptionData() {
        return null;
    }

    public Permission getPermission() {
        return null;
    }
}

