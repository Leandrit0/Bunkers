package us.zanis.bunkers;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import us.zanis.bunkers.eco.Balance;
import us.zanis.bunkers.eco.Coins;
import us.zanis.bunkers.listeners.JoinEvent;
import us.zanis.bunkers.listeners.MineOre;
import us.zanis.bunkers.scoreboard.ScoreboardHelper;
import us.zanis.bunkers.scoreboard.ScoreboardListener;
import us.zanis.bunkers.team.TeamInfo;
import us.zanis.bunkers.teams.Blue;
import us.zanis.bunkers.utils.ConfigManager;
import us.zanis.bunkers.utils.Cuboid;
import us.zanis.bunkers.utils.Manager;

public class Bunkers extends JavaPlugin implements Listener {

	public static Bunkers plugin;
	private static ScoreboardListener scoreboardListener;
    private Map<Player, ScoreboardHelper> scoreboardHelperMap;
	private JoinEvent playerListener;
	
	
	@Override
	public void onEnable() {
		plugin = this;
		
		Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
			
			@Override
			public void run() {
				if(Manager.isGameStarted) {
					for(Player p : Bukkit.getOnlinePlayers()) {
						Coins.addCoins(p.getUniqueId(), 15);
				   }
			   }
			}
		}, 20, 20 * 3);
		ConfigManager.load(this, "config.yml");
		StartCountdown.setup();
		GameTime.setup();
		
		
		setup();
		setupCommands();
		setupListeners();
		System.out.println("[Bunkers] Enabled on the version 1.0");
		Location pos1 = new Location(Bukkit.getWorld("world"), 231, 0, -487);
		Location pos2 = new Location(Bukkit.getWorld("world"), 235, 256, -483);
		Cuboid bluebase = new Cuboid(pos1, pos2);;
	}
	
	public void setupListeners() {
		plugin = this;

		
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(this, this);
		pm.registerEvents(new MineOre(this), this);
		pm.registerEvents(new Blue(this), this);
		
	}
	public void setupCommands() {
		plugin = this;
		
		getCommand("balance").setExecutor(new Balance(this));
		getCommand("team").setExecutor(new TeamInfo(this));
		
	}
	
    public static Bunkers getPlugin(){
        return plugin;
    }
    private static Bunkers instance;
	
    public static Bunkers getInstance()
    {
      if (instance == null)
      {
        instance = new Bunkers();
      }
      return instance;
    }
    private void setup()
    {
      instance = this;
      
      scoreboardListener = new ScoreboardListener();
      Bukkit.getServer().getPluginManager().registerEvents(scoreboardListener, this);
      playerListener = new JoinEvent(this);
      Bukkit.getServer().getPluginManager().registerEvents(playerListener, this);
      
      scoreboardListener.setupScoreboard();
      
      for (Player player : Bukkit.getServer().getOnlinePlayers())
      { 
        scoreboardListener.registerScoreboards(player);
        scoreboardListener.resendTab(player);
      }
    }
    
    public static ScoreboardListener getScoreboardListener(){
    	return scoreboardListener;
    }
}
