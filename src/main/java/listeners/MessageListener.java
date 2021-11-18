package listeners;

        import command.ICommand;
        import core.CommandManager;
        import net.dv8tion.jda.api.EmbedBuilder;
        import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
        import net.dv8tion.jda.api.interactions.commands.privileges.CommandPrivilege;
        import org.jetbrains.annotations.NotNull;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;

        import me.duncte123.botcommons.BotCommons;
        import net.dv8tion.jda.api.entities.*;
        import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
        import net.dv8tion.jda.api.hooks.ListenerAdapter;

        import util.Config;

        import java.util.List;

        import static core.DiscordBot.jda;

public class MessageListener extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {

        Guild guild = event.getGuild();

        if (event.getMessage().getContentDisplay().contains("update")){

            if (event.getAuthor().getId().equals(Config.ownerId)){
                for (ICommand cmd: CommandManager.commands) {

                    guild.upsertCommand(cmd.getCommandData()).queue(command -> {

                        command.updatePrivileges(guild, List.of(
                                        CommandPrivilege.enable(jda.getUserById(Config.ownerId))
                                        //CommandPrivilege.enable(guild.getRoleById(791265130489839628L))
                                )
                        ).queue();

                    });
                }

            }
        }
    }
}