package net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.listener;

import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.HardCoreFFA;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.ConfigPattern;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.HardCoreFFAStatsPattern;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.ScoreBoardPattern;
import net.volverix.me.oxince.me.dukeofitaly.core.util.EloPattern;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

        Player died = event.getEntity().getPlayer();
        Player killer = event.getEntity().getKiller();

        HardCoreFFA hardCoreFFA = HardCoreFFA.getHardCoreFFA();
        ConfigPattern configPattern = hardCoreFFA.getConfigPattern();

        HardCoreFFAStatsPattern statsDied = new HardCoreFFAStatsPattern(died);
        HardCoreFFAStatsPattern statsKiller = new HardCoreFFAStatsPattern(killer);
        ScoreBoardPattern scoreBoardPatternDied = new ScoreBoardPattern(died);
        ScoreBoardPattern scoreBoardPatternKiller = new ScoreBoardPattern(killer);

        String prefix = configPattern.getPrefix();

        /* SETTING ELO FOR ALL INVOLVED PLAYERS*/
        EloPattern eloPatternDied = new EloPattern(died.getUniqueId(), hardCoreFFA.getDriverManager());
        EloPattern eloPatternKiller = new EloPattern(killer.getUniqueId(), hardCoreFFA.getDriverManager());

        Integer newEloDead = eloPatternDied.calculateNewEloPlayerDead(eloPatternDied.getPlayerElo("hardcoreffa"), eloPatternKiller.getPlayerElo("hardcoreffa"));
        Integer newEloKiller = eloPatternDied.calculateNewEloPlayerKiller(eloPatternDied.getPlayerElo("hardcoreffa"), eloPatternKiller.getPlayerElo("hardcoreffa"));
        Integer eloDifferenceDead = eloPatternDied.getPlayerElo("hardcoreffa") - newEloDead;
        Integer eloDifferenceKiller = eloPatternKiller.getPlayerElo("hardcoreffa") - newEloKiller;


        eloPatternDied.setPlayerElo(newEloDead, "hardcoreffa");
        eloPatternKiller.setPlayerElo(newEloKiller, "hardcoreffa");

        died.sendMessage(prefix + "§7You have been killed by §e" + killer.getName() + "§7 [-§c" + eloDifferenceDead + "§7]");
        statsDied.addDeaths(died);
        statsDied.getKillStreak().remove(died);


        statsKiller.addKill(killer);
        killer.sendMessage(prefix + "§7You have killed the player §e" + died.getName() + "§7 [+§a" + eloDifferenceKiller + "§7]");
        killer.playSound(killer.getLocation(), Sound.LEVEL_UP, 1000, 1000);

        if (statsKiller.getKillStreak().containsKey(killer)) {
            statsKiller.addKillStreakKill(killer);
        } else {
            statsKiller.startKillStreak(killer);
        }

        event.setDeathMessage("");
        event.getDrops().clear();

    }


}
