package tristan.core.events.player;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import tristan.core.session.SessionManager;
import tristan.core.utils.RankUtil;

public class ChatListener implements Listener{

    @EventHandler
    public void playerChat(AsyncPlayerChatEvent event){
        String rank = SessionManager.getRank(event.getPlayer());
        String prefix = RankUtil.getPrefix(rank);
        String format = prefix + ChatColor.WHITE + "%1$s" + ChatColor.GRAY + ": " + "%2$s";
        event.setFormat(format);
    }

}
