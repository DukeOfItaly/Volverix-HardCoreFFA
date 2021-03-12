package net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils;

import org.bukkit.Location;

import java.util.ArrayList;

public class ZonePattern {

    private String mapName;


    public ZonePattern(String mapName, Location l1, Location l2) {
        this.mapName = mapName;
    }

    public ArrayList<Location> generateCube(Location l1, Location l2) {
        ArrayList<Location> temp = new ArrayList<>();

        int minX, maxX, minY, maxY, minZ, maxZ;

        if (l1.getX() < l2.getX()) {
            minX = l1.getBlockX();
            maxX = l2.getBlockX();
        } else {
            minX = l2.getBlockX();
            maxX = l1.getBlockX();
        }


        if (l1.getY() < l2.getY()) {
            minY = l1.getBlockY();
            maxY = l2.getBlockY();
        } else {
            minY = l2.getBlockY();
            maxY = l1.getBlockY();
        }


        if (l1.getZ() < l2.getZ()) {
            minZ = l1.getBlockZ();
            maxZ = l2.getBlockZ();
        } else {
            minZ = l2.getBlockZ();
            maxZ = l1.getBlockZ();
        }

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    Location loc = new Location(l1.getWorld(), x, y, z);
                    temp.add(loc);
                }
            }
        }
        return null;
    }

}
