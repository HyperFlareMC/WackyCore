package tristan.core.session;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import tristan.core.Core;
import tristan.core.utils.RankUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SessionManager{

    private static Core core;

    public SessionManager(Core core){
        this.core = core;
    }

    // Create player session
    public static void createSession(Player player){
        if(!configExists(player)){
            createConfig(player);
        }
        FileConfiguration config = getConfig(player);
        saveConfig(player, config);
    }

    // End player session
    public static void endSession(Player player){
        if(!configExists(player)){
            createConfig(player);
        }
        FileConfiguration config = getConfig(player);
        saveConfig(player, config);
    }

    // Create player config
    public static void createConfig(Player player){
        File playerFile = new File(core.getDataFolder() + "/playerconfigs", getUuid(player) + ".yml");
        if(!playerFile.getParentFile().exists()){
            playerFile.getParentFile().mkdirs();
        }
        try{
            if(playerFile.createNewFile()){
                FileConfiguration config = getConfig(player);
                config.set("name", player.getName());
                config.set("uuid", getUuid(player));
                config.set("rank", "default");
                saveConfig(player, config);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    // Return player config
    public static FileConfiguration getConfig(Player player){
        File playerFile = new File(core.getDataFolder() + "/playerconfigs",  getUuid(player));
        if(!playerFile.exists()){
            createConfig(player);
        }
        return YamlConfiguration.loadConfiguration(playerFile);
    }

    // Save player config
    public static void saveConfig(Player player, FileConfiguration config){
        File playerFile = new File(core.getDataFolder() + "/playerconfigs", getUuid(player));
        try{
            config.save(playerFile);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    // Check if player is first time || check if player has a config file
    public static boolean configExists(Player player) {
        File playerFile = new File(core.getDataFolder() + "/playerconfigs", getUuid(player) + ".yml");
        return playerFile.exists();
    }

    // Return player uuid
    public static String getUuid(Player player){
        return player.getUniqueId().toString();
    }

    public static String getRank(Player player){
        FileConfiguration config = getConfig(player);
        return config.getString("rank", "default");
    }

    public static void setRank(Player player, String rank){
        FileConfiguration config = getConfig(player);
        config.set("rank", rank);
        saveConfig(player, config);
    }

    public static boolean hasPermission(Player player, String permission){
        String rank = getRank(player);
        List<String> permissions = RankUtil.getPermissions(rank);
        return permissions.contains("all") || permissions.contains(permission);
    }

}
