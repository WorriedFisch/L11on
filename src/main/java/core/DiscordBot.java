package core;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
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
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class DiscordBot {

    public static JDA jda;
    public JDABuilder builder;
    public final AudioPlayerManager playerManager = new DefaultAudioPlayerManager();

    //public static Logger logger = Logger.getLogger("MyLog");



    public DiscordBot() throws LoginException{
        LiteSQL.connect();

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
                        new ReadyListener(),
                        new ButtonListener(),
                        new SlashCommandListener()
                );
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.playing(Config.PREFIX+"help"));

        jda = this.builder.build();



        System.out.println("Bot online");




    }

    public static void main(String[] args) throws LoginException {
        new DiscordBot();
        /*
        FileHandler fh;

        try {

            // This block configure the logger with handler and formatter
            fh = new FileHandler("./MyLogFile.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            // the following statement is used to log any messages
            logger.info("Bot started");

        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }

         */
    }

}
