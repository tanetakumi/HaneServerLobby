package net.serveron.hane.haneserverlobby.Listener;

import net.serveron.hane.haneserverlobby.HaneServerLobby;
import net.serveron.hane.haneserverlobby.util.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class JoinQuitEvent implements Listener {
    private final HaneServerLobby plugin;

    public JoinQuitEvent(HaneServerLobby plugin){
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent e){

        plugin.getPlayerGame().playerJoin(e.getPlayer());
        System.out.println("Join");
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        e.getPlayer().teleportAsync(new Location(e.getPlayer().getWorld(),0.5,46,0.5,90,0));
        String text = ChatColor.RED+"は"+ChatColor.GOLD+"ね"+ChatColor.YELLOW+"鯖"+ChatColor.GREEN+"へ"+ChatColor.AQUA+"よ"+ChatColor.DARK_AQUA+"う"+ChatColor.DARK_BLUE+"こ"+ChatColor.LIGHT_PURPLE+"そ!";
        e.getPlayer().sendMessage(ChatColor.BOLD+e.getPlayer().getName()+"さん、"+text+ChatColor.AQUA+"\nこの鯖で遊ぶ前に必ずインベントリにある本のルールを読んでください。");
        e.getPlayer().getInventory().setItem(0,plugin.getHsConfig().getRulebook().getItemStack());

        ItemStack itemStack = new ItemStack(Material.STICK);
        e.getPlayer().getInventory().setItem(1,itemStack);

        ItemStack testEnchant = new ItemStack (Material.FISHING_ROD);
        ItemMeta testEnchantMeta = testEnchant.getItemMeta();
        testEnchantMeta.addEnchant(Enchantment.LURE, 5, true);
        testEnchantMeta.addEnchant(Enchantment.DURABILITY,3,true);
        testEnchantMeta.addEnchant(Enchantment.MENDING,1,true);
        testEnchant.setItemMeta(testEnchantMeta);
        e.getPlayer().getInventory().setItem(2,testEnchant);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        plugin.getPlayerGame().playerQuit(e.getPlayer());
        if(e.getPlayer().isBanned()){
            plugin.runAsyncDiscord(e.getPlayer().getName()+"はハッククライアントで入ったため");
        }

    }
}
