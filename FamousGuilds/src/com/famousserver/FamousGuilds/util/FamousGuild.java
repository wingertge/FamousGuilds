package com.famousserver.FamousGuilds.util;

import java.util.HashMap;

public class FamousGuild {

	public String name;
	public HashMap<Integer, String> groups;
	public HashMap<Integer, String> members;
	public HashMap<Integer, Integer> membergroups;
	public String leader;
	
	public FamousGuild(String name, HashMap<Integer, String> groups, HashMap<Integer, String> members, HashMap<Integer, Integer> membergroups, String leader)
	{
		this.name = name;
		this.groups = groups;
		this.members = members;
		this.membergroups = membergroups;
		this.leader = leader;
	}
}
