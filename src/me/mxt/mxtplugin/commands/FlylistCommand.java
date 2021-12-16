package me.mxt.mxtplugin.commands;

import me.mxt.mxtplugin.files.CustomConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.mxt.mxtplugin.commands.FlyCommand.list_of_flying_players;


public class FlylistCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;
            if (player.hasPermission("mxt.fly.list") || player.hasPermission("mxt.*")){
                if (list_of_flying_players.size() == 0) {
                    player.sendMessage(ChatColor.RED + "ERROR: There is no one flying!");
                } else if (list_of_flying_players.size() >= 0) {
                    player.sendMessage(ChatColor.GREEN + "Players [" + list_of_flying_players.size() + ChatColor.GREEN + "]: " + ChatColor.YELLOW + list_of_flying_players);
                }
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("no-permission")));
            }

        } else if (!(sender instanceof Player)){
            if (list_of_flying_players.size() == 0){
                System.out.println("ERROR: There is no one flying!");
            } else if(list_of_flying_players.size() >= 1) {
                System.out.println("Players [" + list_of_flying_players.size() + "]: " + list_of_flying_players);
            }
        }

        return true;
    }
}
