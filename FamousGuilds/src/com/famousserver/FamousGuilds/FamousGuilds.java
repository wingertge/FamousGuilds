package com.famousserver.FamousGuilds;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.famousserver.FamousGuilds.util.Config;
import com.famousserver.FamousGuilds.util.FGMySQL;
import com.famousserver.FamousGuilds.util.FamousGuild;

import org.spout.api.plugin.CommonPlugin;

public class FamousGuilds extends CommonPlugin 
{
	public static FamousGuilds instance;
	private Config config;
	private File configFile = new File("plugins/FamousGuilds/config.yml");
	public Map<Integer, String>te=new HashMap<Integer, String>();
	public Map<Integer, String>mem=new HashMap<Integer, String>();
	public Map<Integer, Integer>memg=new HashMap<Integer, Integer>();

	public void onDisable() 
	{
		
	}

	public void onEnable()
	{
		instance = this;
		config = new Config(configFile);
		reloadConfig();
		addDefaults();
	}

	public Config getConfig()
	{
		return config;
	}
	
	public void saveConfig()
	{
		config.save();
	}
	
	public void reloadConfig()
	{
		config.reload();
	}
	
	public void addDefaults()
	{
		getConfig().addDefault("MySQL.USER", "user");
		getConfig().addDefault("MySQL.PASSWORD", "pw");
		getConfig().addDefault("MySQL.URL", "jdbc:mysql://localhost:3306/");
		getConfig().copyDefaults();
		saveConfig();
	}
}
