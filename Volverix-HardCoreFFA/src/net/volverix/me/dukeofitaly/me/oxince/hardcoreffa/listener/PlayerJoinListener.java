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
        EloPattern eloPattern = new EloPattern(player.getUniqueId(), hardCoreFFA.getDriverManager());

        String prefix = configPattern.getPrefix();
        Location spawn = configPattern.getLocation("spawn");

        if (!(statisticsPattern.statsExist("hardcoreffa"))) {
            hardCoreFFA.getDriverManager().update("INSERT INTO hardcoreffa (UUID, KILLS, DEATHS, POINTS, WINS, EXTRAS, ELO, ELO_RANK) VALUES ('" + player.getUniqueId() + "', '" + 0 + "', '" + 0 + "', '" + 0 + "', '" + 0 + "', '" + 0 + "', '" + 1000 + "', '" + "Iron IV" + "')");
        }

        ScoreBoardPattern scoreBoardPattern = new ScoreBoardPattern(player);

        sp.getKillsMap().put(player, statisticsPattern.getStatistics(StatisticsType.KILLS));
        sp.getDeathsMap().put(player, statisticsPattern.getStatistics(StatisticsType.DEATHS));

        eloPattern.updateEloRank("hardcoreffa");

        player.teleport(spawn);
        player.setGameMode(GameMode.SURVIVAL);
        itemPattern.setKit();
        event.setJoinMessage(prefix + "§7The player §e" + player.getName() + "§7 has joined the game!" + statisticsPattern.getStatistics(StatisticsType.KILLS) + sp.getKillsMap().get(player));
        scoreBoardPattern.setScoreBoard();
    }
}

