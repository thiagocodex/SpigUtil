package me.thiagocodex.spigutil.custommob;

import me.thiagocodex.spigutil.utilities.LoaderUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class CustomCat {


    private static final SecureRandom secureRandom = new SecureRandom();


    public static void spawn(CreatureSpawnEvent event) {

        int min = 0;
        int max = 100;

        double randomDouble = min + (max - min) * secureRandom.nextDouble();

        Bukkit.broadcastMessage("Random double: " + randomDouble);
        Bukkit.broadcastMessage("Chance: " + LoaderUtil.customCatSpawnChance);

        if (event.getEntity() instanceof Cat
                && randomDouble <= LoaderUtil.customCatSpawnChance
                && LoaderUtil.customCatSpawnReasonList.contains(event.getSpawnReason().name())) {

            if (LoaderUtil.isTamed) {
                List<Player> playerList = new ArrayList<>(Bukkit.getOnlinePlayers());
                ((Cat) event.getEntity()).setTamed(true);
                Player animalTamer = playerList.get(secureRandom.nextInt(playerList.size()));
                ((Cat) event.getEntity()).setOwner(animalTamer);
                animalTamer.sendMessage(LoaderUtil.ownerMessage);

            }
        }
    }
}
