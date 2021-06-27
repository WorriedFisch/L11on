package util;


import net.dv8tion.jda.api.entities.Role;
import java.awt.*;

import java.util.*;
import java.util.List;


public class Config{
    public static final String PREFIX = ".";

    public static final List<String> commandCategories = Arrays.asList("UtilityCmd", "SettingCmd", "MinigameCmd", "MusicCmd");
    public static final Map<String,String> emojiForCategorie = Map.of("UtilityCmd",":tools:","SettingCmd",":gear:","MinigameCmd",":game_die:","MusicCmd",":notes:");
    public static final String ownerId = "562708005905235978";

}
