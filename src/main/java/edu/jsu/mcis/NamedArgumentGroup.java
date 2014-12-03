package edu.jsu.mcis;

import java.util.*;
public class NamedArgumentGroup 
{
	private String groupHeader;
	private List<String> group;
	
	public NamedArgumentGroup(String name)
	{
		groupHeader = name;
		group = new ArrayList<String>();
	}
	
	public void appendGroupMember(String name)
	{
		group.add(name);
	}
	
	public void removeGroupMember(String name)
	{
		group.remove(name);
	}
	
	public boolean checkGroup(String name)
	{
		if (group.contains(name))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}