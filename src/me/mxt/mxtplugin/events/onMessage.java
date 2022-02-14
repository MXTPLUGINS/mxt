package me.mxt.mxtplugin.events;

        import org.bukkit.Bukkit;
        import org.bukkit.ChatColor;
        import org.bukkit.entity.Player;
        import org.bukkit.event.Listener;
        import org.bukkit.event.player.AsyncPlayerChatEvent;

        import static me.mxt.mxtplugin.commands.AfkCommand.afk_players;

public class onMessage implements Listener {

    public void chatEvent(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();

        if (afk_players.contains(p)){
            afk_players.remove(p);
            Bukkit.broadcastMessage(ChatColor.GRAY + p.getDisplayName() + " is no longer afk");
        }
    }


}
