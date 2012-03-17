package com.famousserver.FamousGuilds.commands;

import java.sql.SQLException;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.famousserver.FamousGuilds.util.FGMySQL;
import com.famousserver.FamousGuilds.util.FamousCommand;
import com.famousserver.FamousGuilds.util.FamousGuild;

public class AddGuildCommand extends FamousCommand {

	public AddGuildCommand()
	{
		super("add", "famousguilds.add", false, "create", "", "");
	}

	public boolean exec(CommandSender sender, String[] args) {
		Player player = (Player) sender;
		if(args.length != 1)
		{
			return false;
		}
		
		HashMap<Integer, String> hm = new HashMap<Integer, String>();
		HashMap<Integer, Integer> hm2 = new HashMap<Integer, Integer>();
		FamousGuild guild = new FamousGuild(args[0], hm, hm, hm2, player.getName());
		try {
			FGMySQL.addGuild(guild);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		player.sendMessage(ChatColor.GREEN + "You successfully created the guild " + args[0] + ".");
		return true;
	}
	
}
