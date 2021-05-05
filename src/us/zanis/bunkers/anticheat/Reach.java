package us.zanis.bunkers.anticheat;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import us.zanis.bunkers.Bunkers;

public class Reach implements Listener {
	
	private Bunkers plugin;
	public Reach(Bunkers pl) {
		plugin = pl;
	}

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		Player damager = (Player) e.getDamager();
		Player given = (Player) e.getEntity();
		Location damagerloc = damager.getLocation();
		Location givenloc = given.getLocation();

		double distancex = damagerloc.getX() - givenloc.getX();
		double distancez = damagerloc.getZ() - givenloc.getZ();
		
		if(distancex > 4.0) {
			AntiCheat.Log(damager, "Reach", 4, false);
		}
		if(distancez > 4.0) {
			AntiCheat.Log(damager, "Reach", 4, false);
		}
		
	}
	
}
