package me.thiagocodex.spigutil.system;

import me.thiagocodex.spigutil.SpigUtil;
import org.bukkit.entity.Player;

import java.text.MessageFormat;
import java.time.ZoneId;
import java.util.TimeZone;

public class ServerSystem {
    public static void gc(Player player) {
        long oldFreeMem = Runtime.getRuntime().freeMemory();
        System.gc();
        long freeMem = Runtime.getRuntime().freeMemory();
        player.sendMessage("");
        player.sendMessage(SpigUtil.bundle.getString("pluginPrefix") +" "+ MessageFormat.format(SpigUtil.bundle.getString("systemGCPerformed"), memConverter(freeMem - oldFreeMem)));
        player.sendMessage("");
    }

    private static String memConverter(double mem) {
        double value = mem / 1_000_000_000;
        return Double.toString(value).substring(0, 4) + "GB";
    }

    public static void setTimeZone(Player player, String timeZone) {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of(timeZone)));

        player.sendMessage("");
        player.sendMessage(SpigUtil.bundle.getString("pluginPrefix") + " " +
                MessageFormat.format(SpigUtil.bundle.getString("systemTimeZoneSet"), System.setProperty("user.timezone", timeZone)));
        player.sendMessage("");
    }

    public static String info1() {
        return "Free Memory: " + Runtime.getRuntime().freeMemory();
    }

    public static String info2() {
        System.gc();
        return "Free Memory: " + Runtime.getRuntime().freeMemory();
    }
}
