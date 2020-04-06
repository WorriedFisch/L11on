package util;



import java.awt.*;

public class EmbedBuilder extends net.dv8tion.jda.api.EmbedBuilder {
    public static EmbedBuilder EmbedBuilder(String title, String author, Color color, String description){

        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle(title);
        embedBuilder.setAuthor(author);
        embedBuilder.setColor(color);
        embedBuilder.setDescription(description);


        return embedBuilder;
    }






}
