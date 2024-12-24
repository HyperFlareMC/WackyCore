package tristan.core.utils;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import tristan.core.Core;
import java.util.*;

public class RankUtil{

    private static final Map<String, String> rankPrefixes = new HashMap<>();
    private static final Map<String, List<String>> rankPermissions = new HashMap<>();

    public static void loadRanksFromConfig(Core core){
        FileConfiguration config = core.getConfig();
        rankPrefixes.clear();
        rankPermissions.clear();
        if(config.contains("ranks")){
            for(String rank : config.getConfigurationSection("ranks").getKeys(false)){
                String prefix = config.getString("ranks." + rank + ".prefix");
                List<String> permissions = config.getStringList("ranks." + rank + ".permissions");
                rankPrefixes.put(rank, ChatColor.translateAlternateColorCodes('&', prefix));
                rankPermissions.put(rank, permissions);
            }
        }
    }

    public static String getPrefix(String rank){
        return rankPrefixes.getOrDefault(rank,
                    Core.getInstance().getConfig().getString("ranks.default.prefix")
                );
    }

    public static List<String> getPermissions(String rank){
        return rankPermissions.getOrDefault(rank, Collections.emptyList());
    }

}
