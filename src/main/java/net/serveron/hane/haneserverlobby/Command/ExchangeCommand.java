package net.serveron.hane.haneserverlobby.Command;

import net.kyori.adventure.text.Component;
import net.serveron.hane.haneserverlobby.HaneServerLobby;
import net.serveron.hane.haneserverlobby.util.ColorSearch;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExchangeCommand implements CommandExecutor, TabCompleter {
    private final HaneServerLobby plugin;

    public ExchangeCommand(HaneServerLobby plugin) {
        this.plugin = plugin;
        plugin.getCommand("exchange").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof BlockCommandSender)){
            sender.sendMessage("このコマンドはコマンドブロック専用です。");
            return true;
        }
        BlockCommandSender cb = (BlockCommandSender) sender;

        if(args.length == 6){
            Material receive = Material.matchMaterial(args[0]);
            int receiveNum = stringToInt(args[1]);
            Material pay = Material.matchMaterial(args[2]);
            int payNum = stringToInt(args[3]);

            if(receive!=null && pay!=null && receiveNum!=0 && payNum!=0){
                Block block = cb.getBlock().getLocation().add(0,1,0).getBlock();
                if(block.getType().equals(Material.CHEST)){
                    Chest chest = (Chest)block.getWorld().getBlockAt(block.getLocation()).getState();
                    if(chest.getInventory().contains(receive,receiveNum)){
                        chest.getInventory().clear();
                        ItemStack itemStack = new ItemStack(pay,payNum);
                        ItemMeta itemMeta = itemStack.getItemMeta();
                        itemMeta.displayName(Component.text(args[4]).color(ColorSearch.stringToColor(args[5])));
                        itemStack.setItemMeta(itemMeta);
                        chest.getInventory().addItem(itemStack);
                    } else {
                        System.out.println("アイテムがありませんでした。");
                    }
                } else {
                    System.out.println("チェストが見つかりません。");
                }
            } else {
                System.out.println("Console　アイテムエラー");
            }
        } else {
            System.out.println("Console　引数指定エラー");
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String command, String[] args) {
        List<String> autoComplete = new ArrayList<>();
        if(sender.hasPermission("athle")){
            if (args.length == 1){//一段目
                autoComplete.addAll(getAllMaterials());
            } else if(args.length == 3) {
                autoComplete.addAll(getAllMaterials());
            } else if(args.length == 6){
                autoComplete.addAll(Arrays.asList("Black","DarkBlue","DarkGreen","DarkAqua","DarkRed","DarkPurple","Gold","Gray","DarkGray","Blue","Green","Aqua","Red","LightPurple","Yellow","White"));
            }
        }
        //文字比較と削除-----------------------------------------------------
        //Collections.sort(autoComplete);
        autoComplete.removeIf(str -> !str.startsWith(args[args.length - 1]));
        //------------------------------------------------------
        return autoComplete;
    }

    private List<String> getAllMaterials() {
        List<String> list = new ArrayList<>();
        for(Material mat : Material.values()) {
            list.add(mat.name().toLowerCase());
        }
        return list;
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