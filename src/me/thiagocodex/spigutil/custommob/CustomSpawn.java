package me.thiagocodex.spigutil.custommob;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;


public class CustomSpawn implements Listener {


    @EventHandler
    public void onDeath(EntityDeathEvent event) {

        for (ItemStack itemStack : event.getDrops()) {
            if (itemStack.hasItemMeta()) {
                if (!itemStack.getItemMeta().getLore().isEmpty()
                        && itemStack.getItemMeta().getLore().contains(ChatColor.AQUA + "Dropped by a Zombie")) {
                    Damageable damageable = (Damageable) itemStack.getItemMeta();
                    damageable.setDamage(0);
                }
            }
        }
    }


    @EventHandler
    public void onSpawn(CreatureSpawnEvent event) {

        switch (event.getEntity().getClass().getSimpleName()) {

            case "CraftZombie":
                CustomZombie.spawn(event);
        }
    }
}












