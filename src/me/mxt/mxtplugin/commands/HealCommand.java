package me.mxt.mxtplugin.commands;

import me.mxt.mxtplugin.files.CustomConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import static me.mxt.mxtplugin.Main.ver;
import static org.bukkit.Bukkit.getServer;

public class HealCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;
            if (args.length == 0) {
                if(player.hasPermission("mxt.heal") || player.hasPermission("mxt.*")){
                    if (player.isDead()){
                        player.sendMessage(ChatColor.RED + "You cannot heal players that are dead");
                    } else {
                        player.setHealth(20);
                        player.setFoodLevel(20);
                        player.removePotionEffect(PotionEffectType.HUNGER);
                        player.setFireTicks(0);
                        player.sendMessage(ChatColor.GREEN + "You have been healed!");
                    }

                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("no-permission")));
                }
            } else if(args.length >= 1) {
                if (getServer().getPlayer(args[0]) != null) {
                    if(player.hasPermission("mxt.heal.others") || player.hasPermission("mxt.*")){
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target.isDead()){
                            player.sendMessage(ChatColor.RED + "You cannot heal players that are dead");
                        } else {
                            target.setHealth(20);
                            target.setFoodLevel(20);
                            target.sendMessage(ChatColor.GREEN + "You Have been healed by " + ChatColor.RED + player.getName());
                            target.removePotionEffect(PotionEffectType.HUNGER);
                            target.setFireTicks(0);

                            player.sendMessage(ChatColor.GREEN + "You have healed " + ChatColor.RED + target.getName());
                        }

                    } else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("no-permission")));
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "That player is not online!");
                }

            }

        } else if(!(sender instanceof Player)){
            if (args.length == 0){
                System.out.println("Please provide a username, /heal <username>");
            } else if(args.length >= 1){
                if (getServer().getPlayer(args[0]) != null) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target.isDead()){
                        System.out.println("You cannot heal players that are dead");
                    } else {
                        target.setHealth(20);
                        target.setFoodLevel(20);
                        target.removePotionEffect(PotionEffectType.HUNGER);
                        target.setFireTicks(0);
                        target.sendMessage(ChatColor.GREEN + "You Have been healed by " + ChatColor.RED + "CONSOLE");
                        System.out.println("You have healed " + target.getPlayer());
                    }

                } else {
                    System.out.println("That player is not online!");
                }

            }
        }

        return true;
    }
}
