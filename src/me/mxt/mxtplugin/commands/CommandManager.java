package me.mxt.mxtplugin.commands;

import me.mxt.mxtplugin.files.CustomConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor {

    private ArrayList<SubCommand> subcommands = new ArrayList<>();

    public CommandManager(){
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

            if(sender instanceof Player){
                Player p = (Player) sender;

                if (args.length > 0){
                    for(int i = 0; i < getSubcommands().size(); i++){
                        if (args[0].equalsIgnoreCase(getSubcommands().get(i).getName())){
                            getSubcommands().get(i).perform(p, args);
                        }
                    }
                }else if(args.length == 0){
                    if(p.hasPermission("mxt.help") || p.hasPermission("mxt.*")){
                        p.sendMessage(ChatColor.GREEN + "-------------------------------------");
                        for(int i = 0; i < getSubcommands().size(); i++){
                            p.sendMessage(getSubcommands().get(i).getSyntax() + " - " + getSubcommands().get(i).getDescription());
                        }
                        p.sendMessage("/fly <playername> - Toggle your or other's fly mode");
                        p.sendMessage("/mxtreload - Reload the config");
                        p.sendMessage("/discord - Get discord link of the server");
                        p.sendMessage("/gms - Set your or other's gamemode to survival");
                        p.sendMessage("/gmc - Set your or other's gamemode to creative");
                        p.sendMessage("/gma - Set your or other's gamemode to adventure");
                        p.sendMessage("/gmsp - Set your or other's gamemode to spectator");
                        p.sendMessage("/heal - Set your or other's health to max");
                        p.sendMessage("/suicide - Kill yourself in-game");
                        p.sendMessage("/day - Set world time to day");
                        p.sendMessage("/night - Set world time to night");
                        p.sendMessage("/clean - Clean your or other's inventory");
                        p.sendMessage("/ping - Pong!");
                        p.sendMessage("/setspawn - Set spawn for your server");
                        p.sendMessage("/spawn - Teleport you to spawn");
                        p.sendMessage("/flylist - Show flying players");
                        p.sendMessage("/kickall - Kick everyone");
                        p.sendMessage("/safe - Turn on maintenance/safe mode");
                        p.sendMessage(ChatColor.GREEN + "-------------------------------------");
                    } else {
                            p.sendMessage(ChatColor.translateAlternateColorCodes ('&', CustomConfig.get().getString("no-permission")));
                    }

            }

        } else if(!(sender instanceof Player)){
                System.out.println(ChatColor.GREEN + "-------------------------------------");
                for(int i = 0; i < getSubcommands().size(); i++){
                    System.out.println(getSubcommands().get(i).getSyntax() + " - " + getSubcommands().get(i).getDescription());
                }
                System.out.println("/fly <playername> - Toggle your or other's fly mode");
                System.out.println("/mxtreload - Reload the config");
                System.out.println("/discord - Get discord link of the server");
                System.out.println("/gms - Set your or other's gamemode to survival");
                System.out.println("/gmc - Set your or other's gamemode to creative");
                System.out.println("/gma - Set your or other's gamemode to adventure");
                System.out.println("/gmsp - Set your or other's gamemode to spectator");
                System.out.println("/heal - Set your or other's health to max");
                System.out.println("/suicide - Kill yourself in-game");
                System.out.println("/day - Set world time to day");
                System.out.println("/night - Set world time to night");
                System.out.println("/clean - Clean your or other's inventory");
                System.out.println("/ping - Pong!");
                System.out.println("/setspawn - Set spawn for your server");
                System.out.println("/spawn - Teleport you to spawn");
                System.out.println("/flylist - Show flying players");
                System.out.println("/kickall - Kick everyone");
                System.out.println("/safe - Turn on maintenance/safe mode");
                System.out.println(ChatColor.GREEN + "-------------------------------------");
            }

        return true;
    }

    public ArrayList<SubCommand> getSubcommands(){
        return subcommands;
    }

}
