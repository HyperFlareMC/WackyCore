package tristan.core;

import org.bukkit.plugin.java.JavaPlugin;
import tristan.core.cmds.CmdMgr;
import tristan.core.events.EventMgr;

public final class Core extends JavaPlugin{

    private final CmdMgr cmdMgr = new CmdMgr(this);
    private final EventMgr eventMgr = new EventMgr(this);

    @Override
    public void onEnable(){
        saveDefaultConfig();
        cmdMgr.registerCmds();
        eventMgr.registerEvents();
    }

    @Override
    public void onDisable(){
        saveConfig();
    }

}
