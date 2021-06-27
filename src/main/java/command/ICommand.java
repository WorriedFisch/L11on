package command;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public interface  ICommand {
    void handle(CommandContext ctx);

    String getName();

    String getHelp();

    String getCategory();

    List<OptionData> getOptionData();

    default Permission getPermission() {
        return null;
    }

    default List<String> getAliases(){
        return List.of();
    }

}
