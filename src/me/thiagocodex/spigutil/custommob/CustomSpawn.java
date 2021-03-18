package me.thiagocodex.spigutil.custommob;

import me.thiagocodex.spigutil.SpigUtil;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.security.SecureRandom;


public class CustomSpawn implements Listener {

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        String data;
        for (ItemStack itemStack : event.getDrops()) {
            if (itemStack.hasItemMeta()) {
                data = itemStack.getItemMeta().getPersistentDataContainer()
                        .get(new NamespacedKey(SpigUtil.getPlugin(SpigUtil.class), "restorable"), PersistentDataType.STRING);
                if (data != null && data.equalsIgnoreCase("yes")) {
                    Damageable damageable = (Damageable) itemStack.getItemMeta();
                    damageable.setDamage(itemStack.getType().getMaxDurability() - itemStack.getType().getMaxDurability() / (2 + new SecureRandom().nextInt(2)));
                    itemStack.setItemMeta((ItemMeta) damageable);
                }
            }
        }
    }

    @EventHandler
    public void onSpawn(CreatureSpawnEvent event) {
        switch (event.getEntity().getClass().getSimpleName()) {
            case "CraftZombie":
                CustomZombie.spawn(event);
                break;
            case "CraftCat":
                //CustomCat.spawn(event);
                break;
        }
    }
}








