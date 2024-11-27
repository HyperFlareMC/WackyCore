package tristan.core.events;

import tristan.core.Core;

public class EventMgr{

    private final Core core;

    public EventMgr(Core core){
        this.core = core;
    }

    public void registerEvents(){
        core.getServer().getPluginManager().registerEvents(new DisableDefCmds(), core);
        core.getServer().getPluginManager().registerEvents(new MenuListener(core), core);
    }

}
