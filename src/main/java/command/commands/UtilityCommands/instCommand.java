package command.commands.UtilityCommands;


import com.fasterxml.jackson.databind.JsonNode;
import command.ICommand;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

import java.util.Collections;
import java.util.List;

public class instCommand implements ICommand {
    public void handle(SlashCommandEvent event) {
        final String args = event.getOption("name").toString();
        final TextChannel channel = event.getTextChannel();

        final String usn = args;

        WebUtils.ins.getJSONObject("https://apis.duncte123.me/insta/" + usn).async((json) -> {
            if (!json.get("success").asBoolean()) {
                channel.sendMessage(json.get("error").get("message").asText()).queue();
                return;
            }

            final JsonNode user = json.get("user");
            final String username = user.get("username").asText();
            final String pfp = user.get("profile_pic_url").asText();
            final String biography = user.get("biography").asText();
            final boolean isPrivate = user.get("is_private").asBoolean();
            final int following = user.get("following").get("count").asInt();
            final int followers = user.get("followers").get("count").asInt();
            final int uploads = user.get("uploads").get("count").asInt();

            final EmbedBuilder embed = new EmbedBuilder();
            embed
                    .setTitle("\"Instagram info of \" + username, \"https://www.instagram.com/" + username)
                    .setDescription(String.format(
                            "**Private account:** %s\n**Bio:** %s\n**Following:** %s\n**Followers:** %s\n**Uploads:** %s",
                            toEmote(isPrivate),
                            biography,
                            following,
                            followers,
                            uploads
                    ))
                    .setImage(getLatestImage(json.get("images")));
            event.replyEmbeds(embed.build()).queue();
        });
    }

    @Override
    public String getName() {
        return "instagram";
    }

    @Override
    public List<String> getAliases() {
        return Collections.singletonList("insta");
    }

    @Override
    public CommandData getCommandData() {
        return new CommandData(getName(), getHelp()).addOption(OptionType.STRING,"name", "insta name");
    }

    @Override
    public String getHelp() {
        return "Shows instagram statistics of a user with the latest image\n" +
                "Usage: `!!instagram <username>`";
    }

    @Override
    public String getCategory() {
        return null;
    }

    private String getLatestImage(JsonNode json) {
        if (!json.isArray()) {
            return null;
        }

        if (json.size() == 0) {
            return null;
        }

        return json.get(0).get("url").asText();
    }

    private String toEmote(boolean bool) {
        return bool ? "<:sliderRight:582718257598038017>" : "<:sliderLeft:582718257866473472>";
    }
}
