package net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.listener;

import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.ConfigPattern;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.HardCoreFFAStatsPattern;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

        ConfigPattern configPattern = new ConfigPattern();

        Player died = event.getEntity().getPlayer();
        Player killer = event.getEntity().getKiller();

        HardCoreFFAStatsPattern statsDied = new HardCoreFFAStatsPattern(died);
        HardCoreFFAStatsPattern statsKiller = new HardCoreFFAStatsPattern(killer);

        String prefix = configPattern.getConfigString("Game.prefix");
        Location spawn = ConfigPattern.getLocation("spawn");

        died.sendMessage(prefix + "§7You have been killed by §c" + killer);
        statsDied.addDeaths(died);
        died.teleport(spawn);


        statsKiller.addKill(killer);
        killer.sendMessage(prefix + "§7You have killed the player §a" + died);
        killer.playSound(killer.getLocation(), Sound.LEVEL_UP, 1000, 1000);


    }


}
