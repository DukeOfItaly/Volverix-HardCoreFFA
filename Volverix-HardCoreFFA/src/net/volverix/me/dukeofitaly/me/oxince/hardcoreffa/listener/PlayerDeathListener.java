package net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.listener;

import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.HardCoreFFA;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.ConfigPattern;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.HardCoreFFAStatsPattern;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.MapPattern;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

        HardCoreFFA hardCoreFFA = new HardCoreFFA();
        ConfigPattern configPattern = hardCoreFFA.getConfigPattern();
        MapPattern mapPattern = hardCoreFFA.getMapPattern();

        Player died = event.getEntity().getPlayer();
        Player killer = event.getEntity().getKiller();

        HardCoreFFAStatsPattern statsDied = new HardCoreFFAStatsPattern(died);
        HardCoreFFAStatsPattern statsKiller = new HardCoreFFAStatsPattern(killer);


        String prefix = configPattern.getConfigString("Game.prefix");
        Location spawn = ConfigPattern.getLocation(mapPattern.getCurrentMap(), "spawn");

        died.sendMessage(prefix + "§7You have been killed by §c" + killer);
        statsDied.addDeaths(died);
        died.teleport(spawn);
        if (statsDied.getKillStreak().containsKey(died)) {
            statsDied.getKillStreak().remove(died);
        }


        statsKiller.addKill(killer);
        killer.sendMessage(prefix + "§7You have killed the player §a" + died);
        killer.playSound(killer.getLocation(), Sound.LEVEL_UP, 1000, 1000);
        if (statsKiller.getKillStreak().containsKey(killer)) {
            statsKiller.addKillStreakKill(killer);
        } else {
            statsKiller.startKillStreak(killer);
        }


        event.setKeepInventory(true);

    }


}
