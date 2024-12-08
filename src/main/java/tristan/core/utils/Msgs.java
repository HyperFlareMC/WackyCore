package tristan.core.utils;

public class Msgs{

    // TODO MAKE ALL OF THIS CUSTOMIZABLE IN A BIG LIST IN THE BOTTOM OF THE CONFIG.YMl

    public static String noPermission = "You do not have permission to use this command!";

    public static String invalidTarget = "Invalid target: ";
    public static String invalidItem = "Invalid item: ";
    public static String invalidValue = "Invalid value: ";
    public static String invalidMenu = "Invalid menu: ";
    public static String invalidRank = "Invalid rank: ";

    public static String addItemUsage = "Usage: /additem <player> <amount> <+items+>";
    public static String clearInventoryUsage = "Usage: /clearinventory <all|hand> <{self}|player>";
    public static String menuUsage = "Usage: /menu <name>";
    public static String setRankUsage = "Usage: /setrank {player} {rank}";
    public static String kickUsage = "Usage: /kick <player>";
    public static String gamemodeUsage = "Usage: /gamemode <a|s|c> <player>";
    public static String modspecUsage = "Usage: /modspec";

    public static String playerFullInv = "The target player has a full inventory and has not received: ";
    public static String selfFullInv = "You have a full inventory and have not received: ";

    public static String positiveIntRequired = "A positive integer is required!";

    public static String mustBePlayer = "You must be a player to use this command!";

    public static String getKicked = "You have been kicked!";

    public static String clearInvSuccess = "You have successfully cleared your: ";
    public static String clearInvSuccessOther = "You have successfully cleared the target's: ";
    public static String rankUpdateSuccess = "Rank update success!";
    public static String enterModMode = "You have entered mod mode!";
    public static String exitModMode = "You have exited mod mode!";

    public static String[] vanillaCmds = {"aliases", "bukkit", "minecraft", "advancement",
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

}
