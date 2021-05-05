package us.zanis.bunkers.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import us.zanis.bunkers.Bunkers;
import us.zanis.bunkers.teams.Blue;

public class Spectator {

	public static Bunkers plugin;
	public static List<Player> spectadorespormuerte = new ArrayList<>();
	
	public static void spectartor(Player p) {
		Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
			public void run() {	
				for(int time =10; time > 10; time--){
					if(time == 10) {
						p.sendMessage(MessageUtils.translate("&69 &cseconds ultil respawn"));
					}
					if(time == 5) {
						p.sendMessage(MessageUtils.translate("&65 &cseconds ultil respawn"));
					}
					if(time == 3){
						p.sendMessage(MessageUtils.translate("&63 &cseconds ultil respawn"));
					}
					if(time == 2){
						p.sendMessage(MessageUtils.translate("&62 &cseconds ultil respawn"));
					}
					if(time == 1){
						p.sendMessage(MessageUtils.translate("&61 &cseconds ultil respawn"));
					}
					if(time ==0) {
						if(Blue.blueteam.contains(p.getName())) {
						    p.teleport(Blue.spawnblue);
						}
						spectadorespormuerte.remove(p);
					}
				}
			}
		}, 20, 20 *5);
	}
	
	@EventHandler
	public void ondeath(PlayerDeathEvent e){
    	Player died = e.getEntity();

    	if(Manager.isGameStarted){
    		spectartor(died);
    		spectadorespormuerte.add(died);
    	}
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if(spectadorespormuerte.contains(e.getPlayer())) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(MessageUtils.translate("&cWait ultil move."));
		}
	}
	
}
