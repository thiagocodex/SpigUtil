package me.thiagocodex.spigutil.utilities;

import me.thiagocodex.spigutil.config.CustomConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class LoaderUtil {
    public static double customZombieSpawnChance;
    public static List<String> customZombieSpawnReasonList;
    public static List<String> zombieEquipmentKeys = new ArrayList<>();
    public static List<String> customZombieEquipmentList = new ArrayList<>();
    public static String customZombieHelmetName;
    public static List<String> customZombieHelmetLores = new ArrayList<>();
    public static double helmetDropChance;
    public static boolean customNameVisible;
    public static String customZombieCustomName;
    public static double babyChance;


    public static double customCatSpawnChance;
    public static List<String> customCatSpawnReasonList;
    public static List<String> zombieHelmetEnchantments;

    public static boolean isTamed;
    public static String ownerMessage;

    public static void load() {
        //zombie
        customZombieSpawnChance = CustomConfig.getCustomMobs().getDouble("CustomZombie.SpawnChance");
        customZombieSpawnReasonList = CustomConfig.getCustomMobs().getStringList("CustomZombie.SpawnReasons");

        zombieEquipmentKeys.addAll(CustomConfig.getCustomMobs().getKeys(true).stream()
                .filter(s -> s.matches("CustomZombie\\.Equipment\\.\\w+$"))
                .collect(Collectors.toList()));

        customZombieEquipmentList.addAll(zombieEquipmentKeys.stream()
                .map(s -> s.replaceAll("CustomZombie\\.Equipment\\.", ""))
                .collect(Collectors.toList()));
        zombieHelmetEnchantments = CustomConfig.getCustomMobs().getStringList("CustomZombie.Equipment." + customZombieEquipmentList.get(0) + ".Enchantment");
        customZombieHelmetName = StringUtil.color(CustomConfig.getCustomMobs().getString("CustomZombie.Equipment." + customZombieEquipmentList.get(0) + ".CustomName"));
        helmetDropChance = CustomConfig.getCustomMobs().getDouble("CustomZombie.Equipment." + customZombieEquipmentList.get(0) + ".DropChance");
        customZombieHelmetLores
                .addAll(CustomConfig.getCustomMobs().getStringList("CustomZombie.Equipment." + customZombieEquipmentList.get(0) + ".CustomLore").stream()
                        .map(StringUtil::color)
                        .collect(Collectors.toList()));

        customZombieCustomName = StringUtil.color(CustomConfig.getCustomMobs().getString("CustomZombie.CustomName"));
        customNameVisible = CustomConfig.getCustomMobs().getBoolean("CustomZombie.CustomNameVisible");
        babyChance = CustomConfig.getCustomMobs().getDouble("CustomZombie.BabyChance");


        //cat
        customCatSpawnReasonList = CustomConfig.getCustomMobs().getStringList("CustomCat.SpawnReasons");
        customCatSpawnChance = CustomConfig.getCustomMobs().getDouble("CustomCat.SpawnChance");
        isTamed = CustomConfig.getCustomMobs().getBoolean("CustomCat.IsTamed");
        ownerMessage = StringUtil.color(CustomConfig.getCustomMobs().getString("CustomCat.OwnerMessage"));
    }
}
