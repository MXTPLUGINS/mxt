package me.mxt.mxtplugin.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;


public class onQuitEvent implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        e.setQuitMessage(ChatColor.RED + e.getPlayer().getDisplayName() + " has left. " + ChatColor.GREEN + "(" + Bukkit.getOnlinePlayers().size() + Bukkit.getServer().getMaxPlayers() + "/)");
    }

}
