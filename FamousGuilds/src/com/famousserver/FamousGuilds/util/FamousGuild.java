package com.famousserver.FamousGuilds.util;

import java.util.HashMap;
import java.util.Map;

public class FamousGuild {

	public String name;
	public HashMap<Integer, String> groups;
	public HashMap<Integer, String> members;
	public HashMap<Integer, Integer> membergroups;
	public String leader;
	
	public FamousGuild(String name, Map<Integer, String> groups, Map<Integer, String> members, Map<Integer, Integer> membergroups, String leader)
	{
		this.name = name;
		this.groups = (HashMap<Integer, String>) groups;
		this.members = (HashMap<Integer, String>) members;
		this.membergroups = (HashMap<Integer, Integer>) membergroups;
		this.leader = leader;
	}
}
