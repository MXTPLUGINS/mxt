package me.mxt.mxtplugin.events;

import me.mxt.mxtplugin.files.CustomConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;

import static sun.audio.AudioPlayer.player;

public class ClickEvent implements Listener {

    @EventHandler
    public void ClickEvent(InventoryClickEvent e){

        Player player = (Player) e.getWhoClicked();

        if (e.getClickedInventory().getTitle().equalsIgnoreCase(ChatColor.RED + "KICK Everyone")){

            switch (e.getCurrentItem().getType()){
                case YELLOW_FLOWER:
                                for (Player p : Bukkit.getServer().getOnlinePlayers()){
                                    if (p.hasPermission("mxt.kickall.bypass") || p.hasPermission("mxt.*")){
                                        p.sendMessage(ChatColor.RED + player.getDisplayName() + ChatColor.YELLOW + " Have kicked everyone!");
                                    } else {
                                        p.kickPlayer(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("kickall.kick-msg")));
                                    }
                                }
                                player.sendMessage(ChatColor.YELLOW + "You've kicked everyone!");
                                player.closeInventory();
                                break;
                case RED_ROSE:
                            player.sendMessage(ChatColor.GREEN + "You have canceled kicking everyone");
                            player.closeInventory();
                            break;
            }

            e.setCancelled(true);
        }



    }

}
