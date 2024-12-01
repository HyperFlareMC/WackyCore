package tristan.core.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import tristan.core.Core;

public class MenuListener implements Listener{

    private final Core core;

    public MenuListener(Core core){
        this.core = core;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();
        String eventTitle = ChatColor.stripColor(event.getView().getTitle());
        for(String guiName : core.getConfig().getConfigurationSection("guis").getKeys(false)){
            String title = ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', core.getConfig().getString("guis." + guiName + ".title", "Untitled")));
            if(eventTitle.equals(title)){
                event.setCancelled(true);
                int slot = event.getRawSlot();
                String path = "guis." + guiName + ".contents." + slot;
                if(core.getConfig().contains(path + ".commands")){
                    for(String command : core.getConfig().getStringList(path + ".commands")){
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
