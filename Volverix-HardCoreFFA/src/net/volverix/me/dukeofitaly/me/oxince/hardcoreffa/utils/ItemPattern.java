package net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils;

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

    public void setKit() {
        player.getInventory().clear();
        setItem(Material.DIAMOND_SWORD, 0, "§dTEST-SWORD", Arrays.asList(""));
        setItem(Material.GOLDEN_APPLE, 1, "§6Apple", Arrays.asList(""));

        ItemStack[] armor = new ItemStack[4];
        armor[0] = new ItemStack(Material.DIAMOND_BOOTS, 1);
        armor[1] = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
        armor[2] = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
        armor[3] = new ItemStack(Material.DIAMOND_HELMET, 1);

        player.getInventory().setArmorContents(armor);


    }


}
