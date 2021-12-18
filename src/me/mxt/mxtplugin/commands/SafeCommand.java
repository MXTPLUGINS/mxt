package me.mxt.mxtplugin.commands;

import me.mxt.mxtplugin.files.CustomConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SafeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;
            if (CustomConfig.get().getBoolean("safe.execute-cmd-enabled") == true){
                if(player.hasPermission("mxt.safe") || player.hasPermission("mxt.*")){
                    Bukkit.getServer().setWhitelist(true);
                    for (Player p : Bukkit.getServer().getOnlinePlayers()){
                        if (p.isWhitelisted() || p.hasPermission("mxt.safe.bypass")) {
                            p.sendMessage(ChatColor.YELLOW + player.getDisplayName() + ChatColor.GREEN + " Have set the server to maintenance/safe mode");
                            player.sendMessage(ChatColor.YELLOW + "do /whitelist off, to turn safe mode off");
                        } else {
                            p.kickPlayer(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("safe.kick-msg")));
                        }
                    }
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("no-permission")));
                }
            } else if (CustomConfig.get().getBoolean("safe.execute-cmd-enabled") == false){
                player.sendMessage("Unknown command. Type \"/help\" for help.");
            }

        } else if(!(sender instanceof Player)){
            if (CustomConfig.get().getBoolean("safe.execute-cmd-enabled") == true){
                Bukkit.getServer().setWhitelist(true);
                for (Player p : Bukkit.getServer().getOnlinePlayers()){
                    if (p.hasPermission("mxt.safe.bypass") || p.hasPermission("mxt.*")){
                        p.sendMessage(ChatColor.YELLOW + "CONSOLE" + ChatColor.GREEN + " Have set the server to maintenance/safe mode");
                    } else {
                        p.kickPlayer(ChatColor.translateAlternateColorCodes('&', CustomConfig.get().getString("safe.kick-msg")));
                    }

                }
                System.out.println("You've set the server to maintenance/safe mode, do /whitelist off, to turn this mode off");
            } else if (CustomConfig.get().getBoolean("safe.execute-cmd.enabled") ==  false){
                System.out.println("Unknown command. Type \"/help\" for help.");
            }

        }
        return true;
    }
}
