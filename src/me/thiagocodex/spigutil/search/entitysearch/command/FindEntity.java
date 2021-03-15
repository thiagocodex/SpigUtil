package me.thiagocodex.spigutil.search.entitysearch.command;

import me.thiagocodex.spigutil.SpigUtil;
import me.thiagocodex.spigutil.search.EntityFinder;
import me.thiagocodex.spigutil.utilities.Util;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FindEntity extends EntityFinder {

    private FindEntity(Class<?> classType) {
        super(classType);
    }

    private static FindEntity instance;

    public static FindEntity getInstance() {
        return instance == null ? instance = new FindEntity(Entity.class) : instance;
    }

    private List<Map.Entry<Entity, Double>> checkType(List<Map.Entry<Entity, Double>> foundList) {
        foundList.removeAll(foundList.stream()
                .filter(e -> !e.getKey().getClass().getSimpleName().equalsIgnoreCase(Util.fixedClassName(super.target)))
                .collect(Collectors.toList()));
        return super.foundList;
    }

    private void checkCategory(List<Map.Entry<Entity, Double>> foundList) {
        foundList.removeAll(foundList.stream().filter(e -> !(e.getKey() instanceof Monster)).collect(Collectors.toList()));
    }

    @Override
    protected void filter() {

        switch (super.target) {
            case "hostile":
                checkCategory(super.foundList);
                break;
            default:
                checkType(super.foundList).removeAll(super.foundList.stream()
                        .filter(s -> !s.getKey().getClass().getSimpleName().equalsIgnoreCase(Util.fixedClassName(super.target)))
                        .collect(Collectors.toList()));
        }
    }

    @Override
    protected void message() {
        int limit;
        limit = super.foundList.size() < ((int) super.amount) ? super.foundList.size() : super.amount;
        player.sendMessage("");
        player.sendMessage(MessageFormat.format(SpigUtil.bundle.getString("messageTitle"), limit));
        if (limit != 0) {
            for (int i = 0; i < limit; i++) {
                Entity entity = super.foundList.get(i).getKey();
                Location location = entity.getLocation();
                String strLocation = location.getBlockX() + " " + location.getBlockY() + " " + location.getBlockZ();
                int distance = super.foundList.get(i).getValue().intValue();
                player.sendMessage(MessageFormat.format(
                        SpigUtil.bundle.getString("entityFound"), entity.getName(), strLocation, distance));
            }
        } else {
            player.sendMessage(SpigUtil.bundle.getString("entityNotFound"));
        }
        player.sendMessage("");
    }
}
