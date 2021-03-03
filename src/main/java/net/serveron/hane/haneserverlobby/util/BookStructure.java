package net.serveron.hane.haneserverlobby.util;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.List;

public class BookStructure {
    private final String title;
    private final String author;
    private final List<String> pages;

    public BookStructure(String title,String author,List<String> pages){
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

    public String getBookTitle(){
        return title;
    }
    public String getBookAuthor(){ return author; }
    public List<String> getBookPages(){
        return pages;
    }

    public ItemStack getItemStack(){
        ItemStack writtenBook = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) writtenBook.getItemMeta();
        bookMeta.setTitle(title);
        bookMeta.setAuthor(author);
        for(String page : pages) {
            bookMeta.addPages(Component.text(page));
        }
        writtenBook.setItemMeta(bookMeta);
        return writtenBook;
    }
}
