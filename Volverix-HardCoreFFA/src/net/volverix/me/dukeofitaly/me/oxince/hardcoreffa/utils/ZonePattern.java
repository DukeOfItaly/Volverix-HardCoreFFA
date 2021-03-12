package net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils;

import lombok.Getter;
import lombok.Setter;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.HardCoreFFA;
import org.bukkit.Location;

import java.util.ArrayList;


public class ZonePattern {

    public ArrayList<Location> locations = new ArrayList<>();
    HardCoreFFA hardCoreFFA = HardCoreFFA.getHardCoreFFA();
    ConfigPattern configPattern = hardCoreFFA.getConfigPattern();


    @Getter
    @Setter
    private Location l1;

    @Getter
    @Setter
    private Location l2;

    public void setPositionL1InConfig(Location l1) {
        configPattern.setLocation("l1", l1);
    }

    public void setPositionL2InConfig(Location l2) {
        configPattern.setLocation("l2", l2);
    }

    public boolean isInSpawn(Location loc, Location l1, Location l2) {
        double maxX = (l1.getX() > l2.getX() ? l1.getX() : l2.getX());
        double minX = (l1.getX() < l2.getX() ? l1.getX() : l2.getX());

        double maxY = (l1.getY() > l2.getY() ? l1.getY() : l2.getY());
        double minY = (l1.getY() < l2.getY() ? l1.getY() : l2.getY());

        double maxZ = (l1.getZ() > l2.getZ() ? l1.getZ() : l2.getZ());
        double minZ = (l1.getZ() < l2.getZ() ? l1.getZ() : l2.getZ());

        if (loc.getX() <= maxX && loc.getX() >= minX) {
            if (loc.getY() <= maxY && loc.getY() >= minY) {
                if (loc.getZ() <= maxZ && loc.getZ() >= minZ) {
                    return true;
                }
            }

        }

        return false;
    }

}
