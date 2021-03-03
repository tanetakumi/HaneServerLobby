package net.serveron.hane.haneserverlobby.Command;

import net.kyori.adventure.text.Component;
import net.serveron.hane.haneserverlobby.HaneServerLobby;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class DiscordCommand implements CommandExecutor {
    private final HaneServerLobby plugin;

    public DiscordCommand(HaneServerLobby plugin) {
        this.plugin = plugin;
        plugin.getCommand("discord").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;

            if (args.length == 1) {
                if (player.hasPermission("discord")) {
                    plugin.runAsyncDiscord(args[0]);
                } else {
                    player.sendMessage(Component.text("権限がないよ"));
                }
            } else {
                player.sendMessage(Component.text("コマンドの引数が違うよ"));
            }

        } else if(sender instanceof BlockCommandSender){
            if (args.length == 1) {
                plugin.runAsyncDiscord(args[0]);
            } else {
                System.out.println("Command Block　引数指定エラー");
            }
        } else if(sender instanceof ConsoleCommandSender){
            if (args.length == 1) {
                plugin.runAsyncDiscord(args[0]);
            } else {
                System.out.println("Console　引数指定エラー");
            }
        }
        return true;
    }

    private int stringToInt(String str){
        int x = 0;
        try{
            x = Integer.parseInt(str);
        }
        catch(Exception ignored){
        }
        return x;
    }
}