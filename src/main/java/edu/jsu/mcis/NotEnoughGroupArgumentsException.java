package edu.jsu.mcis;

import java.util.*;
public class NotEnoughGroupArgumentsException extends RuntimeException
{
	private String usage;
	private String programName;
	private List<String> namesArray;
	private int index = 0;
	private String currentGroupHeader;
	
	public NotEnoughGroupArgumentsException(String helpUsage, String programName)
	{
		usage = helpUsage;
		this.programName = programName;
	}
	
	public void setArgumentsRequired(Map<String, NamedArgumentGroup> hash)
	{
		
		namesArray = new ArrayList<String>();
		for(int i = 0; i < hash.get(currentGroupHeader).getOverallGroupSize(); i++)
		{
			namesArray.add(hash.get(currentGroupHeader).getGroupMember(i));
		}
	}
	public void setCurrentGroupHeader(String currentGroupHeader)
	{
		this.currentGroupHeader = currentGroupHeader;
	}
	public void setCurrentIndex(int currentIndex)
	{
		index = currentIndex;
	}
	
	public String toString()
	{
		String errorMessage = usage + "\n";
		errorMessage = errorMessage + programName + ".java: error: the following group arguments from, " + currentGroupHeader + " group, are required:";
		for(int j = index; j < namesArray.size(); j++)
		{
			errorMessage = errorMessage + " " + namesArray.get(j);
		}
		return errorMessage;
	}
}