package me.mxt.mxtplugin.commands;

import me.mxt.mxtplugin.files.CustomConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DiscordCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (sender.hasPermission("mxt.discord") || sender.hasPermission("mxt.*")){
                if (CustomConfig.get().getBoolean("discord.enabled") == true){
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("discord.link")));
                } else if(CustomConfig.get().getBoolean("discord.enabled") == false) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("disabled-cmd-msg")));
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes ('&',  CustomConfig.get().getString("no-permission")));
            }

        return true;
    }
}
