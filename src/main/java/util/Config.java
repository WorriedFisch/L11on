package util;

import core.Main;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Config{
    public static final String GUILDID = "611583174723764234";
    public static final Role adminRole = Main.shardMan.getGuildById(GUILDID).getRoleById("634748404991262721");
    public static final String PREFIX = ".";
    public static final EmbedBuilder HELPMASSAGE = EmbedBuilder.EmbedBuilder("Help",null, Color.GREEN,"Help isn't configured actually \n Vorschläge erwünscht");    //"Hilfe kriegst du bei <@418861325435797514>";
    public static final List<String> CategorieRoles = Arrays.asList("694931364725784647","704298093893648465","695270237049978920","704291838017798207","695270305236779110");
    public static final Long OwnerId = 418861325435797514l;
    public static final String TOKEN = "Njg3MjYwMDY0NDY5MjIxNDEw.Xq08Mg.Oe1raAO2_xXor_Ym22F6HGHt-P8";
    public static final Role Verifiziertrole = Main.shardMan.getGuildById(GUILDID).getRoleById("696415745885732964");

}
