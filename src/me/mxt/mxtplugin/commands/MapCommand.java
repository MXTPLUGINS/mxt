package me.mxt.mxtplugin.commands;

import me.mxt.mxtplugin.files.CustomConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MapCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;
            if (player.hasPermission("mxt.map") || player.hasPermission("mxt.*")){
                player.sendMessage(ChatColor.GREEN + "The map your on is " + ChatColor.YELLOW + player.getWorld().getName());
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes ('&',  CustomConfig.get().getString("no-permission")));
            }
        } else {
            sender.sendMessage(ChatColor.RED + "ERROR: Console can't execute this command");
        }

        return true;
    }
}
