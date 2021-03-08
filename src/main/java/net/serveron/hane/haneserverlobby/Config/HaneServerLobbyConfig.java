package net.serveron.hane.haneserverlobby.Config;

import net.serveron.hane.haneserverlobby.HaneServerLobby;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class HaneServerLobbyConfig {

    private final HaneServerLobby plugin;
    private FileConfiguration config;

    private String discordWebhook;

    private BookStructure rulebook;

    private LocationStructure firstSpawn;

    private LocationStructure preSpawn;

    private List<LocationStructure> jumpLocations;

    public HaneServerLobbyConfig(HaneServerLobby plugin) {
        this.plugin = plugin;
        load();
    }

    @SuppressWarnings("unchecked")
    public void load() {
        // 設定ファイルを保存
        plugin.saveDefaultConfig();
        if (config != null) { // configが非null == リロードで呼び出された
            plugin.reloadConfig();
        }
        config = plugin.getConfig();

        discordWebhook = config.getString("discordwebhook");

        String title = config.getString("rulebook.title");
        String author = config.getString("rulebook.author");
        List<String> pages = config.getStringList("rulebook.pages");
        rulebook = new BookStructure(title, author, pages);

        firstSpawn = new LocationStructure(config.getDouble("firstspawn.x"),config.getDouble("firstspawn.y")
                ,config.getDouble("firstspawn.z"),config.getDouble("firstspawn.yaw"),config.getDouble("firstspawn.pitch"));

        preSpawn = new LocationStructure(config.getDouble("prespawn.x"),config.getDouble("prespawn.y")
                ,config.getDouble("prespawn.z"),config.getDouble("prespawn.yaw"),config.getDouble("prespawn.pitch"));



    }

    public BookStructure getRulebook(){
        return rulebook;
    }

    public String getDiscordWebhook() { return discordWebhook; }

    public LocationStructure getFirstSpawn() {
        return firstSpawn;
    }

    public LocationStructure getPreSpawn() {
        return preSpawn;
    }

    public void setRulebook(BookStructure rulebook){
        this.rulebook = rulebook;
        config.set("rulebook.title",rulebook.getBookTitle());
        config.set("rulebook.author",rulebook.getBookAuthor());
        config.set("rulebook.pages",rulebook.getBookPages());
        plugin.saveConfig();
    }
}
