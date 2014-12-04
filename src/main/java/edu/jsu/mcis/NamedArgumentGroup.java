package edu.jsu.mcis;

import java.util.*;

	/**
     * Class that collects a group of named arguments by setting them with 
	 * a specified header. 
     */

public class NamedArgumentGroup 
{
	private String groupHeader;
	private List<String> group;
	private int groupCount;
	
	
	/**
     * Constructor used to instantiate a list with a given name. The count 
	 * is automatically set to 1.
     * @param name Sets the group header.
     */
	public NamedArgumentGroup(String name)
	{
		groupHeader = name;
		group = new ArrayList<String>();
		groupCount = 1;
	}
	/**
	 * Adds a group member to the list based on its name.
     * @param name Uses the name field.
     */
	public void appendGroupMember(String name)
	{
		group.add(name);
	}
	/**
     * Checks to make sure the name being passed in is equal to the groups header. 
     * @param name Uses the name field to return either true or false.
     */
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
	/**
     * Returns the group member based on its location in the list.
     * @param index Used to find a specific member.
     */
	public String getGroupMember(int index)
	{
		return group.get(index);
	}
	/**
     * Returns true if the name is found within the group.
     * @param name Used to search the list. 
     */
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
	/**
     * Returns the current group count.
     */
	public int getCurrentGroupSize()
	{
		return groupCount;
	}
	/**
     * Returns the current list size.
     */
	public int getOverallGroupSize()
	{
		return group.size();
	}
}