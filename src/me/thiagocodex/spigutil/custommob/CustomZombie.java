package me.thiagocodex.spigutil.custommob;

import me.thiagocodex.spigutil.SpigUtil;
import me.thiagocodex.spigutil.utilities.LoaderUtil;
import me.thiagocodex.spigutil.utilities.StringUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CustomZombie {
    private static final SecureRandom secureRandom = new SecureRandom();

    public static void spawn(CreatureSpawnEvent event) {
        byte mobSelector = (byte) new SecureRandom().nextInt(LoaderUtil.mobAmount.size());
        double randomDouble = 100 * secureRandom.nextDouble();
        ItemStack[] itemStacks = new ItemStack[6];
        if (event.getEntity() instanceof Zombie
                && randomDouble <= LoaderUtil.mobSpawnChance.get(mobSelector)
                && LoaderUtil.mobSpawnReasons.get(mobSelector).contains(event.getSpawnReason().name())) {
            List<List<String>> materials = new ArrayList<>();
            Collections.addAll(materials, LoaderUtil.mobHelmetMaterial);//0
            Collections.addAll(materials, LoaderUtil.mobChestPlateMaterial);//1
            Collections.addAll(materials, LoaderUtil.mobMainHandMaterial);//1
            Collections.addAll(materials, LoaderUtil.mobOffHandMaterial);//1
            Collections.addAll(materials, LoaderUtil.mobLeggingsMaterial);//1
            Collections.addAll(materials, LoaderUtil.mobBootsMaterial);//1
            List<List<String>> displayNames = new ArrayList<>();
            Collections.addAll(displayNames, LoaderUtil.mobHelmetDisplayName);//0
            Collections.addAll(displayNames, LoaderUtil.mobChestPlateDisplayName);//0
            Collections.addAll(displayNames, LoaderUtil.mobMainHandDisplayName);//0
            Collections.addAll(displayNames, LoaderUtil.mobOffHandDisplayName);//0
            Collections.addAll(displayNames, LoaderUtil.mobLeggingsDisplayName);//0
            Collections.addAll(displayNames, LoaderUtil.mobBootsDisplayName);//0
            List<List<List<String>>> enchantments = new ArrayList<>();
            Collections.addAll(enchantments, LoaderUtil.mobHelmetEnchantments);
            Collections.addAll(enchantments, LoaderUtil.mobChestPlateEnchantments);
            Collections.addAll(enchantments, LoaderUtil.mobMainHandEnchantments);
            Collections.addAll(enchantments, LoaderUtil.mobOffHandEnchantments);
            Collections.addAll(enchantments, LoaderUtil.mobLeggingsEnchantments);
            Collections.addAll(enchantments, LoaderUtil.mobBootsEnchantments);
            for (int i = 0; i < itemStacks.length; i++) {
                if (materials.get(i).get(mobSelector) != null) {
                    itemStacks[i] = new ItemStack(Material.valueOf(materials.get(i).get(mobSelector)));
                    ItemMeta itemMeta = itemStacks[i].getItemMeta();
                    itemMeta.getPersistentDataContainer().set(new NamespacedKey(SpigUtil.getPlugin(SpigUtil.class),
                            "restorable"), PersistentDataType.STRING, "yes");
                    itemStacks[i].setItemMeta(itemMeta);
                }
            }
            for (int i = 0; i < itemStacks.length; i++) {
                ItemStack itemStack = itemStacks[i];
                if (itemStack != null) {
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    for (String enchantment : enchantments.get(i).get(mobSelector)) {
                        itemMeta.addEnchant(Enchantment.getByName(enchantment.split("#")[0]), Integer.parseInt(enchantment.split("#")[1]), true);
                    }
                    itemMeta.setDisplayName(displayNames.get(i).get(mobSelector));
                    itemMeta.setLore(enchantments.get(i).get(mobSelector).stream()
                            .map(s -> s = ChatColor.translateAlternateColorCodes('&', s))
                            .collect(Collectors.toList()));
                    itemStack.setItemMeta(itemMeta);
                }
            }
            event.getEntity().setCustomName(StringUtil.color(LoaderUtil.mobCustomName.get(mobSelector)));
            event.getEntity().setCustomNameVisible(LoaderUtil.mobCustomNameVisible.get(mobSelector));

            event.getEntity().getEquipment().setHelmet(itemStacks[0]);
            event.getEntity().getEquipment().setHelmetDropChance((float) (LoaderUtil.mobHelmetDropChance.get(mobSelector) / 100));
            event.getEntity().getEquipment().setChestplate(itemStacks[1]);
            event.getEntity().getEquipment().setChestplateDropChance((float) (LoaderUtil.mobChestPlateDropChance.get(mobSelector) / 100));
            event.getEntity().getEquipment().setItemInMainHand(itemStacks[2]);
            event.getEntity().getEquipment().setItemInMainHandDropChance((float) (LoaderUtil.mobMainHandDropChance.get(mobSelector) / 100));
            event.getEntity().getEquipment().setItemInOffHand(itemStacks[3]);
            event.getEntity().getEquipment().setItemInOffHandDropChance((float) (LoaderUtil.mobOffHandDropChance.get(mobSelector) / 100));
            event.getEntity().getEquipment().setLeggings(itemStacks[4]);
            event.getEntity().getEquipment().setLeggingsDropChance((float) (LoaderUtil.mobLeggingsDropChance.get(mobSelector) / 100));
            event.getEntity().getEquipment().setBoots(itemStacks[5]);
            event.getEntity().getEquipment().setBootsDropChance((float) (LoaderUtil.mobBootsDropChance.get(mobSelector) / 100));
        }
    }
}
