package net.serveron.hane.haneserverlobby.Listener;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import net.serveron.hane.haneserverlobby.HaneServerLobby;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.spigotmc.event.entity.EntityDismountEvent;

import java.util.Objects;
import java.util.Optional;

public class SitEvent implements Listener {
    private final HaneServerLobby plugin;

    public SitEvent(HaneServerLobby plugin){
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJump(PlayerJumpEvent e){
        if(e.getPlayer().getInventory().getItemInMainHand().equals(new ItemStack(Material.STICK))){
            Block block = e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN);
            if(block.getType().toString().contains("STAIRS")){
                if(Objects.requireNonNull(e.getPlayer().getTargetBlock(2)).equals(block)){
                    ArmorStand armorStand = e.getPlayer().getWorld().spawn(block.getLocation().add(0.5,0.3,0.5), ArmorStand.class);
                    armorStand.setCustomName("sitarmor");
                    armorStand.setMarker(true);
                    armorStand.setInvisible(true);
                    armorStand.setGravity(false);
                    armorStand.setRotation(e.getPlayer().getLocation().getYaw(),e.getPlayer().getLocation().getPitch());
                    armorStand.addPassenger(e.getPlayer());
                }
            } else {
                if(e.getPlayer().getPassengers().size()==0){
                    Optional<Entity> ent = e.getPlayer().getLocation().getNearbyEntities(2,2,2).stream()
                            .filter(entity -> entity instanceof Player)
                            .filter(entity -> !entity.getName().equals(e.getPlayer().getName()))
                            .filter(entity -> entity.getPassengers().size()==0)
                            .filter(entity -> ((Player) entity).isSneaking())
                            .findFirst();
                    ent.ifPresent(entity -> entity.addPassenger(e.getPlayer()));
                }
            }
        }

    }

    @EventHandler
    public void onLeaveChair(EntityDismountEvent e){
        if(e.getDismounted().getType().equals(EntityType.ARMOR_STAND)){
            e.getDismounted().remove();
        }
    }
}
