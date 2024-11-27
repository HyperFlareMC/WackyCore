package tristan.core.cmds.player;

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
import tristan.core.gui.GuiMgr;

import java.util.List;

public class Menu implements CommandExecutor{

    private final Core core;
    public GuiMgr guiMgr;

    private String usage = "Usage: /menu <name>";
    private String mustBePlayer = "You must be a player to use this command!";
    private String invalidMenu = "Invalid menu!";

    public Menu(Core core){
        this.core = core;
        this.guiMgr = new GuiMgr(core);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args){
        if(!(sender instanceof Player)){
            sender.sendMessage(mustBePlayer);
            return true;
        }
        if(args.length == 0){
            sender.sendMessage(usage);
            return true;
        }
        if(!isValidGui(args[0])){
            sender.sendMessage(invalidMenu);
            return true;
        }
        Inventory inventory = guiMgr.createGui(args[0], (Player) sender);
        ((Player) sender).openInventory(inventory);
        return true;
    }

    public boolean isValidGui(String arg){
        return core.getConfig().contains("guis." + arg);
    }

}
