package us.zanis.bunkers;

import org.bukkit.Bukkit;

public class GameTime {

	private static Bunkers plugin;
	public GameTime(Bunkers pl) {
		plugin = pl;
	}
	
	public static boolean gametime = false;
	public static int gametimer = 0;
	public static int gametimertask;
	
	
	public static void setup(){
		timerCount();
	}
	
	public static void timerCount() {
		gametimertask = Bukkit.getScheduler().scheduleSyncRepeatingTask(Bunkers.getPlugin(), new Runnable() {
			
			@Override
			public void run() {
				if(gametime) {
					gametimer++;
				}
				
			}
		}, 0l, 20L);
	}
}
