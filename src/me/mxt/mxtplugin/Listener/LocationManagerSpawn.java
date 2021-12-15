package me.mxt.mxtplugin.Listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class LocationManagerSpawn {


    public File file = new File("plugins/mxt", "spawn.yml");
    public FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public void setSpawn(Location loc){
        String name = "spawn";

        cfg.set(name + ".world", loc.getWorld().getName());
        cfg.set(name + ".x", loc.getX());
        cfg.set(name + ".y", loc.getY());
        cfg.set(name + ".z", loc.getZ());
        cfg.set(name + ".yaw", loc.getYaw());
        cfg.set(name + ".pitch", loc.getPitch());
        savecfg();
    }

    public Location getSpawn(){
        String name = "spawn";
        World w = Bukkit.getWorld(cfg.getString(name + ".world"));
        double x = cfg.getDouble(name + ".x");
        double y = cfg.getDouble(name + ".y");
        double z = cfg.getDouble(name + ".z");
        Location loc = new Location(w, x, y, z);
        loc.setYaw(cfg.getInt(".pitch"));
        return loc;
    }

    public void savecfg() {
        try {
            cfg.save(file);
        } catch (Exception e){

        }
    }

}
