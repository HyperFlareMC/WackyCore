package tristan.core.config;

import org.bukkit.configuration.ConfigurationSection;
import tristan.core.Core;

import java.util.List;

public class ConfigMgr{

    private final Core core;

    public ConfigMgr(Core core){
        this.core = core;
    }

    public boolean isGui(String guiName){
        return core.getConfig().contains("guis." + guiName);
    }

    public ConfigurationSection getGuis(){
        return core.getConfig().getConfigurationSection("guis");
    }

    public String getGuiTitle(String guiName){
        return (String) core.getConfig().get("guis." + guiName + ".title", "Untitled");
    }

    public int getGuiSize(String guiName){
        return core.getConfig().getInt("guis." + guiName + ".size", 9);
    }

    public ConfigurationSection getGuiContents(String guiName){
        return core.getConfig().getConfigurationSection("guis." + guiName + ".contents");
    }

    public List<String> getContentCommands(String guiName, String contentKey){
        return core.getConfig().getStringList("guis." + guiName + ".contents." + contentKey + ".commands");
    }

    // methods to access specific parts of gui contents, not necessary as of rn
    /*public Map<String, Object> getContent(String guiName, String contentKey){
        return core.getConfig().getConfigurationSection("guis." + guiName + ".contents." + contentKey).getValues(false);
    }

    public int getContentLocation(String guiName){
        return core.getConfig().getInt("guis." + guiName + ".contents.");
    }

    // CONTENT KEY IS SLOT IN INVENTORY
    public String getContentMaterial(String guiName, String contentKey){
        return core.getConfig().getString("guis." + guiName + ".contents." + contentKey + ".material", "AIR");
    }

    public String getContentName(String guiName, String contentKey){
        return core.getConfig().getString("guis." + guiName + ".contents." + contentKey + ".name", "Untitled");
    }

    public List<String> getContentLore(String guiName, String contentKey) {
        return core.getConfig().getStringList("guis." + guiName + ".contents." + contentKey + ".lore");
    }*/

}
