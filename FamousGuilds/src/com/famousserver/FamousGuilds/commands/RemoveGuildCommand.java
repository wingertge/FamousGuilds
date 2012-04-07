package com.famousserver.FamousGuilds.commands;

import java.sql.SQLException;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.famousserver.FamousGuilds.util.FGMySQL;
import com.famousserver.FamousGuilds.util.FamousCommand;

public class RemoveGuildCommand extends FamousCommand {

	public RemoveGuildCommand() {
		super("removeguild", "guild.remove", false, "Remove your guild.", "/guild removeguild", "remguild", "remove", "rem");
	}

	public boolean exec(CommandSender sender, String[] args) {
		try
		{
			String guild = FGMySQL.getMemberGuild(sender.getName());
			if(guild.equals(""))
			{
				sender.sendMessage(ChatColor.RED + "You are not in a guild!");
				return true;
			}
			String leader = FGMySQL.getLeader(guild);
			if(!leader.equalsIgnoreCase(sender.getName()))
			{
				sender.sendMessage(ChatColor.RED + "You must be the leader of this guild to do that!");
				return true;
			}
			FGMySQL.removeGuild(guild);
			sender.sendMessage(ChatColor.GREEN + "Successfully removed the guild " + guild + "!");
			return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
}
