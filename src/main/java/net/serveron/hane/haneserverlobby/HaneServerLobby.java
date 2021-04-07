package net.serveron.hane.haneserverlobby;

import net.serveron.hane.haneserverlobby.Command.*;
import net.serveron.hane.haneserverlobby.config.HaneServerLobbyConfig;
import net.serveron.hane.haneserverlobby.listener.FishEvent;
import net.serveron.hane.haneserverlobby.listener.JoinQuitEvent;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class HaneServerLobby extends JavaPlugin {
    private HaneServerLobbyConfig hsConfig;
    public ExecutorService threadPool;

    @Override
    public void onEnable() {
        // Plugin startup logic
        try{
            hsConfig = new HaneServerLobbyConfig(this);
        } catch (Exception e){
            System.out.println("[HaneServerLobby] Config Error");
            e.printStackTrace();
            setEnabled(false);
        }

        if(!hsConfig.isConfirm()){
            System.out.println("[HaneServerLobby] ここはLobbyサーバーですか？確認が必要です。");
            setEnabled(false);
        }

        if(isEnabled()){
            threadPool = Executors.newFixedThreadPool(3);
            new JoinQuitEvent(this);
            new FishEvent(this);
            new RuleBookCommand(this);
        }

    }

    @Override
    public void onDisable() {
        if(threadPool!=null){
            threadPool.shutdown();
        }
        HandlerList.unregisterAll(this);
    }

    public HaneServerLobbyConfig getHsConfig() {
        return hsConfig;
    }
}
