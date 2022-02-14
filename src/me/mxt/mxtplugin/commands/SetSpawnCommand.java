package me.mxt.mxtplugin.commands;

import me.mxt.mxtplugin.Main;
import me.mxt.mxtplugin.files.CustomConfig;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;

            Location loc = p.getLocation();
            if (p.hasPermission("mxt.setspawn") || p.hasPermission("mxt.*")){
                Main.lms.setSpawn(loc);
                p.sendMessage(ChatColor.GREEN + "Spawn has been set!");
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes ('&',  CustomConfig.get().getString("no-permission")));
            }
        } else if (!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Console can't execute this command!");
        }

        return true;
    }
}
