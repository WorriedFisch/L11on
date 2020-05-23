package util;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.WebhookClientBuilder;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;
import core.Main;
import net.dv8tion.jda.api.entities.Webhook;
import net.dv8tion.jda.api.managers.WebhookManager;

public class webhooks {
    public static void webhook(){
        String url = "https://discordapp.com/api/webhooks/710816023137222657/KLLQFVbUz19Aw7OB0d3a_6V4zq5BjzsMFrcEq209TGKGYhdYz0ZO0Fk5p9wCLHiNEn_e";


        WebhookClientBuilder builder = new WebhookClientBuilder(url); // or id, token
        builder.setThreadFactory((job) -> {
            Thread thread = new Thread(job);
            thread.setName("Hello");
            thread.setDaemon(true);
            return thread;
        });
        builder.setWait(true);
        WebhookClient client = builder.build();

        client.send("test");

    }
}
