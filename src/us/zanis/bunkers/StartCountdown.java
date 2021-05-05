package us.zanis.bunkers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import us.zanis.bunkers.teams.Blue;
import us.zanis.bunkers.utils.Manager;
import us.zanis.bunkers.utils.MessageUtils;

public class StartCountdown {

	
	private Bunkers plugin;
	public StartCountdown(Bunkers pl) {
		plugin = pl;
	}
	
	public static boolean canstart = false;
    public static int minPlayers = 2;
    static int broadcast = 25;
    public static int waitingTimer = 20;
    public static int waitingTask;
	
    public static void setup(){
        waitingTimer();
    }
    public static void waitingTimer(){
        waitingTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(Bunkers.getPlugin(), new Runnable() {

            @Override
            public void run() {
                if(Bukkit.getOnlinePlayers().size() >= minPlayers) {
                    switch (waitingTimer){
                    case 20:
                    	canstart = true;
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&cGame starting in &e" + waitingTimer + "&c second(s)..."));
                        break;
                    case 15:
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&cGame starting in &e" + waitingTimer + "&c second(s)..."));
                        break;
                    case 10:
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&cGame starting in &e" + waitingTimer + "&c second(s)..."));
                        break;
                    case 5:
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&cGame starting in &e" + waitingTimer + "&c second(s)..."));
                        break;
                    case 4:
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&cGame starting in &e" + waitingTimer + "&c second(s)..."));
                        break;
                    case 3:
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&cGame starting in &e" + waitingTimer + "&c second(s)..."));
                        break;
                    case 2:
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&cGame starting in &e" + waitingTimer + "&c second(s)..."));
                        break;
                    case 1:
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&cGame starting in &e" + waitingTimer + "&c second(s)..."));
                    	Bukkit.broadcastMessage(MessageUtils.translate("&aSetting up teams..."));
                        break;
                    case 0:
                    	canstart = false;
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&cGame starting..."));
                        GameTime.gametime = true;
                        //for(Player p : Bukkit.getOnlinePlayers()) {
                        	//if(Blue.blueteam.contains(p.getName())){
                        		//p.kickPlayer(MessageUtils.translate("&cYou must need to select team:/"));
                        	//}
                        //}
                        Manager.startGame();
                        Bukkit.getScheduler().cancelTask(waitingTask);
                        break;
                }
                waitingTimer--;	
                }
            }
        }, 0L, 20L);
    }


}
