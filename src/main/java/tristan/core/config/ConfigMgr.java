package tristan.core.config;

import org.bukkit.Material;
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

    public Material getCompassMaterial(){
        Material material = Material.matchMaterial(core.getConfig().getString("compass.material"));
        return material;
    }

    public String getCompassName(){
        return core.getConfig().getString("compass.name");
    }

    public int getCompassSlot(){
        return core.getConfig().getInt("compass.slot");
    }

    public List<String> getCompassCommands(){
        return core.getConfig().getStringList("compass.commands");
    }

}
