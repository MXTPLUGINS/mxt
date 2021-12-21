package me.mxt.mxtplugin.commands;

import me.mxt.mxtplugin.files.CustomConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class AfkCommand implements CommandExecutor {

    public static ArrayList<Player> afk_players = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;
            if (p.hasPermission("mxt.afk") || p.hasPermission("mxt.*")){
                if (afk_players.contains(p)){
                    afk_players.remove(p);
                    Bukkit.broadcastMessage(ChatColor.GRAY + p.getDisplayName() + " is no longer afk");
                } else if (!afk_players.contains(p)){
                    afk_players.add(p);
                    Bukkit.broadcastMessage(ChatColor.GRAY + p.getDisplayName() + " is now afk");
                }
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("no-permission")));
            }



        }


        return true;
    }


}