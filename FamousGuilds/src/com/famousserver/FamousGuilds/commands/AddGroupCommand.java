package com.famousserver.FamousGuilds.commands;

import java.sql.SQLException;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.famousserver.FamousGuilds.util.FGMySQL;
import com.famousserver.FamousGuilds.util.FamousCommand;

public class AddGroupCommand extends FamousCommand{

	public AddGroupCommand() {
		super("addgroup", "famousguilds.addgroup", false, "", "");
	}

	public boolean exec(CommandSender sender, String[] args) {
		Player player = (Player)sender;
		if(args.length != 1)
		{
			return false;
		}
		try
		{
			String guild = FGMySQL.getMemberGuild(player.getName());
			if(guild.equals(""))
			{
				player.sendMessage(ChatColor.RED + "You are not in a guild!");
				return true;
			}
			String group = FGMySQL.getMemberGroup(guild, player.getName());
			if(FGMySQL.getProperty(guild, group, "managegroups"))
			{
				String name = args[0];
				if(FGMySQL.getGroupID(guild, name) == 0)
				{
					FGMySQL.addGroup(guild, name);
					player.sendMessage(ChatColor.GREEN + "You successfully added the group " + group + "!");
					return true;
				}
				else
				{
					player.sendMessage(ChatColor.RED + "Group already exists.");
					return true;
				}
			}
			else
			{
				player.sendMessage(ChatColor.RED + "You are not allowed to manage groups!");
				return true;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

}
