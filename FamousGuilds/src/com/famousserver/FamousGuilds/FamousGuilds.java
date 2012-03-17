package com.famousserver.FamousGuilds;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.famousserver.FamousGuilds.commands.AdminCommands;
import com.famousserver.FamousGuilds.commands.GuildCommands;
import com.famousserver.FamousGuilds.util.Config;
import com.famousserver.FamousGuilds.util.FGMySQL;
import org.spout.api.command.CommandRegistrationsFactory;
import org.spout.api.command.annotated.AnnotatedCommandRegistrationFactory;
import org.spout.api.command.annotated.SimpleAnnotatedCommandExecutorFactory;
import org.spout.api.command.annotated.SimpleInjector;
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setupCommands()
	{
		CommandRegistrationsFactory commandRegFactory = new AnnotatedCommandRegistrationFactory(new SimpleInjector(new Object[] { this }), new SimpleAnnotatedCommandExecutorFactory());
		getGame().getRootCommand().addSubCommands(this, GuildCommands.class, commandRegFactory);
		getGame().getRootCommand().addSubCommands(this, AdminCommands.class, commandRegFactory);
	}
	
	public void onEnable()
	{
		instance = this;
		config = new Config(configFile);
		setupCommands();
		reloadConfig();
		addDefaults();
		log("Loaded Guilds:");
		for(String guild : FGMySQL.guilds)
		{
			log(guild);
		}
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
	
	public void log(String msg)
	{
		System.out.println(msg);
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
