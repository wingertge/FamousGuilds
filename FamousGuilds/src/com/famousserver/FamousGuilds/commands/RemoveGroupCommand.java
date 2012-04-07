package com.famousserver.FamousGuilds.commands;

import java.sql.SQLException;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.famousserver.FamousGuilds.util.FGMySQL;
import com.famousserver.FamousGuilds.util.FamousCommand;

public class RemoveGroupCommand extends FamousCommand {

	public RemoveGroupCommand() {
		super("removegroup", "guild.managegroups", false, "Remove a group.", "/guild removegroup <group>", "remgroup");
	}

	public boolean exec(CommandSender sender, String[] args) {
		if(args.length != 1)
		{
			sender.sendMessage(usage);
			return true;
		}
		String group = args[0];
		if(group.equalsIgnoreCase("Leader") || group.equalsIgnoreCase("Member"))
		{
			sender.sendMessage("You can't delete this group!");
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
			if(FGMySQL.getGroupID(guild, group) == 0)
			{
				sender.sendMessage(ChatColor.RED + "The specified group does not exist!");
				return true;
			}
			String membergroup = FGMySQL.getMemberGroup(guild, sender.getName());
			boolean hasPermission = FGMySQL.getProperty(guild, membergroup, "managegroups");
			if(!hasPermission)
			{
				sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
				return true;
			}
			FGMySQL.removeGroup(guild, group);
			sender.sendMessage(ChatColor.GREEN + "You have successfully removed the group " + group + ".");
			return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
}
