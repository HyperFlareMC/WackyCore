package tristan.core.cmds.admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tristan.core.session.SessionManager;
import tristan.core.utils.Msgs;

public class ModSpec implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args){
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(!SessionManager.hasPermission(player, "modspec")){
                player.sendMessage(Msgs.noPermission);
                return true;
            }
            if(args.length != 0){
                player.sendMessage(Msgs.modspecUsage);
                return true;
            }
            setModMode(player);
            return true;

        }
        sender.sendMessage(Msgs.mustBePlayer);
        return true;
    }

    public boolean isModMode(Player player){
        return player.isInvisible();
    }

    public void setModMode(Player player){
        if(isModMode(player)){
            player.setInvisible(false);
            player.sendMessage(Msgs.exitModMode);
        }
        player.setInvisible(true);
        player.sendMessage(Msgs.enterModMode);
    }

}
