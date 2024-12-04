package tristan.core.events.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import tristan.core.session.SessionManager;

public class SessionListener implements Listener{

    @EventHandler
    public void playerJoin(PlayerJoinEvent event){
        SessionManager.createSession(event.getPlayer());
    }

    @EventHandler
    public void playerQuit(PlayerQuitEvent event){
        SessionManager.endSession(event.getPlayer());
    }

}
