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
		commands.add(new AddGuildCommand());
		commands.add(new AddGroupCommand());
		commands.add(new InviteCommand());
		commands.add(new LeaveCommand());
	}
	
	public boolean exec(CommandSender sender, String[] args)
	{
		String name = args[0];
		String[] realArgs = new String[args.length-1];
		for(int i=0; i<realArgs.length; i++)
		{
			realArgs[i] = args[i+1];
		}
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
				}
					
				if(!sender.hasPermission(command.permission))
				{
					sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
					return true;
				}
				
				return command.exec(sender, realArgs);
			}
		}
		sender.sendMessage(ChatColor.RED + "Command not found!");
		return false;
	}	
}
