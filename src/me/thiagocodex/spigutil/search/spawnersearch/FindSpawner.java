package me.thiagocodex.spigutil.search.spawnersearch;

import me.thiagocodex.spigutil.SpigUtil;
import me.thiagocodex.spigutil.search.BlockStateFinder;
import org.bukkit.Location;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.craftbukkit.v1_16_R3.block.CraftCreatureSpawner;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FindSpawner extends BlockStateFinder {
    private List<Map.Entry<BlockState, Double>> checkType(List<Map.Entry<BlockState, Double>> foundList) {
        foundList.removeAll(foundList.stream().filter(s -> !s.getKey().getClass().getSimpleName().equals("CraftCreatureSpawner")).collect(Collectors.toList()));
        return super.foundList;
    }

    @Override
    protected void filter() {
        checkType(super.foundList).removeAll(checkType(super.foundList)
                .stream().filter(s -> !((CreatureSpawner) s.getKey()).getSpawnedType().name().equalsIgnoreCase(super.target))
                .collect(Collectors.toList()));
    }

    @Override
    protected void message() {
        int limit;
        limit = super.foundList.size() < ((int) super.amount) ? super.foundList.size() : super.amount;
        player.sendMessage("");
        player.sendMessage(MessageFormat.format(SpigUtil.bundle.getString("messageTitle"), limit));
        if (limit != 0) {
            for (int i = 0; i < limit; i++) {
                CreatureSpawner creatureSpawner = (CreatureSpawner) super.foundList.get(i).getKey();
                Location location = creatureSpawner.getLocation();
                String strLocation = location.getBlockX() + " " + location.getBlockY() + " " + location.getBlockZ();
                int distance = super.foundList.get(i).getValue().intValue();

                player.sendMessage(MessageFormat.format(
                        SpigUtil.bundle.getString("blockStateFound"), creatureSpawner.getSpawnedType().name(), strLocation, distance));

            }
            player.sendMessage("");
        } else {
            player.sendMessage(SpigUtil.bundle.getString("blockStateNotFound"));
            player.sendMessage("");
        }
    }


    private FindSpawner(Class<?> classType) {
        super(classType);
    }

    private static FindSpawner INSTANCE;

    public static FindSpawner getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FindSpawner(CraftCreatureSpawner.class);
        }
        return INSTANCE;
    }
}
