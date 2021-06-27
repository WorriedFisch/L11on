package command.commands.MusicCommands;

import command.CommandContext;
import command.ICommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public class playCommand implements ICommand {
    public void handle(CommandContext ctx) {
        System.out.println("play");
    }

    public String getName() {
        return "play";
    }

    public String getHelp() {
        return "Connects to your current voicechannel if not connected and plays music by link or by yt search";
    }

    public String getCategory() {
        return "MusicCmd";
    }

    public List<String> getAliases() {
        return List.of("p");
    }

    public List<OptionData> getOptionData() {
        return List.of(
                new OptionData(OptionType.STRING, "link/query", "Plays a song with the given name or url.")

        );

    }

    public Permission getPermission() {
        return null;
    }
}
