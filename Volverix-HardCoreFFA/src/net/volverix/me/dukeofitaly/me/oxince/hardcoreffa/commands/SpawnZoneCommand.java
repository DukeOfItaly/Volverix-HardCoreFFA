package net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.commands;

import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.HardCoreFFA;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.ConfigPattern;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.ZonePattern;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnZoneCommand implements CommandExecutor {

    HardCoreFFA hardCoreFFA = HardCoreFFA.getHardCoreFFA();
    ConfigPattern configPattern = hardCoreFFA.getConfigPattern();
    ZonePattern zonePattern = hardCoreFFA.getZonePattern();

    private String prefix = configPattern.getPrefix();


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("hardcoreffa.setpos")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("pos1")) {
                        Location loc = player.getLocation();
                        zonePattern.setPositionL1InConfig(loc);
                        player.sendMessage(prefix + "§7Du hast die Position '§a1§7' gesetzt!");
                    } else if (args[0].equalsIgnoreCase("pos2")) {
                        Location loc = player.getLocation();
                        zonePattern.setPositionL2InConfig(loc);
                        player.sendMessage(prefix + "§7Du hast die Position '§a2§7' gesetzt!");
                    } else {
                        player.sendMessage(prefix + "§7Bitte nutze /setpos <pos1 | pos 2>!");
                    }
                } else {
                    player.sendMessage(prefix + "§7Bitte nutze /setpos <pos1 | pos 2>!");
                }
            } else {
                player.sendMessage(prefix + "§cYou don't have the permissions to execute this command!");
            }
        }

        return false;
    }


}
