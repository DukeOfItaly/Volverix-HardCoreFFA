package net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.commands;

import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.HardCoreFFA;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.ConfigPattern;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.MapPattern;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ForceMapCommand implements CommandExecutor {

    HardCoreFFA hardCoreFFA = HardCoreFFA.getHardCoreFFA();
    MapPattern mapPattern = hardCoreFFA.getMapPattern();
    ConfigPattern configPattern = hardCoreFFA.getConfigPattern();

    String prefix = configPattern.getConfigString("Game.Prefix");

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("hardcoreffa.forcemap")) {
                if (args.length == 1) {
                    String map = args[2];
                    mapPattern.setNextMap(map);
                } else {
                    player.sendMessage(prefix + "§7Please use /forcemap <mapname>");
                }
            } else {
                player.sendMessage(configPattern.getConfigString("Game.Prefix") + "§cYou don't have the permissions to execute this command!");
            }
        }
        return false;
    }
}
