package me.mxt.mxtplugin.commands;

import me.mxt.mxtplugin.files.CustomConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SuicideCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;
            if (CustomConfig.get().getBoolean("suicide.enabled") == true){
                if (player.hasPermission("mxt.suicide") || player.hasPermission("mxt.*")){
                    player.setHealth(0);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("suicide.player-message")));
                    if(CustomConfig.get().getBoolean("suicide.broadcast-message-enabled") == true){
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("suicide.broadcast-msg").replaceAll("%player_name%", player.getName())));
                    } else if(CustomConfig.get().getBoolean("suicide.broadcast-message-enabled") == false){
                        player.sendMessage("");
                    }
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("no-permission")));
                }
            } else if(CustomConfig.get().getBoolean("suicide.enabled") == false){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("disabled-cmd-msg")));
            }

        } else if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Console can't execute this command!");
        }

        return true;
    }
}
