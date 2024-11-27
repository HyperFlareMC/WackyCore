package tristan.core.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;

public class DisableDefCmds implements Listener{

    // TODO put this into a RandomValues class in utils that holds random variables like this 60000 list of cmds
    private final String[] vanillaCmds = {"aliases", "bukkit", "minecraft", "advancement",
            "attribute", "ban", "ban-ip", "banlist", "bossbar", "clear", "clone",
            "damage", "data", "datapack", "debug", "defaultgamemode", "deop", "difficulty",
            "effect", "enchant", "execute", "experience", "fill", "fillbiome", "forceload",
            "function", "gamemode", "gamerule", "give", "help", "item", "jfr", "kick", "kill",
            "list", "locate", "loot", "me", "minecraft:help", "minecraft:reload", "msg", "op",
            "pardon", "pardon-ip", "particle", "perf", "place", "playsound", "plugins", "random",
            "recipe", "reload", "restart", "return", "ride", "rotate", "save-all", "save-off",
            "save-on", "say", "schedule", "scoreboard", "seed", "setblock", "setidletimeout",
            "setworldspawn", "spawnpoint", "spectate", "spigot", "spreadplayers", "stop",
            "stopsound", "summon", "tag", "team", "teammsg", "teleport", "tell", "tellraw",
            "tick", "time", "timings", "title", "tm", "tp", "tps", "transfer", "trigger",
            "version", "w", "weather", "whitelist", "worldborder", "xp"
    };

    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event){
        String message = event.getMessage();
        String command = message.split(" ")[0].substring(1).toLowerCase();
        for(String cmd : vanillaCmds){
            if(command.equals(cmd)){
                event.setCancelled(true);
                event.getPlayer().sendMessage("No.");
                return;
            }
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandSendEvent event){
        for(String cmd : vanillaCmds){
            if(event.getCommands().contains(cmd)){
                event.getCommands().remove(cmd);
                event.getPlayer().sendMessage("No.");
            }
            return;
        }
    }

}
