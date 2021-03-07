package net.serveron.hane.haneserverlobby.Command;

import net.kyori.adventure.text.Component;
import net.serveron.hane.haneserverlobby.HaneServerLobby;
import net.serveron.hane.haneserverlobby.util.MainColor;
import net.serveron.hane.haneserverlobby.util.PlayerSearch;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TeleportCommand implements CommandExecutor, TabCompleter {
    private final HaneServerLobby plugin;

    public TeleportCommand(HaneServerLobby plugin) {
        this.plugin = plugin;
        plugin.getCommand("tele").setExecutor(this);
        plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof BlockCommandSender)){
            sender.sendMessage("このコマンドはコマンドブロック専用です。");
            return true;
        }
        BlockCommandSender cb = (BlockCommandSender) sender;

        if(args.length == 1){
            try{
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                DataOutputStream dos = new DataOutputStream(baos);
                dos.writeUTF("Connect");
                dos.writeUTF(args[0]);
                PlayerSearch.getNearbyPlayer(cb.getBlock().getLocation()).sendPluginMessage(plugin, "BungeeCord", baos.toByteArray());
                baos.close();
                dos.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        } else {
            System.out.println("Console　引数指定エラー");
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String command, String[] args) {
        List<String> autoComplete = new ArrayList<>();
        if(sender.hasPermission("tele")){
            if (args.length == 1){//一段目
                autoComplete.addAll(getAllMaterials());
            }
        }
        //文字比較と削除-----------------------------------------------------
        //Collections.sort(autoComplete);
        autoComplete.removeIf(str -> !str.startsWith(args[args.length - 1]));
        //------------------------------------------------------
        return autoComplete;
    }

    private List<String> getAllMaterials() {
        List<String> list = new ArrayList<String>();
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