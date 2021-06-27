package listeners;

import core.CommandManager;
import me.duncte123.botcommons.BotCommons;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Config;

public class SlashCommandListener extends ListenerAdapter  {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageListener.class);
    public static CommandManager manager = new CommandManager();


    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        User user = event.getUser();

        if (user.isBot()) {
            return;
        }

        String prefix = Config.PREFIX;
        String raw = event.getName();


        if (raw.equalsIgnoreCase(prefix + "shutdown")
                && user.getId().equals(Config.ownerId)) {
            LOGGER.info("Shutting down");
            event.getJDA().shutdown();
            BotCommons.shutdown(event.getJDA());
        }

        manager.handle(event);

    }
}
