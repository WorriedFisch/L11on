package core;

import listeners.MessageListener;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import util.Config;
import util.Token;

import javax.security.auth.login.LoginException;


public class Main extends ListenerAdapter {

    public static void main(String[] Args) throws LoginException {

        JDABuilder builder = new JDABuilder();

        builder.setToken(Token.TOKEN);
        builder.setAutoReconnect(true);
        builder.addEventListeners(new MessageListener());
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.playing(Config.PREFIX+"help"));
        builder.build();



    }

}
