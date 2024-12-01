package tristan.core.config;

import tristan.core.Core;

public class ConfigMgr{

    private final Core core;

    public ConfigMgr(Core core){
        this.core = core;
    }

    public String getGuis(){
        return (String) core.getConfig().get("guis");
    }

    public boolean isGui(String guiName){
        if(core.getConfig().contains("guis." + guiName)){
            return true;
        }
        return false;
    }

}
