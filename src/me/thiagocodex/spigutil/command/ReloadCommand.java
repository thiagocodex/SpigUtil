package me.thiagocodex.spigutil.command;

import me.thiagocodex.spigutil.SpigUtil;
import org.bukkit.command.CommandSender;

public class ReloadCommand {

    public static void command(CommandSender commandSender, String s, String[] strings) {
        if (strings[0].equalsIgnoreCase("reload")) {

            if (s.equalsIgnoreCase("su") && strings[0].equalsIgnoreCase("reload")) {
                if (commandSender.hasPermission("spigutil.reload")) {
                    SpigUtil.loadReload();
                    commandSender.sendMessage(SpigUtil.bundle.getString("pluginPrefix") + " " + SpigUtil.bundle.getString("pluginReloaded"));
                }
            }
        }
    }
}
