package com.famousserver.FamousGuilds.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
	public static List<String> guilds = new ArrayList<String>();
	
	static
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(url + "mysql", user, pw);
			Statement st = con.createStatement();
			String query = "SHOW DATABASES";
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{
				String name = rs.getString("Database");
				if(name.startsWith("guild_"))
				{
					guilds.add(name.substring(6));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static FamousGuild getGuild(String name) throws SQLException
	{
		FamousGuild guild;
		String leader;
		HashMap<Integer, String> groups = new HashMap<Integer, String>();
		HashMap<Integer, String> members = new HashMap<Integer, String>();
		HashMap<Integer, Integer> membergroups = new HashMap<Integer, Integer>();
		con = DriverManager.getConnection(url + "guild_" + name, user, pw);
		Statement st = con.createStatement();
		String querygroups = "SELECT ID, name FROM groups";
		ResultSet rs = st.executeQuery(querygroups);
		while(rs.next())
		{
			groups.put(rs.getInt("ID"), rs.getString("name"));
		}
		String querymembers = "SELECT ID, name FROM members";
		rs = st.executeQuery(querymembers);
		while(rs.next())
		{
			members.put(rs.getInt("ID"), rs.getString("name"));
		}
		String querymembergroups = "SELECT memberID, groupID FROM membergroups";
		rs = st.executeQuery(querymembergroups);
		while(rs.next())
		{
			membergroups.put(rs.getInt("memberID"), rs.getInt("groupID"));
		}
		int groupID = getGroupID(name, "Leader");
		String query = "SELECT memberID FROM membergroups WHERE groupID LIKE '" + groupID + "'";
		rs = st.executeQuery(query);
		rs.next();
		int memberID = rs.getInt("memberID");
		st.close();
		con.close();
		leader = getMemberName(name, memberID);
		guild = new FamousGuild(name, groups, members, membergroups, leader);
		return guild;
	}
	
	public static List<FamousGuild> getGuilds() throws SQLException
	{
		List<FamousGuild> guilds1 = new ArrayList<FamousGuild>();
		for(String guild : guilds)
		{
			guilds1.add(getGuild(guild));
		}
		return guilds1;
	}
	
	public static HashMap<Integer, String> getGroups(String guild) throws SQLException
	{
		HashMap<Integer, String> groups = new HashMap<Integer, String>();
		con = DriverManager.getConnection(url + "guild_" + guild, user, pw);
		Statement st = con.createStatement();
		String query = "SELECT ID, name FROM groups";
		ResultSet rs = st.executeQuery(query);
		while(rs.next())
		{
			groups.put(rs.getInt("ID"), rs.getString("name"));
		}
		st.close();
		con.close();
		return groups;
	}
	
	public static HashMap<Integer, String> getMembers(String guild) throws SQLException
	{
		HashMap<Integer, String> members = new HashMap<Integer, String>();
		con = DriverManager.getConnection(url + "guild_" + guild, user, pw);
		Statement st = con.createStatement();
		String query = "SELECT ID, name FROM members";
		ResultSet rs = st.executeQuery(query);
		while(rs.next())
		{
			members.put(rs.getInt("ID"), rs.getString("name"));
		}
		st.close();
		con.close();
		return members;
	}
	
	public static int getMemberID(String guild, String member) throws SQLException
	{
		con = DriverManager.getConnection(url + "guild_" + guild, user, pw);
		Statement st = con.createStatement();
		String query = "SELECT ID FROM members WHERE name LIKE '" + member + "'";
		ResultSet rs = st.executeQuery(query);
		rs.next();
		int id = rs.getInt("ID");
		st.close();
		con.close();
		return id;
	}
	
	public static int getGroupID(String guild, String group) throws SQLException
	{
		con = DriverManager.getConnection(url + "guild_" + guild, user, pw);
		Statement st = con.createStatement();
		String query = "SELECT ID FROM groups WHERE name LIKE '" + group + "'";
		ResultSet rs = st.executeQuery(query);
		rs.next();
		int id = rs.getInt("ID");
		st.close();
		con.close();
		return id;
	}
	
	public static String getGroupName(String guild, int ID) throws SQLException
	{
		con = DriverManager.getConnection(url + "guild_" + guild, user, pw);
		Statement st = con.createStatement();
		String query = "SELECT name FROM groups WHERE ID LIKE '" + ID + "'";
		ResultSet rs = st.executeQuery(query);
		rs.next();
		String result = rs.getString(1);
		st.close();
		con.close();
		return result;
	}
	
	public static String getMemberName(String guild, int ID) throws SQLException
	{
		con = DriverManager.getConnection(url + "guild_" + guild, user, pw);
		Statement st = con.createStatement();
		String query = "SELECT name FROM members WHERE ID LIKE '" + ID + "'";
		ResultSet rs = st.executeQuery(query);
		rs.next();
		String result = rs.getString(1);
		st.close();
		con.close();
		return result;
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
	
	public static void removeMember(String guild, String member) throws SQLException
	{
		con = DriverManager.getConnection(url + "guild_" + guild, user, pw);
		Statement st = con.createStatement();
		String addmem = "DELETE FROM members WHERE name LIKE '" + member + "'"; 
		st.executeUpdate(addmem);
		st.close();
		con.close();
	}
	
	public static void addGroup(String guild, String group) throws SQLException
	{
		con = DriverManager.getConnection(url + "guild_" + guild, user, pw);
		Statement st = con.createStatement();
		String removegroup = "INSERT INTO groups (name) VALUE ('Leader')";
		st.executeUpdate(removegroup);
		st.close();
		con.close();
	}
	
	public static void removeGroup(String guild, String group) throws SQLException
	{
		con = DriverManager.getConnection(url + "guild_" + guild, user, pw);
		Statement st = con.createStatement();
		String removemember = "DELETE FROM groups WHERE name LIKE '" + group + "'";
		st.executeUpdate(removemember);
		st.close();
		con.close();
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
		guilds.add(guild.name);
	}
	
	public static void removeGuild(String guild) throws SQLException
	{
		con = DriverManager.getConnection(url + "mysql", user, pw);
		Statement st = con.createStatement();
		String update = "DROP DATABASE IF EXISTS " + guild;
		st.executeUpdate(update);
		st.close();
		con.close();
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
	
	public static void setLeader(String guild, String member) throws SQLException
	{
		int groupID = getGroupID(guild, "Leader");
		int memberID = getMemberID(guild, member);
		String update = "UPDATE membergroups SET memberID='" + memberID + "' WHERE groupID LIKE '" + groupID + "'";
		//TODO: assign new group to old leader
		con = DriverManager.getConnection(url + "guild_" + guild, user, pw);
		Statement st = con.createStatement();
		st.executeUpdate(update);
		st.close();
		con.close();
	}
	
	public static String getLeader(String guild) throws SQLException
	{
		int groupID = getGroupID(guild, "Leader");
		String query = "SELECT memberID FROM membergroups WHERE groupID LIKE '" + groupID + "'";
		con = DriverManager.getConnection(url + "guild_" + guild, user, pw);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		rs.next();
		int memberID = rs.getInt("memberID");
		st.close();
		con.close();
		String ret = getMemberName(guild, memberID);
		return ret;
	}
	
	//TODO: figure something out
	public static void setGuildLogo(String guild, String url)
	{
		
	}
	
	public static String getGuildLogo(String guild)
	{
		return "";
	}
}
