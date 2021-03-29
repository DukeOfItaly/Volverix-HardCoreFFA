package net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils;


import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.HardCoreFFA;
import net.volverix.me.oxince.me.dukeofitaly.core.CorePlayer;
import net.volverix.me.oxince.me.dukeofitaly.core.util.EloPattern;
import net.volverix.me.oxince.me.dukeofitaly.core.util.StatisticsPattern;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreBoardPattern {

    HardCoreFFA hardCoreFFA = HardCoreFFA.getHardCoreFFA();


    private Player player;




    public ScoreBoardPattern(Player player) {
        this.player = player;
    }

    public void setScoreBoard() {

        CorePlayer corePlayer = new CorePlayer(player.getUniqueId());
        HardCoreFFAStatsPattern sp = new HardCoreFFAStatsPattern(player);
        StatisticsPattern statsPattern = new StatisticsPattern(hardCoreFFA.getDriverManager(), "hardcoreffa", player.getUniqueId());
        ConfigPattern configPattern = hardCoreFFA.getConfigPattern();
        EloPattern eloPattern = new EloPattern(player.getUniqueId(), hardCoreFFA.getDriverManager());

        Integer currentKillStreak = 0;

        if (sp.getKillStreak().containsKey(player)) {
            currentKillStreak = sp.getKillStreak(player);
        }


        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("aaa", "bbb");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§bHardCoreFFA");


        objective.getScore("§1").setScore(13);
        objective.getScore("§7Kills§8:").setScore(12);
        objective.getScore("§8» §4§6" + sp.getKillsMap().get(player)).setScore(11);
        objective.getScore("§2").setScore(10);
        objective.getScore("§7Deaths§8:").setScore(9);
        objective.getScore("§8» §5§6" + sp.getDeathsMap().get(player)).setScore(8);
        objective.getScore("§3").setScore(7);
        objective.getScore("§7Killstreak§8:").setScore(6);
        objective.getScore("§8» §6" + currentKillStreak).setScore(5);
        objective.getScore("§4").setScore(4);
        objective.getScore("§7Map§8:").setScore(3);
        objective.getScore("§8» §6" + configPattern.getConfigString("MapName")).setScore(2);
        objective.getScore("§8").setScore(1);
        objective.getScore("§b                       §b").setScore(0);

        player.setScoreboard(scoreboard);
    }

}
