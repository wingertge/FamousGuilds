package com.famousserver.FamousGuilds.commands;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.famousserver.FamousGuilds.util.FGMySQL;
import com.famousserver.FamousGuilds.util.FamousCommand;

public class PermissionCommand extends FamousCommand{

	public PermissionCommand() {
		super("permission", "guild.managegroups", false, "Manage permissions for groups.", "/guild permission <group> <permission> <value>", "perm");
	}

	@Override
	public boolean exec(CommandSender sender, String[] args) {
		if(args.length != 3)
		{
			sender.sendMessage(usage);
			return true;
		}
		boolean prop = false;
		if(args[2].equalsIgnoreCase("true"))
		{
			prop = true;
		}
		else if(args[2].equalsIgnoreCase("false"))
		{
			prop = false;
		}
		else
		{
			sender.sendMessage(ChatColor.RED + "Illegal permission value!");
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
			String membergroup = FGMySQL.getMemberGroup(guild, sender.getName());
			boolean hasPermission = FGMySQL.getProperty(guild, membergroup, "managegroups");
			if(!hasPermission)
			{
				sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
				return true;
			}
			String group = args[0];
			if(FGMySQL.getGroupID(guild, group) == 0)
			{
				sender.sendMessage(ChatColor.RED + "Specified group does not exist!");
				return true;
			}
			String property = args[1];
			List<String> props = new ArrayList<String>();
			props.add("kick");
			props.add("invite");
			props.add("managegroups");
			props.add("managesiege");
			props.add("accessguildbank");
			props.add("changeoptions");
			if(!props.contains(property))
			{
				sender.sendMessage(ChatColor.RED + "Illegal permission node!");
				return true;
			}
			FGMySQL.setProperty(guild, group, property, prop);
			sender.sendMessage(ChatColor.GREEN + "Successfully changed group permission!");
			return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
}
