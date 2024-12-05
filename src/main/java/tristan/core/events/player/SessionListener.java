package tristan.core.events.player;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import tristan.core.Core;
import tristan.core.config.ConfigMgr;
import tristan.core.session.SessionManager;

import java.util.List;

public class SessionListener implements Listener{

    private final ConfigMgr configMgr;

    public SessionListener(Core core){
        this.configMgr = new ConfigMgr(core);
    }

    @EventHandler
    public void playerJoin(PlayerJoinEvent event){
        SessionManager.createSession(event.getPlayer());
        Inventory inventory = event.getPlayer().getInventory();
        Material material = configMgr.getCompassMaterial();
        if(material == null){
            return;
        }
        String name = ChatColor.translateAlternateColorCodes('&', configMgr.getCompassName());
        int slot = configMgr.getCompassSlot();
        if(slot < 0 || slot > 8){
            return;
        }
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemStack.setItemMeta(itemMeta);
        inventory.setItem(slot, itemStack);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        ItemStack currentItem = event.getCurrentItem();
        ItemStack cursorItem = event.getCursor();
        String compassName = ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', configMgr.getCompassName()));
        int compassSlot = configMgr.getCompassSlot();
        if(currentItem != null && currentItem.hasItemMeta() && currentItem.getItemMeta().hasDisplayName()){
            String itemName = ChatColor.stripColor(currentItem.getItemMeta().getDisplayName());
            if(itemName.equals(compassName)){
                event.setCancelled(true);
            }
        }
        if(cursorItem != null && cursorItem.hasItemMeta() && cursorItem.getItemMeta().hasDisplayName()){
            String cursorItemName = ChatColor.stripColor(cursorItem.getItemMeta().getDisplayName());
            if (cursorItemName.equals(compassName)){
                event.setCancelled(true);
            }
        }
        if(event.isShiftClick() && currentItem != null && currentItem.hasItemMeta()){
            String itemName = ChatColor.stripColor(currentItem.getItemMeta().getDisplayName());
            if(itemName.equals(compassName)){
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        if(event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK){
            return;
        }
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if(item == null || !item.hasItemMeta() || item.getItemMeta().getDisplayName() == null){
            return;
        }
        String itemName = ChatColor.stripColor(item.getItemMeta().getDisplayName());
        String compassName = ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', configMgr.getCompassName()));
        if(itemName.equals(compassName)){
            List<String> commands = configMgr.getCompassCommands();
            for(String command : commands){
                player.performCommand(command);
            }
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event){
        ItemStack droppedItem = event.getItemDrop().getItemStack();
        if(droppedItem.hasItemMeta() && droppedItem.getItemMeta().hasDisplayName()){
            String itemName = ChatColor.stripColor(droppedItem.getItemMeta().getDisplayName());
            String compassName = ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', configMgr.getCompassName()));
            if(itemName.equals(compassName)){
                event.setCancelled(true);
            }
        }
    }



    @EventHandler
    public void playerQuit(PlayerQuitEvent event){
        SessionManager.endSession(event.getPlayer());
    }

}
