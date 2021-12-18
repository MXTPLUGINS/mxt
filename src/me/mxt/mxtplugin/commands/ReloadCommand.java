package me.mxt.mxtplugin.commands;

import me.mxt.mxtplugin.files.CustomConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.mxt.mxtplugin.Main;

import static me.mxt.mxtplugin.Main.ver;

public class ReloadCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("mxt.reload") || player.hasPermission("mxt.*")) {
                CustomConfig.reload();
                player.sendMessage(ChatColor.GREEN + "Config Reloaded! Check console to see if there is any error");
                if (!(CustomConfig.get().getDouble("version") == ver)){
                    player.sendMessage(ChatColor.RED + "Your config.yml is not up to date, delete the config and let it make it self or set your config version to " + ver + " to stop this warning");
                }
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes ('&', CustomConfig.get().getString("no-permission")));
            }

        }else if(!(sender instanceof Player)){
            CustomConfig.reload();
            System.out.println("Config Reloaded! Check console to see if there is any error");
             if (!(CustomConfig.get().getDouble("version") == ver)){
                Bukkit.getLogger().warning("[MXT] Your config.yml is not up to date, delete the config and let it make it self or set your config version to " + ver + " to stop this warning");
            }
        }



        return true;
    }
}