package com.famousserver.FamousGuilds.util;

import java.util.HashMap;
import java.util.List;

import com.famousserver.FamousGuilds.FamousGuilds;

public class FGMySQL 
{
	private static FamousGuilds plugin = FamousGuilds.instance;

	public static FamousGuild getGuild(String name)
	{
		return null;
	}
	
	public static List<FamousGuild> getGuilds()
	{
		return null;
	}
	
	public static void saveGuild(FamousGuild guild)
	{
		
	}
	
	public static void saveGuilds(List<FamousGuild> guilds)
	{
		
	}
	
	public static HashMap<Integer, String> getGroups(String guild)
	{
		return null;
	}
	
	public static HashMap<Integer, String> getMembers(String guild)
	{
		return null;
	}
	
	public static int getMemberID(String guild, String member)
	{
		return 0;
	}
	
	public static int getGroupID(String guild, String group)
	{
		return 0;
	}
	
	public static String getGroupName(String guild, int ID)
	{
		return "";
	}
	
	public static String getMemberName(String guild, int ID)
	{
		return "";
	}
	
	public static void addMember(String guild, String member)
	{
		
	}
	
	public static void removeMember(String guild, String member)
	{
		
	}
	
	public static void addGroup(String guild, String group)
	{
		
	}
	
	public static void removeGroup(String guild, String group)
	{
		
	}
	
	public static void addGuild(FamousGuild guild)
	{
		
	}
	
	public static void removeGuild(FamousGuild guild)
	{
		
	}
	
	public static void assignGroup(String guild, String member, String group)
	{
		
	}
	
	public static void assignGroup(String guild, int memberID, int groupID)
	{
		
	}
	
	public static void assignGroup(String guild, String member, int groupID)
	{
		
	}
	
	public static void assignGroup(String guild, int memberID, String group)
	{
		
	}
	
	//TODO: figure something out
	public static void setLeader(String guild, String member)
	{
		
	}
	
	public static String getLeader(String guild)
	{
		return "";
	}
	
	public static void setGuildLogo(String guild, String url)
	{
		
	}
	
	public static String getGuildLogo(String guild)
	{
		return "";
	}
}
