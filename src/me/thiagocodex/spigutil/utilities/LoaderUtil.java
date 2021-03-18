package me.thiagocodex.spigutil.utilities;

import me.thiagocodex.spigutil.config.CustomConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LoaderUtil {
    public static final List<Integer> mobAmount = new ArrayList<>();
    public static final List<String> mobCustomName = new ArrayList<>();
    public static final List<Boolean> mobCustomNameVisible = new ArrayList<>();
    public static final List<Double> mobSpawnChance = new ArrayList<>();
    public static final List<Double> mobBabyChance = new ArrayList<>();
    public static final List<List<String>> mobSpawnReasons = new ArrayList<>();
    public static final List<String> mobHelmetMaterial = new ArrayList<>();
    public static final List<List<String>> mobHelmetEnchantments = new ArrayList<>();
    public static final List<String> mobHelmetDisplayName = new ArrayList<>();
    public static final List<List<String>> mobHelmetLores = new ArrayList<>();
    public static final List<Double> mobHelmetDropChance = new ArrayList<>();
    public static final List<String> mobChestPlateMaterial = new ArrayList<>();
    public static final List<List<String>> mobChestPlateEnchantments = new ArrayList<>();
    public static final List<String> mobChestPlateDisplayName = new ArrayList<>();
    public static final List<List<String>> mobChestPlateLores = new ArrayList<>();
    public static final List<Double> mobChestPlateDropChance = new ArrayList<>();
    public static final List<String> mobMainHandMaterial = new ArrayList<>();
    public static final List<List<String>> mobMainHandEnchantments = new ArrayList<>();
    public static final List<String> mobMainHandDisplayName = new ArrayList<>();
    public static final List<List<String>> mobMainHandLores = new ArrayList<>();
    public static final List<Double> mobMainHandDropChance = new ArrayList<>();
    public static final List<String> mobOffHandMaterial = new ArrayList<>();
    public static final List<List<String>> mobOffHandEnchantments = new ArrayList<>();
    public static final List<String> mobOffHandDisplayName = new ArrayList<>();
    public static final List<List<String>> mobOffHandLores = new ArrayList<>();
    public static final List<Double> mobOffHandDropChance = new ArrayList<>();
    public static final List<String> mobLeggingsMaterial = new ArrayList<>();
    public static final List<List<String>> mobLeggingsEnchantments = new ArrayList<>();
    public static final List<String> mobLeggingsDisplayName = new ArrayList<>();
    public static final List<List<String>> mobLeggingsLores = new ArrayList<>();
    public static final List<Double> mobLeggingsDropChance = new ArrayList<>();
    public static final List<String> mobBootsMaterial = new ArrayList<>();
    public static final List<List<String>> mobBootsEnchantments = new ArrayList<>();
    public static final List<String> mobBootsDisplayName = new ArrayList<>();
    public static final List<List<String>> mobBootsLores = new ArrayList<>();
    public static final List<Double> mobBootsDropChance = new ArrayList<>();
    public static final List<?>[] objects = {mobAmount, mobCustomName, mobCustomNameVisible, mobSpawnChance, mobBabyChance, mobSpawnReasons, mobHelmetMaterial, mobHelmetEnchantments, mobHelmetDisplayName, mobHelmetLores, mobHelmetDropChance, mobChestPlateMaterial, mobChestPlateEnchantments, mobChestPlateDisplayName, mobChestPlateLores, mobChestPlateDropChance, mobMainHandMaterial, mobMainHandEnchantments, mobMainHandDisplayName, mobMainHandLores, mobMainHandDropChance, mobOffHandMaterial, mobOffHandEnchantments, mobOffHandDisplayName, mobOffHandLores, mobOffHandDropChance, mobLeggingsMaterial, mobLeggingsEnchantments, mobLeggingsDisplayName, mobLeggingsLores, mobLeggingsDropChance, mobBootsMaterial, mobBootsEnchantments, mobBootsDisplayName, mobBootsLores, mobBootsDropChance};

    public static void load() {
        for (List<?> toClear : objects) {
            toClear.clear();
        }
        String keys = CustomConfig.getCustomMobs().getKeys(true).toString();
        String[] subsKeys = keys.replaceAll("CustomZombie\\.", "").replaceAll("[]\\[]", "").split(", ");
        for (String number : subsKeys) {
            if (number.matches("[0-9]{1,2}")) {
                mobAmount.add(Integer.parseInt(number));
            }
        }
        for (int i = 1; i <= Collections.max(mobAmount); i++) {
            mobCustomName.add(CustomConfig.getCustomMobs().getString("CustomZombie." + i + ".CustomName"));
            mobCustomNameVisible.add(CustomConfig.getCustomMobs().getBoolean("CustomZombie." + i + ".CustomNameVisible"));
            mobSpawnChance.add(CustomConfig.getCustomMobs().getDouble("CustomZombie." + i + ".SpawnChance"));
            mobBabyChance.add(CustomConfig.getCustomMobs().getDouble("CustomZombie." + i + ".BabyChance"));
            mobSpawnReasons.add(CustomConfig.getCustomMobs().getStringList("CustomZombie." + i + ".SpawnReasons"));
            mobHelmetMaterial.add(CustomConfig.getCustomMobs().getString("CustomZombie." + i + ".HeadEquipment.Material"));
            mobHelmetDisplayName.add(CustomConfig.getCustomMobs().getString("CustomZombie." + i + ".HeadEquipment.DisplayName"));
            mobHelmetEnchantments.add(CustomConfig.getCustomMobs().getStringList("CustomZombie." + i + ".HeadEquipment.Enchantments"));
            mobHelmetLores.add(CustomConfig.getCustomMobs().getStringList("CustomZombie." + i + ".HeadEquipment.Lores"));
            mobHelmetDropChance.add(CustomConfig.getCustomMobs().getDouble("CustomZombie." + i + ".HeadEquipment.DropChance"));
            mobChestPlateMaterial.add(CustomConfig.getCustomMobs().getString("CustomZombie." + i + ".ChestEquipment.Material"));
            mobChestPlateDisplayName.add(CustomConfig.getCustomMobs().getString("CustomZombie." + i + ".ChestEquipment.DisplayName"));
            mobChestPlateEnchantments.add(CustomConfig.getCustomMobs().getStringList("CustomZombie." + i + ".ChestEquipment.Enchantments"));
            mobChestPlateLores.add(CustomConfig.getCustomMobs().getStringList("CustomZombie." + i + ".ChestEquipment.Lores"));
            mobChestPlateDropChance.add(CustomConfig.getCustomMobs().getDouble("CustomZombie." + i + ".ChestEquipment.DropChance"));
            mobMainHandMaterial.add(CustomConfig.getCustomMobs().getString("CustomZombie." + i + ".MainHandEquipment.Material"));
            mobMainHandDisplayName.add(CustomConfig.getCustomMobs().getString("CustomZombie." + i + ".MainHandEquipment.DisplayName"));
            mobMainHandEnchantments.add(CustomConfig.getCustomMobs().getStringList("CustomZombie." + i + ".MainHandEquipment.Enchantments"));
            mobMainHandLores.add(CustomConfig.getCustomMobs().getStringList("CustomZombie." + i + ".MainHandEquipment.Lores"));
            mobMainHandDropChance.add(CustomConfig.getCustomMobs().getDouble("CustomZombie." + i + ".MainHandEquipment.DropChance"));
            mobOffHandMaterial.add(CustomConfig.getCustomMobs().getString("CustomZombie." + i + ".OffHandEquipment.Material"));
            mobOffHandEnchantments.add(CustomConfig.getCustomMobs().getStringList("CustomZombie." + i + ".OffHandEquipment.Enchantments"));
            mobOffHandDisplayName.add(CustomConfig.getCustomMobs().getString("CustomZombie." + i + ".OffHandEquipment.DisplayName"));
            mobOffHandLores.add(CustomConfig.getCustomMobs().getStringList("CustomZombie." + i + ".OffHandEquipment.Lores"));
            mobOffHandDropChance.add(CustomConfig.getCustomMobs().getDouble("CustomZombie." + i + ".OffHandEquipment.DropChance"));
            mobLeggingsMaterial.add(CustomConfig.getCustomMobs().getString("CustomZombie." + i + ".LeggingsEquipment.Material"));
            mobLeggingsEnchantments.add(CustomConfig.getCustomMobs().getStringList("CustomZombie." + i + ".LeggingsEquipment.Enchantments"));
            mobLeggingsDisplayName.add(CustomConfig.getCustomMobs().getString("CustomZombie." + i + ".LeggingsEquipment.DisplayName"));
            mobLeggingsLores.add(CustomConfig.getCustomMobs().getStringList("CustomZombie." + i + ".LeggingsEquipment.Lores"));
            mobLeggingsDropChance.add(CustomConfig.getCustomMobs().getDouble("CustomZombie." + i + ".LeggingsEquipment.DropChance"));
            mobBootsMaterial.add(CustomConfig.getCustomMobs().getString("CustomZombie." + i + ".BootsEquipment.Material"));
            mobBootsDisplayName.add(CustomConfig.getCustomMobs().getString("CustomZombie." + i + ".BootsEquipment.DisplayName"));
            mobBootsEnchantments.add(CustomConfig.getCustomMobs().getStringList("CustomZombie." + i + ".BootsEquipment.Enchantments"));
            mobBootsLores.add(CustomConfig.getCustomMobs().getStringList("CustomZombie." + i + ".BootsEquipment.Lores"));
            mobBootsDropChance.add(CustomConfig.getCustomMobs().getDouble("CustomZombie." + i + ".BootsEquipment.DropChance"));
        }
    }
}
