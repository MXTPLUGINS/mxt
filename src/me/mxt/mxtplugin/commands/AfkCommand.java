package me.mxt.mxtplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AfkCommand implements CommandExecutor {

    public static boolean isAFK;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;
            if (p.hasPermission("mxt.afk") || p.hasPermission("mxt.*")){
                if (isAFK){
                    isAFK = false;
                    Bukkit.broadcastMessage(ChatColor.GRAY + p.getDisplayName() + " is no longer afk");
                } else {
                    isAFK = true;
                    Bukkit.broadcastMessage(ChatColor.GRAY + p.getDisplayName() + " is now afk");
                }
            }



        }


        return true;
    }


}