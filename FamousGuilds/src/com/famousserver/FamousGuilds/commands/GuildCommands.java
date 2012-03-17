package com.famousserver.FamousGuilds.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.famousserver.FamousGuilds.util.FamousCommand;

public class GuildCommands extends FamousCommand {

	List<FamousCommand> commands = new ArrayList<FamousCommand>();
	
	public GuildCommands()
	{
		super("guild", "", true, "NOHELP", "NOUSAGE", "g", "fg", "famousguild");
	}
	
	public boolean exec(CommandSender sender, String[] args)
	{
		FamousCommand cmd;
		if(args[0].equalsIgnoreCase("add"))
		{
			cmd = new AddGuildCommand();
			return cmd.exec(sender, args);
		}
		else
		{
			return false;
		}
	}	
}
