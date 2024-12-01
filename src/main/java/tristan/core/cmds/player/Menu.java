package tristan.core.cmds.player;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import tristan.core.Core;
import tristan.core.gui.GuiMgr;

public class Menu implements CommandExecutor{

    private final Core core;
    public GuiMgr guiMgr;

    private String usage = "Usage: /menu <name>";
    private String mustBePlayer = "You must be a player to use this command!";
    private String invalidMenu = "Invalid menu";

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
        Player player = (Player) sender;
        if(args.length == 0){
            player.sendMessage(usage);
            return true;
        }
        if(!guiMgr.isValidGui(args[0])){
            player.sendMessage(invalidMenu);
            for(String gui : guiMgr.getGuiList()){
                for(int i = 0; i < guiMgr.getGuiLength(); i++){
                    player.sendMessage(i + " : " + gui);
                }
            }
            return true;
        }
        Inventory gui = guiMgr.createGui(args[0], (Player) sender);
        player.openInventory(gui);
        return true;
    }

}
