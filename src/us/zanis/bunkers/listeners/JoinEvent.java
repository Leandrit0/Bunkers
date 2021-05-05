package us.zanis.bunkers.listeners;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;

import us.zanis.bunkers.Bunkers;
import us.zanis.bunkers.teams.Blue;
import us.zanis.bunkers.utils.Manager;
import us.zanis.bunkers.utils.MessageUtils;

import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

	private Bunkers plugin;
	
	
	public JoinEvent(Bunkers pl) {
		plugin = pl;
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Block b = e.getBlock();
		Player p = e.getPlayer();
		
		if(Blue.getBlueCuboid().contains(b)) {
			if(!Blue.blueteam.contains(p.getName())) {
				e.setCancelled(true);
				p.sendMessage(MessageUtils.translate("&cYou cannot break in a territory of &1Blue."));
			}
		}
	}
	
	@EventHandler
	public void playerPreLogin(AsyncPlayerPreLoginEvent event) {
		if (Manager.isGameStarted){
			event.disallow(Result.KICK_OTHER, MessageUtils.translate("&cThe game has already started!"));
		}
	}
	@SuppressWarnings("static-access")
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		
		e.setJoinMessage(null);
		
		plugin.getScoreboardListener().registerScoreboards(player);
	}
	
}
