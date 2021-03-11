package net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.commands;

import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.HardCoreFFA;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.ConfigPattern;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.MapPattern;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateMapCommand implements CommandExecutor {

    HardCoreFFA hardCoreFFA = HardCoreFFA.getHardCoreFFA();
    ConfigPattern configPattern = hardCoreFFA.getConfigPattern();
    MapPattern mapPattern = hardCoreFFA.getMapPattern();
    String prefix = configPattern.getConfigString("Game.Prefix");

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("hardcoreffa.createmap")) {
                if (args.length == 1) {
                    String mapname = args[2];
                    if (!(mapPattern.getMaps().contains(mapname))) {
                        configPattern.createMap(mapname);
                        player.sendMessage(prefix + "§7You have created the map: §a" + mapname + "§7!");
                    } else {
                        player.sendMessage(prefix + "§cA map with this name has already been created!");
                    }
                } else {
                    player.sendMessage(prefix + "§7Please use /createmap <mapname>");
                }
            } else {
                player.sendMessage(prefix + "§cYou don't have the permissions to execute this command!");
            }
        }
        return false;
    }
}
