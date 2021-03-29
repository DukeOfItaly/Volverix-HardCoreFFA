package net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.listener;


import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.HardCoreFFA;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.ConfigPattern;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.ZonePattern;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.net.http.WebSocket;

public class PlayerDamageListener implements Listener {

    HardCoreFFA hardCoreFFA = HardCoreFFA.getHardCoreFFA();
    ZonePattern zonePattern = hardCoreFFA.getZonePattern();
    ConfigPattern configPattern = hardCoreFFA.getConfigPattern();

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            Player target = (Player) event.getEntity();
            Location loc = target.getLocation();
            Location l1 = configPattern.getLocation("l1");
            Location l2 = configPattern.getLocation("l2");
            if (zonePattern.isInSpawn(loc, l1, l2)) {
                event.setCancelled(true);
            } else {
                event.setCancelled(false);
            }
        }
    }
}


