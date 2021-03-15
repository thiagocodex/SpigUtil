package me.thiagocodex.spigutil.search;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.*;

public abstract class EntityFinder {
    private final Map<Entity, Double> entityDoubleMap = new HashMap<>();
    protected List<Map.Entry<Entity, Double>> foundList = new ArrayList<>();

    protected abstract void message();

    protected abstract void filter();


    protected Class<?> classType;
    protected Player player;
    protected byte amount;
    protected String target;
    protected String args;

    public EntityFinder(Class<?> classType) {
        this.classType = classType;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setAmount(byte amount) {
        this.amount = amount;
    }

    public void setTarget(String target) {
        this.target = target;
    }


    public void search(final Player player) {
        this.player = player;
        this.foundList.clear();
        this.entityDoubleMap.clear();
        for (Chunk chunk : player.getWorld().getLoadedChunks()) {
            for (Entity entity : chunk.getEntities()) {
                Location entityLocation = entity.getLocation();
                this.entityDoubleMap.put(entity, entityLocation.distance(this.player.getLocation()));
            }
        }

        this.foundList.addAll(this.entityDoubleMap.entrySet());
        Comparator<Map.Entry<Entity, Double>> comparator = Map.Entry.comparingByValue();
        this.foundList.sort(comparator);
        this.filter();
        this.message();
    }
}
