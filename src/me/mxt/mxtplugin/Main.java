package me.mxt.mxtplugin;

import me.mxt.mxtplugin.Listener.LocationManagerSpawn;
import me.mxt.mxtplugin.commands.*;
import me.mxt.mxtplugin.events.JoinEvent;
import me.mxt.mxtplugin.files.CustomConfig;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

    public static LocationManagerSpawn lms;
    public static Double ver = 1.2;

    @Override
    public void onEnable() {
        System.out.println("\n" + "--------MXT--------\n Server: " + getServer().getVersion() + "\n" + " Plugin: " + ver + "\n" + "The plugin has enabled" + "--------MXT--------");
        lms = new LocationManagerSpawn();

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        CustomConfig.setup();
        CustomConfig.get().options().copyDefaults(true);
        CustomConfig.save();

        getCommand("mxt").setExecutor(new CommandManager());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("mxtreload").setExecutor(new ReloadCommand());
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getCommand("discord").setExecutor(new DiscordCommand());
        getCommand("gms").setExecutor(new SurvivalCommand());
        getCommand("gmc").setExecutor(new CreativeCommand());
        getCommand("gma").setExecutor(new AdventureCommand());
        getCommand("gmsp").setExecutor(new SpectatorCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("day").setExecutor(new DayCommand());
        getCommand("night").setExecutor(new NightCommand());
        getCommand("suicide").setExecutor(new SuicideCommand());
        getCommand("clean").setExecutor(new CleanCommand());
        getCommand("ping").setExecutor(new PingCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("flylist").setExecutor(new FlylistCommand());


        PluginManager pluginManager = Bukkit.getPluginManager();

        if(CustomConfig.get().getDouble("version") == ver){
            System.out.println("[MXT] Your config.yml is up to date!");
        } else if (!(CustomConfig.get().getDouble("version") == ver)){
                Bukkit.getLogger().warning("[MXT] Your config.yml is not up to date, delete the config and let it make it self or set your config version to " + ver + " to stop this warning");
        }
    }




    @Override
    public void onDisable() {
        System.out.println("\n" + "--------MXT--------\n Server: " + getServer().getVersion() + "\n" + " Plugin: " + ver + "\n" + "The plugin has disabled" + "--------MXT--------");
    }

}
