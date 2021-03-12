package me.thiagocodex.spigutil.system;

import me.thiagocodex.spigutil.SpigUtil;
import org.bukkit.entity.Player;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.util.Date;
import java.util.TimeZone;

public class ServerSystem {
    private static TimeZone timeZoneObj = TimeZone.getDefault();

    public static void gc(Player player) {
        long oldFreeMem = Runtime.getRuntime().freeMemory();
        System.gc();
        long freeMem = Runtime.getRuntime().freeMemory();
        player.sendMessage("");
        player.sendMessage(SpigUtil.bundle.getString("pluginPrefix") + " " + MessageFormat.format(SpigUtil.bundle.getString("systemGCPerformed"), memConverter(freeMem - oldFreeMem)));
        player.sendMessage("");
    }

    private static String memConverter(double mem) {
        double value = mem / 1_000_000_000;
        return Double.toString(value).substring(0, 4) + "GB";
    }

    public static void setTimeZone(Player player, String timeZone) {
        TimeZone.setDefault(timeZoneObj);
        timeZoneObj = TimeZone.getTimeZone(timeZone);
        System.setProperty("user.timezone", String.valueOf(timeZone));
        player.sendMessage("");
        player.sendMessage(SpigUtil.bundle.getString("pluginPrefix") + " " +
                MessageFormat.format(SpigUtil.bundle.getString("systemTimeZoneSet"),
                        System.getProperty("user.timezone", String.valueOf(timeZone))));
        player.sendMessage("");
    }

    public static void time(Player player) {

        player.sendMessage(DateTime.date());
    }
}
