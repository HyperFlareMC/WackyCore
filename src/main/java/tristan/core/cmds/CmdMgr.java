package tristan.core.cmds;

import org.bukkit.command.PluginCommand;
import tristan.core.Core;
import tristan.core.cmds.admin.*;
import tristan.core.cmds.inventory.ClearInventory;
import tristan.core.cmds.player.Menu;

public class CmdMgr{

    private final Core core;

    public CmdMgr(Core core){
        this.core = core;
    }

    public void registerCmds(){
        PluginCommand inv = core.getCommand("clearinventory");
        inv.setExecutor(new ClearInventory(core));
        PluginCommand menu = core.getCommand("menu");
        menu.setExecutor(new Menu(core));
        PluginCommand addItem = core.getCommand("additem");
        addItem.setExecutor(new AddItem());
        PluginCommand setRank = core.getCommand("setrank");
        setRank.setExecutor(new SetRank(core));
        PluginCommand ckick = core.getCommand("ckick");
        ckick.setExecutor(new CKick());
        PluginCommand cgamemode = core.getCommand("cgamemode");
        cgamemode.setExecutor(new CGamemode());
        PluginCommand modspec = core.getCommand("modspec");
        modspec.setExecutor(new ModSpec());
    }

}
