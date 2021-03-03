package net.serveron.hane.haneserverlobby.Command;

import net.kyori.adventure.text.Component;
import net.serveron.hane.haneserverlobby.HaneServerLobby;
import net.serveron.hane.haneserverlobby.util.PlayerSearch;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class BanCommand implements CommandExecutor {
    private final HaneServerLobby plugin;

    public BanCommand(HaneServerLobby plugin) {
        this.plugin = plugin;
        plugin.getCommand("protector").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof ConsoleCommandSender){
            if (args.length == 2) {
                Player player = PlayerSearch.getPlayerFromName(args[0]);
                if(player!=null){
                    player.banPlayer("ハッククライアントを使用したためBANされました。");
                    plugin.runAsyncDiscord(player.getName()+args[1]);
                } else {
                    System.out.println("[HaneServerLobby] プレイヤーが見つかりませんでした。");
                }
            } else {
                System.out.println("Console　引数指定エラー");
            }
        }
        return true;
    }
}
