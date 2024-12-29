package tristan.core.cmds.admin;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import tristan.core.Core;
import tristan.core.session.SessionManager;
import tristan.core.utils.Msgs;

public class ModSpec implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args){
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(!SessionManager.hasPermission(player, "modspec")){
                player.sendMessage(Msgs.noPermission);
                return true;
            }
            if(args.length != 0){
                player.sendMessage(Msgs.modspecUsage);
                return true;
            }
            setModMode(player);
            return true;

        }
        sender.sendMessage(Msgs.mustBePlayer);
        return true;
    }

    public boolean isModMode(Player player){
        return player.isInvisible();
    }

    public void setModMode(Player player){
        if(isModMode(player)){
            player.setInvisible(false);
            player.sendMessage(Msgs.exitModMode);
        }
        player.setInvisible(true);
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        Core core = Core.getInstance();
        manager.addPacketListener(new PacketAdapter(core, PacketType.Play.Server.ENTITY_EQUIPMENT){
            @Override
            public void onPacketSending(PacketEvent event){
                if(event.getPacketType() == PacketType.Play.Server.ENTITY_EQUIPMENT){
                    int entityId = event.getPacket().getIntegers().read(0);
                    if(entityId == event.getPlayer().getEntityId()){
                        event.setCancelled(true);
                    }
                }
            }
        });
        player.sendMessage(Msgs.enterModMode);
    }

}
