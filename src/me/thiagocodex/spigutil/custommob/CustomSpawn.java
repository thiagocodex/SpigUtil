package me.thiagocodex.spigutil.custommob;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;


public class CustomSpawn implements Listener {


    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        for (ItemStack itemStack : event.getDrops()) {

            net.minecraft.server.v1_16_R3.ItemStack stack = CraftItemStack.asNMSCopy(itemStack);
            NBTTagCompound tag = stack.getTag() != null ? stack.getTag() : new NBTTagCompound();

            if (itemStack.hasItemMeta() && tag.getString("SpigUtil").equalsIgnoreCase("toRestore")) {
                Damageable damageable = (Damageable) itemStack.getItemMeta();
                damageable.setDamage(itemStack.getType().getMaxDurability() / 2);
                itemStack.setItemMeta((ItemMeta) damageable);
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
                CustomCat.spawn(event);
                break;
        }
    }
}








