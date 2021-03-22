package me.thiagocodex.spigutil.custommob;

import me.thiagocodex.spigutil.SpigUtil;
import me.thiagocodex.spigutil.utilities.LoaderUtil;
import me.thiagocodex.spigutil.utilities.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPiglinBrute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.entity.Ageable;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class CustomMob {


    public static void spawn(CreatureSpawnEvent event) {
        SecureRandom secureRandom = new SecureRandom();
        List<Integer> selectedMob = LoaderUtil.map.entrySet().stream()
                .filter(entry -> event.getEntity().getClass().getSimpleName().equalsIgnoreCase(entry.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        double randomDouble = 100 * secureRandom.nextDouble();
        int randomInt = secureRandom.nextInt(selectedMob.size());
        int mobSelector = selectedMob.get(randomInt) - 1;

        if (randomDouble <= LoaderUtil.mobSpawnChance.get(mobSelector) && LoaderUtil.mobSpawnReasons.get(mobSelector).contains(event.getSpawnReason().name())) {
            List<List<String>> materials = new ArrayList<>();
            Collections.addAll(materials, LoaderUtil.mobHelmetMaterial);
            Collections.addAll(materials, LoaderUtil.mobChestPlateMaterial);
            Collections.addAll(materials, LoaderUtil.mobMainHandMaterial);
            Collections.addAll(materials, LoaderUtil.mobOffHandMaterial);
            Collections.addAll(materials, LoaderUtil.mobLeggingsMaterial);
            Collections.addAll(materials, LoaderUtil.mobBootsMaterial);
            List<List<String>> displayNames = new ArrayList<>();
            Collections.addAll(displayNames, LoaderUtil.mobHelmetDisplayName);
            Collections.addAll(displayNames, LoaderUtil.mobChestPlateDisplayName);
            Collections.addAll(displayNames, LoaderUtil.mobMainHandDisplayName);
            Collections.addAll(displayNames, LoaderUtil.mobOffHandDisplayName);
            Collections.addAll(displayNames, LoaderUtil.mobLeggingsDisplayName);
            Collections.addAll(displayNames, LoaderUtil.mobBootsDisplayName);
            List<List<List<String>>> enchantments = new ArrayList<>();
            Collections.addAll(enchantments, LoaderUtil.mobHelmetEnchantments);
            Collections.addAll(enchantments, LoaderUtil.mobChestPlateEnchantments);
            Collections.addAll(enchantments, LoaderUtil.mobMainHandEnchantments);
            Collections.addAll(enchantments, LoaderUtil.mobOffHandEnchantments);
            Collections.addAll(enchantments, LoaderUtil.mobLeggingsEnchantments);
            Collections.addAll(enchantments, LoaderUtil.mobBootsEnchantments);
            List<List<List<String>>> lores = new ArrayList<>();
            Collections.addAll(lores, LoaderUtil.mobHelmetLores);
            Collections.addAll(lores, LoaderUtil.mobChestPlateLores);
            Collections.addAll(lores, LoaderUtil.mobMainHandLores);
            Collections.addAll(lores, LoaderUtil.mobOffHandLores);
            Collections.addAll(lores, LoaderUtil.mobLeggingsLores);
            Collections.addAll(lores, LoaderUtil.mobBootsLores);
            List<List<List<String>>> attributesModifiers = new ArrayList<>();
            Collections.addAll(attributesModifiers, LoaderUtil.mobHelmetAttributeModifier);
            Collections.addAll(attributesModifiers, LoaderUtil.mobChestPlateAttributeModifier);
            Collections.addAll(attributesModifiers, LoaderUtil.mobMainHandAttributeModifier);
            Collections.addAll(attributesModifiers, LoaderUtil.mobOffHandAttributeModifier);
            Collections.addAll(attributesModifiers, LoaderUtil.mobLeggingsAttributeModifier);
            Collections.addAll(attributesModifiers, LoaderUtil.mobBootsAttributeModifier);
            ItemStack[] itemStacks = new ItemStack[6];

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
                        Enchantment enchant = EnchantmentWrapper.getByKey(NamespacedKey.minecraft(enchantment.split("#")[0].toLowerCase()));
                        itemMeta.addEnchant(enchant, Integer.parseInt(enchantment.split("#")[1]), true);
                    }
                    itemMeta.setDisplayName(StringUtil.color(displayNames.get(i).get(mobSelector)));
                    itemMeta.setLore(lores.get(i).get(mobSelector).stream()
                            .map(s -> s = ChatColor.translateAlternateColorCodes('&', s))
                            .collect(Collectors.toList()));
                    for (String attributeModifier : attributesModifiers.get(i).get(mobSelector)) {
                        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(),
                                UUID.randomUUID().toString(),
                                Double.parseDouble(attributeModifier.split("#")[1]),
                                AttributeModifier.Operation.valueOf(attributeModifier.split("#")[3]),
                                EquipmentSlot.valueOf(attributeModifier.split("#")[2]));
                        itemMeta.addAttributeModifier(Attribute.valueOf(attributeModifier.split("#")[0]), modifier);
                    }
                    itemStack.setItemMeta(itemMeta);
                }
            }
            event.getEntity().setCustomName(StringUtil.color(LoaderUtil.mobCustomName.get(mobSelector)));
            event.getEntity().setCustomNameVisible(LoaderUtil.mobCustomNameVisible.get(mobSelector));
            if (event.getEntity() instanceof Ageable) {
                if (100 * secureRandom.nextDouble() <= LoaderUtil.mobBabyChance.get(mobSelector))
                    ((Ageable) event.getEntity()).setBaby();
                else ((Ageable) event.getEntity()).setAdult();
            }
            event.getEntity().addScoreboardTag("SpigUtilCustom");

            Bukkit.broadcastMessage("Nome: " + event.getEntity().getName());
            Bukkit.broadcastMessage("Nome Personalizado: " + event.getEntity().getCustomName());

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
