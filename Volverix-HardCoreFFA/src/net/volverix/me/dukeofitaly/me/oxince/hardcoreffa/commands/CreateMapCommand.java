package net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.commands;

import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.HardCoreFFA;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.ConfigPattern;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateMapCommand implements CommandExecutor {

    HardCoreFFA hardCoreFFA = HardCoreFFA.getHardCoreFFA();
    ConfigPattern configPattern = hardCoreFFA.getConfigPattern();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("hardcoreffa.createmap")) {
                if (args.length == 1) {
                    String mapname = args[2];

                } else {
                    player.sendMessage(configPattern.getConfigString("Game.Prefix") + "§7Please use /createmap <mapname>");
                }
            } else {
                player.sendMessage(configPattern.getConfigString("Game.Prefix") + "§cYou don't have the permissions to execute this command!");
            }
        }
        return false;
    }
}
