package net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.commands;


import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.HardCoreFFA;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.game.KitTypes;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.ConfigPattern;
import net.volverix.me.dukeofitaly.me.oxince.hardcoreffa.utils.ItemPattern;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class BuildCommand implements CommandExecutor {

    public static ArrayList<Player> inBuild = new ArrayList<>();
    HardCoreFFA hardCoreFFA = HardCoreFFA.getHardCoreFFA();
    ConfigPattern configPattern = hardCoreFFA.getConfigPattern();
    private String prefix = configPattern.getPrefix();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            ItemPattern itemPattern = new ItemPattern(player);
            if (player.hasPermission("hardcoreffa.build")) {
                if (!(inBuild.contains(player))) {
                    inBuild.add(player);
                    player.setGameMode(GameMode.CREATIVE);
                    player.sendMessage(prefix + "§7You are now in §dBuild-Mode§7!");
                } else {
                    player.setGameMode(GameMode.SURVIVAL);
                    itemPattern.setKit(KitTypes.getCurrentKit());
                    inBuild.remove(player);
                    player.sendMessage(prefix + "§7You are no longer in §dBuild-Mode§7!");
                }
            } else {
                player.sendMessage(prefix + "§cYou don't have the permissions to execute this command!");
            }
        }
        return false;
    }
}
