package net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class HardCoreFFAStatsPattern {


    @Getter
    private HashMap<Player, Integer> deathsMap = new HashMap<>();

    @Getter
    private HashMap<Player, Integer> killsMap = new HashMap<>();

    private Player player;

    public HardCoreFFAStatsPattern(Player player) {
        this.player = player;
    }

    public Integer getDeaths() {
        Integer d = deathsMap.get(player);
        return d;
    }

    public Integer getKills() {
        Integer k = killsMap.get(player);
        return k;
    }

    public void addKill(Player player) {
        Integer currentKills = getKills();
        Integer newKills = currentKills + 1;
        killsMap.put(player, newKills);
    }

    public void addDeaths(Player player) {
        Integer currentDeaths = getDeaths();
        Integer newDeaths = currentDeaths + 1;
        deathsMap.put(player, newDeaths);
    }

}
