package me.mxt.mxtplugin.events;

import com.connorlinfoot.actionbarapi.ActionBarAPI;
import com.connorlinfoot.titleapi.TitleAPI;
import me.mxt.mxtplugin.files.CustomConfig;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static me.mxt.mxtplugin.Main.ver;
import static me.mxt.mxtplugin.commands.VanishCommand.vanish_players;

public class JoinEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player player = (Player)e.getPlayer();
            if (CustomConfig.get().getBoolean("welcome-title.enabled") == true){
                TitleAPI.sendTitle(player, 25, 25, 25, CustomConfig.get().getString("welcome-title.message"), CustomConfig.get().getString("welcome-title.messagesubtitle"));
            } else if(CustomConfig.get().getBoolean("welcome-title.enabled") == false){
                player.sendMessage("");
            }
            if (CustomConfig.get().getBoolean("welcome-bar.enabled") == true){
                ActionBarAPI.sendActionBar(e.getPlayer(), ChatColor.translateAlternateColorCodes ('&', CustomConfig.get().getString("welcome-bar.message")));
            } else if (CustomConfig.get().getBoolean("welcome-bar.enabled") == false){
                player.sendMessage("");
            }
            if (player.isOp() || player.hasPermission("mxt.notify")){
                if (CustomConfig.get().getDouble("version") == ver){
                    player.sendMessage(ChatColor.GREEN + "[MXT] Your config.yml is up to date!");
                } else if((CustomConfig.get().getDouble("version") != ver)){
                    player.sendMessage(ChatColor.RED + "[MXT] Your config.yml is not up to date, delete the config and let it make it self or set your config version to " + ver + " to stop this warning");
                }

            } else {
                player.sendMessage("");
            }

            for (Player p : vanish_players){
                e.getPlayer().hidePlayer(p);
            }


    }

}
