package me.thiagocodex.spigutil.remove;

import me.thiagocodex.spigutil.SpigUtil;
import me.thiagocodex.spigutil.utilities.StringUtil;
import org.bukkit.Chunk;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EntityRemove {

    private static final List<Entity> entitiesToRemove = new ArrayList<>();

    private String entityToRemove;

    private String target;

    public String getTarget() {

        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getEntityToRemove() {
        return entityToRemove;
    }

    public void setEntityToRemove(String entityToRemove) {
        this.entityToRemove = entityToRemove;
    }

    public void check(Player player) {
        entitiesToRemove.clear();
        for (Chunk chunk : player.getWorld().getLoadedChunks()) {
            entitiesToRemove.addAll(Arrays.asList(chunk.getEntities()));
        }
    }

    public void remove() {
        for (Entity entity : entitiesToRemove) {
            if (!(entity instanceof Player)) entity.remove();
        }
    }


    public void filterName() {
        entitiesToRemove.removeAll(entitiesToRemove.stream()
                .filter(entity -> !entity.getName().equalsIgnoreCase(StringUtil.color(getTarget())))
                .collect(Collectors.toList()));

    }

    public void filterCustomName() {
        entitiesToRemove.removeIf(entity -> entity.getCustomName() == null);
        entitiesToRemove.removeAll(entitiesToRemove.stream()
                .filter(entity -> !entity.getCustomName().equalsIgnoreCase(StringUtil.color(getTarget())))
                .collect(Collectors.toList()));

    }

    public void filterCustom() {
        entitiesToRemove.removeAll(entitiesToRemove.stream()
                .filter(entity -> !entity.getScoreboardTags().contains("SpigUtilCustom")).collect(Collectors.toList()));
    }

    public void filterType() {
        entitiesToRemove.removeAll(entitiesToRemove.stream()
                .filter(entity -> !entity.getClass().getSimpleName().equalsIgnoreCase(StringUtil.addCraftPrefix(getTarget())))
                .collect(Collectors.toList()));

    }

    public void message(Player player, String key, String param) {
        if (entitiesToRemove.size() > 0) {
            player.sendMessage("");
            player.sendMessage(MessageFormat.format(SpigUtil.bundle.getString(key), entitiesToRemove.size(), param));
            player.sendMessage("");
        }else{
            player.sendMessage("");
            player.sendMessage(MessageFormat.format(SpigUtil.bundle.getString("entityNotRemovedAtAll"), param));
            player.sendMessage("");
        }
    }
}
