package command.commands.UtilityCommands;

import command.CommandContext;
import command.ICommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import util.Config;

import java.util.List;

public class changeNicknameCommand implements ICommand {

    @Override
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

    @Override
    public String getName() {
        return "changenickname";
    }

    @Override
    public String getHelp() {
        return "Change ";
    }

    @Override
    public String getCategory() {
        return "UtilityCmd";
    }

    @Override
    public List<String> getAliases() {
        return List.of("ck");
    }
}
