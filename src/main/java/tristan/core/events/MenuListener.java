package tristan.core.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import tristan.core.Core;
import tristan.core.config.ConfigMgr;

public class MenuListener implements Listener{

    private final Core core;
    private final ConfigMgr configMgr;

    public MenuListener(Core core){
        this.core = core;
        this.configMgr = new ConfigMgr(core);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        String eventTitle = ChatColor.stripColor(event.getView().getTitle());
        for(String guiName : configMgr.getGuis().getKeys(false)){
            String title = ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', configMgr.getGuiTitle(guiName)));
            if(eventTitle.equals(title)){
                event.setCancelled(true);
                int slot = event.getRawSlot();
                String path = "guis." + guiName + ".contents." + slot;
                if(core.getConfig().contains(path + ".commands")){
                    for(String command : configMgr.getContentCommands(guiName, String.valueOf(slot))){
                        command = command.replace("{player}", player.getName());
                        player.performCommand(command);
                    }
                }
                return;
            }
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event){
        if(event.getView().getTitle().equals(core.getConfig().getString("guis."))){
            event.setCancelled(true);
        }
    }

}
