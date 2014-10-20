package edu.jsu.mcis;

import java.util.*;

public class ArgumentErrorHandler
{
	private HelpInfoGenerator h = new HelpInfoGenerator();
	
	public String buildStringTooFewArguments(List<String> arrayOfNames, String program, int i)
	{
		String errorMessage = h.getUsageLine(arrayOfNames, program);
		errorMessage = errorMessage + "\n" + program + ".java: error: the following arguments are required:";
		for(int j = i; j < arrayOfNames.size(); j++)
		{
			errorMessage = errorMessage + " " + arrayOfNames.get(j);
		}
		return errorMessage;
	}
	
	public String buildStringTooManyArguments(List<String> arrayOfNames, String program, String nextValue, Scanner argScanner)
	{
		String errorMessage = h.getUsageLine(arrayOfNames, program);
		errorMessage = errorMessage + "\n" + program + ".java: error: unrecognized arguments: " + nextValue;
		while(argScanner.hasNext())
		{
			nextValue = argScanner.next();
			errorMessage = errorMessage + " " + nextValue;
		}
		return errorMessage;
	}
}