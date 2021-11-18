package listeners;

import command.ICommand;
import command.commands.UtilityCommands.helpCommand;
import core.CommandManager;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import util.Config;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static java.time.format.DateTimeFormatter.*;


public class ButtonListener extends ListenerAdapter {
    @Override
    public void onButtonClick(ButtonClickEvent event) {

        String componentId = event.getComponentId();

        ICommand command  = CommandManager.commands.
                stream()
                .filter(cmd -> cmd.getButtons().contains(componentId)).findFirst().get();

        command.ButtonAction(event);


    }
}
