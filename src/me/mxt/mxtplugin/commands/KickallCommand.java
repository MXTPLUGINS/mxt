package me.mxt.mxtplugin.commands;

import me.mxt.mxtplugin.files.CustomConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class KickallCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;
            if (CustomConfig.get().getBoolean("kickall.enabled") == true) {
                if (player.hasPermission("mxt.kickall") || player.hasPermission("mxt.*")) {
                    Inventory confirm = Bukkit.createInventory(player, 9, ChatColor.RED + "KICK Everyone");

                    ItemStack yes = new ItemStack(Material.YELLOW_FLOWER);
                    ItemStack no = new ItemStack(Material.RED_ROSE);

                    ItemMeta yes_meta = yes.getItemMeta();
                    yes_meta.setDisplayName(ChatColor.YELLOW + "Confirm");
                    yes.setItemMeta(yes_meta);

                    ItemMeta no_meta = no.getItemMeta();
                    no_meta.setDisplayName(ChatColor.RED + "Cancel");
                    no.setItemMeta(no_meta);

                    ItemStack[] menu_items = {yes, no};
                    confirm.setContents(menu_items);
                    player.openInventory(confirm);
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("no-permission")));
                }
            } else if (CustomConfig.get().getBoolean("kickall.enabled") == false){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("disabled-cmd-msg")));
            }
        } else if (!(sender instanceof Player)){
            if (CustomConfig.get().getBoolean("kickall.enabled") == true) {
                for (Player p : Bukkit.getServer().getOnlinePlayers()){
                    if (p.hasPermission("mxt.kickall.bypass") || p.hasPermission("mxt.*")){
                        p.sendMessage(ChatColor.RED + "CONSOLE" + ChatColor.YELLOW + " Have kicked everyone!");
                    } else {
                        p.kickPlayer(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("kickall.kick-msg")));
                    }
            }

            } else if (CustomConfig.get().getBoolean("kickall.enabled") == false){
                System.out.println(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("disabled-cmd-msg")));
            }
        }

        return true;
    }

}


