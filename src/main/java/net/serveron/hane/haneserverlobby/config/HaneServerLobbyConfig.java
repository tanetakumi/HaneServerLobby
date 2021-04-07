package net.serveron.hane.haneserverlobby.config;

import net.serveron.hane.haneserverlobby.HaneServerLobby;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class HaneServerLobbyConfig {

    private final HaneServerLobby plugin;
    private FileConfiguration config;

    private boolean confirm;

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

        confirm = config.getBoolean("lobby");

        String title = config.getString("rulebook.title");
        String author = config.getString("rulebook.author");
        List<String> pages = config.getStringList("rulebook.pages");
        rulebook = new BookStructure(title, author, pages);

    }

    public boolean isConfirm() { return confirm; }

    public BookStructure getRulebook(){
        return rulebook;
    }

    public void setRulebook(BookStructure rulebook){
        this.rulebook = rulebook;
        config.set("rulebook.title",rulebook.getBookTitle());
        config.set("rulebook.author",rulebook.getBookAuthor());
        config.set("rulebook.pages",rulebook.getBookPages());
        plugin.saveConfig();
    }
}
