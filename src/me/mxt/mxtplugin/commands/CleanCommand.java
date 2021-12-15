package me.mxt.mxtplugin.commands;

import me.mxt.mxtplugin.files.CustomConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;

public class CleanCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;
            if(args.length == 0){
                if (player.hasPermission("mxt.clean") || player.hasPermission("mxt.*")){
                    player.getInventory().clear();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("clean.message")));
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("no-permission")));
                }
            } else if(args.length >= 1){
                if (player.getServer().getPlayer(args[0]) != null) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (player.hasPermission("mxt.clean.others") || player.hasPermission("mxt.*")){
                        target.getInventory().clear();
                        target.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("clean.message-target").replaceAll("%player_name%", player.getName())));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("clean.message-player").replaceAll("%target_name%", target.getName())));
                    } else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("no-permission")));
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "That player is not online!");
                }


            }

        } else if(!(sender instanceof Player)){
            if (args.length == 0){
                System.out.println("Please provide a username, /clean <player>");
            } else if (args.length >= 1){
                if (getServer().getPlayer(args[0]) != null) {
                    Player target = Bukkit.getPlayer(args[0]);
                    target.getInventory().clear();
                    target.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("clean.message-target").replaceAll("%player_name%", ChatColor.RED + "CONSOLE")));
                    System.out.println("You have cleared " + target.getName() + " inventory");
                } else {
                    System.out.println("That player is not online!");
                }

            }
        }
        return true;
    }
}
