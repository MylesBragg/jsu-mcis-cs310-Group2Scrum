package edu.jsu.mcis;

import java.util.*;

/**
  * Exception class used to specifically identify whether the arguments being provided exceed the 
  * required limit. 
  */
public class TooManyArgumentsException extends RuntimeException
{
	private String usage;
	private String program;
	private String nextValue;
	private Scanner argumentScanner;
	
	    /**
     * Exception that is thrown when their are too many arguments being 
     * supplied. 
     * @param helpUsage Sets the usage field.
     * @param prog Sets the program field.
     */
	public TooManyArgumentsException(String helpUsage, String prog)
	{
		usage = helpUsage;
		program = prog;
	}
	/**
     * Sets the argument in order to identify if their are too many arguments being used.
	 * @param nextValue Takes the next value and stores it.
     */
	public void setNextValue(String nextValue)
	{
		this.nextValue = nextValue;
	}
	/**
     * Scans the arguments in order to identify if their are too many being used. 
	 * @param argumentScanner Stores the scanner information.
     */
	public void setArgumentScanner(Scanner argumentScanner)
	{
		this.argumentScanner = argumentScanner;
	}
	   /**
     * Displays an error message when their are too many arguments given.
     * @return Returns a message describing the error.
     */
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