package com.famousserver.FamousGuilds.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.spout.api.util.config.Configuration;
import org.spout.api.util.config.ConfigurationNode;

public class Config extends Configuration {

	private List<ConfigurationNode> nodes1 = new ArrayList<ConfigurationNode>();
	
	public Config(File file) {
		super(file);
	}

	public void copyDefaults()
	{
		for(ConfigurationNode node : nodes1)
		{
			addNode(node);
		}
	}
	
	public void addDefault(String path, Object value)
	{
		ConfigurationNode node = new ConfigurationNode(path, value);
		if(!nodes1.contains(node) && !this.nodes.contains(node))
		{
			nodes1.add(node);
		}
	}
	
	public void reload()
	{
		this.save();
		this.load();
	}
	
	@SuppressWarnings("unchecked")
	public Set<String> getKeys(String path)
	{
		if(!path.contains("\\."))
		{
			Object o = root.get(path);
			if(o == null || !(o instanceof Map))
			{
				return null;
			}
			return ((Map<String, Object>)o).keySet();
		}
		Map<String, Object> nodes = this.root;
		String[] parts = path.split("\\.");
		for(int i=0; i<parts.length; i++)
		{
			Object o = nodes.get(parts[i]);
			
			if(o == null || !(o instanceof Map))
			{
				return null;
			}
			
			if(i == parts.length-1)
			{
				Set<String> keys = ((Map<String, Object>)o).keySet();
				return keys;
			}
			
			nodes = (Map<String, Object>)o;
		}
		return null;
	}
}
