package tristan.core.cmds.admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tristan.core.session.SessionManager;
import tristan.core.utils.Msgs;

public class ModSpec implements CommandExecutor{

    private boolean modMode = false;

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
            if(isModMode(player)){
                player.setInvisible(false);
                player.sendMessage(Msgs.exitModMode);
                return true;
            }
            player.setInvisible(true);
            player.sendMessage(Msgs.enterModMode);
            return true;

        }
        sender.sendMessage(Msgs.mustBePlayer);
        return true;
    }

    public boolean isModMode(Player player){
        if(player.isInvisible()){
            this.modMode = true;
        }
        this.modMode = false;
        return this.modMode;
    }

}
