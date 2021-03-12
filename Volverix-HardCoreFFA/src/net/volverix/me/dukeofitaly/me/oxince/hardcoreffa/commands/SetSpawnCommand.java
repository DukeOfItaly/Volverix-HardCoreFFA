package net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.commands;

import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.HardCoreFFA;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.ConfigPattern;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {

    HardCoreFFA hardCoreFFA = HardCoreFFA.getHardCoreFFA();
    ConfigPattern configPattern = hardCoreFFA.getConfigPattern();
    String prefix = configPattern.getPrefix();


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("hardcoreffa.setspawn")) {
                if (args.length == 0) {
                    Location spawn = player.getLocation();
                    configPattern.setLocation("spawn", spawn);
                    player.sendMessage(prefix + "§aYou have set the spawn correctly!");
                } else {
                    player.sendMessage(prefix + "§7Please use /setspawn");
                }
            } else {
                player.sendMessage(prefix + "§cYou don't have the permissions to execute this command!");
            }
        }
        return false;
    }
}
