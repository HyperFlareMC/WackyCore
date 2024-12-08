package tristan.core.cmds.inventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import tristan.core.Core;
import tristan.core.config.ConfigMgr;
import tristan.core.session.SessionManager;
import tristan.core.utils.Msgs;

public class ClearInventory implements CommandExecutor{

    private final Core core;
    private final ConfigMgr configMgr;

    private String[] usageArg1Options = {"all", "hand"};

    public ClearInventory(Core core){
        this.core = core;
        this.configMgr = new ConfigMgr(core);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(!SessionManager.hasPermission(player, "clearinv")){
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
                    sender.sendMessage(Msgs.clearInvSuccess + args[0]);
                    return true;
                }
                sender.sendMessage(Msgs.clearInventoryUsage);
                return true;
            case 2:
                if(isValidOption(args[0]) && isValidTarget(args[1])){
                    Player target = Bukkit.getPlayer(args[1]);
                    allHand((target), args[0]);
                    if(target == sender){
                        sender.sendMessage(Msgs.clearInvSuccess + args[0]);
                        return true;
                    }
                    sender.sendMessage(Msgs.clearInvSuccessOther + args[0]);
                    return true;
                }else if(isValidOption(args[0])){
                    sender.sendMessage(Msgs.invalidTarget + args[1]);
                    return true;
                }else if(isValidTarget(args[1])){
                    sender.sendMessage(Msgs.invalidValue + args[0]);
                    return true;
                }
                sender.sendMessage(Msgs.clearInventoryUsage);
                return true;
            default:
                sender.sendMessage(Msgs.clearInventoryUsage);
                return true;
        }
    }

    public void allHand(Player player, String arg){
        Inventory inv = player.getInventory();
        switch(arg.toLowerCase()){
            case "all":
                inv.clear();
                Material material = configMgr.getCompassMaterial();
                if(material == null){
                    return;
                }
                String name = ChatColor.translateAlternateColorCodes('&', configMgr.getCompassName());
                int slot = configMgr.getCompassSlot();
                if(slot < 0 || slot > 8){
                    return;
                }
                ItemStack itemStack = new ItemStack(material);
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName(name);
                itemStack.setItemMeta(itemMeta);
                inv.setItem(slot, itemStack);
                break;
            case "hand":
                player.getInventory().setItemInMainHand(null);
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
