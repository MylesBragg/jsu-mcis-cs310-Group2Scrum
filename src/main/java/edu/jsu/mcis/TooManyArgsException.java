package edu.jsu.mcis;

import java.util.*;

public class TooManyArgsException extends RuntimeException
{
	String usage;
	String program;
	String nextValue;
	Scanner args;
	
	public TooManyArgsException(String helpUsage, String prog, String nextVal, Scanner argScanner)
	{
		usage = helpUsage;
		program = prog;
		nextValue = nextVal;
		args = argScanner;
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