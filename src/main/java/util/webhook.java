package util;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.WebhookClientBuilder;
import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;


public class webhook {
    public static void sendwebhook(String url,
                                   String author,
                                   String authoriconurl,
                                   String authorurl,
                                   String title,
                                   String titleurl,
                                   String description,
                                   String footer,
                                   String footerurl,
                                   int color
    ){


        WebhookClientBuilder builder = new WebhookClientBuilder(url); // or id, token

        builder.setThreadFactory((job) -> {
            Thread thread = new Thread(job);
            thread.setName("Hello");
            thread.setDaemon(true);
            return thread;
        });



        WebhookEmbed.EmbedAuthor embedAuthor = new WebhookEmbed.EmbedAuthor(author, authoriconurl, authorurl);
        WebhookEmbed.EmbedTitle embedTitle = new WebhookEmbed.EmbedTitle(title,titleurl);
        WebhookEmbed.EmbedFooter embedFooter = new WebhookEmbed.EmbedFooter(footer,footerurl);


        builder.setWait(true);
        WebhookClient client = builder.build();

        WebhookEmbed embed = new WebhookEmbedBuilder()
                .setDescription(description)
                .setAuthor(embedAuthor)
                .setTitle(embedTitle)
                .setColor(color)
                .setFooter(embedFooter)
                .setTimestamp(java.time.Instant.now())
                .build();

        client.send(embed);

    }
}
