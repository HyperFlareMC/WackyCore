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
import java.util.logging.Logger;

public class GuiMgr{

    private final Core core;
    private final Logger logger;
    private final FileConfiguration config;

    public GuiMgr(Core core) {
        this.core = core;
        this.logger = core.getLogger();
        this.config = core.getConfig();
    }

    public Inventory createGui(String guiName, Player player){
        FileConfiguration config = core.getConfig();
        String path = "guis." + guiName;
        String title = ChatColor.translateAlternateColorCodes('&', config.getString(path + ".title", "Untitled"));
        int size = validateSize(config.getInt(path + ".size", 9));
        Inventory gui = Bukkit.createInventory(player, size, title);
        ConfigurationSection contents = config.getConfigurationSection(path + ".contents");
        if(contents == null){
            logger.warning("No contents configured for GUI: " + guiName);
            return gui;
        }
        populateGui(gui, contents, path);
        return gui;
    }

    private int validateSize(int size){
        if(size % 9 != 0 || size < 9 || size > 54){
            logger.warning("Invalid inventory size. Defaulting to 9.");
            return 9;
        }
        return size;
    }

    private void populateGui(Inventory gui, ConfigurationSection contents, String basePath){
        for(String slotStr : contents.getKeys(false)){
            try{
                int slot = Integer.parseInt(slotStr);
                if(slot < 0 || slot >= gui.getSize()){
                    logger.warning("Slot " + slotStr + " is out of bounds for GUI at " + basePath);
                    continue;
                }
                ItemStack item = createItem(contents.getConfigurationSection(slotStr));
                if(item != null){
                    gui.setItem(slot, item);
                }
            }catch(NumberFormatException e){
                logger.warning("Invalid slot '" + slotStr + "' for GUI at " + basePath);
            }
        }
    }

    private ItemStack createItem(ConfigurationSection section){
        if(section == null) return null;
        String materialName = section.getString("material", "AIR");
        Material material = Material.matchMaterial(materialName);
        if(material == null || material == Material.AIR){
            logger.warning("Invalid or missing material: " + materialName);
            return null;
        }
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if(meta == null) return item;

        String displayName = section.getString("name");
        if(displayName != null){
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        }
        List<String> lore = section.getStringList("lore");
        if(!lore.isEmpty()){
            for(int i = 0; i < lore.size(); i++){
                lore.set(i, ChatColor.translateAlternateColorCodes('&', lore.get(i)));
            }
            meta.setLore(lore);
        }
        item.setItemMeta(meta);
        return item;
    }

    public List<String> getGuiList(){
        return config.getStringList("guis");
    }

    public int getGuiLength(){
        return config.getStringList("guis").size();
    }

    public boolean isValidGui(String arg){
        return core.getConfig().contains("guis." + arg);
    }

}