package net.serveron.hane.haneserverlobby;

import net.serveron.hane.haneserverlobby.util.BookStructure;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HaneServerLobbyConfig {
    private final HaneServerLobby plugin;
    private FileConfiguration config;

    private String discordWebhook;
    //private final List<BookStructure> rulebooks = new ArrayList<>();
    private BookStructure rulebook;

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

        String title = config.getString("title");
        String author = config.getString("author");
        List<String> pages = config.getStringList("pages");
        rulebook = new BookStructure(title, author, pages);

    }

    public BookStructure getRulebook(){
        return rulebook;
    }

    public String getDiscordWebhook() { return discordWebhook; }

    public void setRulebook(BookStructure rulebook){
        this.rulebook = rulebook;
        config.set("title",rulebook.getBookTitle());
        config.set("author",rulebook.getBookAuthor());
        config.set("pages",rulebook.getBookPages());
        plugin.saveConfig();
    }
}
