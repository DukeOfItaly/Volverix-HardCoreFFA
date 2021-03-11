package net.volverix.me.dukeofitaly.me.oxince.hardcoreffa;


import lombok.Getter;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.commands.SetSpawnCommand;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.game.KitTypes;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.listener.PlayerDeathListener;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.listener.PlayerDisconnectListener;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.listener.PlayerJoinListener;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.ConfigPattern;
import net.volverix.me.oxince.volverixcore.sql.DriverManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class HardCoreFFA extends JavaPlugin {


    @Getter
    private static HardCoreFFA hardCoreFFA;
    @Getter
    private ConfigPattern configPattern;
    @Getter
    private DriverManager driverManager;

    @Override
    public void onEnable() {
        hardCoreFFA = this;
        configPattern = new ConfigPattern();
        driverManager = new DriverManager("", "", "", "");
        KitTypes.setCurrentKit(KitTypes.TEST);
        configPattern.saveConfig();
        configPattern.setupFiles();
        registerCommands();
        registerListener();

    }

    public void onDisable() {
    }

    private void registerCommands() {
        this.getCommand("setspawn").setExecutor(new SetSpawnCommand());
    }

    private void registerListener() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerDeathListener(), this);
        pm.registerEvents(new PlayerJoinListener(), this);
        pm.registerEvents(new PlayerDisconnectListener(), this);
    }

}
