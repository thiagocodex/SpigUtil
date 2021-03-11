package me.thiagocodex.spigutil.search.blockstatesearch;

import me.thiagocodex.spigutil.SpigUtil;
import me.thiagocodex.spigutil.search.BlockStateFinder;
import org.bukkit.Location;
import org.bukkit.block.BlockState;
import org.bukkit.craftbukkit.v1_16_R3.block.CraftBlockState;

import java.text.MessageFormat;
import java.util.stream.Collectors;

public class FindBlockState extends BlockStateFinder {


    private static FindBlockState INSTANCE;

    public FindBlockState(Class<?> classType) {
        super(classType);
    }

    public static FindBlockState getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FindBlockState(CraftBlockState.class);
        }
        return INSTANCE;
    }

    private static String fixedClassName(String target) {
        return target.equalsIgnoreCase("structure")
                ? "Craft" + target + "Block" : target.equalsIgnoreCase("furnace")
                ? "CraftFurnace" + target : "Craft" + target;

    }

    @Override
    protected void filter() {

        super.foundList.removeAll(super.foundList.stream().filter(h ->
                !(h.getKey()).getClass().getSimpleName().equalsIgnoreCase(fixedClassName(super.target))).collect(Collectors.toList()));
    }


    @Override
    protected void message() {
        int limit;
        limit = super.foundList.size() < ((int) super.amount) ? super.foundList.size() : super.amount;
        player.sendMessage("");
        player.sendMessage(MessageFormat.format(SpigUtil.bundle.getString("messageTitle"), limit));

        if (limit != 0) {
            for (int i = 0; i < limit; i++) {
                BlockState blockState = super.foundList.get(i).getKey();
                Location location = blockState.getLocation();
                String strLocation = location.getBlockX() + " " + location.getBlockY() + " " + location.getBlockZ();
                int distance = super.foundList.get(i).getValue().intValue();
                player.sendMessage(MessageFormat.format(
                        SpigUtil.bundle.getString("blockStateFound"), blockState.getType().name(), strLocation, distance));
            }
            player.sendMessage("");
        } else {
            player.sendMessage(SpigUtil.bundle.getString("blockStateNotFound"));
            player.sendMessage("");
        }
    }
}
