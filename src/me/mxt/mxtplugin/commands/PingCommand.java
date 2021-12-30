package me.mxt.mxtplugin.commands;

import me.mxt.mxtplugin.files.CustomConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PingCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;

            if (player.hasPermission("mxt.ping") || player.hasPermission("mxt.*")){
                player.sendMessage("Pong!");
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("no-permission")));
            }

        } else {
            sender.sendMessage("Pong!");
        }

        return true;
    }
}
