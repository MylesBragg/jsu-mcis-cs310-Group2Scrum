package edu.jsu.mcis;

import java.util.*;
public class NotEnoughArgumentsException extends RuntimeException
{
	private String usage;
	private String programName;
	private List<String> namesArray;
	private int index = 0;
	
	public NotEnoughArgumentsException(String helpUsage, String programName)
	{
		usage = helpUsage;
		this.programName = programName;
	}
	
	public void setArgumentsRequired(Map<String, PositionalArgument> hash)
	{
		Iterator<String> hashKeys = hash.keySet().iterator();
		namesArray = new ArrayList<String>();
		while (hashKeys.hasNext())
		{
			namesArray.add(hashKeys.next());
		}
	}
	
	public void setCurrentIndex(int currentIndex)
	{
		index = currentIndex;
	}
	
	public String toString()
	{
		String errorMessage = usage + "\n";
		errorMessage = errorMessage + programName + ".java: error: the following arguments are required:";
		for(int j = index; j < namesArray.size(); j++)
		{
			errorMessage = errorMessage + " " + namesArray.get(j);
		}
		return errorMessage;
	}
}