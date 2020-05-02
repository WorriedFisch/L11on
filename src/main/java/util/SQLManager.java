package util;

import core.LiteSQL;

import static org.sqlite.SQLiteConfig.DateClass.TEXT;


public class SQLManager {

    public static void onCreate(){

        //id guildId channelId messageId emote roleId

        LiteSQL.onUpdate("CREATE TABLE IF NOT EXISTS reactroles(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, guildid INTEGER, channelid INTEGER, messageid INTEGER, emote TEXT, cemoteid INTEGER, rollenid INTEGER, type TEXT) ");

    }
}
