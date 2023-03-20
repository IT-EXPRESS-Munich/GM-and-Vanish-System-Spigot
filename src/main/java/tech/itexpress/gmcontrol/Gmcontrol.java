package tech.itexpress.gmcontrol;

import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Gmcontrol extends JavaPlugin implements CommandExecutor {

    private boolean vanished = false;

    public void onEnable() {
        getCommand("gm").setExecutor(this);
        getCommand("v").setExecutor(this);
        getLogger().info("GMControl enabled.");
        final PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents((Listener)new Join(), (Plugin)this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!cmd.getName().equalsIgnoreCase("gm") && !cmd.getName().equalsIgnoreCase("v"))
            return false;

        Player player = (Player) sender;
        if (!player.hasPermission("system.team")) {
            player.sendMessage("§8[§dE§5hren§dG§5ames§8] §7Dazu hast du keine Rechte!");
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("gm")) {
            if (args.length < 1) {
                player.sendMessage("§8[§dE§5hren§dG§5ames§8] §7Gamemode §8[§d0§8/§d1§8/§d2§8/§3§8/§e5§8]");
                return true;
            }

            switch (args[0]) {
                case "1":
                    player.sendMessage("§8[§dE§5hren§dG§5ames§8] §7Du bist nun im Gamemode §d1");
                    player.setGameMode(GameMode.CREATIVE);
                    break;
                case "2":
                    player.sendMessage("§8[§dE§5hren§dG§5ames§8] §7Du bist nun im Gamemode §d2");
                    player.setGameMode(GameMode.ADVENTURE);
                    break;
                case "3":
                    player.sendMessage("§8[§dE§5hren§dG§5ames§8] §7Du bist nun im Gamemode §d3");
                    player.setGameMode(GameMode.SPECTATOR);
                    break;
                case "0":
                case "4":
                    player.sendMessage("§8[§dE§5hren§dG§5ames§8] §7Du bist nun im Gamemode §d0");
                    player.setGameMode(GameMode.SURVIVAL);
                    break;
                case "5":
                    if (!vanished) {
                        player.sendMessage("§8[§dE§5hren§dG§5ames§8] §7Du bist nun im Vanish-Modus");
                        player.setGameMode(GameMode.SPECTATOR);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1), true);
                        if (Liste.Players.contains(player.getPlayer().getName())) {
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                all.hidePlayer(player);
                            }
                        }
                        vanished = true;
                    } else {
                        player.sendMessage("§8[§dE§5hren§dG§5ames§8] §7Du bist nun nicht mehr im Vanish-Modus");
                        player.setGameMode(GameMode.SURVIVAL);
                        player.removePotionEffect(PotionEffectType.INVISIBILITY);
                        if (Liste.Players.contains(player.getPlayer().getName())) {
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                all.showPlayer(player);
                            }
                        }
                        vanished = false;
                    }
                    break;
                default:
                    player.sendMessage("§8[§dE§5hren§dG§5ames§8] §7Ungültiger Gamemode. Verwendung: /gm <0|1|2|3|4|5>");
                    break;
            }
        } else if (cmd.getName().equalsIgnoreCase("v")) {
            if (!vanished) {
                player.sendMessage("§8[§dE§5hren§dG§5ames§8] §7Du bist nun im Vanish-Modus");
                player.setGameMode(GameMode.SPECTATOR);
                player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1), true);
                if (Liste.Players.contains(player.getPlayer().getName())) {
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        all.hidePlayer(player);
                    }
                }
                vanished = true;
            } else {
                player.sendMessage("§8[§dE§5hren§dG§5ames§8] §7Du bist nun nicht mehr im Vanish-Modus");
                player.setGameMode(GameMode.SURVIVAL);
                player.removePotionEffect(PotionEffectType.INVISIBILITY);
                if (Liste.Players.contains(player.getPlayer().getName())) {
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        all.showPlayer(player);
                    }
                }
                vanished = false;
            }
        }


        return true;
    }
}
