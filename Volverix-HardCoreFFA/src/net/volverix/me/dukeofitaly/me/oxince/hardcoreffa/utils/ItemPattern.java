package net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils;

import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.game.KitTypes;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class ItemPattern {

    private Player player;

    public ItemPattern(Player player) {
        this.player = player;
    }

    public void setItem(Material itemStack, Integer slot, String displayName, List lore) {
        Inventory inv = player.getInventory();
        ItemStack is = new ItemStack(itemStack);
        ItemMeta m = is.getItemMeta();
        m.setDisplayName(displayName);
        m.setLore(lore);
        is.setItemMeta(m);
        inv.setItem(slot, is);
    }

    public void setKit(KitTypes kitTypes) {
        player.getInventory().clear();
        switch (kitTypes) {
            case TEST:
                setItem(Material.DIAMOND_SWORD, 0, "§dTEST-SWORD", Arrays.asList("§7Test Lore"));
        }
    }

    public void setJoinItems() {
        player.getInventory().clear();
        setItem(Material.PAPER, 0, "§dMap-Vote", Arrays.asList(""));
        setItem(Material.IRON_CHESTPLATE, 4, "§dKit-Vote", Arrays.asList(""));
        setItem(Material.SLIME_BALL, 8, "§dLeave", Arrays.asList(""));
    }


}
