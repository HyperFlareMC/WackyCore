package tristan.core.cmds.admin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import tristan.core.session.SessionManager;
import tristan.core.utils.Msgs;
import tristan.core.utils.sounds.SoundsMgr;

import java.util.HashMap;

public class AddItem implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args){
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(!SessionManager.hasPermission(player, "additem")){
                player.sendMessage(Msgs.noPermission);
                return true;
            }
        }
        if(args.length < 3){
            sender.sendMessage(Msgs.addItemUsage);
            if(sender instanceof Player){
                SoundsMgr.playFail((Player) sender);
            }
            return true;
        }
        Player targetPlayer = Bukkit.getPlayer(args[0]);
        if(targetPlayer == null){
            sender.sendMessage(Msgs.invalidTarget + args[0]);
            SoundsMgr.playFail((Player) sender);
            return true;
        }
        int amount;
        try{
            amount = Integer.parseInt(args[1]);
            if(amount <= 0) throw new NumberFormatException();
        }catch(NumberFormatException e){
            sender.sendMessage(Msgs.positiveIntRequired);
            SoundsMgr.playFail((Player) sender);
            return true;
        }
        for(int i = 2; i < args.length; i++){
            String itemName = args[i].toUpperCase();
            Material material = Material.matchMaterial(itemName);
            if(material == null){
                sender.sendMessage(Msgs.invalidItem + itemName);
                continue;
            }
            ItemStack item = new ItemStack(material, amount);
            HashMap<Integer, ItemStack> leftover = targetPlayer.getInventory().addItem(item);
            if(targetPlayer == sender){
                if(!leftover.isEmpty()){
                    sender.sendMessage(Msgs.selfFullInv + material.name());
                }
            }
            if(!leftover.isEmpty()){
                sender.sendMessage(Msgs.playerFullInv + material.name());
            }
        }
        SoundsMgr.playSuccess(targetPlayer);
        SoundsMgr.playSuccess((Player) sender);
        return true;
    }

}
