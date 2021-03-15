package me.thiagocodex.spigutil.utilities;

public class Util {



    public static String fixedClassName(String target) {
        return target.equalsIgnoreCase("structure")
                ? "Craft" + target + "Block" : target.equalsIgnoreCase("furnace")
                ? "CraftFurnace" + target : "Craft" + target;
    }
}
