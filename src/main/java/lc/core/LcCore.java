package lc.core;

import lc.core.Controller.ConfigManager;
import lc.core.Controller.Database;
import lc.core.Utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class LcCore extends JavaPlugin {

    private static LcCore instance;
    private static ConfigManager config;
    public void onEnable() {
        instance = this;
        config = new ConfigManager();
        config.registerConfig();
        Database.setupDatabaseData();
        try {
            Database.openConnection();
            checkConnection();
        } catch (Exception e) {
            Bukkit.shutdown();
            e.printStackTrace();
        }

    }


    private void deleteLogs() {
        File logs = new File(Bukkit.getWorldContainer().getAbsolutePath(), "logs");

        if (logs.exists() && logs.isDirectory()) {
            String[] children = logs.list();

            assert children != null;
            if (children.length > 20) {
                Util.deleteDir(logs);
            }
        }
    }

    private void checkConnection() {
        Bukkit.getScheduler().runTaskTimer(this, new Runnable()
        {
            public void run()
            {
                Database.CheckConnection();
            }
        },  36000L, 36000L);
    }


    public void onDisable() {
        deleteLogs();
        Bukkit.getScheduler().cancelTasks(this);
    }


    public static LcCore getInstance() { return instance; }
}
