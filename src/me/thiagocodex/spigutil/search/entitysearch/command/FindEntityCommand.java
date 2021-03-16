package me.thiagocodex.spigutil.search.entitysearch.command;

import me.thiagocodex.spigutil.search.EntityFinder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FindEntityCommand {



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

    public static final String[] mobs = {"bat", "bee", "blaze", "cat", "cave_spider", "chicken", "cod", "cow", "creeper", "dolphin", "donkey", "drowned", "elder_guardian", "ender_dragon", "enderman", "endermite", "evoker", "fox", "ghast", "giant", "guardian", "hoglin", "horse", "hostile", "husk", "illusioner", "iron_golem", "llama", "magma_cube", "mooshroom", "mule", "ocelot", "panda", "parrot", "phantom", "pig", "piglin", "piglin_brute pillager", "polar_bear", "pufferfish", "rabbit", "ravager", "salmon", "sheep", "shulker", "silverfish", "skeleton", "skeleton_horse", "slime", "snow_golem", "spider", "squid", "stray", "strider", "trader_llama", "tropical_fish", "turtle", "vex", "villager", "vindicator", "wandering_trader", "witch", "wither", "wither_skeleton", "wolf", "zoglin", "zombie", "zombie_horse", "zombie_pigman", "zombie_villager", "zombified_piglin"};
}
