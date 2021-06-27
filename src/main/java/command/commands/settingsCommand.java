package command.commands;

import command.CommandContext;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public abstract class settingsCommand {

    public void handle(CommandContext ctx) {

    }

    public String getName() {
        return "settings";
    }

    public String getHelp() {
        return null;
    }

    public String getCategory() {
        return null;
    }

    public abstract List<OptionData> getOptionData();

    public Permission getPermission() {
        return null;
    }

    public List<String> getAliases(){
        return List.of();
    }
}
