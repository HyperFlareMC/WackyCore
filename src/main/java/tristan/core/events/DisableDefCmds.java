package tristan.core.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;
import tristan.core.utils.Msgs;

public class DisableDefCmds implements Listener{

    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event){
        String message = event.getMessage();
        String command = message.split(" ")[0].substring(1).toLowerCase();
        for(String cmd : Msgs.vanillaCmds){
            if(command.equals(cmd)){
                event.setCancelled(true);
                return;
            }
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandSendEvent event){
        for(String cmd : Msgs.vanillaCmds){
            if(event.getCommands().contains(cmd)){
                event.getCommands().remove(cmd);
            }
            return;
        }
    }

}
