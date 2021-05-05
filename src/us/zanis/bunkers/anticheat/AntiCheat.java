package us.zanis.bunkers.anticheat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import us.zanis.bunkers.utils.MessageUtils;

public class AntiCheat {

	public static void Log(Player cheater, String string, int level, boolean obvious) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.sendMessage(MessageUtils.translate("&6[Kas] &6" + cheater.getName() + "was detected " + string + " at the level" + level 
					+ " &4Obvious: &3" + obvious));
		}
	}
	
	
}
