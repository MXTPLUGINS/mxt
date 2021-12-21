package me.mxt.mxtplugin.commands;

import me.mxt.mxtplugin.files.CustomConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static org.bukkit.Bukkit.getServer;

public class FlyCommand implements CommandExecutor {

    public static ArrayList<Player> list_of_flying_players = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (CustomConfig.get().getBoolean("fly.enabled") == true) {
                if (args.length == 0) {
                    if (player.hasPermission("mxt.fly") || player.hasPermission("mxt.*")) {
                        if (list_of_flying_players.contains(player)) {
                            list_of_flying_players.remove(player);
                            player.setAllowFlight(false);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("fly.fly-disable-msg")));
                        } else if (!list_of_flying_players.contains(player)) {
                            list_of_flying_players.add(player);
                            player.setAllowFlight(true);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("fly.fly-enable-msg")));
                        }

                    } else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("no-permission")));
                    }
                } else if (args.length >= 1) {
                    if (getServer().getPlayer(args[0]) != null) {
                        Player target = Bukkit.getPlayer(args[0]);
                        if (player.hasPermission("mxt.fly.others") || player.hasPermission("mxt.*")) {
                            if (list_of_flying_players.contains(target)) {
                                list_of_flying_players.remove(target);
                                target.setAllowFlight(false);
                                target.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("fly.fly-others-msg-target-disable").replaceAll("%player%", player.getDisplayName())));
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("fly.fly-others-msg-player-disable").replaceAll("%target%", target.getDisplayName())));
                            } else if (!list_of_flying_players.contains(target)) {
                                list_of_flying_players.add(target);
                                target.setAllowFlight(true);
                                target.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("fly.fly-others-msg-target-enable").replaceAll("%player%", player.getDisplayName())));
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("fly.fly-others-msg-player-enable").replaceAll("%target%", target.getDisplayName())));
                            }

                        } else {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("no-permission")));
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "ERROR: That player is not online!");
                    }

                }
            } else if (CustomConfig.get().getBoolean("fly.enabled") == false) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("disabled-cmd-msg")));
            }


        } else if (!(sender instanceof Player)) {
            if (args.length == 0) {
                System.out.println("Please provide a username, /fly <player>");
            } else if (args.length == 1) {
                if (getServer().getPlayer(args[0]) != null) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (list_of_flying_players.contains(target)) {
                        list_of_flying_players.remove(target);
                        target.setAllowFlight(false);
                        target.sendMessage(ChatColor.GREEN + "Fly mode have been " + ChatColor.RED + "DISABLED" + ChatColor.GREEN + " by " + ChatColor.RED + "CONSOLE");
                        System.out.println("You have DISABLED fly for " + target.getName());
                    } else if (!list_of_flying_players.contains(target)) {
                        list_of_flying_players.add(target);
                        target.setAllowFlight(true);
                        target.sendMessage(ChatColor.GREEN + "Fly mode have been " + ChatColor.RED + "ENABLED" + ChatColor.GREEN + " by " + ChatColor.RED + "CONSOLE");
                        System.out.println("You have ENABLED fly for " + target.getName());
                    }
                } else {
                    System.out.println("That player is not online!");
                }

                }

            }

        return true;
    }
}