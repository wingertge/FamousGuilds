package com.famousserver.FamousGuilds.commands;

import java.sql.SQLException;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.famousserver.FamousGuilds.util.FGMySQL;
import com.famousserver.FamousGuilds.util.FamousCommand;

public class JoinCommand extends FamousCommand {

	public JoinCommand() {
		super("join", "guild.join", false, "Join a guild", "/guild join <guild>", "j");
	}

	public boolean exec(CommandSender sender, String[] args) {
		if(args.length != 1)
		{
			sender.sendMessage(usage);
			return true;
		}
		try
		{
			String oldguild = FGMySQL.getMemberGuild(sender.getName());
			if(!oldguild.equals(""))
			{
				sender.sendMessage(ChatColor.RED + "You are already in a guild!");
				return true;
			}
			if(!FGMySQL.getGuilds().contains(args[0]))
			{
				sender.sendMessage(ChatColor.RED + "Guild does not exist!");
			}
			String option = FGMySQL.getOption(args[0], "status");
			if(option != "public")
			{
				sender.sendMessage(ChatColor.RED + "Guild is not public!");
				return true;
			}
			FGMySQL.joinGuild(args[0], sender.getName());
			sender.sendMessage(ChatColor.GREEN + "You joined the guild " + args[0] + "!");
			return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
}
