package me.thiagocodex.spigutil.tests;

import me.thiagocodex.spigutil.utilities.LoaderUtil;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

public class NBTTagTest implements Listener {


   /* @EventHandler
    public void damageTest(EntityDamageByEntityEvent event) {
        Bukkit.broadcastMessage(event.getDamage() + "");

        ItemStack itemStack = ((LivingEntity) event.getDamager()).getEquipment().getItemInMainHand();
        Bukkit.broadcastMessage(itemStack.getType().name());
        if (event.getEntity() instanceof LivingEntity) {
            Bukkit.broadcastMessage(String.valueOf((event.getEntity() instanceof LivingEntity)));
            if (itemStack.getType() == Material.DIAMOND_SWORD) {
                if (itemStack.getItemMeta().hasAttributeModifiers() && itemStack.getItemMeta().getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE).stream()
                        .anyMatch(a -> a.getName().equalsIgnoreCase("Dano_Extra? ou n"))) {

                    event.setDamage(itemStack.getItemMeta().getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE).stream()
                            .filter(a -> a.getName().equalsIgnoreCase("Dano_Extra? ou n"))
                            .findFirst().get().getAmount());
                }
            }
        }

    }*/

    public static void giveMod(Player player) {
        ItemStack itemStack = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta itemMeta = itemStack.getItemMeta();


        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),
                        "Dano_Extra? ou n", 200.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));


        itemMeta.setDisplayName(ChatColor.GREEN + "Espada do Admin");
        itemStack.setItemMeta(itemMeta);

        player.getInventory().addItem(itemStack);

    }

}
