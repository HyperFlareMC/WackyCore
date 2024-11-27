package tristan.core.cmds.inventory;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import tristan.core.Core;

public class ClearInventory implements CommandExecutor{

    private final Core core;

    private String mustBePlayer = "You must be a player to use this command without a second argument for the target player!";
    private String usage = "Usage: /clearinventory <all|hotbar|hand> <{self}|player>";
    private String[] usageArg1Options = {"all", "hand"};
    private String emptyInv = "Your inventory is already empty!";
    private String invCleared = "Your inventory has been cleared!";
    private String handCleared = "Your hand has been cleared!";
    private String invalidPlayer = "Your target player isn't on the server!";
    private String invalidOption = "Your option isn't valid!";
    private String success = "Success!";

    public ClearInventory(Core core){
        this.core = core;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(args.length == 0){
            sender.sendMessage(usage);
            return true;
        }else if(args.length == 1){
            if(isValidOption(args[0])){
                if(!(sender instanceof Player)){
                    sender.sendMessage(mustBePlayer);
                    sender.sendMessage(usage);
                    return true;
                }
                allHand((Player) sender, args[0]);
                sender.sendMessage(success);
                return true;
            }
            sender.sendMessage(usage);
            return true;
        }else if(args.length == 2){
            if(isValidOption(args[0]) && isValidTarget(args[1])){
                Player target = Bukkit.getPlayer(args[1]);
                allHand((target), args[0]);
                sender.sendMessage(success);
                target.sendMessage(success);
                return true;
            }else if(isValidOption(args[0])){
                sender.sendMessage(invalidPlayer);
                return true;
            }else if(isValidTarget(args[1])){
                sender.sendMessage(invalidOption);
                return true;
            }
            sender.sendMessage(invalidPlayer);
            sender.sendMessage(invalidOption);
            return true;
        }
        return true;
    }

    public void allHand(Player player, String arg){
        Inventory inv = player.getInventory();
        switch(arg.toLowerCase()){
            case "all":
                if(inv.isEmpty()){
                    player.sendMessage(emptyInv);
                    return;
                }
                inv.clear();
                player.sendMessage(invCleared);
                break;
            case "hand":
                player.getInventory().setItemInMainHand(null);
                player.sendMessage(handCleared);
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
