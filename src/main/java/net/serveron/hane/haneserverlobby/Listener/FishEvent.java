package net.serveron.hane.haneserverlobby.Listener;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.serveron.hane.haneserverlobby.HaneServerLobby;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerFishEvent;

import java.awt.*;

public class FishEvent implements Listener {
    private final HaneServerLobby plugin;

    public FishEvent(HaneServerLobby plugin){
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler
    public void onPlayerFish(PlayerFishEvent e) {
        if(e.getCaught()!=null){
            if(e.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)){
                Component component = Component.text(e.getCaught().getName()).color(TextColor.color(0xffff00))
                        .append(Component.text("を捕まえた").color(TextColor.color(0x00ff00)))
                        .append(Component.text("\n釣り回数"+plugin.getPlayerGame().onFish(e.getPlayer())+"回").color(TextColor.color(0x00ff00)));
                e.getPlayer().sendMessage(component);
            }
        }
    }
    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent e){
        if(!e.getPlayer().hasPermission("op")){
            e.setCancelled(true);
        }
    }
}
