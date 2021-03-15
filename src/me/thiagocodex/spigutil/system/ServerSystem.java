package me.thiagocodex.spigutil.system;

import me.thiagocodex.spigutil.SpigUtil;
import me.thiagocodex.spigutil.system.datetime.DateTime;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

import java.text.MessageFormat;

public class ServerSystem {

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

    public static void time(Player player) {
        player.sendMessage("");
        player.sendMessage(ChatColor.GOLD +  DateTime.date());
        player.sendMessage("");
    }
}
