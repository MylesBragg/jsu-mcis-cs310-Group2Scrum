package edu.jsu.mcis;

import java.util.*;

public class ArgumentErrorHandler
{
	public String buildStringTooFewArguments(List<String> arrayOfNames, String program, int i)
	{
		String errorMessage = "usage: java "	+ program;
		for(int j = 0; j < arrayOfNames.size(); j++)
		{
			errorMessage = errorMessage + " " + arrayOfNames.get(j);
		}
		errorMessage = errorMessage + "\n" + program + ".java: error: the following arguments are required:";
		for(int j = i; j < arrayOfNames.size(); j++)
		{
			errorMessage = errorMessage + " " + arrayOfNames.get(j);
		}
		return errorMessage;
	}
	
	public String buildStringTooManyArguments(List<String> arrayOfNames, String program, String nextValue)
	{
		String errorMessage = "usage: java "	+ program;
		for(int i = 0; i < arrayOfNames.size(); i++)
		{
			errorMessage = errorMessage + " " + arrayOfNames.get(i);
		}
		errorMessage = errorMessage + "\n" + program + ".java: error: unrecognized arguments: " + nextValue;
		return errorMessage;
	}
}