package net.serveron.hane.haneserverlobby;

import net.serveron.hane.haneserverlobby.Command.*;
import net.serveron.hane.haneserverlobby.Config.HaneServerLobbyConfig;
import net.serveron.hane.haneserverlobby.Listener.FishEvent;
import net.serveron.hane.haneserverlobby.Listener.JoinQuitEvent;
import net.serveron.hane.haneserverlobby.Listener.JumpEvent;
import net.serveron.hane.haneserverlobby.Listener.SitEvent;
import net.serveron.hane.haneserverlobby.util.AthleticData;
import net.serveron.hane.haneserverlobby.util.DiscordWebhook;
import net.serveron.hane.haneserverlobby.util.PlayerData;
import net.serveron.hane.haneserverlobby.util.SQLite;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class HaneServerLobby extends JavaPlugin {
    private SQLite sqLite;
    private HaneServerLobbyConfig hsConfig;
    private PlayerGame playerGame;
    public ExecutorService threadPool;

    @Override
    public void onEnable() {
        // Plugin startup logic

        hsConfig = new HaneServerLobbyConfig(this);
        sqLite = new SQLite(this,"haneserver");
        if(!sqLite.openConnection()){
            System.out.println("コネクションエラー！！");
            setEnabled(false);
        }

        threadPool = Executors.newFixedThreadPool(3);
        playerGame = new PlayerGame(this);

        new JoinQuitEvent(this);
        new JumpEvent(this);
        new FishEvent(this);
        new SitEvent(this);

        new GameCommand(this);
        new RuleBookCommand(this);
        new DiscordCommand(this);
        new BanCommand(this);
        new ExchangeCommand(this);
        new TeleportCommand(this);
        new HeadCommand(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        threadPool.shutdown();
        sqLite.closeDisconnect();
        HandlerList.unregisterAll(this);
    }

    public SQLite getSqLite() {
        return sqLite;
    }

    public HaneServerLobbyConfig getHsConfig() {
        return hsConfig;
    }

    public PlayerGame getPlayerGame() {
        return playerGame;
    }

    /**
     *
     * @param playerData
     */
    public void runAsyncSetPlayerData(PlayerData playerData) {
        Runnable task = () -> sqLite.setPlayerData(playerData);
        threadPool.submit(task);
    }

    public void runAsyncDiscord(String text) {
        if(hsConfig.getDiscordWebhook()!=null){
            threadPool.submit(new DiscordWebhook(text,hsConfig.getDiscordWebhook()));
        } else {
            System.out.println("DiscordWebhookがNULLでした。");
        }
    }

    public void runAsyncSetAthleticData(AthleticData athleticData) {
        Runnable task = () -> sqLite.setAthleticData(athleticData);
        threadPool.submit(task);
    }
}
