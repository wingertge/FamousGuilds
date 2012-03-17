package com.famousserver.FamousGuilds;

import org.spout.api.plugin.CommonPlugin;

public class FamousGuilds extends CommonPlugin 
{
	public static FamousGuilds instance;

	public void onDisable() 
	{
		
	}

	public void onEnable()
	{
		instance = this;
	}

}
