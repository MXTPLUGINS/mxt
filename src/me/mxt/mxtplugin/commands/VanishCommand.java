package me.mxt.mxtplugin.commands;

import me.mxt.mxtplugin.files.CustomConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class VanishCommand implements CommandExecutor {

    public static ArrayList<Player> vanish_players = new ArrayList<Player>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;
            if (player.hasPermission("mxt.vanish") || player.hasPermission("mxt.*")){
                if (!vanish_players.contains(player)){
                    for (Player pl : Bukkit.getServer().getOnlinePlayers()){
                        pl.hidePlayer(player);
                        player.sendMessage(ChatColor.GREEN + "Your now vanished");
                    }
                    vanish_players.add(player);
                } else {
                    for (Player pl : Bukkit.getServer().getOnlinePlayers()){
                        pl.showPlayer(player);
                        player.sendMessage(ChatColor.GREEN + "Your now visible");
                    }
                    vanish_players.remove(player);
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
