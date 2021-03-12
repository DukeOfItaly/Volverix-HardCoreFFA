package net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.listener;

import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.HardCoreFFA;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.commands.BuildCommand;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.ConfigPattern;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.HardCoreFFAStatsPattern;
import net.volverix.me.oxince.me.dukeofitaly.core.util.StatisticsPattern;
import net.volverix.me.oxince.me.dukeofitaly.core.util.StatisticsType;
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
        StatisticsPattern statsPattern = new StatisticsPattern(hardCoreFFA.getDriverManager(), "HardCoreFFA", player.getUniqueId());
        ConfigPattern configPattern = hardCoreFFA.getConfigPattern();


        statsPattern.increaseStatistics(StatisticsType.KILLS, sp.getKills());
        statsPattern.increaseStatistics(StatisticsType.DEATHS, sp.getDeaths());

        sp.getDeathsMap().remove(player);
        sp.getKillsMap().remove(player);

        if (BuildCommand.inBuild.contains(player)) {
            BuildCommand.inBuild.remove(player);
        }

        //LEAVE MESSAGE
        String prefix = configPattern.getPrefix();
        event.setQuitMessage(prefix + "§7The player §e" + player + "§7 has left the game!");

    }

}
