package core;

import listeners.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.requests.GatewayIntent;

import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import util.*;

import javax.security.auth.login.LoginException;

public class DiscordBot{

    public static JDA jda;
    public JDABuilder builder;



    public DiscordBot() throws LoginException{
        //LiteSQL.connect();
        //SQLManager.onCreate();


        this.builder = JDABuilder.createDefault(Token.Token);

        builder
                .setAutoReconnect(true)
                .enableIntents(
                        GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.GUILD_PRESENCES)

                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .setChunkingFilter(ChunkingFilter.ALL)
                .addEventListeners(
                        new MessageListener(),
                        new VoiceListener(),
                        new GuildJoinListener(),
                        new ReadyListener()
                );
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.playing(Config.PREFIX+"help"));

        jda = this.builder.build();



        System.out.println("Bot online");




    }

    public static void main(String[] args) throws LoginException {
        new DiscordBot();
    }

}
