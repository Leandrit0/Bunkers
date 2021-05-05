package us.zanis.bunkers.eco;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import us.zanis.bunkers.Bunkers;
import us.zanis.bunkers.utils.Manager;
import us.zanis.bunkers.utils.MessageUtils;

public class Balance implements CommandExecutor {

	private Bunkers plugin;
	public Balance(Bunkers pl) {
		plugin = pl;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("balance")) {
			if(args.length == 0) {
				Player p = (Player) sender;
				p.sendMessage(MessageUtils.translate("&e➤ &6Balance: &a" + Coins.getCoins(p.getUniqueId()) + "$"));
			}
			if (args.length == 1){
				Player target = Bukkit.getPlayer(args[0]);Player p = (Player) sender;
				
				p.sendMessage(MessageUtils.translate("&e➤ &6" + target.getName() + "´s balance: &a" + Coins.getCoins(p.getUniqueId()) + "$"));
			}
		}
		return false;
	}
}
