package core;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import util.SECRETS;

public class Main {

    public static void main(String[] Args){

        JDABuilder builder = new JDABuilder();

        builder.setToken(SECRETS.TOKEN);
        builder.setAutoReconnect(true);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.playing("Krasser Bot"));

    }
}
