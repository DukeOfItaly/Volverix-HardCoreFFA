package net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.listener;

import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.HardCoreFFA;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.ConfigPattern;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.HardCoreFFAStatsPattern;
import net.volverix.me.oxince.volverixcore.util.StatisticsType;
import net.volverix.me.oxince.volverixcore.util.StatsPattern;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerDisconnectListener implements Listener {

    HardCoreFFA hardCoreFFA = HardCoreFFA.getHardCoreFFA();

    @EventHandler
    public void onDisconnect(PlayerQuitEvent event) {

        Player player = event.getPlayer();

        //UPDATE STATISTICS
        HardCoreFFAStatsPattern sp = new HardCoreFFAStatsPattern(player);
        StatsPattern statsPattern = new StatsPattern(hardCoreFFA.getDriverManager(), "HardCoreFFA", player.getUniqueId());
        ConfigPattern configPattern = hardCoreFFA.getConfigPattern();

        statsPattern.increaseStatistics(StatisticsType.KILLS);
        statsPattern.increaseStatistics(StatisticsType.DEATHS);

        sp.getDeathsMap().remove(player);
        sp.getKillsMap().remove(player);


        //LEAVE MESSAGE
        String prefix = configPattern.getPrefix();
        event.setQuitMessage(prefix + "§7The player §e" + player + "§7 has left the game!");

    }

}
