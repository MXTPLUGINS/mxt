package me.mxt.mxtplugin.commands;

import me.mxt.mxtplugin.files.CustomConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static me.mxt.mxtplugin.commands.FlyCommand.list_of_flying_players;


public class FlylistCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender.hasPermission("mxt.fly.list") || sender.hasPermission("mxt.*")) {
            if (list_of_flying_players.size() == 0) {
                sender.sendMessage(ChatColor.RED + "ERROR: There is no one flying!");
            } else if (list_of_flying_players.size() >= 0) {
                sender.sendMessage(ChatColor.GREEN + "Players [" + list_of_flying_players.size() + ChatColor.GREEN + "]: " + ChatColor.YELLOW + list_of_flying_players);
            }
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("no-permission")));
        }


        return true;
    }
}
