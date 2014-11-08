package edu.jsu.mcis;

import java.util.*;

public class TooManyArgumentsException extends RuntimeException
{
	String usage;
	String program;
	String nextValue;
	Scanner args;
	
	public TooManyArgumentsException(String helpUsage, String prog, String nextVal, Scanner argScanner)
	{
		usage = helpUsage;
		program = prog;
		nextValue = nextVal;
		args = argScanner;
	}
	
	public String getUsage()
	{
		return usage;
	}
	
	public String getNextValue()
	{
		return nextValue;
	}
	
	public Scanner getArgs()
	{
		return args;
	}
	
	public String toString()
	{
		String errorMessage = usage + "\n";
		errorMessage = errorMessage + program + ".java: error: unrecognized arguments: " + nextValue;
		while(args.hasNext())
		{
			nextValue = args.next();
			errorMessage = errorMessage + " " + nextValue;
		}
		return errorMessage;
	}
}