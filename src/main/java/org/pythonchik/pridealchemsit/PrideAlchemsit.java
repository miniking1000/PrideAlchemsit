package org.pythonchik.pridealchemsit;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class PrideAlchemsit extends JavaPlugin {
    private static FileConfiguration config;
    Plugin plugin;
    public static Message message;
    public static Message getMessage(){return message;}

    public void reload5(){
        Bukkit.getPluginManager().disablePlugin(plugin);
        Bukkit.getPluginManager().enablePlugin(plugin);
    }

    @Override
    public void onEnable() {
        plugin = this;
        message = new Message(this);
        loadConfig();
        new Menu(this,config);
        getCommand("alchemist").setExecutor(new AlchemCommand(this,config));
        //getCommand("alchemist").setTabCompleter(new tabBadge(this,config));
        getServer().getPluginManager().registerEvents(new listeners(this,config),this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public void loadConfig() {
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            saveResource("config.yml", false);
        }
        config = null;
        config = YamlConfiguration.loadConfiguration(configFile);
    }



}


