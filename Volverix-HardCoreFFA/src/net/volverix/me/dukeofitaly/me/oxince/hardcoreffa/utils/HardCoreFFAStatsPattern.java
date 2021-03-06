package net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class HardCoreFFAStatsPattern {


    @Getter
    private HashMap<Player, Integer> deathsMap = new HashMap<>();

    @Getter
    private HashMap<Player, Integer> killsMap = new HashMap<>();

    @Getter
    private HashMap<Player, Integer> killStreak = new HashMap<>();

    private Player player;

    public HardCoreFFAStatsPattern(Player player) {
        this.player = player;
    }


    public void addKill(Player player) {
        Integer currentKills = getKillsMap().get(player);
        Integer newKills = currentKills + 1;
        killsMap.put(player, newKills);
    }

    public void addDeaths(Player player) {
        Integer currentDeaths = getDeathsMap().get(player);
        Integer newDeaths = currentDeaths + 1;
        deathsMap.put(player, newDeaths);
    }

    public Integer getKillStreak(Player player) {
        Integer killstreak = killStreak.get(player);
        return killstreak;
    }

    public void setKillStreak(Player player, Integer value) {
        killStreak.put(player, value);
    }

    public void resetKillStreak(Player player) {
        killStreak.remove(player);
    }

    public void startKillStreak(Player player) {
        killStreak.put(player, 1);
    }

    public void addKillStreakKill(Player player) {
        Integer currentKilLStreak = getKillStreak(player);
        Integer newKillStreak = currentKilLStreak + 1;
        setKillStreak(player, newKillStreak);
    }


}
