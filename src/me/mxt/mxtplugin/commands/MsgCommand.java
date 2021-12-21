package me.mxt.mxtplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;

public class MsgCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;
            if (player.hasPermission("mxt.msg") || player.hasPermission("mxt.*")){
                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "ERROR: Please provide a player /msg <username> <message>");
                } else if (args.length == 1){
                    player.sendMessage(ChatColor.RED + "ERROR: Please provide a message /msg <username> <message>");
                } else if (args.length >= 1){
                    if (getServer().getPlayer(args[0]) != null) {
                        Player target = Bukkit.getPlayer(args[0]);
                        String msg = args[1];
                        target.sendMessage(ChatColor.YELLOW + player.getDisplayName() + ChatColor.GRAY + " » " + ChatColor.GREEN + msg);
                        player.sendMessage(ChatColor.YELLOW + player.getDisplayName() + ChatColor.GRAY + " » " + ChatColor.GREEN + msg);
                        target.playSound(target.getLocation(), Sound.ORB_PICKUP, 2.0F, 1.0F);
                    } else {
                        player.sendMessage(ChatColor.RED + "That player is not online!");
                    }
                }
            }
        } else if (!(sender instanceof Player)){
            if (args.length == 0) {
                System.out.println("ERROR: Please provide a player /msg <username> <message>");
            } else if (args.length == 1){
                System.out.println("ERROR: Please provide a message /msg <username> <message>");
            } else if (args.length >= 1){
                if (getServer().getPlayer(args[0]) != null) {
                    Player target = Bukkit.getPlayer(args[0]);
                    String msg = args[1];
                    target.sendMessage(ChatColor.YELLOW + "CONSOLE" + ChatColor.GRAY + " » " + ChatColor.GREEN + msg);
                    System.out.println("CONSOLE » " + msg);
                    target.playSound(target.getLocation(), Sound.ORB_PICKUP, 2.0F, 1.0F);

                } else {
                    System.out.println("That player is not online!");
                }
            }
        }

        return true;
    }
}
