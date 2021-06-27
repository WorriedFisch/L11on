package command.commands.UtilityCommands;

import command.CommandContext;
import command.ICommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import util.Config;

import java.util.List;

public class changeNicknameCommand implements ICommand {

    public void handle(CommandContext ctx) {

        GuildMessageReceivedEvent event = ctx.getEvent();
        List<Role> roles = event.getMessage().getMentionedRoles();



        MessageChannel channel = event.getChannel();
        String nickname = event.getMessage().getContentDisplay().split(" ")[1];
        Guild guild = event.getGuild();

        Member executingMember = event.getMember();


        if (!executingMember.hasPermission(Permission.NICKNAME_MANAGE) || !executingMember.getId().equals(Config.ownerId))   {
            channel.sendMessage("You don't have the permission to Change the Nicknames").queue();
            return;
        }

        if (roles.size() == 0){
            channel.sendMessage("You need to specify a Role").queue();
            return;
        }


            if (executingMember.hasPermission(Permission.NICKNAME_MANAGE) || executingMember.getId().equals(Config.ownerId)){
                for (Role role: roles) {
                    for (Member member:guild.getMembersWithRoles(role)) {
                        System.out.println(member);
                        member.modifyNickname(nickname).queue();
                    }
                }
            }
        }

    public String getName() {
        return "changenickname";
    }

    public String getHelp() {
        return "Change the Nickname of all Members with a role ";
    }

    public String getCategory() {
        return "UtilityCmd";
    }

    public List<String> getAliases() {
        return List.of("ck");
    }

    public List<OptionData> getOptionData() {
        return null;
    }

    public Permission getPermission() {
        return null;
    }
}
