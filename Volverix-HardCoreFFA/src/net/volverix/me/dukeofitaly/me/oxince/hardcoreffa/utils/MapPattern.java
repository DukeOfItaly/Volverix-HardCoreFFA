package net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils;

import lombok.Getter;
import lombok.Setter;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.HardCoreFFA;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapPattern {


    HardCoreFFA hardCoreFFA = HardCoreFFA.getHardCoreFFA();
    ConfigPattern configPattern = hardCoreFFA.getConfigPattern();

    String prefix = configPattern.getConfigString("Game.Prefix");

    @Getter
    public HashMap<String, Integer> mapVotes = new HashMap<>();
    @Getter
    public List<String> maps = new ArrayList<>();
    public ArrayList<Player> hasVoted = new ArrayList<>();
    @Getter
    private String currentMap;
    @Getter
    @Setter
    private String nextMap;

    public void setCurrentMap(String mapName) {
        currentMap = mapName;
    }

    public void changeCurrentMap() {
    }

    public Integer getCurrentVotesForMap(String mapName) {
        Integer votes = mapVotes.get(mapName);
        return votes;
    }

    public void voteMap(String mapName, Player voter) {
        if (hasVoted.contains(voter)) {
            voter.sendMessage(prefix + "§cYou have already voted!");
        } else {
            Integer newVotes = getCurrentVotesForMap(mapName) + 1;
            mapVotes.put(mapName, newVotes);
            hasVoted.add(voter);
            voter.sendMessage(prefix + "§aThank you for your map vote!");
        }
    }


}
