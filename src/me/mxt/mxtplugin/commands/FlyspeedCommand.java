package me.mxt.mxtplugin.commands;

import me.mxt.mxtplugin.files.CustomConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.mxt.mxtplugin.commands.FlyCommand.list_of_flying_players;

public class FlyspeedCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;

            if (player.hasPermission("mxt.fly.speed") || player.hasPermission("mxt.*")){

                if (args.length == 0){
                    player.sendMessage(ChatColor.RED + "ERROR: Please use /flyspeed <speed>");
                } else if (args.length >= 1){
                    if (list_of_flying_players.contains(player)){
                        Float speed = Float.valueOf(args[0]);

                        if (speed >= 1.1 || speed <= -1.1){
                            player.sendMessage(ChatColor.RED + "ERROR: The value you set is not allowed, Allowed: -1 to 1 (ect /flyspeed <0.5>)");
                        } else {
                            player.setFlySpeed(speed);
                            player.sendMessage(ChatColor.GREEN + "Your fly speed has been set to " + speed);
                        }
                } else {
                        player.sendMessage(ChatColor.RED + "ERROR: Enable fly mode to execute this command!");
                    }


                }

            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("no-permission")));
            }

        } else {
            sender.sendMessage(ChatColor.RED + "Console can't execute this command!");
        }

        return true;
    }
}
