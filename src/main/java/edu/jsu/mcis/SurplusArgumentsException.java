package edu.jsu.mcis;

import java.util.*;

public class SurplusArgumentsException extends RuntimeException
{
	String usage;
	String prog;
	String nextVal;
	Scanner arguments;
	
	public SurplusArgumentsException(List<String> arrayOfNames, String program, String nextValue, Scanner argScanner)
	{
		HelpInfoGenerator h = new HelpInfoGenerator();
		usage = h.getUsageLine(arrayOfNames, program);
		prog = program;
		nextVal = nextValue;
		arguments = argScanner;
	}
	
	public String toString()
	{
		String errorMessage = usage;
		errorMessage = errorMessage + "\n" + prog + ".java: error: unrecognized arguments: " + nextVal;
		while(arguments.hasNext())
		{
			nextVal = arguments.next();
			errorMessage = errorMessage + " " + nextVal;
		}
		return errorMessage;
	}
}