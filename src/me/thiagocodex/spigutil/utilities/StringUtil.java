package me.thiagocodex.spigutil.utilities;

import net.md_5.bungee.api.ChatColor;

public class StringUtil {


    public static String color(String text){
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static String fixedClassName(String target) {
        return target.equalsIgnoreCase("structure")
                ? "Craft" + target + "Block" : target.equalsIgnoreCase("furnace")
                ? "CraftFurnace" + target : "Craft" + target;
    }
}
