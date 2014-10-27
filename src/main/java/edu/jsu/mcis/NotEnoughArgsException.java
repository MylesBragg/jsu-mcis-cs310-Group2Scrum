package edu.jsu.mcis;

import java.util.*;

public class NotEnoughArgsException extends RuntimeException
{
	String usage;
	String program;
	List<String> namesArray;
	int index = 0;
	
	public NotEnoughArgsException(String helpUsage, String prog, List<String> arrayOfNames, int i)
	{
		usage = helpUsage;
		program = prog;
		namesArray = new ArrayList<String>();
		for(int j = 0; j < arrayOfNames.size(); j++)
		{
			namesArray.add(arrayOfNames.get(j));
		}
		index = i;
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