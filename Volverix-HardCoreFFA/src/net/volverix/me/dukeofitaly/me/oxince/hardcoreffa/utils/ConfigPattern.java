package net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils;

import lombok.Getter;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.HardCoreFFA;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigPattern {

    HardCoreFFA hardCoreFFA = HardCoreFFA.getHardCoreFFA();
    MapPattern mapPattern = hardCoreFFA.getMapPattern();

    @Getter
    private String prefix = getConfigString("Game.Prefix");

    public static File folder = new File("plugins/Tactic-BW/");
    public static File file = new File("plugins/Tactic-BW/data.yml");
    public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public void saveConfig() {
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setupFiles() {
        if (!folder.exists()) {
            folder.mkdir();
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        cfg.addDefault("Maps", new ArrayList<>());
        cfg.addDefault("Game.Prefix", "§7[§bHardCoreFFA§7] ");
        cfg.options().copyDefaults(true);
        saveConfig();
    }

    public String getConfigString(String path) {
        return cfg.getString(path).replace("&", "§");
    }

    public static Location getLocation(String mapName, String name) {
        String mainPath = "Locations." + mapName + "." + name;
        Location loc = new Location(Bukkit.getWorld(cfg.getString(mainPath + ".World")), cfg.getDouble(mainPath + ".X"),
                cfg.getDouble(mainPath + ".Y"), cfg.getDouble(mainPath + ".Z"));
        loc.setYaw((float) cfg.getDouble(mainPath + ".Yaw"));
        loc.setPitch((float) cfg.getDouble(mainPath + ".Pitch"));
        return loc;
    }

    public void setLocation(String mapName, String name, Location loc) {
        cfg.set("Locations." + mapName + "." + name + ".X", loc.getBlockX() + 0.5);
        cfg.set("Locations." + mapName + "." + name + ".Y", loc.getBlockY());
        cfg.set("Locations." + mapName + "." + name + ".Z", loc.getBlockZ() + 0.5);
        cfg.set("Locations." + mapName + "." + name + ".Yaw", Math.round(loc.getYaw() / 45) * 45);
        cfg.set("Locations." + mapName + "." + name + ".Pitch", Math.round(loc.getPitch() / 45) * 45);
        cfg.set("Locations." + mapName + "." + name + ".World", loc.getWorld().getName());
        saveConfig();
    }

    public void createMap(String mapName) {
        mapPattern.getMaps().add(mapName);
        cfg.set("Maps", mapPattern.getMaps());
    }

    public String getWorld(String name) {
        String mainPath = "Locations." + name;
        String world = cfg.getString(mainPath + ".World");
        return world;
    }

    public List<String> getMaps() {
        List<String> maps = cfg.getStringList("Maps");
        return maps;
    }

}
