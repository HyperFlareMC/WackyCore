package tristan.core.cmds.inventory;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import tristan.core.Core;
import tristan.core.session.SessionManager;
import tristan.core.utils.Msgs;
import tristan.core.utils.sounds.SoundsMgr;

public class ClearInventory implements CommandExecutor{

    private final Core core;

    private String[] usageArg1Options = {"all", "hand"};

    public ClearInventory(Core core){
        this.core = core;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(!SessionManager.hasPermission(player, "mod.clearinv")){
                player.sendMessage(Msgs.noPermission);
                return true;
            }
        }
        switch(args.length){
            case 1:
                if(isValidOption(args[0])){
                    if(!(sender instanceof Player)){
                        sender.sendMessage(Msgs.mustBePlayer);
                        return true;
                    }
                    allHand((Player) sender, args[0]);
                    SoundsMgr.playSuccess((Player) sender);
                    return true;
                }
                sender.sendMessage(Msgs.clearInventoryUsage);
                return true;
            case 2:
                if(isValidOption(args[0]) && isValidTarget(args[1])){
                    Player target = Bukkit.getPlayer(args[1]);
                    allHand((target), args[0]);
                    if(sender instanceof Player){
                        SoundsMgr.playSuccess((Player) sender);
                    }
                    SoundsMgr.playSuccess(target);
                    return true;
                }else if(isValidOption(args[0])){
                    if(sender instanceof Player){
                        SoundsMgr.playFail((Player) sender);
                    }
                    sender.sendMessage(Msgs.invalidTarget + args[1]);
                    return true;
                }else if(isValidTarget(args[1])){
                    if(sender instanceof Player){
                        SoundsMgr.playFail((Player) sender);
                    }
                    sender.sendMessage(Msgs.invalidValue + args[0]);
                    return true;
                }
                sender.sendMessage(Msgs.clearInventoryUsage);
                SoundsMgr.playFail((Player) sender);
                return true;
            default:
                sender.sendMessage(Msgs.clearInventoryUsage);
                SoundsMgr.playFail((Player) sender);
                return true;
        }
    }

    public void allHand(Player player, String arg){
        Inventory inv = player.getInventory();
        switch(arg.toLowerCase()){
            case "all":
                inv.clear();
                SoundsMgr.playSuccess(player);
                break;
            case "hand":
                player.getInventory().setItemInMainHand(null);
                SoundsMgr.playSuccess(player);
                break;
        }
    }

    public boolean isValidOption(String arg){
        for(String item : usageArg1Options){
            if(item.equalsIgnoreCase(arg)){
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean isValidTarget(String arg){
        return core.getServer().getPlayer(arg) != null;
    }

}
