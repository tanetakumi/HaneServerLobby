package net.serveron.hane.haneserverlobby.listener;


import net.serveron.hane.haneserverlobby.HaneServerLobby;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlaceBlock implements Listener {

    public PlaceBlock(HaneServerLobby plugin){
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }

    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent e){
        if(!e.getPlayer().hasPermission("op")){
            e.setCancelled(true);
        }
    }
}
