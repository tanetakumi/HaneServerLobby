package net.serveron.hane.haneserverlobby.Command;

import net.serveron.hane.haneserverlobby.HaneServerLobby;
import net.serveron.hane.haneserverlobby.Config.BookStructure;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RuleBookCommand implements CommandExecutor, TabCompleter {
    public HaneServerLobby plugin;

    public RuleBookCommand(HaneServerLobby plugin) {
        this.plugin = plugin;
        plugin.getCommand("rulebook").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("You cannot use commands with the console.");
            return true;
        }
        Player player = (Player) sender;
        if(args.length > 0){
            if(player.hasPermission("rulebook")){
                if(args[0].equalsIgnoreCase("help")){
                    player.sendMessage(ChatColor.AQUA+"右手に追加したい本を持って /rulebook update で本の更新ができるよ");
                } else if(args[0].equalsIgnoreCase("update")){
                    ItemStack itemStack = player.getInventory().getItemInMainHand();
                    if(itemStack.getType() == Material.WRITTEN_BOOK){
                        BookMeta bookMeta = (BookMeta) itemStack.getItemMeta();
                        BookStructure book = new BookStructure(bookMeta.getTitle(), bookMeta.getAuthor(), bookMeta.getPages());
                        plugin.getHsConfig().setRulebook(book);
                        player.sendMessage("保存しました。");
                    } else {
                        player.sendMessage(ChatColor.AQUA+"右手に追加したい本を持って /rulebook update で本の更新をしてください。");
                    }
                } else {
                    player.sendMessage(ChatColor.AQUA+"右手に追加したい本を持って /rulebook update で本の更新ができるよ");
                }
            } else{
                player.sendMessage("権限がありません。");
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String command, String[] args) {
        List<String> autoComplete = new ArrayList<>();
        if(sender.hasPermission("rulebook")){
            if (args.length == 1){//一段目
                autoComplete.addAll(Arrays.asList("update","help"));
            }
        }
        //文字比較と削除-----------------------------------------------------
        //Collections.sort(autoComplete);
        autoComplete.removeIf(str -> !str.startsWith(args[args.length - 1]));
        //------------------------------------------------------
        return autoComplete;
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