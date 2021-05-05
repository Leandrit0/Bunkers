package us.zanis.bunkers.team;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import us.zanis.bunkers.Bunkers;

public class TeamShowCommand implements CommandExecutor {

	private Bunkers plugin;
	public TeamShowCommand(Bunkers pl) {
		plugin = pl;
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		return false;
	}

}
