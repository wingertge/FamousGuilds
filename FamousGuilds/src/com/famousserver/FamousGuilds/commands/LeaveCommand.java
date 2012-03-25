package com.famousserver.FamousGuilds.commands;

import java.sql.SQLException;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.famousserver.FamousGuilds.util.FGMySQL;
import com.famousserver.FamousGuilds.util.FamousCommand;

public class LeaveCommand extends FamousCommand {

	public LeaveCommand() {
		super("leave", "famousguilds.leave", false, "Leave a guild.", "/guild leave", "l");
	}

	public boolean exec(CommandSender sender, String[] args) {
		try
		{
		Player player = (Player)sender;
		String guild = FGMySQL.getMemberGuild(player.getName());
		if(guild.equals(""))
		{
			player.sendMessage("You are not in a guild!");
			return true;
		}
		FGMySQL.removeMember(guild, player.getName());
		player.sendMessage("You left " + guild + ".");
		return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
}
