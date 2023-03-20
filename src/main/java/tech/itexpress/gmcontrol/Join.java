package tech.itexpress.gmcontrol;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Join implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Liste.Players.add(event.getPlayer().getName());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Liste.Players.remove(event.getPlayer().getName());
    }
}