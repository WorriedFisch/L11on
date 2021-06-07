package listeners;


import core.CommandManager;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.duncte123.botcommons.BotCommons;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import util.Config;

public class MessageListener extends ListenerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageListener.class);
    private final CommandManager manager = new CommandManager();

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        User user = event.getAuthor();

        if (user.isBot() || event.isWebhookMessage()) {
            return;
        }

        String prefix = Config.PREFIX;
        String raw = event.getMessage().getContentRaw();


        if (raw.equalsIgnoreCase(prefix + "shutdown")
                && user.getId().equals(Config.ownerId)) {
            LOGGER.info("Shutting down");
            event.getJDA().shutdown();
            BotCommons.shutdown(event.getJDA());
    }

        if (raw.startsWith(prefix)) {
            manager.handle(event);
        }



    }
}