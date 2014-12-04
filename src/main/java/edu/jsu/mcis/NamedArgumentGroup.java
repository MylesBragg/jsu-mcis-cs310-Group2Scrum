package edu.jsu.mcis;

import java.util.*;
public class NamedArgumentGroup 
{
	private String groupHeader;
	private List<String> group;
	private int groupCount;
	
	public NamedArgumentGroup(String name)
	{
		groupHeader = name;
		group = new ArrayList<String>();
		groupCount = 1;
	}
	
	public void appendGroupMember(String name)
	{
		group.add(name);
	}
	
	public boolean checkGroupHeader(String name)
	{
		if (name.equals(groupHeader))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public String getGroupMember(int index)
	{
		return group.get(index);
	}
	public boolean checkGroup(String name)
	{
		if (group.contains(name))
		{
			groupCount++;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public int getCurrentGroupSize()
	{
		return groupCount;
	}
	public int getOverallGroupSize()
	{
		return group.size();
	}
}