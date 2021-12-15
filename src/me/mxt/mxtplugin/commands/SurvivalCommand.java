package me.mxt.mxtplugin.commands;

import me.mxt.mxtplugin.files.CustomConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;

public class SurvivalCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length >= 1) {
                if (getServer().getPlayer(args[0]) != null) {
                    if (player.hasPermission("mxt.gms.others") || player.hasPermission("mxt.gm.*") || player.hasPermission("mxt.gm.others.*") || player.hasPermission("mxt.*")) {
                        Player target = Bukkit.getPlayer(args[0]);
                        target.setGameMode(GameMode.SURVIVAL);
                        String gamemodetarget = target.getGameMode().toString();
                        target.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("game-mode-set-others-target").replaceAll("%gamemode%", gamemodetarget).replaceAll("%player_name%", player.getName())));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("game-mode-set-others-player").replaceAll("%gamemode%", gamemodetarget).replaceAll("%target_name%", target.getName())));
                    } else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("no-permission")));
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "That player is not online!");
                }

                return true;

            }
            if (player.hasPermission("mxt.gms") || player.hasPermission("mxt.gm.*") || player.hasPermission("mxt.*")) {
                player.setGameMode(GameMode.SURVIVAL);
                String gamemode = player.getGameMode().toString();
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("game-mode-set-self").replaceAll("%gamemode%", gamemode).replaceAll("%player_name%", player.getName())));
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("no-permission")));
            }
            return true;

        } else if (!(sender instanceof Player)) {
            if (args.length == 0) {
                System.out.println("Please provide a username, /gms <player>");
            } else if (args.length >= 1) {
                if (getServer().getPlayer(args[0]) != null) {
                    Player target = Bukkit.getPlayer(args[0]);
                    target.setGameMode(GameMode.SURVIVAL);
                    target.sendMessage(ChatColor.GREEN + "Your gamemode has set to " + ChatColor.RED + "SURVIVAL" + ChatColor.GREEN + " by " + ChatColor.RED + "CONSOLE");
                    System.out.println("You have set " + target.getName() + " gamemode to " + "SURVIVAL");
                } else {
                    System.out.println("That player is not online!");
                }

            }

        }
        return true;
    }
}