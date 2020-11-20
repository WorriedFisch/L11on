package core;

import listeners.*;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import util.*;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main extends ListenerAdapter {

    public static JDA jda;
    public static JDABuilder jdaBuilder = new JDABuilder(AccountType.BOT);
    public static void main(String[] args) throws LoginException{
        //LiteSQL.connect();
        //SQLManager.onCreate();


        jda = jdaBuilder.createDefault(Token.Token)
                .setAutoReconnect(true)
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .enableIntents(GatewayIntent.GUILD_PRESENCES)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .setChunkingFilter(ChunkingFilter.ALL)
                .addEventListeners(
                new MessageListener(),
                new VoiceListener(),
                new GuildJoinListener()
        ).build();



        jdaBuilder.setStatus(OnlineStatus.ONLINE);
        jdaBuilder.setActivity(Activity.playing(Config.PREFIX+"help"));




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
                        if(jdaBuilder != null){
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
        jdaBuilder.setStatus(OnlineStatus.OFFLINE);
        jda.shutdown();
        LiteSQL.disconnect();
        System.out.println("Bot offline");
        System.exit(0);
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
