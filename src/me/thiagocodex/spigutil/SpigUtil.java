package me.thiagocodex.spigutil;

import me.thiagocodex.spigutil.config.CustomConfig;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class SpigUtil extends JavaPlugin {

    public static String strLocale;

    public static ResourceBundle bundle;
    public static final String[] zones = {"America/Sao_Paulo"};

    @Override
    public void onEnable() {
        loadReload();
        Objects.requireNonNull(getServer().getPluginCommand("su")).setExecutor(new Commander());
        getServer().getConsoleSender().sendMessage(bundle.getString("pluginPrefix") + " " + bundle.getString("pluginEnabled"));
    }

    public static void loadReload() {
        CustomConfig.createFiles();
        CustomConfig.load();

        strLocale = CustomConfig.getConfig().getString("Plugin.Locale");
        assert strLocale != null;
        Locale locale = new Locale(strLocale.split("_")[0], strLocale.split("_")[1]);
        bundle = ResourceBundle.getBundle("me.thiagocodex.spigutil.internationalization.messages", locale);
    }
}
