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


        died.sendMessage(prefix + "§7Du wurdest von §e" + killer.getName() + "§7 getötet!");
        statsDied.addDeaths(died);
        statsDied.getKillStreak().remove(died);


        statsKiller.addKill(killer);
        killer.sendMessage(prefix + "§7Du hast §e" + died.getName() + " §7getötet");
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
