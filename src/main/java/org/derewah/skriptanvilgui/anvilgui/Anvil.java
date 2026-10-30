package org.derewah.skriptanvilgui.anvilgui;

import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.derewah.skriptanvilgui.SkriptAnvilGUI;
import org.derewah.skriptanvilgui.events.Anvil.BridgeAnvilClick;
import org.derewah.skriptanvilgui.events.Anvil.BridgeAnvilClose;

import java.util.Arrays;

public class Anvil {


    private final AnvilGUI.Builder builder;
    private String title;
    private String text;

    private ItemStack itemLeft;
    private ItemStack itemRight;

    private ItemStack itemOutput;


    public Anvil(){
        this.builder = new AnvilGUI.Builder();
        this.text = "Input Text";
        this.title = "Anvil Title";
        builder.text(this.text);
        builder.title(this.title);
        builder.plugin(SkriptAnvilGUI.getInstance());
        builder.onClick((slot, stateSnapshot) -> {
            return Arrays.asList(AnvilGUI.ResponseAction.run(() ->
                    Bukkit.getPluginManager().callEvent(new BridgeAnvilClick(slot, stateSnapshot, this))));
        });
        builder.onClose(stateSnapshot -> {
            BridgeAnvilClose event = new BridgeAnvilClose(stateSnapshot, this);
            Bukkit.getPluginManager().callEvent(event);
            // Decided after all the triggers ran: one reopen however many times it was cancelled
            if (event.isCancelled()) {
                openAnvil(stateSnapshot.getPlayer());
            }
        });
    }

    public void setText(String text){
        this.text = text;
        this.builder.text(text);
    }

    public String getText(){
        return this.text;
    }

    public void setTitle(String title){
        this.title = title;
        this.builder.title(title);
    }

    public String getTitle(){
        return this.title;
    }

    public void setItemLeft(ItemStack item){
        this.itemLeft = item;
        // AnvilGUI writes the text in the meta of the left item when opening, air has none
        this.builder.itemLeft(item.getItemMeta() == null ? new ItemStack(Material.PAPER) : item);
    }

    public ItemStack getItemLeft(){
        return this.itemLeft;
    }

    public void setItemRight(ItemStack item){
        this.itemRight = item;
        this.builder.itemRight(item);
    }

    public ItemStack getItemRight(){
        return this.itemRight;
    }

    public void setItemOutput(ItemStack item){
        this.itemOutput = item;
        this.builder.itemOutput(item);
    }

    public ItemStack getItemOutput(){
        return this.itemOutput;
    }

    public AnvilGUI.Builder getBuilder(){
        return this.builder;
    }

    public void openAnvil(Player player){
        Bukkit.getScheduler().runTaskLater(SkriptAnvilGUI.getInstance(), () -> open(player), 1);
    }

    /**
     * Opens the gui now. Nothing would ever close it for an offline player and its listener would stay registered.
     */
    public void open(Player player){
        if (player.isOnline()) {
            getBuilder().open(player);
        }
    }


    @Override
    public String toString() {
        return "anvil gui "
                + (getText() != null ? "with text " + getText() : "")
                + (getTitle() != null ? "with title"  + getTitle() : "");
    }
}
