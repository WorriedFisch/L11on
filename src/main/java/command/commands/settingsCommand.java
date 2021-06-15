package command.commands;

import command.CommandContext;
import command.ICommand;

public class settingsCommand implements ICommand {

    @Override
    public void handle(CommandContext ctx) {

    }

    @Override
    public String getName() {
        return "settings";
    }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public String getCategory() {
        return null;
    }
}
