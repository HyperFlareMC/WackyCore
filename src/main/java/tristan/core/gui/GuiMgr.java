package tristan.core.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import tristan.core.Core;

import java.util.List;

public class GuiMgr{

    private final Core core;

    public GuiMgr(Core core){
        this.core = core;
    }

    public Inventory createGui(String guiName, Player player){
        FileConfiguration config = core.getConfig();
        String path = "guis." + guiName;

        String title = ChatColor.translateAlternateColorCodes('&', config.getString(path + ".title", "Untitled"));

        int size = config.getInt(path + ".size");
        if(size % 9 != 0 || size < 9 || size > 54){
            core.getLogger().warning("Invalid size for menu " + guiName + ". Defaulting to 9.");
            size = 9;
        }

        Inventory gui = Bukkit.createInventory(player, size, title);

        ConfigurationSection contents = config.getConfigurationSection(path + ".contents");
        if(contents == null){
            core.getLogger().warning("No contents configured for " + guiName);
            return Bukkit.createInventory(player, 9, "uh, this isn't supposed to happen!");
        }

        for(String slotStr : contents.getKeys(false)){
            try{
                int slot = Integer.parseInt(slotStr);
                String itemPath = path + ".contents." + slotStr;
                Material material = Material.matchMaterial(contents.getString(itemPath));
                if(material == null){
                    core.getLogger().warning("Invalid material for menu " + guiName + " in slot " + slotStr);
                    continue;
                }
                ItemStack item = new ItemStack(material);
                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', contents.getString(itemPath, "Untitled")));
                itemMeta.setLore(config.getStringList(itemPath + ".lore"));
                item.setItemMeta(itemMeta);
                gui.setItem(slot, item);
            }catch(NumberFormatException e){
                core.getLogger().warning("Invalid slot '" + slotStr + "' for menu " + guiName);
            }

        }
        return gui;
    }

}
