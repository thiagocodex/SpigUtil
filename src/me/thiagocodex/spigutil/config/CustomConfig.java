package me.thiagocodex.spigutil.config;

import me.thiagocodex.spigutil.SpigUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class CustomConfig {
    public static SpigUtil plugin = SpigUtil.getPlugin(SpigUtil.class);

    private static FileConfiguration config;
    private static FileConfiguration customMobs;
    private static final File configFile = new File(plugin.getDataFolder(), "config.yml");
    private static final File customMobsFile = new File(plugin.getDataFolder(), "custom_mobs.yml");

    public static void createFiles() {
        try {
            if (Files.notExists(plugin.getDataFolder().toPath())) {
                Files.createDirectory(plugin.getDataFolder().toPath());
            }
            if (Files.notExists(configFile.toPath())) {
                Files.createFile(configFile.toPath());
                write();
            }

            if (Files.notExists(customMobsFile.toPath())) {
                Files.createFile(customMobsFile.toPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static FileConfiguration getConfig() {
        return config;
    }

    public static FileConfiguration getCustomMobs() {
        return customMobs;
    }


    public static void load() {
        config = YamlConfiguration.loadConfiguration(configFile);
        customMobs = YamlConfiguration.loadConfiguration(customMobsFile);
    }

    private static void write() {
        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(configFile), StandardCharsets.UTF_8));
            writer.write("Plugin:\n" +
                    "  Locale: 'pt_BR'\n" +
                    "\n" +
                    "  ZoneID: 'America/Sao_Paulo'\n" +
                    "\n" +
                    "  TimeFormat: 'EXTENDED'\n" +
                    "  \n" +
                    "  Pattern: '<dddd>, <MM>/<dd>/<y> - <h>:<mm>:<ss> <ampm> <z>'\n" +
                    "  \n" +
                    "  \n");
            writer.flush();
            writer.close();
            ////////////////////////////////////////////////
            Writer writer2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(customMobsFile), StandardCharsets.UTF_8));
            writer2.write("Zombie:\n" +
                    "  CustomName: '&2&lZumbizão Safadon'\n" +
                    "  CustomNameVisible: true\n" +
                    "  SpawnPotentials: 5\n" +
                    "  CustomItemChestPlate: \n" +
                    "    Material: Diamond_Chest\n" +
                    "    DropChance: 5\n" +
                    "  CustomItemLeggings:\n" +
                    "    Material: Diamond_Leggings\n" +
                    "  \n" +
                    "  ");
            writer2.flush();
            writer2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
