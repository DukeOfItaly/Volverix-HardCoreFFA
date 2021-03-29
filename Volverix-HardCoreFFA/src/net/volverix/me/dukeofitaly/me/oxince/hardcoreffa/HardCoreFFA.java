package net.volverix.me.dukeofitaly.me.oxince.hardcoreffa;


import lombok.Getter;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.commands.BuildCommand;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.commands.CreateMapCommand;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.commands.SetSpawnCommand;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.commands.SpawnZoneCommand;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.game.KitTypes;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.listener.*;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.ConfigPattern;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.ZonePattern;
import net.volverix.me.oxince.me.dukeofitaly.core.sql.DriverManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class HardCoreFFA extends JavaPlugin {


    private static HardCoreFFA hardCoreFFA;

    private ConfigPattern configPattern;
    private DriverManager driverManager;
    private ZonePattern zonePattern;

    public static HardCoreFFA getHardCoreFFA() {
        return hardCoreFFA;
    }

    public ConfigPattern getConfigPattern() {
        return configPattern;
    }

    public DriverManager getDriverManager() {
        return driverManager;
    }

    public ZonePattern getZonePattern() {
        return zonePattern;
    }

    @Override
    public void onEnable() {
        hardCoreFFA = this;
        connect();
        configPattern = new ConfigPattern();
        zonePattern = new ZonePattern();
        zonePattern.setL1(configPattern.getLocation("l1"));
        zonePattern.setL2(configPattern.getLocation("l2"));
        KitTypes.setCurrentKit(KitTypes.TEST);
        configPattern.saveConfig();
        configPattern.setupFiles();
        configPattern.setPrefix(configPattern.getConfigString("Game.Prefix"));
        registerCommands();
        registerListener();
    }

    public void onDisable() {
    }

    private void registerCommands() {
        this.getCommand("setspawn").setExecutor(new SetSpawnCommand());
        this.getCommand("build").setExecutor(new BuildCommand());
        this.getCommand("setpos").setExecutor(new SpawnZoneCommand());
        this.getCommand("createmap").setExecutor(new CreateMapCommand());
    }

    private void registerListener() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerDeathListener(), this);
        pm.registerEvents(new PlayerJoinListener(), this);
        pm.registerEvents(new PlayerDisconnectListener(), this);
        pm.registerEvents(new PlayerHungerListener(), this);
        pm.registerEvents(new PlayerDropItemListener(), this);
        pm.registerEvents(new PlayerDamageListener(), this);
        pm.registerEvents(new EventsListener(), this);
        pm.registerEvents(new PlayerRespawnListener(), this);
    }

    private void connect() {
        driverManager = new DriverManager("localhost", "root", "hardcoreffa", "");
        if (!driverManager.isConnected()) {
            try {
                System.out.println("[HardCoreFFA] Trying to connect to MySQL database via VolverixCore...");
                driverManager = new DriverManager("localhost", "root", "hardcoreffa", "");
                driverManager.startConnection();
                driverManager.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS PlayerData (UUID VARCHAR(128) PRIMARY KEY, COINS INT(16), CLAN VARCHAR(16), CLAN_RANK VARCHAR(16), ELO INT(16))").executeUpdate();
                driverManager.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS PlayerSettings (UUID VARCHAR(128) PRIMARY KEY, PARTY_REQUESTS INT(16), FRIEND_REQUESTS INT(16))").executeUpdate();
                driverManager.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS hardcoreffa (UUID VARCHAR(128) PRIMARY KEY, KILLS INT(16), DEATHS INT(16), POINTS INT(16), WINS INT(16), EXTRAS INT(16), ELO INT(16), ELO_RANK VARCHAR(64))").executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
