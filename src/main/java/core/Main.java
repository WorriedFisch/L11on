package core;

import listeners.*;
import net.dv8tion.jda.api.OnlineStatus;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import util.*;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main extends ListenerAdapter {


    public static ShardManager shardMan;
    public static void main(String[] args) throws LoginException{

        LiteSQL.connect();
        SQLManager.onCreate();


        DefaultShardManagerBuilder builder = new DefaultShardManagerBuilder();

        builder.setToken(Token.TOKEN);
        builder.setAutoReconnect(true);
        builder.addEventListeners(
                new MessageListener(),
                new VoiceListener(),
                new GuildJoinListener(),
                new ReactionRoleListener()
        );

        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.playing(Config.PREFIX+"help"));

        shardMan = builder.build();


        System.out.println("Bot online");

        BufferedReader();
        timer();
    }


    public static void BufferedReader(){
        new Thread(() -> {

            String line = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                while((line = reader.readLine()) != null){
                    if (line.equalsIgnoreCase("exit")) {
                        if(shardMan != null){
                            shutdown();
                        }
                    }else {
                        System.out.println("Use 'exit' to shutdown.");
                    }
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }
    public static boolean shutdown = false;

    public static void shutdown(){
        shutdown = true;
        shardMan.setStatus(OnlineStatus.OFFLINE);
        shardMan.shutdown();
        LiteSQL.disconnect();
        System.out.println("Bot offline");
    }

    public static void timer() {
        new Thread(() -> {
            try {
                long time = System.currentTimeMillis();
                while(!shutdown){
                    if (System.currentTimeMillis() >= time + 1000){
                        time = System.currentTimeMillis();
                        Infos.main();

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                timer();
            }
        }).start();
    }
}
