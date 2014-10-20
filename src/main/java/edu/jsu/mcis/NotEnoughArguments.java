package edu.jsu.mcis;

import java.util.*;

public class NotEnoughArguments extends RuntimeException
{
	List<String> namesArray;
	String usage;
	String prog;
	int index = 0;
	
	public NotEnoughArguments(List<String> arrayOfNames, String program, int i)
	{
		HelpInfoGenerator h = new HelpInfoGenerator();
		for(int j = 0; j < arrayOfNames.size(); j++)
		{
			namesArray.add(arrayOfNames.get(j));
		}
		usage = h.getUsageLine(arrayOfNames, program);
		prog = program;
		index = i;
	}
	
	public String toString()
	{
		String errorMessage = usage;
		errorMessage = errorMessage + "\n" + prog + ".java: error: the following arguments are required:";
		for(int j = index; j < namesArray.size(); j++)
		{
			errorMessage = errorMessage + " " + namesArray.get(j);
		}
		return errorMessage;
	}
}