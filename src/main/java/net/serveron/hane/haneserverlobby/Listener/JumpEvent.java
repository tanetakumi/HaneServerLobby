package net.serveron.hane.haneserverlobby.Listener;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import net.serveron.hane.haneserverlobby.HaneServerLobby;
import net.serveron.hane.haneserverlobby.util.PlayerData;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class JumpEvent implements Listener {

   private final HaneServerLobby plugin;

    public JumpEvent(HaneServerLobby plugin){
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }

    @EventHandler
    public void onJump(PlayerJumpEvent e){
        Block block = e.getFrom().getBlock().getRelative(BlockFace.DOWN);
        if(block.getType().equals(Material.BARRIER) && (block.getLocation().getBlockY()==91 || block.getLocation().getBlockY()==100)){
            e.getPlayer().teleport(new Location(e.getPlayer().getWorld(),-27.5,93,63.5,-180,0));
        } else if(block.getType().equals(Material.NETHERITE_BLOCK)){
            e.getPlayer().teleport(new Location(e.getPlayer().getWorld(),-65.5,45,21.5,0,0));
        }

    }
}
