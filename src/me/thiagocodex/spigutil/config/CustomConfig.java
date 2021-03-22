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
    private static FileConfiguration test;
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
                    "  Pattern: '<dddd>, <MM>/<dd>/<y> - <h>:<mm>:<ss> <ampm> <z>'");
            writer.flush();
            writer.close();
            Writer writer2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(customMobsFile), StandardCharsets.UTF_8));
            writer2.write("CustomMob:\n" +
                    "  '1':\n" +
                    "    EntityType: Zombie\n" +
                    "    CustomName: '&b&lT&2&lh&3&le&c&lC&5&lo&6&ld&7&le&d&lr&e&ls&f™ &2L&3e&2r&3r&2o&3y'\n" +
                    "    CustomNameVisible: false\n" +
                    "    SpawnChance: 100.0\n" +
                    "    BabyChance: 1.0\n" +
                    "    SpawnReasons:\n" +
                    "      - NATURAL\n" +
                    "      - SPAWNER_EGG\n" +
                    "      - SPAWNER\n" +
                    "    HeadEquipment:\n" +
                    "      Material: IRON_HELMET\n" +
                    "      DropChance: 100.79\n" +
                    "      DisplayName: '&2L&3e&2r&3r&2o&3y&f''s custom helmet'\n" +
                    "      Enchantments:\n" +
                    "        - PROTECTION#4\n" +
                    "    MainHandEquipment:\n" +
                    "      Material: IRON_SWORD\n" +
                    "      DropChance: 102.41\n" +
                    "      DisplayName: '&2L&3e&2r&3r&2o&3y&f''s custom sword'\n" +
                    "      Attribute:\n" +
                    "        - GENERIC_ATTACK_DAMAGE#10.0#HAND#ADD_NUMBER\n" +
                    "      Enchantments:\n" +
                    "        - KNOCKBACK#2\n" +
                    "\n" +
                    "  '2':\n" +
                    "    EntityType: PiglinBrute\n" +
                    "    CustomName: '&b&lT&2&lh&3&le&c&lC&5&lo&6&ld&7&le&d&lr&e&ls&f™ &7&lScal'\n" +
                    "    CustomNameVisible: false\n" +
                    "    SpawnChance: 100.0\n" +
                    "    BabyChance: 0.0\n" +
                    "    SpawnReasons:\n" +
                    "      - NATURAL\n" +
                    "      - SPAWNER_EGG\n" +
                    "      - SPAWNER\n" +
                    "    ChestEquipment:\n" +
                    "      Material: NETHERITE_CHESTPLATE\n" +
                    "      DropChance: 100.79\n" +
                    "      DisplayName: '&e&lScal&f''s custom chestplate'\n" +
                    "      Enchantments:\n" +
                    "        - PROTECTION#4\n" +
                    "    MainHandEquipment:\n" +
                    "      Material: NETHERITE_AXE\n" +
                    "      DropChance: 102.41\n" +
                    "      DisplayName: '&e&lScal&f''s custom axe'\n" +
                    "      Attribute:\n" +
                    "        - GENERIC_ATTACK_DAMAGE#10.0#HAND#ADD_NUMBER\n" +
                    "      Enchantments:\n" +
                    "        - KNOCKBACK#2");
            writer2.flush();
            writer2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
