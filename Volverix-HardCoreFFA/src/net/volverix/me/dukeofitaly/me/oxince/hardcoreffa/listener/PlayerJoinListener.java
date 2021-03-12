package net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.listener;

import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.HardCoreFFA;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.ConfigPattern;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.ItemPattern;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.ScoreBoardPattern;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.ZonePattern;
import net.volverix.me.oxince.me.dukeofitaly.core.CorePlayer;
import net.volverix.me.oxince.me.dukeofitaly.core.util.ClanPattern;
import net.volverix.me.oxince.me.dukeofitaly.core.util.StatisticsPattern;
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

        CorePlayer volverixPlayer = new CorePlayer(player.getUniqueId());
        ItemPattern itemPattern = new ItemPattern(player);
        ConfigPattern configPattern = hardCoreFFA.getConfigPattern();

        StatisticsPattern statisticsPattern = new StatisticsPattern(hardCoreFFA.getDriverManager(), "hardcoreffa", player.getUniqueId());
        ScoreBoardPattern scoreBoardPattern = new ScoreBoardPattern(player);

        if (!(statisticsPattern.statsExist("HardCoreFFA"))) {
            hardCoreFFA.getDriverManager().update("INSERT INTO hardcoreffa (UUID, KILLS, DEATHS, POINTS, WINS, EXTRAS) VALUES ('" + player.getUniqueId() + "', '" + 0 + "', '" + 0 + "', '" + 0 + "', '" + 0 + "', '" + 0 + "')");
        }

        String prefix = configPattern.getPrefix();
        Location spawn = configPattern.getLocation("spawn");

        event.setJoinMessage(prefix + "§7The player §e" + player.getName() + "§7 has joined the game!");
        player.teleport(spawn);

        if (!(ClanPattern.clanMembers.containsKey(player.getUniqueId()))) {

        }

        player.setGameMode(GameMode.SURVIVAL);
        player.getInventory().clear();
        itemPattern.setJoinItems();

        scoreBoardPattern.setScoreBoard();

    }
}

