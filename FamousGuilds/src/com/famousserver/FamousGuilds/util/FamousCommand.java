package com.famousserver.FamousGuilds.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;

public abstract class FamousCommand {

	public String name;
	public List<String> aliases = new ArrayList<String>();
	public String permission;
	public String help;
	public String usage;
	public boolean isConsoleCommand;
	
	public FamousCommand(String name, String permission, boolean isConsoleCommand, String help, String usage, String... aliases)
	{
		this.name = name;
		this.permission = permission;
		this.help = help;
		this.usage = usage;
		this.isConsoleCommand = isConsoleCommand;
		for(String alias : aliases)
		{
			this.aliases.add(alias);
		}
	}
	
	public abstract boolean exec(CommandSender sender, String[] args);
}
