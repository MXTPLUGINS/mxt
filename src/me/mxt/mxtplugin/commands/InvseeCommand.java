package me.mxt.mxtplugin.commands;

import me.mxt.mxtplugin.files.CustomConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InvseeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("mxt.invsee") || player.hasPermission("mxt.*")) {
                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "ERROR: You have to enter a username! /invsee <username>");
                } else if (args.length == 1 && args.length >= 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        player.openInventory( target.getInventory());
                    } else {
                        player.sendMessage(ChatColor.RED + "ERORR: That player is not online!");
                    }
                }
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("no-permission")));
            }
        } else {
            sender.sendMessage(ChatColor.RED + "ERROR: Console can't execute this command!");
        }

        return true;

    }


}

