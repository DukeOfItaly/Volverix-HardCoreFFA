package net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.listener;

import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.HardCoreFFA;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.ConfigPattern;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.ScoreBoardPattern;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawnListener implements Listener {

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {

        Player player = event.getPlayer();
        HardCoreFFA hardCoreFFA = HardCoreFFA.getHardCoreFFA();
        ConfigPattern configPattern = hardCoreFFA.getConfigPattern();
        Location spawn = configPattern.getLocation("spawn");

        ScoreBoardPattern scoreBoardPattern = new ScoreBoardPattern(player);

        player.teleport(spawn);
        scoreBoardPattern.setScoreBoard();
    }

}
