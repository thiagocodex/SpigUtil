package me.thiagocodex.spigutil.system.command;

import me.thiagocodex.spigutil.Commander;
import me.thiagocodex.spigutil.SpigUtil;
import me.thiagocodex.spigutil.system.ServerSystem;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.TimeZone;

public class ServerSystemCommand {

    public static void command(CommandSender commandSender, String s, String[] strings) {

        if (s.equalsIgnoreCase("su")) {
            if (strings[0].equalsIgnoreCase("system")) {

                switch (strings[1]) {
                    case "settimezone":
                        if (Arrays.asList(TimeZone.getAvailableIDs()).contains(strings[2])) {
                            ServerSystem.setTimeZone((Player) commandSender, strings[2]);
                        } else {
                            commandSender.sendMessage("erro teste");
                        }
                        break;
                    case "gc":
                        ServerSystem.gc((Player) commandSender);
                        break;
                }
            }
        }
    }

    public static void complete(CommandSender commandSender, String s, String[] strings) {
        Commander.complete.clear();

        if (s.equalsIgnoreCase("su")) {
            if ((strings.length == 3) && strings[1].equalsIgnoreCase("settimezone")) {

                for (String element : SpigUtil.zones) {
                    for (int i = 1; i <= element.length(); i++) {
                        if (element.substring(0, i).equalsIgnoreCase(strings[2])) {
                            Commander.complete.add(element);
                        } else if (strings[2].length() == 0) {
                            Commander.complete.addAll(Arrays.asList(SpigUtil.zones));
                        }
                    }
                }
            }
        }
    }
}
