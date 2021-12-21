package me.mxt.mxtplugin.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import static me.mxt.mxtplugin.commands.AfkCommand.afk_players;

public class onPlayerMove implements Listener {


    @EventHandler
    public void onAfkMove(PlayerMoveEvent e){
        Player p = (Player) e.getPlayer();
        if (afk_players.contains(p)){
            afk_players.remove(p);
            Bukkit.broadcastMessage(ChatColor.GRAY + p.getDisplayName() + " is no longer afk");
        }
    }

}
