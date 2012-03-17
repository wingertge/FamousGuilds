package com.famousserver.FamousGuilds;

import java.util.HashMap;
import java.util.Map;

import com.famousserver.FamousGuilds.commands.GuildCommands;
import com.famousserver.FamousGuilds.util.FGMySQL;
import com.famousserver.FamousGuilds.util.FamousCommandHandler;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class FamousGuilds extends JavaPlugin 
{
	public static FamousGuilds instance;
	public FamousCommandHandler commandHandler;
	public Map<Integer, String>te=new HashMap<Integer, String>();
	public Map<Integer, String>mem=new HashMap<Integer, String>();
	public Map<Integer, Integer>memg=new HashMap<Integer, Integer>();

	public void onDisable() 
	{
		
	}
	
	public void onEnable()
	{
		instance = this;
		commandHandler = new FamousCommandHandler();
		reloadConfig();
		addDefaults();
		commandHandler.registerCommand(new GuildCommands());
		log("Loaded Guilds:");
		for(String guild : FGMySQL.guilds)
		{
			log(guild);
		}
	}
	
	public void log(String msg)
	{
		System.out.println(msg);
	}
	
	public void addDefaults()
	{
		getConfig().addDefault("MySQL.USER", "user");
		getConfig().addDefault("MySQL.PASSWORD", "pw");
		getConfig().addDefault("MySQL.URL", "jdbc:mysql://localhost:3306/");
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		return commandHandler.onCommand(sender, command, label, args);
	}
}
