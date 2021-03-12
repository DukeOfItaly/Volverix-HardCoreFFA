package net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.listener;

import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.HardCoreFFA;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.commands.BuildCommand;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.game.KitTypes;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.ItemPattern;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.ZonePattern;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;

public class PlayerMoveListener implements Listener {

    public static ArrayList<Player> inSpawn = new ArrayList<>();

    HardCoreFFA hardCoreFFA = HardCoreFFA.getHardCoreFFA();
    ZonePattern zonePattern = hardCoreFFA.getZonePattern();

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (inSpawn.contains(player)) {
            if (zonePattern.isInSpawn(player.getLocation(), zonePattern.getL1(), zonePattern.getL2())) {
            } else {
                inSpawn.remove(player);
                ItemPattern itemPattern = new ItemPattern(player);
                if (BuildCommand.inBuild.contains(player)) {
                } else
                    itemPattern.setKit(KitTypes.getCurrentKit());
            }
        } else {
            if (!(inSpawn.contains(player))) {
                if (zonePattern.isInSpawn(player.getLocation(), zonePattern.getL1(), zonePattern.getL2())) {
                    inSpawn.add(player);
                    ItemPattern itemPattern = new ItemPattern(player);
                    itemPattern.setJoinItems();
                }
            }
        }
    }

}
