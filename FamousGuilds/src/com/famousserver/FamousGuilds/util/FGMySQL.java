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
	static Connection con = null;
	
	static
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
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
	
	public static void addMember(String guild, String member) throws SQLException
	{
		con = DriverManager.getConnection(url + "guild_" + guild, user, pw);
		Statement st = con.createStatement();
		String addmem = "INSERT INTO members (name) VALUE('" + member + "')"; 
		st.executeUpdate(addmem);
		st.close();
		con.close();
	}
	
	public static void removeMember(String guild, String member)
	{
		
	}
	
	public static void addGroup(String guild, String group) throws SQLException
	{
		con = DriverManager.getConnection(url + "guild_" + guild, user, pw);
		Statement st = con.createStatement();
		String addgroup = "INSERT INTO groups (name) VALUE ('Leader')";
		st.executeUpdate(addgroup);
		st.close();
		con.close();
	}
	
	public static void removeGroup(String guild, String group)
	{
		
	}
	
	public static void addGuild(FamousGuild guild) throws SQLException
	{
		con = DriverManager.getConnection(url + "mysql", user, pw);
		Statement st = con.createStatement();
		String createdb = "CREATE DATABASE IF NOT EXISTS guild_" + guild.name; 
		st.executeUpdate(createdb);
		st.close();
		con.close();
		con = DriverManager.getConnection(url + "guild_" + guild.name, user, pw);
		st = con.createStatement();
		String table1 = "CREATE TABLE IF NOT EXISTS groups(ID MEDIUMINT NOT NULL AUTO_INCREMENT, name varchar(30), PRIMARY KEY (ID))";
		String table2 = "CREATE TABLE IF NOT EXISTS members(ID MEDIUMINT NOT NULL AUTO_INCREMENT, name varchar(30), PRIMARY KEY (ID))";
		String table3 = "CREATE TABLE IF NOT EXISTS membergroups(memberID INT Unsigned Not Null References members(ID), groupID Int Unsigned Not Null References groups(ID),Primary Key (memberID, groupID))";
		st.executeUpdate(table1);
		st.executeUpdate(table2);
		st.executeUpdate(table3);
		st.close();
		con.close();
		addGroup(guild.name, "Leader");
		addMember(guild.name, guild.leader);
		assignGroup(guild.name, guild.leader, "Leader");
	}
	
	public static void removeGuild(FamousGuild guild)
	{
		
	}
	
	public static void assignGroup(String guild, String member, String group) throws SQLException
	{
		con = DriverManager.getConnection(url + "guild_" + guild, user, pw);
		Statement st = con.createStatement();
		String s = "SELECT ID FROM members WHERE name LIKE '" + member + "'";
		String ss = "SELECT ID FROM groups WHERE name LIKE 'Leader'";
		ResultSet rs = st.executeQuery(s);
		rs.next();
		int memberID = rs.getInt(1);
		ResultSet rs2 = st.executeQuery(ss);
		rs2.next();
		int groupID = rs2.getInt(1);
		st.close();
		con.close();
		assignGroup(guild, memberID, groupID);
	}
	
	public static void assignGroup(String guild, int memberID, int groupID) throws SQLException
	{
		con = DriverManager.getConnection(url + "guild_" + guild, user, pw);
		Statement st = con.createStatement();
		String addtomeme = "INSERT INTO membergroups VALUES('" + memberID + "', '" + groupID + "')";
		st.executeUpdate(addtomeme);
		st.close();
		con.close();
	}
	
	public static void assignGroup(String guild, String member, int groupID) throws SQLException
	{
		con = DriverManager.getConnection(url + "guild_" + guild, user, pw);
		Statement st = con.createStatement();
		String s = "SELECT ID FROM members WHERE name LIKE '" + member + "'";
		ResultSet rs = st.executeQuery(s);
		rs.next();
		int memberID = rs.getInt(1);
		st.close();
		con.close();
		assignGroup(guild, memberID, groupID);
	}
	
	public static void assignGroup(String guild, int memberID, String group) throws SQLException
	{
		con = DriverManager.getConnection(url + "guild_" + guild, user, pw);
		Statement st = con.createStatement();
		String s = "SELECT ID FROM groups WHERE name LIKE '" + group + "'";
		ResultSet rs = st.executeQuery(s);
		rs.next();
		int groupID = rs.getInt(1);
		st.close();
		con.close();
		assignGroup(guild, memberID, groupID);
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
