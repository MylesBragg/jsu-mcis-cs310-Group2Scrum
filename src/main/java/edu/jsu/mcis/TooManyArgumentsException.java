package edu.jsu.mcis;

import java.util.*;

public class TooManyArgumentsException extends RuntimeException
{
	private String usage;
	private String program;
	private String nextValue;
	private Scanner argumentScanner;
	
	public TooManyArgumentsException(String helpUsage, String prog)
	{
		usage = helpUsage;
		program = prog;
	}
	public void setNextValue(String nextValue)
	{
		this.nextValue = nextValue;
	}
	
	public void setArgumentScanner(Scanner argumentScanner)
	{
		this.argumentScanner = argumentScanner;
	}
	public String toString()
	{
		String errorMessage = usage + "\n";
		errorMessage = errorMessage + program + ".java: error: unrecognized arguments: " + nextValue;
		while(argumentScanner.hasNext())
		{
			nextValue = argumentScanner.next();
			errorMessage = errorMessage + " " + nextValue;
		}
		return errorMessage;
	}
}