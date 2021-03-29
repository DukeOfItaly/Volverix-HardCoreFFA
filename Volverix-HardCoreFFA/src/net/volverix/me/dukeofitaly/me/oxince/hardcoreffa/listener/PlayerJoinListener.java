package net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.listener;

import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.HardCoreFFA;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.*;
import net.volverix.me.oxince.me.dukeofitaly.core.CorePlayer;
import net.volverix.me.oxince.me.dukeofitaly.core.util.EloPattern;
import net.volverix.me.oxince.me.dukeofitaly.core.util.StatisticsPattern;
import net.volverix.me.oxince.me.dukeofitaly.core.util.StatisticsType;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    HardCoreFFA hardCoreFFA = HardCoreFFA.getHardCoreFFA();
    ZonePattern zonePattern = hardCoreFFA.getZonePattern();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        HardCoreFFAStatsPattern sp = new HardCoreFFAStatsPattern(player);
        CorePlayer volverixPlayer = new CorePlayer(player.getUniqueId());
        ItemPattern itemPattern = new ItemPattern(player);
        ConfigPattern configPattern = hardCoreFFA.getConfigPattern();

        StatisticsPattern statisticsPattern = new StatisticsPattern(hardCoreFFA.getDriverManager(), "hardcoreffa", player.getUniqueId());

        String prefix = configPattern.getPrefix();
        Location spawn = configPattern.getLocation("spawn");

        statisticsPattern.createStatistics();

        ScoreBoardPattern scoreBoardPattern = new ScoreBoardPattern(player);

        sp.getKillsMap().put(player, statisticsPattern.getStatistics(StatisticsType.KILLS));
        sp.getDeathsMap().put(player, statisticsPattern.getStatistics(StatisticsType.DEATHS));

        player.teleport(spawn);
        player.setGameMode(GameMode.SURVIVAL);
        itemPattern.setKit();
        event.setJoinMessage(prefix + "§7Der Spieler §e" + player.getName() + "§7 hat das Spiel betreten!");
        scoreBoardPattern.setScoreBoard();
    }
}

