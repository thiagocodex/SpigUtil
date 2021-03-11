package me.thiagocodex.spigutil.search;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;

import java.util.*;

public abstract class BlockStateFinder {

    protected abstract void filter();

    protected abstract void message();


    private final Map<BlockState, Double> blockStateMap = new HashMap<>();
    protected final List<Map.Entry<BlockState, Double>> foundList = new ArrayList<>();
    protected final StringBuilder message = new StringBuilder();

    protected Class<?> classType;
    protected Player player;
    protected String target;

    protected byte amount;

    protected BlockStateFinder(Class<?> classType) {
        this.classType = classType;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setAmount(byte amount) {
        this.amount = amount;
    }

    public void search() {
        this.message.setLength(0);
        this.blockStateMap.clear();
        this.foundList.clear();

        for (Chunk chunk : this.player.getWorld().getLoadedChunks()) {
            for (BlockState blockState : chunk.getTileEntities()) {
                Location blockStateLocation = blockState.getBlock().getLocation();
                this.blockStateMap.put(blockState, blockStateLocation.distance(this.player.getLocation()));
            }
        }

        this.foundList.addAll(this.blockStateMap.entrySet());
        Comparator<Map.Entry<BlockState, Double>> comparator = Map.Entry.comparingByValue();
        this.foundList.sort(comparator);

        this.filter();
        this.message();
    }
}
