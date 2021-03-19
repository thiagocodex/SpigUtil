package me.thiagocodex.spigutil.tests;

import com.google.common.collect.Multimap;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftInventoryView;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Constructor;
import java.util.*;

public class NBTTAGTEST implements Listener {


    @EventHandler
    public void checkNBT(PlayerInteractEvent event) {


        ItemStack itemStack = event.getItem();

        ItemMeta itemMeta = itemStack.getItemMeta();

        net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);

        NBTTagCompound compound = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();

        NBTTagCompound damage = new NBTTagCompound();

        damage.setString("AttributeName", "generic.attack_damage");
        damage.setInt("UUIDMost", 154800);
        damage.setString("Name", "generic.attack_damage");
        damage.setInt("Amount", 100);
        damage.setInt("Operation", 0);
        damage.setInt("UUIDLeast", 190321);
        damage.setString("Slot", "mainhand");
        NBTTagList nbtTagList = new NBTTagList();
        nbtTagList.add(damage);

        NBTTagCompound speed = new NBTTagCompound();

        speed.setString("AttributeName", "generic.attack_speed");
        speed.setInt("UUIDMost", 155751);
        speed.setString("Name", "generic.attack_speed");
        speed.setInt("Amount", 8);
        speed.setInt("Operation", 0);
        speed.setInt("UUIDLeast", 511903);
        speed.setString("Slot", "mainhand");

        nbtTagList.add(speed);

        compound.set("AttributeModifiers", nbtTagList);

        //nmsItem.setTag(compound);

        net.minecraft.server.v1_16_R3.AttributeModifier nmsModifier = new net.minecraft.server.v1_16_R3.AttributeModifier(
                UUID.randomUUID(),
                "qualquer",
                7.0,
                net.minecraft.server.v1_16_R3.AttributeModifier.Operation.ADDITION);


       /* AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(),
                "Weapon modifier", 2.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);*/

        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(),
                "Weapon modifier2", 20.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);

        //itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);
        //itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier2);


        itemStack.setItemMeta(itemMeta);

        //Bukkit.broadcastMessage(compound.get("isplay").asString());
       // event.getPlayer().getInventory().addItem(CraftItemStack.asCraftMirror(nmsItem));

    }

}
