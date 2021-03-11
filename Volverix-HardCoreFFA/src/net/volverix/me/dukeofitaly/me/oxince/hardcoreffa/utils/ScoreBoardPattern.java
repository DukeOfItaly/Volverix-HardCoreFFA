package net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils;


import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.HardCoreFFA;
import net.volverix.me.oxince.volverixcore.VolverixPlayer;
import net.volverix.me.oxince.volverixcore.util.StatisticsType;
import net.volverix.me.oxince.volverixcore.util.StatsPattern;
import org.bukkit.entity.Player;

public class ScoreBoardPattern {

    HardCoreFFA hardCoreFFA = new HardCoreFFA();
    VolverixPlayer volverixPlayer = new VolverixPlayer(hardCoreFFA.getDriverManager());

    private StatsPattern statsPattern;
    Integer currentKills = statsPattern.getStatistics(StatisticsType.KILLS);
    Integer currentDeaths = statsPattern.getStatistics(StatisticsType.DEATHS);

    public ScoreBoardPattern(Player player) {
        this.statsPattern = new StatsPattern(hardCoreFFA.getDriverManager(), "HardCoreFFA", player.getUniqueId());
    }

    public void setScoreBoard() {

    }

}
