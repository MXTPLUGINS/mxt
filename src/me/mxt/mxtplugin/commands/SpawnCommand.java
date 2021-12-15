package me.mxt.mxtplugin.commands;

import me.mxt.mxtplugin.Main;
import me.mxt.mxtplugin.files.CustomConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;

public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player){
            Player p = (Player) sender;
            if (args.length == 0){
                if (p.hasPermission("mxt.spawn") || p.hasPermission("mxt.*")){
                    p.teleport(Main.lms.getSpawn());
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("spawn.teleport-msg")));
                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes ('&', CustomConfig.get().getString("no-permission")));
                }
            } else if (args.length >= 1){
                if (p.getServer().getPlayer(args[0]) != null) {
                    if ( p.hasPermission("mxt.spawn.others") || p.hasPermission("mxt.*")){
                        Player target = Bukkit.getPlayer(args[0]);
                        target.teleport(Main.lms.getSpawn());
                        target.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("spawn.teleport-others-msg-target").replaceAll("%player_name%", p.getName())));
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("spawn.teleport-others-msg-player").replaceAll("%target_name%", target.getName())));
                    } else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes ('&', CustomConfig.get().getString("no-permission")));
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "That player is not online!");
                }

            }


        } if (!(sender instanceof Player)){
            if (args.length == 0){
                System.out.println("Please provide a username, /spawn <player>");
            } else if (args.length >= 1){
                if (getServer().getPlayer(args[0]) != null) {
                    Player target = Bukkit.getPlayer(args[0]);
                    target.teleport(Main.lms.getSpawn());
                    target.sendMessage("You have been teleported to spawn by " + ChatColor.RED + "CONSOLE");
                    System.out.println(ChatColor.GREEN + "You have teleported " + target.getName() + " to spawn!");
                } else {
                    System.out.println("That player is not online!");
                }

            }
        }



        return false;
    }
}
