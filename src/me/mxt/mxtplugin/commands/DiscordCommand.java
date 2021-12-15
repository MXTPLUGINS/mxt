package me.mxt.mxtplugin.commands;

import me.mxt.mxtplugin.files.CustomConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DiscordCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (CustomConfig.get().getBoolean("discord.enabled") == true){
                if (player.hasPermission("mxt.discord") || player.hasPermission("mxt.*")){
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("discord.link")));
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes ('&',  CustomConfig.get().getString("no-permission")));
                }
            } else if(CustomConfig.get().getBoolean("discord.enabled") == false){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("disabled-cmd-msg")));
            }
        } else if(!(sender instanceof Player)){
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("discord.link")));
    }
        return true;
    }
}
