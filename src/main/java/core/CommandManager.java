package core;

import command.ICommand;
import command.commands.UtilityCommands.*;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    public static List<ICommand> commands = new ArrayList<>();

    public CommandManager(){

        addCommand(new pingCommand());
        addCommand(new helpCommand(this));
        //addCommand(new minigames.g2048());
        addCommand(new voiceMoveCommand());
        addCommand(new changeNicknameCommand());

        addCommand(new addRoleCommand());
        addCommand(new removeRoleCommand());

        addCommand(new purgeCommand());

    }

    private void addCommand(ICommand cmd){
        boolean nameFound = this.commands.stream().anyMatch((it) -> it.getName().equalsIgnoreCase(cmd.getName()));

        if (nameFound){
            throw new IllegalArgumentException("A command with this name is already pressent");
        }

        commands.add(cmd);
    }
    public List<ICommand> getCommands(String category) {

        List<ICommand> commands = new ArrayList<ICommand>();

        for (ICommand cmd : this.commands) {
            if (cmd.getCategory().equalsIgnoreCase(category)) {
                commands.add(cmd);
            }
        }
        return commands;
    }
    public List<ICommand> getCommands() {
        return commands;
    }

    @Nullable
    public ICommand getCommand(String search){
        String searchLower = search;

        for (ICommand cmd : this.commands) {
            if (cmd.getName().equalsIgnoreCase(searchLower) || cmd.getAliases().contains(searchLower)) {
                return cmd;
            }
        }

        return null;
    }

    public void handle(SlashCommandEvent event) {


        ICommand cmd = this.getCommand(event.getName());

        if (cmd != null) {

            cmd.handle(event);
        }

    }

}