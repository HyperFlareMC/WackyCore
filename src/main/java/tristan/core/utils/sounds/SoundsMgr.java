package tristan.core.utils.sounds;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundsMgr{

    public static void playSuccess(Player player){
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.75f, 1.2f);
    }

    public static void playFail(Player player){
        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 0.75f, 0.8f);
    }

}
