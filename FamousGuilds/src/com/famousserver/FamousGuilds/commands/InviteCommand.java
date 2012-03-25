package com.famousserver.FamousGuilds.commands;

import java.sql.SQLException;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.famousserver.FamousGuilds.FamousGuilds;
import com.famousserver.FamousGuilds.util.FGMySQL;
import com.famousserver.FamousGuilds.util.FamousCommand;

public class InviteCommand extends FamousCommand {

	public InviteCommand() {
		super("invite", "famousguilds.invite", false, "Invite someone to your guild", "/guild invite <name>", "inv");
	}

	public boolean exec(CommandSender sender, String[] args) {
		try {
			Player player = (Player)sender;
			
			if(args.length != 1)
			{
				player.sendMessage(usage);
				return true;
			}
			
			String guild = FGMySQL.getMemberGuild(player.getName());
			
			if(guild.equals(""))
			{
				player.sendMessage("You are not in a guild!");
				return true;
			}
			
			if(!(FGMySQL.getProperty(guild, FGMySQL.getMemberGroup(guild, player.getName()), "invite")))
			{
				player.sendMessage("You don't have permission to invite someone.");
				return true;
			}
			
			String target = args[0];
			Player tar = null;
			
			try
			{
				tar = FamousGuilds.instance.getServer().getPlayer(target);
			}
			catch(Exception e)
			{
				player.sendMessage("Target player must exists and be online!");
				return true;
			}
			
			if(FGMySQL.getMemberGuild(target).equals(""))
			{
				FGMySQL.joinGuild(guild, target);
				tar.sendMessage("You joined the guild " + guild + ".");
				return true;
			}
			else
			{
				player.sendMessage("The target is already in a guild.");
				tar.sendMessage(player.getName() + " tried to invite you to " + FGMySQL.getMemberGuild(player.getName()) + ". The invitation was declined because you are already in a guild.");
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
