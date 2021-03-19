package me.thiagocodex.spigutil;

import me.thiagocodex.spigutil.config.CustomConfig;
import me.thiagocodex.spigutil.custommob.CustomSpawn;

import me.thiagocodex.spigutil.tests.NBTTAGTEST;
import me.thiagocodex.spigutil.utilities.LoaderUtil;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.ZoneId;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class SpigUtil extends JavaPlugin {
    public static String langAndCountry;
    public static Locale locale;
    public static String zoneID;
    public static TimeZone timeZone;
    public static String pattern;
    public static String timeFormat;
    public static ResourceBundle bundle;

    @Override
    public void onEnable() {
        loadReload();
        Objects.requireNonNull(getServer().getPluginCommand("su")).setExecutor(new Commander());
        getServer().getConsoleSender().sendMessage(bundle.getString("pluginPrefix") + " " + bundle.getString("pluginEnabled"));
        getServer().getPluginManager().registerEvents(new CustomSpawn(), this);
        getServer().getPluginManager().registerEvents(new NBTTAGTEST(), this);
    }

    public static void loadReload() {
        CustomConfig.createFiles();
        CustomConfig.load();
        LoaderUtil.load();
        timeFormat = CustomConfig.getConfig().getString("Plugin.TimeFormat");
        langAndCountry = CustomConfig.getConfig().getString("Plugin.Locale");
        zoneID = CustomConfig.getConfig().getString("Plugin.ZoneID");
        pattern = CustomConfig.getConfig().getString("Plugin.Pattern");
        assert langAndCountry != null;
        locale = new Locale(langAndCountry.split("_")[0], langAndCountry.split("_")[1]);
        assert zoneID != null;
        timeZone = TimeZone.getTimeZone(ZoneId.of(zoneID));
        TimeZone.setDefault(timeZone);
        Locale.setDefault(locale);
        bundle = ResourceBundle.getBundle("me.thiagocodex.spigutil.internationalization.messages", locale);
    }
}
