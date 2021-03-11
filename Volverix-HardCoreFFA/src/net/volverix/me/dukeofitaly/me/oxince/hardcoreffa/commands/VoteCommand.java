package net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.commands;

import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.HardCoreFFA;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.ConfigPattern;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.MapPattern;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VoteCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        HardCoreFFA hardCoreFFA = HardCoreFFA.getHardCoreFFA();
        MapPattern mapPattern = hardCoreFFA.getMapPattern();
        ConfigPattern configPattern = hardCoreFFA.getConfigPattern();

        String prefix = configPattern.getConfigString("Game.Prefix");

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1) {
                mapPattern.voteMap(args[2], player);
            } else {
                player.sendMessage(prefix + "§7Please use /vote <mapname>");
            }
        }
        return false;
    }
}
