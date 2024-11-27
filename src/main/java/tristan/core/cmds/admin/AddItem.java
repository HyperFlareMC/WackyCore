package tristan.core.cmds.admin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class AddItem implements CommandExecutor{

    private String usage = "Usage: /additem <player> <amount> <+items+>";
    private String invalidTarget = "The target you have entered is not promptly present!";
    private String positiveIntRequired = "You must enter a positive integer!";
    private String invalidItem = "You have entered an invalid item!";
    private String playerFullInv = "The target player has a full inventory!";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args){
        Player targetPlayer = Bukkit.getPlayer(args[0]);
        int amount;
        if(args.length < 3){
            sender.sendMessage(usage);
            return false;
        }
        if(targetPlayer == null){
            sender.sendMessage(invalidTarget);
            return false;
        }
        try{
            amount = Integer.parseInt(args[1]);
            if(amount <= 0) throw new NumberFormatException();
        }catch(NumberFormatException e){
            sender.sendMessage(positiveIntRequired);
            return true;
        }
        for(int i = 2; i < args.length; i++){
            String itemName = args[i].toUpperCase();
            Material material = Material.matchMaterial(itemName);
            if(material == null){
                sender.sendMessage(invalidItem + ": " + itemName);
                continue;
            }
            ItemStack item = new ItemStack(material, amount);
            HashMap<Integer, ItemStack> leftover = targetPlayer.getInventory().addItem(item);
            if(!leftover.isEmpty()){
                sender.sendMessage(playerFullInv + " and hasn't received: " + material.name());
            }
            sender.sendMessage(targetPlayer.getName() + " has received " + material.name());
            targetPlayer.sendMessage("You have received" + material.name());
        }
        return true;
    }

}
