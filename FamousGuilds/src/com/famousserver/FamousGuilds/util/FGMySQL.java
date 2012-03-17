package com.famousserver.FamousGuilds.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

import com.famousserver.FamousGuilds.FamousGuilds;

public class FGMySQL 
{
	private static FamousGuilds plugin = FamousGuilds.instance;
	static String user = plugin.getConfig().getString("MySQL.USER");
	static String pw = plugin.getConfig().getString("MySQL.PASSWORD");
	static String url = plugin.getConfig().getString("MySQL.URL");
	static String db = plugin.getConfig().getString("MySQL.DB");
	static Connection con = null;
	
	
	
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
	
	public static void addGuild(FamousGuild guild) throws SQLException
	{
		con = DriverManager.getConnection(url+db, user, pw);
		Statement st = con.createStatement();
		String createdb = "CREATE DATABASE IF NOT EXISTS guild_" + guild.name; 
		st.executeUpdate(createdb);
		String table1 = "CREATE TABLE IF NOT EXISTS groups(ID MEDIUMINT NOT NULL AUTO_INCREMENT, name varchar(30), PRIMARY KEY (ID))";
		String table2 = "CREATE TABLE IF NOT EXISTS members(ID MEDIUMINT NOT NULL AUTO_INCREMENT, name varchar(30), PRIMARY KEY (ID))";
		String table3 = "CREATE TABLE IF NOT EXISTS membergroups(member INT Unsigned Not Null References members(ID), group Int Unsigned Not Null References groups(ID),  Primary Key (member, group))";
		st.executeUpdate(table1);
		st.executeUpdate(table2);
		st.executeUpdate(table3);
		String addgroup = "INSERT INTO groups VALUES(name='Leader')"; 
		String addmem = "INSERT INTO members VALUES(name='" + guild.leader + "'"; 
		st.executeUpdate(addmem);
		st.executeUpdate(addgroup);
		String s = "SELECT ID FROM members WHERE name LIKE '" + guild.leader + "'";
		String ss = "SELECT ID FROM groups WHERE name LIKE 'Leader'";
		ResultSet rs = st.executeQuery(s);
		ResultSet rs2 = st.executeQuery(ss);
		int groupID = rs.getInt(0);
		int memberID = rs2.getInt(0);
		String addtomeme = "INSERT INTO membergroups VALUES('" + memberID + "', '" + groupID + "')";
		st.execute(addtomeme);
		st.close();
		con.close();
		
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
