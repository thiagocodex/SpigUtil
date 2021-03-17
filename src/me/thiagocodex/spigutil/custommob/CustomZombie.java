package me.thiagocodex.spigutil.custommob;

import me.thiagocodex.spigutil.utilities.LoaderUtil;
import me.thiagocodex.spigutil.utilities.StringUtil;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftZombie;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class CustomZombie {

    private static final SecureRandom secureRandom = new SecureRandom();
    public static void spawn(CreatureSpawnEvent event) {

        event.getEntity().setCustomName(LoaderUtil.customZombieCustomName);
        event.getEntity().setCustomNameVisible(LoaderUtil.customNameVisible);

        double random = 100 * secureRandom.nextDouble();

        if (random <= LoaderUtil.babyChance)
            ((Zombie) event.getEntity()).setBaby();

        double randomDouble = 100 * secureRandom.nextDouble();
        if (event.getEntity() instanceof Zombie
                && randomDouble <= LoaderUtil.customZombieSpawnChance
                && LoaderUtil.customZombieSpawnReasonList.contains(event.getSpawnReason().name())) {

            ItemStack[] itemStacks = new ItemStack[LoaderUtil.customZombieEquipmentList.size()];
            for (int i = 0; i < itemStacks.length; i++) {
                if (LoaderUtil.customZombieEquipmentList.get(i).equalsIgnoreCase("NONE")) itemStacks[i] = null;
                else {
                    itemStacks[i] = new ItemStack(Material.valueOf(LoaderUtil.customZombieEquipmentList.get(i)));
                    net.minecraft.server.v1_16_R3.ItemStack stack = CraftItemStack.asNMSCopy(itemStacks[i]);
                    NBTTagCompound tag = stack.getTag() != null ? stack.getTag() : new NBTTagCompound();
                    tag.setString("SpigUtil", "toRestore");
                    stack.setTag(tag);
                    itemStacks[i] = CraftItemStack.asCraftMirror(stack);
                }
            }

            //START HELMET
            if (itemStacks[0] != null) {
                ItemMeta itemMeta = itemStacks[0].getItemMeta();
                for (String enchantment : LoaderUtil.zombieHelmetEnchantments) {
                    itemMeta.addEnchant(Enchantment.getByName(enchantment.split("#")[0]),
                            Integer.parseInt(enchantment.split("#")[1]), true);
                }
                List<String> lore = new ArrayList<>();

                lore.addAll(LoaderUtil.customZombieHelmetLores);

                itemMeta.setDisplayName(ChatColor.RESET + LoaderUtil.customZombieHelmetName);
                itemMeta.setLore(lore);
                itemStacks[0].setItemMeta(itemMeta);
                event.getEntity().getEquipment().setHelmetDropChance((float) LoaderUtil.helmetDropChance / 100);
                event.getEntity().getEquipment().setHelmet(itemStacks[0]);
            }
            //END HELMET

            //START OFFHAND
            if (itemStacks[4] != null) {
                ItemMeta itemMeta = itemStacks[3].getItemMeta();
                List<String> lore = new ArrayList<>();
                itemMeta.setDisplayName(ChatColor.RESET + "Picareta");
                itemMeta.setLore(lore);
                itemStacks[3].setItemMeta(itemMeta);
                event.getEntity().getEquipment().setItemInOffHandDropChance(1.00f);
                event.getEntity().getEquipment().setItemInOffHand(itemStacks[3]);
            }
            //END HELMET
        }
    }
}
