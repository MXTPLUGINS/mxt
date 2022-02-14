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

            if (sender.hasPermission("mxt.reload") || sender.hasPermission("mxt.*")) {
                CustomConfig.reload();
                sender.sendMessage(ChatColor.GREEN + "Config Reloaded! Check console to see if there is any error");
                if (!(CustomConfig.get().getDouble("version") == ver)){
                    sender.sendMessage(ChatColor.RED + "Your config.yml is not up to date, delete the config and let it make it self or set your config version to " + ver + " to stop this warning");
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes ('&', CustomConfig.get().getString("no-permission")));
            }

        return true;
    }
}