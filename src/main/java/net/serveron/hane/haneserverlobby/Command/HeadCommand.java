package net.serveron.hane.haneserverlobby.Command;

import net.kyori.adventure.text.Component;
import net.serveron.hane.haneserverlobby.HaneServerLobby;
import net.serveron.hane.haneserverlobby.util.MainColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HeadCommand implements CommandExecutor{
    private final HaneServerLobby plugin;

    public HeadCommand(HaneServerLobby plugin) {
        this.plugin = plugin;
        plugin.getCommand("head").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You cannot use commands with the console.");
            return true;
        }
        Player player = (Player) sender;

        ItemStack itemStack = player.getInventory().getItemInMainHand();
        if(itemStack.hasItemMeta()){
            if(itemStack.getItemMeta().hasDisplayName()) {
                player.getInventory().setHelmet(itemStack);
                itemStack.setAmount(itemStack.getAmount() - 1);
                player.getInventory().setItemInMainHand(itemStack);
                System.out.println("[HaneServerLobby]"+player.getName()+"が頭に付けました");
            }
        }
        return true;
    }

    private int stringToInt(String str){
        int x = -1;
        try{
            x = Integer.parseInt(str);
        }
        catch(Exception ignored){
        }
        return x;
    }
}