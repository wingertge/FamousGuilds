package com.famousserver.FamousGuilds.commands;

import java.sql.SQLException;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.famousserver.FamousGuilds.util.FGMySQL;
import com.famousserver.FamousGuilds.util.FamousCommand;

public class PropertyCommand extends FamousCommand {

	public PropertyCommand() {
		super("property", "guild.property", false, "Change a guild property", "/guild property <property> <value>", "option");
	}

	public boolean exec(CommandSender sender, String[] args) {
		if(args.length != 2)
		{
			sender.sendMessage(usage);
			return true;
		}
		try
		{
			String guild = FGMySQL.getMemberGuild(sender.getName());
			if(guild.equals(""))
			{
				sender.sendMessage(ChatColor.RED + "You are not in a guild!");
				return true;
			}
			
			String group = FGMySQL.getMemberGroup(guild, sender.getName());
			boolean perm = FGMySQL.getProperty(guild, group, "changeoptions");
			if(!perm)
			{
				sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
				return true;
			}
			
			FGMySQL.setOption(guild, args[0], args[1]);
			sender.sendMessage(ChatColor.GREEN + "You have set the option " + args[0] + " to " + args[1] + "!");
			return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
}
