package me.thiagocodex.spigutil.custommob;

import me.thiagocodex.spigutil.SpigUtil;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_16_R3.Entity;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.EntityZombie;
import net.minecraft.server.v1_16_R3.World;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CustomZombie {

    public static void spawn(CreatureSpawnEvent event) {
        if (event.getEntity() instanceof Zombie
                && ThreadLocalRandom.current().nextInt(5) == 1
                && event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SPAWNER_EGG) {

            ItemStack diamondChestPlate = new ItemStack(Material.DIAMOND_CHESTPLATE);
            ItemMeta itemMeta = diamondChestPlate.getItemMeta();
            itemMeta.addEnchant(Enchantment.MENDING, 1, true);
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.AQUA + "Dropped by a Zombie");
            itemMeta.setLore(lore);
            diamondChestPlate.setItemMeta(itemMeta);

            event.getEntity().setCustomName(ChatColor.translateAlternateColorCodes('&', SpigUtil.zombieCustomName));
            event.getEntity().setCustomNameVisible(SpigUtil.zombieCustomNameVisible);

            event.getEntity().getEquipment().setChestplateDropChance(0.10f);
            event.getEntity().getEquipment().setChestplate(diamondChestPlate);
            Bukkit.broadcastMessage(event.getEntity().getLocation().toString());
        }
    }
}
