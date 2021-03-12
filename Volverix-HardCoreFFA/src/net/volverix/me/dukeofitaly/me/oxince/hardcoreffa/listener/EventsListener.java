package net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.listener;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class EventsListener implements Listener {

    @EventHandler
    public void onChangeWeather(WeatherChangeEvent event) {
        event.setCancelled(true);
    }

    public void onGrassToSoil(BlockPhysicsEvent event) {

    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onCraft(CraftItemEvent event) {
        event.setCancelled(true);
    }

}
