package tristan.core.cmds.player;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import tristan.core.Core;
import tristan.core.config.ConfigMgr;
import tristan.core.gui.GuiMgr;
import tristan.core.session.SessionManager;
import tristan.core.utils.Msgs;

public class Menu implements CommandExecutor{

    public GuiMgr guiMgr;
    private final ConfigMgr configMgr;

    public Menu(Core core){
        this.guiMgr = new GuiMgr(core);
        this.configMgr = new ConfigMgr(core);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args){
        if(!(sender instanceof Player)){
            sender.sendMessage(Msgs.mustBePlayer);
            return true;
        }
        Player player = (Player) sender;
        if(!SessionManager.hasPermission(player, "menu")){
            player.sendMessage(Msgs.noPermission);
            return true;
        }
        if(args.length != 1){
            player.sendMessage(Msgs.menuUsage);
            return true;
        }
        if(!configMgr.isGui(args[0])){
            player.sendMessage(Msgs.invalidMenu + args[0]);
            return true;
        }
        Inventory gui = guiMgr.createGui(args[0], (Player) sender);
        player.openInventory(gui);
        return true;
    }

}
