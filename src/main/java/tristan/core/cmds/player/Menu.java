package tristan.core.cmds.player;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import tristan.core.Core;
import tristan.core.gui.GuiMgr;
import tristan.core.utils.Msgs;
import tristan.core.utils.sounds.SoundsMgr;

public class Menu implements CommandExecutor{

    private final Core core;
    public GuiMgr guiMgr;

    private String invalidMenu = "Invalid menu";

    public Menu(Core core){
        this.core = core;
        this.guiMgr = new GuiMgr(core);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args){
        if(!(sender instanceof Player)){
            sender.sendMessage(Msgs.mustBePlayer);
            return true;
        }
        Player player = (Player) sender;
        if(args.length == 0){
            player.sendMessage(Msgs.menuUsage);
            SoundsMgr.playFail(player);
            return true;
        }
        if(!guiMgr.isValidGui(args[0])){
            player.sendMessage(invalidMenu);
            SoundsMgr.playFail(player);
            return true;
        }
        Inventory gui = guiMgr.createGui(args[0], (Player) sender);
        player.openInventory(gui);
        return true;
    }

}
