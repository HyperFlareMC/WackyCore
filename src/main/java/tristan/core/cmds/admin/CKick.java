package tristan.core.cmds.admin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tristan.core.session.SessionManager;
import tristan.core.utils.Msgs;

public class CKick implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args){
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(!SessionManager.hasPermission(player, "ckick")){
                player.sendMessage(Msgs.noPermission);
                return true;
            }
        }
        if(args.length == 1){
            Player target = Bukkit.getPlayer(args[0]);
            if(target != null){
                target.kickPlayer(Msgs.getKicked);
                return true;
            }
            sender.sendMessage(Msgs.invalidTarget + args[0]);
            return true;
        }
        sender.sendMessage(Msgs.kickUsage);
        return true;
    }
}
