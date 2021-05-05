package us.zanis.bunkers.team;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.avaje.ebeaninternal.server.cluster.mcast.Message;

import us.zanis.bunkers.Bunkers;
import us.zanis.bunkers.teams.Blue;
import us.zanis.bunkers.teams.Green;
import us.zanis.bunkers.utils.Manager;
import us.zanis.bunkers.utils.MessageUtils;

public class TeamInfo implements CommandExecutor {
	
	private Bunkers plugin;
	public TeamInfo(Bunkers pl) {
		plugin = pl;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("team")) {
			if(args.length == 0) {
				p.sendMessage(MessageUtils.translate("&e---------------------------------"));
				p.sendMessage(MessageUtils.translate("&6/team join <red/blue/green/yellow) &7(Join to a team)"));
				p.sendMessage(MessageUtils.translate("&6/team info <red/blue/green/yellow) &7(Check the info of a team)"));
				p.sendMessage(MessageUtils.translate("&e---------------------------------"));
			}
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("info")) {
					if(Blue.blueteam.contains(p.getName())) {
						p.sendMessage(MessageUtils.translate("&7-----&1Blue Team&7----"));
						p.sendMessage(MessageUtils.translate("&a&lDTR&r: &r" + Blue.getBlueDtr()));
						p.sendMessage(MessageUtils.translate("&6&lMembers&r: &r" + Blue.blueteam.size()));
						for(int i = 0; i < Blue.blueteam.size(); i++) {
							p.sendMessage(MessageUtils.translate("&8- &7" + Blue.blueteam.get(i)));
						}
						p.sendMessage(MessageUtils.translate("&e&lHome&r: &rX " + Blue.spawnblue.getBlockX() + "  Y" + Blue.spawnblue.getBlockY() + "  Z" + Blue.spawnblue.getBlockZ()));
						p.sendMessage(MessageUtils.translate("&7-----&1Blue Team&7----"));
					}
				}	
			}
		}
		if(args.length == 2) {
			if(args[0].equalsIgnoreCase("join")) {
				if(args[1].equalsIgnoreCase("blue")) {
					if(Blue.blueteam.size() < Manager.MAX_SIZE) {
					    Blue.blueteam.add(p.getName());
					    p.sendMessage(MessageUtils.translate("&1Succesfully join the team Blue."));
					} else {
						p.sendMessage(MessageUtils.translate("&cError: Team blue is MAX SIZE!"));
					}
				}
				if(args[1].equalsIgnoreCase("green")) {
					if(Blue.blueteam.size() < Manager.MAX_SIZE) {
					    Green.greenteam.add(p.getName());
					    p.sendMessage(MessageUtils.translate("&1Succesfully join the team Green."));
					} else {
						p.sendMessage(MessageUtils.translate("&cError: Team blue is MAX SIZE!"));
					}
				}
			}
		}
		return false;
	}

}
