package minigames;

import command.CommandContext;
import command.ICommand;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.javatuples.Pair;
import org.javatuples.Tuple;

import java.util.HashMap;
import java.util.Random;


public class g2048 implements ICommand {


    private static HashMap<Long, Pair<Integer, Message>> id = new HashMap<Long,Pair<Integer, Message>>();

    private static int[][][] field = new int[4][4][1000];

    private static MessageChannel channel;
    private static int userId;

    public static void spawn(){


        int spawnNumber;
        int xAxis;
        int yAxis;
        int probability;

        Random random = new Random();

        xAxis = random.nextInt(4);
        yAxis = random.nextInt(4);
        probability = random.nextInt(10);

        if (probability == 0){
            spawnNumber = 4;
        }else{
            spawnNumber = 2;
        }
        if (field[xAxis][yAxis][userId] == 0){
            field[xAxis][yAxis][userId] = spawnNumber;
        }
    }

    public static void up(){
        for (int y = 3; y > 0; y--){
            for (int x = 0; x < 4; x++){
                if (field[x][y][userId] == field[x][y - 1][userId] && field[x][y][userId] != 0){
                    field[x][y - 1][userId] = field[x][y - 1][userId]*2;
                    field[x][y][userId] = 0;
                }else if (field[x][y - 1][userId] == 0){
                    field[x][y - 1][userId] = field[x][y][userId];
                    field[x][y][userId] = 0;
                }
            }
        }

        print();
    }

    public static void down(){
        for (int y = 0; y < 3; y++){
            for (int x = 0; x < 4; x++){
                if (field[x][y][userId] == field[x][y + 1][userId] && field[x][y][userId] != 0){
                    field[x][y + 1][userId] = field[x][y + 1][userId]*2;
                    field[x][y][userId] = 0;

                }else if (field[x][y + 1][userId] == 0){
                    field[x][y + 1][userId] = field[x][y][userId];
                    field[x][y][userId] = 0;
                }
            }
        }

        print();
    }
    public static void left(){}

    public static void right(){}

    private static void print(){

        String message = "```json\n";

        for (int y = 0; y < field.length; ++y) {
            for(int x = 0; x < field[y].length - 1; ++x) {
                message = message + field[x][y][userId] + " | ";
            }
            message = message + field[3][y][userId];

            message = message + "\n";
        }

        message = message + "```";

        channel.sendMessage(message).queue();

    }

    @Override
    public void handle(CommandContext ctx) {

        GuildMessageReceivedEvent event = ctx.getEvent();



        channel = event.getChannel();
        userId = 1;


        spawn();
        spawn();
        print();


    }

    @Override
    public String getName() {
        return "2048";
    }

    @Override
    public String getHelp() {
        return "Play 2048 in discord";
    }
}
