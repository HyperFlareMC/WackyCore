package tristan.core.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import tristan.core.Core;
import tristan.core.utils.Sounds;

import java.util.HashMap;
import java.util.Map;

public class MenuListener implements Listener{

    private final Core core;

    public MenuListener(Core core){
        this.core = core;
    }
    public void onInventoryClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();
        String guiTitle = ChatColor.stripColor(event.getView().getTitle());
        for (String guiName : core.getConfig().getConfigurationSection("guis").getKeys(false)) {
            String title = ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', core.getConfig().getString("guis." + guiName + ".title", "")));
            if (guiTitle.equals(title)){
                event.setCancelled(true); // Prevent item movement
                int slot = event.getRawSlot();
                String path = "guis." + guiName + ".contents." + slot;
                if (core.getConfig().contains(path + ".action.command")){
                    String command = core.getConfig().getString(path + ".action.command", "").replace("{player}", player.getName());
                    player.performCommand(command);
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
