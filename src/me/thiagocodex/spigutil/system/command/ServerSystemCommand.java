package me.thiagocodex.spigutil.system.command;

import me.thiagocodex.spigutil.system.ServerSystem;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ServerSystemCommand {

    public static void command(CommandSender commandSender, String s, String[] strings) {

        if (s.equalsIgnoreCase("su")) {
            if (strings[0].equalsIgnoreCase("system")) {

                switch (strings[1]) {
                    case "gc":
                        ServerSystem.gc((Player) commandSender);
                        break;
                    case "time":
                        ServerSystem.time((Player) commandSender);
                }
            }
        }
    }
}
