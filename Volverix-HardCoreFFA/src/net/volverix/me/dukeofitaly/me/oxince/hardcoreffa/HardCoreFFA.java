package net.volverix.me.dukeofitaly.me.oxince.hardcoreffa;

import org.bukkit.plugin.java.JavaPlugin;

public class HardCoreFFA extends JavaPlugin {

    private HardCoreFFA hardCoreFFA;

    @Override
    public void onEnable() {
        this.hardCoreFFA = getHardCoreFFA();


    }

    @Override
    public void onDisable() {

    }

    public HardCoreFFA getHardCoreFFA() {
        return hardCoreFFA;
    }

}
