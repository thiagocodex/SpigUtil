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
    private static final File configFile = new File(plugin.getDataFolder(), "config.yml");

    public static void createFiles() {
        try {
            if (Files.notExists(plugin.getDataFolder().toPath())) {
                Files.createDirectory(plugin.getDataFolder().toPath());
            }
            if (Files.notExists(configFile.toPath())) {
                Files.createFile(configFile.toPath());
                write();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static FileConfiguration getConfig() {
        return config;
    }

    public static void load() {
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    private static void write() {
        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(configFile), StandardCharsets.UTF_8));
            writer.write("Plugin:\n" +
                    "  Locale: 'en_US'\n" +
                    "\n" +
                    "  ZoneID: 'America/Los_Angeles'\n" +
                    "\n" +
                    "#You can choose between: # TINY   e.g:  0:50\n" +
                    "  TimeFormat: 'NORMAL'   # SMALL  e.g:  4:20 pm \n" +
                    "                         # NORMAL e.g: 11:50 am CST\n" +
                    "\n" +
                    "Plugin_Messages:\n" +
                    "  Prefix: ''\n" +
                    "\n" +
                    "  Enabled: 'Enabled successfully'\n" +
                    "  \n" +
                    "  Reloaded: 'Reloaded Successfully'\n" +
                    "  \n" +
                    "  Message_Title: '§a------ §6Spig§cUtil §a------§r'\n" +
                    "  \n" +
                    "  Time_Zone_Set: 'System TimeZone set as%colon% %currenttimezone%'\n" +
                    "  \n" +
                    "  Garbage_Collector_Exec: 'Garbage collector performed; reallocated memory%colon% %reallocatedmemory%'\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
