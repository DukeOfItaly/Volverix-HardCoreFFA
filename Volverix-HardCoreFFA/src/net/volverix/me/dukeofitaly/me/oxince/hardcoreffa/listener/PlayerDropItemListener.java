package net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.listener;

import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.commands.BuildCommand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PlayerDropItemListener implements Listener {

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        if (BuildCommand.inBuild.contains(event.getPlayer())) {
            event.setCancelled(false);
        } else {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemPickUp(PlayerPickupItemEvent event) {
        if (BuildCommand.inBuild.contains(event.getPlayer())) {
            event.setCancelled(false);
        } else {
            event.setCancelled(true);
        }
    }

}
