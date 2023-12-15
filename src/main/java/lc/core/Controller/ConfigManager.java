package lc.core.Controller;

import lc.core.LcCore;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ConfigManager {
    private final LcCore plugin = LcCore.getInstance();

    private FileConfiguration config = null;

    private File configFile = null;


    public FileConfiguration getConfig() {
        if (this.config == null)
            reloadConfig();
        return this.config;
    }

    public void reloadConfig() {
        if (this.config == null)
            this.configFile = new File(this.plugin.getDataFolder(), "config.yml");
        this.config = YamlConfiguration.loadConfiguration(this.configFile);
        Reader defConfigStream = new InputStreamReader(this.plugin.getResource("config.yml"), StandardCharsets.UTF_8);
        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
        this.config.setDefaults(defConfig);
    }

    public void saveConfig() {
        try {
            this.config.save(this.configFile);
        } catch (IOException e) {
            Bukkit.getLogger().severe("[LCCore] No se ha podido guardar el archivo config.yml");
        }
    }

    public void registerConfig() {
        this.configFile = new File(this.plugin.getDataFolder(), "config.yml");
        if (!this.configFile.exists()) {
            getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }
}