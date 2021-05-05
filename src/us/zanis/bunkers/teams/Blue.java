package us.zanis.bunkers.teams;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import us.zanis.bunkers.Bunkers;
import us.zanis.bunkers.utils.Cuboid;

public class Blue implements Listener {

	
	private Bunkers plugin;
	public Blue(Bunkers pl){
		plugin = pl;
	}
	
	public static List<String> blueteam = new ArrayList<>();
	public static Location pos1 = new Location(Bukkit.getWorld("world"), 231, 0, -487);
	public static Location pos2 = new Location(Bukkit.getWorld("world"), 235, 256, -483);
	public static Cuboid bluebase = new Cuboid(pos1, pos2);;
	public static double dtr = 5.0;
	
	public static Location spawnblue = new Location(Bukkit.getWorld("world"), 0, 10, 0);
	
	public static double getBlueDtr() {
		return dtr;
	}
	
	public static Cuboid getBlueCuboid() {
		return bluebase;
	}
	
    public int getSize() {
        return blueteam.size();
    }
    
    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
    	Player died = e.getEntity().getPlayer();
    	double lostdtr = 1.0;
    	
    	if(Blue.blueteam.contains(died.getName())) {
    		Blue.dtr = Blue.dtr - lostdtr;
    	}
    }
    
}
