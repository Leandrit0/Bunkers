package us.zanis.bunkers.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import us.zanis.bunkers.Bunkers;
import us.zanis.bunkers.teams.Blue;
import us.zanis.bunkers.utils.MessageUtils;


public class MineOre implements Listener {

	private Bunkers plugin;
	public List<Player> joinedblue = new ArrayList<>();

	public MineOre(Bunkers pl) {
		plugin = pl;
	}
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Block bloque = e.getBlock();

		if (bloque.getType().equals(Material.IRON_ORE)) {
			bloque.getState().setType(Material.COBBLESTONE);
			bloque.getState().update();
		}
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		Location to = e.getTo();
		
		if(Blue.getBlueCuboid().contains(e.getFrom()) && !Blue.getBlueCuboid().contains(to)){
			p.sendMessage(MessageUtils.translate("&eLeaving: &1Blue &eterritory."));
		}		
		if(!Blue.getBlueCuboid().contains(e.getFrom()) && Blue.getBlueCuboid().contains(to)){
			p.sendMessage(MessageUtils.translate("&eEntering: &1Blue &eterritory."));
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		if(Blue.blueteam.contains(e.getPlayer().getName())) {
			Blue.blueteam.remove(e.getPlayer().getName());
		}
	}
}