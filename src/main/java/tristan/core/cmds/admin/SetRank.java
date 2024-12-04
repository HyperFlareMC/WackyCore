package tristan.core.cmds.admin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tristan.core.Core;
import tristan.core.session.SessionManager;
import tristan.core.utils.Msgs;
import tristan.core.utils.RankUtil;

public class SetRank implements CommandExecutor{

    private final Core core;

    public SetRank(Core core){
        this.core = core;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args){
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(!SessionManager.hasPermission(player, "setrank")){
                player.sendMessage(Msgs.noPermission);
                return true;
            }
        }
        if(args.length < 2){
            sender.sendMessage(Msgs.setRankUsage);
            return true;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if(target == null){
            sender.sendMessage(Msgs.invalidTarget + args[0]);
            return true;
        }
        String rank = args[1];
        if(!core.getConfig().contains("ranks." + rank)){
            sender.sendMessage(Msgs.invalidRank + rank);
            return true;
        }
        SessionManager.setRank(target, rank);
        core.reloadConfig();
        RankUtil.loadRanksFromConfig(core);
        return true;
    }

}
