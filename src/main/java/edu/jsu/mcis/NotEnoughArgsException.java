package edu.jsu.mcis;

import java.util.*;
public class NotEnoughArgsException extends RuntimeException
{
	String usage;
	String program;
	List<String> namesArray;
	int index = 0;
	
	public NotEnoughArgsException(String helpUsage, String prog, LinkedHashMap<String, Argument> hash, int i)
	{
		usage = helpUsage;
		program = prog;
		Iterator<String> hashKeys = hash.keySet().iterator();
		namesArray = new ArrayList<String>();
		while (hashKeys.hasNext())
		{
			namesArray.add(hashKeys.next());
		}
		index = i;
	}
	
	public String getUsage()
	{
		return usage;
	}
	
	public List<String> getNamesArray()
	{
		return namesArray;
	}
	
	public int getIndex()
	{
		return index;
	}

	public String toString()
	{
		String errorMessage = usage + "\n";
		errorMessage = errorMessage + program + ".java: error: the following arguments are required:";
		for(int j = index; j < namesArray.size(); j++)
		{
			errorMessage = errorMessage + " " + namesArray.get(j);
		}
		return errorMessage;
	}
}