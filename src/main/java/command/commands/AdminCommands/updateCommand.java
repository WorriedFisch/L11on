package command.commands.AdminCommands;

import command.ICommand;
import core.CommandManager;
import core.LiteSQL;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.privileges.CommandPrivilege;
import util.Config;

import java.util.List;

import static core.DiscordBot.jda;

public class updateCommand implements ICommand {
    @Override
    public void handle(SlashCommandEvent event) {


        Guild guild = event.getGuild();

        event.deferReply(true).queue();
        event.getHook().editOriginal("updating all commands know").queue();

        updateCommands(guild);

        event.getHook().editOriginal("updated all commands successfully").queue();


    }

    public void updateCommands(Guild guild){
        guild.retrieveCommands().queue(commands -> {

            for (Command cmd:commands) {
                cmd.delete().queue();
            }

        });

        for (ICommand cmd: CommandManager.commands) {

            guild.upsertCommand(cmd.getCommandData()).queue(command -> {

                command.updatePrivileges(guild, List.of(
                        CommandPrivilege.enable(jda.getUserById(Config.ownerId)),
                        CommandPrivilege.enable(guild.getRoleById(791265130489839628L))
                        )
                ).queue();

            });
        }
    }

    @Override
    public String getName() {
        return "updatecommands";
    }

    @Override
    public String getHelp() {
        return "Update all Slash Commands";
    }

    @Override
    public String getCategory() {
        return "SettingCmd";
    }

    @Override
    public Permission getPermission() {
        return null;
    }

    @Override
    public List<String> getAliases() {
        return List.of("uc");
    }

    @Override
    public CommandData getCommandData() {
        return new CommandData(this.getName(), this.getHelp()).setDefaultEnabled(false);
    }


}
