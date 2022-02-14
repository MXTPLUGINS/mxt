package me.mxt.mxtplugin;

import me.mxt.mxtplugin.Listener.LocationManagerSpawn;
import me.mxt.mxtplugin.commands.*;
import me.mxt.mxtplugin.events.ClickEvent;
import me.mxt.mxtplugin.events.JoinEvent;
import me.mxt.mxtplugin.events.onPlayerMove;
import me.mxt.mxtplugin.files.CustomConfig;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

    public static LocationManagerSpawn lms;
    public static Double ver = 1.10 ;

    @Override
    public void onEnable() {
        commands();
        events();
        files();
        Metrics();
        spawn();
        uptodatecheck();
    }


    public void commands(){
        getCommand("mxt").setExecutor(new CommandManager());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("mxtreload").setExecutor(new ReloadCommand());
        getCommand("discord").setExecutor(new DiscordCommand());
        getCommand("gms").setExecutor(new SurvivalCommand());
        getCommand("gmc").setExecutor(new CreativeCommand());
        getCommand("gma").setExecutor(new AdventureCommand());
        getCommand("gmsp").setExecutor(new SpectatorCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("suicide").setExecutor(new SuicideCommand());
        getCommand("clean").setExecutor(new CleanCommand());
        getCommand("ping").setExecutor(new PingCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("flylist").setExecutor(new FlylistCommand());
        getCommand("afk").setExecutor(new AfkCommand());
        getCommand("msg").setExecutor(new MsgCommand());
        getCommand("invsee").setExecutor(new InvseeCommand());
        getCommand("vanish").setExecutor(new VanishCommand());
        getCommand("map").setExecutor(new MapCommand());
        getCommand("flyspeed").setExecutor(new FlyspeedCommand());
    }

    public void events(){
        getServer().getPluginManager().registerEvents(new onPlayerMove(), this);
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getServer().getPluginManager().registerEvents(new ClickEvent(), this);
    }

    public void files(){
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        CustomConfig.setup();
        CustomConfig.get().options().copyDefaults(true);
        CustomConfig.save();
    }

    public void Metrics(){
        Metrics metrics = new Metrics(this,  13682);
        PluginManager pluginManager = Bukkit.getPluginManager();
    }

    public void startupmsg(){
        System.out.println("\n" + "--------MXT--------\n Server: " + getServer().getVersion() + "\n" + " Plugin: " + ver + "\n" + "The plugin has enabled" + "\n" + "--------MXT--------");
    }

    public void spawn(){
        lms = new LocationManagerSpawn();
    }

    public void uptodatecheck(){
        if (!(CustomConfig.get().getDouble("version") == ver)){
            Bukkit.getLogger().warning("[MXT] Your config.yml is not up to date, delete the config and let it make it self or set your config version to " + ver + " to stop this warning");
        }
    }

    @Override
    public void onDisable() {

    }

}
