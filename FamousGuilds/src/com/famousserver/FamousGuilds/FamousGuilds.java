package com.famousserver.FamousGuilds;

import java.io.File;

import com.famousserver.FamousGuilds.util.Config;

import org.spout.api.plugin.CommonPlugin;

public class FamousGuilds extends CommonPlugin 
{
	public static FamousGuilds instance;
	private Config config;
	private File configFile = new File("plugins/FamousGuilds/config.yml");

	public void onDisable() 
	{
		
	}

	public void onEnable()
	{
		instance = this;
		config = new Config(configFile);
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
}
