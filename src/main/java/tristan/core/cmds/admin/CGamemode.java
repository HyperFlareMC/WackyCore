package tristan.core.cmds.admin;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tristan.core.session.SessionManager;
import tristan.core.utils.Msgs;

public class CGamemode implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args){
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(!SessionManager.hasPermission(player, "cgamemode")){
                player.sendMessage(Msgs.noPermission);
                return true;
            }
        }
        switch(args.length){
            case 0:
                sender.sendMessage(Msgs.gamemodeUsage);
                return true;
            case 1:
                if(!(sender instanceof Player)){
                    sender.sendMessage(Msgs.mustBePlayer);
                    return true;
                }
                Player player = (Player) sender;
                switch(args[0]){
                    case "a":
                        player.setGameMode(GameMode.ADVENTURE);
                        return true;
                    case "s":
                        player.setGameMode(GameMode.SURVIVAL);
                        return true;
                    case "c":
                        player.setGameMode(GameMode.CREATIVE);
                        return true;
                    default:
                        player.sendMessage(Msgs.gamemodeUsage);
                        return true;
                }
            case 2:
                Player target = Bukkit.getPlayer(args[1]);
                if(target == null){
                    sender.sendMessage(Msgs.invalidTarget + target);
                    return true;
                }
                switch(args[0]){
                    case "a":
                        target.setGameMode(GameMode.ADVENTURE);
                        return true;
                    case "s":
                        target.setGameMode(GameMode.SURVIVAL);
                        return true;
                    case "c":
                        target.setGameMode(GameMode.CREATIVE);
                        return true;
                    default:
                        sender.sendMessage(Msgs.gamemodeUsage);
                        return true;
                }
            default:
                sender.sendMessage(Msgs.gamemodeUsage);
        }
        return true;
    }

}
