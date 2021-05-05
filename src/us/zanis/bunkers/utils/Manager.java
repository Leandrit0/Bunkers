package us.zanis.bunkers.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.avaje.ebeaninternal.server.cluster.mcast.Message;

import us.zanis.bunkers.teams.Blue;

public class Manager {

	
	//Boolean Manager//
	public static boolean isGameStarted = false;
	public static boolean isServerWhitelisted = false;
	//Boolean Manager//
	
	
	
	//String Manager//
	public static String Permission_Error = MessageUtils.translate("&eⒾ &cYou doesn´t have the permission to use this command");
	public static String Console_Error = MessageUtils.translate("&eⒾ &cOnly players can execute this command");
	//String Manager//
	
	
	public static int MAX_SIZE = 4;
	
	//Start Manager//
	public static void startGame(){
	    isGameStarted = true;
	    for(Player p : Bukkit.getOnlinePlayers()) {
		    if(Blue.blueteam.contains(p.getName())) {
		    	p.teleport(Blue.spawnblue);
		    	p.sendMessage(MessageUtils.translate("&cThe game has started, you are on the team &1&lBlue."));
		    	p.sendMessage(MessageUtils.translate("&cRemember this gamemode is in beta, any bugs report please."));
			}	
	    }
	}
	
	
	
	public static String getTeamName(Player p) {
		if(Blue.blueteam.contains(p.getName())) {
			return MessageUtils.translate("&1Blue");
		}
		return MessageUtils.translate("&7No Team!");
	}
}
