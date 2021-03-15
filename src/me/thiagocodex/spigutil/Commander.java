package me.thiagocodex.spigutil;

import me.thiagocodex.spigutil.command.ReloadCommand;
import me.thiagocodex.spigutil.search.BlockStateFinder;
import me.thiagocodex.spigutil.search.EntityFinder;
import me.thiagocodex.spigutil.search.blockstatesearch.FindBlockState;
import me.thiagocodex.spigutil.search.blockstatesearch.command.FindBlockStateCommand;
import me.thiagocodex.spigutil.search.entitysearch.command.FindEntity;
import me.thiagocodex.spigutil.search.entitysearch.command.FindEntityCommand;
import me.thiagocodex.spigutil.search.spawnersearch.FindSpawner;
import me.thiagocodex.spigutil.search.spawnersearch.command.FindSpawnerCommand;
import me.thiagocodex.spigutil.system.ServerSystem;
import me.thiagocodex.spigutil.system.command.ServerSystemCommand;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Commander implements CommandExecutor, TabCompleter {


    public static void commonEntityCommand(EntityFinder tofind, CommandSender commandSender, String s, String[] strings) {

        if (s.equalsIgnoreCase("su")) {
            if (strings[0].equalsIgnoreCase("locate")) {

                //test
                tofind.setPlayer(((Player) commandSender));
                tofind.setTarget(strings[2]);
                tofind.setAmount(Byte.parseByte(strings[3]));
                tofind.search(((Player) commandSender));
            }
        }
    }


    public static void commonBlockStateCommand(BlockStateFinder toFind, CommandSender commandSender, String s, String[] strings) {
        if (s.equalsIgnoreCase("su")) {
            if ((strings.length == 4)
                    && strings[0].equalsIgnoreCase("locate")
                    && strings[1].equalsIgnoreCase("blockstate")
                    || strings[1].equalsIgnoreCase("spawner")) {
                boolean error = false;
                toFind.setPlayer((Player) commandSender);
                toFind.setTarget(strings[2]);
                try {
                    toFind.setAmount(Byte.parseByte(strings[3]));
                } catch (NumberFormatException e) {
                    error = true;
                }
                if (!error) {
                    toFind.search();
                } else {
                    commandSender.sendMessage(ChatColor.RED + "The amount number needs to be less than " + Byte.MAX_VALUE);
                }
            } else {
                commandSender.sendMessage(ChatColor.RED + "Invalid command. Usage: su locate blockstate <blockstatetype> <amount>");
            }
        }
    }

    public static void commonComplete(String[] array, String arg, String s, String[] strings) {
        complete.clear();
        if (s.equalsIgnoreCase("su")) {
            if ((strings.length == 3) && strings[1].equalsIgnoreCase(arg)) {
                for (String element : array) {
                    for (int i = 1; i <= element.length(); i++) {
                        if (element.substring(0, i).equalsIgnoreCase(strings[2])) {
                            complete.add(element);
                        } else if (strings[2].length() == 0) {
                            complete.addAll(Arrays.asList(array));
                        }
                    }
                }
            } else if (strings.length == 4) {
                complete.add("1");
                complete.add("3");
                complete.add("5");
                complete.add("9");
            }
        }
    }


    public static final List<String> complete = new ArrayList<>();


    @Override
    public boolean onCommand(@Nonnull CommandSender commandSender, @Nonnull Command command, @Nonnull String s, String[] strings) {
        switch (strings[0]) {


            case "reload":
                ReloadCommand.command(commandSender, s, strings);
                break;

            case "locate":
                switch (strings[1]) {
                    case "blockstate":
                        commonBlockStateCommand(FindBlockState.getInstance(), commandSender, s, strings);
                        break;
                    case "spawner":
                        commonBlockStateCommand(FindSpawner.getInstance(), commandSender, s, strings);
                        break;
                    case "entity":
                        commonEntityCommand(FindEntity.getInstance(), commandSender, s, strings);
                        break;
                }
                break;

            case "system":
                switch (strings[1]) {
                    case "settimezone":
                        ServerSystemCommand.command(commandSender, s, strings);
                        break;
                    case "gc":
                        ServerSystem.gc((Player) commandSender);
                        break;
                    case "time":
                        ServerSystem.time((Player) commandSender);
                        break;
                }
                break;
        }
        return true;
    }


    @Override
    public List<String> onTabComplete(@Nonnull CommandSender commandSender, @Nonnull Command command, String s, @Nonnull String[] strings) {
        if (s.equalsIgnoreCase("su")) {
            complete.clear();
            if (strings.length <= 1) {
                complete.add("locate");
                complete.add("reload");
                complete.add("system");
            } else {
                switch (strings[0]) {
                    case "locate":
                        complete.add("blockstate");
                        complete.add("spawner");
                        switch (strings[1]) {
                            case "blockstate":
                                commonComplete(FindBlockStateCommand.blockStateArray, strings[1], s, strings);
                                break;
                            case "spawner":
                                commonComplete(FindSpawnerCommand.spawnedTypes, strings[1], s, strings);
                                break;
                            case "entity":
                                commonComplete(FindEntityCommand.mobs, strings[1], s, strings);
                                break;
                            default:
                        }
                        break;
                    case "system":
                        complete.add("time");
                        complete.add("gc");
                    default:
                }
            }
        }
        Collections.sort(complete);
        return complete;
    }

}
