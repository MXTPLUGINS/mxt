package me.mxt.mxtplugin.commands;

import me.mxt.mxtplugin.files.CustomConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NightCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("mxt.night") || player.hasPermission("mxt.*")) {
                player.getLocation().getWorld().setTime(13000);
                player.sendMessage(ChatColor.GREEN + "You have set world time to " + ChatColor.RED + "NIGHT");
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("no-permission")));
            }
        } else if(!(sender instanceof Player)){
        System.out.println("Console can't execute this command!");
    }
        return true;
    }
}
