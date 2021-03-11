package net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.listener;

import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.HardCoreFFA;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.game.KitTypes;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.ConfigPattern;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.ItemPattern;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.ScoreBoardPattern;
import net.volverix.me.oxince.volverixcore.VolverixPlayer;
import net.volverix.me.oxince.volverixcore.util.ClanPattern;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    HardCoreFFA hardCoreFFA = new HardCoreFFA();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        VolverixPlayer volverixPlayer = new VolverixPlayer(hardCoreFFA.getDriverManager());
        ItemPattern itemPattern = new ItemPattern(player);
        ConfigPattern configPattern = new ConfigPattern();
        ClanPattern clanPattern = volverixPlayer.getClanPattern(player);
        ScoreBoardPattern scoreBoardPattern = new ScoreBoardPattern(player);
        String clanName = clanPattern.getClanName();
        Location spawn = ConfigPattern.getLocation("spawn");


        event.setJoinMessage(configPattern.getConfigString("Game.Prefix") + "§7The player §e" + player.getName() + "§7 has joined the game!");
        player.teleport(spawn);

        if (!(ClanPattern.clanMembers.containsKey(player.getUniqueId()))) {
            clanPattern.addClanPlayerInHashMap(clanName);
        }

        itemPattern.setKit(KitTypes.getCurrentKit());


        scoreBoardPattern.setScoreBoard();

    }
}

