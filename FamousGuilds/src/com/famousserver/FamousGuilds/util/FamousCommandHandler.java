package com.famousserver.FamousGuilds.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FamousCommandHandler {

public List<FamousCommand> commands = new ArrayList<FamousCommand>();
	
	public void registerCommand(FamousCommand command)
	{
		if(!commands.contains(command))
		{
			commands.add(command);
		}
	}
	
	public boolean onCommand(CommandSender sender, Command command1, String label, String[] args) {
		String name = command1.getName();
		for(FamousCommand command : commands)
		{
			if(command.name.equalsIgnoreCase(name) || command.aliases.contains(name))
			{
				if(!(sender instanceof Player))
				{
					if(!command.isConsoleCommand)
					{
						sender.sendMessage(ChatColor.RED + "This is not a console command!");
						return true;
					}
					
					if(!sender.hasPermission(command.permission))
					{
						sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
						return true;
					}
				}
				
				return command.exec(sender, args);
			}
		}
		sender.sendMessage(ChatColor.RED + "Command not found!");
		return false;
	}
	
}
