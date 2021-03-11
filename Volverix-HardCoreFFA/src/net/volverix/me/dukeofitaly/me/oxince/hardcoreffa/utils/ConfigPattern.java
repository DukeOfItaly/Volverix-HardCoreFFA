package net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ConfigPattern {

    public static File folder = new File("plugins/Tactic-BW/");
    public static File file = new File("plugins/Tactic-BW/data.yml");
    public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public static Location getLocation(String name) {
        String mainPath = "Locations." + name;
        Location loc = new Location(Bukkit.getWorld(cfg.getString(mainPath + ".World")), cfg.getDouble(mainPath + ".X"),
                cfg.getDouble(mainPath + ".Y"), cfg.getDouble(mainPath + ".Z"));
        loc.setYaw((float) cfg.getDouble(mainPath + ".Yaw"));
        loc.setPitch((float) cfg.getDouble(mainPath + ".Pitch"));
        return loc;
    }

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

    public void setLocation(String name, Location loc) {
        cfg.set("Locations." + name + ".X", loc.getBlockX() + 0.5);
        cfg.set("Locations." + name + ".Y", loc.getBlockY());
        cfg.set("Locations." + name + ".Z", loc.getBlockZ() + 0.5);
        cfg.set("Locations." + name + ".Yaw", Math.round(loc.getYaw() / 45) * 45);
        cfg.set("Locations." + name + ".Pitch", Math.round(loc.getPitch() / 45) * 45);
        cfg.set("Locations." + name + ".World", loc.getWorld().getName());
        saveConfig();
    }

    public String getWorld(String name) {
        String mainPath = "Locations." + name;
        String world = cfg.getString(mainPath + ".World");
        return world;
    }

}
